package com.ciembro.healthappfront;

import com.ciembro.healthappfront.dto.UserToRegisterDto;
import com.ciembro.healthappfront.service.UserService;
import com.ciembro.healthappfront.service.WeatherApiService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;

import java.util.List;

@Route("register")
public class RegisterForm extends VerticalLayout {

    private final UserService userService = new UserService();
    private WeatherApiService weatherApiService = WeatherApiService.INSTANCE;
    private final TextField username = new TextField("Username");
    private TextField location = new TextField("Wpisz miejscowość");
    private final TextField email = new TextField("Email");
    private final PasswordField password = new PasswordField("Password");
    private final Button registerButton = new Button("Register");
    private final Button loginPageButton = new Button("Back to login page");
    private Button searchButton = new Button("Szukaj");
    private Button backButton = new Button("Wróć");
    private ComboBox<String> locationsComboBox = new ComboBox<>();
    private final Binder<UserToRegisterDto> binder = new Binder<>(UserToRegisterDto.class);
    private UserToRegisterDto userDto = new UserToRegisterDto();
    private HorizontalLayout searchLayout = new HorizontalLayout(location,searchButton);
    private HorizontalLayout comboBoxLayout = new HorizontalLayout(locationsComboBox, backButton);

    public RegisterForm(){
        binder.forField(username).bind(UserToRegisterDto::getUsername, UserToRegisterDto::setUsername);
        binder.forField(location).bind(UserToRegisterDto::getLocation, UserToRegisterDto::setLocation);
        binder.forField(email).bind(UserToRegisterDto::getEmail, UserToRegisterDto::setEmail);
        binder.forField(password).bind(UserToRegisterDto::getPassword, UserToRegisterDto::setPassword);
        setUserDto(userDto);

        registerButton.addClickListener(e -> registerUser());
        loginPageButton.addClickListener(e -> UI.getCurrent().getPage().setLocation("/"));

        searchLayout.setAlignItems(Alignment.END);
        comboBoxLayout.setAlignItems(Alignment.END);
        showSearch();
        username.setRequired(true);
        location.setRequired(true);
        email.setRequired(true);
        password.setRequired(true);

        searchButton.addClickListener(e -> {
            String loc = location.getValue();
            searchLocations(loc);
        });

        backButton.addClickListener(e -> showSearch());

        locationsComboBox.addValueChangeListener(e -> {
            showSearch();
            location.setValue(locationsComboBox.getValue());
        });

        add(
                username,
                email,
                password,
                searchLayout,
                comboBoxLayout,
                new HorizontalLayout(registerButton, loginPageButton)
        );
    }

    private void searchLocations(String loc){
        List<String> matchingLocations = weatherApiService.getLocations(loc);
        locationsComboBox.setItems(matchingLocations);
        hideSearch();
    }

    private void hideSearch(){
        searchLayout.setVisible(false);
        comboBoxLayout.setVisible(true);
    }

    private void showSearch(){
        searchLayout.setVisible(true);
        comboBoxLayout.setVisible(false);
    }

    public void setUserDto(UserToRegisterDto userDto) {
        binder.setBean(userDto);
    }

    private void registerUser(){
        userDto = binder.getBean();
        boolean isRegistered = userService.registerUser(userDto);
        if (isRegistered){
            add(new Label("You've registered successfully. Go back to login page."));
        } else {
            add(new Label("Error during registering new user. Try again."));
        }
        username.clear();
        email.clear();
        password.clear();
    }
}

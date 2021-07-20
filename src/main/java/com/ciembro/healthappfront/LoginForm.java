package com.ciembro.healthappfront;

import com.ciembro.healthappfront.dto.UserDto;
import com.ciembro.healthappfront.service.UserService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

public class LoginForm extends VerticalLayout {

    private MainView parent;
    private final UserService userService = new UserService();
    private final TextField username = new TextField("Użytkownik");
    private final PasswordField password = new PasswordField("Hasło");
    private final Button loginButton = new Button("Zaloguj się");
    private final Button registerButton = new Button("Zarejestruj się");
    private final Binder<UserDto> binder = new Binder<>(UserDto.class);
    private UserDto userDto = new UserDto();
    private Label badCredentialsLabel = new Label("Nie udało się zalogować.");

    public LoginForm(MainView parent){
        this.parent = parent;
        binder.forField(username).bind(UserDto::getUsername, UserDto::setUsername);
        binder.forField(password).bind(UserDto::getPassword, UserDto::setPassword);
        setUserDto(userDto);

        loginButton.addClickListener(e -> loginUser());
        registerButton.addClickListener(e -> UI.getCurrent().getPage().setLocation("register"));

        add (   new H1("Zaloguj się"),
                username,
                password,
                new HorizontalLayout(loginButton, registerButton),
                badCredentialsLabel
        );
        badCredentialsLabel.setVisible(false);
    }

    public void setUserDto(UserDto userDto) {
        binder.setBean(userDto);
    }

    private void loginUser(){
        UserDto userDto = binder.getBean();
        boolean isAuthenticated = userService.authenticateUser(userDto);

        if (!isAuthenticated){
            badCredentialsLabel.setVisible(true);
        } else {
            parent.setWelcomeHeader();
            parent.showMenu();
            setVisible(false);
        }
    }

}

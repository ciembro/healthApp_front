package com.ciembro.healthappfront;

import com.ciembro.healthappfront.domain.UserToRegisterDto;
import com.ciembro.healthappfront.service.UserService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;

@Route("register-view")
public class RegisterForm extends VerticalLayout {

    private final UserService userService = new UserService();
    private final TextField username = new TextField("Username");
    private final TextField email = new TextField("Email");
    private final PasswordField password = new PasswordField("Password");
    private final Button registerButton = new Button("Register");
    private final Button loginPageButton = new Button("Back to login page");
    private final Binder<UserToRegisterDto> binder = new Binder<>(UserToRegisterDto.class);
    private UserToRegisterDto userDto = new UserToRegisterDto();

    public RegisterForm(){
        binder.forField(username).bind(UserToRegisterDto::getUsername, UserToRegisterDto::setUsername);
        binder.forField(email).bind(UserToRegisterDto::getEmail, UserToRegisterDto::setEmail);
        binder.forField(password).bind(UserToRegisterDto::getPassword, UserToRegisterDto::setPassword);
        setUserDto(userDto);

        registerButton.addClickListener(e -> registerUser());
        loginPageButton.addClickListener(e -> UI.getCurrent().getPage().setLocation("login"));


        add(
                username,
                email,
                password,
                new HorizontalLayout(registerButton, loginPageButton)
        );
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
        username.setValue("");
        email.setValue("");
        password.setValue("");
    }
}

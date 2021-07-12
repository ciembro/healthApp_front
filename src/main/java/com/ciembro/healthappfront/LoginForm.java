package com.ciembro.healthappfront;

import com.ciembro.healthappfront.domain.UserDto;
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
import com.vaadin.flow.router.Route;

@Route("login")
public class LoginForm extends VerticalLayout {

    private final UserService userService = new UserService();
    private final TextField username = new TextField("Username");
    private final PasswordField password = new PasswordField("Password");
    private final Button loginButton = new Button("Login");
    private final Button registerButton = new Button("Register");
    private final Binder<UserDto> binder = new Binder<>(UserDto.class);
    private UserDto userDto = new UserDto();

    public LoginForm(){

        binder.forField(username).bind(UserDto::getUsername, UserDto::setUsername);
        binder.forField(password).bind(UserDto::getPassword, UserDto::setPassword);
        setUserDto(userDto);

        loginButton.addClickListener(e -> loginUser());
        registerButton.addClickListener(e -> UI.getCurrent().getPage().setLocation("register-view"));

        add (
                new H1("Welcome"),
                username,
                password,
                new HorizontalLayout(loginButton, registerButton)
        );
    }

    public void setUserDto(UserDto userDto) {
        binder.setBean(userDto);
    }

    private void loginUser(){
        UserDto userDto = binder.getBean();
        Label label = new Label("Bad credentials");
        boolean isAuthenticated = userService.authenticateUser(userDto);
        if (!isAuthenticated){
            add(label);

        } else {
            UI.getCurrent().getPage().setLocation("user-view");
        }

    }

}

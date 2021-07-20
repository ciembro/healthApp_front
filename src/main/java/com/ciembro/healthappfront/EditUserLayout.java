package com.ciembro.healthappfront;

import com.ciembro.healthappfront.service.UserService;
import com.ciembro.healthappfront.service.WeatherApiService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.server.VaadinSession;

import java.util.List;


public class EditUserLayout extends VerticalLayout {

    private MainView parent;
    private WeatherApiService weatherApiService = WeatherApiService.INSTANCE;
    private UserService userService = new UserService();
    private final TextField username = new TextField("Username");
    private TextField location = new TextField("Wpisz miejscowość");
    private Button searchButton = new Button("Szukaj");
    private Button backButton = new Button("Wróć");
    private Button acceptButton = new Button("Zaakceptuj zmiany");
    private ComboBox<String> locationsComboBox = new ComboBox<>();
    private HorizontalLayout searchLayout = new HorizontalLayout(location,searchButton);
    private HorizontalLayout comboBoxLayout = new HorizontalLayout(locationsComboBox, backButton);

    public EditUserLayout(MainView parent) {
        this.parent = parent;
        searchLayout.setAlignItems(Alignment.END);
        comboBoxLayout.setAlignItems(Alignment.END);

        showSearch();

        searchButton.addClickListener(e -> {
            String loc = location.getValue();
            searchLocations(loc);
            hideSearch();
        });

        backButton.addClickListener(e -> showSearch());
        acceptButton.addClickListener(e-> save());

        locationsComboBox.addValueChangeListener(e -> {
            showSearch();
            if (locationsComboBox.getValue() != null)
                location.setValue(locationsComboBox.getValue());
            locationsComboBox.clear();
        });

        add(username,
                searchLayout,
                comboBoxLayout,
                acceptButton);
    }

    public void setUpForm(){
        String username = (String) VaadinSession.getCurrent().getAttribute("Użytkownik");
        this.username.setValue(username);
        this.username.setReadOnly(true);
    }

    private void save(){
        if (!location.isEmpty()){
            userService.changeUserLocation(location.getValue());
            setVisible(false);
        }
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
}

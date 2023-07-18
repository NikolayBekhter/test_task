package com.example.test.views;

import com.example.test.servises.CounterService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route
public class MainView extends VerticalLayout {

    private final CounterService service;

    TextField textField;
    Button increaseButton;

    public MainView(CounterService service) {
        this.service = service;

        textField = new TextField("Value");
        textField.setValue(String.valueOf(service.getCurrentValue()));
        textField.addValueChangeListener(event -> updateValue());

        increaseButton = new Button("Increase");
        increaseButton.addClickListener(event -> {
            service.incrementValue();
            textField.setValue(String.valueOf(service.getCurrentValue()));
            Notification.show("Value incremented!");
        });

        add(textField, increaseButton);
    }

    void updateValue() {
        try {
            int newValue = Integer.parseInt(textField.getValue());
            service.setCurrentValue(newValue);
            Notification.show("Value updated!");
        } catch (NumberFormatException e) {
            Notification.show("Invalid input! Please enter a valid number.");
        }
    }
}

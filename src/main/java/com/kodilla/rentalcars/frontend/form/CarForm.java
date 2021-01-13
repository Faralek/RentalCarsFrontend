package com.kodilla.rentalcars.frontend.form;

import com.kodilla.rentalcars.frontend.client.CarClient;
import com.kodilla.rentalcars.frontend.domain.Car;
import com.kodilla.rentalcars.frontend.domain.Cart;
import com.kodilla.rentalcars.frontend.domain.Order;
import com.kodilla.rentalcars.frontend.domain.User;
import com.kodilla.rentalcars.frontend.gui.CarGui;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.BigDecimalField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

public class CarForm extends FormLayout {

    private TextField description = new TextField("description");
    private TextField name = new TextField("name");
    private BigDecimalField dailyPrice = new BigDecimalField("dailyPrice");

    private Button save = new Button("Save");
    private Button delete = new Button("Delete");

    private CarClient carClient = CarClient.getInstance();

    Binder<Car> binder = new Binder<>(Car.class);

    public CarForm(CarGui carGui) {

        HorizontalLayout buttons = new HorizontalLayout(save, delete);
        add(description, name, dailyPrice, buttons);

        save.addClickListener(event -> save(carGui));
        delete.addClickListener(event -> delete(carGui));

        binder.bindInstanceFields(this);
    }

    private void save(CarGui carGui) {
        Car car = binder.getBean();
        if (car.getCart()==null) {
            Cart cart = new Cart();
            car.setCart(cart);
        }
        if (car.getOrder()==null) {
            Order order = new Order();
            car.setOrder(order);
        }
        carClient.addCar(car);
        carGui.refresh(carClient);
        setCar(null);
    }

    private void delete(CarGui carGui) {
        Car car = binder.getBean();
        carClient.deleteCar(car.getId());
        carGui.refresh(carClient);
        setCar(null);
    }

    public void setCar(Car car) {
        binder.setBean(car);
        if (car == null) {
            setVisible(false);
        } else {
            setVisible(true);
            name.focus();
        }
    }

}

package com.kodilla.rentalcars.frontend.form;

import com.kodilla.rentalcars.frontend.client.CarClient;
import com.kodilla.rentalcars.frontend.client.CartClient;
import com.kodilla.rentalcars.frontend.domain.Car;
import com.kodilla.rentalcars.frontend.domain.Cart;
import com.kodilla.rentalcars.frontend.gui.CartGui;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.BigDecimalField;
import com.vaadin.flow.data.binder.Binder;

import java.util.List;

public class CartForm extends FormLayout {

    private BigDecimalField sum = new BigDecimalField("sum");
    private Grid grid = new Grid<>(Car.class);
    private Grid carsFromCartGrid = new Grid<>(Car.class);
    private Button save = new Button("Save");
    private Button delete = new Button("Delete");
    private Button addToCart = new Button("Add to cart");
    private Button refreshCarsInCart = new Button("refresh cars in cart");

    private CartClient cartClient = CartClient.getInstance();
    private CarClient carClient = CarClient.getInstance();

    Binder<Cart> binder = new Binder<>(Cart.class);

    public CartForm(CartGui cartGui) {
        add(grid);
        add(carsFromCartGrid);
        HorizontalLayout buttons = new HorizontalLayout(save, delete, addToCart,refreshCarsInCart);
        add(sum, buttons);

        save.addClickListener(event -> save(cartGui));
        delete.addClickListener(event -> delete(cartGui));
        addToCart.addClickListener(event -> cartClient.addCarToCart(
                grid.getSelectionModel().getFirstSelectedItem().get(),
                binder.getBean().getId(),1));
        refreshCarsInCart.addClickListener(event -> {
            refreshCarsFromCart(cartClient, binder.getBean().getId());
        });
        binder.bindInstanceFields(this);
        refreshCars(carClient);
    }

    private void save(CartGui cartGui) {
        Cart cart = binder.getBean();

        cartClient.addCart(cart);
        cartGui.refresh(cartClient);
        setCart(null);
    }

    private void delete(CartGui cartGui) {
        Cart cart = binder.getBean();
        cartClient.deleteCart(cart.getId());
        cartGui.refresh(cartClient);
        setCart(null);
    }

    public void setCart(Cart cart) {
        binder.setBean(cart);
        if (cart == null) {
            setVisible(false);
        } else {
            setVisible(true);
        }
    }

    public void refreshCars(CarClient carClient) {
        List<Car> carList;
        carList = carClient.getCarsFromApi();
        grid.setItems(carList);
    }

    public void refreshCarsFromCart(CartClient cartClient, Long id) {
        List<Car> carListFromCart;
        carListFromCart = cartClient.getCarsFromCart(id);
        carsFromCartGrid.setItems(carListFromCart);
    }

}

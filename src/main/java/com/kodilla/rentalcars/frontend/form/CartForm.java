package com.kodilla.rentalcars.frontend.form;

import com.kodilla.rentalcars.frontend.client.CartClient;
import com.kodilla.rentalcars.frontend.domain.Cart;
import com.kodilla.rentalcars.frontend.gui.CartGui;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.BigDecimalField;
import com.vaadin.flow.data.binder.Binder;

public class CartForm extends FormLayout {

    private BigDecimalField sum = new BigDecimalField("sum");

    private Button save = new Button("Save");
    private Button delete = new Button("Delete");

    private CartClient cartClient = CartClient.getInstance();

    Binder<Cart> binder = new Binder<>(Cart.class);

    public CartForm(CartGui cartGui) {

        HorizontalLayout buttons = new HorizontalLayout(save, delete);
        add(sum, buttons);

        save.addClickListener(event -> save(cartGui));
        delete.addClickListener(event -> delete(cartGui));

        binder.bindInstanceFields(this);
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

}

package com.kodilla.rentalcars.frontend.gui;

import com.kodilla.rentalcars.frontend.client.CarClient;
import com.kodilla.rentalcars.frontend.client.CartClient;
import com.kodilla.rentalcars.frontend.domain.Car;
import com.kodilla.rentalcars.frontend.domain.Cart;
import com.kodilla.rentalcars.frontend.form.CarForm;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Route("carts")
public class CartGui extends VerticalLayout {

    private Grid grid = new Grid<>(Cart.class);
    private Button addNewCart = new Button("Add new cart");
    private Button deleteCart = new Button("delete cart");

    @Autowired
    public CartGui(CartClient cartClient) {

        grid.setColumns("id","sum", "cars");

        addNewCart.addClickListener(e -> {
            grid.asSingleSelect().clear();
            cartClient.addCart(new Cart());
            refresh(cartClient);
        });

        deleteCart.addClickListener(e -> {
            grid.asSingleSelect().clear();
            grid.getSelectionModel().getFirstSelectedItem();
            Cart cart = (Cart) grid.getSelectionModel().getFirstSelectedItem().get();
            cartClient.deleteCart(cart.getId());
        });

        HorizontalLayout toolbar = new HorizontalLayout(addNewCart,deleteCart);

        HorizontalLayout mainContent = new HorizontalLayout(grid);
        mainContent.setSizeFull();
        grid.setSizeFull();

        add(toolbar, mainContent);
        setSizeFull();
        refresh(cartClient);



    }

    public void refresh(CartClient cartClient) {
        List<Cart> cartList;
        cartList = cartClient.getCartsFromApi();
        grid.setItems(cartList);
    }

}

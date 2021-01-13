package com.kodilla.rentalcars.frontend.gui;

import com.kodilla.rentalcars.frontend.client.CartClient;
import com.kodilla.rentalcars.frontend.domain.Cart;
import com.kodilla.rentalcars.frontend.form.CartForm;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Route("carts")
public class CartGui extends VerticalLayout {

    private Grid grid = new Grid<>(Cart.class);
    private CartForm form = new CartForm(this);
    private Button addNewCart = new Button("Add new cart");

    @Autowired
    public CartGui(CartClient cartClient) {

        grid.setColumns("id", "sum", "cars");

        addNewCart.addClickListener(e -> {
            grid.asSingleSelect().clear();
            form.setCart(new Cart());
        });
        HorizontalLayout toolbar = new HorizontalLayout(addNewCart);

        HorizontalLayout mainContent = new HorizontalLayout(grid, form);
        mainContent.setSizeFull();
        grid.setSizeFull();

        add(toolbar, mainContent);
        form.setCart(null);
        setSizeFull();
        refresh(cartClient);

        grid.asSingleSelect().addValueChangeListener(event -> form.setCart((Cart) grid.asSingleSelect().getValue()));
    }

    public void refresh(CartClient cartClient) {
        List<Cart> cartList;
        cartList = cartClient.getCartsFromApi();
        grid.setItems(cartList);
    }

}

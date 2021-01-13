package com.kodilla.rentalcars.frontend.gui;

import com.kodilla.rentalcars.frontend.client.OrderClient;
import com.kodilla.rentalcars.frontend.domain.Order;
import com.kodilla.rentalcars.frontend.form.OrderForm;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Route("orders")
public class OrderGui extends VerticalLayout {

    private Grid grid = new Grid<>(Order.class);
    private OrderForm form = new OrderForm(this);
    private Button addNewOrder = new Button("Add new order");

    @Autowired
    public OrderGui(OrderClient orderClient) {
        grid.setColumns("id", "cart", "cars", "user", "sum");

        addNewOrder.addClickListener(e -> {
            grid.asSingleSelect().clear();
            form.setOrder(new Order());
        });
        HorizontalLayout toolbar = new HorizontalLayout(addNewOrder);

        HorizontalLayout mainContent = new HorizontalLayout(grid, form);
        mainContent.setSizeFull();
        grid.setSizeFull();

        add(toolbar, mainContent);
        form.setOrder(null);
        setSizeFull();
        refresh(orderClient);

        grid.asSingleSelect().addValueChangeListener(event -> form.setOrder((Order) grid.asSingleSelect().getValue()));
    }

    public void refresh(OrderClient orderClient) {
        List<Order> orderList;
        orderList = orderClient.getOrdersFromApi();
        grid.setItems(orderList);
    }
}


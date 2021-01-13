package com.kodilla.rentalcars.frontend.form;

import com.kodilla.rentalcars.frontend.client.OrderClient;
import com.kodilla.rentalcars.frontend.domain.Order;
import com.kodilla.rentalcars.frontend.gui.OrderGui;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.BigDecimalField;
import com.vaadin.flow.data.binder.Binder;

public class OrderForm extends FormLayout {

    private BigDecimalField sum = new BigDecimalField("sum");

    private Button save = new Button("Save");
    private Button delete = new Button("Delete");

    private OrderClient orderClient = OrderClient.getInstance();

    Binder<Order> binder = new Binder<>(Order.class);

    public OrderForm(OrderGui orderGui) {

        HorizontalLayout buttons = new HorizontalLayout(save, delete);
        add(buttons);

        save.addClickListener(event -> save(orderGui));
        delete.addClickListener(event -> delete(orderGui));

        binder.bindInstanceFields(this);
    }

    private void save(OrderGui orderGui) {
        Order order = binder.getBean();
        orderClient.addOrder(order);
        orderGui.refresh(orderClient);
        setOrder(null);
    }

    private void delete(OrderGui orderGui) {
        Order order = binder.getBean();
        orderClient.deleteOrder(order.getId());
        orderGui.refresh(orderClient);
        setOrder(null);
    }

    public void setOrder(Order order) {
        binder.setBean(order);
        if (order == null) {
            setVisible(false);
        } else {
            setVisible(true);
        }
    }
}

package com.kodilla.rentalcars.frontend.form;


import com.kodilla.rentalcars.frontend.client.UserClient;
import com.kodilla.rentalcars.frontend.domain.User;
import com.kodilla.rentalcars.frontend.gui.UserGui;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

public class UserForm extends FormLayout {

    private TextField username = new TextField("username");
    private TextField password = new TextField("password");

    private Button save = new Button("Save");
    private Button delete = new Button("Delete");

    private UserClient userClient = UserClient.getInstance();

    Binder<User> binder = new Binder<>(User.class);

    public UserForm(UserGui userGui) {

        HorizontalLayout buttons = new HorizontalLayout(save, delete);
        add(username, password, buttons);

        save.addClickListener(event -> save(userGui));
        delete.addClickListener(event -> delete(userGui));

        binder.bindInstanceFields(this);
    }

    private void save(UserGui userGui) {
        User user = binder.getBean();

        userClient.addUser(user);
        userGui.refresh(userClient);
        setUser(null);
    }

    private void delete(UserGui userGui) {
        User user = binder.getBean();
        userClient.deleteUser(user.getId());
        userGui.refresh(userClient);
        setUser(null);
    }

    public void setUser(User user) {
        binder.setBean(user);
        if (user == null) {
            setVisible(false);
        } else {
            setVisible(true);
            username.focus();
        }
    }

}


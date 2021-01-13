package com.kodilla.rentalcars.frontend.gui;

import com.kodilla.rentalcars.frontend.client.UserClient;
import com.kodilla.rentalcars.frontend.domain.User;
import com.kodilla.rentalcars.frontend.form.UserForm;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Route("users")
public class UserGui extends VerticalLayout {
    private Grid grid = new Grid<>(User.class);
    private UserForm form = new UserForm(this);
    private Button addNewUser = new Button("Add new user");

    @Autowired
    public UserGui(UserClient userClient) {

        grid.setColumns("id", "username", "password", "cart");

        addNewUser.addClickListener(e -> {
            grid.asSingleSelect().clear();
            form.setUser(new User());
        });
        HorizontalLayout toolbar = new HorizontalLayout(addNewUser);

        HorizontalLayout mainContent = new HorizontalLayout(grid, form);
        mainContent.setSizeFull();
        grid.setSizeFull();

        add(toolbar, mainContent);
        form.setUser(null);
        setSizeFull();
        refresh(userClient);

        grid.asSingleSelect().addValueChangeListener(event -> form.setUser((User) grid.asSingleSelect().getValue()));

    }

    public void refresh(UserClient userClient) {
        List<User> userList;
        userList = userClient.getUsersFromApi();
        grid.setItems(userList);
    }

}


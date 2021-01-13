package com.kodilla.rentalcars.frontend.gui;

import com.kodilla.rentalcars.frontend.client.ExtrasClient;
import com.kodilla.rentalcars.frontend.domain.Extras;
import com.kodilla.rentalcars.frontend.form.ExtrasForm;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Route("extras")
public class ExtrasGui extends VerticalLayout {

    private Grid grid = new Grid<>(Extras.class);
    private ExtrasForm form = new ExtrasForm(this);
    private Button addNewExtras = new Button("Add new extras");

    @Autowired
    public ExtrasGui(ExtrasClient extrasClient) {
        grid.setColumns("id", "name", "price");

        addNewExtras.addClickListener(e -> {
            grid.asSingleSelect().clear();
            form.setExtras(new Extras());
        });
        HorizontalLayout toolbar = new HorizontalLayout(addNewExtras);

        HorizontalLayout mainContent = new HorizontalLayout(grid, form);
        mainContent.setSizeFull();
        grid.setSizeFull();

        add(toolbar, mainContent);
        form.setExtras(null);
        setSizeFull();
        refresh(extrasClient);

        grid.asSingleSelect().addValueChangeListener(event -> form.setExtras((Extras) grid.asSingleSelect().getValue()));
    }

    public void refresh(ExtrasClient extrasClient) {
        List<Extras> extrasList;
        extrasList = extrasClient.getExtrasListFromApi();
        grid.setItems(extrasList);
    }
}



package com.kodilla.rentalcars.frontend.form;

import com.kodilla.rentalcars.frontend.client.ExtrasClient;
import com.kodilla.rentalcars.frontend.domain.Extras;
import com.kodilla.rentalcars.frontend.gui.ExtrasGui;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.BigDecimalField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

public class ExtrasForm extends FormLayout {

    private TextField name = new TextField("name");
    private BigDecimalField price = new BigDecimalField("price");

    private Button save = new Button("Save");
    private Button delete = new Button("Delete");

    private ExtrasClient extrasClient = ExtrasClient.getInstance();

    Binder<Extras> binder = new Binder<>(Extras.class);

    public ExtrasForm(ExtrasGui extrasGui) {

        HorizontalLayout buttons = new HorizontalLayout(save, delete);
        add(name, price, buttons);

        save.addClickListener(event -> save(extrasGui));
        delete.addClickListener(event -> delete(extrasGui));

        binder.bindInstanceFields(this);
    }

    private void save(ExtrasGui extrasGui) {
        Extras extras = binder.getBean();
        extrasClient.addExtras(extras);
        extrasGui.refresh(extrasClient);
        setExtras(null);
    }

    private void delete(ExtrasGui extrasGui) {
        Extras extras = binder.getBean();
        extrasClient.deleteExtras(extras.getId());
        extrasGui.refresh(extrasClient);
        setExtras(null);
    }

    public void setExtras(Extras extras) {
        binder.setBean(extras);
        if (extras == null) {
            setVisible(false);
        } else {
            setVisible(true);
            name.focus();
        }
    }

}
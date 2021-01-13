package com.kodilla.rentalcars.frontend.gui;

import com.kodilla.rentalcars.frontend.client.CarClient;
import com.kodilla.rentalcars.frontend.domain.Car;
import com.kodilla.rentalcars.frontend.form.CarForm;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Route("cars")
public class CarGui extends VerticalLayout {
    private Grid grid = new Grid<>(Car.class);
    private CarForm form = new CarForm(this);
    private Button addNewCar = new Button("Add new car");

    @Autowired
    public CarGui(CarClient carClient) {

        grid.setColumns("id", "description", "name", "dailyPrice");

        addNewCar.addClickListener(e -> {
            grid.asSingleSelect().clear();
            form.setCar(new Car());
        });
        HorizontalLayout toolbar = new HorizontalLayout(addNewCar);

        HorizontalLayout mainContent = new HorizontalLayout(grid, form);
        mainContent.setSizeFull();
        grid.setSizeFull();

        add(toolbar, mainContent);
        form.setCar(null);
        setSizeFull();
        refresh(carClient);

        grid.asSingleSelect().addValueChangeListener(event -> form.setCar((Car) grid.asSingleSelect().getValue()));
    }

    public void refresh(CarClient carClient) {
        List<Car> carList;
        carList = carClient.getCarsFromApi();
        grid.setItems(carList);
    }

}

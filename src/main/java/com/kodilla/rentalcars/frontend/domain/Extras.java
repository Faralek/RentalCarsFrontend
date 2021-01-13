package com.kodilla.rentalcars.frontend.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.math.BigDecimal;
import java.util.List;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "name",
        "price",
        "cars",
})
public class Extras {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("price")
    private BigDecimal price;
    @JsonProperty("cars")
    private List<Car> cars;

    public Extras() {
    }

    public Extras(Long id, String name, BigDecimal price, List<Car> cars) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.cars = cars;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }
}

package com.kodilla.rentalcars.frontend.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "description",
        "name",
        "dailyPrice",
        "extrasList",
        "cart",
        "order",
})
public class Car {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("description")
    private String description;
    @JsonProperty("name")
    private String name;
    @JsonProperty("dailyPrice")
    private BigDecimal dailyPrice;
    @JsonProperty("extrasList")
    private List<Extras> extrasList = new ArrayList<>();
    @JsonProperty("cart")
    private Cart cart;
    @JsonProperty("order")
    private Order order;

    public Car() {
    }

    public Car(String description, String name, BigDecimal dailyPrice) {
        this.description = description;
        this.name = name;
        this.dailyPrice = dailyPrice;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getDailyPrice() {
        return dailyPrice;
    }

    public List<Extras> getExtrasList() {
        return extrasList;
    }

    public Cart getCart() {
        return cart;
    }

    public Order getOrder() {
        return order;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDailyPrice(BigDecimal dailyPrice) {
        this.dailyPrice = dailyPrice;
    }

    public void setExtrasList(List<Extras> extrasList) {
        this.extrasList = extrasList;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}

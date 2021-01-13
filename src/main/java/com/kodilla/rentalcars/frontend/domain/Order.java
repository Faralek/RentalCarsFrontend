package com.kodilla.rentalcars.frontend.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.math.BigDecimal;
import java.util.List;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "cart",
        "cars",
        "user",
        "sum",
})
public class Order {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("cart")
    private Cart cart;
    @JsonProperty("cars")
    private List<Car> cars;
    @JsonProperty("user")
    private User user;
    @JsonProperty("sum")
    private BigDecimal sum;

    public Order() {
    }
    public Order(Long id, Cart cart, List<Car> cars, User user, BigDecimal sum) {
        this.id = id;
        this.cart = cart;
        this.cars = cars;
        this.user = user;
        this.sum = sum;
    }

    public Long getId() {
        return id;
    }

    public Cart getCart() {
        return cart;
    }

    public List<Car> getCars() {
        return cars;
    }

    public User getUser() {
        return user;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }
}

package com.kodilla.rentalcars.frontend.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import java.util.List;
import java.util.Optional;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "sum",
        "cars",
        "orders",
        "user",
})
@NoArgsConstructor
public class Cart {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("sum")
    private BigDecimal sum;
    @JsonProperty("cars")
    private List<Car> cars;
    @JsonProperty("orders")
    private List<Order> orders;
    @JsonProperty("user")
    private User user;

    public Cart() {
    }

    public Cart(Long id, BigDecimal sum, List<Car> cars, List<Order> orders, User user) {
        this.id = id;
        this.sum = sum;
        this.cars = cars;
        this.orders = orders;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public List<Car> getCars() {
        return cars;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public User getUser() {
        return user;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

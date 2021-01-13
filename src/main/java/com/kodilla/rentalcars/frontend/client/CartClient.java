package com.kodilla.rentalcars.frontend.client;

import com.kodilla.rentalcars.frontend.domain.Car;
import com.kodilla.rentalcars.frontend.domain.Cart;
import com.kodilla.rentalcars.frontend.domain.User;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CartClient {
    private static CartClient cartClient;

    public static CartClient getInstance() {
        if (cartClient == null) {
            cartClient = new CartClient();
        }
        return cartClient;
    }

    RestTemplate restTemplate = new RestTemplate();

    public Cart getCartFromApi(Long id) {
        ResponseEntity<Cart> exchange = restTemplate.exchange(
                "http://localhost:8081/v1/carts/" + id,
                HttpMethod.GET,
                HttpEntity.EMPTY,
                Cart.class);
        return exchange.getBody();
    }

    public List<Car> getCarsFromCart(Long id){
        List<Car> cars = getCartFromApi(id).getCars();
        return cars;
    }

    public Cart addCarToCart(Object object, Long id, int duration){
        Cart cart = getCartFromApi(id);
        Car car = (Car) object;
        cart.getCars().add(car);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Car> request = new HttpEntity<>(car, headers);
        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8081/v1/carts/addCar")
                .queryParam("id", id)
                .queryParam("duration", duration).build().encode().toUri();
        restTemplate.postForObject(url, request, Cart.class);

        return cart;
    }

    public List<Cart> getCartsFromApi() {
        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8081/v1/carts")
                .queryParam("fields", "id, sum ,cars").build().encode().toUri();

        Cart[] response = restTemplate.getForObject(url, Cart[].class);
        return Arrays.asList(Optional.ofNullable(response).orElse(new Cart[0]));

    }

    public void deleteCart(Long id) {
        restTemplate.exchange(
                "http://localhost:8081/v1/carts/" + id,
                HttpMethod.DELETE,
                HttpEntity.EMPTY,
                User.class);
    }

    public Cart addCart(Cart cart) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Cart> request = new HttpEntity<>(cart, headers);
        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8081/v1/carts/").build().encode().toUri();
        restTemplate.postForObject(url, request, Cart.class);
        return cart;
    }

}
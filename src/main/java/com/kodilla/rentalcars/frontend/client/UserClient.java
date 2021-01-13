package com.kodilla.rentalcars.frontend.client;

import com.kodilla.rentalcars.frontend.domain.Cart;
import com.kodilla.rentalcars.frontend.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserClient {
    private static UserClient userClient;

    private final CartClient cartClient = new CartClient();

    public static UserClient getInstance() {
        if (userClient == null) {
            userClient = new UserClient();
        }
        return userClient;
    }

    RestTemplate restTemplate = new RestTemplate();

    public User getUserFromApi(int id) {
        ResponseEntity<User> exchange = restTemplate.exchange(
                "http://localhost:8081/v1/cars/" + id,
                HttpMethod.GET,
                HttpEntity.EMPTY,
                User.class);
        return exchange.getBody();
    }

    public List<User> getUsersFromApi() {
        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8081/v1/users")
                .queryParam("fields", "id, name ,password, cart, orders").build().encode().toUri();

        User[] response = restTemplate.getForObject(url, User[].class);
        return Arrays.asList(Optional.ofNullable(response).orElse(new User[0]));

    }

    public void deleteUser(Long id) {
        restTemplate.exchange(
                "http://localhost:8081/v1/users/" + id,
                HttpMethod.DELETE,
                HttpEntity.EMPTY,
                User.class);
    }

    public void addUser(User user) {

        Cart cart = new Cart();
        cart.setUser(user);
        user.setCart(cart);


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<User> request = new HttpEntity<>(user, headers);

        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8081/v1/users/")
                .queryParam("name", user.getUsername())
                .queryParam("password", user.getPassword())
                .queryParam("cart", user.getCart()).build().encode().toUri();

        restTemplate.postForObject(url, request, User.class);
    }
}

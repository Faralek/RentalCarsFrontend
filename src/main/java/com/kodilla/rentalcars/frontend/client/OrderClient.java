package com.kodilla.rentalcars.frontend.client;

import com.kodilla.rentalcars.frontend.domain.Extras;
import com.kodilla.rentalcars.frontend.domain.Order;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class OrderClient {
    private static OrderClient orderClient;

    public static OrderClient getInstance() {
        if (orderClient == null) {
            orderClient = new OrderClient();
        }
        return orderClient;
    }

    RestTemplate restTemplate = new RestTemplate();

    public Order getOrderFromApi(int id) {
        ResponseEntity<Order> exchange = restTemplate.exchange(
                "http://localhost:8081/v1/orders/" + id,
                HttpMethod.GET,
                HttpEntity.EMPTY,
                Order.class);
        return exchange.getBody();
    }

    public List<Order> getOrdersFromApi() {
        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8081/v1/orders")
                .queryParam("fields", "id, cart ,cars, user, sum").build().encode().toUri();

        Order[] response = restTemplate.getForObject(url, Order[].class);
        System.out.println(response.toString());
        return Arrays.asList(Optional.ofNullable(response).orElse(new Order[0]));

    }

    public void deleteOrder(Long id) {
        restTemplate.exchange(
                "http://localhost:8081/v1/orders/" + id,
                HttpMethod.DELETE,
                HttpEntity.EMPTY,
                Extras.class);
    }

    public void addOrder(Order order) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Order> request = new HttpEntity<>(order, headers);

        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8081/v1/orders")
                .queryParam("id", order.getId())
                .queryParam("name", order.getCart())
                .queryParam("cars", order.getCars())
                .queryParam("user", order.getUser())
                .queryParam("sum", order.getSum()).build().encode().toUri();
        restTemplate.postForObject(url, request, Extras.class);
    }
}
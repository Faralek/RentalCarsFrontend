package com.kodilla.rentalcars.frontend.client;

import com.kodilla.rentalcars.frontend.domain.Car;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CarClient {
    private static CarClient carClient;

    public static CarClient getInstance() {
        if (carClient == null) {
            carClient = new CarClient();
        }
        return carClient;
    }

    RestTemplate restTemplate = new RestTemplate();

    public Car getCarFromApi(int id) {
        ResponseEntity<Car> exchange = restTemplate.exchange(
                "http://localhost:8081/v1/cars/" + id,
                HttpMethod.GET,
                HttpEntity.EMPTY,
                Car.class);
        return exchange.getBody();
    }

    public List<Car> getCarsFromApi() {
        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8081/v1/cars")
                .queryParam("fields", "id, description ,name, dailyPrice").build().encode().toUri();

        Car[] response = restTemplate.getForObject(url, Car[].class);
        return Arrays.asList(Optional.ofNullable(response).orElse(new Car[0]));

    }

    public void deleteCar(Long id) {
        restTemplate.exchange(
                "http://localhost:8081/v1/cars/" + id,
                HttpMethod.DELETE,
                HttpEntity.EMPTY,
                Car.class);
    }

    public void addCar(Car car) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Car> request = new HttpEntity<>(car, headers);

        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8081/v1/cars/")
                .queryParam("description", car.getDescription())
                .queryParam("name", car.getName())
                .queryParam("dailyPrice", car.getDailyPrice())
                .queryParam("extrasList", car.getExtrasList())
                .queryParam("cart", car.getCart())
                .queryParam("order", car.getOrder()).build().encode().toUri();

        restTemplate.postForObject(url, request, Car.class);
    }

}

package com.kodilla.rentalcars.frontend.client;

import com.kodilla.rentalcars.frontend.domain.Extras;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ExtrasClient {
    private static ExtrasClient extrasClient;

    public static ExtrasClient getInstance() {
        if (extrasClient == null) {
            extrasClient = new ExtrasClient();
        }
        return extrasClient;
    }

    RestTemplate restTemplate = new RestTemplate();

    public Extras getExtrasFromApi(int id) {
        ResponseEntity<Extras> exchange = restTemplate.exchange(
                "http://localhost:8081/v1/extras/" + id,
                HttpMethod.GET,
                HttpEntity.EMPTY,
                Extras.class);
        return exchange.getBody();
    }

    public List<Extras> getExtrasListFromApi() {
        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8081/v1/extras")
                .queryParam("fields", "id, name ,price").build().encode().toUri();

        Extras[] response = restTemplate.getForObject(url, Extras[].class);
        return Arrays.asList(Optional.ofNullable(response).orElse(new Extras[0]));

    }

    public void deleteExtras(Long id) {
        restTemplate.exchange(
                "http://localhost:8081/v1/extras/" + id,
                HttpMethod.DELETE,
                HttpEntity.EMPTY,
                Extras.class);
    }

    public void addExtras(Extras extras) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Extras> request = new HttpEntity<>(extras, headers);

        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8081/v1/carts/")
                .queryParam("name", extras.getName())
                .queryParam("price", extras.getPrice()).build().encode().toUri();
        restTemplate.postForObject(url, request, Extras.class);
    }
}
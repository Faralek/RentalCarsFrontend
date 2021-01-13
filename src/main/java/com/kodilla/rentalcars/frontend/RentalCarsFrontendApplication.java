package com.kodilla.rentalcars.frontend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class RentalCarsFrontendApplication {

    public static void main(String[] args) {
        SpringApplication.run(RentalCarsFrontendApplication.class, args);
    }

}

package com.example.isusevicemaven;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class IsuSeviceMavenApplication {

    public static void main(String[] args) {
        SpringApplication.run(IsuSeviceMavenApplication.class, args);
    }

}

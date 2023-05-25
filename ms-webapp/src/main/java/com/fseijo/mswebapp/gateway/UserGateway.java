package com.fseijo.mswebapp.gateway;

import com.fseijo.mswebapp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class UserGateway {
    @Autowired
    private RestTemplate restTemplate;

    public User[] getUsersList() {
        return restTemplate.getForObject("http://localhost:8090/user/list", User[].class);
    }

    public User addNewUser (User user){
       return restTemplate.postForObject("http://localhost:8090/user/validate", user, User.class);
    }
}

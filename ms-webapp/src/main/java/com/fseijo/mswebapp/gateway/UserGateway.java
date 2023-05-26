package com.fseijo.mswebapp.gateway;

import com.fseijo.mswebapp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    public User getUserById(Integer id){
        ResponseEntity<User> userResponseEntity = restTemplate.getForEntity("http://localhost:8090/user/update/{id}", User.class, id.toString());
        return userResponseEntity.getBody();
    }
    public User updateUserById(User user){
        return restTemplate.postForObject("http://localhost:8090/user/update/{id}",user, User.class, user.getId().toString());
    }

    public void deleteUserById(Integer id){
      restTemplate.delete("http://localhost:8090/user/delete/{id}", id.toString());
    }
}

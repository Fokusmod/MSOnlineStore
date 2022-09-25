package com.geekbrains.authservice;

import com.geekbrains.apiservice.RegistrationRequest;
import com.geekbrains.apiservice.UserDto;
import net.javacrumbs.jsonunit.JsonAssert;
import net.javacrumbs.jsonunit.core.Option;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;


class AuthServiceApplicationTests extends AuthTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

//    @Test
//    void testGetUser() {
//        UserDto user = testRestTemplate.getForEntity("/api/v1/user/user",UserDto.class).getBody();
//        String expected = getResource("getUser/expected.json");
//        JsonAssert.assertJsonEquals(expected,user,JsonAssert.when(Option.IGNORING_ARRAY_ORDER));
//    }
//    @Test
//    void testRegistration (){
//        RegistrationRequest registrationRequest = new RegistrationRequest();
//        registrationRequest.setUsername("Test");
//        registrationRequest.setPassword("Test");
//        registrationRequest.setEmail("Test@mail.ru");
//        ResponseEntity<String> response = testRestTemplate.postForEntity("/api/v1/registration",registrationRequest,String.class);
//
//        assert response.getBody().equals("200 CREATED");
//    }



}

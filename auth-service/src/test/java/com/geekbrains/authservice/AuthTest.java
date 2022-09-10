package com.geekbrains.authservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthTest {

    @Autowired
    private TestRestTemplate testRestTemplate;



    public RequestBuilder createRequest() {
        return new RequestBuilder();
    }


    public String getResource(String name) {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(name)){
            assert inputStream != null;
            byte[] bytes = inputStream.readAllBytes();
            return new String(bytes, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    public class RequestBuilder {

        private final Map<String, Object> params;

        private String url;

        public RequestBuilder() {
            params = new HashMap<>();
        }

        public RequestBuilder url(String url) {
            this.url = url;
            return this;
        }

        public RequestBuilder param(String parameter, Object value) {
            params.put(parameter, value);
            return this;
        }

        public<T> T get(Class<T> cls) {
            return testRestTemplate.getForObject(url, cls, params);
        }

        public <T> T get(ParameterizedTypeReference<T> typeReference) {
            return testRestTemplate.exchange(url, HttpMethod.GET, null, typeReference, params)
                    .getBody();
        }
    }
}

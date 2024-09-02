package com.truenorth.challenge.api.adapter.api;

import com.truenorth.challenge.api.exceptions.BadRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Slf4j
public abstract class Api<T> {

    RestTemplate restTemplate = new RestTemplate();
    final Class<T> typeResponseClass;
    private ResponseEntity<T> response;

    protected Api(String baseUrl, Class<T> typeResponseClass) {
        this.typeResponseClass = typeResponseClass;
    }

    public  T get(String url, Map<String, String> queryParams){
        log.info("Try to {} request to {}", HttpMethod.GET, url);
        log.info("params {}", queryParams);
        ResponseEntity<T> response;
        try {
            response = restTemplate.getForEntity(url, typeResponseClass, queryParams);
        } catch (Exception e){
            String messageClean = e.getLocalizedMessage().replaceAll("503 Service Unavailable:", "")
                    .replaceAll("<EOL>", "")
                    .replaceAll("\"", "")
                    .replaceAll(" error: ", "")
                    .trim();
            throw new BadRequestException(messageClean);
        }


        log.info("response {}", response.getBody());
        return response.getBody();
    }
}

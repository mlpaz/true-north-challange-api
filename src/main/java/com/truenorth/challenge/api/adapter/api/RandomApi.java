package com.truenorth.challenge.api.adapter.api;

import com.truenorth.challenge.api.factory.RandomStringFactory;
import com.truenorth.challenge.api.resource.request.operation.input.GenerateStringInput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.util.Map;

@Slf4j
@Component
public class RandomApi extends Api<String> {

    private final static String BASE_URL = "https://www.random.org";

    public RandomApi() {
        super(BASE_URL, String.class);
    }

    public String getRandomString(GenerateStringInput input){
        String url = RandomStringFactory.buildUrl(BASE_URL);
        Map<String, String> queryParams = RandomStringFactory.buildQueryParamsMap(input);
        return super.get(url, queryParams);

    }
}

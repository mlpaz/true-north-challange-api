package com.truenorth.challenge.api.factory;

import com.truenorth.challenge.api.resource.request.operation.input.GenerateStringInput;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

public class RandomStringFactory {
    public final static String NUMBER = "num";
    public final static String LENGTH = "len";
    public final static String DIGITS = "digits";
    public final static String UPPER_ALPHA = "upperalpha";
    public final static String LOWER_ALPHA = "loweralpha";
    public final static String UNIQUE = "unique";
    public final static String FORMAT = "format";
    public final static String RANDOM = "rnd";

    private final static String STRINGS_PATH = "/strings";

    public  static Map<String, String> buildQueryParamsMap(GenerateStringInput input){
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put(NUMBER, input.getNumber().toString());
        queryParams.put(LENGTH, input.getLength().toString());
        queryParams.put(DIGITS, onOff(input.getDigits()));
        queryParams.put(UPPER_ALPHA, onOff(input.getUpperLetter()));
        queryParams.put(LOWER_ALPHA, onOff(input.getLowerLetter()));
        queryParams.put(UNIQUE, onOff(input.getUnique()));
        queryParams.put(FORMAT, "plain");
        queryParams.put(RANDOM, "new");
        return queryParams;
    }

    public  static String buildUrl(String baseUrl){
        return UriComponentsBuilder.fromHttpUrl(baseUrl + STRINGS_PATH)
                .queryParam(NUMBER, "{"+ NUMBER +"}")
                .queryParam(LENGTH, "{"+ LENGTH +"}")
                .queryParam(DIGITS, "{"+ DIGITS +"}")
                .queryParam(UPPER_ALPHA, "{"+ UPPER_ALPHA +"}")
                .queryParam(LOWER_ALPHA, "{"+ LOWER_ALPHA +"}")
                .queryParam(UNIQUE, "{"+ UNIQUE +"}")
                .queryParam(FORMAT, "{"+ FORMAT +"}")
                .queryParam(RANDOM, "{"+ RANDOM +"}")
                .encode()
                .toUriString();
    }
    private static String onOff(boolean on) {
        return (on) ? "on" : "off";
    }
}

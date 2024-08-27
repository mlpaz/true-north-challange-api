package com.test.demo.api.resource.request.operation.input;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GenerateStringInput implements IOperationInput {

    Boolean upperLetter;

    Boolean digits;

    Boolean lowerLetter;

    Integer length;

    Integer number;

}

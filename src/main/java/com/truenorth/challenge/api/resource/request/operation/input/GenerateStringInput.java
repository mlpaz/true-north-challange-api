package com.truenorth.challenge.api.resource.request.operation.input;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GenerateStringInput implements IOperationInput {

    Boolean upperLetter;

    Boolean digits;

    Boolean lowerLetter;

    Boolean unique;

    Integer length;

    Integer number;

}

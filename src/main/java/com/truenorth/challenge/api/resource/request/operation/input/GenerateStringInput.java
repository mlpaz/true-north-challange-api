package com.truenorth.challenge.api.resource.request.operation.input;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class GenerateStringInput implements IOperationInput {

    Boolean upperLetter;

    Boolean digits;

    Boolean lowerLetter;

    Boolean unique;

    Integer length;

    Integer number;

}

package com.truenorth.challenge.api.factory;

import com.truenorth.challenge.api.resource.dto.RecordDTO;
import com.truenorth.challenge.api.model.Record;

public class  RecordDTOFactory {

        public  static RecordDTO fromModel(Record record){
            return RecordDTO.builder()
                .id(record.getId())
                .operation(record.getOperation())
                .userId(record.getUserId())
                .operationResponse(record.getOperationResponse())
                .date(record.getCreatedAt())
                .userBalance(record.getUserBalance())
                .amount(record.getAmount())
                .operationResponse(record.getOperationResponse())
                .build();
        }



}

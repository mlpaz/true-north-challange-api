package com.test.demo.api.factory;

import com.test.demo.api.resource.dto.RecordDTO;
import com.test.demo.api.model.Record;

public class  RecordDTOFactory {

        public  static RecordDTO fromModel(Record record){
            return RecordDTO.builder()
                .id(record.getId())
                .operation(record.getOperation())
                .userId(record.getUserId())
                .operationResponse(record.getOperationResponse())
                .date(record.getCreatedAt())
                .userBalance(record.getUserBalance())
                .operationResponse(record.getOperationResponse())
                .build();
        }



}

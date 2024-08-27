package com.truenorth.challenge.api.port.in;

import com.truenorth.challenge.api.resource.dto.RecordDTO;

import java.util.UUID;

public interface DeleteRecordCommand {

    RecordDTO execute(UUID id);
}

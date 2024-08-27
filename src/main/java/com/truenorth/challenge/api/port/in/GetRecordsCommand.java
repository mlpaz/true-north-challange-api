package com.truenorth.challenge.api.port.in;

import com.truenorth.challenge.api.resource.dto.RecordDTO;
import com.truenorth.challenge.api.resource.request.RecordFiltersRequest;
import org.springframework.data.domain.Page;

public interface GetRecordsCommand {

    Page<RecordDTO> execute(RecordFiltersRequest filters);
}

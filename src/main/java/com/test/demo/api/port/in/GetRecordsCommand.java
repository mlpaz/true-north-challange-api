package com.test.demo.api.port.in;

import com.test.demo.api.resource.dto.RecordDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GetRecordsCommand {

    Page<RecordDTO> execute(Pageable page);
}

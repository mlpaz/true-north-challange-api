package com.test.demo.api.use.cases;

import com.test.demo.api.adapter.persistence.RecordJPARepository;
import com.test.demo.api.exceptions.ResourceNotFoundException;
import com.test.demo.api.factory.RecordDTOFactory;
import com.test.demo.api.model.Record;
import com.test.demo.api.port.in.DeleteRecordCommand;
import com.test.demo.api.resource.dto.RecordDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.UUID;


@Service
@Slf4j
public class DeleteRecord implements DeleteRecordCommand {

    @Autowired
    RecordJPARepository recordRepository;

    @Override
    public RecordDTO execute(UUID id) {

        log.info("Try to delete Record with id {}", id);
        Record record = recordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Record Not Found"));
        record.setDeletedAt(OffsetDateTime.now());
        recordRepository.save(record);

        return RecordDTOFactory.fromModel(record);
    }
}

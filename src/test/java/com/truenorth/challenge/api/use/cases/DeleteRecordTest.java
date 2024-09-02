package com.truenorth.challenge.api.use.cases;

import com.truenorth.challenge.api.adapter.persistence.RecordJPARepository;
import com.truenorth.challenge.api.adapter.persistence.UserJPARepository;
import com.truenorth.challenge.api.exceptions.BadRequestException;
import com.truenorth.challenge.api.exceptions.ResourceNotFoundException;
import com.truenorth.challenge.api.factory.RecordDTOFactory;
import com.truenorth.challenge.api.model.Record;
import com.truenorth.challenge.api.model.User;
import com.truenorth.challenge.api.model.enums.Status;
import com.truenorth.challenge.api.resource.dto.RecordDTO;
import com.truenorth.challenge.api.resource.dto.UserTokenDTO;
import com.truenorth.challenge.api.resource.request.LogInRequest;
import com.truenorth.challenge.api.service.jwt.JwtService;
import com.truenorth.challenge.factory.RecordFactory;
import com.truenorth.challenge.factory.UserFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.Clock;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class DeleteRecordTest {

    @Mock
    RecordJPARepository repository;

    @InjectMocks
    DeleteRecord deleteRecord;


    @Test
    void givenInvalidIdPWhenDeleteRecordThenExpectError(){
        // Given
        UUID id = UUID.randomUUID();
        when(repository.findById(id))
                .thenReturn(Optional.empty());
        // When
        assertThrows(ResourceNotFoundException.class, () -> deleteRecord.execute(id));
        // Then
        verify(repository).findById(id);
    }

    @Test
    void givenValidIdWhenDeleteRecordThenVerify(){
        // Given
        Record record = RecordFactory.build();
        UUID id = UUID.randomUUID();
        when(repository.findById(id))
                .thenReturn(Optional.of(record));

        // When
        RecordDTO response = deleteRecord.execute(id);
        //Then
        verify(repository).findById(id);
        RecordDTO expect = RecordDTOFactory.fromModel(record);
        assertTrue(new ReflectionEquals(expect,"deleteAt")
                .matches(response));
    }


}

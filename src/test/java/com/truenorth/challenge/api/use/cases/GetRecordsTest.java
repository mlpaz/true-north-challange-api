package com.truenorth.challenge.api.use.cases;

import com.truenorth.challenge.api.adapter.persistence.RecordJPARepository;
import com.truenorth.challenge.api.adapter.persistence.UserJPARepository;
import com.truenorth.challenge.api.exceptions.ResourceNotFoundException;
import com.truenorth.challenge.api.factory.RecordDTOFactory;
import com.truenorth.challenge.api.model.Record;
import com.truenorth.challenge.api.model.User;
import com.truenorth.challenge.api.model.enums.Status;
import com.truenorth.challenge.api.resource.dto.RecordDTO;
import com.truenorth.challenge.api.resource.request.LogInRequest;
import com.truenorth.challenge.api.resource.request.RecordFiltersRequest;
import com.truenorth.challenge.factory.RecordFactory;
import com.truenorth.challenge.factory.UserFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class GetRecordsTest {

    @Mock
    UserJPARepository userRepository;

    @Mock
    RecordJPARepository recordRepository;

    @InjectMocks
    GetRecords getRecords;

    @Mock
    private Authentication auth;

    @BeforeEach
    public void initSecurityContext() {
        when(auth.getName()).thenReturn("test@gmail.com");
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    @Test
    void givenInvalidUserWhenGetRecordsThenExpectError(){
        // Given
        RecordFiltersRequest filtersRequest = RecordFactory.buildGetRequest();
        when(userRepository.findFirstByEmail("test@gmail.com"))
                .thenReturn(Optional.empty());
        // When
        assertThrows(ResourceNotFoundException.class,
                () -> getRecords.execute(filtersRequest));
        // Then
        verify(userRepository).findFirstByEmail("test@gmail.com");
    }

    @Test
    void givenValidUserWhenLoginThenVerify(){
        // Given
        RecordFiltersRequest filtersRequest = RecordFactory.buildGetRequest();
        Record record = Record.builder().build();
        Page<Record> recordPage = Page.empty();
        recordPage.and(record);
        User user = UserFactory.build();
        when(userRepository.findFirstByEmail("test@gmail.com"))
                .thenReturn(Optional.of(user));
        when(recordRepository.findAll(any(Specification.class), any(Pageable.class)))
                .thenReturn(recordPage);


        // When
        Page<RecordDTO> response = getRecords.execute(filtersRequest);
        //Then
        verify(userRepository).findFirstByEmail("test@gmail.com");
        Page<RecordDTO> expect = recordPage.map(RecordDTOFactory::fromModel);
        assertTrue(new ReflectionEquals(expect).matches(response));
    }


}

package com.truenorth.challenge.api.use.cases;

import com.truenorth.challenge.api.adapter.persistence.RecordJPARepository;
import com.truenorth.challenge.api.adapter.persistence.UserJPARepository;
import com.truenorth.challenge.api.adapter.persistence.specification.RecordSpecifications;
import com.truenorth.challenge.api.exceptions.ResourceNotFoundException;
import com.truenorth.challenge.api.factory.RecordDTOFactory;
import com.truenorth.challenge.api.model.Record;
import com.truenorth.challenge.api.model.User;
import com.truenorth.challenge.api.port.in.GetRecordsCommand;
import com.truenorth.challenge.api.resource.dto.RecordDTO;
import com.truenorth.challenge.api.resource.request.RecordFiltersRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class GetRecords implements GetRecordsCommand {

    @Autowired
    RecordJPARepository recordRepository;

    @Autowired
    UserJPARepository userRepository;

    @Override
    public Page<RecordDTO> execute(RecordFiltersRequest filters) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userRepository.findFirstByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found"));

        Specification<Record> specification = RecordSpecifications.eqUser(user.getId())
                .and(RecordSpecifications.notDeleted());

        if(filters.getOperationType() != null){
            specification = specification
                    .and(RecordSpecifications.eqType(filters.getOperationType()));
        }
        if(filters.getGreaterThanDate() != null){
            specification = specification
                    .and(RecordSpecifications.greaterThanDate(filters.getGreaterThanDate()));
        }

        if(filters.getLessThanDate() != null){
            specification = specification
                    .and(RecordSpecifications.lessThanDate(filters.getLessThanDate()));
        }

        Sort sort =  Sort.by(filters.getOrderBy()).ascending();
        if (Sort.Direction.DESC.equals(filters.getDirection())){
            sort = sort.descending();
        }

        PageRequest pageRequest = PageRequest.of(filters.getPage(), filters.getSize(), sort);
        Page<Record> records = recordRepository.findAll(specification, pageRequest);



        return records.map(RecordDTOFactory::fromModel);
    }
}

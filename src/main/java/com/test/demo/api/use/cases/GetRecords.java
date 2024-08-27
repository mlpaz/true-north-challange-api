package com.test.demo.api.use.cases;

import com.test.demo.api.adapter.persistence.RecordJPARepository;
import com.test.demo.api.adapter.persistence.UserJPARepository;
import com.test.demo.api.exceptions.ResourceNotFoundException;
import com.test.demo.api.exceptions.UnauthorizedException;
import com.test.demo.api.factory.RecordDTOFactory;
import com.test.demo.api.model.Record;
import com.test.demo.api.model.User;
import com.test.demo.api.port.in.GetRecordsCommand;
import com.test.demo.api.port.in.LogInCommand;
import com.test.demo.api.resource.dto.RecordDTO;
import com.test.demo.api.resource.dto.UserDetailsDTO;
import com.test.demo.api.resource.dto.UserTokenDTO;
import com.test.demo.api.resource.request.LogInRequest;
import com.test.demo.api.service.jwt.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public Page<RecordDTO> execute(Pageable page) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userRepository.findFirstByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found"));
        Page<Record> records = recordRepository.findPageByUserId(user.getId(), page);
        return records.map(RecordDTOFactory::fromModel);
    }
}

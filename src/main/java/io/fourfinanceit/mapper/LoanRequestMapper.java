package io.fourfinanceit.mapper;

import io.fourfinanceit.controller.dto.LoanRequestDTO;
import io.fourfinanceit.domain.LoanRequestDO;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

public interface LoanRequestMapper {

    LoanRequestMapper INSTANCE = Mappers.getMapper(LoanRequestMapper.class);

    @Mappings({
            @Mapping(target = "clientId", source = "client.id"),
            @Mapping(target = "ipAddress", source = "ipAddress"),
            @Mapping(target = "created", source = "created"),
            @Mapping(target = "requestedSum", source = "requestedSum"),
            @Mapping(target = "duration", source = "duration")
    })
    LoanRequestDO map(LoanRequestDTO loanRequest);
}

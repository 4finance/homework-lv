package io.fourfinanceit.mapper;

import io.fourfinanceit.controller.dto.LoanExtensionRequestDTO;
import io.fourfinanceit.domain.LoanExtensionRequestDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LoanExtensionRequestMapper {

    LoanExtensionRequestMapper INSTANCE = Mappers.getMapper(LoanExtensionRequestMapper.class);

    @Mappings({
            @Mapping(target = "client.id", source = "clientId"),
            @Mapping(target = "loan.id", source = "loanId"),
            @Mapping(target = "ipAddress", source = "ipAddress"),
            @Mapping(target = "duration", source = "duration")
    })
    LoanExtensionRequestDO map(LoanExtensionRequestDTO loanExtensionRequest);
}

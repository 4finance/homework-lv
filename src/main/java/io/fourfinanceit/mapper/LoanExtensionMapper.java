package io.fourfinanceit.mapper;

import io.fourfinanceit.controller.dto.LoanExtensionRequestDTO;
import io.fourfinanceit.domain.LoanExtensionDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LoanExtensionMapper {

    LoanExtensionMapper INSTANCE = Mappers.getMapper(LoanExtensionMapper.class);

    @Mappings({
            @Mapping(target = "duration", source = "duration")
    })
    LoanExtensionDO map(LoanExtensionRequestDTO loanExtensionRequest);
}

package io.fourfinanceit.mapper;

import io.fourfinanceit.controller.dto.LoanDTO;
import io.fourfinanceit.domain.LoanDO;
import io.fourfinanceit.domain.LoanRequestDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LoanMapper {

    LoanMapper INSTANCE = Mappers.getMapper(LoanMapper.class);

    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "loanSum", source = "loanSum"),
            @Mapping(target = "interestRate", source = "interestRate"),
            @Mapping(target = "created", source = "created"),
            @Mapping(target = "duration", source = "duration"),
            @Mapping(target = "loanExtension.id", source = "loanExtension.id"),
            @Mapping(target = "loanExtension.duration", source = "loanExtension.duration"),
            @Mapping(target = "loanExtension.interestRate", source = "loanExtension.interestRate"),
            @Mapping(target = "loanExtension.created", source = "loanExtension.created")
    })
    LoanDTO map(LoanDO loan);

    @Mappings({
            @Mapping(target = "client.id", source = "client.id"),
            @Mapping(target = "loanSum", source = "requestedSum"),
            @Mapping(target = "duration", source = "duration")
    })
    LoanDO map(LoanRequestDO request);
}

package io.fourfinanceit.mapper;

import io.fourfinanceit.controller.dto.LoanDTO;
import io.fourfinanceit.domain.LoanDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LoanMapper {

    LoanMapper INSTANCE = Mappers.getMapper(LoanMapper.class);

    LoanDTO map(LoanDO loan);
}

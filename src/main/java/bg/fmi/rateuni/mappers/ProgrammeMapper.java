package bg.fmi.rateuni.mappers;

import bg.fmi.rateuni.dto.response.ProgrammeInfoResponse;
import bg.fmi.rateuni.dto.response.ProgrammeResponse;
import bg.fmi.rateuni.models.Programme;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface ProgrammeMapper {
    @Mapping(source = "idResponse", target = "id")
    Programme mapFromDto(ProgrammeResponse programmeDto);

    @Mapping(source = "id", target = "idInfoResponse")
    @Mapping(source = "programmeDisciplines", target = "disciplines")
    ProgrammeInfoResponse mapToInfoResponseDto(Programme programme);

    @Mapping(source = "id", target = "idResponse")
    ProgrammeResponse mapToDto(Programme programme);
}
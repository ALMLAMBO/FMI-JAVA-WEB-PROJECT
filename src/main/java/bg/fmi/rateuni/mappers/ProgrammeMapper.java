package bg.fmi.rateuni.mappers;

import bg.fmi.rateuni.dto.ProgrammeDto;
import bg.fmi.rateuni.models.Programme;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface ProgrammeMapper {
    @Mapping(source = "title", target = "title")
    Programme mapFromDto(ProgrammeDto programmeDto);

    @Mapping(source = "title", target = "title")
    ProgrammeDto mapToDto(Programme programme);
}
package bg.fmi.rateuni.mappers;

import bg.fmi.rateuni.dto.FacultyDto;
import bg.fmi.rateuni.dto.response.FacultyInfoResponse;
import bg.fmi.rateuni.dto.response.FacultyResponse;
import bg.fmi.rateuni.dto.response.ProgrammeInfoResponse;
import bg.fmi.rateuni.models.Faculty;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FacultyMapper {
    @Mapping(source = "id", target = "id")
    Faculty mapFromDto(FacultyInfoResponse facultyDto);

    @Mapping(source = "id", target = "id")
    FacultyDto mapToDto(Faculty faculty);
}
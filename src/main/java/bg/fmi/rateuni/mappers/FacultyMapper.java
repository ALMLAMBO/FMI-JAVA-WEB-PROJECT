package bg.fmi.rateuni.mappers;

import bg.fmi.rateuni.dto.response.FacultyInfoResponse;
import bg.fmi.rateuni.dto.response.FacultyResponse;
import bg.fmi.rateuni.models.Faculty;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FacultyMapper {
    @Mapping(source = "idResponse", target = "id")
    Faculty mapFromDto(FacultyResponse facultyDto);

    @Mapping(source = "id", target = "idInfoResponse")
    @Mapping(source = "facultyPrograms", target = "programs")
    FacultyInfoResponse mapToInfoResponseDto(Faculty faculty);

    @Mapping(source = "id", target = "idResponse")
    FacultyResponse mapToDto(Faculty faculty);
}
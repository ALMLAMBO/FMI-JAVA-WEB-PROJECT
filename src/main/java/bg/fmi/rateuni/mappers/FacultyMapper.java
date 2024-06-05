package bg.fmi.rateuni.mappers;

import bg.fmi.rateuni.dto.response.FacultyInfoResponse;
import bg.fmi.rateuni.dto.response.FacultyResponse;
import bg.fmi.rateuni.models.Faculty;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FacultyMapper {
    @Mapping(source = "idResponse", target = "id")
    Faculty mapFromDto(FacultyResponse facultyDto);

    @Mapping(source = "faculty.id", target = "idInfoResponse")
    @Mapping(source = "facultyPrograms", target = "programs")
    FacultyInfoResponse mapToInfoResponseDto(Faculty faculty);

    @Mapping(source = "faculty.id", target = "idResponse")
    FacultyResponse mapToDto(Faculty faculty);
}
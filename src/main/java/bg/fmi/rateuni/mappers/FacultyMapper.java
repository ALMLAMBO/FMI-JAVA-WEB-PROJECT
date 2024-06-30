package bg.fmi.rateuni.mappers;

import bg.fmi.rateuni.dto.request.CreateFacultyRequest;
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
    FacultyInfoResponse mapToInfoResponseDto(Faculty faculty);

    @Mapping(source = "id", target = "idResponse")
    FacultyResponse mapToDto(Faculty faculty);
    
    Faculty mapFromCreateRequest(CreateFacultyRequest createFacultyRequest);
}
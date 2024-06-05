package bg.fmi.rateuni.mappers;

import bg.fmi.rateuni.dto.response.UniversityInfoResponse;
import bg.fmi.rateuni.models.University;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UniversityMapper {
    @Mapping(source = "idInfoResponse", target = "id")
    University mapFromDto(UniversityInfoResponse universityDto);

    @Mapping(source = "id", target = "idInfoResponse")
    @Mapping(source = "universityFaculties", target = "faculties")
    UniversityInfoResponse mapToInfoResponseDto(University university);
}

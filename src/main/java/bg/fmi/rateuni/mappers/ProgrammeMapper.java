package bg.fmi.rateuni.mappers;

import bg.fmi.rateuni.dto.request.CreateFacultyRequest;
import bg.fmi.rateuni.dto.request.CreateProgrammeRequest;
import bg.fmi.rateuni.dto.response.ProgrammeInfoResponse;
import bg.fmi.rateuni.dto.response.ProgrammeResponse;
import bg.fmi.rateuni.models.Faculty;
import bg.fmi.rateuni.models.Programme;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProgrammeMapper {

    @Mapping(source = "programmeDto.idResponse", target = "id")
    Programme mapFromDto(ProgrammeResponse programmeDto);
    
    @Mapping(source = "programme.id", target = "idInfoResponse")
    @Mapping(source = "programmeDisciplines", target = "disciplines")
    ProgrammeInfoResponse mapToInfoResponseDto(Programme programme);
    
    @Mapping(source = "programme.id", target = "idResponse")
    ProgrammeResponse mapToDto(Programme programme);

    Programme mapFromCreateRequest(CreateProgrammeRequest createProgrammeRequest);
}
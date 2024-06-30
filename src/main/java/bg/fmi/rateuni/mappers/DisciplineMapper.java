package bg.fmi.rateuni.mappers;

import bg.fmi.rateuni.dto.request.CreateDisciplineRequest;
import bg.fmi.rateuni.dto.request.CreateFacultyRequest;
import bg.fmi.rateuni.dto.response.DisciplineInfoResponse;
import bg.fmi.rateuni.dto.response.DisciplineResponse;
import bg.fmi.rateuni.models.Discipline;
import bg.fmi.rateuni.models.Faculty;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface DisciplineMapper {
    DisciplineResponse mapToDto(Discipline discipline);
    DisciplineInfoResponse mapToInfoDto(Discipline discipline);

    Discipline mapFromDto(DisciplineResponse disciplineResponse);

    Discipline mapFromCreateRequest(CreateDisciplineRequest createDisciplineRequest);
}

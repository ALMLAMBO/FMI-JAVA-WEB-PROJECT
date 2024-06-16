package bg.fmi.rateuni.services.business;

import bg.fmi.rateuni.dto.request.CreateDisciplineRequest;
import bg.fmi.rateuni.dto.request.CreateProgrammeRequest;
import bg.fmi.rateuni.dto.response.BaseResponse;
import bg.fmi.rateuni.dto.response.ProgrammeInfoResponse;
import bg.fmi.rateuni.mappers.DisciplineMapper;
import bg.fmi.rateuni.mappers.ProgrammeMapper;
import bg.fmi.rateuni.models.Discipline;
import bg.fmi.rateuni.models.Programme;
import bg.fmi.rateuni.services.crud.DisciplineCrudService;
import bg.fmi.rateuni.services.crud.ProgrammeCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProgrammeService {
    @Autowired
    private ProgrammeCrudService programmeCrudService;

    @Autowired
    private ProgrammeMapper programmeMapper;

    @Autowired
    private DisciplineCrudService disciplineCrudService;

    @Autowired
    private DisciplineMapper disciplineMapper;

    public ProgrammeInfoResponse getProgrammeById(UUID id) {
        Programme programme = programmeCrudService.getProgrammeById(id).get();
        if(programme == null) {
            throw new IllegalArgumentException("Programme with id " + id + " not found");
        }

        ProgrammeInfoResponse programmeInfoResponse = programmeMapper.mapToInfoResponseDto(programme);
        programmeInfoResponse.setDisciplines(disciplineCrudService.getDisciplineById(id)
                .stream()
                .map(discipline -> disciplineMapper.mapToDto(discipline))
                .toList());

        return programmeInfoResponse;
    }

    public BaseResponse createProgramme(CreateProgrammeRequest programmeRequest) {
        Programme programme = programmeCrudService.getProgrammeByTitle(
                programmeRequest.getTitle()).orElse(null);

        if(programme != null) {
            return new BaseResponse("Programme with title " + programmeRequest.getTitle() + " already exists");
        }

        programme = programmeMapper.mapFromCreateRequest(programmeRequest);
        programme.setId(UUID.randomUUID());
        programmeCrudService.createUpdateProgramme(programme);
        return new BaseResponse("Programme created successfully");
    }

    public BaseResponse updateProgramme(UUID id, CreateProgrammeRequest programmeRequest) {
        Programme programme = programmeCrudService.getProgrammeById(id).get();
        if(programme == null) {
            return createProgramme(programmeRequest);
        }

        programme = programmeMapper.mapFromCreateRequest(programmeRequest);
        programmeCrudService.createUpdateProgramme(programme);

        return new BaseResponse("Programme updated successfully");
    }

    public BaseResponse deleteProgramme(UUID id) {
        programmeCrudService.deleteProgramme(id);
        return new BaseResponse("Programme deleted successfully");
    }

    public BaseResponse addDisciplineToProgramme(UUID programmeId, CreateDisciplineRequest disciplineRequest) {
        Programme programme = programmeCrudService.getProgrammeById(programmeId).get();
        if(programme == null) {
            throw new IllegalArgumentException("Programme with id " + programmeId + " not found");
        }

        Discipline discipline = disciplineCrudService.getDisciplineByName(disciplineRequest.getName()).orElse(null);
        if(discipline != null) {
            return new BaseResponse("Discipline with name " + disciplineRequest.getName() + " already exists");
        }

        discipline = disciplineMapper.mapFromCreateRequest(disciplineRequest);
        discipline.setId(UUID.randomUUID());
        discipline.setProgramme(programme);
        disciplineCrudService.createUpdateDiscipline(discipline);
        return new BaseResponse("Discipline added successfully");
    }
}

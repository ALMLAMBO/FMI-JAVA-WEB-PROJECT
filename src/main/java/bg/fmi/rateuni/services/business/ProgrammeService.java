package bg.fmi.rateuni.services.business;

import bg.fmi.rateuni.dto.request.CreateDisciplineRequest;
import bg.fmi.rateuni.dto.request.CreateProgrammeRequest;
import bg.fmi.rateuni.dto.response.DisciplineResponse;
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
    private DisciplineCrudService disciplineCrudService;

    @Autowired
    private ProgrammeMapper programmeMapper;

    @Autowired
    private DisciplineMapper disciplineMapper;

    public List<ProgrammeInfoResponse> getAllPrograms() {
        return programmeCrudService
                .getAllPrograms()
                .stream()
                .map(programme -> {
                    ProgrammeInfoResponse programmeInfoResponse = programmeMapper.mapToInfoResponseDto(programme);
                    List<DisciplineResponse> disciplines = disciplineCrudService
                            .getDisciplineByID(programme.getId())
                            .stream()
                            .map(discipline -> disciplineMapper.mapToDto(discipline))
                            .toList();

                    programmeInfoResponse.setDisciplines(disciplines);
                    return programmeInfoResponse;
                })
                .toList();
    }

    public ProgrammeInfoResponse getProgrammeById(UUID id) {
        Programme programme = programmeCrudService.getProgrammeById(id).get();
        if(programme == null) {
            throw new IllegalArgumentException("Programme with id " + id + " not found");
        }

        return programmeMapper.mapToInfoResponseDto(programme);
    }

    public void createUpdateProgramme(CreateProgrammeRequest createProgrammeRequest) {
        Programme programme = programmeMapper.mapFromCreateRequest(createProgrammeRequest);
        programme.setId(UUID.randomUUID());
        programmeCrudService.createUpdateProgramme(programme);
    }

    public void addDisciplineToProgramme(UUID programmeId, CreateDisciplineRequest disciplineRequest) {
        Discipline discipline = disciplineMapper.mapFromCreateRequest(disciplineRequest);
        disciplineCrudService.createUpdateDiscipline(discipline);
        Programme programme = programmeCrudService.getProgrammeById(programmeId).get();
        programme.getProgrammeDisciplines().add(discipline);
        programmeCrudService.addDisciplineToProgramme(programmeId, programme.getProgrammeDisciplines());
    }
}

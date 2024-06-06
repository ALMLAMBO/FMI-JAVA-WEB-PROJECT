package bg.fmi.rateuni.services.business;

import bg.fmi.rateuni.dto.response.DisciplineResponse;
import bg.fmi.rateuni.mappers.DisciplineMapper;
import bg.fmi.rateuni.mappers.ProgrammeMapper;
import bg.fmi.rateuni.models.Discipline;
import bg.fmi.rateuni.services.crud.DisciplineCrudService;
import bg.fmi.rateuni.services.crud.ReviewCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DisciplineService {

    @Autowired
    private DisciplineCrudService disciplineCrudService;

    @Autowired
    private ProgrammeMapper programmeMapper;

    @Autowired
    private DisciplineMapper disciplineMapper;

    public List<DisciplineResponse> getAllDisciplines() {
        return disciplineCrudService
                .getAllDisciplines()
                .stream()
                .map(discipline -> disciplineMapper.mapToDto(discipline))
                .toList();
    }

    public DisciplineResponse getDisciplineById(UUID id) {
        Discipline discipline = disciplineCrudService.getDisciplineByID(id).get();
        if(discipline == null) {
            throw new IllegalArgumentException("Discipline with id " + id + " not found");
        }
        return disciplineMapper.mapToDto(discipline);
    }

    public void createUpdateProgramme(Discipline discipline) {
        disciplineCrudService.createUpdateDiscipline(discipline);
    }
}

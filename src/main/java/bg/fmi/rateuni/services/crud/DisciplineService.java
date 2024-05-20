package bg.fmi.rateuni.services.crud;

import bg.fmi.rateuni.models.Discipline;
import bg.fmi.rateuni.repository.DisciplineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DisciplineService {
    @Autowired
    private DisciplineRepository disciplineRepository;

    public List<Discipline> getAllDisciplines() {
        return disciplineRepository.findAll();
    }

    public Optional<Discipline> getDisciplineByID(UUID id) {
        return disciplineRepository.findById(id);
    }

    public void createUpdateDiscipline (Discipline discipline) {
        disciplineRepository.save(discipline);
    }
}

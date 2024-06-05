package bg.fmi.rateuni.services.crud;

import bg.fmi.rateuni.models.Discipline;
import bg.fmi.rateuni.models.Programme;
import bg.fmi.rateuni.models.Review;
import bg.fmi.rateuni.repository.DisciplineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class DisciplineCrudService {
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
    
    public void addReviewToDiscipline(UUID disciplineId, Set<Review> reviews) {
        disciplineRepository.addReviewToDiscipline(disciplineId, reviews);
    }

    public List<Review> getDisciplineReviews(UUID id) {
        return disciplineRepository.findDisciplineReviewsById(id);
    }
    
    public void addDisciplineToProgramme(UUID disciplineId, Set<Programme> programmes) {
        disciplineRepository.addDisciplineToProgramme(disciplineId, programmes);
    }
    
    public List<Programme> getDisciplineProgrammes(UUID id) {
        return disciplineRepository.findDisciplineProgramsById(id);
    }
    
    public void addUserToDiscipline(UUID disciplineId, Set<Programme> users) {
        disciplineRepository.addUserToDiscipline(disciplineId, users);
    }
    
    public List<Programme> getDisciplineUsers(UUID id) {
        return disciplineRepository.findDisciplineUsersById(id);
    }
}

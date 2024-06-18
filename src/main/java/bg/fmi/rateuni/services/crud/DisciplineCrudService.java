package bg.fmi.rateuni.services.crud;

import bg.fmi.rateuni.models.Discipline;
import bg.fmi.rateuni.models.Review;
import bg.fmi.rateuni.models.User;
import bg.fmi.rateuni.repository.DisciplineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DisciplineCrudService {
    @Autowired
    private DisciplineRepository disciplineRepository;
    
    public Optional<Discipline> getDisciplineById(UUID id) {
        return disciplineRepository.findById(id);
    }

    public Optional<Discipline> getDisciplineByName(String name) {
        return disciplineRepository.findByName(name);
    }

    public void createUpdateDiscipline(Discipline discipline) {
        disciplineRepository.save(discipline);
    }

    public void deleteDiscipline(UUID id) {
        disciplineRepository.deleteById(id);
    }
    
    public void addUserToDiscipline(UUID id, User user) {
        Discipline discipline = disciplineRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Discipline not found"));
        
        discipline.getUsers().add(user);
        disciplineRepository.save(discipline);
    }

    public void removeUserFromDiscipline(UUID id, User user) {
        Discipline discipline = disciplineRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Discipline not found"));

        discipline.getUsers().remove(user);
        disciplineRepository.save(discipline);
    }

    public List<User> getUsersByDisciplineId(UUID id) {
        return disciplineRepository.findAllUsersByDisciplineId(id);
    }
    
    public void addReviewToDiscipline(UUID id, Review review) {
        Discipline discipline = disciplineRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Discipline not found"));
        
        discipline.getRatings().add(review);
        disciplineRepository.save(discipline);
    }
    
    public List<Review> getReviewsByDisciplineId(UUID id) {
        return disciplineRepository.findAllRatingsByDisciplineId(id);
    }

    public List<Discipline> getDisciplinesByProgrammeId(UUID programmeId) {
        return disciplineRepository.findDisciplinesByProgrammeId(programmeId);
    }
}
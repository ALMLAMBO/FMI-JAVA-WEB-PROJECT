package bg.fmi.rateuni.services.crud;

import bg.fmi.rateuni.models.University;
import bg.fmi.rateuni.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UniversityService {
    @Autowired
    private UniversityRepository universityRepository;

    public List<University> getAllUniversities() {
        return universityRepository.findAll();
    }
    
    public Optional<University> getUniversityById(UUID id) {
        return universityRepository.findById(id);
    }
    
    public void createUpdateUniversity(University university) {
        universityRepository.save(university);
    }
}
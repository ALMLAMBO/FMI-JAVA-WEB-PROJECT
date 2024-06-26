package bg.fmi.rateuni.services.crud;

import bg.fmi.rateuni.models.University;
import bg.fmi.rateuni.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UniversityCrudService {
    @Autowired
    private UniversityRepository universityRepository;

    public List<University> getAllUniversities() {
        return universityRepository.findAll();
    }
    
    public Optional<University> getUniversityById(UUID id) {
        return universityRepository.findById(id);
    }
    
    public Optional<University> getUniversityByName(String name) {
        return universityRepository.findByName(name);
    }
    
    public void createUpdateUniversity(University university) {
        universityRepository.save(university);
    }
    
    public void deleteUniversity(UUID id) {
        universityRepository.deleteById(id);
    }
}
package bg.fmi.rateuni.services.crud;

import bg.fmi.rateuni.models.Faculty;
import bg.fmi.rateuni.models.Programme;
import bg.fmi.rateuni.models.User;
import bg.fmi.rateuni.repository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class FacultyCrudService {
    @Autowired
    private FacultyRepository facultyRepository;

    public List<Faculty> getAllFaculties() {
        return facultyRepository.findAll();
    }

    public Optional<Faculty> getFacultyById (UUID id) {
        return facultyRepository.findById(id);
    }

    public void createUpdateFaculty (Faculty faculty) {
        facultyRepository.save(faculty);
    }
}
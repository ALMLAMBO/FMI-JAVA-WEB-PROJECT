package bg.fmi.rateuni.services.crud;

import bg.fmi.rateuni.models.Programme;
import bg.fmi.rateuni.repository.ProgrammeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProgrammeCrudService {
    @Autowired
    private ProgrammeRepository programmeRepository;

    public Optional<Programme> getProgrammeById(UUID id) {
        return programmeRepository.findById(id);
    }

    public Optional<Programme> getProgrammeByTitle(String title) {
        return programmeRepository.findByTitle(title);
    }

    public void createUpdateProgramme(Programme programme) {
        programmeRepository.save(programme);
    }

    public void deleteProgramme(UUID id) {
        programmeRepository.deleteById(id);
    }

    public List<Programme> getProgramsByFacultyId(UUID facultyId) {
        return programmeRepository.findProgramsByFacultyId(facultyId);
    }
}

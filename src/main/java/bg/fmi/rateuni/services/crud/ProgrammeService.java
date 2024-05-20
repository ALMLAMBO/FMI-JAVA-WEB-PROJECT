package bg.fmi.rateuni.services.crud;

import bg.fmi.rateuni.models.Programme;
import bg.fmi.rateuni.repository.ProgrammeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProgrammeService {
    @Autowired
    private ProgrammeRepository programmeRepository;

    public List<Programme> getAllPrograms() {
        return programmeRepository.findAll();
    }

    public Optional<Programme> getProgrammeByID(UUID id) {
        return programmeRepository.findById(id);
    }

    public void createUpdateProgramme (Programme programme) {
        programmeRepository.save(programme);
    }
}

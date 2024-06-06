package bg.fmi.rateuni.services.business;

import bg.fmi.rateuni.dto.request.CreateProgrammeRequest;
import bg.fmi.rateuni.dto.response.FacultyInfoResponse;
import bg.fmi.rateuni.dto.response.ProgrammeResponse;
import bg.fmi.rateuni.mappers.FacultyMapper;
import bg.fmi.rateuni.mappers.ProgrammeMapper;
import bg.fmi.rateuni.models.Faculty;
import bg.fmi.rateuni.models.Programme;
import bg.fmi.rateuni.services.crud.FacultyCrudService;
import bg.fmi.rateuni.services.crud.ProgrammeCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FacultyService {
    @Autowired
    private FacultyCrudService facultyCrudService;

    @Autowired
    private ProgrammeCrudService programmeCrudService;

    @Autowired
    private FacultyMapper facultyMapper;

    @Autowired
    private ProgrammeMapper programmeMapper;

    public List<FacultyInfoResponse> getAllFaculties() {
        return facultyCrudService
                .getAllFaculties()
                .stream()
                .map(faculty -> {
                    FacultyInfoResponse facultyInfoResponse = facultyMapper.mapToInfoResponseDto(faculty);
                    List<ProgrammeResponse> programs = facultyCrudService
                            .getProgramsByFacultyId(faculty.getId())
                            .stream()
                            .map(programme -> programmeMapper.mapToDto(programme))
                            .toList();
                    facultyInfoResponse.setPrograms(programs);
                    return facultyInfoResponse;
                })
                .toList();
    }

    public FacultyInfoResponse getFacultyById(UUID id) {
        Faculty faculty = facultyCrudService.getFacultyById(id).get();
        if(faculty == null) {
            throw new IllegalArgumentException("Faculty with id " + id + " not found");
        }

        return facultyMapper.mapToInfoResponseDto(faculty);
    }

    public void createUpdateFaculty(Faculty faculty) {
        facultyCrudService.createUpdateFaculty(faculty);
    }

    public void addProgrammeToFaculty(UUID facultyId, CreateProgrammeRequest programmeRequest) {
        Programme programme = programmeMapper.mapFromCreateRequest(programmeRequest);
        programmeCrudService.createUpdateProgramme(programme);
        Faculty faculty = facultyCrudService.getFacultyById(facultyId).get();
        faculty.getFacultyPrograms().add(programme);
        facultyCrudService.addProgrammeToFaculty(faculty.getId(), faculty.getFacultyPrograms());
    }
}

package bg.fmi.rateuni.services.business;

import bg.fmi.rateuni.dto.request.CreateProgrammeRequest;
import bg.fmi.rateuni.dto.request.CreateFacultyRequest;
import bg.fmi.rateuni.dto.response.BaseResponse;
import bg.fmi.rateuni.dto.response.FacultyInfoResponse;
import bg.fmi.rateuni.mappers.ProgrammeMapper;
import bg.fmi.rateuni.mappers.FacultyMapper;
import bg.fmi.rateuni.models.Programme;
import bg.fmi.rateuni.models.Faculty;
import bg.fmi.rateuni.services.crud.ProgrammeCrudService;
import bg.fmi.rateuni.services.crud.FacultyCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FacultyService {
    @Autowired
    private FacultyCrudService facultyCrudService;

    @Autowired
    private FacultyMapper facultyMapper;

    @Autowired
    private ProgrammeCrudService programmeCrudService;

    @Autowired
    private ProgrammeMapper programmeMapper;

    public FacultyInfoResponse getFacultyById(UUID id) {
        Faculty faculty = facultyCrudService.getFacultyById(id).get();
        if(faculty == null) {
            throw new IllegalArgumentException("Faculty with id " + id + " not found");
        }

        FacultyInfoResponse facultyInfoResponse = facultyMapper.mapToInfoResponseDto(faculty);
        facultyInfoResponse.setPrograms(programmeCrudService.getProgramsByFacultyId(id)
                .stream()
                .map(programme -> programmeMapper.mapToDto(programme))
                .toList());

        return facultyInfoResponse;
    }

    public BaseResponse createFaculty(CreateFacultyRequest facultyRequest) {
        Faculty faculty = facultyCrudService.getFacultyByName(
                facultyRequest.getName()).orElse(null);

        if(faculty != null) {
            return new BaseResponse("Faculty with name " + facultyRequest.getName() + " already exists");
        }

        faculty = facultyMapper.mapFromCreateRequest(facultyRequest);
        faculty.setId(UUID.randomUUID());
        facultyCrudService.createUpdateFaculty(faculty);
        return new BaseResponse("Faculty created successfully");
    }

    public BaseResponse updateFaculty(UUID id, CreateFacultyRequest facultyRequest) {
        Faculty faculty = facultyCrudService.getFacultyById(id).get();
        if(faculty == null) {
            return createFaculty(facultyRequest);
        }

        faculty = facultyMapper.mapFromCreateRequest(facultyRequest);
        facultyCrudService.createUpdateFaculty(faculty);

        return new BaseResponse("Faculty updated successfully");
    }

    public BaseResponse deleteFaculty(UUID id) {
        facultyCrudService.deleteFaculty(id);
        return new BaseResponse("Faculty deleted successfully");
    }

    public BaseResponse addProgrammeToFaculty(UUID facultyId, CreateProgrammeRequest programmeRequest) {
        Faculty faculty = facultyCrudService.getFacultyById(facultyId).get();
        if(faculty == null) {
            throw new IllegalArgumentException("Faculty with id " + facultyId + " not found");
        }

        Programme programme = programmeCrudService.getProgrammeByTitle(programmeRequest.getTitle()).orElse(null);
        if(programme != null) {
            return new BaseResponse("Programme with name " + programmeRequest.getTitle() + " already exists");
        }

        programme = programmeMapper.mapFromCreateRequest(programmeRequest);
        programme.setId(UUID.randomUUID());
        programme.setFaculty(faculty);
        programmeCrudService.createUpdateProgramme(programme);
        return new BaseResponse("Programme added successfully");
    }
}

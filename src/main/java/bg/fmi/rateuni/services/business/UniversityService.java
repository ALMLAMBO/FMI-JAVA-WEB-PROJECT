package bg.fmi.rateuni.services.business;

import bg.fmi.rateuni.dto.request.CreateFacultyRequest;
import bg.fmi.rateuni.dto.request.CreateUniversityRequest;
import bg.fmi.rateuni.dto.response.FacultyResponse;
import bg.fmi.rateuni.dto.response.UniversityInfoResponse;
import bg.fmi.rateuni.mappers.FacultyMapper;
import bg.fmi.rateuni.mappers.UniversityMapper;
import bg.fmi.rateuni.models.Faculty;
import bg.fmi.rateuni.models.University;
import bg.fmi.rateuni.services.crud.FacultyCrudService;
import bg.fmi.rateuni.services.crud.UniversityCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UniversityService {
    @Autowired
    private UniversityCrudService universityCrudService;
    
    @Autowired
    private FacultyCrudService facultyCrudService;
    
    @Autowired
    private UniversityMapper universityMapper;
    
    @Autowired
    private FacultyMapper facultyMapper;
    
    public List<UniversityInfoResponse> getAllUniversities() {
        return universityCrudService
                .getAllUniversities()
                .stream()
                .map(university -> universityMapper.mapToInfoResponseDto(university))
                .toList();
    }
    
    public UniversityInfoResponse getUniversityById(UUID id) {
        University university = universityCrudService.getUniversityById(id).get();
        if(university == null) {
            throw new IllegalArgumentException("University with id " + id + " not found");
        }
        
        UniversityInfoResponse universityInfoResponse = universityMapper.mapToInfoResponseDto(university);
        return null;
    }
    
    public void createUpdateUniversity(CreateUniversityRequest universityRequest) {
        University university = universityMapper.mapFromCreateRequest(universityRequest);
        university.setId(UUID.randomUUID());
        universityCrudService.createUpdateUniversity(university);
    }
    
    public void addFacultyToUniversity(UUID universityId, CreateFacultyRequest facultyRequest) {
        Faculty faculty = facultyMapper.mapFromCreateRequest(facultyRequest);
        try {
            facultyCrudService.createUpdateFaculty(faculty);
        }
        catch(Exception e) {
            faculty.setId(UUID.randomUUID());
            facultyCrudService.createUpdateFaculty(faculty);
        }
        University university = universityCrudService.getUniversityById(universityId).get();
        //university.getUniversityFaculties().add(faculty);
        //universityCrudService.addFacultyToUniversity(university.getId(), university.getUniversityFaculties());
    }
}

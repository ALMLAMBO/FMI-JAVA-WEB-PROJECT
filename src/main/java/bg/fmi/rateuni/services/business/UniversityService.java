package bg.fmi.rateuni.services.business;

import bg.fmi.rateuni.dto.request.CreateFacultyRequest;
import bg.fmi.rateuni.dto.request.CreateUniversityRequest;
import bg.fmi.rateuni.dto.response.BaseResponse;
import bg.fmi.rateuni.dto.response.UniversityInfoResponse;
import bg.fmi.rateuni.dto.response.UniversityResponse;
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
    
    public List<UniversityResponse> getAllUniversities() {
        return universityCrudService
                .getAllUniversities()
                .stream()
                .map(university -> universityMapper.mapToDto(university))
                .toList();
    }
    
    public UniversityInfoResponse getUniversityById(UUID id) {
        University university = universityCrudService.getUniversityById(id).get();
        if(university == null) {
            throw new IllegalArgumentException("University with id " + id + " not found");
        }
        
        UniversityInfoResponse universityInfoResponse = universityMapper.mapToInfoResponseDto(university);
        universityInfoResponse.setFaculties(facultyCrudService.getFacultiesByUniversityId(id)
                .stream()
                .map(faculty -> facultyMapper.mapToDto(faculty))
                .toList());
        
        return universityInfoResponse;
    }
    
    public BaseResponse createUniversity(CreateUniversityRequest universityRequest) {
        University university = universityCrudService.getUniversityByName(
                universityRequest.getName()).orElse(null);
        
        if(university != null) {
            return new BaseResponse("University with name " + universityRequest.getName() + " already exists");
        }   
     
        university = universityMapper.mapFromCreateRequest(universityRequest);
        university.setId(UUID.randomUUID());
        universityCrudService.createUpdateUniversity(university);
        return new BaseResponse("University created successfully");
    }
    
    public BaseResponse updateUniversity(UUID id, CreateUniversityRequest universityRequest) {
        University university = universityCrudService.getUniversityById(id).get();
        if(university == null) {
            return createUniversity(universityRequest);
        }
        
        university = universityMapper.mapFromCreateRequest(universityRequest);
        universityCrudService.createUpdateUniversity(university);
        
        return new BaseResponse("University updated successfully");
    }
    
    public BaseResponse deleteUniversity(UUID id) {
        University university = universityCrudService.getUniversityById(id).orElse(null);
        if(university == null) {
            return new BaseResponse("University with id " + id + " not found");
        }
        
        universityCrudService.deleteUniversity(id);
        return new BaseResponse("University deleted successfully");
    }
    
    public BaseResponse addFacultyToUniversity(UUID universityId, CreateFacultyRequest facultyRequest) {
        University university = universityCrudService.getUniversityById(universityId).get();
        if(university == null) {
            throw new IllegalArgumentException("University with id " + universityId + " not found");
        }

        Faculty faculty = facultyCrudService.getFacultyByName(facultyRequest.getName()).orElse(null);
        if(faculty != null) {
            return new BaseResponse("Faculty with name " + facultyRequest.getName() + " already exists");
        }
        
        faculty = facultyMapper.mapFromCreateRequest(facultyRequest);
        faculty.setId(UUID.randomUUID());
        faculty.setUniversity(university);
        facultyCrudService.createUpdateFaculty(faculty);
        return new BaseResponse("Faculty added successfully");
    }
}

package bg.fmi.rateuni.services.business;

import bg.fmi.rateuni.dto.request.CreateDisciplineRequest;
import bg.fmi.rateuni.dto.request.CreateReviewRequest;
import bg.fmi.rateuni.dto.response.BaseResponse;
import bg.fmi.rateuni.dto.response.DisciplineInfoResponse;
import bg.fmi.rateuni.dto.response.DisciplineResponse;
import bg.fmi.rateuni.mappers.DisciplineMapper;
import bg.fmi.rateuni.mappers.ReviewMapper;
import bg.fmi.rateuni.models.Discipline;
import bg.fmi.rateuni.models.Review;
import bg.fmi.rateuni.services.crud.DisciplineCrudService;
import bg.fmi.rateuni.services.crud.ProgrammeCrudService;
import bg.fmi.rateuni.services.crud.ReviewCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DisciplineService {

    @Autowired
    private DisciplineCrudService disciplineCrudService;

    @Autowired
    private DisciplineMapper disciplineMapper;
    
    @Autowired
    private ReviewCrudService reviewCrudService;
    
    @Autowired
    private ProgrammeCrudService programmeCrudService;
    
    @Autowired
    private ReviewMapper reviewMapper;

    public DisciplineInfoResponse getDisciplineById(UUID id) {
        Discipline discipline = disciplineCrudService.getDisciplineById(id).get();
        if(discipline == null) {
            throw new IllegalArgumentException("Discipline with id " + id + " not found");
        }
        
        DisciplineInfoResponse response = disciplineMapper.mapToInfoDto(discipline);
        response.setReviews(reviewCrudService.getReviewsByDisciplineId(id)
                .stream()
                .map(review -> reviewMapper.mapToDto(review))
                .toList());
        
        return response;
    }

    public List<DisciplineResponse> getDisciplinesByProgrammeId(UUID programmeId) {
        return disciplineCrudService.getDisciplinesByProgrammeId(programmeId)
                .stream()
                .map(discipline -> disciplineMapper.mapToDto(discipline))
                .toList();
    }

    public BaseResponse createDiscipline(CreateDisciplineRequest createDisciplineRequest) {
        Discipline discipline = disciplineCrudService.getDisciplineByName(createDisciplineRequest.getName()).orElse(null);
        if(discipline != null) {
            return new BaseResponse("Discipline with name " + createDisciplineRequest.getName() + " already exists");
        }
        
        discipline = disciplineMapper.mapFromCreateRequest(createDisciplineRequest);
        discipline.setId(UUID.randomUUID());
        discipline.setProgramme(programmeCrudService.getProgrammeById(createDisciplineRequest.getProgrammeId()).get());
        disciplineCrudService.createUpdateDiscipline(discipline);
        return new BaseResponse("Discipline created successfully");
    }
    
    public BaseResponse updateDiscipline(UUID id, CreateDisciplineRequest createDisciplineRequest) {
        Discipline discipline = disciplineCrudService.getDisciplineById(id).get();
        if(discipline == null) {
            return new BaseResponse("Discipline with id " + id + " not found");
        }
        
        discipline = disciplineMapper.mapFromCreateRequest(createDisciplineRequest);
        discipline.setId(id);
        disciplineCrudService.createUpdateDiscipline(discipline);
        return new BaseResponse("Discipline updated successfully");
    }
    
    public BaseResponse deleteDiscipline(UUID id) {
        Discipline discipline = disciplineCrudService.getDisciplineById(id).get();
        if(discipline == null) {
            return new BaseResponse("Discipline with id " + id + " not found");
        }
        
        disciplineCrudService.deleteDiscipline(id);
        return new BaseResponse("Discipline deleted successfully");
    }
    
    public BaseResponse addReviewToDiscipline(UUID id, CreateReviewRequest reviewRequest) {
        Discipline discipline = disciplineCrudService.getDisciplineById(id).get();
        if(discipline == null) {
            return new BaseResponse("Discipline with id " + id + " not found");
        }

        Review review = reviewMapper.mapFromCreateReviewRequest(reviewRequest);
        review.setDiscipline(discipline);
        review.setVisible(false);
        review.setId(UUID.randomUUID());
        reviewCrudService.createUpdateReview(review);
        return new BaseResponse("Review submitted for check successfully");
    }
}

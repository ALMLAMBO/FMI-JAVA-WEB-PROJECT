package bg.fmi.rateuni.controllers;

import bg.fmi.rateuni.dto.request.CreateFacultyRequest;
import bg.fmi.rateuni.dto.request.CreateUniversityRequest;
import bg.fmi.rateuni.dto.response.BaseResponse;
import bg.fmi.rateuni.dto.response.UniversityInfoResponse;
import bg.fmi.rateuni.services.business.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/universities")
public class UniversityController {
    @Autowired
    private UniversityService universityService;
    
    @GetMapping
    public ResponseEntity<List<UniversityInfoResponse>> getAllUniversities() {
        return ResponseEntity.ok(universityService.getAllUniversities());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<UniversityInfoResponse> getUniversityById(@PathVariable UUID id) {
        return ResponseEntity.ok(universityService.getUniversityById(id));
    }
    
    @PostMapping
    public ResponseEntity<BaseResponse> createUniversity(@RequestBody CreateUniversityRequest createUniversityRequest) {
        return ResponseEntity.ok(universityService.createUniversity(createUniversityRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse> getUniversityById(@PathVariable UUID id, CreateUniversityRequest createUniversityRequest) {
        return ResponseEntity.ok(universityService.updateUniversity(id, createUniversityRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> deleteUniversityById(@PathVariable UUID id) {
        return ResponseEntity.ok(universityService.deleteUniversity(id));
    }
    
    @PostMapping("/{id}/faculty")
    public ResponseEntity<BaseResponse> addFacultyToUniversity(@PathVariable UUID id, @RequestBody CreateFacultyRequest createFacultyRequest) {
        universityService.addFacultyToUniversity(id, createFacultyRequest);
        return ResponseEntity.ok(new BaseResponse("Faculty added successfully"));
    }
}

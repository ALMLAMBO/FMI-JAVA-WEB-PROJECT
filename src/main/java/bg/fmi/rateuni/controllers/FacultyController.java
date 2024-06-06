package bg.fmi.rateuni.controllers;

import bg.fmi.rateuni.dto.request.CreateFacultyRequest;
import bg.fmi.rateuni.dto.request.CreateProgrammeRequest;
import bg.fmi.rateuni.dto.response.BaseResponse;
import bg.fmi.rateuni.dto.response.FacultyInfoResponse;
import bg.fmi.rateuni.services.business.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/faculties")
public class FacultyController {

    @Autowired
    private FacultyService facultyService;

    @GetMapping
    public ResponseEntity<List<FacultyInfoResponse>> getAllFaculties() {
        return ResponseEntity.ok(facultyService.getAllFaculties());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FacultyInfoResponse> getUniversityById(@PathVariable UUID id) {
        return ResponseEntity.ok(facultyService.getFacultyById(id));
    }

    @PostMapping
    public ResponseEntity<BaseResponse> createFaculty(@RequestBody CreateFacultyRequest createFacultyRequest) {
        facultyService.createUpdateFaculty(createFacultyRequest);
        return ResponseEntity.ok(new BaseResponse("Faculty created successfully"));
    }

    @PostMapping("/{id}/add-programme")
    public ResponseEntity<BaseResponse> addProgrammeToFaculty(@PathVariable UUID id, @RequestBody CreateProgrammeRequest createProgrammeRequest) {
        facultyService.addProgrammeToFaculty(id, createProgrammeRequest);
        return ResponseEntity.ok(new BaseResponse("Programme added successfully"));
    }

}

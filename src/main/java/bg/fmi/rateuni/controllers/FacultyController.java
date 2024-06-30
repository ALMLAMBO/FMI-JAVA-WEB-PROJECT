package bg.fmi.rateuni.controllers;

import bg.fmi.rateuni.dto.request.CreateFacultyRequest;
import bg.fmi.rateuni.dto.response.BaseResponse;
import bg.fmi.rateuni.dto.response.FacultyInfoResponse;
import bg.fmi.rateuni.dto.response.FacultyResponse;
import bg.fmi.rateuni.services.business.FacultyService;
import bg.fmi.rateuni.services.business.UniversityService;
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


    @GetMapping("/{id}")
    public ResponseEntity<FacultyInfoResponse> getFacultyById(@PathVariable UUID id) {
        return ResponseEntity.ok(facultyService.getFacultyById(id));
    }

    @GetMapping("/{id}/faculties")
    public ResponseEntity<List<FacultyResponse>> getFacultiesForUniversity(@PathVariable UUID id) {
        return ResponseEntity.ok(facultyService.getFacultiesForUniversity(id));
    }

    @PostMapping
    public ResponseEntity<BaseResponse> createFaculty(@RequestBody CreateFacultyRequest createFacultyRequest) {
        return ResponseEntity.ok(facultyService.createFaculty(createFacultyRequest));
    }

}

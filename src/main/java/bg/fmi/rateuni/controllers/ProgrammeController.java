package bg.fmi.rateuni.controllers;

import bg.fmi.rateuni.dto.request.CreateDisciplineRequest;
import bg.fmi.rateuni.dto.request.CreateProgrammeRequest;
import bg.fmi.rateuni.dto.response.BaseResponse;
import bg.fmi.rateuni.dto.response.ProgrammeInfoResponse;
import bg.fmi.rateuni.dto.response.ProgrammeResponse;
import bg.fmi.rateuni.services.business.ProgrammeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/programme")
public class ProgrammeController {

    @Autowired
    private ProgrammeService programmeService;

    @GetMapping("/{id}")
    public ResponseEntity<ProgrammeInfoResponse> getProgrammeById(@PathVariable UUID id) {
        return ResponseEntity.ok(programmeService.getProgrammeById(id));
    }

    @GetMapping("/{id}/programs")
    public ResponseEntity<List<ProgrammeResponse>> getProgrammesForFaculty(@PathVariable UUID id) {
        return ResponseEntity.ok(programmeService.getProgramsForFaculty(id));
    }

    @PostMapping
    public ResponseEntity<BaseResponse> createProgramme(@RequestBody CreateProgrammeRequest createProgrammeRequest) {
        return ResponseEntity.ok(programmeService.createProgramme(createProgrammeRequest));
    }

    @PostMapping("/{id}/discipline")
    public ResponseEntity<BaseResponse> addDisciplineToProgramme(@PathVariable UUID id, @RequestBody CreateDisciplineRequest createDisciplineRequest) {
        return ResponseEntity.ok( programmeService.addDisciplineToProgramme(id, createDisciplineRequest));
    }

}

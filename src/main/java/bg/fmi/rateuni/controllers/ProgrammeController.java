package bg.fmi.rateuni.controllers;

import bg.fmi.rateuni.dto.request.CreateDisciplineRequest;
import bg.fmi.rateuni.dto.request.CreateProgrammeRequest;
import bg.fmi.rateuni.dto.request.CreateProgrammeRequest;
import bg.fmi.rateuni.dto.response.BaseResponse;
import bg.fmi.rateuni.dto.response.ProgrammeInfoResponse;
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

    @PostMapping
    public ResponseEntity<BaseResponse> createProgramme(@RequestBody CreateProgrammeRequest createProgrammeRequest) {
        programmeService.createProgramme(createProgrammeRequest);
        return ResponseEntity.ok(new BaseResponse("Programme created successfully"));
    }

    @PostMapping("/{id}/add-discipline")
    public ResponseEntity<BaseResponse> addDisciplineToProgramme(@PathVariable UUID id, @RequestBody CreateDisciplineRequest createDisciplineRequest) {
        programmeService.addDisciplineToProgramme(id, createDisciplineRequest);
        return ResponseEntity.ok(new BaseResponse("Programme added successfully"));
    }

}

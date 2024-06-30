package bg.fmi.rateuni.controllers;

import bg.fmi.rateuni.dto.request.CreateDisciplineRequest;
import bg.fmi.rateuni.dto.request.CreateReviewRequest;
import bg.fmi.rateuni.dto.response.BaseResponse;
import bg.fmi.rateuni.dto.response.DisciplineInfoResponse;
import bg.fmi.rateuni.dto.response.DisciplineResponse;
import bg.fmi.rateuni.services.business.DisciplineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/disciplines")
public class DisciplineController {

    @Autowired
    private DisciplineService disciplineService;

    @GetMapping("/{id}")
    public ResponseEntity<DisciplineInfoResponse> getDisciplineById(@PathVariable UUID id) {
        return ResponseEntity.ok(disciplineService.getDisciplineById(id));
    }

    @GetMapping("/{id}/discipline")
    public ResponseEntity<List<DisciplineResponse>> getDisciplinesForProgramme(@PathVariable UUID id) {
        return ResponseEntity.ok(disciplineService.getDisciplinesByProgrammeId(id));
    }

    @PostMapping
    public ResponseEntity<BaseResponse> createDiscipline(@RequestBody CreateDisciplineRequest createDisciplineRequest) {
        return ResponseEntity.ok(disciplineService.createDiscipline(createDisciplineRequest));
    }

    @PostMapping("/{id}/review")
    public ResponseEntity<BaseResponse> addReviewToDiscipline(@PathVariable UUID id, @RequestBody CreateReviewRequest createReviewRequest) {
        return ResponseEntity.ok(disciplineService.addReviewToDiscipline(id, createReviewRequest));
    }
}

package bg.fmi.rateuni.controllers;

import bg.fmi.rateuni.dto.request.RegisterRequest;
import bg.fmi.rateuni.dto.response.BaseResponse;
import bg.fmi.rateuni.dto.response.UserDetail;
import bg.fmi.rateuni.services.business.AuthService;
import bg.fmi.rateuni.services.business.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @GetMapping("/{id}")
    public ResponseEntity<UserDetail> getUserById(@PathVariable UUID id) {
        return ResponseEntity.ok(userService.getUserDetails(id));
    }

    @PostMapping
    public ResponseEntity<BaseResponse> createUser(@RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(authService.registerUser(registerRequest));
    }

    @PostMapping("/{id}/discipline")
    public ResponseEntity<BaseResponse> addDisciplineToUser(@PathVariable UUID id, @RequestBody UserDetail userDetail) {
        return ResponseEntity.ok(userService.addUserToDiscipline(userDetail, id));
    }
}

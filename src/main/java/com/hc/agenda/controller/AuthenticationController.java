package com.hc.agenda.controller;

import com.hc.agenda.auth.AuthenticationRequest;
import com.hc.agenda.auth.AuthenticationResponse;
import com.hc.agenda.auth.AuthenticationService;
import com.hc.agenda.auth.RegisterRequest;
import com.hc.agenda.dto.DtoPageResponse;
import com.hc.agenda.dto.DtoRoleUser;
import com.hc.agenda.services.PageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;
    private final PageService pageService;


    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }

    @GetMapping("/openVerifTest")
    public ResponseEntity<DtoPageResponse> openVerifTest(@RequestBody String password){
        return ResponseEntity.ok(pageService.verifMdpPage(password, "/api/v1/auth/verification"));
    }

    @PostMapping("/roles")
    public ResponseEntity<DtoRoleUser> giveRoles(@RequestBody String token) {
        return ResponseEntity.ok(pageService.listRoleUser(token));
    }
}

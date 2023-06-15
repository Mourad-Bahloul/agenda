package com.hc.agenda.cucumberglue;

import com.hc.agenda.auth.AuthenticationRequest;
import com.hc.agenda.auth.AuthenticationResponse;
import com.hc.agenda.auth.AuthenticationService;
import com.hc.agenda.controller.AuthenticationController;
import com.hc.agenda.dto.DtoPageResponse;
import com.hc.agenda.repositories.RoleRepository;
import com.hc.agenda.repositories.UserRepository;
import com.hc.agenda.services.ApplicationConfig;
import com.hc.agenda.services.PageService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RequiredArgsConstructor

public class AuthenticationTest {
    private ResponseEntity<DtoPageResponse> response;
    private UserRepository userRepository;
    private ApplicationConfig applicationConfig;
    private RoleRepository roleRepository;
    @Autowired
    private AuthenticationService service;
    private PageService pageService;
    private AuthenticationController authenticationController;
    private AuthenticationRequest authenticationRequest;
    private ResponseEntity<AuthenticationResponse> authenticationResponse;
    @Given("a registered user with email {string} and password {string}")
    public void aRegisteredUserWithEmailAndPassword(String email, String password) {
        // Prepare the authentication request with the provided email and password

        pageService = new PageService(roleRepository,userRepository,applicationConfig);
        authenticationController = new AuthenticationController(service, pageService);
        authenticationRequest = new AuthenticationRequest(email, password);
    }

    @When("the user authenticates with email {string} and password {string}")
    public void theUserAuthenticatesWithEmailAndPassword(String email, String password) {
        // Set the email and password in the authentication request
        authenticationRequest.setEmail(email);
        authenticationRequest.setPassword(password);

        // Call the authentication endpoint
        authenticationResponse = authenticationController.authenticate(authenticationRequest);

        assertNotNull(authenticationResponse);
    }

    @Then("the response status code should bee {int}")
    public void theResponseStatusCodeShouldBee(int expectedStatusCode) {
        int actualStatusCode = authenticationResponse.getStatusCodeValue();

        assertEquals(expectedStatusCode, actualStatusCode);
    }


    @Then("the response body should contain a JSON token")
    public void theResponseBodyShouldContainJsonToken() {
        // Get the authentication response body from the response
        AuthenticationResponse body = authenticationResponse.getBody();

        // Assert that the response body is not null
        assertNotNull(body);

        // Assert that the response body contains a token
        assertNotNull(body.getToken());
    }
}

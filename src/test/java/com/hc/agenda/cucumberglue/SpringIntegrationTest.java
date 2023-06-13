package com.hc.agenda.cucumberglue;

import com.hc.agenda.auth.AuthenticationRequest;
import com.hc.agenda.auth.AuthenticationResponse;
import com.hc.agenda.auth.AuthenticationService;
import com.hc.agenda.controller.AuthenticationController;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@CucumberContextConfiguration
public class SpringIntegrationTest {

    private AuthenticationController authenticationController;
    private AuthenticationRequest authenticationRequest;
    private ResponseEntity<AuthenticationResponse> authenticationResponse;
    @Given("a registered user with email {string} and password {string}")
    public void aRegisteredUserWithEmailAndPassword(String email, String password) {
        // Prepare the authentication request with the provided email and password
        authenticationRequest = new AuthenticationRequest(email, password);
    }

    @When("the user authenticates with email {string} and password {string}")
    public void theUserAuthenticatesWithEmailAndPassword(String email, String password) {
        // Set the email and password in the authentication request
        authenticationRequest.setEmail(email);
        authenticationRequest.setPassword(password);

        // Call the authentication endpoint
        authenticationResponse = authenticationController.authenticate(authenticationRequest);
    }

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int expectedStatusCode) {
        // Get the actual status code from the response
        int actualStatusCode = authenticationResponse.getStatusCodeValue();

        // Assert that the actual status code matches the expected status code
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
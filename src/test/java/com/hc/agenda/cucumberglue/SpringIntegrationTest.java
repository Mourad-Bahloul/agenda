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


}
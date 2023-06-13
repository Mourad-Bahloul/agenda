package com.hc.agenda.cucumberglue;


import com.hc.agenda.controller.MainController;
import com.hc.agenda.dto.DtoPageResponse;
import com.hc.agenda.repositories.UserRepository;
import com.hc.agenda.services.ApplicationConfig;
import com.hc.agenda.services.PageService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MainControllerSteps {

    private MainController mainController;
    private ResponseEntity<DtoPageResponse> response;
    private UserRepository userRepository;
    private ApplicationConfig applicationConfig;

    @Given("the main controller")
    public void theMainController() {
        PageService pageService = new PageService(userRepository, applicationConfig);
        mainController = new MainController(pageService,userRepository);
    }
    @When("I make a GET request to {string}")
    public void iMakeAGetRequestTo(String endpoint) {
        response = mainController.index();
    }

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int expectedStatusCode) {
        int actualStatusCode = response.getStatusCodeValue();

        assertEquals(expectedStatusCode, actualStatusCode);
    }

    @Then("the response body should contain a valid JSON file")
    public void theResponseBodyShouldContainValidJsonFile() {
        DtoPageResponse body = response.getBody();

        assertNotNull(body);

        assertEquals("True", body.getBooleanPage());
        assertEquals("/api/v1/main/index", body.getPageReturn());
    }

}

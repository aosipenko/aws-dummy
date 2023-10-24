package se.telenor.its.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.util.Random;
import java.util.UUID;

public class DummySteps {

    private final static Random RANDOM = new Random();

    @Given("I print random string")
    public void printRandomString() {
        System.out.println(UUID.randomUUID());
    }

    @When("I print random number")
    public void printRandomInt() {
        System.out.println(RANDOM.nextInt(1000000));
    }

    @Then("step sometimes fails")
    public void failAtRandom() {
        Assert.assertTrue(RANDOM.nextInt(100) > 15);
    }
}

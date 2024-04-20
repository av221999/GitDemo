package stepDefinations;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class stepDefination {
	

@Given("User is on Netbanking landing page")
public void user_is_on_netbanking_landing_page() {
    // Write code here that turns the phrase above into concrete actions
	System.out.println("navigated to login url");
}

@When("User login into application with {string} and password {string}")
public void user_login_into_application_with_and_password(String string1, String string2) {
    // Write code here that turns the phrase above into concrete actions
	System.out.println("loggged in using "+ string1 + " and password " + string2);
	
}

@Then("Home page is populated")
public void home_page_is_populated() {
    // Write code here that turns the phrase above into concrete actions
	System.out.println("Validated Home Page");
    
}

@Then("Cards displayed is {string}")
public void cards_displayed_is(String string) {
    // Write code here that turns the phrase above into concrete actions
    System.out.println("Validated cards");
}


}

package TestStepDefinitions;

import java.util.List;
import java.util.Map;
import Context.TestContext;
import Pages.CreateFormPage;
import Pages.FormDescriptionPage;
import TestStepsData.FormData;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class FormFill<LeadData> {
	public TestContext context;
	public CreateFormPage createformpage;
	
	public FormFill(TestContext context) {
		this.context=context;
		this.createformpage=this.context.getpageobjectmanager().getCreateFormPage();
		
	}
	@Given("I am on Form Page")
	public void goFormPage() {
		createformpage.load("Chrome");
		
	}
	
	@When("I fill up all fields of the form and submit")
	public void fillDetails(List<FormData> formData) {
		createformpage.submitFormDetails(formData);
	}
	
	@DataTableType
    public FormData entry(Map<String, String> entry) {
		//System.out.println(entry.toString());
        return new FormData(entry.get("FirstName"),entry.get("LastName"),entry.get("Email"),entry.get("Gender"),
        		entry.get("Mobile"),entry.get("DOB"),entry.get("Subjects"),entry.get("Hobbies"),entry.get("Current Address"),
        		entry.get("State"),entry.get("City"));
    }
	
	@Then("Form information overlay should open")
	public void formDescription() {
		System.out.println("Form description is shown");
	}
}

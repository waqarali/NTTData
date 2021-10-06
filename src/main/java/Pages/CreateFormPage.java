package Pages;

import java.util.List;

import Managers.WebDriverManager;
import TestStepsData.FormData;

public class CreateFormPage {
	
	WebDriverManager app;
	
	public CreateFormPage(WebDriverManager app) {
		this.app=app;
	}

	public void load(String browser) {
		app.openBrowser(browser);
		app.navigate("url");
		//app.verifyText("expectedText");
	}

	public void submitFormDetails(List<FormData> formData) {
		app.type("form_first_name_xpath", formData.get(0).firstName);
		app.type("form_last_name_xpath", formData.get(0).lastName);
		app.type("form_company_id", formData.get(0).email);
		app.click("form_gender_xpath");
		app.type("form_mobile_css", formData.get(0).mobile);
		app.scrollWindow();
		app.selectDob("form_dob_xpath", formData.get(0).dob);
		app.dropDownSelect("form_subjects_xpath", formData.get(0).subjects);
		app.click("form_hobbies_xpath");
		app.type("form_address_xpath", formData.get(0).address);
		app.nextElement("form_address_xpath",formData.get(0).state);
		app.typeOnly(formData.get(0).city);
		app.click("form_submit_css");
	}
	
}

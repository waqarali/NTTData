package Managers;

import Pages.CreateFormPage;
import Pages.FormDescriptionPage;

public class PageObjectManager {
	
	CreateFormPage createformpage;
	FormDescriptionPage formdescriptionpage;
	WebDriverManager app;
	
	public PageObjectManager() {
		
		this.app = new WebDriverManager();
	}
	
	public CreateFormPage getCreateFormPage() {
		if(createformpage==null)
			createformpage = new CreateFormPage(app);
		return createformpage;
	}
	
}

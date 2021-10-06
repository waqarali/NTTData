package Context;

import Managers.PageObjectManager;

public class TestContext {

	private PageObjectManager pageobjectmanager;
	
	public TestContext() {
		
		this.pageobjectmanager = new PageObjectManager();
	}
	
	public PageObjectManager getpageobjectmanager() {
		return pageobjectmanager;
	}
}

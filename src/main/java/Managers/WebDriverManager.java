package Managers;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.asynchttpclient.util.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverManager {
	
	WebDriver driver;
	Properties prop;
	Keys keys;
	
	public WebDriverManager() {
		
		try{
			prop = new Properties();
			String path = System.getProperty("user.dir")+"\\src\\test\\resources\\project.properties";
			FileInputStream fis = new FileInputStream(path);
			prop.load(fis);
		}catch (Exception e) {
			e.printStackTrace();
			}
		
	}
	
	public void openBrowser(String browser) {
		
		if(browser.equals("Chrome")) {
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\chromedriver.exe");
			driver = new ChromeDriver();
		}else if(browser.equals("Mozilla")) {
			System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"\\geckodriver.exe");
			driver = new FirefoxDriver();
		}else if(browser.equals("IE")) {
			System.setProperty("webdriver.ie.driver",System.getProperty("user.dir")+"\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}else if(browser.equals("Edge")) {
			System.setProperty("webdriver.edge.driver",System.getProperty("user.dir")+"\\msedgedriver.exe");
			driver = new EdgeDriver();
		}
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}
	
	public void navigate(String urlKey) {
		driver.get(getProperty(urlKey));
	}
	
	public By getLocator(String locatorKey) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(locatorKey.endsWith("_id"))
			return By.id(getProperty(locatorKey));
		else if(locatorKey.endsWith("_name"))
			return By.name(getProperty(locatorKey));
		else if(locatorKey.endsWith("_xpath"))
			return By.xpath(getProperty(locatorKey));
		else
			return By.cssSelector(getProperty(locatorKey));
	}
	
	public void scrollWindow() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollTo(0,700)");
	}
	
	public void click(String locatorKey) {
		findElement(locatorKey).click();
	}
	
	public void dropDownSelect(String locatorKey, String data) {
		WebElement element = findElement(locatorKey);
		element.click();
		element.sendKeys(data);
        element.sendKeys(Keys.ARROW_DOWN.ENTER);
			
	 }
	
	
	public void type(String locatorKey, String data) {
		findElement(locatorKey).sendKeys(data);
	}
	
	public void typeOnly(String data) {
		 Actions act = new Actions(driver);
	        act.sendKeys(data).build().perform();
	        act.sendKeys(Keys.ARROW_DOWN.ENTER).build().perform();
	}
	
	public void nextElement(String locatorKey, String data) {
		WebElement element;
		element = findElement(locatorKey);
        element.sendKeys(Keys.TAB);
        Actions act = new Actions(driver);
        act = new Actions(driver);
        act.sendKeys(data).build().perform();
        act.sendKeys(Keys.ARROW_DOWN.ENTER).build().perform();
        act.sendKeys(Keys.TAB).build().perform();
	}
	
	public WebElement findElement(String locatorKey) {
		By locator = getLocator(locatorKey);
		WebElement element = null;
		try {
		  // present and visible
		  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		  wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		  wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		  element = driver.findElement(locator);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return element;
	}
	
public void selectDob(String locatorKey, String data) {
		findElement(locatorKey).click();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");
		
		try {
			Date dateToSelected = sdf.parse(data);
			String day = new SimpleDateFormat("dd").format(dateToSelected);
			int dayget = Integer.parseInt(day);
			System.out.println(dayget);
			String month = new SimpleDateFormat("MMMM").format(dateToSelected);
			System.out.println(month);
			String year = new SimpleDateFormat("yyyy").format(dateToSelected);
			System.out.println(year);
			
			WebElement element;
			element = findElement("calender_month_xpath");
			element.sendKeys(month);
			element.sendKeys(Keys.TAB);
			Actions act = new Actions(driver);
			act.sendKeys(year).build().perform();
			
			for(int i =1; i<=6; i++) {
				for(int j = 1; j<=7; j++) {
					WebElement we = driver.findElement(By.xpath("//*[@id=\"dateOfBirth\"]/div[2]/div[2]/div/div/div[2]/div[2]/div["+i+"]/div["+j+"]"));
					String dayfromcalender = we.getText();
					int dayconvert = Integer.parseInt(dayfromcalender);
						if(dayconvert==dayget) {
							we.click();
							break;
						}
				}
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

public void uploadPhoto(String locatorKey, String photoPath) {
	findElement(locatorKey).sendKeys(photoPath);
}
	
	/****************************Validation Functions***********************/
	
	
	public boolean verifyText(String actualText) {
		String ActualResult = findElement(actualText).getText();
		String expectedResult = getProperty("expectedText");
		if(expectedResult.equals(ActualResult))
			return true;
		else
			return false;
	}
	
	public boolean isElementPresent(String locatorKey) {
		By locator = getLocator(locatorKey);
		
		try {
		  // present and visible
		  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		  wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		  wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		 
		}catch(Exception ex) {
			return false;
		}
		return true;
	}
	
	/******************************Utility Functions************************/
	
	public String getProperty(String key) {
    	return prop.getProperty(key);
    }
}


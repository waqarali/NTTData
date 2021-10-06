package Managers;

import java.io.FileInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class TEST {
	
	static WebDriver driver;
	
	public static void main(String[] arg) {

	System.setProperty("webdriver.chrome.driver","C:\\Users\\waqar\\OneDrive\\Desktop\\Automation\\BrowserExes\\chromedriver.exe");
    driver = new ChromeDriver();
	driver.get("https://demoqa.com/automation-practice-form");
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	JavascriptExecutor js = (JavascriptExecutor)driver;
	js.executeScript("window.scrollTo(0,200)");
	driver.findElement(By.xpath("//*[@id=\"dateOfBirthInput\"]")).click();
	
	//Date SelectionLogic
	
		String date = "04-01-1987";
	
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");
		
		try {
			Date dateToSelected = sdf.parse(date);
			//Date currentDate = new Date();
			String day = new SimpleDateFormat("dd").format(dateToSelected);
			int dayget = Integer.parseInt(day);
	        System.out.println(dayget);
			String month = new SimpleDateFormat("MMMM").format(dateToSelected);
			System.out.println(month);
			String year = new SimpleDateFormat("yyyy").format(dateToSelected);
			System.out.println(year);
			//String montYeartobeselected = month+" "+year;
			//System.out.println(montYeartobeselected);
			
			//driver.findElement(By.xpath("//*[@id=\"dateOfBirth\"]/div[2]/div[2]/div/div/div[2]/div[1]/div[2]/div[1]/select")).click();;
			WebElement el = driver.findElement(By.xpath("//*[@id=\"dateOfBirth\"]/div[2]/div[2]/div/div/div[2]/div[1]/div[2]/div[1]/select"));
			el.sendKeys(month);
			//Thread.sleep(3000);
			el.sendKeys(Keys.TAB);
			//Thread.sleep(3000);
			//WebElement el1 = driver.findElement(By.xpath("//*[@id=\"dateOfBirth\"]/div[2]/div[2]/div/div/div[2]/div[1]/div[2]/div[2]/select"));
			Actions act = new Actions(driver);
			act.sendKeys(year).build().perform();
			//el1.sendKeys(year);
			//driver.findElement(By.xpath("//*[@id=\"dateOfBirth\"]/div[2]/div[2]/div/div/div[2]/div[2]/div[1]/div[1]")).click();
			
			for(int i =1; i<=6; i++) {
				for(int j = 1; j<=7; j++) {
					WebElement we = driver.findElement(By.xpath("//*[@id=\"dateOfBirth\"]/div[2]/div[2]/div/div/div[2]/div[2]/div["+i+"]/div["+j+"]"));
					String dayfromcalender = we.getText();
					//String dayfromcalender = driver.findElement(By.xpath("//*[@id=\"dateOfBirth\"]/div[2]/div[2]/div/div/div[2]/div[2]/div["+i+"]/div["+j+"]")).getText();
					//System.out.println(dayfromcalender);
					int dayconvert = Integer.parseInt(dayfromcalender);
					System.out.println(dayconvert);
						if(dayconvert==dayget) {
							we.click();
						}
				}
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
				
		
		/*
		public boolean verifyTitle(String expectedTitle) {
			String ActualResult = driver.getTitle();
			String expectedResult = getProperty(expectedTitle);
			System.out.println(expectedResult+"    "+ActualResult);
			if(expectedResult.equals(ActualResult))
				return true;
			else
				return false;
			*/
	
	/*
	 * Properties prop = new Properties();
	try {
		
		String path = System.getProperty("user.dir")+"\\src\\test\\resources\\project.properties";
		FileInputStream fis = new FileInputStream(path);
		prop.load(fis);
	}catch (Exception e) {
		e.printStackTrace();
		}*/
	}
	
	public void selectDob(String locator, String data) {
		
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
			
			
			WebElement el = driver.findElement(By.xpath("//*[@id=\"dateOfBirth\"]/div[2]/div[2]/div/div/div[2]/div[1]/div[2]/div[1]/select"));
			el.sendKeys(month);
			
			el.sendKeys(Keys.TAB);
			
			Actions act = new Actions(driver);
			act.sendKeys(year).build().perform();
			
			for(int i =1; i<=6; i++) {
				for(int j = 1; j<=7; j++) {
					WebElement we = driver.findElement(By.xpath("//*[@id=\"dateOfBirth\"]/div[2]/div[2]/div/div/div[2]/div[2]/div["+i+"]/div["+j+"]"));
					String dayfromcalender = we.getText();
					int dayconvert = Integer.parseInt(dayfromcalender);
					System.out.println(dayconvert);
						if(dayconvert==dayget) {
							we.click();
						}
				}
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}

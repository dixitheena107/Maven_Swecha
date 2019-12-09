package tests;




import static org.junit.Assert.*;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;



import static org.junit.Assert.fail;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class bulletinTest1_4 {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  ArrayList<String> classList = new ArrayList<String>();
  
  public int majorCount;
  public String listOfMajors[];

  @Before
  public void setUp() throws Exception {
	  
	System.setProperty("webdriver.gecko.driver", "C:\\Users\\sk67691\\Desktop\\Eclipse Workspace Java\\Selenium 1.1\\gecko\\geckodriver.exe");
	System.setProperty("webdriver.firefox.bin", "C:\\Program Files\\Mozilla Firefox\\firefox.exe" );
    System.setProperty("webdriver.chrome.driver", "C:\\Users\\sk67691\\Desktop\\Eclipse Workspace Java\\Athena - Class Schedule 1.1\\exefiles\\chromedriver.exe");
	
    driver = new ChromeDriver();
    baseUrl = "https://bulletin.uga.edu/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }
  @Test
  public void testUntitledTestCase() throws Exception {
    driver.get("http://www.bulletin.uga.edu/");
    driver.findElement(By.name("courses")).click();
    driver.findElement(By.id("ddlAllPrefixes")).click();
    
    
    Select PrefixList = new Select(driver.findElement(By.id("ddlAllPrefixes")));
   	List<WebElement> l = PrefixList.getOptions();
   	int numOfPrefixes = l.size();
   	majorCount = numOfPrefixes-1;
   	
   	String list[] = new String[l.size()-1];
   	String Values[] = new String[l.size()-1];
	for(int n = 0; n < list.length; n++ ){
		list[n] = l.get(n+1).getText();
		Values[n] = l.get(n+1).getText().substring(0, 4);
		System.out.println(list[n]);
		System.out.println(Values[n]);
	}
	listOfMajors = list;
    
	
	 WebDriverWait wait = new WebDriverWait(driver, 100);

	
	int CourseCount[] = new int[l.size()-1];
	for(int n = 0; n < list.length; n++) {
		driver.navigate().refresh();
		PrefixList = new Select(driver.findElement(By.id("ddlAllPrefixes")));
    PrefixList.selectByVisibleText(list[n]);
 // Thread.sleep(5000);
   Select CourseList = new Select(driver.findElement(By.id("ddlAllCourses")));
 
	List<WebElement> c = CourseList.getOptions();
   	CourseCount[n] = c.size()-2;
    CourseList.selectByVisibleText("All Courses");
   
    System.out.println(Values[n] + ":" + CourseCount[n]);
	}
	
	
	
  }

  

  @After
  public void tearDown() throws Exception {
   // driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}

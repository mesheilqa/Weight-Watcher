package userlogin;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;

public class WeightWatchers 

{
	protected static WebDriver driver;
	   
	   public static void printingHoursOfOperation() 
	   {
			
			List<WebElement> currentHours = driver.findElements(By.xpath("//div[@class='hours-list-item-wrapper hours-list--currentday']"));
			for(WebElement hours: currentHours) {
				System.out.println("Today's hours : "+ hours.getText());
		}
			
	   }	
			
			
			 public static void main(String[] args) throws Exception
			 {
			 	 System.setProperty("webdriver.chrome.driver",
			 				"ADD YOUR LOCATION TO THE CHROME DRIVER FILE \\ALL LIB NEEDED\\chromedriver.exe");
			 	 DesiredCapabilities cap = DesiredCapabilities.chrome();
			 	   driver = new ChromeDriver(cap);
			 	   driver.manage().window().maximize();
			 	   driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				   driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
				

			 		//1
			 	 driver.navigate().to("https://www.weightwatchers.com/us/");
			 	
			 	 
			 	 //2 verifying loaded page title matches the expected string
			 	     String actualTitle = driver.getTitle();
					String expectedTitle = "WW (Weight Watchers): Weight Loss & Wellness Help";
					Assert.assertEquals(expectedTitle, actualTitle);
					System.out.println("Both titles matched!");	
					
					
					//3
					driver.findElement(By.className("find-a-meeting")).click();
					
					//4 verifying loaded page title matches the expected string
					String actualTitle2 = driver.getTitle();
					Assert.assertTrue(actualTitle2.contains("Find WW Studios & Meetings Near You | WW USA"));
					System.out.println("Title contains : Find WW Studios & Meetings Near You | WW USA");
					
					
					//5
					driver.findElement(By.id("meetingSearch")).sendKeys("10011");
					
					//6 search for meetings and printing the first search result name and distance
					driver.findElement(By.xpath("//*[@id='content']/div/div/ui-view/ui-view/div/div[2]/form/div[1]/button")).click();
					
					List<WebElement> titleOfFirstSearchResult = driver.findElements(By.xpath("/html[1]/body[1]/div[2]/div[1]/div[2]/div[1]/div[1]/ui-view[1]/ui-view[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/result-location[1]/div[1]/div[1]/a[1]/location-address[1]/div[1]/div[1]/div[1]/div[1]/span[1]"));
					
					List<WebElement> distanceOfFirstSearch = driver.findElements(By.xpath("//div[@class='location__distance'][contains(text(),'0.49 mi.')]"));
					for(WebElement first : titleOfFirstSearchResult) {
						System.out.println("Title of first search result : "+ first.getText());
					}
					for(WebElement distance : distanceOfFirstSearch) {
						System.out.println("Distance of first search result : " + distance.getText());
					}		
					
					
					//7 verifying displayed name matches the result
					driver.findElement(By.xpath("//*[@id='ml-1180510']/result-location/div/div[1]/a/location-address/div/div/div[1]/div[1]/span")).click();
					
					String displayedLocationName = driver.findElement(By.xpath("//*[@id='content']/div/div/ui-view/ui-view/div[1]/div[1]/div/div/div[2]/div[2]/div/div/location-address/div/div/div[1]/div/span")).getText();
					String titleOfFirstSearchResult2 = "WW Studio Flatiron";
					
					if(titleOfFirstSearchResult2.equals(displayedLocationName)) {
						System.out.println("Verification successful: Correct title displayed on webpage");
					}
					
					else {
						System.out.println("Verification unsuccessful : Incorrect title display ");
					}
					
					//8 Displaying the today's hours of operation
					printingHoursOfOperation();
					
					driver.close();
			 		
			 	 
	}
}

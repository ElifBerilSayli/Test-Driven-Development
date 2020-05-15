package project3;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
public class tdd {
	
		// Initiating your chromedriver
		WebDriver driver = null; 
		
		@BeforeMethod
		public void setup(){
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\Lenovo\\Desktop\\2020-2021Spring\\cs458\\chromedriver.exe");
			driver = new ChromeDriver();
			// setting the driver executable
			

			/* configuration */

			// Applied wait time
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			// maximize window
			driver.manage().window().maximize();

			/* navigate url */
			// open browser with desried URL
			driver.get("file:///C:/Users/Lenovo/Downloads/TDDP1_refactored.html");
			
		}
		
	
		@Test
		 public void trueLocation() throws InterruptedException
		{
			WebElement findme = driver.findElement(By.id("findme"));
			findme.click();
			Thread.sleep(3000);
			String actual = driver.findElement(By.xpath("//div[@class='gm-style-iw-d']//div")).getText();
			String expected = "Tandoðan, Merve Sk. No:8, 06930 Sincan/Ankara, Türkiye";
			Assert.assertEquals(actual,expected);

			
			 
		}
	
		
		@Test
		public void correctAlert() throws InterruptedException
		{
			WebElement lat=driver.findElement(By.id("lat")); 
			WebElement lng=driver.findElement(By.id("lng"));
			WebElement nearest = driver.findElement(By.id("nearest"));
			lat.clear();
			lng.clear();
			lat.sendKeys("95");
			lng.sendKeys("95");
			nearest.click();
			Thread.sleep(2000);
			try
			{
				String actualAlert=driver.switchTo().alert().getText();
			    String expectedAlert= "Latitude should be in the interval -90,90"; 
				Assert.assertEquals(actualAlert,expectedAlert);
			} catch(Exception e){
	            System.out.println("Popup no present");
	        }


		
		}
		
		
		@Test
		 public void trueNearestLocation() throws InterruptedException
		{
			WebElement lat=driver.findElement(By.id("lat")); 
			WebElement lng=driver.findElement(By.id("lng"));
			WebElement submit = driver.findElement(By.id("submit"));
			submit.click();
			Thread.sleep(2000);
			WebElement city = driver.findElement(By.id("city"));
			String actualCity = city.getAttribute("value");
		    String expectedCity= " Amerika Birleþik Devletleri, New York "; 
			Assert.assertEquals(actualCity,expectedCity);
			 
		}

		
		@Test
		public void latIsEmpty()
		{
			WebElement lat=driver.findElement(By.id("lat")); 
			WebElement lng=driver.findElement(By.id("lng"));
			WebElement submit = driver.findElement(By.id("submit"));
			lat.clear();
			lng.clear();
			lat.sendKeys(""); 
			lng.sendKeys("");
			submit.click();
	        driver.switchTo().alert();
			
		}
		
		
		@Test
		public void nonDigitCharacters()
		{
			WebElement lat=driver.findElement(By.id("lat")); 
			WebElement lng=driver.findElement(By.id("lng"));
			WebElement submit = driver.findElement(By.id("submit"));
			lat.clear();
			lng.clear();
			lat.sendKeys("*"); 
			lng.sendKeys(":");
			submit.click();
            driver.switchTo().alert();

			
		}
		

		
		@Test
		public void notValidCity()
		{
			WebElement lat=driver.findElement(By.id("lat")); 
			WebElement lng=driver.findElement(By.id("lng"));
			WebElement submit = driver.findElement(By.id("submit"));
			lat.clear();
			lng.clear();
			lat.sendKeys("40.714224"); 
			lng.sendKeys("-73.961452");
			submit.click();
			 try 
			 { 
				 driver.switchTo().alert().accept(); 
			        
			 }   
			 catch (NoAlertPresentException Ex) 
			 { 
			       
			 } 
			WebElement city = driver.findElement(By.id("city"));
			String actualCity=city.getText();
			boolean a = false;
		    if(actualCity.length() > 0)
		    {
		    	a = true;
		    }
			Assert.assertTrue(a);
          
			
		}
		
		@Test
		public void distanceFromCenter() throws InterruptedException
		{
			WebElement lat=driver.findElement(By.id("lat")); 
			WebElement lng=driver.findElement(By.id("lng"));
			WebElement submit = driver.findElement(By.id("submit"));
			WebElement distance = driver.findElement(By.id("distanceTo"));
			lat.clear();
			lng.clear();
			lat.sendKeys("40.714224"); 
			lng.sendKeys("-73.961452");
			submit.click();
			Thread.sleep(2000);
			distance.click();
			Thread.sleep(2000);
			 try 
			    { 
				 driver.switchTo().alert().accept(); 
			        
			    }   
			    catch (NoAlertPresentException Ex) 
			    { 
			       
			    }   
	
			
			WebElement citydistance = driver.findElement(By.id("citydistance"));
			String actual = citydistance.getAttribute("value");
		    System.out.println(actual);
			boolean a = false;
		    if(actual.length() >  0)
		    {
		    	a = true;
		    }
			Assert.assertTrue(a);
			
			
			
			
		}
		
		
		@AfterMethod
		public void tearDown(){
			driver.quit();
		}
	
	

}

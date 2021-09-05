package Miniproject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class portfolio_proj {

	//public static WebDriver driver;
	public static void main(String[] args) throws InterruptedException, IOException {

		System.setProperty("webdriver.chrome.driver", "E:\\chromedriver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://money.rediff.com/index.html");
		String readurl = driver.getCurrentUrl();
		System.out.println("Read Url" +readurl);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.manage().window().maximize();
		String title=driver.getTitle();
		System.out.println(title);
		
		driver.findElement(By.xpath("//a[contains(text(),'Sign In')]")).click(); //click on sign in 
		//reading text file 
		File file= new File("E:\\workspace\\Helloworld\\src\\testfile\\reduser_id_pass");
		BufferedReader br =new BufferedReader(new FileReader(file));
		String st;
		Properties prop = new Properties();
		prop.load(new FileInputStream(file));
		String userid = prop.getProperty("username");
		String passwrd = prop.getProperty("password");
		while((st = br.readLine()) != null)
			System.out.println(st);
		
		driver.findElement(By.id("useremail")).sendKeys(userid);
		Thread.sleep(2000);//type uname
		
	    driver.findElement(By.id("userpass")).sendKeys(passwrd); // type passw
				
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='loginsubmit']")).click();
		//driver.findElement(By.id("loginsubmit")).click(); // click on submit button
		//driver.findElement(By.xpath("//input[@id='create']")).click();
		//Thread.sleep(2000);
		
		
		
		// create button click code
		List<String> data = getPortfolioListFromExcel.getData();
		for(int i=0;i<data.size();) {
			driver.findElement(By.xpath("//a[@id='createPortfolio']")).click();
			//clear text box
			driver.findElement(By.xpath("//input[@id='create']")).clear();

			//Actions act = new Actions(driver);
			//Thread.sleep(3000);
			//driver.findElement(By.xpath("//a[@id='createPortfolio']")).click();
			WebElement txtbox = driver.findElement(By.xpath("//input[@id='create']"));
			txtbox.sendKeys(data.get(i));
			Thread.sleep(2000);
			driver.findElement(By.xpath("//input[@id='createPortfolioButton']")).click();
			try {
			if(driver.findElement(By.xpath("//div[contains(text(),'You already have')]")).isDisplayed() && driver.findElement(By.id("portfolioAddClose")).isEnabled()) {
				System.out.println("already present "+data.get(i));
				
				
			}
			else {
				System.out.println("created" +data.get(i));
			}
			}catch(NoSuchElementException e) {
				System.out.println("created " +data.get(i));
			}
			
			i++;
			
		}
		System.out.println("closing");
		driver.close();
		
		
		
		//act.moveToElement(txtbox).click().keyDown(Keys.SHIFT).sendKeys("Priyanka").build().perform();
		
		

		
	}

}

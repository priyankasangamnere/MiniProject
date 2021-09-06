package Miniproject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
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

public class portfolio_proj2 {
	
	//write logfile
	public static void writeCommentToTextFile(String modulname, String testresult,String comment) 
	{
		try {
			File file = new File("E:\\workspace\\Helloworld\\src\\testfile\\sh.txt");
			
			String text = modulname +"   "+testresult +"  "+comment +"\n";
			FileWriter fwriter = new FileWriter(file,true);
			BufferedWriter bfw = new BufferedWriter(fwriter);
			//bfw.newLine();
			bfw.write(text);
			bfw.close();
			fwriter.close();
			
		}
		catch(IOException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	//public static WebDriver driver;
	public static void main(String[] args) throws InterruptedException, IOException {

		System.setProperty("webdriver.chrome.driver", "E:\\chromedriver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://money.rediff.com/index.html");
		String readurl = driver.getCurrentUrl();  //read url
		System.out.println("Read Url" +readurl);  //print url on console
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.manage().window().maximize();
		String title=driver.getTitle();
		System.out.println(title);
		
		driver.findElement(By.xpath("//a[contains(text(),'Sign In')]")).click(); //click on sign in 
		//reading text file 
		File file= new File("E:\\workspace\\Helloworld\\src\\testfile\\reduser_id_pass");
		BufferedReader br =new BufferedReader(new FileReader(file));
		String st;
		Properties prop = new Properties(); //access properties
		prop.load(new FileInputStream(file)); 
		String userid = prop.getProperty("username");
		String passwrd = prop.getProperty("password");
		while((st = br.readLine()) != null)
			System.out.println(st); // print u_id and passw on console
		
		driver.findElement(By.id("useremail")).sendKeys(userid); //give username in textbox
		Thread.sleep(2000);
		
	    driver.findElement(By.id("userpass")).sendKeys(passwrd); // give password
				
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='loginsubmit']")).click();  //click  submit
		
		//read data from excel file
		List<String> data = getPortfolioListFromExcel.getData();
		for(int i=0;i<data.size();) {
			driver.findElement(By.xpath("//a[@id='createPortfolio']")).click(); // create button click 
			
			driver.findElement(By.xpath("//input[@id='create']")).clear();  //clear text box

			WebElement txtbox = driver.findElement(By.xpath("//input[@id='create']"));
			txtbox.sendKeys(data.get(i));
			Thread.sleep(2000);
			driver.findElement(By.xpath("//input[@id='createPortfolioButton']")).click(); //click on create button after enter portfolio name in textbox
			try {
			if(driver.findElement(By.xpath("//div[contains(text(),'You already have')]")).isDisplayed() && driver.findElement(By.id("portfolioAddClose")).isEnabled()) {
				System.out.println("already present "+data.get(i));
				driver.findElement(By.id("portfolioAddClose")).click();
				
				writeCommentToTextFile("Create Portfolio - ","PF already exists - ",data.get(i)); //get and print msg in txt file
			}
			else {
				System.out.println("created" +data.get(i));
				writeCommentToTextFile("Create Portfolio - ","Created - ",data.get(i)); //call function
				
			}
			}catch(NoSuchElementException e) {
				System.out.println("created " +data.get(i));
			}
			
			i++;
			
		}
		System.out.println("closing");
		driver.close();
		
		
		
			

		
	}

}

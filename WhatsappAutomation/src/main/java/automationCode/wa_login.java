package automationCode;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class wa_login {

	
	//ReadCsv readCsv = new ReadCsv();
	//PropertyData pData = readCsv.getPropertyData();
	public WebDriver wa_qrCode(String Url) {
		WebDriver driver = null;
		
		try {
			WebDriverManager.chromedriver().version("97.0").setup();
			ChromeOptions options = new ChromeOptions();
			DesiredCapabilities desiredCapabilities = DesiredCapabilities.chrome();
			 //options.addArguments("--headless");
			options.addArguments("user-data-dir=C:\\Users\\DELL\\AppData\\Local\\Google\\Chrome\\User Data\\Profile Automate");
           // desiredCapabilities.setCapability(ChromeOptions.CAPABILITY, options);
			//options.addArguments("log-level=Debug");
			driver = new ChromeDriver(options);
			driver.get(Url);
			driver.manage().window().maximize();
		
		} catch (Exception e) {
			System.out.println("Error:" + e.getMessage());
		}
		return driver;
	}
	

	public WebDriver waitTillLoggedin(WebDriver driver) {
		
		try {
			System.out.println("waiting for QR scan");
			WebDriverWait wait = new WebDriverWait(driver,10000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"side\"]/div[1]/div/label/div/div[2]")));
		}catch(Exception e) {
			System.out.println("Error No whatsapp client has logged in");
			//e.printStackTrace();
		}
		return driver;
	}


	public WebDriver openContact(WebDriver driver, String contact) {
		WebElement searchBox= driver.findElement(By.xpath("//*[@id=\"side\"]/div[1]/div/label/div/div[2]"));
		searchBox.sendKeys(contact+Keys.ENTER);
		String noContact_xpath="//*[@id=\"pane-side\"]/div[1]/div/span";
	
		if(driver.findElements(By.xpath(noContact_xpath)).size() > 0 && driver.findElements(By.xpath(noContact_xpath)).get(0).getText().equalsIgnoreCase("No chats, contacts or messages found")) {
			driver.navigate().to("https://web.whatsapp.com/send?phone="+contact+"&text&app_absent=0");
			
		}
		return driver;
	}


	public WebDriver sendMessage(WebDriver driver, String contactNum, String msg) {
		try {
			driver = openContact(driver, contactNum);
			WebDriverWait wait = new WebDriverWait(driver,30);
			String msgBox_xpath="//*[@id=\"main\"]/footer/div[1]/div/span[2]/div/div[2]/div[1]/div/div[2]";
			if(driver.findElements(By.xpath(msgBox_xpath)).size() <= 0) {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(msgBox_xpath)));
			}
			WebElement msgBox= driver.findElement(By.xpath(msgBox_xpath));
			msgBox.sendKeys(msg);
			WebElement sendBtn = driver.findElement(By.xpath("//*[@id=\"main\"]/footer/div[1]/div/span[2]/div/div[2]/div[2]/button"));
			sendBtn.click();
			System.out.println(contactNum+" > "+msg+" ----SENT");
		
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return driver;
	}

}

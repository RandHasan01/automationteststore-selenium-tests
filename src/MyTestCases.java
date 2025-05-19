import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;

public class MyTestCases {

	WebDriver driver = new ChromeDriver();
	String URL = "https://automationteststore.com/";
	String[] firstNames = { "Ali", "Sara", "Omar", "Lina", "Hassan" };
	String[] lastNames = { "Khaled", "Ahmad", "Fares", "Nour", "Salim" };
	Random rand = new Random();// This will create a Random object to generate random numbers.
	String LoginName = "";
	String Password = "";

	@BeforeTest
	public void MySetup() {
		driver.get(URL);
		driver.manage().window().maximize();

	}

	@Test(priority = 1) // , enabled = false
	public void RegisterTest() throws InterruptedException {
		driver.findElement(By.linkText("Login or register")).click();
//		driver.findElement(By.partialLinkText("Login or")).click();// find tag of type <a> have partial text (Login or) 
		// element not interactable -> can't react with the element but see the element
		// Unable to alocate element -> can't see the element in page
		// top left selenium work to find element in page
		// cssSelector allow to use more than one class
		// className don't allow to use more than one class
		// XPath -> //tagName[@attribuetName ='value']
//		driver.findElement(By.cssSelector(".btn.btn-orange.pull-right")).click();
		driver.findElement(By.xpath("//button[@title='Continue']")).click();// more specific
		int randomIndex = rand.nextInt(5);// what the number will not take it and will stop at it(0-4)
		int randomNumber = rand.nextInt(53435);
		WebElement FirstNameInputField = driver.findElement(By.id("AccountFrm_firstname"));
		WebElement LastNameInputField = driver.findElement(By.id("AccountFrm_lastname"));
		WebElement EmailInputField = driver.findElement(By.id("AccountFrm_email"));
		WebElement Address1InputField = driver.findElement(By.id("AccountFrm_address_1"));
		WebElement CityInputField = driver.findElement(By.id("AccountFrm_city"));
		WebElement ZIPCodeInputField = driver.findElement(By.id("AccountFrm_postcode"));
		WebElement CountryInputField = driver.findElement(By.id("AccountFrm_country_id"));
		WebElement LoginNameInputField = driver.findElement(By.id("AccountFrm_loginname"));
		WebElement PasswordInputField = driver.findElement(By.id("AccountFrm_password"));
		WebElement PasswordConfirmInputField = driver.findElement(By.id("AccountFrm_confirm"));
		WebElement Subscribe = driver.findElement(By.id("AccountFrm_newsletter0"));
		WebElement PrivacyPolicyCheck = driver.findElement(By.id("AccountFrm_agree"));

		String RandomFirstName = firstNames[randomIndex];
		String RandomLastName = lastNames[randomIndex];
		String userName = RandomFirstName + RandomLastName + randomNumber;

		FirstNameInputField.sendKeys(RandomFirstName);
		LastNameInputField.sendKeys(RandomLastName);
		EmailInputField.sendKeys(userName + "@gmail.com");
		Address1InputField.sendKeys("Nasr");
		CityInputField.sendKeys("Amman");

		Select mySelect = new Select(CountryInputField);
//		mySelect.selectByValue("108");
		mySelect.selectByVisibleText("Jordan");

		Thread.sleep(3000);
		WebElement RegionInputField = driver.findElement(By.id("AccountFrm_zone_id"));

		Select mySelect2 = new Select(RegionInputField);
		mySelect2.selectByIndex(1);
		ZIPCodeInputField.sendKeys("80008");
		LoginNameInputField.sendKeys(userName);
		LoginName = userName;

		PasswordInputField.sendKeys("R@and12");
		Password = "R@and12";
		PasswordConfirmInputField.sendKeys("R@and12");
		Subscribe.click();
		PrivacyPolicyCheck.click();

		driver.findElement(By.xpath("//button[@title='Continue']")).click();

	}

	@Test(priority = 2) // , enabled = false
	public void LogoutTest() {

		driver.findElement(By.partialLinkText("Logoff")).click();
		driver.findElement(By.partialLinkText("Continue")).click();
		driver.findElement(By.linkText("Login or register")).click();

	}

	@Test(priority = 3) // , enabled = false
	public void LoginTest() {

		driver.findElement(By.id("loginFrm_loginname")).sendKeys(LoginName);
		driver.findElement(By.id("loginFrm_password")).sendKeys(Password);
		driver.findElement(By.xpath("//button[@title='Login']")).click();
	}

	@Test(priority = 4) // , invocationCount = 10
	public void AddOneRandomItem() throws InterruptedException {
//		WebDriver driver = new ChromeDriver();

		String MenURL = "https://automationteststore.com/index.php?rt=product/category&path=58";
		driver.get(MenURL);
		List<WebElement> Category = driver.findElements(By.cssSelector(".col-md-2.col-sm-2.col-xs-6.align_center"));
		int randomCategory = rand.nextInt(Category.size());
		Category.get(randomCategory).click();

		List<WebElement> addItemButton = driver.findElements(By.cssSelector(".pricetag.jumbotron .productcart"));
		int randomItem = rand.nextInt(addItemButton.size());
		int randomQuantity = rand.nextInt(9) + 2;

		for (int i = 0; i < randomQuantity; i++) {
			addItemButton.get(randomItem).click();
		}
		Thread.sleep(2000);
		driver.get("https://automationteststore.com/index.php?rt=checkout/cart");
		driver.findElement(By.id("cart_checkout2")).click();
//		driver.get("https://automationteststore.com/index.php?rt=checkout/shipping");
		driver.findElement(By.cssSelector(".btn.btn-orange.pull-right.lock-on-click")).click();
		Thread.sleep(2000);
		System.out.println(driver.findElement(By.className("maintext")).getText());

		
		
		
		
//		addItem.get(randomItem2).click();

		// 1-add one random item inside category

//		List<WebElement> addItem = driver.findElements(By.cssSelector(".pricetag.jumbotron .productcart"));
//		System.out.println("number of item " + addItem.size());
//		Random rand3 = new Random();
//		int randomItem2 = rand3.nextInt(addItem.size());
//		System.out.println("which random number chose " + randomItem2);
//		addItem.get(randomItem2).click();

		// 2-add all item inside one category

//		List<WebElement> AddAllItemInOneRandomCategory = driver.findElements(By.cssSelector(".pricetag.jumbotron .productcart"));
//		for (int i = 0; i < AddAllItemInOneRandomCategory.size(); i++) {
//
//			AddAllItemInOneRandomCategory.get(i).click();//there's a problem in button when i add some item
//
//		}

		// 3-add all items in all category
//		for (int i = 0; i < items.size(); i++) {
//			items = driver.findElements(By.cssSelector(".col-md-2.col-sm-2.col-xs-6.align_center"));
//			if (i == 1) {// one button in items don't work as expected so i excluded it to complete the
//							// process(there is a bug)
//				continue;
//
//			}
//			items.get(i).click();
//			List<WebElement> AddAllItemInOneRandomCategory = driver
//					.findElements(By.cssSelector(".pricetag.jumbotron .productcart"));
//			for (int j = 0; j < AddAllItemInOneRandomCategory.size(); j++) {
//				AddAllItemInOneRandomCategory = driver.findElements(By.cssSelector(".pricetag.jumbotron .productcart"));
//				AddAllItemInOneRandomCategory.get(j).click();// there's a problem in one button when i add item
//
//			}
//			driver.navigate().back();
//
//		}

//		Thread.sleep(2000);
//		driver.close();

	}

	@AfterTest
	public void CloseTheApp() throws InterruptedException {
		Thread.sleep(2000);
		driver.close();

	}

}

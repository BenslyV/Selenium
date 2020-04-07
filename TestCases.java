package BensQA.Test;

import java.io.IOException;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import BensQA.Base;
import BensQA.Pages.HomePage;
import BensQA.Pages.ShoppingPage;
import BensQA.Pages.SignInPage;

public class TestCases extends Base {
	// ChromeDriver driver;
	HomePage hp;
	ShoppingPage sp;
	SignInPage si;

	@Parameters
	@BeforeTest
	void setup() throws IOException {
		initDriver();
		driver.get("https://www.rediff.com/");

	}

	@Parameters({"searchkey"})
	@Test(priority = 3,enabled=false)
	void productSearch(@Optional("Shoes123") String key) {
		hp = new HomePage(driver);
		sp = new ShoppingPage(driver);
		hp.shoppingIcon().click();
		sp.searchBox().sendKeys(key);
		sp.searchbutton().click();
	}

	@Test(enabled = true, priority = 1)
	void gotoMoneyPage() {
		hp = new HomePage(driver);
		hp.moneyIcon().click();
		driver.navigate().back();
	}

	@Test(priority = 2)
	void videoPage() {
		hp = new HomePage(driver);
		hp.videoPage().click();
		driver.navigate().back();
	}

	@Test(priority = 0)
	void newstab() {

		hp = new HomePage(driver);
		hp.newsPage().click();

	}

	@AfterTest
	void teardown() {
		driver.close();

	}
	@Test(dataProvider="Login",dataProviderClass=BensQA.DataProvider.DataP.class,priority = 4)
	void signInCheck(String user,String pass) {
		hp=new HomePage(driver);
		si=new SignInPage(driver);
		hp.signIn().click();
		si.usernane().sendKeys(user);
		si.password().sendKeys(pass);
		si.signInButton().click();	
		Assert.assertEquals(si.errorMessage().getText(), "Temporary error occured[#5001], please try again.");
		driver.navigate().refresh();
	}
}

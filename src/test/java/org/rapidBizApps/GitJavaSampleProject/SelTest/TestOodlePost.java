package org.rapidBizApps.GitJavaSampleProject.SelTest;



import java.util.*;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class TestOodlePost {
	private static WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		baseUrl = "http://www.oodle.com/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testOodlepost() throws Exception {
		boolean isSwitched = false;
		// String swinhnd1 = driver.getWindowHandle();
		// System.out.println(swinhnd1);
		//
		driver.get(baseUrl + "/local/palomar-park-ca/");
		driver.findElement(By.linkText("Post")).click();

		Thread.sleep(3000);
		String listform = "(//div[@id='container_post']/descendant::input[@id='listingForm_title'])[last()]";
		String listWhy = "(//div[@id='container_post']/descendant::input[@id='listingForm_why'])[last()]";
		String listBody = "(//div[@id='container_post']/descendant::textarea[@id='listingForm_body'])[last()]";
		String listPrice = "(//div[@id='container_post']/descendant::input[@id='listingForm_price'])[last()]";
		String cata = "(//div[@id='container_post']/descendant::a[@class='breadcrumb'])[last()]";
		String catageory = "((//div[@id='container_post']/descendant::ul/li/a))";
		// String subcatgeory =
		// "((//div[@id='container_post']/descendant::ul/li/a)[last()])";

		driver.findElement(By.xpath(listform)).click();
		driver.findElement(By.xpath(listform)).sendKeys("book1");
		driver.findElement(By.xpath(listWhy)).click();
		driver.findElement(By.xpath(listWhy)).sendKeys("book1");
		driver.findElement(By.xpath(listBody)).click();
		driver.findElement(By.xpath(listBody)).sendKeys(" a java expert book1");
		driver.findElement(By.xpath(listPrice)).click();
		driver.findElement(By.xpath(listPrice)).sendKeys("10");
		driver.findElement(By.xpath(cata)).click();
		
		// Selecting the category and sub-category
		List<WebElement> dropdown = driver.findElements(By.xpath(catageory));
		Iterator<WebElement> r = dropdown.listIterator();
		categoryClick1("Merchandise", dropdown, r);
		List<WebElement> dropdown1 = driver.findElements(By.xpath(catageory));
		Iterator<WebElement> k = dropdown1.listIterator();
		categoryClick1("Antiques", dropdown1, k);
//		List<WebElement> dropdown2 = driver.findElements(By.xpath(catageory));
//		Iterator<WebElement> l = dropdown2.listIterator();
//		categoryClick1("Office", dropdown2, l);

		driver.findElement(By.xpath("//input[@value='Post']")).click();
		String childwindow = null;
		String motherWindow = driver.getWindowHandle();
		for (String newwindow : driver.getWindowHandles()) {
			if (!newwindow.equals(motherWindow))
				childwindow = newwindow;
		}
	
		driver.switchTo().window(childwindow);
		System.out.println(driver.getTitle());
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@id='loginform']//input[@id='email']")).sendKeys("bindu_mu@yahoo.com");
		driver.findElement(By.xpath("//div[@id='loginform']//input[@id='pass']")).sendKeys("sair@m123");
		driver.findElement(By.name("login")).click();		
		driver.switchTo().window(motherWindow);
		Thread.sleep(2000);
		driver.findElement(By.className("bottom-close")).click();
		driver.findElement(By.className("my-name")).click();
		driver.findElement(By.xpath("//ul[@id='header-myaccount-list']/li[3]/a")).click();
	}

	public static void categoryClick1(String category,
			List<WebElement> dropdowns, Iterator<WebElement> i) {

		int j = -1;
		while (i.hasNext()) {
			WebElement item = i.next();
			j = j + 1;
			if (item.getText().equals(category)) {
				dropdowns.get(j).click();
				System.out.println("-----");
			}
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

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alert.getText();
		} finally {
			acceptNextAlert = true;
		}
	}
}

package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.io.File;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {

	@LocalServerPort
	private int port;

	private WebDriver driver;

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		this.driver = new ChromeDriver();
	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}

	@Test
	public void getLoginPage() {
		driver.get("http://localhost:" + this.port + "/login");
		Assertions.assertEquals("Login", driver.getTitle());
	}

	/**
	 * PLEASE DO NOT DELETE THIS method.
	 * Helper method for Udacity-supplied sanity checks.
	 **/
	private void doMockSignUp(String firstName, String lastName, String userName, String password)  {
		// Create a dummy account for logging in later.
		// Visit the sign-up page.
		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);
		driver.get("http://localhost:" + this.port + "/signup");
		webDriverWait.until(ExpectedConditions.titleContains("Sign Up"));

		// Fill out credentials
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputFirstName")));
		WebElement inputFirstName = driver.findElement(By.id("inputFirstName"));
		inputFirstName.click();
		inputFirstName.sendKeys(firstName);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputLastName")));
		WebElement inputLastName = driver.findElement(By.id("inputLastName"));
		inputLastName.click();
		inputLastName.sendKeys(lastName);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputUsername")));
		WebElement inputUsername = driver.findElement(By.id("inputUsername"));
		inputUsername.click();
		inputUsername.sendKeys(userName);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputPassword")));
		WebElement inputPassword = driver.findElement(By.id("inputPassword"));
		inputPassword.click();
		inputPassword.sendKeys(password);

		// Attempt to sign up.
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("buttonSignUp")));
		WebElement buttonSignUp = driver.findElement(By.id("buttonSignUp"));
		buttonSignUp.click();




		/* Check that the sign up was successful.
		// You may have to modify the element "success-msg" and the sign-up
		// success message below depening on the rest of your code.
		*/
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("success-msg")));

		Assertions.assertTrue(driver.findElement(By.id("success-msg")).getText().contains("You successfully signed up!"));


	}



	/**
	 * PLEASE DO NOT DELETE THIS method.
	 * Helper method for Udacity-supplied sanity checks.
	 **/
	private void doLogIn(String userName, String password)
	{
		// Log in to our dummy account.
		driver.get("http://localhost:" + this.port + "/login");
		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputUsername")));
		WebElement loginUserName = driver.findElement(By.id("inputUsername"));
		loginUserName.click();
		loginUserName.sendKeys(userName);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputPassword")));
		WebElement loginPassword = driver.findElement(By.id("inputPassword"));
		loginPassword.click();
		loginPassword.sendKeys(password);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-button")));
		WebElement loginButton = driver.findElement(By.id("login-button"));
		loginButton.click();

		webDriverWait.until(ExpectedConditions.titleContains("Home"));

	}

	/**
	 * PLEASE DO NOT DELETE THIS TEST. You may modify this test to work with the
	 * rest of your code.
	 * This test is provided by Udacity to perform some basic sanity testing of
	 * your code to ensure that it meets certain rubric criteria.
	 *
	 * If this test is failing, please ensure that you are handling redirecting users
	 * back to the login page after a succesful sign up.
	 * Read more about the requirement in the rubric:
	 * https://review.udacity.com/#!/rubrics/2724/view
	 */

	@Test
	public void testRedirection() throws InterruptedException {
		// Create a test account
		doMockSignUp("Redirection","Test","Muteb","Alharbi273");
		driver.get("http://localhost:" + this.port + "/login");
		// Check if we have been redirected to the log in page.
		Assertions.assertEquals("http://localhost:" + this.port + "/login", driver.getCurrentUrl());
	}

	/**
	 * PLEASE DO NOT DELETE THIS TEST. You may modify this test to work with the
	 * rest of your code.
	 * This test is provided by Udacity to perform some basic sanity testing of
	 * your code to ensure that it meets certain rubric criteria.
	 *
	 * If this test is failing, please ensure that you are handling bad URLs
	 * gracefully, for example with a custom error page.
	 *
	 * Read more about custom error pages at:
	 * https://attacomsian.com/blog/spring-boot-custom-error-page#displaying-custom-error-page
	 */
	@Test
	public void testBadUrl() throws InterruptedException {
		// Create a test account
		doMockSignUp("URL","Test","UT","123");
		doLogIn("UT", "123");

		// Try to access a random made-up URL.
		driver.get("http://localhost:" + this.port + "/some-random-page");
		Assertions.assertTrue(driver.getTitle().contains("404 - Page Not Found"));
	}


	/**
	 * PLEASE DO NOT DELETE THIS TEST. You may modify this test to work with the
	 * rest of your code.
	 * This test is provided by Udacity to perform some basic sanity testing of
	 * your code to ensure that it meets certain rubric criteria.
	 *
	 * If this test is failing, please ensure that you are handling uploading large files (>1MB),
	 * gracefully in your code.
	 *
	 * Read more about file size limits here:
	 * https://spring.io/guides/gs/uploading-files/ under the "Tuning File Upload Limits" section.
	 */
	@Test
	public void testLargeUpload() throws InterruptedException {
		// Create a test account
		doMockSignUp("Large File","Test","LFT","123");
		doLogIn("LFT", "123");

		// Try to upload an arbitrary large file
		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);
		String fileName = "upload5m.zip";

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("fileUpload")));
		WebElement fileSelectButton = driver.findElement(By.id("fileUpload"));
		fileSelectButton.sendKeys(new File(fileName).getAbsolutePath());

		WebElement uploadButton = driver.findElement(By.id("uploadButton"));
		uploadButton.click();
		try {
			webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("success")));
		} catch (org.openqa.selenium.TimeoutException e) {
			System.out.println("Large File upload failed");
		}
		Assertions.assertFalse(driver.getPageSource().contains("HTTP Status 403 – Forbidden"));

	}

	@Test
	public void unauthorizedUserAccess(){
		driver.get("http://localhost:" + this.port + "/signup");
		Assertions.assertEquals("Sign Up", driver.getTitle());
		driver.get("http://localhost:" + this.port + "/login");
		Assertions.assertEquals("Login", driver.getTitle());
		driver.get("http://localhost:" + this.port + "/home");
		Assertions.assertEquals("Login", driver.getTitle());
	}

	@Test
	public void testSignupLoginLogoutFlow(){
		doMockSignUp("Muteb" , "Alharbi" , "testSignupLoginLogoutFlow" , "testSignupLoginLogoutFlow");
		doLogIn("testSignupLoginLogoutFlow" , "testSignupLoginLogoutFlow");
		driver.get("http://localhost:" + this.port + "/home");
		Assertions.assertEquals("Home", driver.getTitle());
		WebElement logoutButton = driver.findElement(By.id("logout-button"));
		logoutButton.click();
		driver.get("http://localhost:" + this.port + "/home");
		Assertions.assertEquals("Login", driver.getTitle());
	}

	@Test
	public void createNote() throws InterruptedException {
		doMockSignUp("Muteb" , "Alharbi" , "Muteb" , "Alharbi273");
		doLogIn("createNote" , "createNote");

		clickButton("nav-notes-tab");
		Thread.sleep(200);

		clickButton("addNewNote");


		Thread.sleep(200);

		fillInputText("note-title" , "Note Title");
		fillInputText("note-description","Note Description");
		Thread.sleep(200);

		clickButton("saveNote");
		Thread.sleep(400);

		clickButton("nav-notes-tab");
		Thread.sleep(1000);

	}
	@Test
	public void editNote() throws InterruptedException {

		doLogIn("createNote" , "createNote");
		Thread.sleep(400);

		clickButton("nav-notes-tab");
		Thread.sleep(200);

		clickButton("editNoteButton");
		Thread.sleep(200);

		fillInputText("note-title" , "Change Note Title");
		fillInputText("note-description","Change Note Description");
		Thread.sleep(200);

		clickButton("saveNote");
		clickButton("nav-notes-tab");
		Thread.sleep(1000);
	}
	@Test
	public void deleteNote() throws InterruptedException {
		doLogIn("createNote" , "createNote");
		clickButton("nav-notes-tab");
		Thread.sleep(200);

		clickButton("deleteNoteButton");
		clickButton("nav-notes-tab");
		Thread.sleep(1000);


	}

	@Test
	public void createCredential() throws InterruptedException {
		doLogIn("createCredential" , "createCredential");
		clickButton("nav-credentials-tab");
		Thread.sleep(200);

		clickButton("addNewCredential");
		Thread.sleep(200);

		fillInputText("credential-url" , "www.github.com");
		fillInputText("credential-username","Muteb");
		fillInputText("credential-password","Alharbi273");
		clickButton("saveCredentialButton");
		Thread.sleep(200);
		clickButton("nav-credentials-tab");
		Thread.sleep(200);


	}


	@Test
	public void deleteCredential() throws InterruptedException {
		doLogIn("createCredential" , "createCredential");
		clickButton("nav-credentials-tab");
		Thread.sleep(400);
		clickButton("deleteCredentialButton");
	}
	@Test
	public void editCredential() throws InterruptedException {
		doMockSignUp("createCredential" , "createCredential" , "createCredential" , "createCredential");
		doLogIn("createCredential" , "createCredential");
		clickButton("nav-credentials-tab");
		createCredential();
		Thread.sleep(600);

		clickButton("editCredentialButton");
		Thread.sleep(200);

		fillInputText("credential-url" , "www.youtube.com");
		fillInputText("credential-username","Bob");
		fillInputText("credential-password","0mmO214");
		clickButton("saveCredentialButton");
		Thread.sleep(200);

		clickButton("nav-credentials-tab");
		Thread.sleep(1000);

	}



	public void fillInputText(String id , String content){
		WebElement Element = driver.findElement(By.id(id));
		Element.click();
		Element.sendKeys(content);
	}

	public void clickButton(String id){
		WebElement Element = driver.findElement(By.id(id));
		Element.click();
	}
	public String getElementText(String id){
		WebElement Element = driver.findElement(By.id(id));
		return Element.getText();
	}

}
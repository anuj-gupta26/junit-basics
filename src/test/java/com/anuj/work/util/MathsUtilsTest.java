package com.anuj.work.util;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestReporter;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.condition.OS;

/**
 * @author Anuj Gupta
 * 
 * MathsUtilsTest class to test MathsUtils operations 
*/
/**
 * Note: By Default JUnit will create new Instance of the MathsUtilsTest for every test case, 
 * we can overwrite this behavior using below annotation
 * @TestInstance(TestInstance.Lifecycle.PER_CLASS)   this is create only once instance of the class, 
 * and use the same to call every test case, if we use PER_CLASS then we dont need to declare 
 * @BeforAll and @AfterAll methods as static
 * 
 *  @TestInstance(TestInstance.Lifecycle.PER_METHOD) : is for default behavior
 *  
*/
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MathsUtilsTest {
	
	MathsUtils mathsUtils;

	/**
	 * Method will be executed before initialization of the MathsUtilsTest class, hence the method annotated with @BeforeAll
	 * has to be static , else we will get runtime JUnitException 
	 * 
	 * below method is used , if we want to perform any activity before execution of any test, It will be called only once in test life cycle
	*/
	@BeforeAll
	 void init() {
		System.out.println("Before all called...");
	}

	/**
	 * Method will be called before the execution of every test,
	 * we can do activities in the below method we want to do before execution of all the steps.
	 * 
	 * Here we are creating MathsUtils object, which we require to execute all the tests
	*/
	@BeforeEach
	void beforeMethodExecution(TestInfo testInfo, TestReporter reporter) {
		
		System.out.println("Before Each called ...");
		mathsUtils = new MathsUtils();
		/**
		 * TestInfo and TestReporter are Junits Interfaces, Junit will provide these through dependency injection 
		 * and we can use them as per our requirement 
		 * 		
		 * */
		reporter.publishEntry("Running "+testInfo.getDisplayName()+ " with tags "+testInfo.getTags()+" on time : "+ LocalDateTime.now());
	}
	
	/**
	 * Method will be executed after execution of every test
	*/
	@AfterEach
	void afterEachExecution(){
		System.out.println("Calling cleanup ...");
	}

	/**
	 * Method will be called once all the test are being performed, and will be executed after destroying the MathsUtilsTest instance,
	 * Hence it needs to be static
	 * 
	 * Method will be called only once in test life cycle
	*/
	@AfterAll
	 void destroy() {
		System.out.println("Destroying context ...");
	}
	
	@Test
	void adddTest() {
		int expectedValue = 19;
		assertEquals(expectedValue, mathsUtils.add(10, 9),"Should perform addition of two numbers");
	}
	
	
	
	/**
	 * By default the method names will be shown in the dashboard, 
	 * if you want to overwrite method name you can use @DisplayName annotation
	*/
	@Test
	@DisplayName("Multiplication method test")
	void multiplyTest() {
		int expected = 45;
		/**
		 * Note here protected method is accessible to the Test class,
		 *  because we are maintaining  the same package structure as that of main class  
		*/
		assertEquals(expected, mathsUtils.multiply(9, 5),"Should perform multiplication of two numbers");
	}
	
	@Test
	void divideTest() {
		int expected = 5;
		assertEquals(expected, mathsUtils.divide(45, 9),"Should perform division of two numbers");
	}
	
	@Test
	void divideTestException() {
		assertThrows(ArithmeticException.class,() -> mathsUtils.divide(45, 0), "Should throw ArithmeticException for division by zero");
	}

	/**
	 * @Disabled will stop JUnit, from running this test, becomes useful in Test driven developments
	*/
	@Test
	@Disabled
	@DisplayName("WIP: Test driven development in progress")
	void disabledTest() {
		fail("work in progress, should not execute this test");
	}

	/**
	 * Test will only be enabled on JRE verion 11
	*/
	@Test
	@EnabledOnJre(JRE.JAVA_11)
	void conditionalTestJre() {
//		fail("Should only run on jre 11");
	}

	/**
	 * test will only be executed on linux Operating sysytem
	*/
	@Test
	@EnabledOnOs(OS.LINUX)
	void conditionalTestOs() {
		fail("Should only run on linux operating server");
	}
	
	@Test
	@EnabledIfEnvironmentVariable(named = "DEV_TEST", matches = "true")
	void conditionalEnvironmentVaribale() {
		fail("should only run if environment variable is set");
	}
	
	@Nested
	@DisplayName("Add test for negative nad positive numbers")
	class AddTest{
	@Test
	@DisplayName("Test for adding positive number")
	void adddPositiveTest() {
		int expectedValue = 19;
		assertEquals(expectedValue, mathsUtils.add(10, 9),"Should perform addition of two numbers");
	}
	
	@Test
	@DisplayName("Test for adding negative number")
	void adddNegativeTest() {
		int expectedValue = 10;
		assertEquals(expectedValue, mathsUtils.add(15, -5),"Should perform addition of two numbers");
	}
	}
	
	@Test
	@DisplayName("Multiplication method test for more than one case")
	void multiplyTestBulk() {
		assertAll(
				() -> assertEquals(45, mathsUtils.multiply(9, 5),"Should perform multiplication of two numbers"),
				() -> assertEquals(0, mathsUtils.multiply(9, 0),"Should perform multiplication of two numbers"),
				() -> assertEquals(90, mathsUtils.multiply(9, 10),"Should perform multiplication of two numbers")
				);
	}

	/**
	 * @RepeatedTest(3) is used to repeat the test, provided number of times
	 * 
	 * @Tag is used for segregation of Test cases, suppose you want to run only unit tests or you want to run only integration test,
	 * in that case you can add tag and by changing run configuration you can run any particular type of test  
	*/
	@Tag("Integeration")
	@RepeatedTest(3)
	void divideTestForMessageUsingLambda(RepetitionInfo repetitionInfo) {
		if(repetitionInfo.getCurrentRepetition() == 2) {
			assertEquals(9, mathsUtils.divide(90, 10),() -> "Second repetion");	
		}
		assertEquals(5, mathsUtils.divide(45, 9),() -> "Should perform division of two numbers");
	}
}

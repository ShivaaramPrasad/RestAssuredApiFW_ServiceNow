package com.techurate.api.utilites;

import static com.techurate.api.config.ConfigManage.getConfig;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.testng.ITestResult;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.restassured.http.Header;

public class ExtentReportManager  {


	private static ExtentReports extent;
	public static ExtentTest  parent;
	private static final ThreadLocal<String> testName = new ThreadLocal<String>();
	/**
	 * Method to create Extent Report
	 */
	private String fileName = "result.html";
	private String pattern = "dd-MMM-yyyy HH-mm-ss";

	public String testcaseName, testDescription, authors, category;
	public static String folderName = "";

	@BeforeSuite(alwaysRun = true)
	public synchronized void startReport() {
		System.out.println("Executing testcases by TestNG driven approach ");
		String date = new SimpleDateFormat(pattern).format(new Date());
		folderName = "reports/" + date;

		File folder = new File("./" + folderName);
		if (!folder.exists()) {
			folder.mkdir();
		}
		ExtentSparkReporter htmlReporter = new ExtentSparkReporter("./" + folderName + "/" + fileName);
		htmlReporter.config().setReportName(getConfig().reportName());
		htmlReporter.config().setDocumentTitle(getConfig().reportTitle());
		htmlReporter.config().setTheme(Theme.STANDARD);
		htmlReporter.config().setEncoding("utf-8");
		//htmlReporter.setAppendExisting(true);
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
	}

	/**
	 * Create node for each Test
	 * @param testCaseName - Test case name which will be same as node name
	 * @param testDescription- Test case description
	 * @param category- Test case category - Positive or Negative
	 * @author authors- who created test test case
	 */
	@BeforeClass(alwaysRun = true)
	public synchronized void startTestCase() {
		parent = extent.createTest(testcaseName, testDescription);
		parent.assignCategory(category);
		parent.assignAuthor(authors);
		testName.set(testcaseName);
	}


	/**
	 * Method to log the status
	 * @param desc - description of the report step
	 * @param status - log status of each step
	 */
	// To report steps 


	public static void reportLog(String log, String status) {

		if(status.equalsIgnoreCase("PASS")) {
			parent.pass(MarkupHelper.createLabel(log, ExtentColor.WHITE));		
		}else if(status.equalsIgnoreCase("FAIL")) {
			parent.fail(MarkupHelper.createLabel(log, ExtentColor.RED));
			throw new RuntimeException("See the reporter for details.");
		}else if(status.equalsIgnoreCase("WARNING")) {
			parent.warning(MarkupHelper.createLabel(log, ExtentColor.YELLOW));
		}
		else if(status.equalsIgnoreCase("SKIP")) {
			parent.skip(MarkupHelper.createLabel(log, ExtentColor.ORANGE));
			parent.skip("The test is skipped due to dependency failure");
		}
		else  {
			parent.info(MarkupHelper.createLabel(log, ExtentColor.GREY));
		}		

	}

	public static void logPassDetaits(String log) {
		parent.pass(MarkupHelper.createLabel(log, ExtentColor.GREEN));
	}

	public static void logFailureDetails(String log) {
		parent.fail(MarkupHelper.createLabel(log, ExtentColor.RED));
	}

	public static void logInfoDetails(String log) {
		parent.info(MarkupHelper.createLabel(log, ExtentColor.GREY));
	}
	public static void logExceptionDetails(String log) {
		parent.fail(log);
	}
	
	public static void logRequestMethodName() {
	    String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		parent.info(MarkupHelper.createLabel(methodName,ExtentColor.LIME));
		
	}
	
	public static void logWarningDetails(String log) {
		parent.warning(MarkupHelper.createLabel(log, ExtentColor.YELLOW));
	}

	public static void logSkippedDetails(String log) {
		parent.skip(MarkupHelper.createLabel(log, ExtentColor.ORANGE));
	}

	public static void logJson(String object) {
		parent.info(MarkupHelper.createCodeBlock(object, CodeLanguage.JSON));
	}

	public static void logXml(String object) {
		parent.info(MarkupHelper.createCodeBlock(object, CodeLanguage.XML));
	}
	
	
	public static void logHeaders(List<Header> headerList) {
		String [][] arrayHeaders=  headerList.stream().map(header -> new String[] {header.getName(), header.getValue()})
				.toArray(String[][] :: new);
		parent.info(MarkupHelper.createTable(arrayHeaders));
	}

	@AfterSuite(alwaysRun = true)
	public synchronized void endResult() {
		extent.flush();
	}

	public Status getTestStatus() {
		return parent.getModel().getStatus();
	}

	public void onTestFailure(ITestResult result) {
		ExtentReportManager.logFailureDetails(result.getThrowable().getMessage());
		String stackTrace = Arrays.toString(result.getThrowable().getStackTrace());
		stackTrace = stackTrace.replaceAll(",", "<br>");
		String formmatedTrace = "<details>\n" +
				"    <summary>Click Here To See Exception Logs</summary>\n" +
				"    " + stackTrace + "\n" +
				"</details>\n";
		ExtentReportManager.logExceptionDetails(formmatedTrace);
	}
	
	


}

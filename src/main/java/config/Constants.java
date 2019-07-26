package config;
 
public class Constants {
 
 //List of System Variables
 public static final String URL = "http://rently.rentlypurple.com/";
 public static final String Path_TestData = "/home/rently/gitFramework/RentlyTestAutomation/src/main/resources/testData/DataEngine.xlsx";
 public static final String Path_OR = "home/rently/eclipse-workspace/RentlyAutomation/src/main/resources/testData/OR.txt";
 public static final String File_TestData = "DataEngine.xlsx";
 
 public static final String KEYWORD_FAIL = "FAIL";
 public static final String KEYWORD_PASS = "PASS";
 
 //Data Sheet Column Numbers
 public static final int Col_TestCaseID = 0; 
 public static final int Col_Precondition =1;
 public static final int col_Postcondition = 2;
 public static final int Col_TeststepDescription =2;
 public static final int Col_TestScenarioID =1 ;
 public static final int Col_ActionKeyword =3 ;
 public static final int Col_RunMode =3 ;
// public static final int Browser =3 ;
 public static final int Col_Result =4 ;
 public static final int Col_TestStepResult =4 ;
 
 // Data Engine Excel sheets
 public static final String Sheet_TestSteps = "Test Steps";
 public static final String Sheet_TestCases = "Test Cases";
 
}
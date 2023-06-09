package com.lerato.registerandlogin;
/**
 @author leratokekana
*/

//java imports
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import org.junit.Test;
import static org.junit.Assert.*;


public class RegisterAndLogin {
    
    //declarations for ArrayLists
    static List<String> firstName = new ArrayList<>();
    static List<String> lastName = new ArrayList<>();
    static List<String> username = new ArrayList<>();
    static List<String> password = new ArrayList<>();
    
    private static String taskName;
    private static int taskNum;
    private static String developerDetails;

    //declarationfor scanner item
    static Scanner s = new Scanner(System.in);
    
    //main method
    public static void main(String[] args) {
        System.out.println("Create an account");
        //calling method to create account
        RegisterAcc();
    }
    
    
    //creating login class
    public static void RegisterAcc() {
        
        System.out.println("Enter your first name: ");
        String name;
        name = s.next();
        //saving name to arraylist
        firstName.add(name);
        
        System.out.println("Enter your last name: ");
        String lName;
        lName = s.next();
        //saving name to arraylist
        lastName.add(lName);
        
        captureUsername();
        createPassword();   
    }
    
    public static void captureUsername(){
        System.out.println("Enter a username: ");
        System.out.println("Your username must be 5 or less characters long");
        System.out.println("Your username must also contain an underscore (_) ");
        
        String uName;
        uName = s.next();
        char underscore = '_';
        String underscoreString = new String(new char[]{underscore});
        
        if(uName.length() <= 5 && uName.contains(underscoreString)){
            username.add(uName);
            System.out.println("Username Successfully captured");
        }
        else{
            System.out.println("Username is not correctly formatted");
            captureUsername();
        }
    }
    
    public static void createPassword(){
        
        System.out.println("""
                           Create a password: 
                           Your password must: 
                           Be at least 8 characters long
                           Contain a capital letter
                           Contain a number
                           Contain a special charcter""");
        
        String pass;
        pass = s.next();
        
        if( pass.length() >= 8 &&
            !pass.equals(pass.toLowerCase()) &&
            !pass.equals(pass.toUpperCase()) &&
            pass.contains("!@#$%^&*")
        ){
            password.add(pass);
            System.out.println("Password successfully captured");
        }
        else{
            System.out.println("Your password is not correctly captured");
            createPassword();
        }
    }
    
    public static void loginSystem(){
        HashMap<String, String> users = new HashMap<>();
        
        System.out.println("Welcome to the login system!");
        System.out.print("Enter your username: ");
        String uName = s.nextLine();
        System.out.print("Enter your password: ");
        String pWord = s.nextLine();
        
        //if statement to print out pass word
        if(uName.matches(uName) && (pWord.matches(pWord))){
            System.out.println("Login successful");
            System.out.println("Welcome " + firstName + lastName);
            //calling logged in method
            loggedIn();
        }
            
        else {
            System.out.println("Login failed. Please try again.");
            loginSystem();
        }
        
    }
    
    //boolean method to check password complexity
    boolean checkPasswordComplexity(String password){
        String regex = "^(?=.*[0-9]"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,20}$";
        
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(password);
        return m.matches();
    }
    //boolean testing method for username
    boolean checkUserName(String username){
        return !(username.length() >= 5 || username.indexOf('_') == -1);
    }
    //boolean testing method for password
    boolean loginUser(String username, String password){
        return username.equals(this.username) && password.equals(this.password);
    }
    
    public static void loggedIn(){
        System.out.println("Welcome to EasyKanban"
                + "Press 1 to add tasks"
                + "Press 2 to show report"
                + "Press 3 to quit");
        
        //declaring the variable for the choice
        int choice = s.nextInt();
        //if statement to choose from options
        if (choice == 1){
            System.out.println("Add Tasks");
            askForTask();
        }
        if(choice == 2){
            System.out.println("Show Report"
                    + "Coming soon...");
        }
        if(choice == 3){
            System.exit(0);
        }
           
    }
    //declaring an arraylist to store the steps
    static List<String> Task = new ArrayList<>();
    
    public static void askForTask(){
        System.out.println("How many tasks would you like to add?"
                + "**Note**"
                + "Your tasks should not exceed 50 characters.");
        addTasks();
    }
    public static void addTasks(){
        
        int numTasks = s.nextInt();
        //for loop to add tasks
        for(int t = 0; t < numTasks; t++){
            System.out.println("Add task description: ");
            String addedTask = s.next();
            //if statement to ensure that description is less than 50 characters
            if(addedTask.length() <= 50){
                Task.add(addedTask);
                //adding the task details
                //these will be displayed to the JOptionPane
                System.out.println("Add Task status: ");
                String taskStatus = s.next();
                System.out.println("Add developer details: ");
                developerDetails = s.next();
                System.out.println("Add task number: ");
                taskNum = s.nextInt();
                System.out.println("Add task name: ");
                taskName = s.next();
                //String taskDescription;
                System.out.println("Add task duration");
                int taskDuration = s.nextInt();
                String taskID = generateTaskID();
                
                String taskDetails = "Task Status: " + taskStatus + "\n" +
                    "Developer Details: " + developerDetails + "\n" +
                    "Task Number: " + taskNum + "\n" +
                    "Task Name: " + taskName + "\n" +
                    "Task Description: " + addedTask + "\n" +
                    "Task ID: " + taskID + "\n" +
                    "Task Duration: " + taskDuration + " hours";
                
                System.out.println("Task successfully captured");
                JOptionPane.showMessageDialog(null, taskDetails, "Task deatils", JOptionPane.INFORMATION_MESSAGE);
            } 
            else {
                System.out.println("""
                                   Error capturing task description \n
                                   Please enter a task description of less than 50 characters""");
                addTasks();
            }
        }
    }
    
    //method to generate task ID as required
    public static String generateTaskID() {
        String taskID = taskName.substring(0, 2).toUpperCase() + ":" +
                taskNum + ":" +
                developerDetails.substring(developerDetails.length() - 3).toUpperCase();
        return taskID;
    }

    // Constructor for class tasks
    public void Task(String taskDescription, int hours) {
        this.taskDescription = taskDescription;
        this.hours = hours;
    }

    // Method to check task description length
    public boolean checkTaskDescription() {
        return taskDescription.length() <= 50;
    }

    // Method to create and return task ID
    public String createTaskID() {
        String taskID = taskDescription.substring(0, 2).toUpperCase() + hours;
        return taskID;
    }

    // Method to print task details
    public String printTaskDetails() {
        String taskDetails = "Task Description: " + taskDescription + "\n" +
                "Task ID: " + createTaskID() + "\n" +
                "Hours: " + hours;
        return taskDetails;
    }

    // Method to return total combined hours of all tasks
    public static int returnTotalHours(Task... tasks) {
        int totalHours = 0;
        for (Task task : tasks) {
            totalHours += task.hours;
        }
        return totalHours;
    }
    
        public static void testCaptureUsername_ValidUsername() {
        // Test case: Valid username with length <= 5 and contains an underscore
        String validUsername = "user_";
        boolean expected = true;
        //boolean actual = RegisterAndLogin.checkUserName(validUsername);
        //assertEquals(expected, actual);
    }

    //unit tests:
    public static void testCaptureUsername_InvalidUsername() {
        // Test case: Invalid username with length > 5 and does not contain an underscore
        String invalidUsername = "invaliduser";
        boolean expected = false;
        //boolean actual = RegisterAndLogin.checkUserName(invalidUsername);
        //assertEquals(expected, actual);
    }

    
    public static void testCreatePassword_ValidPassword() {
        // Test case: Valid password with length >= 8, contains a capital letter, a number, and a special character
        String validPassword = "Password1!";
        boolean expected = true;
        //boolean actual = RegisterAndLogin.checkPasswordComplexity(validPassword);
        //assertEquals(expected, actual);
    }

    
    public static void testCreatePassword_InvalidPassword() {
        // Test case: Invalid password that does not meet the complexity requirements
        String invalidPassword = "weakpassword";
        boolean expected = false;
        //boolean actual = RegisterAndLogin.checkPasswordComplexity(invalidPassword);
        //assertEquals(expected, actual);
    }

    
    public static void testLoginUser_ValidCredentials() {
        // Test case: Valid username and password
        String validUsername = "user";
        String validPassword = "Password1!";
        boolean expected = true;
        //boolean actual = RegisterAndLogin.loginUser(validUsername, validPassword);
        //assertEquals(expected, actual);
    }

    
    public static void testLoginUser_InvalidCredentials() {
        // Test case: Invalid username and password
        String invalidUsername = "invaliduser";
        String invalidPassword = "invalidpassword";
        boolean expected = false;
        //boolean actual = RegisterAndLogin.loginUser(invalidUsername, invalidPassword);
        //assertEquals(expected, actual);
    }

}

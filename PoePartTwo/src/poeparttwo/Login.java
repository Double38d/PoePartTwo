/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poeparttwo;

/**
 *
 * @author Student
 */
public class Login extends Registration{
    
   // Validates if login inputs match registered credentials
    public boolean loginUser(String name, String password) {
        if (this.userName == null || this.passWord == null) {
            return false;
        }
        return name.equals(this.userName) && password.equals(this.passWord);
    }

    // Returns the exact login response message required by tests
    public String returnLoginStatus(boolean loginStatus) {
        if (loginStatus) {
            return "Welcome " + firstName + ", " + lastName + " it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
}
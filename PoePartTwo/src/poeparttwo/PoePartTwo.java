/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package poeparttwo;

import java.util.Scanner;

public class PoePartTwo {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Login userLogin = new Login();

        // 1. Part 1 Registration Section
        System.out.println("===== REGISTER =====");
        System.out.print("Please enter first name: ");
        userLogin.firstName = scan.nextLine();
        
        System.out.print("Please enter last name: ");
        userLogin.lastName = scan.nextLine();
        
        System.out.print("Please enter username: ");
        String username = scan.nextLine();
        
        System.out.print("Please enter password: ");
        String password = scan.nextLine();
        
        System.out.print("Please enter cellphone number: ");
        String cellphone = scan.nextLine();

        
        System.out.println("\n" + userLogin.registerUser(username, password, cellphone));

       
        if (!userLogin.registerStatus(username, password, cellphone)) {
            System.out.println("Registration failed. Exiting application.");
            return; 
        }

        
        System.out.println("\n===== LOGIN =====");
        System.out.print("Enter username: ");
        String loginUser = scan.nextLine();
        
        System.out.print("Enter password: ");
        String loginPass = scan.nextLine();

        boolean loginSuccess = userLogin.loginUser(loginUser, loginPass);
        System.out.println(userLogin.returnLoginStatus(loginSuccess));

        
        if (loginSuccess) {
            System.out.println("\n========================================");
            System.out.println("Welcome to EasyKanban.");
            System.out.println("========================================");
           
        }
    }
}
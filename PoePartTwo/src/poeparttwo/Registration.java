/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poeparttwo;

/**
 *
 * @author Student
 */
public class Registration {
    
  // Member variables
    String userName;
    String passWord;
    String cellPhone;
    String firstName;
    String lastName;

    // Checks username: contains '_' and length <= 5
    public boolean checkUserName(String username) {
        return username != null && username.contains("_") && username.length() <= 5;
    }

    // Checks password complexity
    public boolean checkPasswordComplexity(String password) {
        if (password == null) return false;
        return password.length() >= 8
              && password.matches(".*[A-Z].*")
              && password.matches(".*[0-9].*")
             &&  password.matches(".*[^a-zA-Z0-9].*");
    }

    // Checks cellphone number format (+27 followed by 9 digits)
    public boolean checkCellPhoneNumber(String cellphone) {
        if (cellphone == null) return false;
      return cellphone.matches("^\\+27[0-9]{9}$");
    }

    
    public String registerUser(String username, String password, String cellphone) {
        if (!checkUserName(username)) {
            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        }
        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }
        if (!checkCellPhoneNumber(cellphone)) {
            return "Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.";
        }

       
        this.userName = username;
        this.passWord = password;
        this.cellPhone = cellphone;

        return "Username successfully captured.\nPassword successfully captured.\nCell number successfully captured.";
    }

    // Help method for  main program 
    public boolean registerStatus(String username, String password, String cellphone) {
        return checkUserName(username) &&
               checkPasswordComplexity(password) &&
               checkCellPhoneNumber(cellphone);
    }
}
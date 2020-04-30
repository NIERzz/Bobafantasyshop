/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testInterface;

import java.util.Scanner;

/**
 *
 * @author User
 */
import java.util.Scanner;

public class UserInterface {

    static Scanner yourChoice = new Scanner(System.in);
    static int confirmID;

    public String page1() {
        String choice = "test";

        System.out.println("1.Sign in");
        System.out.println("2.Sign up");
        System.out.print("Choose your option:  ");
        while (!(choice.equals("sign in")) && !(choice.equals("sign up")) && !(choice.equals("1")) && !(choice.equals("2"))) {
            choice = yourChoice.nextLine();
            choice = choice.toLowerCase();
            if (!(choice.equals("sign in")) && !(choice.equals("sign up")) && !(choice.equals("1")) && !(choice.equals("2"))) {
                System.out.println("You did not enter one of the options");
                System.out.println("Please re-enter either \"1\" or \"2\"");

            }
        }
        return choice;
    }

    
}

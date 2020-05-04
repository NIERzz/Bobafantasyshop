/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.account;

import model.Person;
import model.account.Account;

/**
 *
 * @author User
 */
public class StaffAccount extends Account{

    public StaffAccount(String id, String password, Person person) {
        super(id, password, person);
    }

}
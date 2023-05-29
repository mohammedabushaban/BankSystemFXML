/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author MoHammeD
 */
public class Account {

    private int id;
    private String username;
    private String accountNummber;
    private String currncy;
    private String balance;
    private String createdAt;

    public Account(int id, String username, String accountNummber, String currncy, String balance, String createdAt) {
        this.id = id;
        this.username = username;
        this.accountNummber = accountNummber;
        this.currncy = currncy;
        this.balance = balance;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAccountNummber() {
        return accountNummber;
    }

    public void setAccountNummber(String accountNummber) {
        this.accountNummber = accountNummber;
    }

    public String getCurrncy() {
        return currncy;
    }

    public void setCurrncy(String currncy) {
        this.currncy = currncy;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    //create a new user in users table
    public int save() throws SQLException, ClassNotFoundException {
        Connection c = DB.getInstance().getConnection();
        PreparedStatement ps = null;
        int recordCounter = 0;
        String sql = "INSERT INTO ACCOUNTS (ID,ACCOUNTN_UMMBER,USERNAME,CARANCY,BALANCE) VALUES (?, ?, ?, ?,?,?)";
        ps = c.prepareStatement(sql);
        ps.setInt(1, this.getId());
        ps.setString(2, this.getAccountNummber());
        ps.setString(3, this.getUsername());
        ps.setString(4, this.getCurrncy());
        ps.setString(5, this.getBalance());
        recordCounter = ps.executeUpdate();
        if (recordCounter > 0) {
            System.out.println(this.getUsername()
                    + " ACcount was added successfully!");
        }
        if (ps != null) {
            ps.close();
        }
        c.close();
        return recordCounter;
    }

    //get all Accounts from Accounts table
    public static ArrayList<Account> getAllAccounts() throws SQLException, ClassNotFoundException {
        Connection c = DB.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Account> accounts = new ArrayList<>();
        String sql = "SELECT * FROM ACCOUNTS ";
        ps = c.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()) {
            Account acount = new Account(0, rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
            acount.setId(rs.getInt(1));
            accounts.add(acount);

        }
        if (ps != null) {
            ps.close();
        }
        c.close();
        return accounts;
    }

    //update an existing Account in Accounts table 
    public int update() throws SQLException, ClassNotFoundException {
        Connection c = DB.getInstance().getConnection();
        PreparedStatement ps = null;
        int recordCounter = 0;
        String sql = "UPDATE ACCOUNTS SET USERNAME=?, ACCOUNTN_UMMBER=?, CARANCY=? , BALANCE=?,ROLE=? WHERE ID=?";
        ps = c.prepareStatement(sql);
        ps.setString(1, this.getAccountNummber());
        ps.setString(2, this.getBalance());
        ps.setString(3, this.getCurrncy());
        ps.setString(4, this.getUsername());
        ps.setInt(6, this.getId());
        recordCounter = ps.executeUpdate();
        if (recordCounter > 0) {
            System.out.println("Acoount with id : " + this.getId() + " was updated successfully!");
        }
        if (ps != null) {
            ps.close();
        }
        c.close();
        return recordCounter;
    }

    //delete an Account from Accounts table 
    public int delete() throws SQLException, ClassNotFoundException {
        Connection c = DB.getInstance().getConnection();
        PreparedStatement ps = null;
        int recordCounter = 0;
        String sql = "DELETE FROM ACCOUNTS WHERE ID=? ";
        ps = c.prepareStatement(sql);
        ps.setInt(1, this.getId());
        recordCounter = ps.executeUpdate();
        if (recordCounter > 0) {
            System.out.println("The account with id : " + this.getId() + " was deleted successfully!");
        }
        if (ps != null) {
            ps.close();
        }
        c.close();
        return recordCounter;
    }

}

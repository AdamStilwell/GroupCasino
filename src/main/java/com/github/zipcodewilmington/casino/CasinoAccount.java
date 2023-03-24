package com.github.zipcodewilmington.casino;

/**
 * Created by leon on 7/21/2020.
 * `ArcadeAccount` is registered for each user of the `Arcade`.
 * The `ArcadeAccount` is used to log into the system to select a `Game` to play.
 */
public class CasinoAccount {
    private String name;
    private String password;
    private Double balance;

    public CasinoAccount(){
        this("bot","bot",0.0);
    }
    public CasinoAccount(String name, String password){
        this(name,password,0.0);
    }
    public CasinoAccount(String name, String password, Double balance){
        this.name = name;
        this.password = password;
        this.balance = balance;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double deposit(Double amount){
        this.balance += amount;
        return this.balance;
    }

    public Double withdraw(Double amount){
        this.balance -= amount;
        return this.balance;
    }
}

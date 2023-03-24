package com.github.zipcodewilmington.casino;

import com.github.zipcodewilmington.utils.AnsiColor;
import com.github.zipcodewilmington.utils.IOConsole;

public abstract class BalanceManager {
    private static final IOConsole console = new IOConsole(AnsiColor.BLUE);
    private static final IOConsole errorConsole = new IOConsole(AnsiColor.RED);
    private static final IOConsole successConsole = new IOConsole(AnsiColor.YELLOW);
    public static void showBalance(CasinoAccount account){
        boolean isInBalanceMenu = true;
        while(isInBalanceMenu){
            System.out.println(account.getBalance());
            successConsole.println("Your current balance is %.2f ",account.getBalance());
            Integer option = balanceMenu();
            switch (option){
                case 1:
                    Double depAmount = console.getDoubleInput("Enter the amount you want to deposit (0 - 20000):");
                    if(depAmount >= 0 && depAmount <= 20000){
                        account.deposit(depAmount);
                        successConsole.println("Deposit successful!");
                    } else {
                        errorConsole.println("Invalid amount, amount should be 1 - 20000!");
                    }
                    break;
                case 2:
                    Double withdrawAmount = console.getDoubleInput("Enter the amount you want to withdraw:");
                    if(withdrawAmount < 0 ){
                        errorConsole.println("Invalid amount, amount cannot be negative!");
                    }else if(withdrawAmount > account.getBalance()){
                        errorConsole.println("Invalid amount, amount cannot exceed current balance!");
                    } else {
                        account.withdraw(withdrawAmount);
                        successConsole.println("Withdraw successfully!");
                    }
                    break;
                case 3:
                    isInBalanceMenu = false;
                    break;
                default:
                    errorConsole.println("Please select from given options only!");
            }
        }
    }

    private static Integer balanceMenu(){
        return console.getIntegerInput(new StringBuilder()
                .append("+-------------------------------+\n")
                .append("|      ACCOUNT BALANCE MENU     |\n")
                .append("+-------------------------------+\n")
                .append("|  1. Deposit                   |\n")
                .append("|  2. Withdraw                  |\n")
                .append("|  3. Exit to main menu         |\n")
                .append("+-------------------------------+\n")
                .append("SELECT A NUMBER: ")
                .toString());
    }
}

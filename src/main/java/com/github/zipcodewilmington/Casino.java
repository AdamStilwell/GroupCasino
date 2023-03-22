package com.github.zipcodewilmington;

import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.CasinoAccountManager;
import com.github.zipcodewilmington.casino.GameInterface;
import com.github.zipcodewilmington.casino.PlayerInterface;
import com.github.zipcodewilmington.casino.games.numberguess.NumberGuessGame;
import com.github.zipcodewilmington.casino.games.numberguess.NumberGuessPlayer;
import com.github.zipcodewilmington.casino.games.slots.SlotsGame;
import com.github.zipcodewilmington.casino.games.slots.SlotsPlayer;
import com.github.zipcodewilmington.utils.AnsiColor;
import com.github.zipcodewilmington.utils.IOConsole;

/**
 * Created by leon on 7/21/2020.
 */
public class Casino implements Runnable {
    private final IOConsole console = new IOConsole(AnsiColor.BLUE);
    private final IOConsole errorConsole = new IOConsole(AnsiColor.RED);
    private final IOConsole successConsole = new IOConsole(AnsiColor.YELLOW);

    private CasinoAccountManager casinoAccountManager;
    private CasinoAccount currentAccount = null;

    public Casino(){
        casinoAccountManager = new CasinoAccountManager();
    }

    @Override
    public void run() {
        boolean isInCasino = true;

        // print welcome message
        welcomeMessage();

        // run the casino until exit
        while(isInCasino){

            // show main menu and ask for selection
            int mainMenuOption = lobbyMenu();

            // check for selection and execute
            switch(mainMenuOption){
                //create an account
                case 1:
                    if(currentAccount==null) {
                        createNewAccount();
                    } else {
                        errorConsole.println("Please log out before execute this option");
                    }
                    break;

                // login
                case 2:
                    if(currentAccount==null) {
                        login();
                    } else {
                        errorConsole.println("You already logged in!");
                    }
                    break;

                // show balance
                case 3:
                    if(currentAccount!=null){
                        showBalance();
                    }else {
                        errorConsole.println("Please log in before execute this option");
                    }
                    break;

                // select a game
                case 4:
                    if(currentAccount!=null){
                        // call games menu
                    }else {
                        errorConsole.println("Please log in before execute this option");
                    }
                    break;

                // go to lounge
                case 5:
                    if(currentAccount!=null){
                    } else {
                        errorConsole.println("Please log in before execute this option");
                    }
                    break;

                // log out of current account
                case 6:
                    if(currentAccount!=null){
                        currentAccount = null;
                        successConsole.println("Logged out successfully!");
                    } else {
                        errorConsole.println("No account was logged in!");
                    }
                    break;

                // exit casino and save data to database
                case 7:
                    casinoAccountManager.saveAllAccounts();
                    successConsole.println("Bye Bye, See You Again Soon!");
                    isInCasino = false;
                    break;

                // other cases => error message
                default:
                    errorConsole.println("Please select from given options only!");
            }
        }
//        String arcadeDashBoardInput;
//        CasinoAccountManager casinoAccountManager = new CasinoAccountManager();
//        do {
//            arcadeDashBoardInput = getArcadeDashboardInput();
//            if ("select-game".equals(arcadeDashBoardInput)) {
//                String accountName = console.getStringInput("Enter your account name:");
//                String accountPassword = console.getStringInput("Enter your account password:");
//                CasinoAccount casinoAccount = casinoAccountManager.getAccount(accountName, accountPassword);
//                boolean isValidLogin = casinoAccount != null;
//                if (isValidLogin) {
//                    String gameSelectionInput = getGameSelectionInput().toUpperCase();
//
//                    // add games to selection prompt
//                    if (gameSelectionInput.equals("SLOTS")) {
//                        play(new SlotsGame(), new SlotsPlayer());
//                    } else if (gameSelectionInput.equals("NUMBERGUESS")) {
//                        play(new NumberGuessGame(), new NumberGuessPlayer());
//
//                        // add games here
//                    } else {
//                        // TODO - implement better exception handling
//                        String errorMessage = "[ %s ] is an invalid game selection";
//                        throw new RuntimeException(String.format(errorMessage, gameSelectionInput));
//                    }
//
//
//                } else {
//                    // TODO - implement better exception handling
//                    String errorMessage = "No account found with name of [ %s ] and password of [ %s ]";
//                    throw new RuntimeException(String.format(errorMessage, accountPassword, accountName));
//                }
//            } else if ("create-account".equals(arcadeDashBoardInput)) {
//            }
//        } while (!"logout".equals(arcadeDashBoardInput));
    }

    private void welcomeMessage() {
         console.println(new StringBuilder()
                .append("Welcome to the Arcade Dashboard!")
                .append("\nFrom here, you can select any of the following options:")
                .toString());
    }

    private Integer lobbyMenu(){
        return console.getIntegerInput(new StringBuilder()
                .append("+-------------------------------+\n")
                .append("|         MAIN  MENU            |\n")
                .append("+-------------------------------+\n")
                .append("|  1. Create an account         |\n")
                .append("|  2. Login                     |\n")
                .append("|  3. Show balance              |\n")
                .append("|  4. Select a game             |\n")
                .append("|  5. Go to lounge              |\n")
                .append("|  6. Logout of current account |\n")
                .append("|  7. Exit casino               |\n")
                .append("+-------------------------------+\n")
                .append("SELECT A NUMBER: ")
                .toString());
    }

    private void createNewAccount(){
        console.println("Welcome to the account-creation screen.");
        while(true){
            String accountName = console.getStringInput("Enter your account name:");
            if(casinoAccountManager.checkAccountName(accountName)){ // if name already exist
                errorConsole.println("Account name already exists! Please choose another one.");
                continue;
            }
            String password = console.getStringInput("Enter your password:");
            CasinoAccount account = casinoAccountManager.createAccount(accountName, password);
            casinoAccountManager.registerAccount(account);
            successConsole.println("Account created successfully! Sending you back to the main menu!");
            break;
        }
    }

    private void login(){
        console.println("Welcome to the log-in screen.");
        while(true){
            String accountName = console.getStringInput("Enter your account name:");
            // if name not already in DB
            if(!casinoAccountManager.checkAccountName(accountName)){
                errorConsole.println("Account name is not recognized! Please try again.");
                continue;
            }
            String password = console.getStringInput("Enter your password:");
            if(casinoAccountManager.checkAccount(accountName,password)){
                CasinoAccount account = casinoAccountManager.createAccount(accountName, password);
                casinoAccountManager.registerAccount(account);
                currentAccount = account;
                successConsole.println("Account logged in successfully!");
                break;
            }
            errorConsole.println("Password is incorrect, please try again!");
        }
    }

    private void showBalance(){
        boolean isInBalanceMenu = true;
        while(isInBalanceMenu){
            successConsole.println("Your current balance is %.2f ",currentAccount.getBalance());
            Integer option = balanceMenu();
            switch (option){
                case 1:
                    Double depAmount = console.getDoubleInput("Enter the amount you want to deposit:");
                    if(depAmount > 0 && depAmount < 20000){
                        currentAccount.deposit(depAmount);
                        successConsole.println("Deposit successful!");
                    } else {
                        errorConsole.println("Invalid amount, amount should be 1 - 20000!");
                    }
                    break;
                case 2:
                    Double withdrawAmount = console.getDoubleInput("Enter the amount you want to withdraw:");
                    if(withdrawAmount < 0 ){
                        errorConsole.println("Invalid amount, amount cannot be negative!");
                    }else if(withdrawAmount > currentAccount.getBalance()){
                        errorConsole.println("Invalid amount, amount cannot exceed current balance!");
                    } else {
                        currentAccount.withdraw(withdrawAmount);
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

    private Integer balanceMenu(){
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

    private String getGame() {
        boolean isGettingGame = true;
        console.println("Welcome to the Game Selection Dashboard!");
        while(isGettingGame){
            Integer option = gameMenu();
            switch (option){
                case 1: //slots game
                    play(new SlotsGame(), new SlotsPlayer());
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    isGettingGame = false;
                    break;
                default:
                    errorConsole.println("Please select from given options only!");
            }
        }
        return console.getStringInput(new StringBuilder()
                .append("Welcome to the Game Selection Dashboard!")
                .append("\nFrom here, you can select any of the following options:")
                .append("\n\t[ SLOTS ], [ NUMBERGUESS ]")
                .toString());
    }

    private Integer gameMenu(){
        return console.getIntegerInput(new StringBuilder()
                .append("+-----------------------+\n")
                .append("|      GAMES MENU       |\n")
                .append("+-----------------------+\n")
                .append("|  1. Slots             |\n")
                .append("|  2. Number Guessing   |\n")
                .append("|  3. Roulette          |\n")
                .append("|  4. Black Jack        |\n")
                .append("|  5. Wheel of 6        |\n")
                .append("|  6. War (non-betting) |\n")
                .append("|  7. Exit to main menu |\n")
                .append("+-----------------------+\n")
                .append("SELECT A NUMBER: ")
                .toString());
    }
    private void play(Object gameObject, Object playerObject) {
        GameInterface game = (GameInterface)gameObject;
        PlayerInterface player = (PlayerInterface)playerObject;
        game.add(player);
        game.run();
    }
}

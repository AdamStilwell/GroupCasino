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


    CasinoAccountManager casinoAccountManager = new CasinoAccountManager();
    CasinoAccount currentAccount = null;

    public Casino(){
        casinoAccountManager = new CasinoAccountManager();
    }


    @Override
    public void run() {
        boolean isInCasino = true;
        while(isInCasino){
            int mainMenuOption = lobbyMenu();
            switch(mainMenuOption){
                case 1: //create an account
                    if(currentAccount==null) {
                        createNewAccount();
                    } else {
                        errorConsole.println("Please log out before execute this option");
                    }
                    break;

                case 2: // login
                    if(currentAccount==null) {
                        login();
                    } else {
                        errorConsole.println("You already logged in!");
                    }
                    break;

                case 3: // show balance
                    if(currentAccount!=null){
                        System.out.println(currentAccount.getBalance());
                    }else {
                        errorConsole.println("Please log in before execute this option");
                    }
                    break;
                case 4: // select a game
                    if(currentAccount!=null){
                        // call games menu
                    }else {
                        errorConsole.println("Please log in before execute this option");
                    }
                    break;
                case 5: // go to lounge
                    if(currentAccount!=null){
                    } else {
                        errorConsole.println("Please log in before execute this option");
                    }
                    break;
                case 6: // log out of current account
                    if(currentAccount!=null){
                        currentAccount = null;
                        successConsole.println("Logged out successfully!");
                    } else {
                        errorConsole.println("No account was logged in!");
                    }
                    break;
                case 7: // exit casino
                    successConsole.println("Bye Bye, See You Again Soon!");
                    isInCasino = false;
                    break;
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

    private String getArcadeDashboardInput() {
        return console.getStringInput(new StringBuilder()
                .append("Welcome to the Arcade Dashboard!")
                .append("\nFrom here, you can select any of the following options:")
                .append("\n\t[ create-account ], [ select-game ]")
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
            if(!casinoAccountManager.checkAccountName(accountName)){ // if name not already in DB
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

    private boolean checkIsNotLoggedIn(String errorMessage){
        errorConsole.println(errorMessage);
        if(currentAccount==null){
            return true;
        }
        return false;
    }

    private String getGameSelectionInput() {
        return console.getStringInput(new StringBuilder()
                .append("Welcome to the Game Selection Dashboard!")
                .append("\nFrom here, you can select any of the following options:")
                .append("\n\t[ SLOTS ], [ NUMBERGUESS ]")
                .toString());
    }

    private void play(Object gameObject, Object playerObject) {
        GameInterface game = (GameInterface)gameObject;
        PlayerInterface player = (PlayerInterface)playerObject;
        game.add(player);
        game.run();
    }
}

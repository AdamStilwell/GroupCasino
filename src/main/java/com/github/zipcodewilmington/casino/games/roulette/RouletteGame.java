package com.github.zipcodewilmington.casino.games.roulette;

import com.github.zipcodewilmington.casino.*;
import com.github.zipcodewilmington.casino.gameTools.WheelThing;
import com.github.zipcodewilmington.utils.AnsiColor;
import com.github.zipcodewilmington.utils.IOConsole;


import java.util.HashMap;
import java.util.Map;

import java.util.Objects;

public class RouletteGame extends Game implements GamblingGameInterface {
    private final IOConsole console = new IOConsole(AnsiColor.CYAN);
    private final IOConsole errorConsole = new IOConsole(AnsiColor.RED);
    private final IOConsole successConsole = new IOConsole(AnsiColor.YELLOW);
    static Map<Integer, String> numberToColor = new HashMap<>();
    boolean isStartGame, isRunning;
    private RoulettePlayer currentPlayer;
    private BettingPayout betHandler;
    static WheelThing wheel;
    Integer payout;

    public RouletteGame() {
        wheel = new WheelThing();
        betHandler = new BettingPayout(5, 1000);
        mapNumberToColor();
        payout = 0;
    }

    @Override
    public void run() {
        isRunning = true;
        console.println("Welcome to the Roulette Game Selection Dashboard!");
        while (isRunning) {
            Integer option = RouletteMenu();
            switch (option) {
                case 1: // start game
                    startGame();
                    break;
                case 2: // check balance / deposit / withdraw
                    BalanceManager.showBalance(currentPlayer.getPlayerAccount());
                    break;
                case 3: // exit game
                    isRunning = false;
                    break;
                default:
                    break;
            }
        }
    }

    private Integer RouletteMenu() {
        return console.getIntegerInput(new StringBuilder()
                .append("+-----------------------+\n")
                .append("|     ROULETTE MENU     |\n")
                .append("+-----------------------+\n")
                .append("|  1. Start Game        |\n")
                .append("|  2. Check Balance     |\n")
                .append("|  3. Exit Game         |\n")
                .append("+-----------------------+\n")
                .append("SELECT A NUMBER: ")
                .toString());
    }

    @Override
    public void startGame() {
        isStartGame = true;
        Double bet = 0.0;
        boolean isWon = false;
        double amountWon = 0.0;
        while (isStartGame) {
            Integer option = RouletteStartGameMenu();
            switch (option) {

                case 1: // Number (pays 38 to 1)
                    bet = betHandler.takeBet(5, 1000, currentPlayer.getPlayerAccount().getBalance());
                    if (bet == 0.0) {
                        isStartGame = false;
                        break;
                    }
                    isWon = checkGuessNumber();
                    amountWon = betHandler.betPayout(bet, 38, currentPlayer.getPlayerAccount(), isWon);
                    if (isWon) {
                        console.println(String.format("You have won $%.2f", amountWon));
                        break;
                    }
                    errorConsole.println(String.format("Sorry, you have lost $%.2f!", bet));
                    break;

                case 2: // Even or Odd (pays 1 to 1)
                    bet = betHandler.takeBet(5, 1000, currentPlayer.getPlayerAccount().getBalance());
                    if (bet == 0.0) {
                        isStartGame = false;
                        break;
                    }
                    isWon = checkEvenOdd();
                    amountWon = betHandler.betPayout(bet, 1, currentPlayer.getPlayerAccount(), isWon);
                    if (isWon) {
                        console.println(String.format("You have won $%.2f", amountWon));
                        break;
                    }
                    errorConsole.println(String.format("Sorry, you have lost $%.2f!", bet));
                    break;

                case 3: // Red or Black (pays 1 to 1)
                    bet = betHandler.takeBet(5, 1000, currentPlayer.getPlayerAccount().getBalance());
                    if (bet == 0.0) {
                        isStartGame = false;
                        break;
                    }
                    isWon = checkRedBlack();
                    amountWon = betHandler.betPayout(bet, 1, currentPlayer.getPlayerAccount(), isWon);
                    if (isWon) {
                        console.println(String.format("You have won $%.2f", amountWon));
                        break;
                    }
                    errorConsole.println(String.format("Sorry, you have lost $%.2f!", bet));
                    break;

                case 4: // Green 0 & 00 (pays 1 to 3)
                    bet = betHandler.takeBet(5, 1000, currentPlayer.getPlayerAccount().getBalance());
                    if (bet == 0.0) {
                        isStartGame = false;
                        break;
                    }
                    isWon = checkZero();
                    amountWon = betHandler.betPayout(bet, 1, currentPlayer.getPlayerAccount(), isWon);
                    if (isWon) {
                        console.println(String.format("You have won $%.2f", amountWon));
                        break;
                    }
                    errorConsole.println(String.format("Sorry, you have lost $%.2f!", bet));
                    break;

                case 5: // return to previous menu
                    isStartGame = false;
                    break;

                case 6: // exit game completely
                    exitGame();
                    break;

                default: // wrong input
                    errorConsole.println("Invalid Input, Please try again!");
                    break;
            }
        }
    }

    private Integer RouletteStartGameMenu() {
        return console.getIntegerInput(new StringBuilder()
                .append("+---------------------------------+\n")
                .append("|   ROULETTE BET TYPE SELECTION   |\n")
                .append("+---------------------------------+\n")
                .append("|  1. Number (pays 38 to 1)       |\n")
                .append("|  2. Even or Odd (pays 1 to 1)   |\n")
                .append("|  3. Red or Black (pays 1 to 1)  |\n")
                .append("|  4. Green 0 & 00 (pays 1 to 3)  |\n")
                .append("|  5. Return to previous menu     |\n")
                .append("|  6. Exit game                   |\n")
                .append("+---------------------------------+\n")
                .append("SELECT A BET OPTION: ")
                .toString());
    }

    public boolean checkZero() { // need to distinguish 0 and 00 later
        Integer numberGuess = -1;
        while (numberGuess != 1 && numberGuess != 2) {
            numberGuess = console.getIntegerInput("Enter '1' for 0 and '2' for 00: ");
            if (numberGuess != 1 && numberGuess != 2) {
                errorConsole.println("Invalid number entered, try again!");
            }
        }
        Integer numberLandedOn = spinWheelNumber();
        return checkResult(numberLandedOn, numberGuess, 4);
    }

    public boolean checkRedBlack() {
        Integer numberGuess = 0;
        while (numberGuess != 1 && numberGuess != 2) {
            numberGuess = console.getIntegerInput("Enter '1' for RED and '2' for BLACK: ");
            if (numberGuess != 1 && numberGuess != 2) {
                errorConsole.println("Invalid number entered, try again!");
            }
        }
        Integer numberLandedOn = spinWheelNumber();
        return checkResult(numberLandedOn, numberGuess, 3);
    }

    public boolean checkEvenOdd() {
        Integer numberGuess = 0;
        while (numberGuess != 1 && numberGuess != 2) {
            numberGuess = console.getIntegerInput("Enter '1' for EVEN and '2' for ODD: ");
            if (numberGuess != 1 && numberGuess != 2) {
                errorConsole.println("Invalid number entered, try again!");
            }
        }
        Integer numberLandedOn = spinWheelNumber();
        return checkResult(numberLandedOn, numberGuess, 2);
    }

    public boolean checkGuessNumber() {
        Integer numberGuess = -1;
        while (numberGuess < 0 && numberGuess > 36) {
            numberGuess = console.getIntegerInput("Enter a number between 0 and 36: ");
            if (numberGuess < 0 && numberGuess > 36) {
                errorConsole.println("Invalid number entered, try again!");
            }
        }
        Integer numberLandedOn = spinWheelNumber();
        return checkResult(numberLandedOn, numberGuess, 1);
    }

    public boolean checkResult(Integer numberLandedOn, Integer numberGuess, Integer typeBet) {

        boolean isWon = false;
        switch (typeBet) {
            case 1: // guess number
                if (Objects.equals(numberGuess, numberLandedOn))
                    isWon = true;
                break;
            case 2: //guess even odd
                if ((numberGuess == 1 && numberLandedOn % 2 == 0) || (numberGuess == 2 && numberLandedOn % 2 != 0))
                    isWon = true;
                break;
            case 3: // check red or black
                if ((numberGuess == 1 && numberToColor.get(numberLandedOn).equalsIgnoreCase("red"))
                        || (numberGuess == 2 && numberToColor.get(numberLandedOn).equalsIgnoreCase("black")))
                    isWon = true;
                break;
            case 4:
                if ((numberLandedOn == 0 || numberLandedOn == 00) && numberGuess == numberLandedOn)
                    isWon = true;
                break;
            default:
                errorConsole.println("Invalid Input, Please try again!");
                break;
        }
        if (isWon) {
            successConsole.println("Congratulations! The ball landed on " +
                    numberLandedOn + "(" + numberToColor.get(numberLandedOn) + ").");
        } else {
            errorConsole.println("Sorry, the ball landed on " +
                    numberLandedOn + "(" + numberToColor.get(numberLandedOn) + ").");
        }

        return isWon;
    }

    public static Integer spinWheelNumber() {
        return wheel.spin(0, 36);
    }


    public boolean exitGame() {
        isRunning = false;
        isStartGame = false;
        return true; // set parameters to stop the game, return true to show game has exited
    }

    public Map<Integer, String> mapNumberToColor() {

        numberToColor = new HashMap<>();
        Integer[] green = new Integer[]{0, 00};
        Integer[] red = new Integer[]{1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36};
        Integer[] black = new Integer[]{2, 4, 6, 8, 10, 11, 13, 15, 17, 20, 22, 24, 26, 28, 29, 31, 33, 35};
        for (int i : green) {
            numberToColor.put(i, "green");
        }
        for (int i : red) {
            numberToColor.put(i, "red");
        }
        for (int i : black) {
            numberToColor.put(i, "black");
        }
        return numberToColor;
    }

    @Override
    public void remove(Player player) {
        currentPlayer = null;
    }

    @Override
    public Player add(Player player) {
        this.currentPlayer = (RoulettePlayer) player;
        return player;
    }

    public RoulettePlayer getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(RoulettePlayer currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

}


//    public void Roulette() {
//         //created wheel object to stimulate roulette wheel
//        int balance = 0;
//
//        Scanner scanner = new Scanner(System.in); // scanner to read suer input
//
//        System.out.println("Welcome to Roulette!"); // Welcome player to game
//
//        while (balance > 0) { // while loop to keep playing until the player runs out of money
//
//            System.out.println("Balance: " + balance);
//            System.out.println("Enter your bet (To quit: 0");
//            int bet = scanner.nextInt();
//
//            if (bet == 0) { // quit game if bet is 0
//                break;
//            }
//            if (bet > balance) { // verify that bet is valid
//                System.out.println("Invalid bet: Not enough money.");
//                continue;
//            }
//            System.out.println("Choose a bet type:"); //ask player to choose bet type
//            System.out.println("1. Number (pays 38 to 1)");
//            System.out.println("2. Even or Odd (pays 1 to 1)");
//            System.out.println("3. Red or Black (pays 1 to 1)");
//            System.out.println("4. Green 0 & 00 (pays 1 to 3)");
//            int betType = scanner.nextInt();
//
//            int result = Integer.parseInt(wheel.spin()); //spin wheel to get random number
//            String color= numberToColor.get(result); // get color of the number it lands on
//            int payOut = 0;
//
//            switch (betType) {
//                case 1: //Straight up
//                    System.out.print("Enter a number between 0 and 36: ");
//                    int number = scanner.nextInt();
//                    if (number == result) {
//                        payOut = bet * 35;
//                        System.out.println("Congratulations! The ball landed on " + result +"("+ color+").");
//                    } else {
//                        System.out.println("Sorry, the ball landed on " + result + "("+ color+").");
//                    }
//                    break;
//                case 2:
//                    System.out.println("Enter '1' for even and '2' for odd: ");
//                    int evenOdd = scanner.nextInt();
//                    if (result == 0 || (result % 2 == 0 && evenOdd == 1) || result % 2 == 1 && evenOdd == 2) {
//                        payOut = bet;
//                        System.out.println("Congratulations! The ball landed on " + result +"("+ color+").");
//                    } else {
//                        System.out.println("Sorry, the ball landed on " + result +"("+ color+").");
//                    }
//                    break;
//                case 3:
//                    System.out.println("Enter '1' for red and '2' for black: ");
//                    int redOrBlack = scanner.nextInt();
//                    if ((result <= 10 || (result >= 19 && result <= 28)) && redOrBlack == 1 ||
//                            ((result >= 11 && result <= 18) || (result >= 29 && result <= 36)) && redOrBlack == 2) {
//                        payOut = bet;
//                        System.out.println("Congratulations! The ball landed on " + result +"("+ color+").");
//                    } else {
//                        System.out.println("Sorry, the ball landed on " + result +"("+ color+").");
//                    }
//                    break;
//                case 4:
//                    System.out.println("Enter '1' for 0 and '2' for 00: ");
//                    int green = scanner.nextInt();
//                    if ((result == 0 && green == 1) || (result == 00 && green == 2)) {
//                        payOut = bet * 38;
//                        System.out.println("Congratulations! The ball landed on " + result +"("+ color+").");
//                    } else {
//                        System.out.println("Sorry, the ball landed on " + result +"("+ color+").");
//                    }
//                    break;
//            }
//            balance -= bet;
//            balance += payOut;
//
//            System.out.println("Balance: "+ balance);
//
//            if (payOut >0){
//                System.out.println("You won "+ payOut+ "!");
//            }else{
//                System.out.println("You lost "+bet+".");
//            }
//
//        }
//
//    }




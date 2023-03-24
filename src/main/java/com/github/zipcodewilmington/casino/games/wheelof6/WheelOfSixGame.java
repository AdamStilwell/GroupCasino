package com.github.zipcodewilmington.casino.games.wheelof6;

import com.github.zipcodewilmington.casino.BalanceManager;
import com.github.zipcodewilmington.casino.BettingPayout;
import com.github.zipcodewilmington.casino.Game;
import com.github.zipcodewilmington.casino.Player;
import com.github.zipcodewilmington.casino.gameTools.WheelThing;
import com.github.zipcodewilmington.casino.games.roulette.RoulettePlayer;
import com.github.zipcodewilmington.utils.AnsiColor;
import com.github.zipcodewilmington.utils.IOConsole;

import java.util.Scanner;

public class WheelOfSixGame extends Game {
    private final IOConsole console = new IOConsole(AnsiColor.CYAN);
    private final IOConsole errorConsole = new IOConsole(AnsiColor.RED);
    private final IOConsole successConsole = new IOConsole(AnsiColor.YELLOW);
    private WheelOfSixPlayer currentPlayer;
    private BettingPayout betHandler;
    boolean isRunning,isStartGame;
    private WheelThing wheel;
    private final String[] wheelSlots = new String[]{"cherry", "lemon", "orange", "plum", "bell", "bar"};

    public WheelOfSixGame() {
        betHandler = new BettingPayout(5,1000);
        wheel = new WheelThing(wheelSlots);
    }

    public double getBet() {
        double bet = betHandler.takeBet(5, 1000, currentPlayer.getPlayerAccount().getBalance());
        return bet;
    }

    public String getGuess() {
        boolean getGuessing = true;
        while(getGuessing){
            int guess = guessOptions();
            if(guess > 0 && guess < 7){
                return wheelSlots[guess-1];
            }
            errorConsole.println("Invalid entry, please try again!");
        }
        return null;
    }

    private int guessOptions(){
        return console.getIntegerInput(new StringBuilder()
                .append("+----------------------+\n")
                .append("|  WHEEL OF SIX SLOTS  |\n")
                .append("+----------------------+\n")
                .append("|     1. cherry        |\n")
                .append("|     2. lemon         |\n")
                .append("|     3. orange        |\n")
                .append("|     4. plum          |\n")
                .append("|     5. bell          |\n")
                .append("|     6. bar           |\n")
                .append("+----------------------+\n")
                .append("SELECT A NUMBER: ")
                .toString());
    }

    public static int calculateWinnings(int bet) {
        return bet;
    }

//    public static void main(String[] args) {
//        String[] wheelSlots = {"1", "2", "3", "4", "5", "6"};
//        WheelThing wheel = new WheelThing();
//        wheel.Wheel(wheelSlots);
//        Scanner scanner = new Scanner(System.in);
//        WheelOfSixGame game = new WheelOfSixGame(wheel, scanner);
//
//        System.out.println("Welcome to the Wheel of 6!");
//        System.out.println("The game is simple - just guess which of the 6 slots the wheel will land on.");
//
//        System.out.println();
//
//        while (true) {
//            game.play();
//
//            int bet = game.getBet();
//            if (bet == 0) {
//                break;
//            }
//        }
//    }



    public Object[] spinWheel(String[] wheel) {
        return wheel;
    }

    public int getPayout(String s, int i) {
        return i;
    }

    @Override
    public void remove(Player player) {

    }
    @Override
    public void run() throws InterruptedException {
        isRunning = true;
        console.println("Welcome to the Roulette Game Selection Dashboard!");
        while (isRunning) {
            Integer option = wheelOfSixMenu();
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

    private Integer wheelOfSixMenu() {
        return console.getIntegerInput(new StringBuilder()
                .append("+-----------------------+\n")
                .append("|   WHEEL OF SIX MENU   |\n")
                .append("+-----------------------+\n")
                .append("|  1. Start Game        |\n")
                .append("|  2. Check Balance     |\n")
                .append("|  3. Exit Game         |\n")
                .append("+-----------------------+\n")
                .append("SELECT A NUMBER: ")
                .toString());
    }

    @Override
    public Player add(Player player) {
        return this.currentPlayer = (WheelOfSixPlayer) player;
    }



    @Override
    public void startGame() {
        console.println("Welcome to the Wheel of 6!");
        console.println("The game is simple - just guess which of the 6 slots the wheel will land on.\n");
        double bet = -1;
        while(bet!=0.0){
            bet = getBet();
            if(bet==0.0){
                return;
            }
            String guess =  getGuess();
            String result = (wheel.spinString(0,wheelSlots.length));
            System.out.println("The wheel landed on: " + result);
            if (guess.equalsIgnoreCase(result)) {
                double wins = betHandler.betPayout(bet,1,currentPlayer.getPlayerAccount(),true);
                successConsole.println("Congratulations! You won $" + wins);
            } else {
                double loses = betHandler.betPayout(bet,1,currentPlayer.getPlayerAccount(),false);
                errorConsole.println(String.format("Sorry, you lost $%.2f better luck next time!",loses));
            }
        }
    }
}
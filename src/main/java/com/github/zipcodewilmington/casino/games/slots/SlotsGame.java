package com.github.zipcodewilmington.casino.games.slots;
import com.github.zipcodewilmington.casino.BalanceManager;
import com.github.zipcodewilmington.casino.BettingPayout;
import com.github.zipcodewilmington.casino.Game;
import com.github.zipcodewilmington.casino.Player;
import com.github.zipcodewilmington.utils.AnsiColor;
import com.github.zipcodewilmington.utils.IOConsole;

import java.util.Random;

/**
 * Created by leon on 7/21/2020.
 */
public class SlotsGame extends Game {
    SlotsPlayer currentPlayer;
    private final String[] outcome = {"ZCW", "Bar", "cherry", "7", "bunny", "$$$"};
    int a, b, c, d, e, f, g, h, j;
    String x,x1, x2, y, y1, y2, z, z1, z2;
    Random rand;
    int minBet = 5;
    int maxBet = 2000;
    BettingPayout bettingPayout = new BettingPayout(minBet, maxBet);
    private final IOConsole console = new IOConsole(AnsiColor.PURPLE);
    public int getSlotRoll() {
        return rand.nextInt(outcome.length);
    }
    public void pullSlots() throws InterruptedException {
        rand = new Random();
        int rollCount = 100;
        for (int i = 0; i < rollCount; i++) {
            a = getSlotRoll();
//            b = getSlotRoll();
//            c = getSlotRoll();
            b = getSlotRoll();
//            e = getSlotRoll();
//            f = getSlotRoll();
            c = getSlotRoll();
//            h = getSlotRoll();
//            j = getSlotRoll();
            x = outcome[a];
//            x1 = outcome[b];
//            x2 = outcome[c];
            y = outcome[b];
//            y1 = outcome[e];
//            y2 = outcome[f];
            z = outcome[c];
//            z1 = outcome[h];
//            z2 = outcome[j];
            System.out.printf("[  " + "  %8s  " + "  :  " + "  %8s  " + "  :  " + "  %8s  " +   "]\r", x,y,z);
            Thread.sleep(30);
        }
        System.out.println("[  " +  x + "  :  " + y + "  :  " + z +   "]");
        System.out.println();
    }
    @Override
    public void remove(Player player) {
    }
    public void run() {
        boolean playSlots = true;
        boolean slotsRunning = true;
            console.println(welcomeSlots());
        while (slotsRunning) {
            int slotInput = slotsMenu();
            switch (slotInput) {
                case 1:
                    playSlots = true;
                    Double money = console.getDoubleInput("Please enter money into slot machine!\nMinimum bet is " + minBet
                    + " Maximum bet is " + maxBet);
                    if (money > currentPlayer.getPlayerAccount().getBalance()){
                        console.println("Insufficient funds\nYour account balance is " + currentPlayer.getPlayerAccount().getBalance());
                        break;
                    }
                    currentPlayer.getPlayerAccount().withdraw(money);
                    console.println("$" + money + " deposited!\n" + "Your account balance is " + currentPlayer.getPlayerAccount().getBalance());
                    while (playSlots) {
                        if (bettingPayout.checkBet(money)) {
                            String pullLever = console.getStringInput("Enter \"pull\" to play or press enter\nEnter \"stop\" to quit");
                            if (pullLever.equalsIgnoreCase("pull") || pullLever.isEmpty()) {
                                try {
                                    pullSlots();
                                    money = winOrLose(money, a,b,c);
                                    playSlots = false;
                                } catch (InterruptedException ex) {
                                    //throw new RuntimeException(ex);
                                }
                            } else if (pullLever.equalsIgnoreCase("stop")) {
                                console.println(currentPlayer.getPlayerAccount().getBalance().toString());
                                playSlots = false;
                            }
                        } else {
                            break;
                        }
                    }
                    break;
                case 2:
                    BalanceManager.showBalance(currentPlayer.getPlayerAccount());
                    break;
                case 3:
                    slotsRunning = false;
                    break;
            }
        }
    }
    public double winOrLose(double money, int a, int b, int c) {
        if (a == 1 && b == 1 && c == 1){
            double payout = bettingPayout.betPayout(money, 40);
            currentPlayer.getPlayerAccount().deposit(payout);
            console.println(currentPlayer.getPlayerAccount().getBalance().toString());
            money += payout;
        } else if (a == b && b == c){
            double payout = bettingPayout.betPayout(money, 20);
            currentPlayer.getPlayerAccount().deposit(payout);
            console.println(currentPlayer.getPlayerAccount().getBalance().toString());
            money += payout;
        } else if (a == b || b == c || a == c){
            double payout = bettingPayout.betPayout(money, 5);
            currentPlayer.getPlayerAccount().deposit(payout);
            console.println(currentPlayer.getPlayerAccount().getBalance().toString());
            money += payout;
        } else {
            console.println("No winnings! Try again");
            console.println(currentPlayer.getPlayerAccount().getBalance().toString());
            money = 0;
        } return money;
    }
    private String welcomeSlots(){
        return new StringBuilder()
                .append("+-------------------------------+\n")
                .append("Ruby Slots! Are you a Winner?\n")
                .toString();
    }
    private Integer slotsMenu(){
        return console.getIntegerInput(new StringBuilder()
                .append("+-------------------------------+\n")
                .append("|         RUBY ~ SLOTS          |\n")
                .append("+-------------------------------+\n")
                .append("|  1. Pay slots                 |\n")
                .append("|  2. Show balance              |\n")
                .append("|  3. Exit slots                |\n")
                .append("+-------------------------------+\n")
                .append("SELECT A NUMBER: ")
                .toString());
    }

    @Override
    public Player add(Player player) {
        this.currentPlayer = (SlotsPlayer)player;
        return currentPlayer;
    }


    @Override
    public void startGame() {

    }

}

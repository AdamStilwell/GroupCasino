package com.github.zipcodewilmington.casino;

public abstract class Game {
    public abstract void remove(Player player);
    public abstract void run() throws InterruptedException;
    public abstract Player add(Player player);
    public abstract void startGame();
}

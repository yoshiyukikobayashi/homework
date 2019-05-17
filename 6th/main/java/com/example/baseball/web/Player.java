package com.example.baseball.web;
import java.util.Random;

public class Player {

  private int battingOrder;

  // constructor

  Player(int battingOrder) {
    this.battingOrder = battingOrder;
  }

  // getter and setter

  public int getBattingOrder() {
    return this.battingOrder;
  }

  // method

  public int batting() {
    Random rnd = new Random();
    return rnd.nextInt(21); // 0 - 8, 16 - 20 = out, 9 - 11 = single hit, 12 - 13 = two base, 14 = three base, 15 = homerun
  }

}
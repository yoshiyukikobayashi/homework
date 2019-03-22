import java.util.Random;

public class Player {

  private Team team;
  private int battingOrder;

  // constructor

  Player(Team team, int battingOrder) {
    this.team = team;
    this.battingOrder = battingOrder;
  }

  // getter and setter

  public int getBattingOrder() {
    return this.battingOrder;
  }

  // method

  public int batting() {
    Random rnd = new Random();
    return rnd.nextInt(16); // 0 - 8 = out, 9 - 11 = single hit, 12 - 13 = two base, 14 = three base, 15 = homerun
  }

}
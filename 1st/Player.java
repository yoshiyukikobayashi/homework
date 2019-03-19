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
    return rnd.nextInt(2); // 0 = out, 1 = hit
  }

}
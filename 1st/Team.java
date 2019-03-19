public class Team {

  private String name;
  private int score = 0;
  private int battingOrder = 1;

  //constructor

  Team(String name) {
    this.name = name;
  }

  // getter and setter

  public String getName() {
    return this.name;
  }
  public int getScore() {
    return this.score;
  }
  public int getBattingOrder() {
    return this.battingOrder;
  }

  public void setScore(int score) {
    this.score += score;
  }
  public void setBattingOrder(int battingOrder) {
    this.battingOrder = battingOrder;
  }

}
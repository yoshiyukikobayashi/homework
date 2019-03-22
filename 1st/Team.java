public class Team {

  private String name;
  private int score = 0;
  private int currentBattingOrder = 1;
  private int outCount = 0;
  private int firstPosition = 0;  // 0 = No one on the first base, 1 = A player the first base.
  private int secondPosition = 0;   // 0 = No one on the second base, 1 = A player the second base.
  private int thirdPosition = 0;   // 0 = No one on the third base, 1 = A player the third base.

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
  public int getCurrentBattingOrder() {
    return this.currentBattingOrder;
  }
  public int getOutCount() {
    return this.outCount;
  }
  public int getFirstPosition() {
    return this.firstPosition;
  }
  public int getSecondPosition() {
    return this.secondPosition;
  }
  public int getThirdPosition() {
    return this.thirdPosition;
  }

  public void setScore(int score) {
    this.score = score;
  }
  public void setCurrentBattingOrder(int currentBattingOrder) {
    this.currentBattingOrder = currentBattingOrder;
  }
  public void setOutCount(int outCount) {
    this.outCount = outCount;
  }
  public void setFirstPosition(int firstPosition) {
    this.firstPosition = firstPosition;
  }
  public void setSecondPosition(int secondPosition) {
    this.secondPosition = secondPosition;
  }
  public void setThirdPosition(int thirdPosition) {
    this.thirdPosition = thirdPosition;
  }

  // Methods

  public String returnRunners() {
    if (this.firstPosition == 0 && this.secondPosition == 0 && this.thirdPosition == 0) {
      return "ランナー無し";
    } else if (this.firstPosition == 1 && this.secondPosition == 0 && this.thirdPosition == 0) {
      return "ランナー 1 塁";
    } else if (this.firstPosition == 0 && this.secondPosition == 1 && this.thirdPosition == 0) {
      return "ランナー 2 塁";
    } else if (this.firstPosition == 0 && this.secondPosition == 0 && this.thirdPosition == 1) {
      return "ランナー 3 塁";
    } else if (this.firstPosition == 1 && this.secondPosition == 1 && this.thirdPosition == 0) {
      return "ランナー 1, 2 塁";
    } else if (this.firstPosition == 1 && this.secondPosition == 0 && this.thirdPosition == 1) {
      return "ランナー 1, 3 塁";
    } else if (this.firstPosition == 0 && this.secondPosition == 1 && this.thirdPosition == 1) {
      return "ランナー 2, 3 塁";
    } else {
      return "満塁";
    }
  }

  public int getRunnerNumber() {
    return this.firstPosition + this.secondPosition + this.thirdPosition;
  }

}
public class Team implements Comparable<Team> {

  private String name;
  private int score = 0;
  private int currentBattingOrder = 1;
  private int outCount = 0;
  private int firstPosition = 0;  // 0 = No one on the first base, 1 = A player the first base.
  private int secondPosition = 0;   // 0 = No one on the second base, 1 = A player the second base.
  private int thirdPosition = 0;   // 0 = No one on the third base, 1 = A player the third base.
  private int pitcherChanged = 0;  // 0 = Able to change pitcher, 1 = Not able to change pitcher.
  private int numberOfWin = 0;
  private int numberOfLose = 0;
  private int numberOfDraw = 0;
  private double winningPercentage = 0;
  private double gameDistance = 0;
  private int kouryuuFlag = 0;

  // 交流戦用
  private int numberOfWinKou = 0;
  private int numberOfLoseKou = 0;
  private int numberOfDrawKou = 0;
  private double winningPercentageKou = 0;
  private double gameDistanceKou = 0;

  public Player[] player = new Player[9];

  // constructor

  Team(String name) {
    this.name = name;
    for (int i = 0; i < 9; i++) {
      player[i] = new Player(i);
    }
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
  public int getPitcherChanged() {
    return this.pitcherChanged;
  }
  public int getNumberOfWin() {
    return this.numberOfWin;
  }
  public int getNumberOfLose() {
    return this.numberOfLose;
  }
  public int getNumberOfDraw() {
    return this.numberOfDraw;
  }
  public double getWinningPercentage() {
    return this.winningPercentage;
  }
  public double getGameDistance() {
    return this.gameDistance;
  }
  public int getNumberOfWinKou() {
    return this.numberOfWinKou;
  }
  public int getNumberOfLoseKou() {
    return this.numberOfLoseKou;
  }
  public int getNumberOfDrawKou() {
    return this.numberOfDrawKou;
  }
  public double getWinningPercentageKou() {
    return this.winningPercentageKou;
  }
  public double getGameDistanceKou() {
    return this.gameDistanceKou;
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
  public void setPitcherChanged(int pitcherChanged) {
    this.pitcherChanged = pitcherChanged;
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

  public void setNumberOfWin() {
    this.numberOfWin++;
  }
  public void setNumberOfWinKou() {
    this.numberOfWinKou++;
  }

  public void setNumberOfLose() {
    this.numberOfLose++;
  }
  public void setNumberOfLoseKou() {
    this.numberOfLoseKou++;
  }

  public void setNumberOfDraw() {
    this.numberOfDraw++;
  }
  public void setNumberOfDrawKou() {
    this.numberOfDrawKou++;
  }

  public void setWinningPercentage() {
    this.winningPercentage = (double)this.numberOfWin / (this.numberOfWin + this.numberOfLose + this.numberOfDraw);
  }
  public void setWinningPercentageKou() {
    this.winningPercentageKou = (double)this.numberOfWinKou / (this.numberOfWinKou + this.numberOfLoseKou + this.numberOfDrawKou);
  }

  public void setGameDistance(Team team) {
    this.gameDistance = ((team.getNumberOfWin() - team.getNumberOfLose()) - (double)(this.numberOfWin - this.numberOfLose)) / 2;
  }
  public void setGameDistanceKou(Team team) {
    this.gameDistanceKou = ((team.getNumberOfWinKou() - team.getNumberOfLoseKou()) - (double)(this.numberOfWinKou - this.numberOfLoseKou)) / 2;
  }

  public void setGameDistanceZero() {
    this.gameDistance = 0;
  }
  public void setGameDistanceZeroKou() {
    this.gameDistanceKou = 0;
  }

  public void setKouryuuFlag() {
    this.kouryuuFlag = 1;
  }
  public void unsetKouryuuFlag() {
    this.kouryuuFlag = 0;
  }

  public void initialize() {
    this.score = 0;
    this.currentBattingOrder = 1;
    this.outCount = 0;
    this.firstPosition = 0;
    this.secondPosition = 0;
    this.thirdPosition = 0;
    this.pitcherChanged = 0;
  }

  @Override
  public int compareTo(Team team) {
    if (this.kouryuuFlag == 0) {
      return (int)(team.getWinningPercentage() * 100000) - (int)(this.winningPercentage * 100000);
    } else {
      return (int)(team.getWinningPercentageKou() * 100000) - (int)(this.winningPercentageKou * 100000);
    }
  }

}
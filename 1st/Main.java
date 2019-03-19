public class Main {

  public static void main(String[] args) {

    int inning = 1;
    int currentBattingOrder1 = 1;
    int currentBattingOrder2 = 1;
    int outCount1 = 0;
    int outCount2 = 0;
    int runnerNumber1 = 0;
    int runnerNumber2 = 0;

    Team team1 = new Team("Swallows");
    Team team2 = new Team("Tigars");
    
    Player[] player1 = new Player[9];
    Player[] player2 = new Player[9];

    for (int i = 0; i < 9; i++) {
      player1[i] = new Player(team1, i);
    }
    for (int i = 0; i < 9; i++) {
      player2[i] = new Player(team2, i);
    }

    System.out.println(team1.getName() + " vs " + team2.getName() + "、先行は" + team1.getName() + "でプレイボール!!!\n");

    while (inning < 10) {

      // Bat first team
      System.out.println("=============================\n");
      System.out.println(inning + "回表の攻撃、開始です！\n");
      outCount1 = 0;
      runnerNumber1 = 0;

      while (true) {
        System.out.println("打順は" + currentBattingOrder1 + "番です。");
        if (player1[currentBattingOrder1-1].batting() > 0) {
          runnerNumber1++;
          System.out.println(team1.getName() + "、ヒット！");
          if (runnerNumber1 > 3) {
            team1.setScore(1);
            System.out.println(team1.getName() + "、1点追加！。合計" + team1.getScore() + "点です！");
          }
        } else {
          outCount1++;
          System.out.println(team1.getName() + "、アウト！　" + outCount1 + "アウトです！");
        }

        currentBattingOrder1++;
        if (currentBattingOrder1 > 9) currentBattingOrder1 = 1;

        if (outCount1 > 2) {
          System.out.println(inning + "回表の攻撃、終了です！ " + team1.getScore() + " (" + team1.getName() + ") 対 " + team2.getScore() + "(" + team2.getName() + ") です！\n");
          System.out.println("---------------------------\n");
          break;
        }
      }

      // Field first team
      System.out.println(inning + "回裏の攻撃、開始です！\n");
      outCount2 = 0;
      runnerNumber2 = 0;

      while (true) {
        System.out.println("打順は" + currentBattingOrder2 + "番です。");
        if (player2[currentBattingOrder2-1].batting() > 0) {
          runnerNumber2++;
          System.out.println(team2.getName() + "、ヒット！");
          if (runnerNumber2 > 3) {
            team2.setScore(1);
            System.out.println(team2.getName() + "、1点追加！。合計" + team2.getScore() + "点です！");
          }
        } else {
          outCount2++;
          System.out.println(team2.getName() + "、アウト！　" + outCount2 + "アウトです！");
        }

        currentBattingOrder2++;
        if (currentBattingOrder2 > 9) currentBattingOrder2 = 1;

        if (outCount2 > 2) {
          System.out.println(inning + "回裏の攻撃、終了です！ " + team1.getScore() + " (" + team1.getName() + ") 対 " + team2.getScore() + "(" + team2.getName() + ") です！\n");
          break;
        }
      }
      
      inning++;

    }

  }


}
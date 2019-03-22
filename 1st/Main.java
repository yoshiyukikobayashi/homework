public class Main {

  public static void batting(Player player, Team team) {

    int battingResult = player.batting();

    switch (battingResult) {

      case 0:
      case 1:
      case 2:
      case 3:
      case 4:
      case 5:
      case 6:
      case 7:
      case 8:
        team.setOutCount(team.getOutCount() + 1);
        System.out.println(team.getName() + "、アウト！　" + team.getOutCount() + "アウトです！");
        break;

      case 9:
      case 10:
      case 11:
        System.out.println(team.getName() + "、ヒット！");
        if (team.getThirdPosition() != 0) {
          team.setScore(team.getScore() + 1);
          team.setThirdPosition(0);
          System.out.println("1点追加です！合計" + team.getScore() + "点です。");
        } else if (team.getSecondPosition() != 0) {
          team.setThirdPosition(1);
          team.setSecondPosition(0);
        } else if (team.getFirstPosition() != 0) {
            team.setSecondPosition(1);
            team.setFirstPosition(0);
        }
        team.setFirstPosition(1);
        System.out.println("現在、" + team.returnRunners() + "です。");
        break;

      case 12:
      case 13:
        System.out.println(team.getName() + "、2ベースヒット！");
        if (team.getThirdPosition() != 0 && team.getSecondPosition() != 0) {
          team.setScore(team.getScore() + 2);
          team.setThirdPosition(0);
          team.setSecondPosition(0);
          System.out.println("2点追加です！合計" + team.getScore() + "点です。");
        } else if (team.getThirdPosition() != 0) {
          team.setScore(team.getScore() + 1);
          team.setThirdPosition(0);
          System.out.println("1点追加です！合計" + team.getScore() + "点です。");
        }else if (team.getSecondPosition() != 0) {
          team.setScore(team.getScore() + 1);
          team.setSecondPosition(0);
          System.out.println("1点追加です！合計" + team.getScore() + "点です。");
        } else if (team.getFirstPosition() != 0) {
            team.setThirdPosition(1);
            team.setFirstPosition(0);
        }
        team.setSecondPosition(1);
        System.out.println("現在、" + team.returnRunners() + "です。");
        break;

      case 14:
        System.out.println(team.getName() + "、3ベースヒット！");
        if (team.getThirdPosition() != 0 && team.getSecondPosition() != 0 && team.getThirdPosition() != 0) {
          team.setScore(team.getScore() + 3);
          team.setThirdPosition(0);
          team.setSecondPosition(0);
          team.setThirdPosition(0);
          System.out.println("3点追加です！合計" + team.getScore() + "点です。");
        } else if (team.getThirdPosition() != 0 && team.getSecondPosition() != 0) {
          team.setScore(team.getScore() + 2);
          team.setThirdPosition(0);
          team.setSecondPosition(0);
          System.out.println("2点追加です！合計" + team.getScore() + "点です。");
        }else if (team.getThirdPosition() != 0 && team.getFirstPosition() != 0) {
          team.setScore(team.getScore() + 2);
          team.setThirdPosition(0);
          team.setFirstPosition(0);
          System.out.println("2点追加です！合計" + team.getScore() + "点です。");
        } else if (team.getSecondPosition() != 0 && team.getFirstPosition() != 0) {
          team.setScore(team.getScore() + 2);
          team.setSecondPosition(0);
          team.setFirstPosition(0);
          System.out.println("2点追加です！合計" + team.getScore() + "点です。");
        }else if (team.getThirdPosition() != 0) {
          team.setScore(team.getScore() + 1);
          team.setThirdPosition(0);
          System.out.println("1点追加です！合計" + team.getScore() + "点です。");
        }else if (team.getSecondPosition() != 0) {
          team.setScore(team.getScore() + 1);
          team.setSecondPosition(0);
          System.out.println("1点追加です！合計" + team.getScore() + "点です。");
        } else if (team.getFirstPosition() != 0) {
          team.setScore(team.getScore() + 1);
          team.setFirstPosition(0);
          System.out.println("1点追加です！合計" + team.getScore() + "点です。");
        }
        team.setThirdPosition(1);
        System.out.println("現在、" + team.returnRunners() + "です。");
        break;

      case 15:
        System.out.println(team.getName() + "、ホームラン！！！");
        int total = 1 + team.getRunnerNumber();
        team.setScore(team.getScore() + total);
        System.out.println(total + "点追加です！合計" + team.getScore() + "点です。");
        team.setThirdPosition(0);
        team.setSecondPosition(0);
        team.setFirstPosition(0);
        break;
    }
  }

  public static void main(String[] args) {

    int inning = 1;
    
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
      System.out.println(inning + "回表の" + team1.getName() +"の攻撃、開始です！");
      
      while (true) {
        System.out.println("\n打順は" + team1.getCurrentBattingOrder() + "番です。");

        batting(player1[team1.getCurrentBattingOrder() - 1], team1);

        team1.setCurrentBattingOrder(team1.getCurrentBattingOrder() + 1);
        if (team1.getCurrentBattingOrder() > 9) team1.setCurrentBattingOrder(1);

        if (team1.getOutCount() > 2) {
          System.out.println("\n" + inning + "回表の攻撃、終了です！ " + team1.getScore() + " (" + team1.getName() + ") 対 " + team2.getScore() + "(" + team2.getName() + ") です！\n");
          team1.setOutCount(0);
          team1.setThirdPosition(0);
          team1.setSecondPosition(0);
          team1.setFirstPosition(0);
          break;
        }
      }

      if (inning == 9 && team1.getScore() < team2.getScore()) {
        System.out.println("試合終了！ " + team1.getScore() + " 対 " + team2.getScore() + " で " + team2.getName() +"の勝利です！！！\n");
        break;
      }

      System.out.println("---------------------------\n");

      // Field first team

      System.out.println(inning + "回裏の" + team2.getName() +"の攻撃、開始です！\n");

      while (true) {
        System.out.println("\n打順は" + team2.getCurrentBattingOrder() + "番です。");

        batting(player1[team2.getCurrentBattingOrder() - 1], team2);

        team2.setCurrentBattingOrder(team2.getCurrentBattingOrder() + 1);
        if (team2.getCurrentBattingOrder() > 9) team2.setCurrentBattingOrder(1);

        if (team2.getOutCount() > 2) {
          System.out.println("\n" + inning + "回裏の攻撃、終了です！ " + team1.getScore() + " (" + team2.getName() + ") 対 " + team2.getScore() + "(" + team2.getName() + ") です！\n");
          team2.setOutCount(0);
          team2.setThirdPosition(0);
          team2.setSecondPosition(0);
          team2.setFirstPosition(0);
          break;
        }
      }
      
      if (inning == 9 && team1.getScore() == team2.getScore()) {
        System.out.println("試合終了！ " + team1.getScore() + " 対 " + team2.getScore() + " で引き分けです！\n");
      } else if (inning == 9 && team1.getScore() > team2.getScore()) {
        System.out.println("試合終了！ " + team1.getScore() + " 対 " + team2.getScore() + " で " + team1.getName() +"の勝利です！！！\n");
      }

      inning++;

    }

  }


}
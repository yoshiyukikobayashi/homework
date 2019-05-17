package com.example.baseball.web;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
//import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

  public static void updateOrderToDb(Team[] team) throws Exception {

	  String sql = "update seleague set numberofwin=?, numberoflose=?, numberofdraw=?, winningpercentage=?, rank=? where name = ?;";
	  try (
              Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/penant", // "jdbc:postgresql://<場所>:<ポート>/<データベース名>"
                      "postgres", //user
                      "password"); //password;
              Statement statement = connection.createStatement();
			  PreparedStatement ps = connection.prepareStatement(sql);
              ){
		  	  for (int i = 0;i < 6;i++) {
		          ps.setString(6, team[i].getName());
		          ps.setInt(1, team[i].getNumberOfWin());
		          ps.setInt(2, team[i].getNumberOfLose());
		          ps.setInt(3, team[i].getNumberOfDraw());
		          ps.setDouble(4, team[i].getWinningPercentage());
		          ps.setInt(5, team[i].getOrder());
		          ps.execute();
		  	  }
	  	}
  }

  public static void saveOrderToDb(Team[] team) throws Exception {

	  String sql = "insert into seleague(id,name,numberofwin,numberoflose,numberofdraw,winningpercentage,rank)values(?,?,?,?,?,?,?);";
	  try (
              Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/penant", // "jdbc:postgresql://<場所>:<ポート>/<データベース名>"
                      "postgres", //user
                      "password"); //password;
              Statement statement = connection.createStatement();
			  PreparedStatement ps = connection.prepareStatement(sql);
              ){
		  	  for (int i = 0;i < 6;i++) {
		  		  ps.setInt(1, i+1);
		          ps.setString(2, team[i].getName());
		          ps.setInt(3, team[i].getNumberOfWin());
		          ps.setInt(4, team[i].getNumberOfLose());
		          ps.setInt(5, team[i].getNumberOfDraw());
		          ps.setDouble(6, team[i].getWinningPercentage());
		          ps.setInt(7, team[i].getOrder());
		          ps.execute();
		  	  }
	  	}
  }

  public static int batting(Player player, Team teamAttak, Team teamDefense, int inning, int uraFlag) {

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
      case 16:
      case 17:
      case 18:
      case 19:
      case 20:
        teamAttak.setOutCount(teamAttak.getOutCount() + 1);
        System.out.println(teamAttak.getName() + "、アウト！　" + teamAttak.getOutCount() + "アウトです！");
        return 0;

      case 9:
      case 10:
      case 11:
        System.out.println(teamAttak.getName() + "、ヒット！");
        if (teamAttak.getThirdPosition() != 0) {
          teamAttak.setScore(teamAttak.getScore() + 1);
          teamAttak.setThirdPosition(0);
          System.out.println("1点追加です！合計" + teamAttak.getScore() + "点です。");
          if (inning >= 9 && uraFlag == 1 && teamAttak.getScore() > teamDefense.getScore()) {
            System.out.println(teamAttak.getName() + "、サヨナラ！！！\n");
            return 1;
          }
        } else if (teamAttak.getSecondPosition() != 0) {
          teamAttak.setThirdPosition(1);
          teamAttak.setSecondPosition(0);
        } else if (teamAttak.getFirstPosition() != 0) {
            teamAttak.setSecondPosition(1);
            teamAttak.setFirstPosition(0);
        }
        teamAttak.setFirstPosition(1);
        System.out.println("現在、" + teamAttak.returnRunners() + "です。");
        return 0;

      case 12:
      case 13:
        System.out.println(teamAttak.getName() + "、2ベースヒット！");
        if (teamAttak.getThirdPosition() != 0 && teamAttak.getSecondPosition() != 0) {
          teamAttak.setScore(teamAttak.getScore() + 2);
          teamAttak.setThirdPosition(0);
          teamAttak.setSecondPosition(0);
          System.out.println("2点追加です！合計" + teamAttak.getScore() + "点です。");
          if (inning >= 9 && uraFlag == 1 && teamAttak.getScore() > teamDefense.getScore()) {
            System.out.println(teamAttak.getName() + "、サヨナラ！！！\n");
            return 1;
          }
        } else if (teamAttak.getThirdPosition() != 0) {
          teamAttak.setScore(teamAttak.getScore() + 1);
          teamAttak.setThirdPosition(0);
          System.out.println("1点追加です！合計" + teamAttak.getScore() + "点です。");
          if (inning >= 9 && uraFlag == 1 && teamAttak.getScore() > teamDefense.getScore()) {
            System.out.println(teamAttak.getName() + "、サヨナラ！！！\n");
            return 1;
          }
        } else if (teamAttak.getSecondPosition() != 0) {
          teamAttak.setScore(teamAttak.getScore() + 1);
          teamAttak.setSecondPosition(0);
          System.out.println("1点追加です！合計" + teamAttak.getScore() + "点です。");
          if (inning >= 9 && uraFlag == 1 && teamAttak.getScore() > teamDefense.getScore()) {
            System.out.println(teamAttak.getName() + "、サヨナラ！！！\n");
            return 1;
          }
        } else if (teamAttak.getFirstPosition() != 0) {
            teamAttak.setThirdPosition(1);
            teamAttak.setFirstPosition(0);
        }
        teamAttak.setSecondPosition(1);
        System.out.println("現在、" + teamAttak.returnRunners() + "です。");
        return 0;

      case 14:
        System.out.println(teamAttak.getName() + "、3ベースヒット！");
        if (teamAttak.getThirdPosition() != 0 && teamAttak.getSecondPosition() != 0 && teamAttak.getThirdPosition() != 0) {
          teamAttak.setScore(teamAttak.getScore() + 3);
          teamAttak.setThirdPosition(0);
          teamAttak.setSecondPosition(0);
          teamAttak.setThirdPosition(0);
          System.out.println("3点追加です！合計" + teamAttak.getScore() + "点です。");
          if (inning >= 9 && uraFlag == 1 && teamAttak.getScore() > teamDefense.getScore()) {
            System.out.println(teamAttak.getName() + "、サヨナラ！！！\n");
            return 1;
          }
        } else if (teamAttak.getThirdPosition() != 0 && teamAttak.getSecondPosition() != 0) {
          teamAttak.setScore(teamAttak.getScore() + 2);
          teamAttak.setThirdPosition(0);
          teamAttak.setSecondPosition(0);
          System.out.println("2点追加です！合計" + teamAttak.getScore() + "点です。");
          if (inning >= 9 && uraFlag == 1 && teamAttak.getScore() > teamDefense.getScore()) {
            System.out.println(teamAttak.getName() + "、サヨナラ！！！\n");
            return 1;
          }
        } else if (teamAttak.getThirdPosition() != 0 && teamAttak.getFirstPosition() != 0) {
          teamAttak.setScore(teamAttak.getScore() + 2);
          teamAttak.setThirdPosition(0);
          teamAttak.setFirstPosition(0);
          System.out.println("2点追加です！合計" + teamAttak.getScore() + "点です。");
          if (inning >= 9 && uraFlag == 1 && teamAttak.getScore() > teamDefense.getScore()) {
            System.out.println(teamAttak.getName() + "、サヨナラ！！！\n");
            return 1;
          }
        } else if (teamAttak.getSecondPosition() != 0 && teamAttak.getFirstPosition() != 0) {
          teamAttak.setScore(teamAttak.getScore() + 2);
          teamAttak.setSecondPosition(0);
          teamAttak.setFirstPosition(0);
          System.out.println("2点追加です！合計" + teamAttak.getScore() + "点です。");
          if (inning >= 9 && uraFlag == 1 && teamAttak.getScore() > teamDefense.getScore()) {
            System.out.println(teamAttak.getName() + "、サヨナラ！！！\n");
            return 1;
          }
        } else if (teamAttak.getThirdPosition() != 0) {
          teamAttak.setScore(teamAttak.getScore() + 1);
          teamAttak.setThirdPosition(0);
          System.out.println("1点追加です！合計" + teamAttak.getScore() + "点です。");
          if (inning >= 9 && uraFlag == 1 && teamAttak.getScore() > teamDefense.getScore()) {
            System.out.println(teamAttak.getName() + "、サヨナラ！！！\n");
            return 1;
          }
        } else if (teamAttak.getSecondPosition() != 0) {
          teamAttak.setScore(teamAttak.getScore() + 1);
          teamAttak.setSecondPosition(0);
          System.out.println("1点追加です！合計" + teamAttak.getScore() + "点です。");
          if (inning >= 9 && uraFlag == 1 && teamAttak.getScore() > teamDefense.getScore()) {
            System.out.println(teamAttak.getName() + "、サヨナラ！！！\n");
            return 1;
          }
        } else if (teamAttak.getFirstPosition() != 0) {
          teamAttak.setScore(teamAttak.getScore() + 1);
          teamAttak.setFirstPosition(0);
          System.out.println("1点追加です！合計" + teamAttak.getScore() + "点です。");
          if (inning >= 9 && uraFlag == 1 && teamAttak.getScore() > teamDefense.getScore()) {
            System.out.println(teamAttak.getName() + "、サヨナラ！！！\n");
            return 1;
          }
        }
        teamAttak.setThirdPosition(1);
        System.out.println("現在、" + teamAttak.returnRunners() + "です。");
        return 0;

      case 15:
        System.out.println(teamAttak.getName() + "、ホームラン！！！");
        int total = 1 + teamAttak.getRunnerNumber();
        teamAttak.setScore(teamAttak.getScore() + total);
        System.out.println(total + "点追加です！合計" + teamAttak.getScore() + "点です。");
        if (inning >= 9 && uraFlag == 1 && teamAttak.getScore() > teamDefense.getScore()) {
          System.out.println(teamAttak.getName() + "、サヨナラ！！！\n");
          return 1;
        }
        teamAttak.setThirdPosition(0);
        teamAttak.setSecondPosition(0);
        teamAttak.setFirstPosition(0);
        return 0;
    }
    return 0;
  }

  public static int playGame(Team team1, Team team2, int playOffFlag, int kouryuuFlag) {

    int inning = 1;
    int uraFlag = 0; // 0 = 表, 1 = 裏
    int sayonaraFlag = 0; // 0 = サヨナラではない、1 = サヨナラ

    System.out.println(team1.getName() + " vs " + team2.getName() + "、先行は" + team1.getName() + "でプレイボール!!!\n");

    while (inning < 13) {

      // Bat first team
      System.out.println("=============================\n");
      uraFlag = 0;
      System.out.println(inning + "回表の" + team1.getName() +"の攻撃、開始です！");

      while (true) {
        System.out.println("\n打順は" + team1.getCurrentBattingOrder() + "番です。");

        batting(team1.player[team1.getCurrentBattingOrder() - 1], team1, team2, inning, uraFlag);

        if (team1.getScore() >= 5 && team2.getPitcherChanged() == 0) {
          System.out.println(team2.getName() +"、ここでピッチャー交代！");
          team2.setPitcherChanged(1);
        }

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

      if (inning >= 9 && team1.getScore() < team2.getScore()) {
        System.out.println("試合終了！ " + team1.getScore() + " 対 " + team2.getScore() + " で " + team2.getName() +"の勝利です！！！\n");
        if (playOffFlag == 1) {
          return 1;
        }
        if (kouryuuFlag == 1) {
          team1.setNumberOfLoseKou();
          team2.setNumberOfWinKou();
          team1.setWinningPercentageKou();
          team2.setWinningPercentageKou();
        }
        team1.setNumberOfLose();
        team2.setNumberOfWin();
        team1.setWinningPercentage();
        team2.setWinningPercentage();
        team1.initialize();
        team2.initialize();
        break;
      }

      System.out.println("---------------------------\n");

      // Field first team

      uraFlag = 1;
      System.out.println(inning + "回裏の" + team2.getName() +"の攻撃、開始です！\n");

      while (true) {
        System.out.println("\n打順は" + team2.getCurrentBattingOrder() + "番です。");

        sayonaraFlag = batting(team2.player[team2.getCurrentBattingOrder() - 1], team2, team1, inning, uraFlag);
        if (sayonaraFlag == 1) break;

        if (team2.getScore() >= 5 && team1.getPitcherChanged() == 0) {
          System.out.println(team1.getName() +"、ここでピッチャー交代！");
          team1.setPitcherChanged(1);
        }

        team2.setCurrentBattingOrder(team2.getCurrentBattingOrder() + 1);
        if (team2.getCurrentBattingOrder() > 9) team2.setCurrentBattingOrder(1);

        if (team2.getOutCount() > 2) {
          System.out.println("\n" + inning + "回裏の攻撃、終了です！ " + team1.getScore() + " (" + team1.getName() + ") 対 " + team2.getScore() + "(" + team2.getName() + ") です！\n");
          team2.setOutCount(0);
          team2.setThirdPosition(0);
          team2.setSecondPosition(0);
          team2.setFirstPosition(0);
          break;
        }
      }

      if (inning >= 9 && team1.getScore() == team2.getScore()) {
        if (inning <= 11) {
          System.out.println(inning + "回でも決着が付きませんでした。延長です！\n");
          inning++;
          continue;
        }
        System.out.println("試合終了！ " + team1.getScore() + " 対 " + team2.getScore() + " で引き分けです！\n");
        if (playOffFlag == 1) {
          return 2;
        }
        if (kouryuuFlag == 1) {
          team1.setNumberOfDrawKou();
          team2.setNumberOfDrawKou();
          team1.setWinningPercentageKou();
          team2.setWinningPercentageKou();
        }
        team1.setNumberOfDraw();
        team2.setNumberOfDraw();
        team1.setWinningPercentage();
        team2.setWinningPercentage();
        team1.initialize();
        team2.initialize();
        break;
      } else if (inning >= 9 && team1.getScore() > team2.getScore()) {
        System.out.println("試合終了！ " + team1.getScore() + " 対 " + team2.getScore() + " で " + team1.getName() +"の勝利です！！！\n");
        if (playOffFlag == 1) {
          return 0;
        }
        if (kouryuuFlag == 1) {
          team1.setNumberOfWinKou();
          team2.setNumberOfLoseKou();
          team1.setWinningPercentageKou();
          team2.setWinningPercentageKou();
        }
        team1.setNumberOfWin();
        team2.setNumberOfLose();
        team1.setWinningPercentage();
        team2.setWinningPercentage();
        team1.initialize();
        team2.initialize();
        break;
      } else if (inning >= 9 && team1.getScore() < team2.getScore()) {
        System.out.println("試合終了！ " + team1.getScore() + " 対 " + team2.getScore() + " で " + team2.getName() +"の勝利です！！！\n");
        if (playOffFlag == 1) {
          return 1;
        }
        if (kouryuuFlag == 1) {
          team1.setNumberOfLoseKou();
          team2.setNumberOfWinKou();
          team1.setWinningPercentageKou();
          team2.setWinningPercentageKou();
        }
        team1.setNumberOfLose();
        team2.setNumberOfWin();
        team1.setWinningPercentage();
        team2.setWinningPercentage();
        team1.initialize();
        team2.initialize();
        break;
      }

      inning++;

    }
    return 3;
  }

  public static void showTeamScores(Team[] team, int day, int spFlag, int kouryuuFlag) throws Exception {  // spFlag == 0 はセリーグ、spFlag == 6 はパリーグ
    ArrayList<Team> order = new ArrayList<Team>();                                        // kouryuuFlag == 0 は通常試合、kouryuuFlag == 1 は交流戦
    if (kouryuuFlag == 0) {
      for (int i = 0 + spFlag; i < 6 + spFlag; i++) {
        order.add(team[i]);
      }
      Collections.sort(order);
      order.get(0).setGameDistanceZero();
      order.get(0).setOrder(1);
      order.get(1).setGameDistance(order.get(0));
      order.get(1).setOrder(2);
      order.get(2).setGameDistance(order.get(1));
      order.get(2).setOrder(3);
      order.get(3).setGameDistance(order.get(2));
      order.get(3).setOrder(4);
      order.get(4).setGameDistance(order.get(3));
      order.get(4).setOrder(5);
      order.get(5).setGameDistance(order.get(4));
      order.get(5).setOrder(6);
    } else {
      for (int i = 0; i < 12; i++) {
        team[i].setKouryuuFlag();
        order.add(team[i]);
      }
      Collections.sort(order);
      order.get(0).setGameDistanceZeroKou();
      order.get(1).setGameDistanceKou(order.get(0));
      order.get(2).setGameDistanceKou(order.get(1));
      order.get(3).setGameDistanceKou(order.get(2));
      order.get(4).setGameDistanceKou(order.get(3));
      order.get(5).setGameDistanceKou(order.get(4));
      order.get(6).setGameDistanceKou(order.get(5));
      order.get(7).setGameDistanceKou(order.get(6));
      order.get(8).setGameDistanceKou(order.get(7));
      order.get(9).setGameDistanceKou(order.get(8));
      order.get(10).setGameDistanceKou(order.get(9));
      order.get(11).setGameDistanceKou(order.get(10));
      for (int i = 0; i < 12; i++) {
        team[i].unsetKouryuuFlag();
      }
    }

    if (kouryuuFlag == 1) {
      if (day == 140 && order.get(0).getWinningPercentageKou() == order.get(1).getWinningPercentageKou()) {
        System.out.println("\n交流戦の結果です！！！　優勝は、同率首位で、" + order.get(0).getName() + "と" + order.get(1).getName() + "です！\n");
        System.out.println("★★★★成績発表★★★★");
        System.out.println("順位　チーム名　勝利数　負け数　引き分け数　勝率　ゲーム差");
        System.out.println("１位　" + order.get(0).getName() + "　　" + order.get(0).getNumberOfWinKou() + "　　　" + order.get(0).getNumberOfLoseKou() + "　　　　" + order.get(0).getNumberOfDrawKou() + "　　　" + String.format("%.3f", order.get(0).getWinningPercentageKou()) + "　　" + order.get(0).getGameDistanceKou());
        System.out.println("１位　" + order.get(1).getName() + "　　" + order.get(1).getNumberOfWinKou() + "　　　" + order.get(1).getNumberOfLoseKou() + "　　　　" + order.get(1).getNumberOfDrawKou() + "　　　" + String.format("%.3f", order.get(1).getWinningPercentageKou()) + "　　" + order.get(1).getGameDistanceKou());
      } else {
        System.out.println("\n交流戦の結果です！！！　優勝は、" + order.get(0).getName() + "です！\n");
        System.out.println("★★★★成績発表★★★★");
        System.out.println("順位　チーム名　勝利数　負け数　引き分け数　勝率　ゲーム差");
        System.out.println("１位　" + order.get(0).getName() + "　　" + order.get(0).getNumberOfWinKou() + "　　　" + order.get(0).getNumberOfLoseKou() + "　　　　" + order.get(0).getNumberOfDrawKou() + "　　　" + String.format("%.3f", order.get(0).getWinningPercentageKou()) + "　　" + order.get(0).getGameDistanceKou());
        System.out.println("２位　" + order.get(1).getName() + "　　" + order.get(1).getNumberOfWinKou() + "　　　" + order.get(1).getNumberOfLoseKou() + "　　　　" + order.get(1).getNumberOfDrawKou() + "　　　" + String.format("%.3f", order.get(1).getWinningPercentageKou()) + "　　" + order.get(1).getGameDistanceKou());
      }
      System.out.println("３位　" + order.get(2).getName() + "　　" + order.get(2).getNumberOfWinKou() + "　　　" + order.get(2).getNumberOfLoseKou() + "　　　　" + order.get(2).getNumberOfDrawKou() + "　　　" + String.format("%.3f", order.get(2).getWinningPercentageKou()) + "　　" + order.get(2).getGameDistanceKou());
      System.out.println("４位　" + order.get(3).getName() + "　　" + order.get(3).getNumberOfWinKou() + "　　　" + order.get(3).getNumberOfLoseKou() + "　　　　" + order.get(3).getNumberOfDrawKou() + "　　　" + String.format("%.3f", order.get(3).getWinningPercentageKou()) + "　　" + order.get(3).getGameDistanceKou());
      System.out.println("５位　" + order.get(4).getName() + "　　" + order.get(4).getNumberOfWinKou() + "　　　" + order.get(4).getNumberOfLoseKou() + "　　　　" + order.get(4).getNumberOfDrawKou() + "　　　" + String.format("%.3f", order.get(4).getWinningPercentageKou()) + "　　" + order.get(4).getGameDistanceKou());
      System.out.println("６位　" + order.get(5).getName() + "　　" + order.get(5).getNumberOfWinKou() + "　　　" + order.get(5).getNumberOfLoseKou() + "　　　　" + order.get(5).getNumberOfDrawKou() + "　　　" + String.format("%.3f", order.get(5).getWinningPercentageKou()) + "　　" + order.get(5).getGameDistanceKou());
      System.out.println("７位　" + order.get(6).getName() + "　　" + order.get(6).getNumberOfWinKou() + "　　　" + order.get(6).getNumberOfLoseKou() + "　　　　" + order.get(6).getNumberOfDrawKou() + "　　　" + String.format("%.3f", order.get(6).getWinningPercentageKou()) + "　　" + order.get(6).getGameDistanceKou());
      System.out.println("８位　" + order.get(7).getName() + "　　" + order.get(7).getNumberOfWinKou() + "　　　" + order.get(7).getNumberOfLoseKou() + "　　　　" + order.get(7).getNumberOfDrawKou() + "　　　" + String.format("%.3f", order.get(7).getWinningPercentageKou()) + "　　" + order.get(7).getGameDistanceKou());
      System.out.println("９位　" + order.get(8).getName() + "　　" + order.get(8).getNumberOfWinKou() + "　　　" + order.get(8).getNumberOfLoseKou() + "　　　　" + order.get(8).getNumberOfDrawKou() + "　　　" + String.format("%.3f", order.get(8).getWinningPercentageKou()) + "　　" + order.get(8).getGameDistanceKou());
      System.out.println("10位　" + order.get(9).getName() + "　　" + order.get(9).getNumberOfWinKou() + "　　　" + order.get(9).getNumberOfLoseKou() + "　　　　" + order.get(9).getNumberOfDrawKou() + "　　　" + String.format("%.3f", order.get(9).getWinningPercentageKou()) + "　　" + order.get(9).getGameDistanceKou());
      System.out.println("11位　" + order.get(10).getName() + "　　" + order.get(10).getNumberOfWinKou() + "　　　" + order.get(10).getNumberOfLoseKou() + "　　　　" + order.get(10).getNumberOfDrawKou() + "　　　" + String.format("%.3f", order.get(10).getWinningPercentageKou()) + "　　" + order.get(10).getGameDistanceKou());
      System.out.println("12位　" + order.get(11).getName() + "　　" + order.get(11).getNumberOfWinKou() + "　　　" + order.get(11).getNumberOfLoseKou() + "　　　　" + order.get(11).getNumberOfDrawKou() + "　　　" + String.format("%.3f", order.get(11).getWinningPercentageKou()) + "　　" + order.get(11).getGameDistanceKou());
      System.out.println("★★★★成績発表★★★★\n");
      return;
    }

    if (day == 140 && order.get(0).getWinningPercentage() == order.get(1).getWinningPercentage() && order.get(0).getNumberOfWin() == order.get(1).getNumberOfWin()) {
      System.out.println("\n全日程終了！！！　上位2チームが同率・同勝利数のため、優勝決定戦で勝利したチームが優勝とします。");
      System.out.println("それでは優勝決定戦を始めます。\n");
      int result = 0;
      while (true) {
        result = playGame(order.get(0), order.get(1), 1, 0);
        if (result != 2) break;
        System.out.println("\n引き分けのため、優勝決定戦の再試合を行います。\n");
      }
      if (result == 0) {
        System.out.println("\n優勝は、" + order.get(0).getName() + "です！\n");
      } else {
        System.out.println("\n優勝は、" + order.get(1).getName() + "です！\n");
        System.out.println("★★★★成績発表★★★★");
        System.out.println("順位　チーム名　勝利数　負け数　引き分け数　勝率　ゲーム差");
        System.out.println("１位　" + order.get(1).getName() + "　　" + order.get(1).getNumberOfWin() + "　　　" + order.get(1).getNumberOfLose() + "　　　　" + order.get(1).getNumberOfDraw() + "　　　" + String.format("%.3f", order.get(1).getWinningPercentage()) + "　　" + order.get(1).getGameDistance());
        System.out.println("２位　" + order.get(0).getName() + "　　" + order.get(0).getNumberOfWin() + "　　　" + order.get(0).getNumberOfLose() + "　　　　" + order.get(0).getNumberOfDraw() + "　　　" + String.format("%.3f", order.get(0).getWinningPercentage()) + "　　" + order.get(0).getGameDistance());
        System.out.println("３位　" + order.get(2).getName() + "　　" + order.get(2).getNumberOfWin() + "　　　" + order.get(2).getNumberOfLose() + "　　　　" + order.get(2).getNumberOfDraw() + "　　　" + String.format("%.3f", order.get(2).getWinningPercentage()) + "　　" + order.get(2).getGameDistance());
        System.out.println("４位　" + order.get(3).getName() + "　　" + order.get(3).getNumberOfWin() + "　　　" + order.get(3).getNumberOfLose() + "　　　　" + order.get(3).getNumberOfDraw() + "　　　" + String.format("%.3f", order.get(3).getWinningPercentage()) + "　　" + order.get(3).getGameDistance());
        System.out.println("５位　" + order.get(4).getName() + "　　" + order.get(4).getNumberOfWin() + "　　　" + order.get(4).getNumberOfLose() + "　　　　" + order.get(4).getNumberOfDraw() + "　　　" + String.format("%.3f", order.get(4).getWinningPercentage()) + "　　" + order.get(4).getGameDistance());
        System.out.println("６位　" + order.get(5).getName() + "　　" + order.get(5).getNumberOfWin() + "　　　" + order.get(5).getNumberOfLose() + "　　　　" + order.get(5).getNumberOfDraw() + "　　　" + String.format("%.3f", order.get(5).getWinningPercentage()) + "　　" + order.get(5).getGameDistance());
        System.out.println("★★★★成績発表★★★★\n");
        if (day == 140) updateOrderToDb(team);
        return;
      }
    } else if (day == 140 && order.get(0).getWinningPercentage() == order.get(1).getWinningPercentage()) {
      if (order.get(0).getNumberOfWin() > order.get(1).getNumberOfWin()) {
        System.out.println("\n全日程終了！！！　優勝は、同率首位ですが勝利数の上回った" + order.get(0).getName() + "です！\n");
      } else {
        System.out.println("\n全日程終了！！！　優勝は、同率首位ですが勝利数の上回った" + order.get(1).getName() + "です！\n");
        System.out.println("★★★★成績発表★★★★");
        System.out.println("順位　チーム名　勝利数　負け数　引き分け数　勝率　ゲーム差");
        System.out.println("１位　" + order.get(1).getName() + "　　" + order.get(1).getNumberOfWin() + "　　　" + order.get(1).getNumberOfLose() + "　　　　" + order.get(1).getNumberOfDraw() + "　　　" + String.format("%.3f", order.get(1).getWinningPercentage()) + "　　" + order.get(1).getGameDistance());
        System.out.println("２位　" + order.get(0).getName() + "　　" + order.get(0).getNumberOfWin() + "　　　" + order.get(0).getNumberOfLose() + "　　　　" + order.get(0).getNumberOfDraw() + "　　　" + String.format("%.3f", order.get(0).getWinningPercentage()) + "　　" + order.get(0).getGameDistance());
        System.out.println("３位　" + order.get(2).getName() + "　　" + order.get(2).getNumberOfWin() + "　　　" + order.get(2).getNumberOfLose() + "　　　　" + order.get(2).getNumberOfDraw() + "　　　" + String.format("%.3f", order.get(2).getWinningPercentage()) + "　　" + order.get(2).getGameDistance());
        System.out.println("４位　" + order.get(3).getName() + "　　" + order.get(3).getNumberOfWin() + "　　　" + order.get(3).getNumberOfLose() + "　　　　" + order.get(3).getNumberOfDraw() + "　　　" + String.format("%.3f", order.get(3).getWinningPercentage()) + "　　" + order.get(3).getGameDistance());
        System.out.println("５位　" + order.get(4).getName() + "　　" + order.get(4).getNumberOfWin() + "　　　" + order.get(4).getNumberOfLose() + "　　　　" + order.get(4).getNumberOfDraw() + "　　　" + String.format("%.3f", order.get(4).getWinningPercentage()) + "　　" + order.get(4).getGameDistance());
        System.out.println("６位　" + order.get(5).getName() + "　　" + order.get(5).getNumberOfWin() + "　　　" + order.get(5).getNumberOfLose() + "　　　　" + order.get(5).getNumberOfDraw() + "　　　" + String.format("%.3f", order.get(5).getWinningPercentage()) + "　　" + order.get(5).getGameDistance());
        System.out.println("★★★★成績発表★★★★\n");
        if (day == 140) updateOrderToDb(team);
        return;
      }
    } else if (day == 140) {
      System.out.println("\n全日程終了！！！　優勝は" + order.get(0).getName() + "です！\n");
    }
    System.out.println("★★★★成績発表★★★★");
    System.out.println("順位　チーム名　勝利数　負け数　引き分け数　勝率　ゲーム差");
    System.out.println("１位　" + order.get(0).getName() + "　　" + order.get(0).getNumberOfWin() + "　　　" + order.get(0).getNumberOfLose() + "　　　　" + order.get(0).getNumberOfDraw() + "　　　" + String.format("%.3f", order.get(0).getWinningPercentage()) + "　　" + order.get(0).getGameDistance());
    System.out.println("２位　" + order.get(1).getName() + "　　" + order.get(1).getNumberOfWin() + "　　　" + order.get(1).getNumberOfLose() + "　　　　" + order.get(1).getNumberOfDraw() + "　　　" + String.format("%.3f", order.get(1).getWinningPercentage()) + "　　" + order.get(1).getGameDistance());
    System.out.println("３位　" + order.get(2).getName() + "　　" + order.get(2).getNumberOfWin() + "　　　" + order.get(2).getNumberOfLose() + "　　　　" + order.get(2).getNumberOfDraw() + "　　　" + String.format("%.3f", order.get(2).getWinningPercentage()) + "　　" + order.get(2).getGameDistance());
    System.out.println("４位　" + order.get(3).getName() + "　　" + order.get(3).getNumberOfWin() + "　　　" + order.get(3).getNumberOfLose() + "　　　　" + order.get(3).getNumberOfDraw() + "　　　" + String.format("%.3f", order.get(3).getWinningPercentage()) + "　　" + order.get(3).getGameDistance());
    System.out.println("５位　" + order.get(4).getName() + "　　" + order.get(4).getNumberOfWin() + "　　　" + order.get(4).getNumberOfLose() + "　　　　" + order.get(4).getNumberOfDraw() + "　　　" + String.format("%.3f", order.get(4).getWinningPercentage()) + "　　" + order.get(4).getGameDistance());
    System.out.println("６位　" + order.get(5).getName() + "　　" + order.get(5).getNumberOfWin() + "　　　" + order.get(5).getNumberOfLose() + "　　　　" + order.get(5).getNumberOfDraw() + "　　　" + String.format("%.3f", order.get(5).getWinningPercentage()) + "　　" + order.get(5).getGameDistance());
    System.out.println("★★★★成績発表★★★★\n");
    if (day == 140) updateOrderToDb(team);
  }

  public void main() throws Exception {

    // 0:Swallows, 1:Tigers, 2:Giants, 3:Baystars, 4:Carp, 5:Dragons
    // 6:Softbank, 7:Fighters, 8:Lotte, 9:Lions, 10:Orix, 11:Rakuten
    Team[] team = new Team[12];

    for (int i = 0; i < 12; i++) {
      if (i == 0) team[i] = new Team("Swallows");
      if (i == 1) team[i] = new Team("Tigers  ");
      if (i == 2) team[i] = new Team("Giants  ");
      if (i == 3) team[i] = new Team("Baystars");
      if (i == 4) team[i] = new Team("Carp    ");
      if (i == 5) team[i] = new Team("Dragons ");
      if (i == 6) team[i] = new Team("Softbank");
      if (i == 7) team[i] = new Team("Fighters");
      if (i == 8) team[i] = new Team("Lotte   ");
      if (i == 9) team[i] = new Team("Lions   ");
      if (i == 10) team[i] = new Team("Orix    ");
      if (i == 11) team[i] = new Team("Rakuten ");
    }

//    saveOrderToDb(team);
    int day = 1;

    while (day < 141) {
      System.out.println("\n第" + day +"日目の試合を始めます。\n\n");
      if (day > 60 && 79 > day) {
        int dayOfKoryuu = day - 60;
        System.out.println("\n交流戦、第" + dayOfKoryuu +"日目の試合を始めます。\n\n");
        if (day % 6 == 1) {
          playGame(team[0], team[6], 0, 1);
          playGame(team[1], team[7], 0, 1);
          playGame(team[2], team[8], 0, 1);
          playGame(team[3], team[9], 0, 1);
          playGame(team[4], team[10], 0, 1);
          playGame(team[5], team[11], 0, 1);
        } else if (day % 6 == 2) {
          playGame(team[0], team[11], 0, 1);
          playGame(team[1], team[6], 0, 1);
          playGame(team[2], team[7], 0, 1);
          playGame(team[3], team[8], 0, 1);
          playGame(team[4], team[9], 0, 1);
          playGame(team[5], team[10], 0, 1);
        } else if (day % 6 == 3) {
          playGame(team[0], team[10], 0, 1);
          playGame(team[1], team[11], 0, 1);
          playGame(team[2], team[6], 0, 1);
          playGame(team[3], team[7], 0, 1);
          playGame(team[4], team[8], 0, 1);
          playGame(team[5], team[9], 0, 1);
        } else if (day % 6 == 4) {
          playGame(team[0], team[9], 0, 1);
          playGame(team[1], team[10], 0, 1);
          playGame(team[2], team[11], 0, 1);
          playGame(team[3], team[6], 0, 1);
          playGame(team[4], team[7], 0, 1);
          playGame(team[5], team[8], 0, 1);
        } else if (day % 6 == 5) {
          playGame(team[0], team[8], 0, 1);
          playGame(team[1], team[9], 0, 1);
          playGame(team[2], team[10], 0, 1);
          playGame(team[3], team[11], 0, 1);
          playGame(team[4], team[6], 0, 1);
          playGame(team[5], team[7], 0, 1);
        } else {
          playGame(team[0], team[7], 0, 1);
          playGame(team[1], team[8], 0, 1);
          playGame(team[2], team[9], 0, 1);
          playGame(team[3], team[10], 0, 1);
          playGame(team[4], team[11], 0, 1);
          playGame(team[5], team[6], 0, 1);
        }
        showTeamScores(team, day, 0, 0);
        showTeamScores(team, day, 6, 0);
        day++;
        continue;
      }
      if (day % 5 == 1) {
        playGame(team[0], team[1], 0, 0);
        playGame(team[2], team[3], 0, 0);
        playGame(team[4], team[5], 0, 0);
        playGame(team[6], team[7], 0, 0);
        playGame(team[8], team[9], 0, 0);
        playGame(team[10], team[11], 0, 0);
      } else if (day % 5 == 2) {
        playGame(team[3], team[0], 0, 0);
        playGame(team[5], team[2], 0, 0);
        playGame(team[1], team[4], 0, 0);
        playGame(team[9], team[6], 0, 0);
        playGame(team[11], team[8], 0, 0);
        playGame(team[7], team[10], 0, 0);
      } else if (day % 5 == 3) {
        playGame(team[0], team[5], 0, 0);
        playGame(team[2], team[1], 0, 0);
        playGame(team[4], team[3], 0, 0);
        playGame(team[6], team[11], 0, 0);
        playGame(team[8], team[7], 0, 0);
        playGame(team[10], team[9], 0, 0);
      } else if (day % 5 == 4) {
        playGame(team[0], team[2], 0, 0);
        playGame(team[5], team[1], 0, 0);
        playGame(team[5], team[3], 0, 0);
        playGame(team[6], team[8], 0, 0);
        playGame(team[11], team[7], 0, 0);
        playGame(team[11], team[9], 0, 0);
      } else {
        playGame(team[1], team[3], 0, 0);
        playGame(team[4], team[0], 0, 0);
        playGame(team[4], team[2], 0, 0);
        playGame(team[7], team[9], 0, 0);
        playGame(team[10], team[6], 0, 0);
        playGame(team[10], team[8], 0, 0);
      }

      if (day == 140) showTeamScores(team, day, 0, 1);
      if (day == 140) System.out.print("セリーグ、");
      showTeamScores(team, day, 0, 0);
      if (day == 140) System.out.print("パリーグ、");
      showTeamScores(team, day, 6, 0);

      day++;
    }




  }


}
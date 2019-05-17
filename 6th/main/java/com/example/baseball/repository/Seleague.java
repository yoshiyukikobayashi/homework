package com.example.baseball.repository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "seleague")
public class Seleague {

  @Id
  @Column(name="id")
//  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer id;
  @Column(name="name")
  private String name;
  @Column(name="numberofwin")
  private Integer numberofwin;
  @Column(name="numberoflose")
  private Integer numberoflose;
  @Column(name="numberofdraw")
  private Integer numberofdraw;
  @Column(name="winningpercentage")
  private Double winningpercentage;
  @Column(name="rank")
  private Integer rank;

  public Integer getId() {
    return id;
  }
  public void setId(Integer id) {
    this.id = id;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public Integer getNumberofwin() {
    return numberofwin;
  }
  public void setNumberofwin (Integer numberOfWin) {
    this.numberofwin = numberOfWin;
  }
  public Integer getNumberoflose() {
    return numberoflose;
  }
  public void setNumberoflose (Integer numberOfLose) {
    this.numberoflose = numberOfLose;
  }
  public Integer getNumberofdraw() {
    return numberofdraw;
  }
  public void setNumberofdraw (Integer numberOfDraw) {
    this.numberofdraw = numberOfDraw;
  }
  public Double getWinningpercentage() {
    return winningpercentage;
  }
  public void setWinningpercentage (Double winningPercentage) {
    this.winningpercentage = winningPercentage;
  }
  public Integer getRank() {
    return rank;
  }
  public void setRank (Integer rank) {
    this.rank = rank;
  }
}
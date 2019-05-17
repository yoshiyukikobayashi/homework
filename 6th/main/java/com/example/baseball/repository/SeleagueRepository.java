package com.example.baseball.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SeleagueRepository extends JpaRepository<Seleague, Integer> {

  @Query("select s from Seleague s order by s.rank asc")
  List<Seleague> findTeamsByRank();

}
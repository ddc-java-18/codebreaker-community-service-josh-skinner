package edu.cnm.deepdive.codebreaker.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

@Entity
@Immutable
@Subselect("SELECT * FROM game_statistics")
public class GameStatistics {


  @Id
  @Column(name ="player_id", nullable = false, updatable = false)
  @JsonIgnore
  private long id;

  @ManyToOne( optional = false, fetch = FetchType.EAGER)
  @PrimaryKeyJoinColumn(name = "player_id")
  @JsonProperty(access = Access.READ_ONLY)
  private User player;

  @Column(nullable = false, updatable = false)
  @JsonProperty(access = Access.READ_ONLY)
  private int poolSize;

  @Column(nullable = false, updatable = false)
  @JsonProperty(access = Access.READ_ONLY)
  private int codeLength;

  @Column(nullable = false, updatable = false)
  @JsonProperty(access = Access.READ_ONLY)
  private int gamesPlayed;

  @Column(nullable = false, updatable = false)
  @JsonProperty(access = Access.READ_ONLY)
  private double avgGuessCount;

  @Column(nullable = false, updatable = false)
  @JsonProperty(access = Access.READ_ONLY)
  private double avgDuration;
  public long getId() {
    return id;
  }

  public User getPlayer() {
    return player;
  }

  public int getPoolSize() {
    return poolSize;
  }

  public int getCodeLength() {
    return codeLength;
  }

  public int getGamesPlayed() {
    return gamesPlayed;
  }

  public double getAvgGuessCount() {
    return avgGuessCount;
  }

  public double getAvgDuration() {
    return avgDuration;
  }

}

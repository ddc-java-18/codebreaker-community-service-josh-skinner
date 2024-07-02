package edu.cnm.deepdive.codebreaker.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.nimbusds.jose.shaded.gson.annotations.JsonAdapter;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Size;
import java.time.Instant;
import java.util.UUID;
import org.hibernate.annotations.CreationTimestamp;

@SuppressWarnings({"JpaDataSourceORMInspection"})
@Entity
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"key", "created", "code", "correct", "close", "solution"})
public class Guess {

  @Id
  @GeneratedValue
  @Column(name = "guess_id", nullable = false, updatable = false)
  @JsonIgnore
  private Long id;

  @Column(nullable = false, updatable = false, unique = true)
  @JsonProperty(value = "key", access = Access.READ_ONLY)
  private UUID externalKey;

  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "game_id", nullable = false, updatable = false)
  @JsonIgnore
  private Game game;

  @Column(nullable = false, updatable = false, length = Game.MAX_CODE_LENGTH)
  @Size(min = Game.MIN_CODE_LENGTH, max = 2 * Game.MAX_CODE_LENGTH, message = "Guess must have same length as code.")
  private String code;

  @Column(nullable = false, updatable = false)
  @JsonProperty(access = Access.READ_ONLY)
  private int correct;

  @Column(nullable = false, updatable = false)
  @JsonProperty(access = Access.READ_ONLY)
  private int close;

  @Column(nullable = false, updatable = false)
  @Temporal(TemporalType.TIMESTAMP)
  @CreationTimestamp
  @JsonProperty(access = Access.READ_ONLY)
  private Instant created;

  public Long getId() {
    return id;
  }

  public UUID getExternalKey() {
    return externalKey;
  }

  public Game getGame() {
    return game;
  }

  public void setGame(Game game) {
    this.game = game;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public int getCorrect() {
    return correct;
  }

  public void setCorrect(int correct) {
    this.correct = correct;
  }

  public int getClose() {
    return close;
  }

  public void setClose(int close) {
    this.close = close;
  }

  public Instant getCreated() {
    return created;
  }


  public boolean isSolution() {
    return correct == getGame().getCodeLength();
  }

  @Override
  public int hashCode() {
    return (id != null) ? id.hashCode() : 0;
  }

  @Override
  public boolean equals(Object obj) {
    boolean result;
    if (obj == this) {
      result = true;
    } else if (obj instanceof Guess other) {
      result = this.id != null && this.id.equals(other.id);
    } else {
      result = false;
    }

    return result;
  }

  @PrePersist
  void generateFieldValues () {
    externalKey = UUID.randomUUID();
  }

}

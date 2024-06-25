package edu.cnm.deepdive.codebreaker.model.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.OrderBy;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.time.Instant;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import org.hibernate.annotations.CreationTimestamp;

@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
@Table(
    indexes = {
        @Index(columnList = "pool_size, code_length"),

    }
)
public class Game {

  public static final int MAX_CODE_LENGTH = 20;

  @Id
  @GeneratedValue
  @Column(nullable = false, updatable = false)
  private Long id;

  @Column(nullable = false, updatable = false, unique = true)
  private UUID externalKey;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "player_id", nullable = false, updatable = false)
  private User player;

  @Column(nullable = false, updatable = false, length = MAX_CODE_LENGTH)
  private String pool;

  @Column(nullable = false, updatable = false)
  private int poolSize;

  @Column(nullable = false, updatable = false)
  private int codeLength;

  @Column(nullable = false, updatable = false)
  private String secretCode;

  @Column(nullable = false, updatable = false)
  @Temporal(TemporalType.TIMESTAMP)
  @CreationTimestamp
  private Instant created;

  public Long getId() {
    return id;
  }

  public UUID getExternalKey() {
    return externalKey;
  }

  public User getPlayer() {
    return player;
  }

  public void setPlayer(User player) {
    this.player = player;
  }

  public String getPool() {
    return pool;
  }

  public void setPool(String pool) {
    this.pool = pool;
  }

  public int getPoolSize() {
    return poolSize;
  }

  public int getCodeLength() {
    return codeLength;
  }

  public void setCodeLength(int codeLength) {
    this.codeLength = codeLength;
  }

  public String getSecretCode() {
    return secretCode;
  }

  public void setSecretCode(String secretCode) {
    this.secretCode = secretCode;
  }

  public Instant getCreated() {
    return created;
  }

  @OneToMany(
      mappedBy = "game", fetch = FetchType.EAGER,
      cascade = CascadeType.ALL, orphanRemoval = true
  )
  @OrderBy("created ASC")
  private final List<Guess> guesses = new LinkedList<>();

  public List<Guess> getGuesses() {
    return guesses;
  }

  public boolean isSolved () {
    return getGuesses()
        .stream()
        .anyMatch(Guess::isSolution);

  }

  @PrePersist
  void generateFieldValues() {
    externalKey = UUID.randomUUID();
    poolSize = (int) pool
        .codePoints()
        .count();
  }

}

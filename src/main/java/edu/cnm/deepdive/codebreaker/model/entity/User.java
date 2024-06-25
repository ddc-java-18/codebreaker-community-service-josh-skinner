package edu.cnm.deepdive.codebreaker.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.time.Instant;
import java.util.UUID;
import org.hibernate.annotations.CreationTimestamp;

@Entity
public class User {

  @Id
  @GeneratedValue
  @Column(name = "user_profile_id", updatable = false)
  private long id;

  @Column(nullable = false, updatable = false, unique = true)
  private UUID externalKey;

  @Column(nullable = false, updatable = true, unique = true)
  private String displayName;

  @Column(nullable = false, updatable = false, unique = true)
  private String oauthKey;

  @Column(nullable = false, updatable = false)
  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  private Instant created;

  @Column(nullable = false, updatable = true)
  @Temporal(TemporalType.TIMESTAMP)
  private Instant accessed;

  public long getId() {
    return id;
  }

  public UUID getExternalKey() {
    return externalKey;
  }

  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  public String getOauthKey() {
    return oauthKey;
  }

  public void setOauthKey(String oauthKey) {
    this.oauthKey = oauthKey;
  }

  public Instant getCreated() {
    return created;
  }

  public Instant getAccessed() {
    return accessed;
  }

  public void setAccessed(Instant accessed) {
    this.accessed = accessed;
  }

  void setExternalKey(UUID externalKey) {
    externalKey = UUID.randomUUID();
  }
}

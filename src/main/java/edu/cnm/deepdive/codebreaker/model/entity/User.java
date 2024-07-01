package edu.cnm.deepdive.codebreaker.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name = "user_profile")
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"key", "displayName", "created"})
public class User {

  @Id
  @GeneratedValue
  @Column(name = "user_profile_id", updatable = false)
  @JsonIgnore
  private Long id;

  @Column(nullable = false, updatable = false, unique = true)
  @JsonProperty(value = "key", access = Access.READ_ONLY)
  private UUID externalKey;

  @Column(nullable = false, updatable = true, unique = true)
  private String displayName;

  @Column(nullable = false, updatable = false, unique = true)
  @JsonIgnore
  private String oauthKey;

  @Column(nullable = false, updatable = false)
  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  private Instant created;

  @Column(nullable = false, updatable = true)
  @Temporal(TemporalType.TIMESTAMP)
  @JsonIgnore
  private Instant accessed;

  public Long getId() {
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

  @OneToMany(
      mappedBy = "player", fetch = FetchType.LAZY,
      cascade = CascadeType.ALL, orphanRemoval = true
  )
  @OrderBy("created DESC")
  @JsonIgnore
  private final List<Game> games = new LinkedList<>();

  public List<Game> getGames() {
    return games;
  }
  @PrePersist
  void generateFieldValues () {
    externalKey = UUID.randomUUID();
  }
}

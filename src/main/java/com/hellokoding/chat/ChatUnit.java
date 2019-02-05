package com.hellokoding.chat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import com.hellokoding.auth.model.User;

@Entity
public class ChatUnit {

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;
  private LocalDateTime date;
  private String message;

  @ManyToOne
  private User user;
  
  public ChatUnit() {
  }
  
  public ChatUnit(Long id, LocalDateTime date, User user, String message) {
    super();
    this.id = id;
    this.date = date;
    this.user = user;
    this.message = message;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public LocalDateTime getDate() {
    return date;
  }

  public void setDate(LocalDateTime date) {
    this.date = date;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}

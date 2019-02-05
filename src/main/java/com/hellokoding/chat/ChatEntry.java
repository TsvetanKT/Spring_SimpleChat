package com.hellokoding.chat;

import org.springframework.stereotype.Component;

@Component
public class ChatEntry {

  private String message;
  private String ownerName;
  private boolean ownMessage;
  
  public ChatEntry() {
  }

  public ChatEntry(String message, String ownerName, boolean ownMessage) {
    super();
    this.message = message;
    this.ownerName = ownerName;
    this.ownMessage = ownMessage;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
  
  public String getOwnerName() {
    return ownerName;
  }

  public void setOwnerName(String ownerName) {
    this.ownerName = ownerName;
  }
  
  public boolean isOwnMessage() {
    return ownMessage;
  }

  public void setOwnMessage(boolean ownMessage) {
    this.ownMessage = ownMessage;
  }
}

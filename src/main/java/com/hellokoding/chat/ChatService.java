package com.hellokoding.chat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hellokoding.auth.model.User;

@Service
public class ChatService {
  
  private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

  @Autowired
  private ChatRepository chatRepository;
  
  public List<ChatEntry> getAllMessages() {
    List<ChatUnit> messages = new ArrayList<>();
    chatRepository.findAll().forEach(messages::add);
    
    return convertChatElements(messages);
  }
  
  public void addMessage(ChatUnit message) {
    chatRepository.save(message);
  }

  public void addMessage(String message, User currentUser) {
    LocalDateTime currentDate = LocalDateTime.now();
    ChatUnit chatUnit = new ChatUnit(null, currentDate, currentUser, message);
    addMessage(chatUnit);
  }
  
  private static List<ChatEntry> convertChatElements(List<ChatUnit> chatDbModels) {
    List<ChatEntry> messages = new ArrayList<>();
    for (ChatUnit chatUnit : chatDbModels) {
      messages.add(new ChatEntry(
          generateChatMessage(chatUnit), chatUnit.getUser().getUsername(), false));
    }
    
    return messages;
  }
  
  private static String generateChatMessage(ChatUnit chatUnit) {
    String dateTimeFormated = chatUnit.getDate().format(formatter);
    return String.format("%s %s: %s", dateTimeFormated, 
        chatUnit.getUser().getUsername(), chatUnit.getMessage());
  }
}

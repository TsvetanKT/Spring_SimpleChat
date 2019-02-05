package com.hellokoding.chat;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.hellokoding.auth.model.User;
import com.hellokoding.auth.service.UserService;

@Controller
public class ChatController {

  @Autowired
  private ChatService chatService;

  @Autowired
  private UserService userService;

  @Autowired
  private ChatEntry chatEntry;

  @RequestMapping(value = {"/", "/chat"}, method = RequestMethod.GET)
  public String chat(Model model) {
    List<ChatEntry> allMessages = chatService.getAllMessages();
    allMessages = markOwnMessages(allMessages);
    model.addAttribute("chatMessages", allMessages);
    model.addAttribute("ChatEntry", chatEntry);

    return "chat";
  }
  
  @RequestMapping(value = {"/postMessage"}, method = RequestMethod.POST)
  public ModelAndView postMessage(@RequestParam("message") String message, Model model) {
    model.addAttribute("ChatEntry", chatEntry);
    User currentUser = getCurrentUser();
    chatService.addMessage(message, currentUser);

    return new ModelAndView("redirect:/chat");
  }

  private User getCurrentUser() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String currentUserName = authentication.getName();
    return userService.findByUsername(currentUserName);
  }
  
  private List<ChatEntry> markOwnMessages(List<ChatEntry> allMessages) {
    String currentUserName = getCurrentUser().getUsername();
    for (ChatEntry chatEntry : allMessages) {
      chatEntry.setOwnMessage(chatEntry.getOwnerName().equals(currentUserName));
    }
    
    return allMessages;
  }
}

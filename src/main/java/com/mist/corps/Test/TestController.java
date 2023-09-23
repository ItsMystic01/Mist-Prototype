package com.mist.corps.Test;

import com.mist.corps.chat.ChatMessage;
import com.mist.corps.chat.MessageType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TestController {

    public List<ChatMessage> chatMessages = new ArrayList<>();

    public TestController() {
        chatMessages.add(new ChatMessage("John", "I'm bad", MessageType.CHAT));
        chatMessages.add(new ChatMessage("Tom", "I'm not good", MessageType.CHAT));
        chatMessages.add(new ChatMessage("Mist", "I'm Good", MessageType.CHAT));
        chatMessages.add(new ChatMessage("Pong", "How are you", MessageType.CHAT));
        chatMessages.add(new ChatMessage("Ping", "Yow", MessageType.CHAT));
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/dynamic")
    public String dynamic(Model model) {
        model.addAttribute("chatMessages", chatMessages);
        model.addAttribute("newMessage", new ChatMessage(null, null, MessageType.CHAT));
        return "dynamic";
    }

    @PostMapping("/new-message")
    public String newMessage(@ModelAttribute ChatMessage chatMessage) {
        chatMessages.add(chatMessage);
        return "redirect:/dynamic";
    }
}

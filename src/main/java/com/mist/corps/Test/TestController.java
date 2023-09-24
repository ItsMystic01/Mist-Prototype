package com.mist.corps.Test;

import com.mist.corps.ChatRoomController.ChatRoom;
import com.mist.corps.ChatRoomController.ChatRoomRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TestController {

    public ChatRoomRepository CHAT_ROOM_REPOSITORY;

    public TestController(ChatRoomRepository chatRoomRepository) {
        CHAT_ROOM_REPOSITORY = chatRoomRepository;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/dynamic")
    public String dynamic(Model model) {
        model.addAttribute("chatRooms", CHAT_ROOM_REPOSITORY.findAll());
        model.addAttribute("newRoom", new ChatRoom());
        return "dynamic";
    }

    @PostMapping("/new-room")
    public String newRoom(@ModelAttribute ChatRoom chatRoom) {
        CHAT_ROOM_REPOSITORY.save(chatRoom);
        return "redirect:/dynamic";
    }
}

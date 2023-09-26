package com.mist.corps.Test;

import com.mist.corps.ChatRoomController.ChatRoom;
import com.mist.corps.ChatRoomController.ChatRoomRepository;
import com.mist.corps.ChatRoomController.NewChatRoomRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ChatRoomController {

    public ChatRoomRepository CHAT_ROOM_REPOSITORY;

    public ChatRoomController(ChatRoomRepository chatRoomRepository) {
        CHAT_ROOM_REPOSITORY = chatRoomRepository;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/dynamic")
    public String createNewRoom(Model model) {
        model.addAttribute("chatRooms", CHAT_ROOM_REPOSITORY.findAll());
        model.addAttribute("newRoom", new ChatRoom());
        return "dynamic";
    }

    @PostMapping("/create-new-room")
    public String newRoom(@ModelAttribute ChatRoom chatRoom) {
        CHAT_ROOM_REPOSITORY.save(chatRoom);
        return "redirect:/dynamic";
    }

    @PostMapping("/delete-room/id")
    public String deleteById(@RequestParam("id") Integer id) {
        CHAT_ROOM_REPOSITORY.deleteById(id);
        return "redirect:/dynamic";
    }

    @PostMapping("/delete-room/name")
    public String deleteByName(@RequestParam("name") String name) {
        List<ChatRoom> chatRoom = CHAT_ROOM_REPOSITORY.findAll().stream().filter(x -> x.getName().equals(name)).toList();
        CHAT_ROOM_REPOSITORY.deleteAllInBatch(chatRoom);
        return "redirect:/dynamic";
    }

//    @PostMapping("delete-room/{chatRoom}")
//    public String deleteRoom(@PathVariable ChatRoom chatRoom) {
//        System.out.println(chatRoom.getId());
////        CHAT_ROOM_REPOSITORY.deleteById(Integer.parseInt(id));
//        return "redirect:/dynamic";
//    }
}

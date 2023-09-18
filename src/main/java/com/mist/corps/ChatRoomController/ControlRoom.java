package com.mist.corps.ChatRoomController;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat-room")
public class ControlRoom {
    private final ChatRoomRepository CHAT_ROOM_REPOSITORY;

    public ControlRoom(ChatRoomRepository chatRoomRepository) {
        CHAT_ROOM_REPOSITORY = chatRoomRepository;
    }

    @GetMapping
    public List<ChatRoom> getChatRooms() {
        return CHAT_ROOM_REPOSITORY.findAll();
    }

    @PostMapping
    public void addChatRoom(@RequestBody NewChatRoomRequest request) {
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setName(request.name());

        CHAT_ROOM_REPOSITORY.save(chatRoom);
    }

    @DeleteMapping("{id}")
    public void deleteChatRoom(@PathVariable("id") Integer id) {
        CHAT_ROOM_REPOSITORY.deleteById(id);
    }

    @PutMapping("{id}")
    public void updateChatRoom(@PathVariable("id") Integer id, @RequestBody NewChatRoomRequest request) {
        ChatRoom chatRoom = CHAT_ROOM_REPOSITORY.getReferenceById(id);
        chatRoom.setName(request.name());

        CHAT_ROOM_REPOSITORY.save(chatRoom);
    }
}

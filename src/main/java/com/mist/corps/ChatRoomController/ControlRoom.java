package com.mist.corps.ChatRoomController;

import com.mist.corps.ChatRoom;
import com.mist.corps.ChatRoomRepository;
import com.mist.corps.NewChatRoomRequest;
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
        chatRoom.setEmail(request.email());
        chatRoom.setAge(request.age());

        CHAT_ROOM_REPOSITORY.save(chatRoom);
    }

    @DeleteMapping("{channelName}")
    public void deleteChatRoom(@PathVariable("channelName") Integer id) {
        CHAT_ROOM_REPOSITORY.deleteById(id);
    }

    @PutMapping("{channelName}")
    public void updateChatRoom(@PathVariable("channelName") Integer id, @RequestBody NewChatRoomRequest request) {
        ChatRoom chatRoom = CHAT_ROOM_REPOSITORY.getReferenceById(id);
        chatRoom.setName(request.name());
        chatRoom.setEmail(request.email());
        chatRoom.setAge(request.age());

        CHAT_ROOM_REPOSITORY.save(chatRoom);
    }
}

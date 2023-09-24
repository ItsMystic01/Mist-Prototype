package com.mist.corps.ChatRoomController;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
//@RequestMapping("/chat-room")
public class ControlRoom {
    private final ChatRoomRepository CHAT_ROOM_REPOSITORY;

    public ControlRoom(ChatRoomRepository chatRoomRepository) {
        CHAT_ROOM_REPOSITORY = chatRoomRepository;
    }

    @GetMapping("/json")
    public List<ChatRoom> getChatRooms() {
        return CHAT_ROOM_REPOSITORY.findAll();
    }
//
//    @PostMapping
//    public void addChatRoom(@RequestBody NewChatRoomRequest request) {
//        ChatRoom chatRoom = new ChatRoom();
//        chatRoom.setName(request.name());
//
//        CHAT_ROOM_REPOSITORY.save(chatRoom);
//    }
//
//    @GetMapping("{id}")
//    public Optional<ChatRoom> getChatRoom(@PathVariable("id") Integer id) {
//        return CHAT_ROOM_REPOSITORY.findById(id);
//    }
//    @GetMapping("/students")
//    public List<ChatRoom> getChatRoom() {
//        List<ChatRoom> chatRooms = new ArrayList<>();
////        chatRooms.add(new ChatRoom(1, "John"));
////        chatRooms.add(new ChatRoom(2, "Steve"));
////        chatRooms.add(new ChatRoom(3, "Steven"));
////        chatRooms.add(new ChatRoom(4, "Jobs"));
////        chatRooms.add(new ChatRoom(5, "Gates"));
////        chatRooms.add(new ChatRoom(6, "Markian"));
//        return chatRooms;
//    }
//
////    http://localhost:8080/student/4/Tom
//    @GetMapping("/student/{id}/{name}/{lastName}")
//    public ChatRoom getChatRoom(@PathVariable("id") Integer id, @PathVariable("name") String name, @PathVariable("lastName") String lastName) {
//        return new ChatRoom(id, name + lastName);
//    }
//
//    // http://localhost:8080/student/query?id=1&name=Tom
//    @GetMapping("/student/query")
//    public ChatRoom getChatRoom(@RequestParam(name = "id") Integer id, @RequestParam(name = "name") String name) {
//        return new ChatRoom(id, name);
//    }
//
//    @DeleteMapping("{id}")
//    public void deleteChatRoom(@PathVariable("id") Integer id) {
//        CHAT_ROOM_REPOSITORY.deleteById(id);
//    }
//
//    @PutMapping("{id}")
//    public void updateChatRoom(@PathVariable("id") Integer id, @RequestBody NewChatRoomRequest request) {
//        ChatRoom chatRoom = CHAT_ROOM_REPOSITORY.getReferenceById(id);
//        chatRoom.setName(request.name());
//
//        CHAT_ROOM_REPOSITORY.save(chatRoom);
//    }
}

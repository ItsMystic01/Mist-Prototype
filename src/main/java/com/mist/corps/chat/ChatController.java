package com.mist.corps.chat;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import java.util.Objects;

@Controller
public class ChatController {

    @MessageMapping("/chat/{chatRoomClass}/sendMessage")
    @SendTo("/topic/{chatRoomClass}")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage, @DestinationVariable String chatRoomClass) {
        return chatMessage;
    }

    @MessageMapping("/chat/{chatRoomName}/addUser")
    @SendTo("/topic/{chatRoomName}")
    public ChatMessage addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor,
                               @DestinationVariable String chatRoomName) {
        Objects.requireNonNull(headerAccessor.getSessionAttributes()).put("username", chatMessage.getSender());

        return chatMessage;
    }
}

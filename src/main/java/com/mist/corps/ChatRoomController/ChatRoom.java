package com.mist.corps.ChatRoomController;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.Objects;

@Getter
@Entity
public class ChatRoom {

    @Id
    @SequenceGenerator(name = "chatroom_id_sequence", sequenceName = "chatroom_id_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "chatroom_id_sequence")
    private Integer id;
    @Getter
    private String name;

    public ChatRoom(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public ChatRoom() {}

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatRoom chatRoom = (ChatRoom) o;
        return Objects.equals(id, chatRoom.id) && Objects.equals(name, chatRoom.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "ChatRoom{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

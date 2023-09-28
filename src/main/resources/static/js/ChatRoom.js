'use strict';

const messageForm = document.querySelector("#messageForm");
const messageInput = document.querySelector("#message");
const messageArea = document.querySelector("#messageArea");
const connectingElement = document.querySelector(".connecting");
const channelName = document.querySelector(".chatRoomName")

let stompClient = null;
let username = null;

const colors = [
    '#2196F3', '#32c787', '#00BCD4', '#ff5652',
    '#ffc107', '#ff85af', '#FF9800', '#39bbb0'
];

function connect(event) {
    username = usernameGenerator();

    let socket = new SockJS('/chat');
    stompClient = Stomp.over(socket);

    stompClient.connect({}, onConnected, onError);

    event.preventDefault();
}

function onConnected() {
    stompClient.subscribe(`/topic/${channelName.textContent}`, onMessageReceived);

    stompClient.send(`/app/chat/${channelName.textContent}/addUser`, {}, JSON.stringify({sender: username, type: 'JOIN'}), { chatRoomName: channelName.textContent });

    connectingElement.classList.add("hidden");
}

function onError() {
    connectingElement.textContent = "Could not connect to WebSocket server. Refresh this page and re-try!"
    connectingElement.style.color = "red";
}

function sendMessage(event) {

    let messageContent = messageInput.value.trim();

    if(messageContent && stompClient) {
        let chatMessage = {
            sender: username,
            content: messageInput.value,
            type: "CHAT"
        };
        stompClient.send(`/app/chat/${channelName.textContent}/sendMessage`, {}, JSON.stringify(chatMessage), { chatRoomClass: channelName.textContent });
        messageInput.value = "";
    }

    event.preventDefault();
}

function onMessageReceived(payload) {

    let message = JSON.parse(payload.body);

    let messageElement = document.createElement("li");

    if(message.type === "JOIN") {
        // messageArea.clear()
        messageElement.classList.add("event-message");
        message.content = message.sender + ' joined!';
    } else if (message.type === "LEAVE") {
        messageElement.classList.add("event-message")
        message.content = message.sender + ' left!';
    } else {
        messageElement.classList.add("chat-message");

        let avatarElement = document.createElement("i");
        let avatarText = document.createTextNode(message.sender[0]);

        avatarElement.appendChild(avatarText);
        avatarElement.style['background-color'] = getAvatarColor(message.sender);

        messageElement.appendChild(avatarElement);

        let usernameElement = document.createElement("span");
        let usernameText = document.createTextNode(message.sender);

        usernameElement.appendChild(usernameText)
        messageElement.appendChild(usernameElement)

    }

    let textElement = document.createElement("p");
    let messageText = document.createTextNode(message.content);

    textElement.appendChild(messageText);
    messageElement.appendChild(textElement);

    messageArea.appendChild(messageElement);
    messageArea.scrollTop = messageArea.scrollHeight;

}

function getAvatarColor(messageSender) {
    let hash = 0;

    for(let i = 0; i < messageSender.length; i++) {
        hash = 31 * hash + messageSender.charCodeAt(i);
    }

    let index = Math.abs(hash % colors.length);

    return colors[index];
}

function usernameGenerator() {
    const randomNumber = Math.floor(Math.random() * (1000000 - 1 + 1)) + 1;

    return randomNumber.toString();
}

document.addEventListener('DOMContentLoaded', connect);
messageForm.addEventListener('submit', sendMessage, true);
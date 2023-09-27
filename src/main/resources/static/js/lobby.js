let chatRoom = document.querySelectorAll("#chatRoomName")

function load(event) {
}

chatRoom.forEach((link) => {
    link.addEventListener("click", load);
});
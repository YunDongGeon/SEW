function LockF5() {
    if (event.keyCode == 116) {
        event.keyCode = 0;
        return false;
    }
}
document.onkeydown = LockF5;
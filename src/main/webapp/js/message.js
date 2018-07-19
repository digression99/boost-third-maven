/**
 *
 */

class Message {
    constructor() {
        this.messageModal = document.querySelector('.message-modal');
        this.messageText = this.messageModal.querySelector('.message-text');
    }

    printMessage(message, state) {
        this.messageText.innerHTML = message;
        this.messageModal.style.display = 'block';
        this.messageModal.classList.add(state);
        console.log('class list : ');
        console.log(this.messageModal.classList);

        setTimeout(() => {
            this.messageModal.classList.remove(state);
            console.log('class list : ');
            console.log(this.messageModal.classList);
            this.messageModal.style.display = 'none';
            this.messageText.innerHTML = "";
        }, MESSAGE_SHOW_MILLISECOND);
    }
}

 // default global instances.
 const messagePrinter = new Message();

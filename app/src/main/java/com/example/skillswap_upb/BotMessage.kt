class BotMessage {
    var message: String? = null
    var senderId: String? = null
    var senderRole: String? = null

    constructor() {}

    constructor(message: String?, senderId: String?, senderRole: String?) {
        this.message = message
        this.senderId = senderId
        this.senderRole = senderRole
    }
}

import org.slf4j.LoggerFactory
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto
import org.telegram.telegrambots.meta.api.objects.InputFile
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.exceptions.TelegramApiException

class Bot() : TelegramLongPollingBot(System.getenv("DODIK_BOT_TOKEN")) {

    companion object {
        private const val ALLOWED_USER_ID = 127877606L
    }

    private val log = LoggerFactory.getLogger(javaClass)
    private val messageFormat = MessageFormatter()

    override fun getBotUsername() = "Dodik"

    override fun onRegister() {
        super.onRegister()
    }

    override fun onUpdateReceived(update: Update) {
        val message = update.message ?: return
        val user = message.from
        require(user.id == ALLOWED_USER_ID)

        val receivedPhoto = message.photo.firstOrNull()?.fileId?.takeIf { it.isNotBlank() }
        requireNotNull(receivedPhoto) { "No valid photo found in message" }

        log.info("${user.firstName} ${user.id} wrote: ${message.text ?: ""} | File: $receivedPhoto")

        val photoToSend = InputFile(receivedPhoto)
        BotConfig.contributors.forEach { contributor ->
            val messageToSend = messageFormat.getMessage(contributor)
            sendPhoto(user.id.toString(), photoToSend, messageToSend)
        }
    }

    private fun sendPhoto(userId: String?, file: InputFile?, message: String?) {
        log.info("sendTo=$userId; file=$file; message=$message")

        val sp = SendPhoto.builder()
            .chatId(userId!!)
            .photo(file!!)
            .parseMode("html")
            .caption(message)
            .build()

        try {
            execute(sp)
        } catch (e: TelegramApiException) {
            throw RuntimeException(e)
        }

        log.info("message has been sent")
    }
}
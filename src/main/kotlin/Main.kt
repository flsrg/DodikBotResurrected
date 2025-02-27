import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession

val log: Logger = LoggerFactory.getLogger("Main")

fun main() {
    TelegramBotsApi(DefaultBotSession::class.java)
        .registerBot(Bot())

    log.info("Bot started")
}
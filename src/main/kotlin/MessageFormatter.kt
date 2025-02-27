import kotlin.math.ceil

class MessageFormatter {
    private val allUsersCount = BotConfig.MY_USERS_COUNT + BotConfig.contributors.sumOf { it.usersCount }
    private val perUserCost = ceil(BotConfig.SERVER_COST.toDouble() / allUsersCount).toInt()

    fun getMessage(contributor: Contributor): String {
        return formatPayment(contributor) +
                "\n" +
                formatCalculationDetails(contributor)
    }

    private fun formatPayment(contributor: Contributor): String {
        val total = contributor.usersCount * perUserCost
        return buildString {
            appendLine("${contributor.introductionMessage}.")
            appendLine("Дублирую: Жду от тебя - <b>${total}руб</b>")
        }
    }

    private fun formatCalculationDetails(contributor: Contributor): String {
        val total = contributor.usersCount * perUserCost
        return buildString {
            appendLine("Как считается:")
            appendLine("  - Стоимость сервера = ${BotConfig.SERVER_COST}р.")
            appendLine("  - Всего $allUsersCount пользователей")
            append("  - Платеж за человека: ${BotConfig.SERVER_COST}/$allUsersCount = ${perUserCost}р.")

            if (contributor.usersCount > 1) {
                appendLine("\n  - У тебя ${contributor.usersCount} человека ${contributor.fromWhomMessage}")
                append("  - Итого: ${perUserCost}*${contributor.usersCount} = ${total}р.")
            }
        }
    }
}
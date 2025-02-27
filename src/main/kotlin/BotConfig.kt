object BotConfig {
    const val SERVER_COST = 866
    const val MY_USERS_COUNT = 2

    val contributors = listOf(
        Den(), Sanj(), Smiga(), Jan(), Sailor(), Andrej()
    )
}

sealed class Contributor(val usersCount: Int, val introductionMessage: String, val fromWhomMessage: String?)
class Den(): Contributor(usersCount = 3, "Старик, оплатил впн", "(за тебя, чела с телевизором и Наташу)")
class Sanj(): Contributor(usersCount = 2 + 2, "Браток, оплатил впн", "(за тебя, Юлю и двоих твоих челов)")
class Smiga(): Contributor(usersCount = 1, "Старик, оплатил впн", null)
class Jan(): Contributor(usersCount = 1, "Яна, привет! Оплатил впн", null)
class Sailor(): Contributor(usersCount = 2 + 4, "Сань, привет! Оплатил впн", "(За тебя, Машу, и еще 4х)")
class Andrej(): Contributor(usersCount = 1, "Андрей, привет! Оплатил впн", null)
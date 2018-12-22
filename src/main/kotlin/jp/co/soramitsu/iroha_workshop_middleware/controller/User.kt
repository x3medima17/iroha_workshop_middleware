package jp.co.soramitsu.iroha_workshop_middleware.controller

import jp.co.soramitsu.iroha_workshop_middleware.controller.iroha_facade.UserIrohaFacade
import jp.co.soramitsu.iroha_workshop_middleware.model.UserData
import org.springframework.http.HttpStatus
import org.springframework.web.client.HttpClientErrorException

class User {
    var id: Int = 0
    var name: String = ""
    var data: UserData = UserData(0, "")
        get() = UserData(id, name)
        private set
    var is_deleted: Boolean = false
    val irohaFacade: UserIrohaFacade

    constructor(name: String) {
        this.name = name
        this.irohaFacade = UserIrohaFacade(name)
    }

    constructor(data: UserData): this(data.name)

    fun getTotalBalance(): Double {
        var totalBalance = irohaFacade.getTotalBalance()
        return totalBalance.toDouble()
    }

    fun charge(amount: Double) {
        irohaFacade.addBalance(amount)
    }

    companion object {
        private val users = mutableMapOf<String, User>(
                "testuser_1" to User("testuser_1"),
                "testuser_2" to User("testuser_2"))

        fun addUser(user: User): String {
            users[user.name] = user
            return user.name
        }

        private fun checkUserExistence(id: String) {
            try {
                if (id !in users.keys) {
                    throw HttpClientErrorException(HttpStatus.BAD_REQUEST, "You have asked for not existing user ID")
                }
            } catch (e: NumberFormatException) {
                throw HttpClientErrorException(HttpStatus.BAD_REQUEST, "You have entered wrong used ID")
            }
        }

        fun getUser(id: String): User {
            checkUserExistence(id)

            return users[id]!!
        }

        fun getAllUsersInfo(): List<UserData> {
            return users.map { it.value.data }
        }
    }
}
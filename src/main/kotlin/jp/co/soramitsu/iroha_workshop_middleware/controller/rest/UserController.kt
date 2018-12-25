package jp.co.soramitsu.iroha_workshop_middleware.controller.rest

import jp.co.soramitsu.iroha_workshop_middleware.controller.User
import org.springframework.web.bind.annotation.*
import jp.co.soramitsu.iroha_workshop_middleware.model.*
import org.springframework.http.HttpStatus
import org.springframework.web.client.HttpServerErrorException

@RestController
class UserController {
    constructor() {
//        System.load("/Users/vr/Work/Visa-Everywhere/Source/vaioh-backend/libirohajava.jnilib")
    }

    @RequestMapping("/users", method = [(RequestMethod.GET)])
    fun getUsers(): List<UserData> {

        return User.getAllUsersInfo()
    }

    @RequestMapping(value = ["/users"], method = [(RequestMethod.POST)])
    fun createUser(@RequestBody(required = true) userData: UserData): InsertionResponse {
        throw HttpServerErrorException(HttpStatus.NOT_IMPLEMENTED, "Endpoint is not implemented yet")
    }

    @RequestMapping(value = ["/users/{userId}"], method = [(RequestMethod.GET)])
    fun getUserInfo(@PathVariable("userId") userId: String): UserData {
        return User.getUser(userId).data
    }

    @RequestMapping(value = ["/users/{userId}"], method = [(RequestMethod.DELETE)])
    fun deleteUser(@PathVariable("userId") userId: String): SimpleResponse {
        User.getUser(userId).is_deleted = true
        return SimpleResponse("OK")
    }

    @RequestMapping(value = ["/users/{userId}/balance"], method = [(RequestMethod.GET)])
    fun getUserBalance(@PathVariable("userId") userId: String): UserBalanceData {
        return UserBalanceData(User.getUser(userId).getTotalBalance().toString())
    }

    @RequestMapping(value = ["/users/{userId}/balance"], method = [(RequestMethod.POST)])
    fun addUserBalance(@PathVariable("userId") userId: String, @RequestBody(required = true) chargeData: ChargeData): SimpleResponse {
        val user = User.getUser(userId)
        user.charge(chargeData.amount.toDouble())
        return SimpleResponse("OK")
    }
}
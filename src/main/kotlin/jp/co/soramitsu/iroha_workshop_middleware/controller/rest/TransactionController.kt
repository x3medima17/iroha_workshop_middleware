package jp.co.soramitsu.iroha_workshop_middleware.controller.rest

import jp.co.soramitsu.iroha_workshop_middleware.controller.*
import jp.co.soramitsu.iroha_workshop_middleware.model.*
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.client.HttpClientErrorException
import java.util.concurrent.atomic.AtomicInteger

@RestController
class TransactionController {

    @RequestMapping("/transactions", method = [(RequestMethod.GET)])
    fun getAllTransactions(): TransactionMapData {
        return TransactionMapData(transactions.mapValues {
            it.value.data
        })
    }
    @RequestMapping("/transactions", method = [(RequestMethod.POST)])
    fun addTransaction(@RequestBody(required = true) transactionData: TransactionData): InsertionResponse {
        val id = transactionCounter.getAndIncrement()

        var transaction: Transaction
        val user = User.getUser(transactionData.sourceAccountId)
        if (user.getTotalBalance() < transactionData.amount.toDouble()) {
            return InsertionResponse("Error, not enough money on source user (${transactionData.sourceAccountId}) account", id.toString())
        } else {
            transaction = Transaction(transactionData)
            transaction.confirmTransaction()
        }
        transactions[id] = transaction

        return InsertionResponse("OK", id.toString())
    }

    @RequestMapping("/transactions/{transactionId}", method = [(RequestMethod.GET)])
    fun getTransactionInfo(@PathVariable("transactionId") transactionId: String): TransactionData {
        checkTransactionExists(transactionId)

        return transactions[transactionId.toInt()]!!.data
    }

    companion object {
        private val transactions: MutableMap<Int, Transaction> = mutableMapOf()
        private var transactionCounter = AtomicInteger(0)
    }

    private fun checkTransactionExists(transactionId: String)
    {
        try {
            val id = transactionId.toInt()
            if (!transactions.keys.contains(id)) {
                throw HttpClientErrorException(HttpStatus.NOT_FOUND, "Transaction with id=$id does not exist")
            }
        } catch (e: Exception) {
            throw HttpClientErrorException(HttpStatus.BAD_REQUEST, "Transaction id=$transactionId is not correct (should be Integer)")
        }
    }
}
package jp.co.soramitsu.iroha_workshop_middleware.controller.iroha_facade

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import iroha.protocol.Primitive
import iroha.protocol.QryResponses
import jp.co.soramitsu.iroha.java.IrohaAPI
import jp.co.soramitsu.iroha.java.Query
import jp.co.soramitsu.iroha.java.Transaction
import jp.co.soramitsu.iroha.java.TransactionStatusObserver
import jp.co.soramitsu.iroha_workshop_middleware.utils.KeysHelper.Companion.getKeys
import jp.co.soramitsu.iroha_workshop_middleware.utils.KeysHelper.Companion.saveKeyPairToFile
import java.security.KeyPair


open class IrohaFacade {
    protected val irohaAPI by lazy {
        IrohaAPI("127.0.0.1", 50051)
    }

    protected fun setAccountDetail(account: String, key: String, value: String) {
        // TODO: Please, implement this method

    }

    protected fun transferAsset(sourceAccount: String, destinationAccount: String, asset: String, amount: Double) {
        // TODO: Please, implement this method
    }

    protected fun addAsset(account: String, asset: String, amount: Double) {
        // TODO: Please, implement this method throw HttpServerErrorException(HttpStatus.NOT_IMPLEMENTED, "Method is not implemented yet")
    }

    protected fun createAccount(newAccountName: String, domainId: String, newUserKeypair: KeyPair) {
        // TODO: Please, implement this method
    }

    // Queries

    fun acquireAccountDetail(account: String, key: String): String {
        // TODO: Please, implement this method
        return "Method is not implemented yet"
    }

    fun acquireAccountBalance(accountID: String, assetID: String): String {
        // TODO: Please, implement this method
        return "Method is not implemented yet"
    }

    protected val defaultDomainId: String = "test"
    private val defaultAdminAccountId = "admin@test"
    var currency: CurrencyType = CurrencyType.Yen

    protected fun getFullAccountId(accountName: String): String = "$accountName@$defaultDomainId"
    protected fun getFullCurrencyAssetId(currency: CurrencyType): String = getFullCurrencyAssetId(currency.shortname)
    protected fun getFullCurrencyAssetId(currency: String): String = "${currency.toLowerCase()}#$defaultDomainId"

}
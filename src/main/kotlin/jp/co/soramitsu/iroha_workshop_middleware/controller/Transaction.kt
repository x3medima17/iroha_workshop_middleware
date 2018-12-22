package jp.co.soramitsu.iroha_workshop_middleware.controller

import jp.co.soramitsu.iroha_workshop_middleware.controller.iroha_facade.TransactionIrohaFacade
import jp.co.soramitsu.iroha_workshop_middleware.model.TransactionData
import jp.co.soramitsu.iroha_workshop_middleware.model.TransactionListData
import java.lang.Exception

class Transaction (val data: TransactionData) {
    private val facade = TransactionIrohaFacade()

    fun confirmTransaction() {
        facade.sendAsset(data.sourceAccountId, data.destinationAccountId, data.currency, data.amount)
    }
}
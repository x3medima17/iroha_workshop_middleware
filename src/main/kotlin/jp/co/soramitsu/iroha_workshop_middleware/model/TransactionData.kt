package jp.co.soramitsu.iroha_workshop_middleware.model

data class TransactionData (val sourceAccountId: String, val destinationAccountId: String, val amount: String, val currency: String)
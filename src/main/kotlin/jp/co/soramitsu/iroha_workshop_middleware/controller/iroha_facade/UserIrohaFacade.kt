package jp.co.soramitsu.iroha_workshop_middleware.controller.iroha_facade

class UserIrohaFacade(val accountName: String) : IrohaFacade() {

    fun getTotalBalance() : String {
        return acquireAccountBalance(getFullAccountId(accountName), getFullCurrencyAssetId(CurrencyType.Yen))
    }

    fun addBalance(amount: Double) {
        addAsset(getFullAccountId(accountName), getFullCurrencyAssetId(CurrencyType.Yen), amount)
    }
}
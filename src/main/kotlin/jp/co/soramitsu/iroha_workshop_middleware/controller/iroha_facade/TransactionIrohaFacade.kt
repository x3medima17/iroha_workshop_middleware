package jp.co.soramitsu.iroha_workshop_middleware.controller.iroha_facade

class TransactionIrohaFacade : IrohaFacade() {
    fun sendAsset(sourceAccountId: String, destinationAccountId: String, assetName:String, amount: Double) {
        transferAsset(
                getFullAccountId(sourceAccountId),
                getFullAccountId(destinationAccountId),
                getFullCurrencyAssetId(assetName),
                amount)
    }
}
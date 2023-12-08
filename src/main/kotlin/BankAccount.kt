class BankAccount(private val accountName : String, private val accountNumber: Int) {
    private var balance = 0

    fun topUpBalance(deposit : Int) {
        balance += deposit
    }

    fun withdrawMoney(moneyAmount: Int) {
        if(moneyAmount > balance)
            throw Exception("There are not enough funds in the account №$accountNumber, balance = $balance")
        balance -= moneyAmount
    }

    fun depositMoney(moneyAmount: Int) {
        balance += moneyAmount
    }

    override fun toString() :String {
        return "Account name = $accountName, account number = $accountNumber, balance = $balance"
    }
}
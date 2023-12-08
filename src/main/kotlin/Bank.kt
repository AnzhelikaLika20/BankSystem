class Bank {
    private var clientsNumber = 0
    private val clients = mutableMapOf<Int, BankClient>()

    private fun checkClientExistence(clientId: Int) {
        if(!clients.containsKey(clientId))
            throw Exception("There is no client with Id = $clientId")
    }

    fun addClient() : BankClient? {
        clientsNumber += 1;
        clients[clientsNumber] = BankClient(clientsNumber)
        return clients[clientsNumber]
    }

    fun printClientAccounts(clientId: Int)  {
        clients[clientId]?.printAccounts()
    }

    fun openNewAccount(clientId: Int, accountName: String) {
        checkClientExistence(clientId)
        clients[clientId]?.openAccount(accountName);
    }

    fun topUpBalance(clientId: Int, accountNumber : Int, deposit : Int) {
        checkClientExistence(clientId)
        if(deposit <= 0)
            throw Exception("Deposit should be positive")
        clients[clientId]?.topUpBalance(accountNumber, deposit)
    }

    fun transferMoney(clientId: Int, accountNumberFrom: Int, accountNumberTo: Int, transferAmount: Int) {
        checkClientExistence(clientId)
        if(transferAmount <= 0)
            throw Exception("Deposit should be positive")
        clients[clientId]?.transferMoney(accountNumberFrom, accountNumberTo, transferAmount)
    }

    inner class BankClient (val personalId :Int) {
        private var accountCounter = 0
        private val accounts = mutableMapOf<Int, BankAccount>()

        private fun checkAccountExistence(accountNumber: Int) {
            if(!accounts.containsKey(accountNumber))
                throw Exception("There is no account №$accountNumber")
        }

        fun printAccounts() {
            if (accountCounter == 0)
                throw Exception("The client hasn't opened accounts")
            accounts.forEach{account -> println(account.value)}
        }

        fun openAccount(accountName: String) {
            accountCounter += 1
            val newAccount = BankAccount(accountName, accountCounter)
            accounts[accountCounter] = newAccount
        }

        fun topUpBalance(accountNumber : Int, deposit : Int) {
            checkAccountExistence(accountNumber)
            accounts[accountNumber]?.topUpBalance(deposit)
        }

        fun transferMoney(accountNumberFrom: Int, accountNumberTo: Int, transferAmount: Int) {
            checkAccountExistence(accountNumberFrom)
            checkAccountExistence(accountNumberTo)
            if(transferAmount <= 0)
                throw Exception("Deposit should be positive")
            accounts[accountNumberFrom]?.withdrawMoney(transferAmount)
            accounts[accountNumberTo]?.depositMoney(transferAmount)
        }
    }
}
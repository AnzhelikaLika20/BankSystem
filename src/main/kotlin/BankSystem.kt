fun main(args: Array<String>) {
    val bank = Bank()
    val bankClient = bank.addClient()
    processUserInput(bank, bankClient)
}

fun getMenu() {
    println(
        "Enter:\n" +
                "1 - to look through list of accounts\n" +
                "2 - to open a new account\n" +
                "3 - to top up your balance\n" +
                "4 - to transfer money between accounts\n" +
                "5 - to end the session"
    )
}

fun processUserInput(bank: Bank, bankClient: Bank.BankClient?) {
    do {
        try {
            getMenu()
            val clientInput = readlnOrNull() ?: continue
            when (clientInput.toInt()) {
                1 -> bank.printClientAccounts(bankClient!!.personalId)
                2 -> {
                    println("Enter an account name")
                    val accountName = readln()
                    bank.openNewAccount(bankClient!!.personalId, accountName)
                }
                3 -> {
                    println("Enter the number of the account to be topped up")
                    val accountNumber = readln().toInt()
                    println("Enter the deposit amount")
                    val deposit = readln().toInt()
                    bank.topUpBalance(bankClient!!.personalId, accountNumber, deposit)
                }
                4 -> {
                    println("Enter the number of the account to transfer from")
                    val accountNumberFrom = readln().toInt()
                    println("Enter the number of the account to transfer to")
                    val accountNumberTo = readln().toInt()
                    println("Enter the transfer amount")
                    val transferAmount = readln().toInt()
                    bank.transferMoney(bankClient!!.personalId, accountNumberFrom, accountNumberTo, transferAmount)
                }
                5 -> {
                    return
                }
                else -> {
                    throw Exception("Incorrect instruction. Try again!")
                }
            }
        } catch (ex: NumberFormatException) {
            println("Incorrect input representation")
        } catch (ex: Exception) {
            println(ex.message)
        }
    } while (true)
}
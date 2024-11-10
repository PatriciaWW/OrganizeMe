// Global variable to be accessible by any function,,, 
// when using them again, they shouldn't be redefined again, i.e precided by var or val. just use the variable name directly
var loggedin = false
var running = true
var account = ""
var option = ""
var acc = false
var pin = 4123
var s_balance = 5002
var c_balance = 5002
var i_balance = 5002

// create a constructor for the account ( to discuss on Tuesday)

class Account {
  var name = ""
  var balance = 0
}

fun main () {

    // use the constructor to create our 3 accounts i.e savings, current, investment
    val savings = Account()
    savings.name = "Savings"
    savings.balance = 5000
    
    val current = Account()
    current.name = "Current"
    current.balance = 5000
    
    val investment = Account()
    investment.name = "Investment"
    investment.balance = 5000

    // start the application by checking if running and user not logged in

    while (running && !(loggedin)) {
        println("Welcome to my bank")

        // get user pin and cinfirm it is correct
        print("Enter pin: ")
        val myPin = Integer.valueOf(readLine()!!)
        if (myPin == pin){
            // if the pin is correct, set logged in to true and move to next step - choose account
            loggedin = true

            // by default as at line 5, account is an empty string. If that is so, we call the function checkAccount() to get user to select account
            if (account == ""){

                // this returns the account a user has picked, i.e s for saving, c for current, i for investment and p for investment
                account = checkAccount()
            }

            // if user is logged in and the account has been selected we proceed
            while (loggedin) {
                
                // check if account is savings
                if (account == "s") {
                    // if the account is savings, update that we are working with the savings account
                    var w_account = savings
                    println("you selected $w_account.name")

                    // call th option() to print the options to the user ang get them to select one. returns the option they make
                    option = option()

                    // we now have users choice, now we check what they want and call the related function
                    if (option == "b"){
                        // function to show the account balance.. requires the name and the balance
                        balance(w_account.name, w_account.balance)
                    
                    }
                    else if(option == "w"){
                        // call the withdraw function with current balance, a new val nbalance will be equal to the value returned by function
                        var nbalance: Int = withdraw(w_account.balance)

                        // update baalance to new balance returned
                        savings.balance = nbalance

                        // call the balance funtion to print the balance to terminal
                        balance(w_account.name, w_account.balance)
                    }
                    else if(option == "d"){
                        // call the deposit function with current balance, a new val nbalance will be equal to the value returned by function
                        val nbalance: Int = deposit(w_account.balance)

                        // update baalance to new balance returned
                        savings.balance = nbalance

                        // call the balance funtion to print the balance to terminal
                        balance(w_account.name, w_account.balance)
                    }
                    else if (option == "p"){
                        // call the change pin function
                        changepin() 
                    }  else {
                        // if user enters any other key, change the selected working account to empty string 
                        account = ""

                        // then call the checkAccount() funtion so that they can select a new account, i.e switch account
                        checkAccount()

                    }

                // the above is repeated for all accounts, i.e savings, current and investement portfolio as below 
                } else if (account == "c") {                   
                    var w_account = current
                    option = option()
                    if (option == "b"){
                        balance(w_account.name, w_account.balance)
                    
                    }
                    else if(option == "w"){
                        var nbalance: Int = withdraw(w_account.balance)
                        current.balance = nbalance
                        balance(w_account.name, w_account.balance)
                    }
                    else if(option == "d"){
                        val nbalance: Int = deposit(w_account.balance)
                        current.balance = nbalance
                        balance(w_account.name, w_account.balance)
                    }
                    else if (option == "p"){
                        changepin() 
                    } else {
                        account = ""
                        checkAccount()
                    }
                    
                } else if (account == "p") {
                    var w_account = investment
                        option = option()
                    if (option == "b"){
                        balance(w_account.name, w_account.balance)
                    
                    }
                    else if(option == "w"){
                        var nbalance: Int = withdraw(w_account.balance)
                        investment.balance = nbalance
                        balance(w_account.name, w_account.balance)
                    }
                    else if(option == "d"){
                        val nbalance: Int = deposit(w_account.balance)
                        investment.balance = nbalance
                        balance(w_account.name, w_account.balance)
                    }
                    else if (option == "p"){
                        changepin()  
                    }  else {
                        account = ""
                        checkAccount()
                    }
                    
                } else if (account == "i") {
                    // to do
                }
            }


        }
        else {println("Wrong pin")}
    }
}

// function to get user to select account
fun checkAccount (): String {
    // show user the options
    println("Select an account: ")
    println("Enter s  for savings: ")
    println("Enter c for current: ")
    println("Enter p fpr investment portfolio: ")
    println("Enter i for internal transaction: ")
    println("Enter q to exit")

    // get user input
    print("Enter your choice: ")
    account = readLine()!!

    // validate what user entered, must be one of the keys specified in line 141 to 145

    if (account != "s" && account != "c" && account != "p" && account != "i" && account != "q") {
        println("Invalid choice.. to quit use q")
    } else if (account == "q") {
        // if user enters q - stop and termiante the application
        loggedin = false
        running = false
    } else {
        // by default, acc is false and we set it true if user has picked an account to operate on
        acc = true

    }

    // return the account the user choose
    return account
}

// function to show bank opertions to user i.e deposit, withdraw etc
fun option(): String {
    // print the options to the terminal for the user
    println("Enter d to deposit")
    println("Enter w to withdraw")
    println("Enter b to check c_balance")
    println("Enter p to to change")
    println("Enter any key to swyich account")

    // get user's choice
    print("Enter your choice: ")
    var option = readLine()!!

    // return the option user made back to the main app
    return option
}


// function to show the account balance, requires the account name and the balance
fun balance(account_name: String, balance: Int) {
    // print the balance to the terminal
    println("Your $account_name account balance is $balance")
}

// deposit function, requires the current balance and returns the new balance
fun deposit(balance: Int): Int {
    print("Enter amount to deposit: ")
    var deposit = Integer.valueOf(readLine())
    val nbalance = balance + deposit
    return nbalance
}


// withdraw function, requires the current balance and returns the new balance
fun withdraw(balance: Int): Int{
    print("Withdrawal Amount: ")
    val withdrawal = Integer.valueOf(readLine())
    if (withdrawal <= balance){
        val nbalance = balance - withdrawal
        return nbalance
    }
    else{
        print("Withdrawal unsuccessful")
        val nbalance = balance
        return nbalance
    }
}

// change pin function, requires the current balance and returns the new balance
fun changepin() {
    print("Enter your old pin: ")
    var oldPin = Integer.valueOf(readLine())
    print("Enter your new pin: ")
    var newPin = Integer.valueOf(readLine())
    print("Confirm new pin: ")
    var newPin2 = Integer.valueOf(readLine())
    if (pin != oldPin){
        println("Wrong pin")
    }
    else{
        if (newPin == newPin2){
            pin == newPin
            println("Pin updated")
        }else {
            println("Pins do not match")
        }
    } 
}
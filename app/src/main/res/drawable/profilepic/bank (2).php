<?php
    $loggedin = false;
    $pin = 1234;
    $acc = false;
    $choice = "";
    $mainbalance = 3000;
    $savingsBalance = 2000;
    $investmentBalance = 6000;
    $balance = 0;
    $depositAmount = "";
    $running = true;
    $account = "";

    // FUNCTIONS:

         // Withdraw function
         function withdraw ($account, $balance){
            $withdrawalAmount = (int)readline("Enter amount to withdraw: ");
            if ($withdrawalAmount < $balance) {
                $balance-=$withdrawalAmount;
                echo ("Your new balance is $balance. \n");
                option();
                return $balance;
            } else {
                echo("Withdrawal not successful! Try again.\n");
                option();
            }
        }
        // End of withdraw function

        // Check balance function
        function checkBalance($account, $balance){
            echo("The balance for your $account account is $balance. \n");
            option();
        }
        // End of balance function

        // Deposit function
        function Deposit ($account, $balance) {
            global $balance, $depositAmount, $mainbalance, $savingsBalance, $investmentBalance;
            $depositAmount = readline("Enter amount you want to deposit: ");
            $balance+= $depositAmount;
            echo ("Your new balance is $balance \n");
            option();
            return $balance;
        }
        // Change pin function
        function changePin(){
            global $pin;
            $oldPin = (int)readline("Enter your old pin: ");
            $newPin = (int)readline("Enter new pin: ");
            $confirmPin = (int)readline("Confirm your new pin: ");
            if ($oldPin != $pin) {
                echo("The previous pin is incorrect. You can't change your pin!");
            } else {
                if ($newPin != $confirmPin) {
                    echo("Pins do not match! Try again.\n");
                    changePin();
                } else {
                    $pin == $newPin;
                    echo("Pin updated successfully! \n");
                    option();
                    return $pin;
                }
            }
        }
        // End of pin function

        // Option function
        function option(){
            global $choice, $loggedin, $account, $balance, $acc;
            while($acc){
                echo("Enter d to deposit into this account.\n");
                echo("Enter b to check balance for this account.\n");
                echo("Enter w to withdraw cash from this account.\n");
                echo("Enter p to change password for this account.\n");
                echo("Enter q to Quit.\n\n");
                $choice = readline("Enter your choice here: ");

                if ($choice != "d" && $choice != "b" && $choice != "w" && $choice != "p" && $choice != "q") {
                    echo("Invalid Choice. Try again");
                    option();
                    break;
                } elseif ($choice == "d") {
                    Deposit($account, $balance);
                    break;
                } elseif ($choice == "b") {
                    checkBalance($account, $balance);
                    break;
                } elseif ($choice == "w") {
                    withdraw($account, $balance);
                    break;
                } elseif ($choice == "p") {
                    changePin();
                    break;
                } else {
                    $acc = false;
                    checkAccount();
                }
             }
        }
        // End of Option function

        // Check Account function
        function checkAccount() {
            global $acc, $running, $loggedin, $mainbalance, $savingsBalance, $investmentBalance, $balance;
            echo("Welcome to your bank account. Choose an account.\n");
            echo("Enter m for main account.\n");
            echo("Enter s for savings account.\n");
            echo("Enter i for Investment account.\n");
            echo("Enter q to Quit.\n");
            $checkaccount = readline("Enter your choice here: ");

            switch ($checkaccount) {
                case "m":
                    $acc = true;
                    $balance = $mainbalance;
                    $account = "Main";
                    echo("You have chosen the $account account.\n");
                    option();
                    break;
                case "s":
                    $acc = true;
                    $balance = $savingsBalance;
                    $account = "Savings";
                    echo("You have chosen the $account account.\n");
                    option();
                    break;
                case "i":
                    $acc = true;
                    $balance = $investmentBalance;
                    $account = "Investment";
                    echo("You have chosen the $account account.\n");
                    option();
                    break;
                case "q":
                    echo ("Thank you for banking with us! See you soon.");
                    $acc = false;
            }
        }
        // End of Check Account function

        // END OF FUNCTIONS

    while($running && !($loggedin)) {
        global $pin, $loggedin, $running, $choice, $balance, $mainbalance, $savingsBalance, $investmentBalance, $checkaccount;
        echo("Welcome to Augustine's bank.\n");
        $myPin = (int)readline("Enter your PIN: ");
        if ($myPin == $pin) {
            $loggedin = true;
            checkAccount();
        } else {
            echo("Incorrect pin. Please try again.\n");
        }
        // Main Code
        while ($acc) {
            option();
        }
    }

?>
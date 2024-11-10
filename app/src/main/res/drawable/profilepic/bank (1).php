<?php
    $loggedin = false;
    $pin = 1234;
    $acc = false;
    $choice = "";
    $mainbalance = 3000;
    $savingsBalance = 2000;
    $investmentBalance = 6000;
    $balance = 0;
    $running = true;
    $account = "";

    function deposit($account,&$balance) {
        echo("You wish to deposit in the $account account.\n");
        $deposit = readLine("Please enter the amount you wish to deposit:  ");
        $nbalance = $balance + $deposit;
        echo("Your new balance is $nbalance");
        echo("Thanks for using Augustine's bank");
    }

    function withdraw($account,&$balance) {
        
        echo("You wish to deposit in the $account account.\n");
        $withdrawal_amount = readLine("Please enter the amount you wish to deposit:  ");
        if($withdrawal_amount<= $balance)
        $nbalance = $balance - $withdrawal_amount ;
        echo("Your new balance is $nbalance");
        echo("Thanks for using Augustine's bank");
    } 
    
    function checkBalance($account,$balance) {
        echo("Your balance in the $account account is $balance" );
        echo("Thanks for using Augustine's bank");

    }

    function changePin($pin){
        $oldpin = (int)readline("Enter your old pin: ");
        if ($oldpin == $pin) {
            $newpin1 = (int)readline("Enter your new pin: ");
            $newpin2 = (int)readline("Confirm your new pin: ");
            if ($newpin1 == $newpin2) {
                echo("Your new pin has been set!");
            }
            else {
                echo("Your pins don't match please try again!");

            }

        }

        else {
            echo("Incorrect old pin.Try again!");
        }

    }

    while($running && !$loggedin) {
        global $pin, $loggedin, $running, $choice, $balance, $mainbalance, $savingsBalance, $investmentBalance;
        echo("Welcome to Augustine's Bank.\n");
        $myPin = (int)readline("Enter your PIN: ");
        if ($myPin == $pin) {
            $loggedin = true;
        } else {
            echo("Incorrect pin. Please try again.\n");
        }
        function choice1(){
            echo("Enter d to deposit into this account.\n");
            echo("Enter b to check balance for this account.\n");
            echo("Enter w to withdraw cash from this account.\n");
            echo("Enter p to change password for this account.\n\n");
            $choice = readline("Enter your choice here: ");

            switch($choice) {
                case "d":
                    deposit();
                    break;

                case "w":
                    withdraw();
                    break;
                
                case "b":
                    checkBalance();
                    break; 

                case "p":
                    changepin();
                    break;
            }
        }


        while ($loggedin){
            echo("Enter the Account type you'd like to access using the options given below: \n");
            echo("Enter m for Main account.\n");
            echo("Enter s for Savings account.\n");
            echo("Enter i for Investment acccount.\n");
            $option = readline("Enter your option here.\n");

            switch ($option) {
                case "m":
                    $acc = true;
                    $balance = $mainbalance;
                    $account = "Main";
                    echo("You have chosen the $account account.\n");
                    echo("Choose what ation you'd like to perform in this account.\n");
                    choice1();
                    break;
                case "s":
                    $acc = true;
                    $balance = $savingsBalance;
                    $account = "Savings";
                    echo("You have chosen the $account account.\n");
                    echo("Choose what ation you'd like to perform in this account.\n");
                    choice1();
                    break;
                case "i":
                    $acc = true;
                    $balance = $investmentBalance;
                    $account = "Investment";
                    echo("You have chosen the $account account.\n");
                    echo("Choose what ation you'd like to perform in this account.\n");
                    choice1();
                    break;
            }

            if ($choice != "d" && $choice != "b" && $choice != "w" && $choice != "p") {
                echo("You have entered an invalid choice, try again.\n");
            } else{
                $acc = true;
            }

            
    
        }
    }

?>
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

            function withdraw(){

            }
        }
    }

?>
Specification Heading
=====================

This is an executable specification file. This file follows markdown syntax.
Every heading in this file denotes a scenario. Every bulleted point denotes a step.


Login
------------------------

* Click to element "gotItButton"
* Click to element "loginButton"
* Click to element "loginUserNameArea"
* "trest" textini "loginUserNameArea" elemente yaz
* Click to element "loginPasswordArea"
* "password1" textini "loginPasswordArea" elemente tek tek yaz
* Click to element "loginButtonArea"
* "5" saniye bekle

Place Bet
------------------------
* "15" saniye bekle
* Click to element "gotItButton"
* "2" saniye bekle
* Click to element "loginButton"
* "1" saniye bekle
* Click to element "loginUserNameArea"
* "trest" textini "loginUserNameArea" elemente yaz
* "2" saniye bekle
* Click to element "loginPasswordArea"
* "password1" textini "loginPasswordArea" elemente tek tek yaz
* Click to element "loginButtonArea"
* "5" saniye bekle
* Elementine tıkla "betChoose"
* "5" saniye bekle
* Elementine tıkla "betAcceptButton"
* "5" saniye bekle

Place Combi Bet
------------------------
* "10" saniye bekle
* Click to element "gotItButton"
* "2" saniye bekle
* Click to element "loginButton"
* "1" saniye bekle
* Click to element "loginUserNameArea"
* "trest" textini "loginUserNameArea" elemente yaz
* "2" saniye bekle
* Click to element "loginPasswordArea"
* "password1" textini "loginPasswordArea" elemente tek tek yaz
* Click to element "loginButtonArea"
* "5" saniye bekle
* Elementine tıkla "betChoose"
* "5" saniye bekle
* Elementine tıkla "combiBetItem"
* "5" saniye bekle
* Elementine tıkla "betAcceptButton"
* "5" saniye bekle

Change Password
------------------------
* "10" saniye bekle
* Click to element "gotItButton"
* "2" saniye bekle
* Click to element "loginButton"
* "1" saniye bekle
* Click to element "loginUserNameArea"
* "trest" textini "loginUserNameArea" elemente yaz
* "2" saniye bekle
* Click to element "loginPasswordArea"
* "password1" textini "loginPasswordArea" elemente tek tek yaz
* Click to element "loginButtonArea"
* "5" saniye bekle
* "customerMenu" wait on element
* "1" saniye bekle
* Elementine tıkla "myAccount"
* Elementine tıkla "changePass"
* "password1" textini "currentPass" elemente yaz
* "Password2" textini "newPass" elemente yaz
* "Password2" textini "repeatNewPass" elemente yaz
* Elementine tıkla "saveButton"
* "customerMenu" wait on element
* Elementine tıkla "logOut"
* Click to element "loginButton"
* "1" saniye bekle
* Click to element "loginUserNameArea"
* "trest" textini "loginUserNameArea" elemente yaz
* "2" saniye bekle
* Click to element "loginPasswordArea"
* "Password2" textini "loginPasswordArea" elemente tek tek yaz
* Click to element "loginButtonArea"
* "customerMenu" wait on element
* "1" saniye bekle
* Elementine tıkla "myAccount"
* Elementine tıkla "changePass"
* "Password2" textini "currentPass" elemente yaz
* "password1" textini "newPass" elemente yaz
* "password1" textini "repeatNewPass" elemente yaz
* Elementine tıkla "saveButton"


## Login test
tags:login
* Open Test environment
* Login Account

## Register new
tags: Register
* Open Test environment
* Register Page Open
* Register New Account

## Transaction History
tags:transactionHistory
* Login Account
* Go to Side Menu Bar
* Go to Transaction History Page

## Place new a Bet
tags: Single Bet
* Open Test environment
* Login Account
* Place Single Bet

## Place new Combi Bet
tags: Combi bet
* Open Test environment
* Login Account
* Place a Combi Bet

## Open My Bets
tags: MY account
* Open Test environment
* Login Account
* Open My account
* My Bets

## Transactions Page
tags: Transaction
* Open Test environment
* Login Account
* Open My account
* Transactions Bookings
* Transactions Deposit
* Transactions Withdrawal
* My Accounts Account Settings Personal Details

## Upload Documents
* Open Test environment
* Login Account
* Open My account
* My Account Upload Documents

## Change Password V2
* Open Test environment
* Login Account
* Open My account
* New Password
* Logout
* Login With New Password
* Open My account
* Old Password

## Change Default Stake
* Open Test environment
* Login Account
* Open My account
* Open Bet Settings
* Enter New Default Stake
* Open Main Page
* Wait "10" seconds
* Open My account
* Open Bet Settings
* Enter Old Default Stake
* Wait "10" seconds

## Change Odd Value Changes
* Open Test environment
* Login Account
* Open My account
* Open Bet Settings
* Change Odd Value Changes

## Make Withdrawal With PIX Out Method
* Open Test environment
* Login Account
* Open My account
* Open Withdrawal
* Choose to Payment Method with "pixInWithdrawalMethod"
* Genarete Random Withdrawal Value and Enter that value
* Select the Key Type and Enter the Key Number
* Select the Identity Type and Enter the Identity Number
* Continue To Transaction
* Verification For Transaction Succesfully
//* Compare the entered withdrawal amount "randomNumber" to amount "requestedAmountInWithdrawalPage" which inside the transactions info by converted text "requestedWithdraw"
////* Calculate the balance
//* Get Transaction Number In Withdrawal Page
//* Complete the transaction
//* Compare the transactions numbers

## Make Withdrawal With Bank Transfer Method
* Open Test environment
* Login Account
* Open My account
* Open Withdrawal
* Choose to Payment Method with "bankTransferInWithdrawalMethod"
* Genarete Random Withdrawal Value and Enter that value
* Select the BT Identity Type and Enter the Identity Number
* Select the Bank Name and Enter the Bank Branch
* Select Account Type and Enter Account Number and Account Digit
* Continue To Transaction
* Verification For Transaction Succesfully

## Make Withdrawal With Crypto Out Method
* Open Test environment
* Login Account
* Open My account
* Open Withdrawal
* Choose to Payment Method with "cryptoOutInWithdrawalMethod"
* Genarete Random Withdrawal Value and Enter that value
* Enter Account Number For Crypto
* Continue To Transaction
* Verification For Transaction Succesfully

## Make Deposite With PIX In Method
tags:makeDepositWithPIXMethod
* Open Test environment
* Login Account
* Open My account
* Choose to Payment Method with "depositPIXMethod"
* Genarete Random PIX Deposit Value and Enter that value
* Continue To Transaction For PIX Deposit
* Confirmation Of Redirecting To Pagsmile Page
* Get Transaction Number In Pagsmile Page
* Enter Pagsmile Details
* Click on Perform Payment
* Continue to Main Site
* Payment Confirmation and Go to transaction Page
* Go to Side Menu Bar
* Go to Transaction History Page
//* Get Transaction Number In Transaction History Page
* Compare the transactions numbers from pagsmile Page to Website page

## Make Deposite With Boleto In Method
tags:makeDepositWithBoletoMethod
* Open Test environment
* Login Account
* Open My account
* Choose to Payment Method with "depositBoletoMethod"
* Genarete Random Boleto Deposit Value and Enter that value
* Continue To Transaction For Boleto Deposit
* Confirmation Of Redirecting To Pagsmile Page
* Get Transaction Number In Pagsmile Page
* Enter Pagsmile Details
* Enter CEP Details
* Click on Perform Payment
* Continue to Main Site For Boleto
* Payment Confirmation and Go to transaction Page
* Go to Side Menu Bar
* Go to Transaction History Page
//* Get Transaction Number In Transaction History Page
* Compare the transactions numbers from pagsmile Page to Website page
//* Enter Account Details
//* Pay Amount

## Make Deposite With Express In Method
tags:makeDepositWithItauMethod
* Open Test environment
* Login Account
* Open My account
* Choose to Payment Method with "depositITAUMethod"
//* Genarete Random ITAU Deposit Value and Enter that value
* Continue To Transaction For ITAU Deposit
* Confirmation Of Redirecting To Pagsmile Page
* Get Transaction Number In Pagsmile Page
* Enter Pagsmile Details
* Click on Perform Payment
* Continue to Main Site
* Payment Confirmation and Go to transaction Page
* Go to Side Menu Bar
* Go to Transaction History Page
//* Get Transaction Number In Transaction History Page
* Compare the transactions numbers from pagsmile Page to Website page

## Make Deposite With Crypto In Method
tags:makeDepositWithBinanceMethod
* Open Test environment
* Login Account
* Open My account
* Choose to Payment Method with "depositBinanceMethod"
* Genarete Random Binance Deposit Value and Enter that value
* Continue To Transaction For Binance Deposit
* Pay Amount

## Make Deposite With Lottery In Method
tags:makeDepositWithPagueNaLotericaMethod
* Open Test environment
* Login Account
* Open My account
* Choose to Payment Method with "depositPagueNaLotericaMethod"
* Genarete Random PagueNaLoterica Deposit Value and Enter that value
* Continue To Transaction For PagueNaLoterica Deposit
* Confirmation Of Redirecting To Pagsmile Page
* Get Transaction Number In Pagsmile Page
* Enter Pagsmile Details
* Click on Perform Payment
* Continue to Main Site
* Payment Confirmation and Go to transaction Page
* Go to Side Menu Bar
* Go to Transaction History Page
//* Get Transaction Number In Transaction History Page
* Compare the transactions numbers from pagsmile Page to Website page










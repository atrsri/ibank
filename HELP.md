# Read Me First
I used java 17 compiler.
I used H2 disk storage DB.

#Code Coverage
87.3% for main/java classes overall 96%

#In H2 console 
connection Url: jdbc:h2:tcp://localhost/~/ibank
username : sa
password is empty
select - Generic H2 (Server) for settings

#Validations
Beneficiary Id number should be greater than 0
Uppercase of beneficiary ifsc code should startswith "IFSC"

Business validations done. No much field level validation done, Please don't neglect to review the code.


#Insert Accounts table data

localhost:8080/ibanker/addAccount, Post Method

Request body 1

{
    "accName":"Srinivasan",
    "phone":"12345666",
    "email":"srini@gmail.com",
    "status":"active"
}

Request body 2

{
    "accName":"vasan",
    "phone":"2300967",
    "email":"vasan@yahoomail.com",
    "status":"active"
}

Request body 3

{
    "accName":"Subramanian",
    "phone":"3456967",
    "email":"subu@yahoo.com",
    "status":"active"
}

#Insert Account-balance
localhost:8080/ibanker/addAccountBalance, Post Method

requestBody 1
{
    "accounts":{
        "id":1
    },
    "balance":10000
    
}

requestBody2

{
    "accounts":{
        "id":2
    },
    "balance":5000
    
}

request Body 3
{
    "accounts":{
        "id":3
    },
    "balance":3000
    
}

#Insert Beneficiary:

url: localhost:8080/ibanker/beneficiary/insertBeneficiary, Post method

RequestBody 1
{
    "accounts":{
        "id":1
    },
    "beneId":123094355,
    "beneIfsc":"IFSC00001",
    "beneName":"Sample 1",
    "status":"Active"
}

RequestBody 2
{
    "accounts":{
        "id":1
    },
    "beneId":444535466,
    "beneIfsc":"IFSC00002",
    "beneName":"Sample 2",
    "status":"Inactive"
}

Request Body 3

{
    "accounts":{
        "id":2
    },
    "beneId":75443554,
    "beneIfsc":"ISFC0003",
    "beneName":"Sample 3",
    "status":"Active"
}

#Update beneficiary

Patch method Url: localhost:8080/ibanker/beneficiary/updateIfsc?ifsc=IFSC00003&id=75443554

#Transactions 

Put Method url : localhost:8080/ibanker/transMoney(use "credit" or "debit" for transType to deposit or withdraw transactions
Request body ex :
{
    "accounts":{
        "id":1
    },
    "transType":"debit",    
    "amount":500
    
}

#Get transaction list

get method url : localhost:8080/ibanker/getAllTransactions

---No Request Body----------------

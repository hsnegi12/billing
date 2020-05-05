This is a Billing system project.

###Project specs

Maven project using SpringBoot as framework.

Tech stack : java

##API's required:

1.) fetchCustomerBills

2.) fetchReceipt


###Other write and authorization APIs,

1.) createCustomer

2.) persistBillToDB

3.) getAuthorizationToken

## Database used : MySQL

###Databases 

1.) Bill : Keeps records of all bills generated

2.) Customer : Keeps record of all customers

3.) Receipt : Keeps records of all receipts.


###Setting up the environment before testing 
1.) Considering local testing, MySQL should be installed in local machine 

2.) application.yml file needs to be configured. Path in which the same file is to be found src/main/resources.

Configuration which needs to be addressed. Line number 4,

4 ---> url: jdbc:mysql://localhost:3306/

Line number 5 & 6 respectively,

5 ---> username:

6 ---> password: 


###One round of sample testing step by step

NOTE : Assuming that postman is used for testing.

1.) Run your application. Database and tables will be created if not already created.

2.) Add customer using POST type and end point as /customer/add

Sample JSON : 

{

    "mobileNumber" : "<value>",
    
    "name" : "<value>"     
}

3.) Add bill using POST type and end point as /bill/add

Sample JSON :

{

    "billerBillID" : "<value>",
    
    "recurrence" : "<value>",
    
    "amountExactness" : "<value>",
    
    "customerAccountId" : "<value>",
    
    "amountValue" : <value>

}

4.) Fetch authorization token using GET type and end point as authorization/get

No i/p needed. A token string will be generated. Copy it!

####NOTE : This token will be valid only for 2 minutes.

5.) Fetch customer bills using GET type and end point as /bill/get

####Additional task : Add a header with key as Authorization and value as String generated in step 4

Sample JSON :

{

    "customerIdentifiers": 
        {
            "attributeName": "mobileNumber",
            "attributeValue": "xxxxxxxxxx"
        }
}

6.) Fetch receipt using GET type and end point as /receipt/get

####Additional task : Add a header with key as Authorization and value as String generated in step 4

Sample JSON :

{

    "billerBillID"   : "12123131322",
    "platformBillID" : "SETU121341312121"
    "paymentDetails" : {
        "platformTransactionRefID" : "TXN12121219",
        "uniquePaymentRefID"       : "XXXXAYYDDD999999",
        "amountPaid" : {
            "value" : 99000 
        },
        "billAmount" : {
            "value" : 99000
        }
    }
}
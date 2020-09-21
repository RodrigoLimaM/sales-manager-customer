# Sales Manager Customer Service

Microsservice for customer management of the sales-manager architecture

* **URL**\
/customer

* **Method:**\
 `POST`
 
 *  **Request Body**
    ```json
    {
      "balance": 0
    }
    ```
 * **Success Response:**
 
    * **Code:** 200 <br />
        **Content:**
    ```json
    {
      "_id": "5f677ec4ab6b5402f7ce30f4",
      "balance": 560
    }
    ```
    
* **Error Response:**

    * **Code:** 400 <br />
        **Content:** 
    ```json
    {
      "timestamp": "2020-09-20T13:09:56.7218531",
      "status": 400,
      "message": "Invalid null or blank field"
    }
    ```
  
 * **Architecture:**
 
    ![Alt text](https://user-images.githubusercontent.com/51386403/93714108-e232b080-fb36-11ea-9881-894dd0f900a2.png "Architecture")
    * 1 - Will receive an order, create it and persist on MongoDB with status ***PENDING***;
    * 2 - The persisted order will be produced on ***NEW_ORDER*** Kafka topic;
    * 3 - Will listen to the topic and check if the customer has a balance;
    * 4 - Will produce a message on ***ORDER_STATUS_CHANGE*** Kafka topic updating the order status (***FINISHED*** or ***CANCELLED***);
    * 5 - Will listen to the topic and update the order status on MongoDB.
    
    
    
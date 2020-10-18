# Sales Manager Customer Service

Microsservice for customer management of the sales-manager architecture

* **URL**\
/customer

* **Method:**\
 `POST`
 
 *  **Request Body**
    ```json
    {
      "balance": 560,
      "email": "rodrigolima@email.com",
      "name": "Rodrigo Lima",
      "password": "12345Ab#"
    }
    ```
 * **Success Response:**
 
    * **Code:** 200 <br />
        **Content:**
    ```json
    {
      "_id": "5f8b358e7eaaac60794a971e",
      "balance": 560,
      "name": "Rodrigo Lima",
      "email": "rodrigolima@email.com",
      "password": "12345Ab#",
      "creationDate": "2020-10-17T15:18:54.6607379",
      "updateDate": "2020-10-17T15:18:54.6607379"
    }
    ```
    
* **Error Response:**

    * **Code:** 400 <br />
        **Content:** 
    ```json
    {
      "timestamp": "2020-10-17T15:16:25.7291067",
      "status": 400,
      "message": [
        "password : invalid password : rejected value [12345678]",
        "email : invalid email : rejected value [test@test]",
        "name : não deve estar em branco : rejected value []",
        "balance : não deve ser nulo : rejected value [null]"
      ]
    }
    ```
  
* **URL**\
/customer/{customerId}

* **Method:**\
 `GET`

  *  **Path Variables**

     `customerId={String}`
 * **Success Response:**

    * **Code:** 200 <br />
        **Content:**
    ```json
    {
      "_id": "5f8b358e7eaaac60794a971e",
      "balance": 560,
      "name": "Rodrigo Lima",
      "email": "rodrigolima@email.com",
      "password": "12345Ab#",
      "creationDate": "2020-10-17T15:18:54.66",
      "updateDate": "2020-10-17T15:18:54.66"
    }
    ```
  
 * **Architecture:**
 
    ![Alt text](https://user-images.githubusercontent.com/51386403/95694559-0ef35880-0c09-11eb-9667-9ae838b4d40f.png "Architecture")
    * 1 - Will receive an order and check if stock is available;
    * 2 - If has stock, will create the order and persist on MongoDB with status ***PROCESSING_PAYMENT***;
    * 3 - The persisted order will be produced on ***NEW_ORDER*** Kafka topic;
    * 4 - Will listen to the topic and check if the customer has available balance;
    * 5 - Will produce a message on ***ORDER_STATUS_CHANGE*** Kafka topic updating the order status (***APPROVED*** or ***CANCELLED***);
    * 6 - Will listen to the topic, update the order status to ***PREPARING_FOR_SHIPPING*** (if order was ***APPROVED***)  and produce a message with the changes in the order;
    * 7 - Will listen to the topic and update the order status on MongoDB;
    * 8 - Will update the product stock (if order was ***APPROVED***);
    * 9 - Occasionally will produce messages as the order status changes and persist the changes on MongoDB.
# Order Processing System in Java with MySQL
This is a GUI `Java` application that can process orders from clients. It works with a `MySQL` database. Uses the `JDBC` driver.  

**Use Cases**...  
As you can see a lot of stuff can be done like adding shippers, searching for products, orders etc.  
![image](https://user-images.githubusercontent.com/37183688/41970634-267204a4-7a14-11e8-98b9-c1679d92f865.png)  

![image](https://user-images.githubusercontent.com/37183688/41970640-2b3bdfb4-7a14-11e8-81ac-dfded7cba0d7.png)  
This is the **diagram** of the relational database. A dump file is included in the project. Export it to your `MySql` Framework. The database was made using `MySql WorkBench`.
![image](https://user-images.githubusercontent.com/37183688/41970656-379ad972-7a14-11e8-8640-b64979a1d2e9.png)  

## Features  
#### 3 Tier package Architecture:    
   - The **Presentation** layer consists of 4 classes and is organized on the MVC model:  
     - `View`: it contains all the methods for constructing the GUI  
     - `Model`: contains the methods that will further communicate with the Model classes  
     - `Controller`: adds the functionality of the Model class to the View  
	 - `TableContent`: it is an enum and it is used to store the current state of the table showing on the screen(in the class View there is   only 1 table and its content is always changed according to which button is pressed)  
	- The **Model** layer contains 1 parent class and 6 child classes. For every table in the database, there is a corresponding class with   EXACTLY the same field names and field data types.  
	  -  `Category`, `Client`,` Supplier`, `Shipper`, `Product`, `Orders` – are the child classes  
	  - `AbstractModel` is the parent class and it extends `AbstractBLL` class(so every model class extends `AbstractModel<T extends AbstractBLL>`   where T is the corresponding BLL class for every table. In this way, for every model class, its corresponding BLL class can be stored   easily so the programmer does not need to verify it each time for what model class what bll class corresponds…  
	- The **Model** Layer(BLL) contains classes that implement the application logic. The `AbstractBLL` abstract class contains the methods for   communicating with the `DAO` classes(update/insert/delete/find). But it also contains methods which can be applied to different model   objects before inserting them into the database or after extracting them from the database. For every model class there is a    corresponding BLL class which extends the parent `AbstractBLL` and each of these classes override the `getValidators()` method which returns   a List with the validators that need to be applied only to that particular model class.   
	  -  validators: there is another subpackage named `validators` in the bll package which contains all the classes that are used for validating data: and Email validator, a Phone validator, and a Date validator. These classes implement the interface `Validator` and add   functionality to the one, single method in the interface: validate.  
	  - data:  for now, this subpackage contains only one class named `OrderFile` which does not else but creates a .txt file for each order.  
	- The **Infrastructure** layer is further divided into 2 more packages: `dao` and `connection`.  
	  - `connection`: contains a singleton class `ConnectionFactory` which is responsible for accessing the database, retrieving connections and   closing connections.  
	  - `dao`: contains an abstract class named `AbstractDAO` which implements all the necessary operations which need to be implemented on a table like: insert, delete, find by id, find all, update. It works with generic model objects. There are 6 other `dao` classes which are the   children of `AbstractDAO` and they are the corresponding `dao` classes for the model classes one by one and they add full functionality to   the parent class in the sense that the parent class knows with what type of data it is working on.  
![image](https://user-images.githubusercontent.com/37183688/41971763-3bbdc5a6-7a18-11e8-884f-8bee74ef7e43.png)  

 #### UML Diagram
 - The UML diagram in the package `dao`:  
![image](https://user-images.githubusercontent.com/37183688/41971828-6bbddafc-7a18-11e8-9432-31ff4fde1d58.png)
 - The UML diagram in the package `model`:  
![image](https://user-images.githubusercontent.com/37183688/41971830-6d4d8660-7a18-11e8-8632-c5ca2223a809.png)
 - The UML diagrams in the package `bll` with its 2 subpackages:  
![image](https://user-images.githubusercontent.com/37183688/41971834-6ef44918-7a18-11e8-897b-f9bded927aa3.png) 
![image](https://user-images.githubusercontent.com/37183688/41971835-70a5a98c-7a18-11e8-814b-8824a2534d71.png)
 - The UML diagram in the package `presentation`:  
![image](https://user-images.githubusercontent.com/37183688/41971842-73df0940-7a18-11e8-80c6-e54c7ddb89cf.png)  
### Uses `JOptionPane` for operations:
![image](https://user-images.githubusercontent.com/37183688/41972120-835d5286-7a19-11e8-886c-e0bd12bd324d.png)
![image](https://user-images.githubusercontent.com/37183688/41972127-8d9ae2b8-7a19-11e8-9684-e18c038159e6.png)
![image](https://user-images.githubusercontent.com/37183688/41972129-8fe794b2-7a19-11e8-8a99-762184c98aff.png)
![image](https://user-images.githubusercontent.com/37183688/41972138-9840801a-7a19-11e8-87f8-dfd76cf74df9.png)
![image](https://user-images.githubusercontent.com/37183688/41972139-99921c1c-7a19-11e8-9b15-c0a562511c7f.png)
![image](https://user-images.githubusercontent.com/37183688/41972154-a7ccc6ce-7a19-11e8-8a78-df6d5d50e989.png)
### Uses `JTable Click Listeners`:  
- For updating a row, just press any cell and after modifying it, press the **UPDATE** button  
![image](https://user-images.githubusercontent.com/37183688/41972183-b8f016c2-7a19-11e8-9591-73a8d2a30b2e.png)  


- For deleting a row, click on it and press the **DELETE** button
![image](https://user-images.githubusercontent.com/37183688/41972280-11845a5a-7a1a-11e8-8c1e-df12807a97e8.png)
![image](https://user-images.githubusercontent.com/37183688/41972299-23db288c-7a1a-11e8-932d-96bf12570890.png)
  
    
    
### Search is very simple:
- Searching products which are under stock(have less than 5 available pieces):  
![image](https://user-images.githubusercontent.com/37183688/41972326-3d91b37c-7a1a-11e8-8ec5-fe306f19d50f.png)
![image](https://user-images.githubusercontent.com/37183688/41972330-3f836a40-7a1a-11e8-82af-f8bf5af4f14b.png)
### Uses validators, cannot order product that is under stock etc.
![image](https://user-images.githubusercontent.com/37183688/41972632-15a4a210-7a1b-11e8-97b1-68c2f622c79d.png)
### Creates .txt files for every order
- they are stored in the  *invoices* folder
![image](https://user-images.githubusercontent.com/37183688/41972420-82460040-7a1a-11e8-92db-a7b8a245c3a3.png)
![image](https://user-images.githubusercontent.com/37183688/41972427-84bfd404-7a1a-11e8-8101-71a4a2e9aab5.png)
![image](https://user-images.githubusercontent.com/37183688/41972429-89f2adca-7a1a-11e8-949b-6346b520892e.png)  



## Little GUIDE
- After the application starts, the end user will see this:
 ![image](https://user-images.githubusercontent.com/37183688/41971915-c305fbd2-7a18-11e8-9791-660f9fa922d9.png)  
- To view other tables, one button from the top needs to be pressed:  
 ![image](https://user-images.githubusercontent.com/37183688/41971932-d2989bcc-7a18-11e8-98d7-26d68f4b8176.png)
- For operations, use the buttons on the left:  
 ![image](https://user-images.githubusercontent.com/37183688/41971949-e23f6880-7a18-11e8-852a-814f1402a5f5.png)  
 - 	For updating a row, simply select the cell you want to modify, double click it and then modify to your needs. Then either press enter or double click elsewhere. Then press the `update` button. 
-	For inserting a new row, press the `insert` button, and then introduce the new fields. Be attentive when introducing the values and introduce only valid data. Press `OK`. If you get the message: `Insert Successful`, it means that you inserted the row successfully else you will get a warning message alongside a log in the console. Check it to further explanation of the error.
-	For deleting a row, simply click once a row, and it will be colored in blue. Press the `delete` button. Whoala.
-	For searching for specific elements, you have to first choose the field for which you are applying the filter then in the input box, type your data for which you want to search. If the chosen field has a `String` format then the option bigger/smaller don’t have any purpose for now. Just press OK. All the rows having the field that contain the `String` you entered will appear. For `number` formats, use the bigger/smaller button to search for bigger, smaller values. If you do not select any of the 2, only equal values will be showed to you. When searching for `Date` format, you can use the bigger/smaller option as well to search for previous dates or dates after a given date.
-	To show the total number of rows, simply press the `totals` button and then enter. For the table `Orders` and `Product` there is one more option for selecting the total number of quantities.
-	GOOD LUCK!





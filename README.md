# contactapp

### Usage
```sh
$ git clone [https://github.com/hiren22/contactapp]
$ cd contactapp
$ mvn clean install
$ java -jar target/contactapp.jar
```
### Features
* CRUD operation for contact app
* Email should be unique
* Delete by contact by email
* Get contact with pagination with name or email 
* Search contact with pagination with name or email based on string match like %name_str%

### URL
* http://localhost:8080/swagger-ui.html#!
* Pass username=admin and pwd=trypwd to authenticate
* Save contact:POST http://localhost:8080/rest/contactapp/v1/  --data @user.json
* Get contacts:GET http://localhost:8080/rest/contactapp/v1/contacts?name=user1&page=1
* Search contacts:GET http://localhost:8080/rest/contactapp/v1/contacts?email=pattern&page=1
* Delete id:DELETE http://localhost:8080/rest/contactapp/v1/contacts/user1

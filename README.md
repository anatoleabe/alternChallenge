# CANCUN Hotel Management
Hotel Booking System

## I. Technology Stack
```
- Spring Framework 4
- Spring boot 2.6
- Java 8
- MySQL 5.7
```

## II. How to Run
```

### 1. Run the API

1. clone this repository
2. install JDK 1.8
3. install MySQL 5.7
    - run at localhost:3306 
    - create databases with name 'cancundb'
    - use default user with name 'root' and password ''
4. cd to api folder 
5. run mvn clean install
6. run mvn spring-boot:run
``` 

### 2. Run the webapp
1. cd to webapp folder 
2. run mvn clean install
3. run mvn spring-boot:run
``` 

## III. Test in the browser
-  Test the application starting from the url http://localhost:9001/ in the browser and start checking the available room. 

- The API is also available at http://localhost:9000/


## IV. Admin Dashboard
This section describes the list of available dashboard.
The URL is {host}/admin/resources, for example http://localhost:9001/admin/reservations

- Create a room using this end point from the browser: http://localhost:9001/room/create
- delete a room http://localhost:9001/room/delete/{id}
- Update a room http://localhost:9001/room/update/{id}



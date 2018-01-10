# restaurant-delivery

- Database DDL located in project.sql
- Backend Java files located in src/main/java/del/res
- JSP Files located in src/main/webapp
- Tests located in src/test/java

## Basic Description

This is a Java-based Full-Stack MVC Web Application.

Long story short, that means this project involves everything from making and communicating with a Database, to creating a decent-looking front end that works, to testing pretty much everything.

That being said, you can pretty much seperate everything into a few different pieces:
- Infrastructure
  - Oracle 12c Database
  - Tomcat v9.0.2 Web Server
- Controllers
  - JDBC Data Access Objects
  - Java Servlets
  - Spring MVC Servlets
- Models
  - Java Classes
- Views
  - JSP Files
  - CSS Files
- Tests
  - TestNG Unit Tests
  - Cucumber + Selenium + JUnit Runner Interface Tests

As for how everything interacts, you can think about it this way:
- A user visits our (JSP) login page, enters their login info and clicks "Log In"
- A Servlet receives their login info and then passes it to
- A JDBC Data Access Object, which uses it to query the database
- The results of that query are stored in a Model
- The Servlet then sees that Model and either stores it for future use or
- Passes that Model to another JSP page to show the user its data

## Setup

**Note: This project makes use of Oracle 12c and Tomcat v9.0.2.**

To set up the database, just run the database_setup.sql in SQL Developer. You may want to run the first set of lines (the ones that create our user) before the rest, just in case anything weird happens.

After that, make sure you configure Tomcat to work with your IDE and assign the Tomcat Runtime v9.0 to this project.

At the point, you should be good to go!

>Should

##
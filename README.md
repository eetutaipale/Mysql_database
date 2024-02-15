This is my MySQL database communication tool for storing different data to my local database. It uses Vaadin to create interface for my project and java to handle communication.

File Functions

Application.java
    Starts the program

MainView.java
  Desings the interface of the program

ApplicationServiceInitListener.java
  Creates the table and inserts information if there is not a table in the       database

table_exist.java
  Used to see if there is a table in the database by lookin for the size of the table

Database_change
    Insert and deletes data from the database
    
Director.java, Movie.java, MovieService.java
  Left from Vaadins desing for the program, will be changed later

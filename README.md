# Lab3 Advanced Repository
## Виконав
- Студент групи ІА-22
- Птачик Роман
- Варіант: 15 (С3 = 0)
## How to launch project
### Clone the repository:
```
git clone https://github.com/FryMondo/lab2Advanced.git
```
### Running project
If you use IDE, open project and run ***SQLGeneratorDemo.java***
### Running project via console
Move to the directory
```
lab3Advanced/src
```
Run next commands
```
javac *.java
java SQLGeneratorDemo
```
## Project Structure
### SQLGeneratorDemo.java
Executes the main logic of the program.
- Creates instances of User, Cat, and Product.
- Demonstrates the functionality of SQLGenerator for each class:
  - Generates SQL queries for CREATE TABLE, INSERT, SELECT, and DELETE.
- Outputs the generated SQL queries to the console.
### SQLGenerator.java
A generic class responsible for generating SQL queries for classes annotated with @Table and @Column.
- Methods:
  - generateCreateTable: Generates a CREATE TABLE SQL query based on the annotated fields.
  - generateInsert: Generates an INSERT SQL query using field values from the provided object.
  - generateSelectAll: Generates a SELECT * SQL query for the annotated class.
  - generateDeleteById: Generates a DELETE SQL query for a specific record identified by an ID column and value.
  - mapFieldTypeToSQLType: Maps Java field types to corresponding SQL types.
- Throws IllegalArgumentException if the class or fields lack necessary annotations.
### Annotations
- @Table
  - Annotates classes to define the corresponding table name in the database.
  - Applied at the class level.
  - Field: name (the name of the table).
- @Column
  - Annotates fields to specify their mapping to database columns.
  - Applied at the field level.
  - Field: name (the name of the column).
### User.java
Represents a user entity for database operations.
- Fields: id, name, email.
- Annotated with @Table(name = "users") and @Column.
### Cat.java
Represents a cat entity for database operations.
- Fields: id, name, age.
- Annotated with @Table(name = "cats") and @Column.
### Product.java
Represents a product entity for database operations.
- Fields: id, name, price.
- Annotated with @Table(name = "products") and @Column.
## Key Functionalities
1. Dynamic SQL Generation
- Automatically generates SQL queries for annotated classes without requiring manual SQL coding.
2. Annotations for Mapping
- Uses @Table and @Column annotations to map Java classes and fields to database tables and columns.
3. Type Safety
- Ensures that SQL generation operates on strongly-typed classes, reducing runtime errors.
4. Demonstration
- Demonstrates functionality using diverse examples (User, Cat, Product).
- Covers all CRUD operations.

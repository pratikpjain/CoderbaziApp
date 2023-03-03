# <span style = "color: wheat">CoderbaziApp</span> 🚀🚀🚀
> An application where we will writing a lot of APIs and creating some fun stuffs!

<h4>Tech-Stack: Java Spring boot, Mysql, Rest API</h4>

**Want to Contribute and make your hands dirty💻?**
<br>
<br>
Follow this steps:
1) <span style = "color: Cyan">Clone the repository</span>
    - Open your <span style = "color: green">IDE</span> and run this command in <span style = "color: violet">Terminal</span> <br> 
    ```sh
        git clone https://github.com/pratikpjain/CoderbaziApp.git
    ```
    - Though you can use any IDE but Intellij IDE is recommended for this project. <a href="https://www.jetbrains.com/idea/">IntelliJ IDE</a>
    
2) <span style = "color: Cyan">To run the application</span>
    - Go to `com.example.CoderBazi.CoderBaziApplication` and run this Java file.
    - You can open it at <a href = "http://localhost:8080">`http://localhost:8080/`</a>
    - Make sure mysql database is running at port `3306` in your machine.

3) <span style = "color: Cyan">To add any new feature or any changes</span>
    - Create a new branch 
    ```sh
    git branch -b branch_name
    ```
    - Make the changes here
    - Test it on local [You can use <a href="https://www.postman.com/">POSTMAN</a> for testing APIs]
    - Raise a PR(Pull Request)
    - Get review done from one of our contributers
    - Then merge it to main!
    - ☠<b>Note</b>: Please don't directly push any code to Main branch without reviewing it.

4) <span style = "color: Cyan">Database Migration</span>
   - Go to `src\main\resources\db\migration`
   - Create a new file with the naming conventions `V{MigrationNumber}__{Description}.sql`
   - For example we are adding a new column to table `Users` and let this is the `3rd` Migration, then name should be `V3__AddNewColumnToUsers.sql`
   - For running the migration, you can do Maven Install on IDE or simply run the app it would make the migrations.
   - <b>Note</b>: Not following this conventions may lead to error in migratons

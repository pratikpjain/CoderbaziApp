# <span style = "color: wheat">CoderbaziApp</span> 🚀🚀
> An application where we will writing a lot of APIs and creating some fun stuffs!

**Want to Contribute and make your hands dirty💻?**<br>
Follow this steps:
1) <span style = "color: Cyan">Clone the repository</span>
    - Open your <span style = "color: green">IDE</span> and run this command in <span style = "color: violet">Terminal</span> <br> 
    `git clone https://github.com/pratikpjain/CoderbaziApp.git`
2) <span style = "color: Cyan">To run the application</span>
    - Go to `com.example.CoderBazi.CoderBaziApplication` and run this Java file.
3) <span style = "color: Cyan">To add any new feature or any changes</span>
    - Create a new branch `git branch -b branch_name`
    - Make the changes here
    - Raise a PR(Pull Request)
    - Get review from one of our contributers
    - Then merge it to main!
    - ☠<b>Note</b>: Please don't directly push any code to Main branch without reviewing it.
4) <span style = "color: Cyan">Database Migration</span>
   - Go to `src\main\resources\db\migration`
   - Create a new file with the naming conventions `V{MigrationNumber}__{Description}.sql`
   - For example we are adding a new column to table `Users` and let this is the `3rd` Migration, then name should be `V3__AddNewColumnToUsers.sql`
   - <b>Note</b>: Not following this conventions may lead to error in migratons

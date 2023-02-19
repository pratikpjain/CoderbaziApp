# CoderbaziApp 🚀🚀
> An application where we will writing a lot of APIs and creating some fun stuffs!

Follow this steps:
1) Clone the repository
    - Open you IDE and enter this command in Terminal `git clone https://github.com/pratikpjain/CoderbaziApp.git`
2) To run the application
    - Go to `com.example.CoderBazi.CoderBaziApplication` and run this Java file.
3) To add any new feature or any changes
    - Create a new branch `git branch -b branch_name`
    - Make the changes here
    - Raise a PR(Pull Request)
    - Get review from one of our contributers
    - Then merge it to main!
    - ☠️**Note** Please don't directly push any code to Main without reviewing it.
4) Database Migration
   - Go to `src\main\resources\db\migration`
   - Create a new file with the naming conventions `V{MigrationNumber}__{Description}.sql`
   - For example we are adding a new column to table `Users` and let this is the `3rd` Migration, then name should be `V3__AddNewColumnToUsers.sql`
   - Note: Not following this conventions may lead to error in migratons

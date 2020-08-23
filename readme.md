# What It Does #
This system takes data from customerData.txt and creates a database of Account objects containing Vehicle objects corresponding to the information loaded. It then processes transactions from transactions.txt and deducts or adds funds according to what was loaded. The system produces exceptions if the Account doesn't exist or funds are lacking for that account.

I used this project to understand more intermediate-level Java as well as practice software engineering concepts by creating a UML Diagram of my proposed system before starting, which made programming the system easier since I already had the groundwork laid and an overview in mind.

# What I Learned #
* Intermediate Java concepts (file I/O, exceptions, enums etc)
* OOP Concepts (inheritance, abstraction and polymorphism)
* Software Engineering ([UML](https://tjeepot.github.io/projects/toll-road-simulator/UML.pdf), basic system design principles)

# Usage Notes #
Compile in your favorite Java editor, or on command line using javac. Ensure customerData.txt and transactions.txt is in the project root. This program has no command-line arguments.
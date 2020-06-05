CREATE DATABASE Restaurant

USE Restaurant

CREATE TABLE Employees(
	DNI CHAR(9) NOT NULL,
	[Password] VARCHAR(30) NOT NULL,
	[Name] VARCHAR(30) NOT NULL,
	[Surname] VARCHAR(30) NOT NULL,
	NAF VARCHAR(12) NOT NULL,
	Birthday DATE NOT NULL,
	Position VARCHAR(13) NULL,
	Category VARCHAR(13) NULL,
	BankAccount CHAR(40) NULL,
	CONSTRAINT PK_Employees PRIMARY KEY (DNI)
)


CREATE TABLE Payslips(
	ID INT IDENTITY NOT NULL,
	DNIEmployee CHAR(9) NOT NULL,
	Salary MONEY NOT NULL,
	CONSTRAINT PK_Payslips PRIMARY KEY (ID),
	CONSTRAINT FK_Payslips_DNIEmployee FOREIGN KEY (DNIEmployee) REFERENCES Employees(DNI) ON DELETE NO ACTION ON UPDATE NO ACTION
)


CREATE TABLE Schedules(
	ID INT IDENTITY NOT NULL,
	DNIEmployee CHAR(9) NOT NULL,
	[WeekDay] VARCHAR(10) NOT NULL,
	StartDate SMALLDATETIME NOT NULL,
	EndDate SMALLDATETIME NOT NULL,
	Salary MONEY NOT NULL,
	CONSTRAINT PK_Schedules PRIMARY KEY (ID),
	CONSTRAINT FK_Schedules_DNIEmployee FOREIGN KEY (DNIEmployee) REFERENCES Employees(DNI) ON DELETE NO ACTION ON UPDATE NO ACTION
)


CREATE TABLE Products(
	ID INT IDENTITY NOT NULL,
	[Name] VARCHAR(20) NOT NULL,
	[Characteristics] VARCHAR(30) NOT NULL,
	Price MONEY NULL,
	CONSTRAINT PK_Products PRIMARY KEY (ID)
)


CREATE TABLE Orders(
	ID INT IDENTITY NOT NULL,
	DNIEmployee CHAR(9) NOT NULL,
	DateOrder SMALLDATETIME NOT NULL,
	[Sent] BIT NULL DEFAULT (0),
	Cancel BIT NULL DEFAULT (0),
	CONSTRAINT PK_Orders PRIMARY KEY (ID),
	CONSTRAINT FK_Orders_DNIEmployee FOREIGN KEY (DNIEmployee) REFERENCES Employees(DNI) ON DELETE NO ACTION ON UPDATE NO ACTION
)


CREATE TABLE OrdersLines(
	ID INT IDENTITY NOT NULL,
	IDOrder INT NOT NULL,
	IDProduct INT NOT NULL,
	Quantity INT NOT NULL,
	CONSTRAINT PK_OrdersLines PRIMARY KEY (ID),
	CONSTRAINT FK_OrdersLines_IDOrder FOREIGN KEY (IDOrder) REFERENCES Orders(ID) ON DELETE NO ACTION ON UPDATE NO ACTION,
	CONSTRAINT FK_OrdersLines_IDProduct FOREIGN KEY (IDProduct) REFERENCES Products(ID) ON DELETE NO ACTION ON UPDATE NO ACTION
)


















CREATE TABLE Foods(
	ID INT IDENTITY NOT NULL,
	[Name] VARCHAR(20) NOT NULL,
	[Description] VARCHAR(30) NOT NULL,
	Price MONEY NULL,
	[Type] VARCHAR(9) NULL
)


CREATE TABLE Drinks(
	ID INT IDENTITY NOT NULL,
	[Name] VARCHAR(20) NOT NULL,
	[Description] VARCHAR(30) NOT NULL,
	Price MONEY NULL,
	Proof DECIMAL(2,1) NULL DEFAULT (0.0),
	Sparkling BIT NULL DEFAULT (0)
)


CREATE TABLE Allergies(
	ID INT IDENTITY NOT NULL,
	[Name] VARCHAR(12) NOT NULL
)


CREATE TABLE Receipts(
	ID INT IDENTITY NOT NULL,
	DateOfReceipt SMALLDATETIME NOT NULL,
	PaidFor BIT NULL DEFAULT (0)
)


CREATE TABLE ReceiptsLines(
	ID INT IDENTITY NOT NULL,
	IDReceipt INT NOT NULL,
	IDConsumable INT NOT NULL,
	Quantity INT NOT NULL
)








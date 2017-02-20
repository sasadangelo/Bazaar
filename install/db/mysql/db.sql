# --------------------------------------------------------------------------------
# Copyleft (C) 2006 OpenCommunity
# author Salvatore D'Angelo
# --------------------------------------------------------------------------------

# --------------------------------------------------------------------------------
# Table structure for table `Accounts`
# --------------------------------------------------------------------------------

CREATE TABLE Accounts (
  nID bigint NOT NULL,
  txtEmailAddress varchar(50) NOT NULL,
  txtPassword varchar(50) NOT NULL,
  bAdmin tinyint(4) NOT NULL default '0',
  nActivationCode bigint NOT NULL,
  bActivationStatus tinyint(4) NOT NULL default '0',
  txtFirstname varchar(20) NOT NULL, 
  txtLastname varchar(20) NOT NULL, 
  txtStreetAddress text NOT NULL,
  txtZipCode varchar(20) NOT NULL,
  txtCity varchar(15) NOT NULL,
  txtState varchar(15) NOT NULL,
  txtCountry varchar(40) NOT NULL,
  txtPhone varchar(25) NOT NULL,
  tsLastChange timestamp NOT NULL,
  tsRegTime timestamp NOT NULL,
  INDEX Acc_I1(txtEmailAddress),
  PRIMARY KEY(nID)
) ENGINE=INNODB;

INSERT INTO Accounts VALUES (1, 'admin@bazaar.it', 'admin', 1, 0, 1, 'Mario', 'Rossi', 'via Roma, 10', '99', 'Roma', 'Roma', 'IT', '339717171', 20031126144201, 20031126144144);

# --------------------------------------------------------------------------------
# Table structure for table `Categories`
# --------------------------------------------------------------------------------

CREATE TABLE Categories (
  nID bigint NOT NULL,
  nParentID bigint,
  txtName varchar(20) NOT NULL,
  txtFullName varchar(512) NOT NULL,
  txtDescription varchar(30),
  PRIMARY KEY  (nID),
  INDEX Cat_I1(nParentID),
  INDEX Cat_I2(txtName),
  FOREIGN KEY (nParentID) REFERENCES Categories(nID) ON DELETE CASCADE 
) ENGINE=INNODB;

# --------------------------------------------------------------------------------
# Table structure for table `Products`
# --------------------------------------------------------------------------------

CREATE TABLE Products (
  nID bigint NOT NULL,
  txtName varchar(20) NOT NULL,
  txtShortDescription varchar(30) NOT NULL,
  txtDescription text,
  bytImage blob,
  bytThumbImage blob,
  nQuantity integer NOT NULL default '1',
  bActive tinyint(4) NOT NULL default '1',
  dblPrice double NOT NULL default '0',
  nAccountID bigint NOT NULL,
  PRIMARY KEY(nID),
  INDEX Prod_I1(nAccountID),
  FOREIGN KEY(nAccountID) REFERENCES Accounts(nID) ON DELETE CASCADE
) ENGINE=INNODB;

# --------------------------------------------------------------------------------
# Table structure for table `CategoryProducts`
# --------------------------------------------------------------------------------

CREATE TABLE CategoryProducts (
  nCategoryID bigint NOT NULL,
  nProductID bigint NOT NULL,
  INDEX CatProd_I1(nCategoryID),
  INDEX CatProd_I2(nProductID),
  FOREIGN KEY (nCategoryID) REFERENCES Categories(nID) ON DELETE CASCADE,
  FOREIGN KEY (nProductID)  REFERENCES Products  (nID) ON DELETE CASCADE,
  PRIMARY KEY(nCategoryID, nProductID)
) ENGINE=INNODB;

# --------------------------------------------------------------------------------
# Table structure for table `Orders`
# --------------------------------------------------------------------------------

CREATE TABLE Orders (
  nID bigint NOT NULL,
  nBuyerID bigint NOT NULL,
  nSellerID bigint  NOT NULL,
  nProductID bigint NOT NULL,
  nQuantity int(11) NOT NULL,
  nOrderState int(11) NOT NULL,
  dblTotal double NOT NULL,
  dblShippingCharges double NOT NULL,
  dblGrandTotal double NOT NULL,
  txtRemarks text,
  tsDate timestamp NOT NULL,
  INDEX OrdAcc_I1(nBuyerID),
  INDEX OrdAcc_I2(nSellerID),
  INDEX OrdProd_I3(nProductID),
  FOREIGN KEY(nBuyerID) REFERENCES Accounts(nID) ON DELETE CASCADE,
  FOREIGN KEY(nSellerID) REFERENCES Accounts(nID) ON DELETE CASCADE,
  FOREIGN KEY(nProductID) REFERENCES Products(nID) ON DELETE CASCADE,
  PRIMARY KEY(nID)
) ENGINE=INNODB;

# --------------------------------------------------------------------------------
# Table structure for table `OrderShippingAddress`
# --------------------------------------------------------------------------------

CREATE TABLE OrderShippingAddress (
  nID bigint NOT NULL,
  nOrderID bigint NOT NULL,
  txtFirstname varchar(20) NOT NULL, 
  txtLastname varchar(20) NOT NULL, 
  txtStreetAddress text NOT NULL,
  txtZipCode varchar(20) NOT NULL,
  txtCity varchar(15) NOT NULL,
  txtState varchar(15) NOT NULL,
  txtCountry varchar(40) NOT NULL,
  txtPhone varchar(25) NOT NULL,
  txtShippingMethod varchar(20) NOT NULL,
  INDEX OrdShip_I1(nOrderID),
  FOREIGN KEY(nOrderID) REFERENCES Orders(nID) ON DELETE CASCADE,
  PRIMARY KEY(nID)
) ENGINE=INNODB;

# --------------------------------------------------------------------------------
# Table structure for table `ShippingCompanies`
# --------------------------------------------------------------------------------

CREATE TABLE ShippingCompanies (
  nID bigint NOT NULL,
  txtName varchar(30) NOT NULL,
  txtURL text,
  PRIMARY KEY  (nID)
) ENGINE=INNODB;

# --------------------------------------------------------------------------------
# Table structure for table `ShippingMethods`
# --------------------------------------------------------------------------------

CREATE TABLE ShippingMethods (
  nID bigint NOT NULL,
  nShippingCompanyID bigint NOT NULL,
  txtName varchar(30) NOT NULL,
  txtDescription text NOT NULL,
  INDEX ShipMeth_I1(nShippingCompanyID),
  FOREIGN KEY(nShippingCompanyID) REFERENCES ShippingCompanies(nID) ON DELETE CASCADE,
  PRIMARY KEY  (nID)
) ENGINE=INNODB;

# --------------------------------------------------------------------------------
# Table structure for table `ShippingCharges`
# --------------------------------------------------------------------------------

CREATE TABLE ShippingCharges (
  nProductID bigint NOT NULL,
  nShippingMethodID bigint NOT NULL,
  dblShippingCharges double NOT NULL,
  INDEX ShipCharges_I1(nProductID),
  INDEX ShipCharges_I2(nShippingMethodID),
  FOREIGN KEY(nProductID) REFERENCES Products(nID) ON DELETE CASCADE,
  FOREIGN KEY(nShippingMethodID) REFERENCES ShippingMethods(nID) ON DELETE CASCADE,
  PRIMARY KEY(nProductID, nShippingMethodID)
) ENGINE=INNODB;

# --------------------------------------------------------------------------------
# Table structure for table `PaymentMethod`
# --------------------------------------------------------------------------------

CREATE TABLE PaymentMethods (
  nID bigint NOT NULL,
  nType int(11) NOT NULL,
  nAccountID bigint NOT NULL,
  INDEX AccPay_I1(nAccountID),
  FOREIGN KEY(nAccountID) REFERENCES Accounts(nID) ON DELETE CASCADE,
  PRIMARY KEY(nID)
) ENGINE=INNODB;

# --------------------------------------------------------------------------------
# Table structure for table `BonificoPaymentMethod`
# --------------------------------------------------------------------------------

CREATE TABLE BonificoPaymentMethod (
  nCc int(16) NOT NULL,
  nAbi int(11) NOT NULL,
  nCab int(11) NOT NULL,
  nCin varchar(1) NOT NULL,
  nPaymentID bigint NOT NULL,
  INDEX BonPay_I1(nPaymentID),
  FOREIGN KEY(nPaymentID) REFERENCES PaymentMethods(nID) ON DELETE CASCADE,
  PRIMARY KEY(nAbi, nCab)
) ENGINE=INNODB;

# --------------------------------------------------------------------------------
# Table structure for table `PaypalPaymentMethod`
# --------------------------------------------------------------------------------

CREATE TABLE PaypalPaymentMethod (
  txtEmailAddress varchar(50) NOT NULL,
  txtPassword varchar(50) NOT NULL,
  nPaymentID bigint NOT NULL,
  INDEX PaypalPay_I1(nPaymentID),
  FOREIGN KEY(nPaymentID) REFERENCES PaymentMethods(nID) ON DELETE CASCADE,
  PRIMARY KEY(txtEmailAddress)
) ENGINE=INNODB;

# --------------------------------------------------------------------------------
# Table structure for table `Oid`
# --------------------------------------------------------------------------------

CREATE TABLE Oid (
  txtTableName varchar(50) NOT NULL,
  nLastId bigint NOT NULL,
  nBlockSize int(11) NOT NULL
) ENGINE=INNODB;

INSERT INTO Oid(txtTableName, nLastId, nBlockSize) VALUES ("ACCOUNT", 0, 10);
INSERT INTO Oid(txtTableName, nLastId, nBlockSize) VALUES ("CATEGORY", 0, 10);
INSERT INTO Oid(txtTableName, nLastId, nBlockSize) VALUES ("PRODUCT", 0, 10);
INSERT INTO Oid(txtTableName, nLastId, nBlockSize) VALUES ("ORDER", 0, 10);
INSERT INTO Oid(txtTableName, nLastId, nBlockSize) VALUES ("ORDERSHIPPINGADDRESS", 0, 10);


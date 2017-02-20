# phpMyAdmin MySQL-Dump
# version 2.3.2
# http://www.phpmyadmin.net/ (download page)
#
# Host: localhost
# Generation Time: Nov 15, 2004 at 02:37 AM
# Server version: 3.23.58
# PHP Version: 4.3.5
# Database : `JspCart`
# --------------------------------------------------------

#
# Table structure for table `Accounts`
#

CREATE TABLE Accounts (
  nID int(11) NOT NULL,
  txtEmailAddress varchar(50) NOT NULL,
  txtPassword varchar(50) NOT NULL,
  bAdmin tinyint(4) NOT NULL default '0',
  txtFirstname varchar(20) NOT NULL, 
  txtLastname varchar(20) NOT NULL, 
  txtStreetAddress text,
  txtZipCode varchar(20) NOT NULL,
  txtCity varchar(15) NOT NULL,
  txtState varchar(15) NOT NULL,
  txtCountry varchar(40) NOT NULL,
  txtPhone varchar(25) NOT NULL,
  bSendPromotion tinyint(4) NOT NULL default '0',
  bNeverSendMail tinyint(4) NOT NULL default '0',
  dblCredit double NOT NULL,
  tsLastChange timestamp(14) NOT NULL,
  tsRegTime timestamp(14) NOT NULL,
  PRIMARY KEY(nID)
) TYPE=INNODB;

INSERT INTO Accounts VALUES (1, 'admin@bazaar.it', 'admin', 1, 'Bazaar', 'Administrator', 'Somewhere', '99', 'City', 'State', 'US', '99999999999', 'None', '""', 0, 0, '0', 20031126144201, 20031126144144);

#
# Table structure for table `Categories`
#

CREATE TABLE Categories (
  nID int(11) NOT NULL auto_increment,
  nParentID int(11) default '0',
  txtName varchar(100) default NULL,
  txtDescription text,
  txtTitle text,
  txtMeta text,
  txtHeader text,
  txtFooter text,
  txtMetaReplyTo text,
  txtMetaLanguage text,
  txtMetaDistribution text,
  txtMetaCopyright text,
  txtMetaClassification text,
  txtMetaAuthor text,
  txtMetaRevisitAfter text,
  txtMetaDescription text,
  txtMetaRobots text,
  txtMetaKeywords text,
  PRIMARY KEY  (nID),
  INDEX Cat_Index(nParentID),
  FOREIGN KEY (nParentID) REFERENCES Categories(nID) ON DELETE CASCADE 
) TYPE=INNODB;

#
# Dumping data for table `Categories`
#

# --------------------------------------------------------

#
# Table structure for table `Products`
#

CREATE TABLE Products (
  nID int(11) NOT NULL default '0',
  txtName varchar(100) NOT NULL default '',
  txtDescription varchar(30),
  txtLongDescription text,
  txtImgUrl text,
  txtWeight varchar(50) NOT NULL default '',
  dblQtyPerPack double NOT NULL default '1',
  dblPricePerQty double NOT NULL default '0',
  bActive int(1) NOT NULL default '1',
  dblPrice double NOT NULL default '0',
  dblDummyPrice double NOT NULL default '0',
  dblShippingFactor double NOT NULL default '0',
  dblCostPerQty double NOT NULL default '0',
  dblCost double NOT NULL default '0',
  bShow int(11) NOT NULL default '0',
  txtEmailAddress varchar(50) NOT NULL default '',
  PRIMARY KEY(nID),
  INDEX User_Index(txtEmailAddress),
  FOREIGN KEY(txtEmailAddress) REFERENCES Accounts(txtEmailAddress) ON DELETE CASCADE
) TYPE=INNODB;

#
# Dumping data for table `Products`
#

# --------------------------------------------------------

#
# Table structure for table `CategoryProducts`
#

CREATE TABLE CategoryProducts (
  nCategoryID int(11) NOT NULL default '0',
  nProductID int(11) NOT NULL default '0',
  nOrder int(11) NOT NULL default '0',
  INDEX Cat1_Index(nCategoryID),
  INDEX Cat2_Index(nProductID),
  FOREIGN KEY (nCategoryID) REFERENCES Categories(nID) ON DELETE CASCADE,
  FOREIGN KEY (nProductID)  REFERENCES Products  (nID) ON DELETE CASCADE 
) TYPE=INNODB;

#
# Dumping data for table `CategoryProducts`
#

# --------------------------------------------------------

#
# Table structure for table `Countries`
#

CREATE TABLE Countries (
  txtID char(2) NOT NULL default '',
  txtName varchar(40) NOT NULL default '',
  dblRegisteredPost double NOT NULL default '15',
  dblExpressCourier double NOT NULL default '20',
  PRIMARY KEY  (txtID)
) TYPE=MyISAM;

#
# Dumping data for table `Countries`
#

INSERT INTO Countries VALUES ('AF', 'Afghanistan', '15', '20');
INSERT INTO Countries VALUES ('AL', 'Albania', '15', '20');
INSERT INTO Countries VALUES ('DZ', 'Algeria', '15', '20');
INSERT INTO Countries VALUES ('AS', 'American Samoa', '15', '20');
INSERT INTO Countries VALUES ('AD', 'Andorra', '15', '20');
INSERT INTO Countries VALUES ('AO', 'Angola', '15', '20');
INSERT INTO Countries VALUES ('AI', 'Anguilla', '15', '20');
INSERT INTO Countries VALUES ('AQ', 'Antarctica', '15', '20');
INSERT INTO Countries VALUES ('AG', 'Antigua and Barbuda', '15', '20');
INSERT INTO Countries VALUES ('AR', 'Argentina', '15', '20');
INSERT INTO Countries VALUES ('AM', 'Armenia', '15', '20');
INSERT INTO Countries VALUES ('AW', 'Aruba', '15', '20');
INSERT INTO Countries VALUES ('AU', 'Australia', '15', '20');
INSERT INTO Countries VALUES ('AT', 'Austria', '15', '20');
INSERT INTO Countries VALUES ('AZ', 'Azerbaidjan', '15', '20');
INSERT INTO Countries VALUES ('BS', 'Bahamas', '15', '20');
INSERT INTO Countries VALUES ('BH', 'Bahrain', '15', '20');
INSERT INTO Countries VALUES ('BD', 'Banglades', '15', '20');
INSERT INTO Countries VALUES ('BB', 'Barbados', '15', '20');
INSERT INTO Countries VALUES ('BY', 'Belarus', '15', '20');
INSERT INTO Countries VALUES ('BE', 'Belgium', '15', '20');
INSERT INTO Countries VALUES ('BZ', 'Belize', '15', '20');
INSERT INTO Countries VALUES ('BJ', 'Benin', '15', '20');
INSERT INTO Countries VALUES ('BM', 'Bermuda', '15', '20');
INSERT INTO Countries VALUES ('BT', 'Bhutan', '15', '20');
INSERT INTO Countries VALUES ('BO', 'Bolivia', '15', '20');
INSERT INTO Countries VALUES ('BA', 'Bosnia-Herzegovina', '15', '20');
INSERT INTO Countries VALUES ('BW', 'Botswana', '15', '20');
INSERT INTO Countries VALUES ('BV', 'Bouvet Island', '15', '20');
INSERT INTO Countries VALUES ('BR', 'Brazil', '15', '20');
INSERT INTO Countries VALUES ('IO', 'British Indian O. Terr.', '15', '20');
INSERT INTO Countries VALUES ('BN', 'Brunei Darussalam', '15', '20');
INSERT INTO Countries VALUES ('BG', 'Bulgaria', '15', '20');
INSERT INTO Countries VALUES ('BF', 'Burkina Faso', '15', '20');
INSERT INTO Countries VALUES ('BI', 'Burundi', '15', '20');
INSERT INTO Countries VALUES ('KH', 'Cambodia', '15', '20');
INSERT INTO Countries VALUES ('CM', 'Cameroon', '15', '20');
INSERT INTO Countries VALUES ('CA', 'Canada', '15', '20');
INSERT INTO Countries VALUES ('CV', 'Cape Verde', '15', '20');
INSERT INTO Countries VALUES ('KY', 'Cayman Islands', '15', '20');
INSERT INTO Countries VALUES ('CF', 'Central African Rep.', '15', '20');
INSERT INTO Countries VALUES ('TD', 'Chad', '15', '20');
INSERT INTO Countries VALUES ('CL', 'Chile', '15', '20');
INSERT INTO Countries VALUES ('CN', 'China', '15', '20');
INSERT INTO Countries VALUES ('CX', 'Christmas Island', '15', '20');
INSERT INTO Countries VALUES ('CC', 'Cocos (Keeling) Isl.', '15', '20');
INSERT INTO Countries VALUES ('CO', 'Colombia', '15', '20');
INSERT INTO Countries VALUES ('KM', 'Comoros', '15', '20');
INSERT INTO Countries VALUES ('CG', 'Congo', '15', '20');
INSERT INTO Countries VALUES ('CK', 'Cook Islands', '15', '20');
INSERT INTO Countries VALUES ('CR', 'Costa Rica', '15', '20');
INSERT INTO Countries VALUES ('HR', 'Croatia', '15', '20');
INSERT INTO Countries VALUES ('CU', 'Cuba', '15', '20');
INSERT INTO Countries VALUES ('CY', 'Cyprus', '15', '20');
INSERT INTO Countries VALUES ('CZ', 'Czech Republic', '15', '20');
INSERT INTO Countries VALUES ('CS', 'Czechoslovakia', '15', '20');
INSERT INTO Countries VALUES ('DK', 'Denmark', '15', '20');
INSERT INTO Countries VALUES ('DJ', 'Djibouti', '15', '20');
INSERT INTO Countries VALUES ('DM', 'Dominica', '15', '20');
INSERT INTO Countries VALUES ('DO', 'Dominican Republi', '15', '20');
INSERT INTO Countries VALUES ('TP', 'East Timor', '15', '20');
INSERT INTO Countries VALUES ('EC', 'Ecuador', '15', '20');
INSERT INTO Countries VALUES ('EG', 'Egypt', '15', '20');
INSERT INTO Countries VALUES ('SV', 'El Salvador', '15', '20');
INSERT INTO Countries VALUES ('GQ', 'Equatorial Guinea', '15', '20');
INSERT INTO Countries VALUES ('EE', 'Estonia', '15', '20');
INSERT INTO Countries VALUES ('ET', 'Ethiopia', '15', '20');
INSERT INTO Countries VALUES ('FK', 'Falkland Isl.(Malvinas)', '15', '20');
INSERT INTO Countries VALUES ('FO', 'Faroe Islands', '15', '20');
INSERT INTO Countries VALUES ('FJ', 'Fiji', '15', '20');
INSERT INTO Countries VALUES ('FI', 'Finland', '15', '20');
INSERT INTO Countries VALUES ('FR', 'France', '15', '20');
INSERT INTO Countries VALUES ('FX', 'France (European Ter.)', '15', '20');
INSERT INTO Countries VALUES ('TF', 'French Southern Terr.', '15', '20');
INSERT INTO Countries VALUES ('GA', 'Gabon', '15', '20');
INSERT INTO Countries VALUES ('GM', 'Gambia', '15', '20');
INSERT INTO Countries VALUES ('GE', 'Georgia', '15', '20');
INSERT INTO Countries VALUES ('DE', 'Germany', '15', '20');
INSERT INTO Countries VALUES ('GH', 'Ghana', '15', '20');
INSERT INTO Countries VALUES ('GI', 'Gibraltar', '15', '20');
INSERT INTO Countries VALUES ('GB', 'Great Britain (UK)', '15', '20');
INSERT INTO Countries VALUES ('GR', 'Greece', '15', '20');
INSERT INTO Countries VALUES ('GL', 'Greenland', '15', '20');
INSERT INTO Countries VALUES ('GD', 'Grenada', '15', '20');
INSERT INTO Countries VALUES ('GP', 'Guadeloupe (Fr.)', '15', '20');
INSERT INTO Countries VALUES ('GU', 'Guam (US)', '15', '20');
INSERT INTO Countries VALUES ('GT', 'Guatemala', '15', '20');
INSERT INTO Countries VALUES ('GF', 'Guiana (Fr.)', '15', '20');
INSERT INTO Countries VALUES ('GN', 'Guinea', '15', '20');
INSERT INTO Countries VALUES ('GW', 'Guinea Bissau', '15', '20');
INSERT INTO Countries VALUES ('GY', 'Guyana', '15', '20');
INSERT INTO Countries VALUES ('HT', 'Haiti', '15', '20');
INSERT INTO Countries VALUES ('HM', 'Heard and McDonald Isl.', '15', '20');
INSERT INTO Countries VALUES ('HN', 'Honduras', '15', '20');
INSERT INTO Countries VALUES ('HK', 'Hong Kong', '15', '20');
INSERT INTO Countries VALUES ('HU', 'Hungary', '15', '20');
INSERT INTO Countries VALUES ('IS', 'Iceland', '15', '20');
INSERT INTO Countries VALUES ('IN', 'India', '15', '20');
INSERT INTO Countries VALUES ('ID', 'Indonesia', '15', '20');
INSERT INTO Countries VALUES ('IR', 'Iran', '15', '20');
INSERT INTO Countries VALUES ('IQ', 'Iraq', '15', '20');
INSERT INTO Countries VALUES ('IE', 'Ireland', '15', '20');
INSERT INTO Countries VALUES ('IL', 'Israel', '15', '20');
INSERT INTO Countries VALUES ('IT', 'Italy', '15', '20');
INSERT INTO Countries VALUES ('CI', 'Ivory Coast', '15', '20');
INSERT INTO Countries VALUES ('JM', 'Jamaica', '15', '20');
INSERT INTO Countries VALUES ('JP', 'Japan', '15', '20');
INSERT INTO Countries VALUES ('JO', 'Jordan', '15', '20');
INSERT INTO Countries VALUES ('KZ', 'Kazachstan', '15', '20');
INSERT INTO Countries VALUES ('KE', 'Kenya', '15', '20');
INSERT INTO Countries VALUES ('KI', 'Kiribati', '15', '20');
INSERT INTO Countries VALUES ('KP', 'Korea (North)', '15', '20');
INSERT INTO Countries VALUES ('KR', 'Korea (South)', '15', '20');
INSERT INTO Countries VALUES ('KW', 'Kuwait', '15', '20');
INSERT INTO Countries VALUES ('KG', 'Kyrgistan', '15', '20');
INSERT INTO Countries VALUES ('LA', 'Laos', '15', '20');
INSERT INTO Countries VALUES ('LV', 'Latvia', '15', '20');
INSERT INTO Countries VALUES ('LB', 'Lebanon', '15', '20');
INSERT INTO Countries VALUES ('LS', 'Lesotho', '15', '20');
INSERT INTO Countries VALUES ('LR', 'Liberia', '15', '20');
INSERT INTO Countries VALUES ('LY', 'Libya', '15', '20');
INSERT INTO Countries VALUES ('LI', 'Liechtenstein', '15', '20');
INSERT INTO Countries VALUES ('LT', 'Lithuania', '15', '20');
INSERT INTO Countries VALUES ('LU', 'Luxembourg', '15', '20');
INSERT INTO Countries VALUES ('MO', 'Macau', '15', '20');
INSERT INTO Countries VALUES ('MK', 'Macedonia', '15', '20');
INSERT INTO Countries VALUES ('MG', 'Madagascar', '15', '20');
INSERT INTO Countries VALUES ('MW', 'Malawi', '15', '20');
INSERT INTO Countries VALUES ('MY', 'Malaysia', '15', '20');
INSERT INTO Countries VALUES ('MV', 'Maldives', '15', '20');
INSERT INTO Countries VALUES ('ML', 'Mali', '15', '20');
INSERT INTO Countries VALUES ('MT', 'Malta', '15', '20');
INSERT INTO Countries VALUES ('MH', 'Marshall Islands', '15', '20');
INSERT INTO Countries VALUES ('MQ', 'Martinique (Fr.)', '15', '20');
INSERT INTO Countries VALUES ('MR', 'Mauritania', '15', '20');
INSERT INTO Countries VALUES ('MU', 'Mauritius', '15', '20');
INSERT INTO Countries VALUES ('MX', 'Mexico', '15', '20');
INSERT INTO Countries VALUES ('FM', 'Micronesia', '15', '20');
INSERT INTO Countries VALUES ('MD', 'Moldavia', '15', '20');
INSERT INTO Countries VALUES ('MC', 'Monaco', '15', '20');
INSERT INTO Countries VALUES ('MN', 'Mongolia', '15', '20');
INSERT INTO Countries VALUES ('MS', 'Montserrat', '15', '20');
INSERT INTO Countries VALUES ('MA', 'Morocco', '15', '20');
INSERT INTO Countries VALUES ('MZ', 'Mozambique', '15', '20');
INSERT INTO Countries VALUES ('MM', 'Myanmar', '15', '20');
INSERT INTO Countries VALUES ('NA', 'Namibia', '15', '20');
INSERT INTO Countries VALUES ('NR', 'Nauru', '15', '20');
INSERT INTO Countries VALUES ('NP', 'Nepal', '15', '20');
INSERT INTO Countries VALUES ('AN', 'Netherland Antilles', '15', '20');
INSERT INTO Countries VALUES ('NL', 'Netherlands', '15', '20');
INSERT INTO Countries VALUES ('NT', 'Neutral Zone', '15', '20');
INSERT INTO Countries VALUES ('NC', 'New Caledonia (Fr.)', '15', '20');
INSERT INTO Countries VALUES ('NZ', 'New Zealand', '15', '20');
INSERT INTO Countries VALUES ('NI', 'Nicaragua', '15', '20');
INSERT INTO Countries VALUES ('NE', 'Niger', '15', '20');
INSERT INTO Countries VALUES ('NG', 'Nigeria', '15', '20');
INSERT INTO Countries VALUES ('NU', 'Niue', '15', '20');
INSERT INTO Countries VALUES ('NF', 'Norfolk Island', '15', '20');
INSERT INTO Countries VALUES ('MP', 'Northern Mariana Isl.', '15', '20');
INSERT INTO Countries VALUES ('NO', 'Norway', '15', '20');
INSERT INTO Countries VALUES ('OM', 'Oman', '15', '20');
INSERT INTO Countries VALUES ('PK', 'Pakistan', '15', '20');
INSERT INTO Countries VALUES ('PW', 'Palau', '15', '20');
INSERT INTO Countries VALUES ('PA', 'Panama', '15', '20');
INSERT INTO Countries VALUES ('PG', 'Papua New', '15', '20');
INSERT INTO Countries VALUES ('PY', 'Paraguay', '15', '20');
INSERT INTO Countries VALUES ('PE', 'Peru', '15', '20');
INSERT INTO Countries VALUES ('PH', 'Philippines', '15', '20');
INSERT INTO Countries VALUES ('PN', 'Pitcairn', '15', '20');
INSERT INTO Countries VALUES ('PL', 'Poland', '15', '20');
INSERT INTO Countries VALUES ('PF', 'Polynesia (Fr.)', '15', '20');
INSERT INTO Countries VALUES ('PT', 'Portugal', '15', '20');
INSERT INTO Countries VALUES ('PR', 'Puerto Rico (US)', '15', '20');
INSERT INTO Countries VALUES ('QA', 'Qatar', '15', '20');
INSERT INTO Countries VALUES ('RE', 'Reunion (Fr.)', '15', '20');
INSERT INTO Countries VALUES ('RO', 'Romania', '15', '20');
INSERT INTO Countries VALUES ('RU', 'Russian Federation', '15', '20');
INSERT INTO Countries VALUES ('RW', 'Rwanda', '15', '20');
INSERT INTO Countries VALUES ('LC', 'Saint Lucia', '15', '20');
INSERT INTO Countries VALUES ('WS', 'Samoa', '15', '20');
INSERT INTO Countries VALUES ('SM', 'San Marino', '15', '20');
INSERT INTO Countries VALUES ('SA', 'Saudi Arabia', '15', '20');
INSERT INTO Countries VALUES ('SN', 'Senegal', '15', '20');
INSERT INTO Countries VALUES ('SC', 'Seychelles', '15', '20');
INSERT INTO Countries VALUES ('SL', 'Sierra Leone', '15', '20');
INSERT INTO Countries VALUES ('SG', 'Singapore', '15', '20');
INSERT INTO Countries VALUES ('SK', 'Slovak Republic', '15', '20');
INSERT INTO Countries VALUES ('SI', 'Slovenia', '15', '20');
INSERT INTO Countries VALUES ('SB', 'Solomon Islands', '15', '20');
INSERT INTO Countries VALUES ('SO', 'Somalia', '15', '20');
INSERT INTO Countries VALUES ('ZA', 'South Africa', '15', '20');
INSERT INTO Countries VALUES ('ES', 'Spain', '15', '20');
INSERT INTO Countries VALUES ('LK', 'Sri Lanka', '15', '20');
INSERT INTO Countries VALUES ('SH', 'St. Helena', '15', '20');
INSERT INTO Countries VALUES ('PM', 'St. Pierre and Miquelon', '15', '20');
INSERT INTO Countries VALUES ('ST', 'St. Tome and Principe', '15', '20');
INSERT INTO Countries VALUES ('KN', 'St.Kitts Nevis Anguilla', '15', '20');
INSERT INTO Countries VALUES ('VC', 'St.Vincent and Grenadines', '15', '20');
INSERT INTO Countries VALUES ('SD', 'Sudan', '15', '20');
INSERT INTO Countries VALUES ('SR', 'Suriname', '15', '20');
INSERT INTO Countries VALUES ('SJ', 'Svalbard and Jan Mayen Is', '15', '20');
INSERT INTO Countries VALUES ('SZ', 'Swaziland', '15', '20');
INSERT INTO Countries VALUES ('SE', 'Sweden', '15', '20');
INSERT INTO Countries VALUES ('CH', 'Switzerland', '15', '20');
INSERT INTO Countries VALUES ('SY', 'Syria', '15', '20');
INSERT INTO Countries VALUES ('TJ', 'Tadjikistan', '15', '20');
INSERT INTO Countries VALUES ('TW', 'Taiwan', '15', '20');
INSERT INTO Countries VALUES ('TZ', 'Tanzania', '15', '20');
INSERT INTO Countries VALUES ('TH', 'Thailand', '15', '20');
INSERT INTO Countries VALUES ('TG', 'Togo', '15', '20');
INSERT INTO Countries VALUES ('TK', 'Tokelau', '15', '20');
INSERT INTO Countries VALUES ('TO', 'Tonga', '15', '20');
INSERT INTO Countries VALUES ('TT', 'Trinidad and Tobago', '15', '20');
INSERT INTO Countries VALUES ('TN', 'Tunisia', '15', '20');
INSERT INTO Countries VALUES ('TR', 'Turkey', '15', '20');
INSERT INTO Countries VALUES ('TM', 'Turkmenistan', '15', '20');
INSERT INTO Countries VALUES ('TC', 'Turks and Caicos Islands', '15', '20');
INSERT INTO Countries VALUES ('TV', 'Tuvalu', '15', '20');
INSERT INTO Countries VALUES ('UG', 'Uganda', '15', '20');
INSERT INTO Countries VALUES ('UK', 'Ukraine', '15', '20');
INSERT INTO Countries VALUES ('AE', 'United Arab Emirates', '15', '20');
INSERT INTO Countries VALUES ('US', 'United States', '15', '20');
INSERT INTO Countries VALUES ('UY', 'Uruguay', '15', '20');
INSERT INTO Countries VALUES ('UM', 'US Minor outlying Isl.', '15', '20');
INSERT INTO Countries VALUES ('UZ', 'Uzbekistan', '15', '20');
INSERT INTO Countries VALUES ('VU', 'Vanuatu', '15', '20');
INSERT INTO Countries VALUES ('VA', 'Vatican City State', '15', '20');
INSERT INTO Countries VALUES ('VE', 'Venezuela', '15', '20');
INSERT INTO Countries VALUES ('VN', 'Vietnam', '15', '20');
INSERT INTO Countries VALUES ('VG', 'Virgin Islands (British)', '15', '20');
INSERT INTO Countries VALUES ('VI', 'Virgin Islands (US)', '15', '20');
INSERT INTO Countries VALUES ('WF', 'Wallis and Futuna Islands', '15', '20');
INSERT INTO Countries VALUES ('EH', 'Western Sahara', '15', '20');
INSERT INTO Countries VALUES ('YE', 'Yemen', '15', '20');
INSERT INTO Countries VALUES ('YU', 'Yugoslavia', '15', '20');
INSERT INTO Countries VALUES ('ZR', 'Zaire', '15', '20');
INSERT INTO Countries VALUES ('ZM', 'Zambia', '15', '20');
INSERT INTO Countries VALUES ('ZW', 'Zimbabwe', '15', '20');
# --------------------------------------------------------

#
# Table structure for table `EmailTemplates`
#

CREATE TABLE EmailTemplates (
  txtName varchar(50) NOT NULL default '',
  txtSubject text NOT NULL,
  txtMessage text NOT NULL,
  txtVariables text NOT NULL,
  PRIMARY KEY  (txtName)
) TYPE=MyISAM;

#
# Dumping data for table `EmailTemplates`
#

INSERT INTO EmailTemplates VALUES ('ForgotPassword', 'La sua $SHOPNAME$ Password', 'Gentile $USER$,<br>\n<br>\nLa sua password e'' <b>$PASSWORD$</b>.<br>\n<br>\n---------------------------------------------<br>\nSaluti $SHOPNAME$\n', '$SHOPNAME$,$USER$,$PASSWORD$,$SHOPCOMPANY$');
INSERT INTO EmailTemplates VALUES ('OrderStatus', 'Stato del suo $SHOPNAME$ - ordine $ID$', '<font face=verdana size=2>Dear $USER$,\n\nQuesto e'' lo stato del suo ORDINE no. $ID$ su $SHOPNAME$.\n<BR>\n<HR size=1>\nOrdine ID : $ID$<BR>\nData : $ORDERDATE$<BR>\r\nQuantita'': $ORDERAMOUNT$<BR>\n\nStato : $ORDERSTATUS$<BR>\n\nNote: $REMARKS$<BR>\n\nTracciamento: $TRACKING$<BR>\n<BR>\n<BR>\n\nPer maggiori dettagli sull''ordine effettui il login su $SHOPWEBADDRESS$<BR>\n<BR>\nGrazie,<BR>\r\n$SHOPNAME$ Staff<BR></font>', '$SHOPNAME$,$ORDERID$,$USER$');
INSERT INTO EmailTemplates VALUES ('Signup', 'Registrazione di $USER$ in $SHOPNAME$', '<pre>\nCaro $USER$,\n\nGrazie per essersi registrato in $SHOPNAME$,<br>\nsaremo lieti di fornirle i migliori servizi a prezzi eccezionali.<br>\n<br>\nPer qualsiasi informazione puo'' contattarci al seguente indirizzo:<br>\n$SHOPSUPPORTEMAIL$<br>\n<br>\n$SHOPNAME$ Staff</pre>', '$USER$, $SHOPNAME$, $SHOPSUPPORTEMAIL$');
INSERT INTO EmailTemplates VALUES ('PendingPayment', 'Ordine Pendente - $ID$ su $SHOPNAME$', '<pre>\nGentile $USER$,\n\nIl pagamento per l''ordine $ID$ non e'' stato ancora da lei effettuato.\n\nLe ricordiamo che senza di esso noi non possiamo procedere con l''esecuzione dell''ordineil processamento dell''ordine.\n\n- $SHOPNAME$ Staff\n</pre>', '');
# --------------------------------------------------------

#
# Table structure for table `OrderShippingAddress`
#

CREATE TABLE OrderShippingAddress (
  nOrderID int(11) NOT NULL default '0',
  txtName varchar(20) default NULL,
  txtStreetAddress text,
  txtZipCode varchar(20) default NULL,
  txtCity varchar(20) default NULL,
  txtState varchar(20) default NULL,
  txtCountry varchar(20) default NULL,
  txtPhone varchar(25) default NULL,
  txtShippingMethod varchar(20) NOT NULL default '',
  PRIMARY KEY  (nOrderID)
) TYPE=MyISAM;

#
# Dumping data for table `OrderShippingAddress`
#

# --------------------------------------------------------

#
# Table structure for table `OrderedItems`
#

CREATE TABLE OrderedItems (
  nOrderID int(50) NOT NULL default '0',
  nProductID int(11) NOT NULL default '0',
  dblPrice double NOT NULL default '0',
  dblCost double NOT NULL default '0',
  nQuantity int(11) NOT NULL default '0',
  txtReason text
) TYPE=MyISAM;

#
# Dumping data for table `OrderedItems`
#

# --------------------------------------------------------

#
# Table structure for table `Orders`
#

CREATE TABLE Orders (
  nID int(11) NOT NULL auto_increment,
  txtEmailAddress varchar(50) NOT NULL default '',
  tsChangeTime timestamp(14) NOT NULL,
  tsDate timestamp(14) NOT NULL,
  nOrderState int(11) NOT NULL default '0',
  dblTotal double NOT NULL default '0',
  dblProcessingFees double NOT NULL default '0',
  dblShippingCharges double NOT NULL default '0',
  dblGrandTotal double NOT NULL default '0',
  dblPaid double NOT NULL default '0',
  txtRemarks text,
  txtTracking text,
  txtTrackingCompany text,
  txtTrackingURL text,
  PRIMARY KEY  (nID)
) TYPE=MyISAM;

#
# Dumping data for table `Orders`
#

# --------------------------------------------------------

#
# Table structure for table `ShippingCharges`
#

CREATE TABLE ShippingCharges (
  txtCountryID varchar(40) NOT NULL default '',
  dblExpressCourier double NOT NULL default '0',
  dblRegisteredMail double NOT NULL default '0',
  PRIMARY KEY  (txtCountryID)
) TYPE=MyISAM;

#
# Dumping data for table `ShippingCharges`
#

# --------------------------------------------------------

#
# Table structure for table `ShippingCompanies`
#

CREATE TABLE ShippingCompanies (
  nID int(11) NOT NULL auto_increment,
  txtName varchar(100) NOT NULL default '',
  txtURL text,
  txtTrackingURL text,
  PRIMARY KEY  (nID)
) TYPE=MyISAM;

#
# Dumping data for table `ShippingCompanies`
#

INSERT INTO ShippingCompanies VALUES (1, 'FedEx', 'http://www.fedex.com', 'http://www.fedex.com/tracking/');
# --------------------------------------------------------

#
# Table structure for table `Variables`
#

CREATE TABLE Variables (
  txtName varchar(100) NOT NULL default '',
  txtValue text,
  PRIMARY KEY  (txtName)
) TYPE=MyISAM;

#
# Dumping data for table `Variables`
#

INSERT INTO Variables VALUES ('ProcessingCharge', '0');
INSERT INTO Variables VALUES ('MaximumProcessingValue', '0');
INSERT INTO Variables VALUES ('USD-2-INR', '46');


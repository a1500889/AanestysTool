CREATE TABLE Aanestys
(AanestysID INT NOT NULL AUTO_INCREMENT,
Tunnus varchar(15) NOT NULL,
AanestysNimi varchar(30) NOT NULL,
Kuvaus varchar(255), PRIMARY KEY(AanestysID)
);


CREATE TABLE Vaihtoehto(
VaihtoehtoID int PRIMARY KEY,
AanestysID int NOT NULL,
VaihtoehtoNimi varchar(30) NOT NULL,
 FOREIGN KEY(AanestysID) REFERENCES Aanestys(AanestysID));


CREATE TABLE Aani
(AaniID INT PRIMARY KEY AUTO_INCREMENT,
VaihtoehtoID INT NOT NULL,
AanestysID INT NOT NULL,
FOREIGN KEY (VaihtoehtoID) REFERENCES Vaihtoehto(VaihtoehtoID),
FOREIGN KEY (AanestysID) REFERENCES Aanestys(AanestysID) );
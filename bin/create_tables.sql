create table Økt(
	ØktID int primary key auto_increment, 
    Dato date, 
    Tidspunkt time, 
    Varighet double, 
    Treningssenter char(40));

create table Notat(
	NotatID int primary key auto_increment,
    Tekstfelt text(1023));

create table ØktNotat(
	ØktID int,
    NotatID int,
    
    foreign key(ØktID)
    references Økt(ØktID),
	
    foreign key(NotatID)
    references Notat(NotatID)
    );
    
create table Øvelse(
	Øvelsesnavn char(40) not null primary key,
	SubClass CHAR(1) NOT NULL
    #CONSTRAINT UQ_Øvelse UNIQUE (Øvelsesnavn, SubClass)
    );

create table Friøvelse(
	Øvelsesnavn char(40) not null primary key,
	SubClass CHAR(1) NOT NULL,
    Beskrivelse text(1023),
    CONSTRAINT CHK_Fri CHECK (SubClass = 'F')
    #CONSTRAINT FK_Øvelse_Friøvelse FOREIGN KEY (Øvelsesnavn, Subclass) REFERENCES Øvelse(Øvelsesnavn, Subclass)
    );
    
create table Apparatøvelse(
	Øvelsesnavn char(40) not null primary key,
	SubClass CHAR(1) NOT NULL,
    Kilo double,
    Sett int,
    CONSTRAINT CHK_App CHECK (SubClass = 'A')
    #CONSTRAINT FK_Øvelse_Apparatøvelse FOREIGN KEY (Øvelsesnavn, Subclass) REFERENCES Øvelse(Øvelsesnavn, Subclass)
    );


create table Øvelsesgruppe(
	Muskelgruppe char(128) primary key);

create table Apparat(
	Apparatnavn  char(128) primary key,
	Beskrivelse text(1023));

create table ØvelseIØkt(
	ØktID int ,
    Øvelsesnavn char(40) , 
    Prestasjon int,
    primary key (ØktID,Øvelsesnavn),
    
    foreign key(ØktID)
    references Økt(ØktID),
    
    foreign key(Øvelsesnavn)
    references Øvelse(Øvelsesnavn)
    );

create table ØvelseIGruppe(
	Øvelsesnavn char(40) ,
    Muskelgruppe char(128) ,
    primary key (Muskelgruppe,Øvelsesnavn),
    
    foreign key(Øvelsesnavn)
    references Øvelse(Øvelsesnavn),
    
    foreign key(Muskelgruppe)
    references Øvelsesgruppe(Muskelgruppe)
    );

create table ApparatTilØvelse(
	Øvelsesnavn char(40) ,
    Apparatnavn  char(128) ,
    primary key (Apparatnavn,Øvelsesnavn),
    
    foreign key(Øvelsesnavn)
    references Øvelse(Øvelsesnavn),
    
    foreign key(Apparatnavn)
    references Apparat(Apparatnavn)
    );



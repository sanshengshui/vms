CREATE TABLE IF NOT EXISTS Terminal (
       terminalId varchar(20) PRIMARY KEY,
       terminalCode varchar(20),
       terminalName nvarchar(20),
       terminalType varchar(20),
       stationId varchar(20),
       createUser varchar(15),
       createTime datetime,
       updateUser char(15),
       updateTime datetime,
       operator varchar(15),
       deleted boolean
);

CREATE TABLE IF NOT EXISTS WorkStation(
    stationId varchar(20) PRIMARY KEY,
    stationCode varchar(20),
    stationName nvarchar(50),
    lotNum varchar(15),
    sn int,
    description nvarchar(50),
    lineId varchar(20),
    createUser varchar(15),
    createTime datetime,
    updateUser varchar(15),
    updateTime datetime,
    deleted boolean
);

CREATE TABLE IF NOT EXISTS Defect(
    defectId varchar(20) PRIMARY KEY,
    defectName nvarchar(50),
    shortcut varchar(20),
    baseId varchar(20),
    stationId varchar(20),
    createUser varchar(15),
    createTime datetime,
    updateUser varchar(15),
    updateTime datetime,
    deleted boolean
);

CREATE TABLE IF NOT EXISTS ProductLine(
        lineId varchar(20) PRIMARY KEY,
        lineCode varchar(20),
        locationId varchar(20),
        defectVersion varchar(15),
        createUser varchar(15),
        createTime datetime,
		updateUser varchar(15),
        updateTime datetime,
        deleted boolean
);

CREATE TABLE IF NOT EXISTS Lot(
    lotNum varchar(15) PRIMARY KEY,
    modelNum varchar(15),
    count int,
    createUser varchar(15),
    createTime datetime,
    updateUser varchar(15),
    updateTime datetime,
    deleted boolean
);

CREATE TABLE IF NOT EXISTS DefectRecord(
    recordId varchar(20) PRIMARY KEY,
    defectId varchar(20),
    terminalId varchar(20),
    stationId varchar(20),
    lotNum varchar(15),
    count int,
    date int,
    time int,
    recorder varchar(15),
    responser varchar(15),
    recordTime varchar(15),
    deleted boolean
);

CREATE TABLE IF NOT EXISTS Location(
    locationId varchar(20) PRIMARY KEY,
    locationName nvarchar(45),
    createUser varchar(15),
    createTime datetime,
    updateUser varchar(15),
    updateTime datetime,
    deleted boolean
);

CREATE TABLE IF NOT EXISTS BaseInfo(
    baseId varchar(20) primary key,
    value varchar(20),
    purpose varchar(20),
    createUser varchar(15),
    createTime datetime,
    updateUser varchar(15),
    updateTime datetime,
    deleted boolean
);

CREATE TABLE IF NOT EXISTS Employee(
    employeeId varchar(20) primary key,
    employeeName nvarchar(45),
    cardCode varchar(20)
);

CREATE TABLE IF NOT EXISTS DateTimeDim(
    dateTime varchar(12) primary key,
    year int,
    month int,
    day int,
    quarter int,
    hour int,
    workDate varchar(8),
    shift nvarchar(20)
);

CREATE TABLE IF NOT EXISTS User(
    userId varchar(20) PRIMARY KEY,
    userName nvarchar(50),
    account varchar(20),
    password varchar(20),
    email varchar(20),
    phoneNumber varchar(15),
    deleted boolean
);
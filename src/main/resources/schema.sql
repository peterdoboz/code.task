DROP TABLE IF EXISTS cat;
DROP TABLE IF EXISTS dog;
CREATE TABLE cat (
    id VARCHAR(255) PRIMARY KEY,
    pettype VARCHAR(255) NOT NULL,
    trackertype VARCHAR(255) NOT NULL,
    ownerid bigint NOT NULL,
    inzone BOOLEAN NOT NULL,
    losttracker BOOLEAN
);
CREATE TABLE dog (
    id VARCHAR(255) PRIMARY KEY,
    pettype VARCHAR(255) NOT NULL,
    trackertype VARCHAR(255) NOT NULL,
    ownerid bigint NOT NULL,
    inzone BOOLEAN NOT NULL
);
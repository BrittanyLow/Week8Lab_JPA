DROP DATABASE if exists NotesDB;
CREATE DATABASE NotesDB;

USE NotesDB;

CREATE TABLE `notes` 
(
    `noteId` INT NOT NULL,
    `dateCreated` DATE NOT NULL,
    `content` VARCHAR(100) NOT NULL,
    CONSTRAINT PK_noteId PRIMARY KEY (noteId)
);


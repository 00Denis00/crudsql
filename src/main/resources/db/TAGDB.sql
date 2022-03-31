CREATE DATABASE IF NOT EXISTS BD;
 
USE BD;

Create table If Not Exists BD.tag 
(
	Id int, 
	Name varchar(255)
);



Create table If Not Exists BD.post 
(
	Id int, 
	firstName varchar(255),
    lastName varchar(255)
);

Create table If Not Exists BD.ptags
(
	Id int, 
	Name varchar(255),
    userId int
);



Create table If Not Exists BD.writer 
(
	Id int, 
	Name varchar(255)
);

Create table If Not Exists BD.writerPost 
(
	writerId int,
	Id int, 
	firstName varchar(255),
    lastName varchar(255)
);

Create table If Not Exists BD.postTag
(
	writerId int,
	postId int,
	Id int, 
	Name varchar(255)
);

CREATE DATABASE IF NOT EXISTS BD;
 
USE BD;

Create table If Not Exists BD.tag 
(
	id int, 
	name varchar(255),
    postId int
);

Create table If Not Exists BD.post 
(
	id int, 
	content varchar(255),
    writerId int
);

Create table If Not Exists BD.writer 
(
	id int, 
	name varchar(255)
);
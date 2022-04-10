CREATE DATABASE IF NOT EXISTS BD;
 
USE BD;

Create table If Not Exists BD.tag 
(
	id INT PRIMARY KEY AUTO_INCREMENT,
	name varchar(255),
    postId int
);

Create table If Not Exists BD.post 
(
	id INT PRIMARY KEY AUTO_INCREMENT, 
	content varchar(255),
    writerId int
);

Create table If Not Exists BD.writer 
(
	id INT PRIMARY KEY AUTO_INCREMENT, 
	name varchar(255)
);

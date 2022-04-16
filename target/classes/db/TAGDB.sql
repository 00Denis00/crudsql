CREATE DATABASE IF NOT EXISTS db;
USE db;

CREATE TABLE IF NOT EXISTS tags
(
	id INT PRIMARY KEY AUTO_INCREMENT,
	name varchar(255)
);

CREATE TABLE IF NOT EXISTS posts
(
	id INT PRIMARY KEY AUTO_INCREMENT,
	content varchar(255),
    writer_id int
);

CREATE TABLE IF NOT EXISTS post_tags
(
    post_id INT NOT NULL,
    tag_id INT NOT NULL,
    FOREIGN KEY (post_id) REFERENCES posts(id),
    FOREIGN KEY (tag_id) REFERENCES tags(id),
    UNIQUE (post_id, tag_id)
);

CREATE TABLE IF NOT EXISTS writers
(
	id INT PRIMARY KEY AUTO_INCREMENT,
	name varchar(255)
);

show databases;
create database LMS;
use LMS;
use lms;

show tables;

CREATE Table book_detail(
book_id int not null auto_increment Primary key,
name varchar(100) not null
);

show tables;

Create table book_quantity(
book_id int not null auto_increment Primary key,
current_quantity int,
total_quantity int,
foreign key (book_id) references book_detail(book_id)
);
show tables;
create table users (
id int primary key not null,
name varchar(50),
email varchar(50)
);
show tables;
drop table book_detail;

Insert into book_detail(book_id,name) Values(1,"Harry Potter");
Insert into book_detail(book_id,name) Values(2,"White Tiger");
Insert into book_detail(book_id,name) Values(3,"Love Story");
select * from book_detail;

Insert into book_quantity(book_id, current_quantity, total_quantity) Values (1,10,10);

Insert into book_quantity(book_id, current_quantity, total_quantity) Values (2,10,10);

Insert into book_quantity(book_id, current_quantity, total_quantity) Values (3,10,10);
Select * from book_quantity;

Insert into users (id, name, email) values (1,"Varun", "v@gmail.com");
select * from users;
create table userTable(name varchar(50),email varchar(50), password varchar(20),income double);

create table authTable(name varchar(50),email varchar(50), password varchar(20),status varchar(10));


select * from test where name='deva';

create table expenses(id INT GENERATED BY DEFAULT AS IDENTITY (START WITH 1,INCREMENT BY 1) PRIMARY KEY,userid varchar(50),category varchar(20),description varchar(50),amount double,day varchar(5),month varchar(5),years varchar(5));


delete from EXPENSES
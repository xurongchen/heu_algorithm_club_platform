create database heuacm;
USE heuacm;
 
CREATE TABLE user (
  id int(11) NOT NULL AUTO_INCREMENT,
  email varchar(64) DEFAULT NULL,
  username varchar(64) DEFAULT NULL,
  password varchar(64) DEFAULT NULL,
  
  name varchar(64) DEFAULT NULL,
  sex boolean default false,
  college int(11) default 0,
  grade int(11),
  stuclass varchar(64),
  stunum varchar(64),
  majoy varchar(64),
  qq varchar(64),
  phone varchar(64),
  
  confirmed boolean default false,
  auth int(11) default 0,
  credits int(11) default 0,
  
  pay int(11) default -1,
  ordernum varchar(64) default null,
  PRIMARY KEY (id),
  unique (email)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

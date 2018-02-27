USE heuacm;
 
CREATE TABLE orderinfo (
  ordernum varchar(64),
  checked boolean,
  PRIMARY KEY (ordernum)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

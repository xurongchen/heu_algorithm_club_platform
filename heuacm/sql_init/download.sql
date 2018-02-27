use heuacm;

CREATE TABLE download (
  id int(11) NOT NULL AUTO_INCREMENT,
  title varchar(128),
  dt datetime default now(),
  filename varchar(128),
  userid int(11),
  visible boolean default true,
  PRIMARY KEY (id)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

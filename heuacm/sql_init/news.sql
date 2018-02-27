USE heuacm;

CREATE TABLE news (
  id int(11) NOT NULL AUTO_INCREMENT,
  title text default null,
  dt datetime default now(),
  content text default null,
  visit int(11) default 0,
  userid int(11),
  auth int(11) default 0,
  visible boolean default true,
  top boolean default false,
  PRIMARY KEY (id)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

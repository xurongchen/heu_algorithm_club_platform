use heuacm;

CREATE TABLE course (
  id int(11) NOT NULL AUTO_INCREMENT,
  title text default null,
  dt datetime default now(),
  content text default null,
  location varchar(128),
  teacher varchar(128),
  userid int(11),
  visible boolean default true,
  PRIMARY KEY (id)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

create table annex(
  id int(11) NOT NULL AUTO_INCREMENT,
  courseid int(11),
  filename varchar(128),
  PRIMARY KEY (id)
)ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

create table signup(
	courseid int(11),
    userid int(11),
    PRIMARY KEY (courseid,userid)
)
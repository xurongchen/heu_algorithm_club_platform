USE heuacm;

CREATE TABLE chat (
  id int(11) NOT NULL AUTO_INCREMENT,
  title text default null,
  dt datetime default now(),
  content text default null,
  vote int(11) default 0,
  userid int(11),
  visible boolean default true,
  PRIMARY KEY (id)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE chatanswer (
  id int(11) NOT NULL AUTO_INCREMENT,
  chatid int(11),
  dt datetime default now(),
  content text default null,
  accept boolean default false,
  vote int(11) default 0,
  userid int(11),
  visible boolean default true,
  PRIMARY KEY (id)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE chatcomment (
  id int(11) NOT NULL AUTO_INCREMENT,
  chatanswerid int(11),
  dt datetime default now(),
  content text default null,
  userid int(11),
  visible boolean default true,
  PRIMARY KEY (id)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

create table chatvote(
	userid int(11),
    val varchar(64),
    PRIMARY KEY (userid,val)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
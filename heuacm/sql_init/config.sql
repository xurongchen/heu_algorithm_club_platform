USE heuacm;
 
CREATE TABLE config (
  configkey varchar(64),
  configval text,
  PRIMARY KEY (configkey)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

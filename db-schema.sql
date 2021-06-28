CREATE SCHEMA dit_schema ;

use dit_schema;

drop table users;

CREATE TABLE users (
                       id bigint NOT NULL AUTO_INCREMENT,
                       lastname varchar(255) NOT NULL,
                       firstname varchar(255),
                       username varchar(255) UNIQUE,
                       password varchar(255),
                       created_ts TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       last_login_ts TIMESTAMP ,
                       PRIMARY KEY (id)
);
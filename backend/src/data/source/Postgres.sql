CREATE DATABASE theclothez WITH ENCODING 'UTF8';

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

DROP TABLE USERS;
DROP TABLE USER_ADDRESS;
DROP TABLE PERMISSION;



CREATE TABLE PERMISSION(
    permission_id SERIAL PRIMARY KEY,
    permission_type int4 NOT NULL,
    description VARCHAR,
    created_at timestamptz DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamptz DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO PERMISSION(permission_type,description) VAlUES (0,'Normal user');
INSERT INTO PERMISSION(permission_type,description) VAlUES (1,'Admin');
INSERT INTO PERMISSION(permission_type,description) VAlUES (2,'Super Admin');


CREATE TABLE USERS (
	user_id uuid DEFAULT uuid_generate_v4 (),
	first_name VARCHAR NOT NULL,
	last_name VARCHAR NOT NULL,
    user_name VARCHAR NOT NULL,
    email VARCHAR(254) NOT NULL,
    is_active BOOL DEFAULT true,
    phone_number VARCHAR(20),
    password VARCHAR(254) NOT NULL,
    created_at timestamptz DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamptz DEFAULT CURRENT_TIMESTAMP,
    avatar VARCHAR DEFAULT '',
    gender VARCHAR(200),
    dob timestamp,
    permission_id int4 NOT NULL, 
	PRIMARY KEY (user_id),
     CONSTRAINT fk_permission
      FOREIGN KEY(permission_id) 
	  REFERENCES PERMISSION(permission_id)
);

CREATE TABLE USER_ADDRESS(
    address_id uuid DEFAULT uuid_generate_v4 (),
    user_id uuid,
    address_line_1 VARCHAR(254),
    address_line_2 VARCHAR(254),
    city VARCHAR(254),
    postal_code VARCHAR(20),
    country VARCHAR(254),
    phone_number VARCHAR(20),
    address_type int4,
    created_at timestamptz DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamptz DEFAULT CURRENT_TIMESTAMP
);

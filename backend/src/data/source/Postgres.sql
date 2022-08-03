CREATE DATABASE theclothez WITH ENCODING 'UTF8' LC_COLLATE='English_United Kingdom' LC_CTYPE='English_United Kingdom';

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";


CREATE TABLE PERMISSION(
    permission_id SERIAL PRIMARY KEY,
    type int4 NOT NULL,
    desc VARCHAR,
    created_at timestamptz,
    updated_at timestamptz,
);

CREATE TABLE USER (
	user_id uuid DEFAULT uuid_generate_v4 (),
	first_name VARCHAR NOT NULL,
	last_name VARCHAR NOT NULL,
    user_name VARCHAR NOT NULL,
    email VARCHAR(254) NOT NULL,
    is_active BOOL DEFAULT true,
    phone_number VARCHAR(20),
    password VARCHAR(254) NOT NULL,
    created_at timestamptz,
    updated_at timestamptz,
    avatar VARCHAR,
    gender VARCHAR(200),
    dob timestamp,
    permission_id uuid NOT NULL, 
	PRIMARY KEY (user_id),
     CONSTRAINT fk_customer
      FOREIGN KEY(permission_id) 
	  REFERENCES PERMISSION(permission_id)
);

CREATE TABLE USER_ADDRESS(
    permission_id SERIAL PRIMARY KEY,
    user_id uuid,
    address_line_1 VARCHAR(254),
    address_line_2 VARCHAR(254),
    city VARCHAR(254),
    postal_code VARCHAR(20),
    country VARCHAR(254),
    phone_number VARCHAR(20),
    address_type int4,
    created_at timestamptz,
    updated_at timestamptz,
);

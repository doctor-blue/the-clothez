DROP DATABASE theclothez;
CREATE DATABASE theclothez WITH ENCODING 'UTF8';
\c theclothez
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";


-- DROP TABLE USERS;
-- DROP TABLE USER_ADDRESS;
-- DROP TABLE PERMISSION;

CREATE TABLE PERMISSION(
    permission_id SERIAL PRIMARY KEY,
    permission_type int4 NOT NULL,
    description VARCHAR,
    created_at timestamptz DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamptz DEFAULT CURRENT_TIMESTAMP
);

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
    gender int4,
    dob timestamp,
    permission_id int4 NOT NULL, 
	PRIMARY KEY (user_id),
     CONSTRAINT fk_permission
      FOREIGN KEY(permission_id) 
	  REFERENCES PERMISSION(permission_id)
);

CREATE TABLE USER_ADDRESS(
    address_id uuid DEFAULT uuid_generate_v4(),
    user_id uuid,
    address_line_1 VARCHAR(254),
    address_line_2 VARCHAR(254),
    city VARCHAR(254),
    postal_code VARCHAR(20),
    country VARCHAR(254),
    phone_number VARCHAR(20),
    address_type int4,
    created_at timestamptz DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamptz DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY(address_id),
       CONSTRAINT fk_user
      FOREIGN KEY(user_id) 
	  REFERENCES USERS(user_id)
);

CREATE TABLE CATEGORY(
 id uuid DEFAULT uuid_generate_v4(),
 gender int4,
 name VARCHAR(254),
 description VARCHAR(254),
 created_at timestamptz DEFAULT CURRENT_TIMESTAMP,
 updated_at timestamptz DEFAULT CURRENT_TIMESTAMP,
 PRIMARY KEY(id)
);

CREATE TABLE SUB_CATEGORY(
    id uuid DEFAULT uuid_generate_v4(),
    category_id uuid NOT NULL,
    name VARCHAR(254),
    description VARCHAR(254),
    created_at timestamptz DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamptz DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY(id),
       CONSTRAINT fk_category
      FOREIGN KEY(category_id) 
	  REFERENCES CATEGORY(id) ON DELETE CASCADE
);

CREATE TABLE PRODUCT(
    id uuid DEFAULT uuid_generate_v4(),
    name VARCHAR,
    description VARCHAR,
    product_code VARCHAR(254),
    form VARCHAR,
    material VARCHAR,
    unit VARCHAR(254),
    quantity_per_unit decimal,
    price decimal DEFAULT 0,
    unit_price VARCHAR(100),
    sub_category_id uuid,
    created_at timestamptz DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamptz DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY(id),
    CONSTRAINT fk_category
      FOREIGN KEY(sub_category_id) 
	  REFERENCES SUB_CATEGORY(id) ON DELETE CASCADE
);

CREATE TABLE PRODUCT_COLOR(
    id uuid DEFAULT uuid_generate_v4(),
    product_id uuid,
    name VARCHAR(254),
    description VARCHAR,
    color_hex VARCHAR(20),
    created_at timestamptz DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamptz DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY(id),
    CONSTRAINT fk_product
      FOREIGN KEY(product_id) 
	  REFERENCES PRODUCT(id) ON DELETE CASCADE
);

CREATE TABLE PRODUCT_SIZE_INFO(
    id uuid DEFAULT uuid_generate_v4(),
    color_id uuid,
    size VARCHAR(50),
    product_amount decimal,
    PRIMARY KEY(id),
    CONSTRAINT fk_color
      FOREIGN KEY(color_id) 
	  REFERENCES PRODUCT_COLOR(id) ON DELETE CASCADE    
);

CREATE TABLE PRODUCT_COLOR_RES(
    id uuid DEFAULT uuid_generate_v4(),
    color_id uuid,
    url VARCHAR,
    description VARCHAR,
    mine_type int4 DEFAULT 0,
    res_type int4 DEFAULT 0,
    PRIMARY KEY(id),
    CONSTRAINT fk_color
      FOREIGN KEY(color_id) 
	  REFERENCES PRODUCT_COLOR(id) ON DELETE CASCADE
);


-- init data
INSERT INTO PERMISSION(permission_type,description) VAlUES (0,'Normal user');
INSERT INTO PERMISSION(permission_type,description) VAlUES (1,'Admin');
INSERT INTO PERMISSION(permission_type,description) VAlUES (2,'Super Admin');
INSERT INTO users(first_name,last_name,user_name,email,dob,permission_id,password) VAlUES('Guest','','guest','guest@starlight.clothez.com',null,1,'$2b$10$e5OtDmDHjkQwQiYPv450t.oAOwvGRhZ4FZHw8baOs153jTxop4GjK');


CREATE TYPE user_role as ENUM('ADMIN', 'CLIENT');
CREATE TABLE customer (
                          id SERIAL PRIMARY KEY,
                          email VARCHAR(255) NOT NULL,
                          name VARCHAR(255),
                          surname VARCHAR(255),
                          nick_name VARCHAR(255),
                          phone_number VARCHAR(255),
                          password VARCHAR(255) NOT NULL,
                          role user_role NOT NULL,
                          user_role VARCHAR(50) NOT NULL -- This field acts as the discriminator column
);
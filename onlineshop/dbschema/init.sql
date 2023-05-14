CREATE TABLE products (
                          id varchar(50) PRIMARY KEY,
                          category_id varchar(50) NOT NULL,
                          name varchar(200) NOT NULL,
                          price numeric(10) NOT NULL,
                          description text NOT NULL,
                          created_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          updated_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE images (
                        id varchar(50) PRIMARY KEY,
                        product_id varchar(50) NOT NULL,
                        url varchar(200) NOT NULL,
                        created_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        updated_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE product_options (
                                 id varchar(50) PRIMARY KEY,
                                 product_id varchar(50) NOT NULL,
                                 name varchar(50) NOT NULL,
                                 created_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                 updated_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE product_option_items (
                                      id varchar(50) PRIMARY KEY,
                                      product_option_id varchar(50) NOT NULL,
                                      name varchar(50) NOT NULL,
                                      created_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                      updated_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
);
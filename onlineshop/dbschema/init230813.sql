-- public.access_tokens definition

-- Drop table

-- DROP TABLE public.access_tokens;

CREATE TABLE public.access_tokens (
                                      "token" varchar(200) NOT NULL,
                                      user_id varchar(100) NOT NULL,
                                      created_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                      updated_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                      CONSTRAINT access_tokens_pkey PRIMARY KEY (token)
);


-- public.cart_line_item_options definition

-- Drop table

-- DROP TABLE public.cart_line_item_options;

CREATE TABLE public.cart_line_item_options (
                                               cart_line_item_id varchar(100) NOT NULL,
                                               product_option_id varchar(100) NOT NULL,
                                               product_option_item_id varchar(100) NOT NULL
);


-- public.cart_line_items definition

-- Drop table

-- DROP TABLE public.cart_line_items;

CREATE TABLE public.cart_line_items (
                                        id varchar(100) NOT NULL,
                                        cart_id varchar(100) NULL,
                                        product_id varchar(100) NOT NULL,
                                        quantity numeric(8) NOT NULL,
                                        created_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                        updated_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                        CONSTRAINT cart_line_items_pkey PRIMARY KEY (id)
);


-- public.carts definition

-- Drop table

-- DROP TABLE public.carts;

CREATE TABLE public.carts (
                              id varchar(100) NOT NULL,
                              user_id varchar(100) NOT NULL,
                              created_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                              updated_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                              CONSTRAINT carts_pkey PRIMARY KEY (id)
);


-- public.categories definition

-- Drop table

-- DROP TABLE public.categories;

CREATE TABLE public.categories (
                                   id varchar(50) NOT NULL,
                                   "name" varchar(50) NOT NULL,
                                   created_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                   updated_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                   hidden bool NOT NULL DEFAULT true,
                                   CONSTRAINT categories_pkey PRIMARY KEY (id)
);

-- public.images definition

-- Drop table

-- DROP TABLE public.images;

CREATE TABLE public.images (
                               id varchar(50) NOT NULL,
                               product_id varchar(50) NULL,
                               url varchar(200) NOT NULL,
                               created_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                               updated_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                               CONSTRAINT images_pkey PRIMARY KEY (id)
);


-- public.order_line_items definition

-- Drop table

-- DROP TABLE public.order_line_items;

CREATE TABLE public.order_line_items (
                                         id varchar(100) NOT NULL,
                                         order_id varchar(100) NULL,
                                         product_id varchar(100) NOT NULL,
                                         product_name varchar(200) NOT NULL,
                                         unit_price numeric(10) NOT NULL,
                                         quantity numeric(8) NOT NULL,
                                         total_price numeric(10) NOT NULL,
                                         created_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                         updated_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                         CONSTRAINT order_line_items_pkey PRIMARY KEY (id)
);


-- public.order_options definition

-- Drop table

-- DROP TABLE public.order_options;

CREATE TABLE public.order_options (
                                      id varchar(100) NOT NULL,
                                      order_line_item_id varchar(100) NULL,
                                      product_option_id varchar(100) NOT NULL,
                                      "name" varchar(50) NOT NULL,
                                      product_option_item_id varchar(100) NOT NULL,
                                      product_option_item_name varchar(50) NOT NULL,
                                      created_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                      updated_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                      CONSTRAINT order_options_pkey PRIMARY KEY (id)
);


-- public.orders definition

-- Drop table

-- DROP TABLE public.orders;

CREATE TABLE public.orders (
                               id varchar(100) NOT NULL,
                               user_id varchar(100) NOT NULL,
                               total_price numeric(10) NOT NULL,
                               receiver_name varchar(100) NOT NULL,
                               address1 varchar(200) NOT NULL,
                               address2 varchar(200) NOT NULL,
                               postal_code varchar(20) NOT NULL,
                               phone_number varchar(20) NOT NULL,
                               payment_merchant_id varchar(50) NOT NULL,
                               payment_transaction_id varchar(50) NOT NULL,
                               status varchar(20) NOT NULL,
                               ordered_at timestamp NOT NULL,
                               created_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                               updated_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                               CONSTRAINT orders_pkey PRIMARY KEY (id)
);

-- public.product_option_items definition

-- Drop table

-- DROP TABLE public.product_option_items;

CREATE TABLE public.product_option_items (
                                             id varchar(50) NOT NULL,
                                             product_option_id varchar(50) NULL,
                                             "name" varchar(50) NOT NULL,
                                             created_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                             updated_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                             CONSTRAINT product_option_items_pkey PRIMARY KEY (id)
);


-- public.product_options definition

-- Drop table

-- DROP TABLE public.product_options;

CREATE TABLE public.product_options (
                                        id varchar(50) NOT NULL,
                                        product_id varchar(50) NULL,
                                        "name" varchar(50) NOT NULL,
                                        created_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                        updated_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                        CONSTRAINT product_options_pkey PRIMARY KEY (id)
);


-- public.products definition

-- Drop table

-- DROP TABLE public.products;

CREATE TABLE public.products (
                                 id varchar(50) NOT NULL,
                                 category_id varchar(50) NOT NULL,
                                 "name" varchar(200) NOT NULL,
                                 price numeric(10) NOT NULL,
                                 description text NOT NULL,
                                 created_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                 updated_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                 hidden bool NOT NULL DEFAULT true,
                                 CONSTRAINT products_pkey PRIMARY KEY (id)
);


-- public.users definition

-- Drop table

-- DROP TABLE public.users;

CREATE TABLE public.users (
                              id varchar(100) NOT NULL,
                              email varchar(200) NOT NULL,
                              "name" varchar(100) NOT NULL,
                              "password" varchar(100) NOT NULL,
                              "role" varchar(50) NOT NULL,
                              created_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                              updated_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                              CONSTRAINT users_pkey PRIMARY KEY (id)
);
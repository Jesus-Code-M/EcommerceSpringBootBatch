CREATE DATABASE ecommerce;

CREATE TABLE ecommerce.orders (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_date DATE NOT NULL,
    status VARCHAR(50) NOT NULL,
    total_amount DOUBLE NOT NULL,
    user_email VARCHAR(255) NOT NULL
);

CREATE TABLE products (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    price DOUBLE NOT NULL
);


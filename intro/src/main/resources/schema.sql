CREATE TABLE IF NOT EXISTS customer(
    customer_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NUlL,
    email VARCHAR(50),
    mobile_number VARCHAR(10),
    created_at date NOT NULL,
    created_by VARCHAR(50),
    updated_at date NOT NULL,
    updated_by VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS accounts(
    customer_id INT NOT NULL,
    account_number INT AUTO_INCREMENT PRIMARY KEY,
    account_type VARCHAR(100) NOT NUlL,
    branch_address VARCHAR(500),
    created_at date NOT NULL,
    created_by VARCHAR(50),
    updated_at date NOT NULL,
    updated_by VARCHAR(50) NOT NULL
);
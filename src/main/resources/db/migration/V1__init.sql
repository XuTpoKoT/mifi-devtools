CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    login VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL
);

CREATE TABLE otp_config (
    id BIGINT PRIMARY KEY,
    code_length INT NOT NULL,
    ttl_seconds INT NOT NULL
);

INSERT INTO otp_config (id, code_length, ttl_seconds)
VALUES (1, 6, 300);

CREATE TABLE otp_codes (
    id BIGSERIAL PRIMARY KEY,
    code text,
    operation_id BIGINT,
    status text,
    created_at TIMESTAMP,
    expires_at TIMESTAMP,
    user_id BIGINT REFERENCES users(id)
);

INSERT INTO users (login, password, role)
VALUES (
    'admin',
    '$2a$10$7QJr5X5p9v9e6v3hWk1vhe6W1s8Kq7R1VQUNBM8DT6vKrrO5gYv7G',
    'ADMIN'
);
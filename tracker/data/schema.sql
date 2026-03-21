DROP TABLE IF EXISTS app_user CASCADE;
DROP TABLE IF EXISTS expense CASCADE;

CREATE TABLE app_user (
    id  BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(128) NOT NULL,
    birthday DATE NOT NULL
);

CREATE TABLE expense (
    id          BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    user_id     BIGINT NOT NULL,
    amount      NUMERIC(12, 2) NOT NULL,
    category    VARCHAR(20) NOT NULL,
    expense_day DATE NOT NULL,

    CONSTRAINT fk_expense_user FOREIGN KEY (user_id) REFERENCES app_user(id),
    CONSTRAINT chk_amount_positive CHECK (amount > 0)
);

CREATE TABLE users (
                       user_id INT PRIMARY KEY,
                       name VARCHAR(255) NOT NULL,
                       email VARCHAR(255) UNIQUE NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       role VARCHAR(20) CHECK (role IN ('PEDIATRICIAN', 'ADMIN')) NOT NULL
);

CREATE TABLE medications (
                             medication_id INT PRIMARY KEY,
                             name VARCHAR(255) NOT NULL,
                             presentation VARCHAR(255),
                             standard_dosage DECIMAL
);

CREATE TABLE prescriptions (
                               prescription_id INT PRIMARY KEY,
                               user_id INT NOT NULL,
                               creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                               notes TEXT,
                               FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

-- Inserindo usuários de teste
INSERT INTO users (user_id, name, email, password, role) VALUES
                                                             (1, 'Dr. Alice', 'alice@example.com', 'hashed_password_1', 'PEDIATRICIAN'),
                                                             (2, 'Admin Bob', 'bob@example.com', 'hashed_password_2', 'ADMIN');

-- Inserindo medicamentos de teste
INSERT INTO medications (medication_id, name, presentation, standard_dosage) VALUES
                                                                                 (1, 'Paracetamol', 'Liquid 100mg/mL', 5.0),
                                                                                 (2, 'Ibuprofen', 'Tablet 200mg', 1.0);

-- Inserindo prescrições de teste
INSERT INTO prescriptions (prescription_id, user_id, notes) VALUES
    (1, (SELECT user_id FROM users WHERE name = 'Dr. Alice'), 'Give every 6 hours for fever.');

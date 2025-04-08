-- Inserindo usuários de teste
INSERT INTO users (user_id, name, email, password, role) VALUES
                                                             (gen_random_uuid(), 'Dr. Alice', 'alice@example.com', '$2a$10$CcHEXqWLmPOSQaokemv6LOWyInYnS7zobNzO8Z49uucER5/pM8w6i', 'PEDIATRICIAN'),
                                                             (gen_random_uuid(), 'Admin Bob', 'bob@example.com', '$2a$10$CcHEXqWLmPOSQaokemv6LOWyInYnS7zobNzO8Z49uucER5/pM8w6i', 'ADMIN');

-- Inserindo medicamentos de teste
INSERT INTO medications (medication_id, name, presentation, standard_dosage) VALUES
                                                                                 (gen_random_uuid(), 'Paracetamol', 'Liquid 100mg/mL', 5.0),
                                                                                 (gen_random_uuid(), 'Ibuprofen', 'Tablet 200mg', 1.0);

-- Inserindo prescrições de teste
INSERT INTO prescriptions (prescription_id, user_id, notes) VALUES
    (gen_random_uuid(), (SELECT user_id FROM users WHERE name = 'Dr. Alice'), 'Give every 6 hours for fever.');
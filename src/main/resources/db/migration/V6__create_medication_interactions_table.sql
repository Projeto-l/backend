
DROP TABLE IF EXISTS medication_interactions;

CREATE TABLE medication_interactions (
    medication1_id UUID NOT NULL,
    medication2_id UUID NOT NULL,
    there_is_conflict BOOLEAN NOT NULL,
    description TEXT,
    medications_alternatives TEXT,
    PRIMARY KEY (medication1_id, medication2_id),
    FOREIGN KEY (medication1_id) REFERENCES medications(medication_id) ON DELETE CASCADE,
    FOREIGN KEY (medication2_id) REFERENCES medications(medication_id) ON DELETE CASCADE
);

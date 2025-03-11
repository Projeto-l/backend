-- V3__Alter_medications_and_create_presentation_tables.sql
-- Este script altera a tabela de medications, cria as tabelas para a hierarquia de Presentation,
-- e insere dados de teste para medicamentos e suas apresentações.

-- Ativa a extensão para geração de UUID (se necessário)
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

------------------------------------------------------
-- 1. Alterar a tabela de medications: Remover colunas antigas
------------------------------------------------------
ALTER TABLE medications
  DROP COLUMN IF EXISTS presentation,
  DROP COLUMN IF EXISTS standard_dosage;

------------------------------------------------------
-- 2. Criar as tabelas para a hierarquia de Presentation (JOINED)
------------------------------------------------------

-- Tabela base para Presentation (classe abstrata)
CREATE TABLE IF NOT EXISTS presentation (
    id_presentation UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    medication_id UUID NOT NULL,
    CONSTRAINT fk_medication
      FOREIGN KEY (medication_id)
      REFERENCES medications (medication_id)
      ON DELETE CASCADE
);

-- Tabela para Tablet (subclasse de Presentation)
CREATE TABLE IF NOT EXISTS tablet (
    id UUID PRIMARY KEY,
    mg_per_tablet DOUBLE PRECISION NOT NULL,
    CONSTRAINT fk_presentation_tablet
      FOREIGN KEY (id)
      REFERENCES presentation (id_presentation)
      ON DELETE CASCADE
);

-- Tabela para OralSuspension (subclasse de Presentation)
CREATE TABLE IF NOT EXISTS oral_suspension (
    id UUID PRIMARY KEY,
    mg_per_ml DOUBLE PRECISION NOT NULL,
    CONSTRAINT fk_presentation_oral
      FOREIGN KEY (id)
      REFERENCES presentation (id_presentation)
      ON DELETE CASCADE
);

-- Tabela para Drops (subclasse de Presentation)
CREATE TABLE IF NOT EXISTS drops (
    id UUID PRIMARY KEY,
    mg_per_ml DOUBLE PRECISION NOT NULL,
    ml_per_drop DOUBLE PRECISION NOT NULL,
    CONSTRAINT fk_presentation_drops
      FOREIGN KEY (id)
      REFERENCES presentation (id_presentation)
      ON DELETE CASCADE
);

------------------------------------------------------
-- 3. Inserir dados de teste
------------------------------------------------------

-- Inserindo medicamentos de teste
INSERT INTO medications (medication_id, name) VALUES 
  (gen_random_uuid(), 'Paracetamol'),
  (gen_random_uuid(), 'Ibuprofen'),
  (gen_random_uuid(), 'Captopril');

-- Inserir apresentação para Paracetamol (OralSuspension: ex. 100 mg/ml)
WITH med AS (
   SELECT medication_id FROM medications WHERE name = 'Paracetamol' LIMIT 1
), inserted AS (
   INSERT INTO presentation (id_presentation, medication_id) 
   SELECT gen_random_uuid(), medication_id FROM med
   RETURNING id_presentation
)
INSERT INTO oral_suspension (id, mg_per_ml) 
SELECT id_presentation, 100.0 FROM inserted;

-- Inserir apresentação para Ibuprofen (Tablet: 200 mg por tablet)
WITH med AS (
   SELECT medication_id FROM medications WHERE name = 'Ibuprofen' LIMIT 1
), inserted AS (
   INSERT INTO presentation (id_presentation, medication_id) 
   SELECT gen_random_uuid(), medication_id FROM med
   RETURNING id_presentation
)
INSERT INTO tablet (id, mg_per_tablet) 
SELECT id_presentation, 200.0 FROM inserted;

-- Inserir apresentação para Captopril (Drops: 50 mg/ml, 0.05 ml por drop)
WITH med AS (
   SELECT medication_id FROM medications WHERE name = 'Captopril' LIMIT 1
), inserted AS (
   INSERT INTO presentation (id_presentation, medication_id) 
   SELECT gen_random_uuid(), medication_id FROM med
   RETURNING id_presentation
)
INSERT INTO drops (id, mg_per_ml, ml_per_drop) 
SELECT id_presentation, 50.0, 0.05 FROM inserted;

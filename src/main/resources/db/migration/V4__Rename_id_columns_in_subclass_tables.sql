-- V4__Rename_id_columns_in_subclass_tables.sql
-- Renomeia a coluna "id" para "id_presentation" nas tabelas derivadas, para alinhamento com o mapeamento da entidade

ALTER TABLE tablet RENAME COLUMN id TO id_presentation;
ALTER TABLE oral_suspension RENAME COLUMN id TO id_presentation;
ALTER TABLE drops RENAME COLUMN id TO id_presentation;

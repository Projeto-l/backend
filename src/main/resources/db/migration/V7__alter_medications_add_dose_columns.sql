
ALTER TABLE medications
    ADD COLUMN default_dose_per_day DECIMAL,
    ADD COLUMN default_dose_per_administration DECIMAL;

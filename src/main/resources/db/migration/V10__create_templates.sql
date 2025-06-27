CREATE TABLE templates (
    template_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID NOT NULL,
    name VARCHAR(255) NOT NULL,
    content TEXT,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

CREATE TABLE template_fields (
    template_id UUID NOT NULL,
    field VARCHAR(255),
    FOREIGN KEY (template_id) REFERENCES templates(template_id) ON DELETE CASCADE
);

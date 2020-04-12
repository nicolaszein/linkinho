CREATE TABLE public.links (
    id serial PRIMARY KEY,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    long_url TEXT NOT NULL,
    tag VARCHAR(50) NOT NULL,
    title VARCHAR(255),
    UNIQUE(tag)
);

CREATE INDEX idx_tag ON links (tag);

CREATE TABLE public.clicks (
    id serial PRIMARY KEY,
    link_id INTEGER NOT NULL,
    created_at TIMESTAMP NOT NULL,
    user_agent TEXT NOT NULL,
    ip VARCHAR(50) NOT NULL,
    FOREIGN KEY (link_id) REFERENCES links (id)

);

CREATE INDEX idx_link_id ON clicks (link_id);

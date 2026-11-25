CREATE TABLE series (
    id            BIGSERIAL PRIMARY KEY ,
    title         VARCHAR(255) NOT NULL,
    author        VARCHAR(255),
    status        VARCHAR(20) NOT NULL,
    total_volumes INT,
    cover_url     TEXT,
    rating        SMALLINT CHECK ( rating between 1 AND 10 ),
    created_at    TIMESTAMP NOT NULL DEFAULT now()

);
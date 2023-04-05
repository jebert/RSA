CREATE TABLE IF NOT EXISTS permission
(
    id uuid NOT NULL,
    description character varying(120) COLLATE pg_catalog."default",
    CONSTRAINT pk_permission_id PRIMARY KEY (id)
)
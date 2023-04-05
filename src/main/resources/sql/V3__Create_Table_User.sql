CREATE TABLE IF NOT EXISTS users (
        id uuid,
        user_name character varying(30) ,
        full_name character varying(120) ,
        email character varying(120),
        password character varying(255),
        account_non_expired bool,
        account_non_locked bool,
        credentials_non_expired bool,
        enabled bool,
        CONSTRAINT pk_users_id PRIMARY KEY (id),
        CONSTRAINT uk_email UNIQUE (email),
        CONSTRAINT uk_user_name UNIQUE (user_name)
)
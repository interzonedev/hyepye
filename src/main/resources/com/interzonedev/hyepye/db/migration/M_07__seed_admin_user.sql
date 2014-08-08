-- Idempotently seed the admin user in the hp_users table.

INSERT INTO hp_user
    (
    username,
    password_hash,
    email,
    first_name,
    last_name,
    role,
    active
    )
SELECT
    'markm',
    '$2a$12$HEvkNYnH1e/zU0S/Rh4sQOAotpOThVv5AN9y8qFU6QbSabn6kdH.G',
    'mark@interzonedev.com',
    'Mark',
    'Markarian',
    'admin',
    TRUE
WHERE
    NOT EXISTS (
        SELECT hp_user_id FROM hp_user WHERE username = 'markm'
    );

-- Create the hp_user table, primary key sequence, update timestamp set trigger and constraints.

DROP TABLE IF EXISTS hp_user;

DROP SEQUENCE IF EXISTS hp_user_seq;

CREATE SEQUENCE hp_user_seq;

CREATE TABLE hp_user (
    hp_user_id INTEGER NOT NULL PRIMARY KEY DEFAULT nextval('hp_user_seq'),
    username VARCHAR(255) NOT NULL,
    password_hash CHAR(60) NOT NULL,
    email VARCHAR(255) NOT NULL,
    first_name VARCHAR(255) DEFAULT NULL,
    last_name VARCHAR(255) DEFAULT NULL,
    role VARCHAR(50) NOT NULL,
    active BOOLEAN DEFAULT TRUE,
    time_created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    time_updated TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

DROP TRIGGER IF EXISTS t_set_hp_user_time_updated ON hp_user;

CREATE TRIGGER t_set_hp_user_time_updated
    BEFORE UPDATE
    ON hp_user
    FOR EACH ROW
    EXECUTE PROCEDURE fn_update_timestamp();

ALTER TABLE hp_user ADD CONSTRAINT u_hp_user_username UNIQUE (username);
ALTER TABLE hp_user ADD CONSTRAINT u_hp_user_email UNIQUE (email);

-- Create the verb table, primary key sequence, update timestamp set trigger, constraints and indicies.

DROP TABLE IF EXISTS verb;

DROP SEQUENCE IF EXISTS verb_seq;

CREATE SEQUENCE verb_seq;

CREATE TABLE verb (
    verb_id INTEGER NOT NULL PRIMARY KEY DEFAULT nextval('verb_seq'),
    armenian VARCHAR(255) COLLATE "hy_AM.UTF-8" NOT NULL,
    english VARCHAR(255) NOT NULL,
    conjugation_group VARCHAR(5) NOT NULL,
    irregular BOOLEAN NOT NULL DEFAULT TRUE,
    status VARCHAR(50) NOT NULL,
    time_created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    time_updated TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by INTEGER NOT NULL,
    modified_by INTEGER NOT NULL,
    CONSTRAINT fk_verb_created_by_hp_user_hp_user_id FOREIGN KEY (created_by) REFERENCES hp_user (hp_user_id),
    CONSTRAINT fk_verb_modified_by_hp_user_hp_user_id FOREIGN KEY (modified_by) REFERENCES hp_user (hp_user_id)
);

DROP TRIGGER IF EXISTS t_set_verb_time_updated ON verb;

CREATE TRIGGER t_set_verb_time_updated
    BEFORE UPDATE
    ON verb
    FOR EACH ROW
    EXECUTE PROCEDURE fn_update_timestamp();

ALTER TABLE verb ADD CONSTRAINT u_verb_armenian UNIQUE (armenian);

CREATE INDEX idx_verb_english ON verb (english);
CREATE INDEX idx_verb_conjugation_group ON verb (conjugation_group);
CREATE INDEX idx_verb_status ON verb (status);

-- Create the conjugation table, primary key sequence, update timestamp set trigger, constraints and indicies.

DROP TABLE IF EXISTS conjugation;

DROP SEQUENCE IF EXISTS conjugation_seq;

CREATE SEQUENCE conjugation_seq;

CREATE TABLE conjugation (
    conjugation_id INTEGER NOT NULL PRIMARY KEY DEFAULT nextval('conjugation_seq'),
    verb_id INTEGER NOT NULL,
    tense VARCHAR(50) NOT NULL,
    first_person_singular VARCHAR(255) COLLATE "hy_AM" NOT NULL,
    second_person_singular VARCHAR(255) COLLATE "hy_AM" NOT NULL,
    third_person_singular VARCHAR(255) COLLATE "hy_AM" NOT NULL,
    first_person_plural VARCHAR(255) COLLATE "hy_AM" NOT NULL,
    second_person_plural VARCHAR(255) COLLATE "hy_AM" NOT NULL,
    third_person_plural VARCHAR(255) COLLATE "hy_AM" NOT NULL,
    status VARCHAR(50) NOT NULL,
    time_created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    time_updated TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by INTEGER NOT NULL,
    modified_by INTEGER NOT NULL,
    CONSTRAINT fk_conjugation_verb_id_verb_verb_id FOREIGN KEY (verb_id) REFERENCES verb (verb_id),
    CONSTRAINT fk_conjugation_created_by_hp_user_hp_user_id FOREIGN KEY (created_by) REFERENCES hp_user (hp_user_id),
    CONSTRAINT fk_conjugation_modified_by_hp_user_hp_user_id FOREIGN KEY (modified_by) REFERENCES hp_user (hp_user_id)
);

DROP TRIGGER IF EXISTS t_set_conjugation_time_updated ON conjugation;

CREATE TRIGGER t_set_conjugation_time_updated
    BEFORE UPDATE
    ON conjugation
    FOR EACH ROW
    EXECUTE PROCEDURE fn_update_timestamp();

CREATE INDEX idx_conjugation_tense ON conjugation (tense);
CREATE INDEX idx_conjugation_status ON conjugation (status);

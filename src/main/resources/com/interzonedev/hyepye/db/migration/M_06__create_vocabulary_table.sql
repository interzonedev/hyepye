-- Create the vocabulary table, primary key sequence, update timestamp set trigger, constraints and indicies.

DROP TABLE IF EXISTS vocabulary;

DROP SEQUENCE IF EXISTS vocabulary_seq;

CREATE SEQUENCE vocabulary_seq;

CREATE TABLE vocabulary (
    vocabulary_id INTEGER NOT NULL PRIMARY KEY DEFAULT nextval('vocabulary_seq'),
    armenian VARCHAR(255) COLLATE "hy_AM.UTF-8" NOT NULL,
    english VARCHAR(255) NOT NULL,
    vocabulary_type VARCHAR(50) NOT NULL,
    status VARCHAR(50) NOT NULL,
    time_created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    time_updated TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by INTEGER NOT NULL,
    modified_by INTEGER NOT NULL,
    CONSTRAINT fk_vocabulary_created_by_hp_user_hp_user_id FOREIGN KEY (created_by) REFERENCES hp_user (hp_user_id),
    CONSTRAINT fk_vocabulary_modified_by_hp_user_hp_user_id FOREIGN KEY (modified_by) REFERENCES hp_user (hp_user_id)
);

DROP TRIGGER IF EXISTS t_set_vocabulary_time_updated ON vocabulary;

CREATE TRIGGER t_set_vocabulary_time_updated
    BEFORE UPDATE
    ON vocabulary
    FOR EACH ROW
    EXECUTE PROCEDURE fn_update_timestamp();

ALTER TABLE vocabulary ADD CONSTRAINT u_vocabulary_armenian UNIQUE (armenian);

CREATE INDEX idx_vocabulary_english ON vocabulary (english);
CREATE INDEX idx_vocabulary_status ON vocabulary (status);
CREATE INDEX idx_vocabulary_vocabulary_type ON vocabulary (vocabulary_type);

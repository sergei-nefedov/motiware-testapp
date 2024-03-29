
DROP TABLE IF EXISTS works;
DROP TABLE IF EXISTS checkpoints;
DROP TABLE IF EXISTS projects;
DROP SEQUENCE IF EXISTS projects_id_seq;
DROP SEQUENCE IF EXISTS checkpoints_id_seq;
DROP SEQUENCE IF EXISTS works_id_seq;

CREATE TABLE "projects"
(
    id                              BIGINT,
    name                            VARCHAR(255),
    number                          VARCHAR(255),
    condition                       VARCHAR(255) check (condition in ('FORMING','APPROVING','REFINEMENT','IMPLEMENTATION','FINISHED')),
    average_completion_percentage   FLOAT(53),
    director                        VARCHAR(255),
    PRIMARY KEY (id)
);
CREATE SEQUENCE IF NOT EXISTS projects_id_seq START WITH 10001 INCREMENT BY 1;

CREATE TABLE if NOT EXISTS "checkpoints"
(
    id                  BIGINT,
    name                VARCHAR(255),
    finish_date         TIMESTAMP(6),
    project_id          BIGINT REFERENCES projects(id),
    PRIMARY KEY (id),
    UNIQUE (name, project_id)
);
CREATE SEQUENCE IF NOT EXISTS checkpoints_id_seq START WITH 1 INCREMENT BY 1;


CREATE TABLE if NOT EXISTS "works"
(
    id                              BIGINT,
    name                            VARCHAR(255),
    start_date                      TIMESTAMP(6),
    finish_date                     TIMESTAMP(6) check (finish_date >= start_date),
    average_completion_percentage   FLOAT(53),
    implementer                     VARCHAR(255),
    project_id                      BIGINT REFERENCES projects(id),
    checkpoint_id                   BIGINT REFERENCES checkpoints(id),
    PRIMARY KEY (id)
);
CREATE SEQUENCE IF NOT EXISTS works_id_seq START WITH 100001 INCREMENT BY 1;
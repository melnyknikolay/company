DROP TABLE IF EXISTS company;
DROP SEQUENCE IF EXISTS global_seq;
CREATE SEQUENCE global_seq START 50;
CREATE TABLE company
(
  id INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  company_name character varying NOT NULL,
  earnings character varying NOT NULL,
  parrent_id integer NOT NULL DEFAULT 0
)

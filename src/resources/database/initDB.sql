DROP TABLE IF EXISTS company;
CREATE SEQUENCE global_seq START 1;
CREATE TABLE company
(
  id INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  company_name character varying NOT NULL,
  earnings character varying NOT NULL,
  parrent_id integer NOT NULL DEFAULT 0
)

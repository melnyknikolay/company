DROP TABLE IF EXISTS company;
CREATE TABLE company
(
  id integer NOT NULL,
  company_name character varying NOT NULL,
  earnings character varying NOT NULL,
  parrent_id integer NOT NULL DEFAULT 0,
  CONSTRAINT company_pkey PRIMARY KEY (id)
)

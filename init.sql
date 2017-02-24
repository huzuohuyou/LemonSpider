-- Table: public.paper

-- DROP TABLE public.paper;

CREATE TABLE public.paper
(
  id serial NOT NULL,
  pm_id integer,
  pmc_id integer NOT NULL,
  sizekb integer NOT NULL,
  title character varying(1024) NOT NULL,
  ts timestamp without time zone NOT NULL,
  doi character varying(1024),
  journal character varying(1024) NOT NULL,
  journal_volume character varying(1024) NOT NULL,
  online_date timestamp without time zone,
  page_begin integer NOT NULL,
  page_end integer NOT NULL,

  authors character varying(102400),
  author_orgs character varying(204800),
  nihms_id integer,

  abstr character varying(204800),
  keyword character varying(102400),

  CONSTRAINT paper_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.paper
  OWNER TO postgres;

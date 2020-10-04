-- Table: public.users

-- DROP TABLE public.users;

CREATE TABLE public.users
(
    login character varying COLLATE pg_catalog."default" NOT NULL,
    id integer NOT NULL DEFAULT nextval('users_id_seq'::regclass),
    password character varying COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT users_pk PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.users
    OWNER to postgres;
-- Index: users_id_uindex

-- DROP INDEX public.users_id_uindex;

CREATE UNIQUE INDEX users_id_uindex
    ON public.users USING btree
    (id ASC NULLS LAST)
    TABLESPACE pg_default;
-- Index: users_login_uindex

-- DROP INDEX public.users_login_uindex;

CREATE UNIQUE INDEX users_login_uindex
    ON public.users USING btree
    (login COLLATE pg_catalog."default" ASC NULLS LAST)
    TABLESPACE pg_default;
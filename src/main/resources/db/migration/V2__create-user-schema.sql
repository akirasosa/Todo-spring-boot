-- user
CREATE TABLE _user (
    id bigint NOT NULL,
    username character varying(255) NOT NULL,
    password character varying(255) NOT NULL
);
CREATE SEQUENCE user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
ALTER SEQUENCE user_id_seq OWNED BY _user.id;
ALTER TABLE ONLY _user ALTER COLUMN id SET DEFAULT nextval('user_id_seq'::regclass);
ALTER TABLE ONLY _user
    ADD CONSTRAINT user_pkey PRIMARY KEY (id);

-- user_authority
CREATE TABLE _user_authority (
    user_id bigint NOT NULL,
    authority character varying(255) NOT NULL
);
ALTER TABLE ONLY _user_authority
    ADD CONSTRAINT user_authority_pkey PRIMARY KEY (user_id, authority);

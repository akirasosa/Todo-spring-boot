-- user
CREATE TABLE _user (
    id bigint NOT NULL,
    username character varying(255) NOT NULL,
    encoded_password character varying(255) NOT NULL
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

INSERT INTO _user (encoded_password, username) VALUES (
'$2a$10$3jjGeDlBfpRtXfRhQap5Tuq1fhVm4q7wbz/FN52qmnzg7yU9p4JTa', /* demo */
'demo');


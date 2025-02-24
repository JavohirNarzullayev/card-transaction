CREATE SEQUENCE users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


create table public.users (
    id         bigint primary key not null default nextval('users_id_seq'::regclass),
    balance    numeric(38, 2),
    created_at timestamp(6) without time zone,
    name       character varying(255)
);




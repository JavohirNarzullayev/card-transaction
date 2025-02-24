create table public.transaction (
   request_id uuid primary key not null,
   amount numeric(38,2),
   created_at timestamp(6) without time zone,
   remaining numeric(38,2),
   type character varying(255),
   user_id bigint
);


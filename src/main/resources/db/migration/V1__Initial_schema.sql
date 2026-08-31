create table book (
    id bigserial  primary key not null,
    version integer not null,
    created_date timestamp not null,
    last_modified_date timestamp not null,
    isbn varchar(255) not null unique,
    title varchar(255) not null,
    author varchar(255) not null,
    price double precision not null,
    publisher varchar(255) not null
);
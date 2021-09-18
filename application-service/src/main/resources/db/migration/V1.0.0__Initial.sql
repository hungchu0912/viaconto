create table application_loan
(
    id                serial,
    personal_id       varchar(100),
    first_name        varchar(100),
    last_name         varchar(100),
    birth_date        timestamp,
    salary            decimal,
    monthly_liability decimal,
    requested_mount   decimal,
    score             decimal,
    status            varchar(30),
    requested_term    smallint,
    created_at        timestamp,
    updated_at        timestamp,
    constraint application_loan_pk primary key (id)
);
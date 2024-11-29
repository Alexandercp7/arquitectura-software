create database empresa;

\connect empresa;

create table emp(
                    id SERIAL PRIMARY KEY ,
                    name character varying(80) not null,
                    email character varying(25),
                    address character varying(80) ,
                    phone character varying(25) ,

                    constraint emps_name_unique UNIQUE (name)
);
INSERT INTO emp (name, email, address, phone)
VALUES ('Jane Smith', 'jane.smith@example.com', '456 Elm St', '555-5678');

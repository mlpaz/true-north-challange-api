create table users (
	id uuid DEFAULT gen_random_uuid(),
	email varchar(255) NOT NULL,
	credit decimal NOT NULL,
	password varchar(255) NOT NULL,
	status varchar (50) NOT NULL check (status in ('ACTIVE','INACTIVE')),
	created_at timestamp not null default now(),
    updated_at timestamp not null default now(),
    deleted_at timestamp,
    UNIQUE (email),
    PRIMARY KEY (id)
);

INSERT INTO users (created_at, updated_at, deleted_at, email, status, credit, password) VALUES
(now(), now(), null, 'mlpaz.code@gmail.com', 'INACTIVE', 100, 'oyFIbMU672dmmZ97fLpf6Q==');
INSERT INTO users (created_at, updated_at, deleted_at, email, status, credit, password) VALUES
(now(), now(), null, 'leon.paz@gmail.com', 'INACTIVE', 5, 'oyFIbMU672dmmZ97fLpf6Q==');

create table operation (
	id uuid DEFAULT gen_random_uuid(),
	type varchar (50) not null check (type in ('ADDITION', 'SUBTRACTION', 'MULTIPLICATION', 'DIVISION', 'SQUARE_ROOT', 'RANDOM_STRING')),
	cost decimal not null,
    PRIMARY KEY (id),
    UNIQUE (type)
);

INSERT INTO operation (type, cost) VALUES
('ADDITION', 0.5);
INSERT INTO operation (type, cost) VALUES
('SUBTRACTION', 0.75);
INSERT INTO operation (type, cost) VALUES
('MULTIPLICATION', 1);
INSERT INTO operation (type, cost) VALUES
('DIVISION', 2);
INSERT INTO operation (type, cost) VALUES
('SQUARE_ROOT', 5);
INSERT INTO operation (type, cost) VALUES
('RANDOM_STRING', 10);

create table record (
	id uuid DEFAULT gen_random_uuid(),
	operation_id uuid NOT NULL,
	user_id uuid NOT NULL,
	user_balance decimal NOT NULL,
	amount decimal NOT NULL,
	operation_response varchar(255),
    created_at timestamp not null default now(),
    updated_at timestamp not null default now(),
    deleted_at timestamp,
    PRIMARY KEY (id)
);

INSERT INTO users (created_at, updated_at, deleted_at, email, status, credit, password) VALUES
(now(), now(), null, 'mlpaz.code@gmail.com', 'INACTIVE', 100, 'oyFIbMU672dmmZ97fLpf6Q==');
INSERT INTO users (created_at, updated_at, deleted_at, email, status, credit, password) VALUES
(now(), now(), null, 'leon.paz@gmail.com', 'INACTIVE', 5, 'oyFIbMU672dmmZ97fLpf6Q==');

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
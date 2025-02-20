INSERT INTO users (id, created_at, updated_at, deleted_at, email, status, credit, password) VALUES
('1b1b06ee-db62-47e7-acbb-3e3256c1451c', now(), now(), null, 'mlpaz.code@gmail.com', 'INACTIVE', 100, 'oyFIbMU672dmmZ97fLpf6Q==');
INSERT INTO users (id, created_at, updated_at, deleted_at, email, status, credit, password) VALUES
('d91e133e-a6e6-4193-90c3-afc842055c7a', now(), now(), null, 'leon.paz@gmail.com', 'INACTIVE', 5, 'oyFIbMU672dmmZ97fLpf6Q==');

INSERT INTO operation (id, type, cost) VALUES
('0913e445-275b-4f4d-a70b-7b2a43fd93c6', 'ADDITION', 0.5);
INSERT INTO operation (id, type, cost) VALUES
('2463cabd-40ec-4c49-887b-f286d78c5004', 'SUBTRACTION', 0.75);
INSERT INTO operation (id, type, cost) VALUES
('2a89b0c2-87f6-4524-bf7c-03f952ee9576', 'MULTIPLICATION', 1);
INSERT INTO operation (id, type, cost) VALUES
('b8560fdd-caef-4847-9858-3a8e1f1b2322', 'DIVISION', 2);
INSERT INTO operation (id, type, cost) VALUES
('bd52f4e2-74af-47c1-8dd9-d4d739dd98cc', 'SQUARE_ROOT', 5);
INSERT INTO operation (id, type, cost) VALUES
('cd7f793e-2b1e-4518-ad93-333433da7046', 'RANDOM_STRING', 10);
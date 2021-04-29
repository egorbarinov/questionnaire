INSERT INTO questionnaire.user (id, first_name, last_name, username, password, email, deleted)
VALUES ('92c1453e-b731-4d7b-8c2c-945339f9b1bf', 'John', 'Silver', 'admin', '$2y$12$mxC5L3gNOZ5RdohwzlWZGO.tfyzRh34WzmCDs2GNTHIaHjxGnFkUK', 'john.silver@mail.ru', false),
       ('81f977c2-ba6e-4780-94a6-8212e647d4eb', 'Sarah', 'Connor', 'guest', '$2y$12$KjH4l7SKLrWbZnX0Nnked.TE8f/6WWt3gKP1slFt.q.vgD29Mz6mW', 'qwerty@gmail.com', false),
       ('e65d5367-ef4d-4f3d-9d77-bb6bd6e39ec7', 'Pol', 'Alan', 'manager', '$2y$12$uFSKJ1OdwNbK7vMbXF8qZ.43KOGdxiDyFBFmS1WKNtJD3Xgqo0ACi', 'qwerty@gmail.com', false);

INSERT INTO questionnaire.role (id, name)
VALUES  ('95043703-5d14-448d-9487-bc16c88cc5eb', 'ROLE_USER'),
        ('79b9dce1-d9b6-401c-b195-07e545574879', 'ROLE_MANAGER'),
        ('0c004091-8eaa-4fdd-bbe8-afbdde7b6d71', 'ROLE_ADMIN');

INSERT INTO questionnaire.user_role (user_id, role_id)
SELECT (SELECT id FROM questionnaire.user WHERE username = 'admin'), (SELECT id FROM questionnaire.role WHERE name = 'ROLE_ADMIN')
UNION ALL
SELECT (SELECT id FROM questionnaire.user  WHERE username = 'guest'), (SELECT id FROM questionnaire.role WHERE name = 'ROLE_USER')
UNION ALL
SELECT (SELECT id FROM questionnaire.user  WHERE username = 'manager'), (SELECT id FROM questionnaire.role WHERE name = 'ROLE_MANAGER');

INSERT INTO questionnaire.question_type (id, name)
VALUES  ('961dc8b9-6cc1-444c-b843-c16f71ca9f04', 'Одиночный'),
        ('7f1d5c0c-f6e4-4356-83d8-949e30b9b918', 'Множественный'),
        ('942c9584-9804-4ea4-86a6-0db30dcc681b', 'Вопрос со свободным ответом');



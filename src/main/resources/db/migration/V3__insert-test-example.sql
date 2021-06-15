
INSERT INTO questionnaire.test (id, author_id, name, duration)
VALUES ('dec15719-95f2-46a1-931a-865617972d70',
        'e65d5367-ef4d-4f3d-9d77-bb6bd6e39ec7',
        'Пробный тест', 30);

INSERT INTO questionnaire.chapter (id, test_id, name)
VALUES ('baafe2c6-aad5-4bbc-bb52-89ad9bf00d50',
        'dec15719-95f2-46a1-931a-865617972d70',
        'Единственный раздел');

INSERT INTO questionnaire.question (id, question, question_type_id)
VALUES ('7d963490-25b5-4394-9c24-9797dc992302',
        'Кто из президентов США написал свой собственный рассказ про Шерлока Холмса?',
        '961dc8b9-6cc1-444c-b843-c16f71ca9f04'),

       ('5ba638c2-4997-4109-8743-43d4666b807e',
        'Какую пошлину ввели в XII  веке в Англии для того чтобы заставить мужчин пойти на войну?',
        '961dc8b9-6cc1-444c-b843-c16f71ca9f04'),

       ('db8c2ed7-2bc7-4be6-8bd5-e5cc437bda9e',
        'Откуда пошло выражение «деньги не пахнут?',
        '961dc8b9-6cc1-444c-b843-c16f71ca9f04'),

       ('d3755f1d-639a-42f7-9081-4ef36f939df7',
        'Туристы, приезжающие на Майорку, обязаны заплатить налог…',
        '961dc8b9-6cc1-444c-b843-c16f71ca9f04'),

       ('d9b9f41b-d6f3-477b-bf5f-43c3b8984c20',
        'Основой для «Сказки о рыбаке и рыбке Пушкина послужила сказка братьев Гримм «Рыбак и его жена». В ней немецкая «коллега» нашей старухи превратилась в:',
        '961dc8b9-6cc1-444c-b843-c16f71ca9f04'),

       ('0cdae115-03b3-4546-80de-950ebe2ddb74',
        'Российский мультфильм, удостоенный «Оскара», — это…',
        '942c9584-9804-4ea4-86a6-0db30dcc681b'),

       ('3310eba3-e157-4183-86d6-02abe9d623b7',
        'В каком городе не работал великий композитор 18-го века Кристоф Виллибальд Глюк?',
        '942c9584-9804-4ea4-86a6-0db30dcc681b');

INSERT INTO questionnaire.question_chapter (question_id, chapter_id)
VALUES ('7d963490-25b5-4394-9c24-9797dc992302',
        'baafe2c6-aad5-4bbc-bb52-89ad9bf00d50'),

       ('5ba638c2-4997-4109-8743-43d4666b807e',
        'baafe2c6-aad5-4bbc-bb52-89ad9bf00d50'),

       ('db8c2ed7-2bc7-4be6-8bd5-e5cc437bda9e',
        'baafe2c6-aad5-4bbc-bb52-89ad9bf00d50'),

       ('d3755f1d-639a-42f7-9081-4ef36f939df7',
        'baafe2c6-aad5-4bbc-bb52-89ad9bf00d50'),

       ('d9b9f41b-d6f3-477b-bf5f-43c3b8984c20',
        'baafe2c6-aad5-4bbc-bb52-89ad9bf00d50'),

       ('0cdae115-03b3-4546-80de-950ebe2ddb74',
        'baafe2c6-aad5-4bbc-bb52-89ad9bf00d50'),

       ('3310eba3-e157-4183-86d6-02abe9d623b7',
        'baafe2c6-aad5-4bbc-bb52-89ad9bf00d50');

INSERT INTO questionnaire.answer (id, answer, correct, question_id)
VALUES ('efa27049-f686-4527-8b08-739d35ee74dd', 'Джон Кеннеди', false ,
        '7d963490-25b5-4394-9c24-9797dc992302'),

       ('86aa7f40-b675-4a09-bf0d-2cdd96c6f3ee', 'Франклин Рузвельт', true,
        '7d963490-25b5-4394-9c24-9797dc992302'),

       ('84361be4-fb05-4d8a-9c02-4e5fd12d0474', 'Рональд Рейган', false,
        '7d963490-25b5-4394-9c24-9797dc992302'),

       ('b98c67ab-d2f6-42b3-b315-3aa06f50c9ab', 'Налог на тунеядство', false,
        '5ba638c2-4997-4109-8743-43d4666b807e'),

       ('48fc46ef-8651-4287-a147-4d18c0e4c13a', 'Налог на трусость', true,
        '5ba638c2-4997-4109-8743-43d4666b807e'),

       ('e7d3cabc-31f1-4e36-8b1f-699779cac722',
        'Налог на отсутствие сапог', false,
        '5ba638c2-4997-4109-8743-43d4666b807e'),

       ('f4705d3e-892b-48cc-9d49-69706cff93bd',
        'От подателей за провоз парфюмерии', false,
        'db8c2ed7-2bc7-4be6-8bd5-e5cc437bda9e'),

       ('65852c78-856f-4528-a7c8-68d4aa6855be',
        'От сборов за нестиранные носки', false,
        'db8c2ed7-2bc7-4be6-8bd5-e5cc437bda9e'),

       ('2bd119a3-fc3b-41a5-ad3e-a5e04290428f', 'От налога на туалеты', true,
        'db8c2ed7-2bc7-4be6-8bd5-e5cc437bda9e'),

       ('9214bd92-ade1-4b04-9cc4-8787b2cfb762', 'На плавки', false,
        'd3755f1d-639a-42f7-9081-4ef36f939df7'),

       ('02402164-a2ba-4bcb-8a9a-cd6c49f51867', 'На пальмы', false,
        'd3755f1d-639a-42f7-9081-4ef36f939df7'),

       ('b638e608-02b8-433e-9096-3640c8778950', 'На солнце', true,
        'd3755f1d-639a-42f7-9081-4ef36f939df7'),

       ('7b063f79-f563-4c76-b482-63dd52a00272', 'Папу Римского', true,
        'd9b9f41b-d6f3-477b-bf5f-43c3b8984c20'),

       ('21b87c02-918a-41ff-94d3-e75bb162b656', 'Королеву', false,
        'd9b9f41b-d6f3-477b-bf5f-43c3b8984c20'),

       ('2c873e3f-a1af-49ed-959d-9b90c5b80e07', 'Директора рыбзавода', false,
        'd9b9f41b-d6f3-477b-bf5f-43c3b8984c20'),

       ('8cf41605-5c32-4cd8-843c-222553dbda99',
        'Командира отряда водолазов', false,
        'd9b9f41b-d6f3-477b-bf5f-43c3b8984c20'),

       ('0fc7bdb3-8bf5-4fe9-9c1f-83a6ac2b9b29', 'Старик и море', true,
        '0cdae115-03b3-4546-80de-950ebe2ddb74'),

       ('089aac93-13fd-45b5-85d0-7b227adecc68', 'Берлин', true,
        '3310eba3-e157-4183-86d6-02abe9d623b7');

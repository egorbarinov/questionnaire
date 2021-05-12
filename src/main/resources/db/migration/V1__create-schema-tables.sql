alter table if exists questionnaire.answer
drop constraint if exists FK8frr4bcabmmeyyu60qt7iiblo;

alter table if exists questionnaire.chapter
drop constraint if exists FKdhb9s6rxymbvvecwh6gng6ras;

alter table if exists questionnaire.question
drop constraint if exists FK7svspov4rexjawqdvi2jni81u;

alter table if exists questionnaire.question_chapter
drop constraint if exists FKrqr7qatk7qx58hl61nrb4284c;

alter table if exists questionnaire.question_chapter
drop constraint if exists FK9rjfw7sxvitb4v3xb8mn9ekg9;

alter table if exists questionnaire.selected_answer
drop constraint if exists FKekvxn39ai6od1i39vgtpko96j;

alter table if exists questionnaire.selected_answer
drop constraint if exists FKik27s0xli1taed1axw8cdwca6;

alter table if exists questionnaire.user_role
drop constraint if exists FKa68196081fvovjhkek5m97n3y;

alter table if exists questionnaire.user_role
drop constraint if exists FK859n2jvi8ivhui0rl0esws6o;

alter table if exists questionnaire.user_test
drop constraint if exists FKt0r12g1hnb4e9f0j12yn4ky7g;

alter table if exists questionnaire.user_test
drop constraint if exists FK54073ir8kqlk4cfahr56begn4;

alter table if exists questionnaire.user_test_question
drop constraint if exists FKbsqbegasbgpxxvvmnrjmyxflh;

alter table if exists questionnaire.user_test_question
drop constraint if exists FK93h1jfnqt839r8pttcd9h8o0q;

drop table if exists questionnaire.answer cascade;

drop table if exists questionnaire.chapter cascade;

drop table if exists questionnaire.question cascade;

drop table if exists questionnaire.question_chapter cascade;

drop table if exists questionnaire.question_type cascade;

drop table if exists questionnaire.role cascade;

drop table if exists questionnaire.selected_answer cascade;

drop table if exists questionnaire.user cascade;

drop table if exists questionnaire.user_role cascade;

drop table if exists questionnaire.user_test cascade;

drop table if exists questionnaire.user_test_question cascade;

alter table if exists test
drop constraint if exists FKri5vnwoi057j5f2a6angur1xp;

drop table if exists test cascade;

create table questionnaire.answer (
                                      id uuid not null,
                                      answer varchar(255),
                                      correct boolean,
                                      deleted boolean default false,
                                      question_id uuid,
                                      primary key (id)
);


create table questionnaire.chapter (
                                       id uuid not null,
                                       description varchar(255),
                                       deleted boolean default false,
                                       name varchar(255),
                                       test_id uuid,
                                       primary key (id)
);

create table questionnaire.question (
                                        id uuid not null,
                                        deleted boolean default false,
                                        question varchar(255),
                                        question_type_id uuid,
                                        primary key (id)
);

create table questionnaire.question_chapter (
                                                chapter_id uuid not null,
                                                question_id uuid not null,
                                                primary key (chapter_id, question_id)
);

create table questionnaire.question_type (
                                             id uuid not null,
                                             name varchar(255),
                                             primary key (id)
);

create table questionnaire.role (
                                    id uuid not null,
                                    name varchar(255),
                                    primary key (id)
);

create table questionnaire.selected_answer (
                                               test_question_id uuid not null,
                                               answer_id uuid not null,
                                               primary key (test_question_id, answer_id)
);

create table questionnaire.user (
                                    id uuid not null,
                                    email varchar(255),
                                    first_name varchar(255),
                                    deleted boolean default false,
                                    last_name varchar(255),
                                    password varchar(255),
                                    username varchar(255),
                                    primary key (id)
);

create table questionnaire.user_role (
                                         user_id uuid not null,
                                         role_id uuid not null,
                                         primary key (user_id, role_id)
);

create table questionnaire.user_test (
                                         id uuid not null,
                                         finished timestamp,
                                         passed boolean,
                                         score int2,
                                         started timestamp,
                                         test_id uuid,
                                         user_id uuid,
                                         primary key (id)
);

create table questionnaire.user_test_question (
                                                  id uuid not null,
                                                  answered timestamp,
                                                  user_answer varchar(255),
                                                  correct boolean,
                                                  question_id uuid,
                                                  user_test_id uuid,
                                                  primary key (id)
);

create table test (
                      id uuid not null,
                      created timestamp,
                      description varchar(255),
                      duration int2,
                      deleted boolean default false,
                      name varchar(255),
                      pass_score int2 default 1,
                      author_id uuid,
                      primary key (id)
);

alter table if exists questionnaire.answer
    add constraint FK8frr4bcabmmeyyu60qt7iiblo
    foreign key (question_id)
    references questionnaire.question;

alter table if exists questionnaire.chapter
    add constraint FKdhb9s6rxymbvvecwh6gng6ras
    foreign key (test_id)
    references test;

alter table if exists questionnaire.question
    add constraint FK7svspov4rexjawqdvi2jni81u
    foreign key (question_type_id)
    references questionnaire.question_type;

alter table if exists questionnaire.question_chapter
    add constraint FKrqr7qatk7qx58hl61nrb4284c
    foreign key (question_id)
    references questionnaire.question;

alter table if exists questionnaire.question_chapter
    add constraint FK9rjfw7sxvitb4v3xb8mn9ekg9
    foreign key (chapter_id)
    references questionnaire.chapter;

alter table if exists questionnaire.selected_answer
    add constraint FKekvxn39ai6od1i39vgtpko96j
    foreign key (answer_id)
    references questionnaire.answer;

alter table if exists questionnaire.selected_answer
    add constraint FKik27s0xli1taed1axw8cdwca6
    foreign key (test_question_id)
    references questionnaire.user_test_question;

alter table if exists questionnaire.user_role
    add constraint FKa68196081fvovjhkek5m97n3y
    foreign key (role_id)
    references questionnaire.role;

alter table if exists questionnaire.user_role
    add constraint FK859n2jvi8ivhui0rl0esws6o
    foreign key (user_id)
    references questionnaire.user;

alter table if exists questionnaire.user_test
    add constraint FKt0r12g1hnb4e9f0j12yn4ky7g
    foreign key (test_id)
    references test;

alter table if exists questionnaire.user_test
    add constraint FK54073ir8kqlk4cfahr56begn4
    foreign key (user_id)
    references questionnaire.user;

alter table if exists questionnaire.user_test_question
    add constraint FKbsqbegasbgpxxvvmnrjmyxflh
    foreign key (question_id)
    references questionnaire.question;

alter table if exists questionnaire.user_test_question
    add constraint FK93h1jfnqt839r8pttcd9h8o0q
    foreign key (user_test_id)
    references questionnaire.user_test;

alter table if exists test
    add constraint FKri5vnwoi057j5f2a6angur1xp
    foreign key (author_id)
    references questionnaire.user;

create table review
(
    id          int auto_increment  primary key,
    title       varchar(255)                        not null,
    review_text varchar(10000)                      null,
    created_ts  timestamp default CURRENT_TIMESTAMP null,
    recommended tinyint(1)                          null,
    product_id  int                                 not null
);

-- auto-generated definition
create table product
(
    product_id          int auto_increment primary key,
    name        varchar(255) not null,
    description varchar(255) not null
);

-- auto-generated definition
create table comment
(
    comment_id           int auto_increment primary key,
    title                varchar(255)                        not null,
    comment              varchar(255)                      null,
    review_id            int                                 not null
);
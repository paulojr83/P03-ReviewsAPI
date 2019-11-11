create table review
(
    id              int auto_increment  primary key,
    title           varchar(255)                        not null,
    score           int                                 not null,
    review_text      varchar(10000)                      not null,
    created_At       timestamp default CURRENT_TIMESTAMP not null,
    updated_At       timestamp default CURRENT_TIMESTAMP not null,
    product_id      int                                 not null
);

-- auto-generated definition
create table product
(
    product_id          int auto_increment primary key,
    name                varchar(255) not null,
    description         varchar(255) not null,
    createdAt           timestamp default CURRENT_TIMESTAMP null,
    updatedAt           timestamp default CURRENT_TIMESTAMP null,
);

-- auto-generated definition
create table comment
(
    comment_id          int auto_increment primary key,
    title               varchar(255)                        not null,
    comment             varchar(255)                        null,
    review_id           int                                 not null,
    createdAt           timestamp default CURRENT_TIMESTAMP null,
    updatedAt           timestamp default CURRENT_TIMESTAMP null,
);

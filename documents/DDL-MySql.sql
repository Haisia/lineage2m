create table if not exists api_key
(
    pk    int auto_increment
        primary key,
    `key` text not null,
    constraint pk
        unique (pk)
);

create table if not exists item_info
(
    pk                  int auto_increment
        primary key,
    item_id             int           not null comment '아이템 아이디',
    item_name           varchar(50)   not null comment '아이템 이름',
    enchant_level       int default 0 not null comment '아이템 강화 수치',
    grade               varchar(10)   not null comment '아이템 등급',
    grade_name          varchar(10)   not null comment '아이템 등급 이름',
    image               text          null,
    trade_category_name varchar(50)   not null comment '거래소 카테고리 이름',
    constraint item_id
        unique (item_id),
    constraint pk
        unique (pk)
)
    comment '아이템 스펙관련 정보';

create table if not exists item_option
(
    pk            int auto_increment
        primary key,
    option_name   varchar(50) not null comment '옵션의 이름',
    display       varchar(50) null comment '옵션의 수치',
    extra_display varchar(50) null comment '추가옵션',
    description   varchar(50) null comment '옵션설명',
    constraint pk
        unique (pk)
)
    comment '아이템의 성능관련 정보들';

create table if not exists item_info_option_map
(
    pk             int auto_increment
        primary key,
    item_info_pk   int not null,
    item_option_pk int not null,
    constraint pk
        unique (pk),
    constraint item_info_fk
        foreign key (item_info_pk) references item_info (pk),
    constraint item_option_fk
        foreign key (item_option_pk) references item_option (pk)
)
    comment 'item_info, item_option 의 n:m 관계를
1:n - 1:m 관계로 매핑해주는 테이블';

create table if not exists world
(
    pk         int auto_increment
        primary key,
    world_id   int         null,
    world_name varchar(10) not null,
    constraint pk
        unique (pk),
    constraint world_name
        unique (world_name)
)
    comment '월드목록';

create table if not exists server
(
    pk          int auto_increment
        primary key,
    server_id   int         not null,
    server_name varchar(10) not null,
    world_pk    int         not null,
    constraint pk
        unique (pk),
    constraint worldFk
        foreign key (world_pk) references world (pk)
)
    comment '서버목록';


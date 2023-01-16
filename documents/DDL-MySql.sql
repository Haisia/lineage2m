create table api_key
(
    pk    int auto_increment
        primary key,
    `key` text not null,
    constraint pk
        unique (pk)
);

create table item_info
(
    pk                  int auto_increment
        primary key,
    item_id             int         not null comment '아이템 아이디',
    item_name           varchar(50) not null comment '아이템 이름',
    grade               varchar(10) not null comment '아이템 등급',
    grade_name          varchar(10) not null comment '아이템 등급 이름',
    image               text        null,
    trade_category_name varchar(50) not null comment '거래소 카테고리 이름',
    constraint item_id
        unique (item_id),
    constraint pk
        unique (pk)
)
    comment '아이템 스펙관련 정보';

create table attribute
(
    pk                 int auto_increment
        primary key,
    droppable          tinyint(1) default 0 not null comment '사망 시 드랍 여부',
    tradeable          tinyint(1) default 0 not null comment '거래 가능 여부',
    collection_count   int        default 0 not null comment '컬렉션 수',
    weight             int        default 0 not null comment '무게',
    description        text                 null comment '설명',
    storable           tinyint(1) default 0 not null comment '창고 저장 가능 여부
',
    safe_enchant_level int        default 0 not null comment '안전강화 단계',
    material_name      varchar(50)          null,
    enchantable        tinyint(1) default 0 not null comment '강화 가능 여부',
    item_info_pk       int                  not null,
    constraint item_pk
        unique (item_info_pk),
    constraint item_pk_2
        unique (item_info_pk),
    constraint pk
        unique (pk),
    constraint attribute_item_info_pk_fk
        foreign key (item_info_pk) references item_info (pk)
)
    comment '아이템 속성';

create table enchant_level
(
    pk            int auto_increment
        primary key,
    enchant_level int default 0 not null comment '인첸트 레벨',
    item_info_pk  int           not null,
    constraint pk
        unique (pk),
    constraint enchant_level_item_info_pk_fk
        foreign key (item_info_pk) references item_info (pk)
)
    comment '아이템 인첸트 레벨별로 옵션 매핑을 제공하는 테이블';

create table item_option
(
    pk               int auto_increment,
    option_name      varchar(50) not null comment '옵션의 이름',
    display          varchar(50) null comment '옵션의 수치',
    extra_display    varchar(50) null comment '추가옵션',
    description      text        null comment '옵션설명',
    enchant_level_pk int         not null,
    constraint item_option_pk
        unique (pk),
    constraint item_option_enchant_level_pk_fk
        foreign key (enchant_level_pk) references enchant_level (pk)
)
    comment '아이템의 성능관련 정보들';

create table item_price_stats
(
    pk            int auto_increment
        primary key,
    create_date   date          not null comment 'row 추가 일자',
    last_price    int           not null comment '최근거래가격',
    enchant_level int default 0 not null comment '강화레벨',
    world_id      int           not null,
    world_name    varchar(10)   not null,
    item_info_pk  int           not null,
    constraint pk
        unique (pk),
    constraint item_price_stats_item_info_pk_fk
        foreign key (item_info_pk) references item_info (pk)
)
    comment '일별 아이템 가격정보 저장 테이블';

create table world
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

create table server
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


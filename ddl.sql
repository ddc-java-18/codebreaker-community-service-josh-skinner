create sequence game_seq start with 1 increment by 50;
create sequence guess_seq start with 1 increment by 50;
create sequence user_profile_seq start with 1 increment by 50;
create table game
(
    code_length  integer                     not null,
    pool_size    integer                     not null,
    created      timestamp(6) with time zone not null,
    id           bigint                      not null,
    player_id    bigint                      not null,
    external_key uuid                        not null unique,
    pool         varchar(20)                 not null,
    secret_code  varchar(255)                not null,
    primary key (id)
);
create table guess
(
    close        integer                     not null,
    correct      integer                     not null,
    created      timestamp(6) with time zone not null,
    game_id      bigint                      not null,
    guess_id     bigint                      not null,
    external_key uuid                        not null unique,
    code         varchar(20)                 not null,
    primary key (guess_id)
);
create table user_profile
(
    accessed        timestamp(6) with time zone not null,
    created         timestamp(6) with time zone not null,
    user_profile_id bigint                      not null,
    external_key    uuid                        not null unique,
    display_name    varchar(255)                not null unique,
    oauth_key       varchar(255)                not null unique,
    primary key (user_profile_id)
);
create index IDXjfmcaveni4s00otoq5x2j5n65 on game (pool_size, code_length);
alter table if exists game
    add constraint FKe2gg67v12imv2fr2nx0af0usn foreign key (player_id) references user_profile;
alter table if exists guess
    add constraint FK17wrv62yn4umhcoh8y608l16d foreign key (game_id) references game;
create sequence game_seq start with 1 increment by 50;
create sequence guess_seq start with 1 increment by 50;
create sequence user_profile_seq start with 1 increment by 50;
create table game (code_length integer not null, pool_size integer not null, created timestamp(6) with time zone not null, id bigint not null, player_id bigint not null, external_key uuid not null unique, pool varchar(20) not null, secret_code varchar(255) not null, primary key (id));
create table guess (close integer not null, correct integer not null, created timestamp(6) with time zone not null, game_id bigint not null, guess_id bigint not null, external_key uuid not null unique, code varchar(20) not null, primary key (guess_id));
create table user_profile (accessed timestamp(6) with time zone not null, created timestamp(6) with time zone not null, user_profile_id bigint not null, external_key uuid not null unique, display_name varchar(255) not null unique, oauth_key varchar(255) not null unique, primary key (user_profile_id));
create index IDXjfmcaveni4s00otoq5x2j5n65 on game (pool_size, code_length);
alter table if exists game add constraint FKe2gg67v12imv2fr2nx0af0usn foreign key (player_id) references user_profile;
alter table if exists guess add constraint FK17wrv62yn4umhcoh8y608l16d foreign key (game_id) references game;

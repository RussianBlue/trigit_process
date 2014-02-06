# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table users (
  id                        bigint auto_increment not null,
  user_id                   varchar(255),
  password                  varchar(255),
  name                      varchar(255),
  email                     varchar(255),
  project_id                varchar(255),
  phone_number              varchar(255),
  created_at                datetime,
  last_login_at             datetime,
  sign_in_count             bigint,
  constraint pk_users primary key (id))
;




# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table users;

SET FOREIGN_KEY_CHECKS=1;


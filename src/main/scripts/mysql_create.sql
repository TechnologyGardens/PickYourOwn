create table category (id bigint not null auto_increment, name varchar(255), primary key (id)) engine=InnoDB;
create table category_products (category_id bigint not null, product_id bigint not null, primary key (category_id, product_id)) engine=InnoDB;
create table farm (id bigint not null auto_increment, description longtext, image longblob, name varchar(255), site_id bigint, primary key (id)) engine=InnoDB;
create table farm_farmers (farmer_id bigint not null, farm_id bigint not null, primary key (farmer_id, farm_id)) engine=InnoDB;
create table farm_products (farm_id bigint not null, product_id bigint not null, primary key (farm_id, product_id)) engine=InnoDB;
create table farmer (id bigint not null auto_increment, email varchar(255), first_name varchar(255), last_name varchar(255), social varchar(255), telephone varchar(255), web varchar(255), primary key (id)) engine=InnoDB;
create table price (id bigint not null auto_increment, max_quantity integer not null, min_quantity integer not null, unit varchar(255), value decimal(19,2), farm_id bigint, product_id bigint, primary key (id)) engine=InnoDB;
create table product (id bigint not null auto_increment, name varchar(255), primary key (id)) engine=InnoDB;
create table regular_business_hours (id bigint not null auto_increment, closes_at datetime, days_of_the_week integer not null, opens_at datetime, regular_business_hours_id bigint, primary key (id)) engine=InnoDB;
create table site (id bigint not null auto_increment, address varchar(255), city varchar(255), country varchar(255), directions varchar(255), postal_code varchar(255), season_closes varchar(255), season_opens varchar(255), social varchar(255), state_province varchar(255), web varchar(255), farm_id bigint, primary key (id)) engine=InnoDB;
create table special_event_business_hours (id bigint not null auto_increment, closes_at datetime, name varchar(255), opens_at datetime, special_event_business_hours_id bigint, primary key (id)) engine=InnoDB;
alter table category_products add constraint FKqwansr9rihghep5rgvu2yctl7 foreign key (product_id) references category (id);
alter table category_products add constraint FKd9j7gma323lpd7p857qrs4j56 foreign key (category_id) references product (id);
alter table farm add constraint FKfyftqvd15pl0i8ctcq4wexe1c foreign key (site_id) references site (id);
alter table farm_farmers add constraint FKms6s7nama94qtsxe5u27akaor foreign key (farm_id) references farm (id);
alter table farm_farmers add constraint FK2lqy730obvr7luk9y3p4141xt foreign key (farmer_id) references farmer (id);
alter table farm_products add constraint FK238x77u1dgxcy2dhlmhmsqh1f foreign key (product_id) references farm (id);
alter table farm_products add constraint FKjs331u3mylp1v1u9me22akwi6 foreign key (farm_id) references product (id);
alter table price add constraint FKjs4y5f5b497nfv6jgij9499jq foreign key (farm_id) references farm (id);
alter table price add constraint FKk4mbgqf5yru5ib5b6w5l6ukkj foreign key (product_id) references product (id);
alter table regular_business_hours add constraint FKgjlbb0i5g9to62md32ypdtwsi foreign key (regular_business_hours_id) references site (id);
alter table site add constraint FKgm9mv51mey17nt9fnesuow5qo foreign key (farm_id) references farm (id);
alter table special_event_business_hours add constraint FKi7lcmdigm9qlkipbbkgf2362y foreign key (special_event_business_hours_id) references site (id);

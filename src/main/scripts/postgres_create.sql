create table category (id  bigserial not null, name varchar(255), primary key (id));
create table category_products (category_id int8 not null, product_id int8 not null, primary key (category_id, product_id));
create table farm (id  bigserial not null, description text, image oid, name varchar(255), site int8, primary key (id));
create table farm_farmers (farmer_id int8 not null, farm_id int8 not null, primary key (farmer_id, farm_id));
create table farm_products (farm_id int8 not null, product_id int8 not null, primary key (farm_id, product_id));
create table farmer (id  bigserial not null, email varchar(255), first_name varchar(255), last_name varchar(255), social varchar(255), telephone varchar(255), web varchar(255), primary key (id));
create table price (id  bigserial not null, max_quantity int4 not null, min_quantity int4 not null, unit varchar(255), value numeric(19, 2), farm_id int8, product_id int8, primary key (id));
create table product (id  bigserial not null, name varchar(255), primary key (id));
create table regular_business_hours (id  bigserial not null, closes_at timestamp, days_of_the_week int4 not null, opens_at timestamp, regular_business_hours_id int8, primary key (id));
create table site (id  bigserial not null, address varchar(255), city varchar(255), country varchar(255), directions varchar(255), postal_code varchar(255), season_closes varchar(255), season_opens varchar(255), social varchar(255), state_province varchar(255), web varchar(255), farm int8, primary key (id));
create table special_event_business_hours (id  bigserial not null, closes_at timestamp, name varchar(255), opens_at timestamp, special_event_business_hours_id int8, primary key (id));
alter table category_products add constraint FKqwansr9rihghep5rgvu2yctl7 foreign key (product_id) references category;
alter table category_products add constraint FKd9j7gma323lpd7p857qrs4j56 foreign key (category_id) references product;
alter table farm add constraint FKesw2mpsuxk1e8eg8lqc8iheqj foreign key (site) references site;
alter table farm_farmers add constraint FKms6s7nama94qtsxe5u27akaor foreign key (farm_id) references farm;
alter table farm_farmers add constraint FK2lqy730obvr7luk9y3p4141xt foreign key (farmer_id) references farmer;
alter table farm_products add constraint FK238x77u1dgxcy2dhlmhmsqh1f foreign key (product_id) references farm;
alter table farm_products add constraint FKjs331u3mylp1v1u9me22akwi6 foreign key (farm_id) references product;
alter table price add constraint FKjs4y5f5b497nfv6jgij9499jq foreign key (farm_id) references farm;
alter table price add constraint FKk4mbgqf5yru5ib5b6w5l6ukkj foreign key (product_id) references product;
alter table regular_business_hours add constraint FKgjlbb0i5g9to62md32ypdtwsi foreign key (regular_business_hours_id) references site;
alter table site add constraint FKdq2h4ip5drf40qnic8w7x3nhi foreign key (farm) references farm;
alter table special_event_business_hours add constraint FKi7lcmdigm9qlkipbbkgf2362y foreign key (special_event_business_hours_id) references site;

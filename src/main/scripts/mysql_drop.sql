alter table category_products drop foreign key FKqwansr9rihghep5rgvu2yctl7;
alter table category_products drop foreign key FKd9j7gma323lpd7p857qrs4j56;
alter table farm drop foreign key FKfyftqvd15pl0i8ctcq4wexe1c;
alter table farm_farmers drop foreign key FKms6s7nama94qtsxe5u27akaor;
alter table farm_farmers drop foreign key FK2lqy730obvr7luk9y3p4141xt;
alter table farm_products drop foreign key FK238x77u1dgxcy2dhlmhmsqh1f;
alter table farm_products drop foreign key FKjs331u3mylp1v1u9me22akwi6;
alter table price drop foreign key FKjs4y5f5b497nfv6jgij9499jq;
alter table price drop foreign key FKk4mbgqf5yru5ib5b6w5l6ukkj;
alter table regular_business_hours drop foreign key FKgjlbb0i5g9to62md32ypdtwsi;
alter table site drop foreign key FKgm9mv51mey17nt9fnesuow5qo;
alter table special_event_business_hours drop foreign key FKi7lcmdigm9qlkipbbkgf2362y;
drop table if exists category;
drop table if exists category_products;
drop table if exists farm;
drop table if exists farm_farmers;
drop table if exists farm_products;
drop table if exists farmer;
drop table if exists price;
drop table if exists product;
drop table if exists regular_business_hours;
drop table if exists site;
drop table if exists special_event_business_hours;

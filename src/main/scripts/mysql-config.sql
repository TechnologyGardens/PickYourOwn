# docker run --name vital-mysql -e MYSQL_ALLOW_EMPTY_PASSWORD=yes -v d:\DB\MySQL:/var/lib/mysql -p 3306:3306 -d mysql
#docker exec -it vital-mysql bash
#mysql

#Create Databases
CREATE DATABASE pyo_dev;
CREATE DATABASE pyo_prod;

#Create database service accounts
CREATE USER 'pyo_dev_user'@'localhost' IDENTIFIED BY 'PickYourOwn2018';
CREATE USER 'pyo_dev_user'@'%' IDENTIFIED BY 'PickYourOwn2018';
CREATE USER 'pyo_prod_user'@'localhost' IDENTIFIED BY 'PickYourOwn2018';
CREATE USER 'pyo_prod_user'@'%' IDENTIFIED BY 'PickYourOwn2018';

#Database grants
GRANT SELECT,INSERT,DELETE,UPDATE ON pyo_dev.* TO 'pyo_dev_user'@'localhost';
GRANT SELECT,INSERT,DELETE,UPDATE ON pyo_dev.* TO 'pyo_dev_user'@'%';

GRANT SELECT,INSERT,DELETE,UPDATE ON pyo_prod.* to 'pyo_prod_user'@'localhost';
GRANT SELECT,INSERT,DELETE,UPDATE ON pyo_prod.* to 'pyo_prod_user'@'%';

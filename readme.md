# Pick Your Own Service

[![CircleCI](https://circleci.com/gh/TechnologyGardens/PickYourOwn.svg?style=svg)](https://circleci.com/gh/TechnologyGardens/PickYourOwn)

Pick Your Own app helps gardeners to list their produce and invite
interested customers to visit the farm and pick their product of choice.
The three main classes are:
 - Farm: Provides information about location of the property, directions,
 working hours, links to social media. Current version allows one site
 per farm.
 - Farmer: Name and contact information for the owner/employee of a farm
 - Product: Name and description of the product
Farms can be associated with many farmers and products with many to many
relationships

Pick Your Own currently supports three persistence providers.
- H2 memory database (JDBC URL: jdbc:h2:mem:testdb) (use default profile)
- MySQL (e.g jdbc:mysql://localhost:3306/pyo_dev) (use dev-mysql or prod_mysql profiles)
- PostgreSQL (e.g. jdbc:postgresql://localhost:5432/pyo_dev) (use dev-postgres or prod-postgres profile)
and some test data are defined in BootstrapData class.

Database passwords and usernames are read from environmental variables
 DEV_DB_USERNAME=pyo_dev_user;DEV_DB_PASSWORD=PickYourOwn2018;PROD_DB_USERNAME=pyo_prod_user;PROD_DB_PASSWORD=PickYourOwn2018
and profiles can be specified using VM option "-Dspring.profiles.active=profile_name" e.g.:
-Dspring.profiles.active=dev-postgres

Once application is runnig go to local host to get started:
http://localhost:8080
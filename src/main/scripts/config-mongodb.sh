docker run --name vital-mongodb -v d:\DB\MongoDB:/var/lib/mongodb -p 27017:27017 -d bitnami/mongodb

use admin
db.createUser(
  {
    user: "myUserAdmin",
    pwd: "root",
    roles: [ { role: "userAdminAnyDatabase", db: "admin" } ]
  }
)

use pyo_dev
db.createUser(
  {
    user: "pyo_dev_user",
    pwd: "PickYourOwn2018",
    roles: [ { role: "readWrite", db: "pyo_dev" },
             { role: "read", db: "reporting" } ]
  }
)

use pyo_prod
db.createUser(
  {
    user: "pyo_prod_user",
    pwd: "PickYourOwn2018",
    roles: [ { role: "readWrite", db: "pyo_prod" },
             { role: "read", db: "reporting" } ]
  }
)
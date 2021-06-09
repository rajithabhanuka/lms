./mongod --dbpath ~/Mongodata/

./mongo

use lms_db;

db.createUser({user : "root", pwd : "12345", roles : [{role : "readWrite", db : "lms_db"}]});

Exit

./mongod --auth --dbpath ~/Mongodata/

./mongo localhost/lms_db -u 'root' -p '12345';

db.createCollection("User")

db.User.insert({"email" : "rajithabhanuka1@gmail.com", "password": "123", "role": "TEACHER", "regdate" : "2020-06-05"});

db.User.insert({"email" : "rajithabhanuka3@gmail.com", "password": "admin", "role": "TEACHER", "regdate" : "2020-06-05"});

CREATE DATABASE IF NOT EXISTS credit_me;
CREATE USER 'spring'@'%' IDENTIFIED BY 'spring';
GRANT ALL ON credit_me.* TO 'spring'@'%';
FLUSH PRIVILEGES;

CREATE DATABASE IF NOT EXISTS credit_me_test;
GRANT ALL ON credit_me_test.* TO 'spring'@'%';
FLUSH PRIVILEGES;

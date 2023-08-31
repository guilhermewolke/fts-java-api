CREATE DATABASE IF NOT EXISTS fts_bookshelf;

--CREATE TABLE IF NOT EXISTS `fts_bookshelf`.`author` (
--    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
--    name VARCHAR(40) NOT NULL,
--    nationality VARCHAR(40) NOT NULL
--)ENGINE=InnoDB;
--
--CREATE TABLE IF NOT EXISTS `fts_bookshelf`.`book` (
--    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
--    author_id INT NOT NULL,
--    title VARCHAR(100) NOT NULL
--)ENGINE=InnoDB;
--
CREATE USER 'root'@'localhost' IDENTIFIED BY 'root';
GRANT ALL ON `fts_bookshelf`.* TO 'root'@'%';


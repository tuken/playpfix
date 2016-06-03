# --- !Ups
CREATE TABLE `domain` (`id` INTEGER NOT NULL AUTO_INCREMENT, `domain` VARCHAR(255) NOT NULL DEFAULT '', `description` VARCHAR(255) NOT NULL DEFAULT '', `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP, `updated_at` DATETIME ON UPDATE CURRENT_TIMESTAMP, `active` TINYINT(1) NOT NULL DEFAULT 1, PRIMARY KEY (`id`)) ENGINE=InnoDB DEFAULT CHARACTER SET utf8;
INSERT INTO `domain` (`domain`, `description`) VALUES ('saver.jp', 'セーバーのメインドメイン');
CREATE TABLE `alias` (`id` INTEGER NOT NULL AUTO_INCREMENT, `domain_id` INTEGER NOT NULL, `address` VARCHAR(255) NOT NULL DEFAULT '', `goto` TEXT NOT NULL, `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP, `updated_at` DATETIME ON UPDATE CURRENT_TIMESTAMP, `active` TINYINT(1) NOT NULL DEFAULT 1, PRIMARY KEY (`id`), CONSTRAINT `alias_fk_domain` FOREIGN KEY (`domain_id`) REFERENCES `domain` (`id`) ON DELETE CASCADE ON UPDATE CASCADE);
INSERT INTO alias (`domain_id`, `address`, `goto`) VALUES (1, 't.ikawa@saver.jp', 't.ikawa@saver.jp');

# --- !Downs
DROP TABLE `alias`;
DROP TABLE `domain`;

ALTER TABLE `carshopping`.`t_activity` ADD COLUMN `hasBuyGroupPerson` INT NULL DEFAULT 0  AFTER `tuanPrice` ;

ALTER TABLE `carshopping`.`t_product` ADD COLUMN `giftID` VARCHAR(45) NULL  AFTER `isTimePromotion` ;

CREATE  TABLE `carshopping`.`t_gift` (

  `id` INT NOT NULL AUTO_INCREMENT ,

  `giftName` VARCHAR(100) NOT NULL ,

  `giftPrice` DECIMAL(9,2) NULL ,

  `createAccount` VARCHAR(45) NULL ,

  `createtime` DATETIME NULL ,

  `updateAccount` VARCHAR(45) NULL ,

  `updatetime` DATETIME NULL ,

  `status` VARCHAR(5) NULL DEFAULT 'down' ,

  PRIMARY KEY (`id`) )

ENGINE = InnoDB;

ALTER TABLE `carshopping`.`t_gift` ADD COLUMN `picture` VARCHAR(100) NULL  AFTER `status` ;


CREATE  TABLE `carshopping`.`t_hotQuery` (

  `id` INT NOT NULL AUTO_INCREMENT ,

  `key` VARCHAR(45) NOT NULL ,

  `url` VARCHAR(100) NOT NULL ,

  PRIMARY KEY (`id`) )

ENGINE = InnoDB;

ALTER TABLE `carshopping`.`t_hotquery` CHANGE COLUMN `key` `key1` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL  ;

ALTER TABLE `carshopping`.`t_orderdetail` ADD COLUMN `giftID` VARCHAR(45) NULL  AFTER `specInfo` ;




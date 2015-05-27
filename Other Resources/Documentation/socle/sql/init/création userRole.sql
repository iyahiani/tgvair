CREATE TABLE `socle_role` (
  `idRole` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Roles des utilisateurs',
  `technicalNameRole` varchar(20) NOT NULL COMMENT 'Nom technique',
  `labelRole` varchar(100) NOT NULL,
  PRIMARY KEY (`idRole`),
  KEY `indUnique_technicalNameRole` (`technicalNameRole`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

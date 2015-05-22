CREATE TABLE `socle_ref_directory` (
  `idRefDirectory` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `technicalNameRefDirectory` varchar(30) NOT NULL,
  `pathRefDirectory` varchar(100) NOT NULL,
  `commentsRefDirectory` text CHARACTER SET utf8mb4,
  PRIMARY KEY (`idRefDirectory`),
  UNIQUE KEY `indUnique_RefDirectoryTechnicalName` (`technicalNameRefDirectory`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
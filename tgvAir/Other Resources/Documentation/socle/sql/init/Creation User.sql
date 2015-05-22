#################################################
#				Table Socle_user				#
#################################################

CREATE TABLE `socle_user` (
`idUser` int(11) unsigned NOT NULL AUTO_INCREMENT,
`nomUser` varchar(35) NOT NULL DEFAULT '',
`prenomUser` varchar(35) NOT NULL DEFAULT '',
`loginUser` varchar(50) NOT NULL DEFAULT '',
`passwordUser` varchar(56) NOT NULL DEFAULT '',
`mailUser` varchar(100),
`cpUser` varchar (20),
`telephonePro1User` varchar(20),
`telephonePro2User` varchar(20),
`telephonePortable1User` varchar(20),
`fax1User` varchar(20),
`adresseNumeroRueUser` varchar(10),
`adresseNomRueUser` varchar(75),
`adresseComplement1User` varchar(50),
`adresseComplement2User` varchar(50),
`adresseCodepostalUser` varchar(10),
`adresseVilleUser` varchar(30),
`attribut1User` varchar(35),
`attribut2User` varchar(35),
`attribut3User` varchar(35),
`attribut4User` varchar(35),
`attribut5User` varchar(35),
`idUserCreateUser` int(11) unsigned NOT NULL,
`DateCreateUser` datetime NOT NULL,
`idUserUpdateUser` int(11) unsigned NOT NULL,
`DateUpdateUser` datetime NOT NULL,
`tomcatRoleUser` varchar(30),
`commentaireUtilisateurUser` varchar(75),
`robotUser` varchar(35),
`dateLastUpdateUtilisateurUser` datetime NOT NULL,

  PRIMARY KEY (`idUser`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

ALTER TABLE `socle_user` ADD CONSTRAINT `socle_user_idUserCreate_FK` FOREIGN KEY (`idUserCreateUser`) REFERENCES `socle_user` (`idUser`) ON DELETE RESTRICT ON UPDATE RESTRICT;

INSERT INTO `socle_user` VALUE ('1','havok','havok','havok','havok','','','','','','','','','','','','','','','','','','1',NOW(),'1',NOW(),'','','',NOW())
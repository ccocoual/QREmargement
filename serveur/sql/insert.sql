INSERT INTO `qre`.`classe`(`id`,`libelle`) VALUES 
(1, 'm2miage'),
(2, 'm1miage');

INSERT INTO `qre`.`groupe`(`id`, `libelle`, `classe_id`) VALUES 
(1,'Gr1',1),
(2,'Gr2',1),
(3,'Gr1',2);

INSERT INTO `qre`.`etudiant`(`id`, `nom`, `prenom`, `email`, `num_etu`, `groupe_id`, `classe_id`) VALUES
(1,'Nom1','Prenom1','nom1.prenom1@etudiant.fr','15000001',1,1),
(2,'Nom2','Prenom2','nom2.prenom2@etudiant.fr','15000002',1,1),
(3,'Nom3','Prenom3','nom3.prenom3@etudiant.fr','15000003',2,1);


INSERT INTO `qre`.`professeur`(`id`, `nom`, `prenom`, `email`, `password`) VALUES 
(1, 'Miklos','Zoltan','miklos.zoltan@prof.fr','5f4dcc3b5aa765d61d8327deb882cf99'),
(2, 'Nom1','Prenom1','nom1.prenom1@prof.fr','5f4dcc3b5aa765d61d8327deb882cf99'),
(3, 'Nom2','Prenom2','nom2.prenom2@prof.fr','5f4dcc3b5aa765d61d8327deb882cf99');

INSERT INTO `qre`.`professeur_has_classe`(`professeur_id`, `classe_id`) VALUES 
(1,1),
(1,2),
(2,1);

INSERT INTO `qre`.`authentication`(`id`, `token`, `date_expiration`, `professeur_id`) VALUES 
(1,'fzekfnazeklfne','2030-01-20 11:22:33', 1),
(2,'azkelnflkzecnf','2000-01-20 11:22:33', 2);

INSERT INTO `qre`.`matiere`(`id`, `libelle`) VALUES 
(1, 'BDD'),
(2, 'PRM');

INSERT INTO `qre`.`emargement`(`id`, `date`, `url_generated`, `type`, `matiere_id`, `professeur_id`) VALUES 
(1, '2016-01-20 11:22:33','ezlkffsjekf','CM', 1, 1),
(2, '2016-01-21 11:22:33','klzlkefnz','TD', 2, 1);

INSERT INTO `qre`.`emargement_has_groupe`(`groupe_id`, `classe_id`, `emargement_id`, `matiere_id`, `professeur_id`) VALUES 
(1, 1, 1, 1, 1),
(2, 1, 2, 2, 1);

INSERT INTO `qre`.`signature`(`emargement_id`, `etudiant_id`, `signee`, `date`) VALUES 
(1, 1, 0, '0000-00-00 00:00:00'),
(1, 2, 1, '2016-01-22 08:03:00'),
(2, 3, 0, '0000-00-00 00:00:00');


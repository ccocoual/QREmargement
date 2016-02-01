CREATE TRIGGER `insert_after_emargement_has_groupe` AFTER INSERT ON `emargement_has_groupe`
 FOR EACH ROW BEGIN
DECLARE done INT DEFAULT 0;
DECLARE signee INT DEFAULT 0;
DECLARE etudiant_id INT;

DECLARE cur1 CURSOR FOR SELECT etudiant.id FROM etudiant WHERE groupe_id = NEW.groupe_id;
DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = 1;

  OPEN cur1;

  REPEAT
    FETCH cur1 INTO etudiant_id;
    
    IF NOT done THEN
      INSERT INTO signature (emargement_id, etudiant_id, signee) VALUES (NEW.emargement_id, etudiant_id, signee);
    END IF;
  UNTIL done END REPEAT;

  CLOSE cur1;
END
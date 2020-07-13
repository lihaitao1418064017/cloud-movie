ALTER TABLE `data_dict`
  CHANGE creator_id creator_user VARCHAR(255) NULL DEFAULT NULL;
ALTER TABLE `data_dict`
  CHANGE update_id update_user VARCHAR(255) NULL DEFAULT NULL;

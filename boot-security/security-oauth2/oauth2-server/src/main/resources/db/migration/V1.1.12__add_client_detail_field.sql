ALTER TABLE oauth_client_details
  ADD COLUMN status INT(10) DEFAULT NULL;

ALTER TABLE oauth_client_details
  ADD COLUMN creator_user VARCHAR(255) DEFAULT NULL;
ALTER TABLE oauth_client_details
  ADD COLUMN create_time BIGINT(20) DEFAULT NULL;

ALTER TABLE oauth_client_details
  ADD COLUMN update_user VARCHAR(255) DEFAULT NULL;

ALTER TABLE oauth_client_details
  ADD COLUMN update_time BIGINT(20) DEFAULT NULL;
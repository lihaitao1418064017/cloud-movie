ALTER TABLE `oauth_client_details`
  DROP PRIMARY KEY;

ALTER TABLE oauth_client_details
  ADD COLUMN id BIGINT(11) NOT NULL;

ALTER TABLE oauth_client_details
  ADD PRIMARY KEY (id);

ALTER TABLE oauth_client_details
  CHANGE id id BIGINT(11) NOT NULL AUTO_INCREMENT;
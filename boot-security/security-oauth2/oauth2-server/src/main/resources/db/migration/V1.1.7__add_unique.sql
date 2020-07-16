ALTER TABLE user_role
  ADD UNIQUE KEY `user_role` (`user_id`, `role_id`);
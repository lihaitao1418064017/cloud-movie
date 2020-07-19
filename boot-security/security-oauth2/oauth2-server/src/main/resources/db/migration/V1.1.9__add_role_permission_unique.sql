ALTER TABLE role_permission
  ADD UNIQUE KEY `permission_role` (`permission_id`, `role_id`);
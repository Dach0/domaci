INSERT INTO user_roles(user_id, role_id) 
VALUES 
(
	(SELECT id FROM users WHERE last_name = 'Brajovic'), 
	(SELECT id FROM roles WHERE name = 'ROLE_USER')
);

INSERT INTO user_roles(user_id, role_id) 
VALUES 
(
	(SELECT id FROM users WHERE last_name = 'Camaj'), 
	(SELECT id FROM roles WHERE name = 'ROLE_USER')
);
INSERT INTO user_roles(user_id, role_id) 
VALUES 
(
	(SELECT id FROM users WHERE last_name = 'Femic'), 
	(SELECT id FROM roles WHERE name = 'ROLE_USER')
);
INSERT INTO user_roles(user_id, role_id) 
VALUES 
(
	(SELECT id FROM users WHERE last_name = 'Lazarevic'), 
	(SELECT id FROM roles WHERE name = 'ROLE_USER')
);
INSERT INTO user_roles(user_id, role_id) 
VALUES 
(
	(SELECT id FROM users WHERE last_name = 'Mickovic'), 
	(SELECT id FROM roles WHERE name = 'ROLE_USER')
);
INSERT INTO user_roles(user_id, role_id) 
VALUES 
(
	(SELECT id FROM users WHERE last_name = 'Orbovic'), 
	(SELECT id FROM roles WHERE name = 'ROLE_USER')
);
INSERT INTO user_roles(user_id, role_id) 
VALUES 
(
	(SELECT id FROM users WHERE last_name = 'Djurisic'), 
	(SELECT id FROM roles WHERE name = 'ROLE_USER')
);
INSERT INTO user_roles(user_id, role_id) 
VALUES 
(
	(SELECT id FROM users WHERE last_name = 'Radulovic'), 
	(SELECT id FROM roles WHERE name = 'ROLE_USER')
);
INSERT INTO user_roles(user_id, role_id) 
VALUES 
(
	(SELECT id FROM users WHERE last_name = 'Krivacevic'), 
	(SELECT id FROM roles WHERE name = 'ROLE_USER')
);
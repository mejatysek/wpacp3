INSERT INTO `role` (`id`, `name`) VALUES
(1,	'admin'),
(2,	'corrector'),
(3,	'author');


INSERT INTO `users` (`id`, `email`, `name`, `password`, `phone`, `salt`, `surname`, `role_id`) VALUES
(1,	'admin@admin.cz',	'admin',	'dfbf133a4bf4d46bfad98d7b94725452e2889fd5',	NULL,	'd32ac175fdd4da9c677068bb6e055fca292239e8',	'test', 1);


-- 2013-01-21 04:14:21

INSERT INTO `state` (`id`, `name`) VALUES
(1,	'waiting'),
(2,	'progress'),
(3,	'complete');

-- 2013-01-21 13:42:39

INSERT INTO `correctorsgroup` (`id`, `name`) VALUES
(1,	'Obecná'),
(2,	'IT'),
(3,	'Přirodovědná');

-- 2013-01-21 13:49:25
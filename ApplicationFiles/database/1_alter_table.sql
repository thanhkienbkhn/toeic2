ALTER TABLE public.users ADD COLUMN role_id BIGINT;

ALTER TABLE public.users ADD CONSTRAINT fk_user_role FOREIGN KEY (role_id) REFERENCES public.role (role_id) ON UPDATE RESTRICT ON DELETE RESTRICT;
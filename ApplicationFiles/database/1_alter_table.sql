ALTER TABLE public.user ADD COLUMN role_id BIGINT;

ALTER TABLE public.user ADD CONSTRAINT fk_user_role FOREIGN KEY (role_id) REFERENCES public.role (role_id) ON UPDATE RESTRICT ON DELETE RESTRICT;
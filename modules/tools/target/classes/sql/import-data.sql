--media
ALTER TABLE archive.media_app_watch_record ADD COLUMN new_id bigint;

ALTER TABLE archive.media_comment ADD COLUMN new_id integer;

ALTER TABLE archive.media ADD COLUMN new_id integer;

ALTER TABLE archive.media_praise ADD COLUMN new_id integer;

ALTER TABLE archive.media_resource ADD COLUMN new_id integer;

ALTER TABLE archive.media_watch_analysis ADD COLUMN new_id integer;

ALTER TABLE archive.media_watch_record ADD COLUMN new_id bigint;


--lib 
ALTER TABLE lib.lib_item ADD COLUMN new_id bigint;

ALTER TABLE lib.node_resource ADD COLUMN new_id bigint;

ALTER TABLE lib.node_resource ADD COLUMN id bigserial;

ALTER TABLE lib.path_node ADD COLUMN new_id bigint;


ALTER TABLE lib.path_node_v2 ADD COLUMN new_id integer;

ALTER TABLE lib.study_path ADD COLUMN new_id integer;

--pgm

ALTER TABLE public.knowledge ADD COLUMN new_id integer;

ALTER TABLE public.subject ADD COLUMN new_id integer;

--um

ALTER TABLE um.user_ ADD COLUMN new_id integer;






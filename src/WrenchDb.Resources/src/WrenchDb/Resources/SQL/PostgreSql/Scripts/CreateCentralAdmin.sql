---------------------------------------------

-- Table wdb_application

CREATE TABLE "wdb_application"(
 "application_id" BigSerial NOT NULL,
 "application_name" Character varying(200) NOT NULL,
 "application_hostname" Bigint NOT NULL
)
WITH (OIDS=FALSE)
;

-- Add keys for table wdb_application

ALTER TABLE "wdb_application" ADD CONSTRAINT "application_pk" PRIMARY KEY ("application_id")
;

ALTER TABLE "wdb_application" ADD CONSTRAINT "application_name" UNIQUE ("application_name")
;

ALTER TABLE "wdb_application" ADD CONSTRAINT "application_hostname" UNIQUE ("application_hostname")
;

-- Table wdb_entity

CREATE TABLE "wdb_entity"(
 "entity_id" BigSerial NOT NULL,
 "application_id" Bigint NOT NULL,
 "entity_name" Character varying(200) NOT NULL,
 "entity_desc" Character varying(1000),
 "is_managed" Bit(1)
)
WITH (OIDS=FALSE)
;
COMMENT ON COLUMN "wdb_entity"."is_managed" IS 'managed by scaffolding system'
;

-- Add keys for table wdb_entity

ALTER TABLE "wdb_entity" ADD CONSTRAINT "wdb_entityPK" PRIMARY KEY ("entity_id")
;

ALTER TABLE "wdb_entity" ADD CONSTRAINT "wdb_entityUK" UNIQUE ("entity_name","application_id")
;

-- Table wbd_property

CREATE TABLE "wbd_property"(
 "property_id" BigSerial NOT NULL,
 "entity_id" Bigint NOT NULL,
 "property_type" Bigint,
 "property_name" Bigint NOT NULL,
 "property_label" Character varying(200),
 "is_inlist" Bigint,
 "is_innew" Bit(1),
 "is_inedit" Bit(1),
 "is_indetail" Bigint,
 "config_data" Character varying(8000),
 "width" Integer
)
WITH (OIDS=FALSE)
;
COMMENT ON COLUMN "wbd_property"."is_inlist" IS 'show in list'
;
COMMENT ON COLUMN "wbd_property"."is_innew" IS 'show in new form'
;
COMMENT ON COLUMN "wbd_property"."is_inedit" IS 'is in edit form'
;
COMMENT ON COLUMN "wbd_property"."is_indetail" IS 'show in detail form'
;
COMMENT ON COLUMN "wbd_property"."config_data" IS 'contains JSON configuration:data'
;

-- Add keys for table wbd_property

ALTER TABLE "wbd_property" ADD CONSTRAINT "property_pk" PRIMARY KEY ("property_id")
;

ALTER TABLE "wbd_property" ADD CONSTRAINT "property_id" UNIQUE ("property_id")
;

ALTER TABLE "wbd_property" ADD CONSTRAINT "property_name_entity_uk" UNIQUE ("property_name","entity_id")
;

-- Table wdb_property_type

CREATE TABLE "wdb_property_type"(
 "property_type" BigSerial NOT NULL,
 "property_type_name" Bit varying(200),
 "property_type_desc" Bit varying(1000)
)
WITH (OIDS=FALSE)
;

-- Add keys for table wdb_property_type

ALTER TABLE "wdb_property_type" ADD CONSTRAINT "property_type_pk" PRIMARY KEY ("property_type")
;

ALTER TABLE "wdb_property_type" ADD CONSTRAINT "property_type" UNIQUE ("property_type")
;

-- Table wdb_sitemap

CREATE TABLE "wdb_sitemap"(
 "sitemap_id" BigSerial NOT NULL,
 "application_id" Bigint NOT NULL,
 "parent_sitemap_id" Bigint,
 "sitemap_name" Bit varying(200) NOT NULL,
 "sitemap_label" Bigint NOT NULL,
 "sitemap_url" Character varying(1000) NOT NULL,
 "config_data" Character varying(2000)
)
WITH (OIDS=FALSE)
;

-- Add keys for table wdb_sitemap

ALTER TABLE "wdb_sitemap" ADD CONSTRAINT "sitemap_pk" PRIMARY KEY ("sitemap_id")
;

ALTER TABLE "wdb_sitemap" ADD CONSTRAINT "sitemap_id" UNIQUE ("sitemap_id")
;

ALTER TABLE "wdb_sitemap" ADD CONSTRAINT "sitemap_uk" UNIQUE ("sitemap_name","application_id")
;

-- Table wdb_settings

CREATE TABLE "wdb_settings"(
 "setting_id" BigSerial NOT NULL,
 "application_id" Bigint NOT NULL,
 "setting_category" Bit varying(200) NOT NULL,
 "setting_key" Bit varying(200) NOT NULL,
 "setting_value" Character varying(2000)
)
WITH (OIDS=FALSE)
;

-- Add keys for table wdb_settings

ALTER TABLE "wdb_settings" ADD CONSTRAINT "settings_pk" PRIMARY KEY ("setting_id")
;

ALTER TABLE "wdb_settings" ADD CONSTRAINT "setting_id" UNIQUE ("setting_id")
;

ALTER TABLE "wdb_settings" ADD CONSTRAINT "settings_UK" UNIQUE ("setting_category","application_id","setting_key")
;

-- Table wdb_changescript

CREATE TABLE "wdb_changescript"(
 "changescript_id" BigSerial NOT NULL,
 "application_id" Bigint,
 "changescript_desc" Character varying(200),
 "changescript_sql" Bit varying(2000) NOT NULL,
 "is_deployed" Boolean DEFAULT false NOT NULL
)
WITH (OIDS=FALSE)
;

-- Add keys for table wdb_changescript

ALTER TABLE "wdb_changescript" ADD CONSTRAINT "wdb_changescriptPK" PRIMARY KEY ("changescript_id")
;

ALTER TABLE "wdb_changescript" ADD CONSTRAINT "changescript_id" UNIQUE ("changescript_id")
;

-- Create relationships section ------------------------------------------------- 

ALTER TABLE "wdb_entity" ADD CONSTRAINT "Relationship8" FOREIGN KEY ("application_id") REFERENCES "wdb_application" ("application_id") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "wbd_property" ADD CONSTRAINT "Relationship9" FOREIGN KEY ("entity_id") REFERENCES "wdb_entity" ("entity_id") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "wbd_property" ADD CONSTRAINT "Relationship10" FOREIGN KEY ("property_type") REFERENCES "wdb_property_type" ("property_type") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "wdb_sitemap" ADD CONSTRAINT "Relationship11" FOREIGN KEY ("application_id") REFERENCES "wdb_application" ("application_id") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "wdb_sitemap" ADD CONSTRAINT "Relationship12" FOREIGN KEY ("parent_sitemap_id") REFERENCES "wdb_sitemap" ("sitemap_id") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "wdb_settings" ADD CONSTRAINT "Relationship13" FOREIGN KEY ("application_id") REFERENCES "wdb_application" ("application_id") ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE "wdb_changescript" ADD CONSTRAINT "Relationship14" FOREIGN KEY ("application_id") REFERENCES "wdb_application" ("application_id") ON DELETE NO ACTION ON UPDATE NO ACTION
;






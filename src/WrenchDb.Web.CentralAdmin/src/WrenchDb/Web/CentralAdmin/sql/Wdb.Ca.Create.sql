--
-- PostgreSQL database dump
--

-- Dumped from database version 9.2.4
-- Dumped by pg_dump version 9.2.4
-- Started on 2013-12-21 13:10:10

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 188 (class 3079 OID 11727)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2043 (class 0 OID 0)
-- Dependencies: 188
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 173 (class 1259 OID 49352)
-- Name: wdb_property; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE wdb_property (
    property_id bigint NOT NULL,
    entity_id bigint NOT NULL,
    property_type bigint,
    property_label character varying(200),
    config_data character varying(8000),
    width integer,
    is_innew boolean,
    is_inedit boolean,
    is_inview boolean,
    is_inlist boolean,
    property_name character varying(300) NOT NULL
);


ALTER TABLE public.wdb_property OWNER TO postgres;

--
-- TOC entry 2044 (class 0 OID 0)
-- Dependencies: 173
-- Name: COLUMN wdb_property.config_data; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN wdb_property.config_data IS 'contains JSON configuration:data';


--
-- TOC entry 172 (class 1259 OID 49350)
-- Name: wbd_property_property_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE wbd_property_property_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.wbd_property_property_id_seq OWNER TO postgres;

--
-- TOC entry 2045 (class 0 OID 0)
-- Dependencies: 172
-- Name: wbd_property_property_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE wbd_property_property_id_seq OWNED BY wdb_property.property_id;


--
-- TOC entry 169 (class 1259 OID 49327)
-- Name: wdb_application; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE wdb_application (
    application_id bigint NOT NULL,
    application_name character varying(200) NOT NULL,
    application_hostname character varying(200),
    application_server_uri character varying(1000),
    servertype_id bigint NOT NULL,
    server_user character varying(200),
    server_password character varying(200),
    database_jdbc character varying(400),
    database_username character varying(300),
    database_password character varying(300),
    databasetype_id bigint NOT NULL
);


ALTER TABLE public.wdb_application OWNER TO postgres;

--
-- TOC entry 168 (class 1259 OID 49325)
-- Name: wdb_application_application_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE wdb_application_application_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.wdb_application_application_id_seq OWNER TO postgres;

--
-- TOC entry 2046 (class 0 OID 0)
-- Dependencies: 168
-- Name: wdb_application_application_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE wdb_application_application_id_seq OWNED BY wdb_application.application_id;


--
-- TOC entry 181 (class 1259 OID 49407)
-- Name: wdb_changescript; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE wdb_changescript (
    changescript_id bigint NOT NULL,
    application_id bigint,
    changescript_desc character varying(200),
    is_deployed boolean DEFAULT false NOT NULL,
    changescript_sql character varying(8000),
    script_source integer DEFAULT 1
);


ALTER TABLE public.wdb_changescript OWNER TO postgres;

--
-- TOC entry 2047 (class 0 OID 0)
-- Dependencies: 181
-- Name: COLUMN wdb_changescript.script_source; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN wdb_changescript.script_source IS '1=centraladmin
2=user';


--
-- TOC entry 180 (class 1259 OID 49405)
-- Name: wdb_changescript_changescript_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE wdb_changescript_changescript_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.wdb_changescript_changescript_id_seq OWNER TO postgres;

--
-- TOC entry 2048 (class 0 OID 0)
-- Dependencies: 180
-- Name: wdb_changescript_changescript_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE wdb_changescript_changescript_id_seq OWNED BY wdb_changescript.changescript_id;


--
-- TOC entry 185 (class 1259 OID 81958)
-- Name: wdb_databasetype; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE wdb_databasetype (
    databasetype_id bigint NOT NULL,
    databasetype_name character varying(300),
    databasetype_deployer character varying(500)
);


ALTER TABLE public.wdb_databasetype OWNER TO postgres;

--
-- TOC entry 184 (class 1259 OID 81956)
-- Name: wdb_databasetype_databasetype_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE wdb_databasetype_databasetype_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.wdb_databasetype_databasetype_id_seq OWNER TO postgres;

--
-- TOC entry 2049 (class 0 OID 0)
-- Dependencies: 184
-- Name: wdb_databasetype_databasetype_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE wdb_databasetype_databasetype_id_seq OWNED BY wdb_databasetype.databasetype_id;


--
-- TOC entry 171 (class 1259 OID 49339)
-- Name: wdb_entity; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE wdb_entity (
    entity_id bigint NOT NULL,
    application_id bigint NOT NULL,
    entity_name character varying(200) NOT NULL,
    entity_desc character varying(1000),
    is_managed boolean
);


ALTER TABLE public.wdb_entity OWNER TO postgres;

--
-- TOC entry 170 (class 1259 OID 49337)
-- Name: wdb_entity_entity_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE wdb_entity_entity_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.wdb_entity_entity_id_seq OWNER TO postgres;

--
-- TOC entry 2050 (class 0 OID 0)
-- Dependencies: 170
-- Name: wdb_entity_entity_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE wdb_entity_entity_id_seq OWNED BY wdb_entity.entity_id;


--
-- TOC entry 175 (class 1259 OID 49367)
-- Name: wdb_property_type; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE wdb_property_type (
    property_type bigint NOT NULL,
    property_type_name character varying,
    property_type_desc character varying,
    property_type_sql character varying(300)
);


ALTER TABLE public.wdb_property_type OWNER TO postgres;

--
-- TOC entry 174 (class 1259 OID 49365)
-- Name: wdb_property_type_property_type_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE wdb_property_type_property_type_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.wdb_property_type_property_type_seq OWNER TO postgres;

--
-- TOC entry 2051 (class 0 OID 0)
-- Dependencies: 174
-- Name: wdb_property_type_property_type_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE wdb_property_type_property_type_seq OWNED BY wdb_property_type.property_type;


--
-- TOC entry 187 (class 1259 OID 81989)
-- Name: wdb_release; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE wdb_release (
    wdb_release_id bigint NOT NULL,
    release_version bigint NOT NULL,
    release_fullversion character varying(100) NOT NULL,
    release_memocode character varying(300) NOT NULL,
    database_version bigint NOT NULL,
    released_on date DEFAULT now() NOT NULL,
    application_id bigint
);


ALTER TABLE public.wdb_release OWNER TO postgres;

--
-- TOC entry 2052 (class 0 OID 0)
-- Dependencies: 187
-- Name: COLUMN wdb_release.database_version; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN wdb_release.database_version IS 'max changescript value for application at deploy';


--
-- TOC entry 186 (class 1259 OID 81987)
-- Name: wdb_release_wdb_release_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE wdb_release_wdb_release_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.wdb_release_wdb_release_id_seq OWNER TO postgres;

--
-- TOC entry 2053 (class 0 OID 0)
-- Dependencies: 186
-- Name: wdb_release_wdb_release_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE wdb_release_wdb_release_id_seq OWNED BY wdb_release.wdb_release_id;


--
-- TOC entry 183 (class 1259 OID 81937)
-- Name: wdb_servertype; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE wdb_servertype (
    servertype_id integer NOT NULL,
    servertype_name character varying(300),
    servertype_deployername character varying
);


ALTER TABLE public.wdb_servertype OWNER TO postgres;

--
-- TOC entry 182 (class 1259 OID 81935)
-- Name: wdb_servertype_servertype_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE wdb_servertype_servertype_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.wdb_servertype_servertype_id_seq OWNER TO postgres;

--
-- TOC entry 2054 (class 0 OID 0)
-- Dependencies: 182
-- Name: wdb_servertype_servertype_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE wdb_servertype_servertype_id_seq OWNED BY wdb_servertype.servertype_id;


--
-- TOC entry 179 (class 1259 OID 49392)
-- Name: wdb_settings; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE wdb_settings (
    setting_id bigint NOT NULL,
    application_id bigint NOT NULL,
    setting_value character varying(2000),
    setting_category character varying(244),
    setting_key character varying(240)
);


ALTER TABLE public.wdb_settings OWNER TO postgres;

--
-- TOC entry 178 (class 1259 OID 49390)
-- Name: wdb_settings_setting_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE wdb_settings_setting_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.wdb_settings_setting_id_seq OWNER TO postgres;

--
-- TOC entry 2055 (class 0 OID 0)
-- Dependencies: 178
-- Name: wdb_settings_setting_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE wdb_settings_setting_id_seq OWNED BY wdb_settings.setting_id;


--
-- TOC entry 177 (class 1259 OID 49377)
-- Name: wdb_sitemap; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE wdb_sitemap (
    sitemap_id bigint NOT NULL,
    application_id bigint NOT NULL,
    parent_sitemap_id bigint,
    sitemap_url character varying(1000) NOT NULL,
    config_data character varying(2000),
    sitemap_name character varying(234),
    sitemap_label character varying(2000)
);


ALTER TABLE public.wdb_sitemap OWNER TO postgres;

--
-- TOC entry 176 (class 1259 OID 49375)
-- Name: wdb_sitemap_sitemap_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE wdb_sitemap_sitemap_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.wdb_sitemap_sitemap_id_seq OWNER TO postgres;

--
-- TOC entry 2056 (class 0 OID 0)
-- Dependencies: 176
-- Name: wdb_sitemap_sitemap_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE wdb_sitemap_sitemap_id_seq OWNED BY wdb_sitemap.sitemap_id;


--
-- TOC entry 1979 (class 2604 OID 49330)
-- Name: application_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY wdb_application ALTER COLUMN application_id SET DEFAULT nextval('wdb_application_application_id_seq'::regclass);


--
-- TOC entry 1985 (class 2604 OID 49410)
-- Name: changescript_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY wdb_changescript ALTER COLUMN changescript_id SET DEFAULT nextval('wdb_changescript_changescript_id_seq'::regclass);


--
-- TOC entry 1989 (class 2604 OID 82008)
-- Name: databasetype_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY wdb_databasetype ALTER COLUMN databasetype_id SET DEFAULT nextval('wdb_databasetype_databasetype_id_seq'::regclass);


--
-- TOC entry 1980 (class 2604 OID 49342)
-- Name: entity_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY wdb_entity ALTER COLUMN entity_id SET DEFAULT nextval('wdb_entity_entity_id_seq'::regclass);


--
-- TOC entry 1981 (class 2604 OID 49355)
-- Name: property_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY wdb_property ALTER COLUMN property_id SET DEFAULT nextval('wbd_property_property_id_seq'::regclass);


--
-- TOC entry 1982 (class 2604 OID 49370)
-- Name: property_type; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY wdb_property_type ALTER COLUMN property_type SET DEFAULT nextval('wdb_property_type_property_type_seq'::regclass);


--
-- TOC entry 1991 (class 2604 OID 82001)
-- Name: wdb_release_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY wdb_release ALTER COLUMN wdb_release_id SET DEFAULT nextval('wdb_release_wdb_release_id_seq'::regclass);


--
-- TOC entry 1988 (class 2604 OID 81940)
-- Name: servertype_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY wdb_servertype ALTER COLUMN servertype_id SET DEFAULT nextval('wdb_servertype_servertype_id_seq'::regclass);


--
-- TOC entry 1984 (class 2604 OID 49395)
-- Name: setting_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY wdb_settings ALTER COLUMN setting_id SET DEFAULT nextval('wdb_settings_setting_id_seq'::regclass);


--
-- TOC entry 1983 (class 2604 OID 49380)
-- Name: sitemap_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY wdb_sitemap ALTER COLUMN sitemap_id SET DEFAULT nextval('wdb_sitemap_sitemap_id_seq'::regclass);


--
-- TOC entry 1993 (class 2606 OID 49334)
-- Name: application_name; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY wdb_application
    ADD CONSTRAINT application_name UNIQUE (application_name);


--
-- TOC entry 1995 (class 2606 OID 49332)
-- Name: application_pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY wdb_application
    ADD CONSTRAINT application_pk PRIMARY KEY (application_id);


--
-- TOC entry 2017 (class 2606 OID 49415)
-- Name: changescript_id; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY wdb_changescript
    ADD CONSTRAINT changescript_id UNIQUE (changescript_id);


--
-- TOC entry 2021 (class 2606 OID 81942)
-- Name: pk_SERVER_NAME; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY wdb_servertype
    ADD CONSTRAINT "pk_SERVER_NAME" PRIMARY KEY (servertype_id);


--
-- TOC entry 2023 (class 2606 OID 82010)
-- Name: pk_databasetype; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY wdb_databasetype
    ADD CONSTRAINT pk_databasetype PRIMARY KEY (databasetype_id);


--
-- TOC entry 2025 (class 2606 OID 82003)
-- Name: pk_release; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY wdb_release
    ADD CONSTRAINT pk_release PRIMARY KEY (wdb_release_id);


--
-- TOC entry 2001 (class 2606 OID 49362)
-- Name: property_id; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY wdb_property
    ADD CONSTRAINT property_id UNIQUE (property_id);


--
-- TOC entry 2003 (class 2606 OID 49360)
-- Name: property_pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY wdb_property
    ADD CONSTRAINT property_pk PRIMARY KEY (property_id);


--
-- TOC entry 2005 (class 2606 OID 49374)
-- Name: property_type; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY wdb_property_type
    ADD CONSTRAINT property_type UNIQUE (property_type);


--
-- TOC entry 2007 (class 2606 OID 49372)
-- Name: property_type_pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY wdb_property_type
    ADD CONSTRAINT property_type_pk PRIMARY KEY (property_type);


--
-- TOC entry 2013 (class 2606 OID 49402)
-- Name: setting_id; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY wdb_settings
    ADD CONSTRAINT setting_id UNIQUE (setting_id);


--
-- TOC entry 2015 (class 2606 OID 49400)
-- Name: settings_pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY wdb_settings
    ADD CONSTRAINT settings_pk PRIMARY KEY (setting_id);


--
-- TOC entry 2009 (class 2606 OID 49387)
-- Name: sitemap_id; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY wdb_sitemap
    ADD CONSTRAINT sitemap_id UNIQUE (sitemap_id);


--
-- TOC entry 2011 (class 2606 OID 49385)
-- Name: sitemap_pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY wdb_sitemap
    ADD CONSTRAINT sitemap_pk PRIMARY KEY (sitemap_id);


--
-- TOC entry 2019 (class 2606 OID 49413)
-- Name: wdb_changescriptPK; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY wdb_changescript
    ADD CONSTRAINT "wdb_changescriptPK" PRIMARY KEY (changescript_id);


--
-- TOC entry 1997 (class 2606 OID 49347)
-- Name: wdb_entityPK; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY wdb_entity
    ADD CONSTRAINT "wdb_entityPK" PRIMARY KEY (entity_id);


--
-- TOC entry 1999 (class 2606 OID 49349)
-- Name: wdb_entityUK; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY wdb_entity
    ADD CONSTRAINT "wdb_entityUK" UNIQUE (entity_name, application_id);


--
-- TOC entry 2029 (class 2606 OID 90131)
-- Name: Relationship10; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY wdb_property
    ADD CONSTRAINT "Relationship10" FOREIGN KEY (property_type) REFERENCES wdb_property_type(property_type);


--
-- TOC entry 2031 (class 2606 OID 73829)
-- Name: Relationship11; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY wdb_sitemap
    ADD CONSTRAINT "Relationship11" FOREIGN KEY (application_id) REFERENCES wdb_application(application_id);


--
-- TOC entry 2032 (class 2606 OID 73834)
-- Name: Relationship12; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY wdb_sitemap
    ADD CONSTRAINT "Relationship12" FOREIGN KEY (parent_sitemap_id) REFERENCES wdb_sitemap(sitemap_id);


--
-- TOC entry 2033 (class 2606 OID 73790)
-- Name: Relationship13; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY wdb_settings
    ADD CONSTRAINT "Relationship13" FOREIGN KEY (application_id) REFERENCES wdb_application(application_id);


--
-- TOC entry 2034 (class 2606 OID 73824)
-- Name: Relationship14; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY wdb_changescript
    ADD CONSTRAINT "Relationship14" FOREIGN KEY (application_id) REFERENCES wdb_application(application_id);


--
-- TOC entry 2028 (class 2606 OID 73738)
-- Name: Relationship8; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY wdb_entity
    ADD CONSTRAINT "Relationship8" FOREIGN KEY (application_id) REFERENCES wdb_application(application_id);


--
-- TOC entry 2030 (class 2606 OID 90136)
-- Name: Relationship9; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY wdb_property
    ADD CONSTRAINT "Relationship9" FOREIGN KEY (entity_id) REFERENCES wdb_entity(entity_id);


--
-- TOC entry 2026 (class 2606 OID 81977)
-- Name: fk_application_server; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY wdb_application
    ADD CONSTRAINT fk_application_server FOREIGN KEY (servertype_id) REFERENCES wdb_servertype(servertype_id);


--
-- TOC entry 2027 (class 2606 OID 82011)
-- Name: fk_databasetype; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY wdb_application
    ADD CONSTRAINT fk_databasetype FOREIGN KEY (databasetype_id) REFERENCES wdb_databasetype(databasetype_id);


--
-- TOC entry 2035 (class 2606 OID 81996)
-- Name: fk_release_application; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY wdb_release
    ADD CONSTRAINT fk_release_application FOREIGN KEY (application_id) REFERENCES wdb_application(application_id);


--
-- TOC entry 2042 (class 0 OID 0)
-- Dependencies: 5
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2013-12-21 13:10:11

--
-- PostgreSQL database dump complete
--



--
-- PostgreSQL database dump
--

-- Dumped from database version 9.2.4
-- Dumped by pg_dump version 9.2.4
-- Started on 2013-12-21 13:12:10

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

SET search_path = public, pg_catalog;




--
-- TOC entry 2053 (class 0 OID 81958)
-- Dependencies: 185
-- Data for Name: wdb_databasetype; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO wdb_databasetype VALUES (1, 'postgres', 'WrenchDb.Deploy.Database.Impl.WdbDatabasePostgresDeployer');



INSERT INTO wdb_servertype VALUES (1, 'tomcat7', 'WrenchDb.Deploy.Server.Impl.WdbTomcatDeployer');



INSERT INTO wdb_application VALUES (1, 'CentralAdmin', 'http://localhost:8084/WrenchDb.CentralAdmin.Web/', '/Apache Tomcat 7.0.34', 1, NULL, NULL, NULL, NULL, NULL, 1);




INSERT INTO wdb_property_type VALUES (8, 'SelectChoice', NULL, NULL);
INSERT INTO wdb_property_type VALUES (10, 'Image', NULL, NULL);
INSERT INTO wdb_property_type VALUES (11, 'File', NULL, NULL);
INSERT INTO wdb_property_type VALUES (12, 'RadioChoice', NULL, NULL);
INSERT INTO wdb_property_type VALUES (16, 'Expression', NULL, NULL);
INSERT INTO wdb_property_type VALUES (1, 'SingleLineText', NULL, 'character varying(300)');
INSERT INTO wdb_property_type VALUES (2, 'MultiLineText', NULL, 'character varying(3000)');
INSERT INTO wdb_property_type VALUES (3, 'HtmlText', NULL, 'character varying(3000)');
INSERT INTO wdb_property_type VALUES (18, 'Url', NULL, 'character varying(1000)');
INSERT INTO wdb_property_type VALUES (17, 'Email', NULL, 'character varying(1000)');
INSERT INTO wdb_property_type VALUES (15, 'AutoGeneratedPK', NULL, 'BIGSERIAL');
INSERT INTO wdb_property_type VALUES (14, 'Hidden', NULL, 'character varying(1000)');
INSERT INTO wdb_property_type VALUES (13, 'Password', NULL, 'character varying(100)');
INSERT INTO wdb_property_type VALUES (9, 'CheckBox', NULL, 'BOOLEAN');
INSERT INTO wdb_property_type VALUES (7, 'Lookup', NULL, 'BIGINT');
INSERT INTO wdb_property_type VALUES (6, 'Integer', NULL, 'INT');
INSERT INTO wdb_property_type VALUES (5, 'Number', NULL, 'FLOAT');
INSERT INTO wdb_property_type VALUES (4, 'DatePicker', NULL, 'DATE');






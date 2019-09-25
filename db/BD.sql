--
-- PostgreSQL database dump
--

-- Dumped from database version 11.4
-- Dumped by pg_dump version 11.4

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: DEV1; Type: DATABASE; Schema: -; Owner: -
--

CREATE DATABASE "DEV1" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Spanish_Colombia.1252' LC_CTYPE = 'Spanish_Colombia.1252';


\connect "DEV1"

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_with_oids = false;

--
-- Name: prenda; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.prenda (
    codigo character varying(50) NOT NULL,
    nombre character varying(50),
    tipo character varying(50),
    cantidad character varying(50),
    precio character varying(50)
);


--
-- Name: proveedor; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.proveedor (
    cedula character varying(50) NOT NULL,
    nombre character varying(50),
    direccion character varying(100),
    telefono character varying(50),
    email character varying(50)
);


--
-- Name: usuario; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.usuario (
    cedula character varying(50) NOT NULL,
    nombre character varying(50),
    direccion character varying(50),
    telefono character varying(50),
    contrasena character varying(50),
    tipo_usuario character varying(50)
);


--
-- Data for Name: prenda; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.prenda VALUES ('1231241', 'Blusita', 'Blusa', '212', '112');
INSERT INTO public.prenda VALUES ('462', 'Blusa', '12', '12', '12');
INSERT INTO public.prenda VALUES ('213', 'camiseta', 'manga larga', '1', '20000');


--
-- Data for Name: proveedor; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.proveedor VALUES ('567567567', 'Ana perez', 'Oficina Lote Carretera Apartado Carretera', '124312', 'ana@h.com');
INSERT INTO public.proveedor VALUES ('789789789', 'Ana perez', 'Oficina Lote Carretera Apartado Carretera', '124312', 'ana@h.com');
INSERT INTO public.proveedor VALUES ('412412412', 'Ana perez', 'Oficina Lote Carretera Apartado Carretera', '124312', 'ana@h.com');
INSERT INTO public.proveedor VALUES ('1212313123', 'Ana perez', 'Oficina Lote Carretera Apartado Carretera', '124312', 'ana@h.com');
INSERT INTO public.proveedor VALUES ('123123123', 'Jaime', 'Oficina Lote Carretera Apartado', '241212312', 'ana@correo.com');
INSERT INTO public.proveedor VALUES ('124', 'juana', 'Oficina Lote Carretera Apartado Carretera', '3154324', 'ana"hotmai');
INSERT INTO public.proveedor VALUES ('aaa', 'juana', 'Agencia', '4512', 'ana"hotmai');


--
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.usuario VALUES ('admin', 'Ana malunga', 'Cra 15', '3188458920', 'admin', 'Administrador');


--
-- Name: prenda prenda_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.prenda
    ADD CONSTRAINT prenda_pkey PRIMARY KEY (codigo);


--
-- Name: proveedor proveedor_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.proveedor
    ADD CONSTRAINT proveedor_pkey PRIMARY KEY (cedula);


--
-- Name: usuario usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (cedula);


--
-- PostgreSQL database dump complete
--


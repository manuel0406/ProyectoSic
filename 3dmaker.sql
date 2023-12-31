PGDMP     *    9        	    
    {            dmakers    14.8    14.8 �    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    19830    dmakers    DATABASE     c   CREATE DATABASE dmakers WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Spanish_Spain.1252';
    DROP DATABASE dmakers;
                dmakers    false            �           0    0    DATABASE dmakers    ACL     i   REVOKE CONNECT,TEMPORARY ON DATABASE dmakers FROM PUBLIC;
GRANT TEMPORARY ON DATABASE dmakers TO PUBLIC;
                   dmakers    false    3485            �            1259    19832    ajuste    TABLE     �   CREATE TABLE public.ajuste (
    idajuste integer NOT NULL,
    idcuenta integer,
    codigo integer,
    saldodeudor real,
    saldoacredor real
);
    DROP TABLE public.ajuste;
       public         heap    dmakers    false            �            1259    19831    ajuste_idajuste_seq    SEQUENCE     �   CREATE SEQUENCE public.ajuste_idajuste_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.ajuste_idajuste_seq;
       public          dmakers    false    210            �           0    0    ajuste_idajuste_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE public.ajuste_idajuste_seq OWNED BY public.ajuste.idajuste;
          public          dmakers    false    209            �            1259    19841    ajustebalancecomprobacion    TABLE     �   CREATE TABLE public.ajustebalancecomprobacion (
    idajustec integer NOT NULL,
    idbalancec integer,
    idajuste integer,
    saldodeudor real,
    saldoacredor real,
    codigocuenta integer
);
 -   DROP TABLE public.ajustebalancecomprobacion;
       public         heap    dmakers    false            �            1259    19840 '   ajustebalancecomprobacion_idajustec_seq    SEQUENCE     �   CREATE SEQUENCE public.ajustebalancecomprobacion_idajustec_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 >   DROP SEQUENCE public.ajustebalancecomprobacion_idajustec_seq;
       public          dmakers    false    212            �           0    0 '   ajustebalancecomprobacion_idajustec_seq    SEQUENCE OWNED BY     s   ALTER SEQUENCE public.ajustebalancecomprobacion_idajustec_seq OWNED BY public.ajustebalancecomprobacion.idajustec;
          public          dmakers    false    211            �            1259    19851    balancecomprobacion    TABLE     �   CREATE TABLE public.balancecomprobacion (
    idbalancec integer NOT NULL,
    idcuenta integer,
    saldoacredor real,
    saldodeudor real
);
 '   DROP TABLE public.balancecomprobacion;
       public         heap    dmakers    false            �            1259    19850 "   balancecomprobacion_idbalancec_seq    SEQUENCE     �   CREATE SEQUENCE public.balancecomprobacion_idbalancec_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 9   DROP SEQUENCE public.balancecomprobacion_idbalancec_seq;
       public          dmakers    false    214            �           0    0 "   balancecomprobacion_idbalancec_seq    SEQUENCE OWNED BY     i   ALTER SEQUENCE public.balancecomprobacion_idbalancec_seq OWNED BY public.balancecomprobacion.idbalancec;
          public          dmakers    false    213            �            1259    19860    balancegeneral    TABLE     �   CREATE TABLE public.balancegeneral (
    idbalanceg integer NOT NULL,
    idajustec integer,
    totalactivos real,
    totalpasivos real,
    totalcapital real
);
 "   DROP TABLE public.balancegeneral;
       public         heap    dmakers    false            �            1259    19859    balancegeneral_idbalanceg_seq    SEQUENCE     �   CREATE SEQUENCE public.balancegeneral_idbalanceg_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 4   DROP SEQUENCE public.balancegeneral_idbalanceg_seq;
       public          dmakers    false    216            �           0    0    balancegeneral_idbalanceg_seq    SEQUENCE OWNED BY     _   ALTER SEQUENCE public.balancegeneral_idbalanceg_seq OWNED BY public.balancegeneral.idbalanceg;
          public          dmakers    false    215            �            1259    19868    catalogocuenta    TABLE     m   CREATE TABLE public.catalogocuenta (
    codigo integer NOT NULL,
    nombrecuenta character varying(100)
);
 "   DROP TABLE public.catalogocuenta;
       public         heap    dmakers    false            �            1259    19884    cuenta    TABLE     �   CREATE TABLE public.cuenta (
    idcuenta integer NOT NULL,
    codigo integer,
    idtransaccion integer,
    totalizacion real,
    deudor boolean
);
    DROP TABLE public.cuenta;
       public         heap    dmakers    false            �            1259    19883    cuenta_idcuenta_seq    SEQUENCE     �   CREATE SEQUENCE public.cuenta_idcuenta_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.cuenta_idcuenta_seq;
       public          dmakers    false    219            �           0    0    cuenta_idcuenta_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE public.cuenta_idcuenta_seq OWNED BY public.cuenta.idcuenta;
          public          dmakers    false    218            �            1259    19894    estadocapital    TABLE     z   CREATE TABLE public.estadocapital (
    idestadocapital integer NOT NULL,
    idajustec integer,
    nuevocapital real
);
 !   DROP TABLE public.estadocapital;
       public         heap    dmakers    false            �            1259    19893 !   estadocapital_idestadocapital_seq    SEQUENCE     �   CREATE SEQUENCE public.estadocapital_idestadocapital_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 8   DROP SEQUENCE public.estadocapital_idestadocapital_seq;
       public          dmakers    false    221            �           0    0 !   estadocapital_idestadocapital_seq    SEQUENCE OWNED BY     g   ALTER SEQUENCE public.estadocapital_idestadocapital_seq OWNED BY public.estadocapital.idestadocapital;
          public          dmakers    false    220            �            1259    19903    estadoderesultado    TABLE     �   CREATE TABLE public.estadoderesultado (
    idestadoresultado integer NOT NULL,
    idajustec integer,
    utilidadbruta real,
    utilidadoperacion real,
    utilidadantesimpuesto real,
    utilidadneta real
);
 %   DROP TABLE public.estadoderesultado;
       public         heap    dmakers    false            �            1259    19902 '   estadoderesultado_idestadoresultado_seq    SEQUENCE     �   CREATE SEQUENCE public.estadoderesultado_idestadoresultado_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 >   DROP SEQUENCE public.estadoderesultado_idestadoresultado_seq;
       public          dmakers    false    223            �           0    0 '   estadoderesultado_idestadoresultado_seq    SEQUENCE OWNED BY     s   ALTER SEQUENCE public.estadoderesultado_idestadoresultado_seq OWNED BY public.estadoderesultado.idestadoresultado;
          public          dmakers    false    222            �            1259    19912 
   inventario    TABLE     �   CREATE TABLE public.inventario (
    idinventario integer NOT NULL,
    idproducto integer,
    cantidadinvetario integer,
    preciototal real,
    inventariofinal real,
    totalcompras real,
    costovendido real
);
    DROP TABLE public.inventario;
       public         heap    dmakers    false            �            1259    19911    inventario_idinventario_seq    SEQUENCE     �   CREATE SEQUENCE public.inventario_idinventario_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 2   DROP SEQUENCE public.inventario_idinventario_seq;
       public          dmakers    false    225            �           0    0    inventario_idinventario_seq    SEQUENCE OWNED BY     [   ALTER SEQUENCE public.inventario_idinventario_seq OWNED BY public.inventario.idinventario;
          public          dmakers    false    224            �            1259    20058 
   movimiento    TABLE     �   CREATE TABLE public.movimiento (
    idmovimiento integer NOT NULL,
    idproducto integer,
    cantidadmovimiento real,
    totalmovimiento real,
    clasificacion character varying(100),
    fechamovimiento date
);
    DROP TABLE public.movimiento;
       public         heap    dmakers    false            �            1259    20057    movimiento_idmovimiento_seq    SEQUENCE     �   CREATE SEQUENCE public.movimiento_idmovimiento_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 2   DROP SEQUENCE public.movimiento_idmovimiento_seq;
       public          dmakers    false    235            �           0    0    movimiento_idmovimiento_seq    SEQUENCE OWNED BY     [   ALTER SEQUENCE public.movimiento_idmovimiento_seq OWNED BY public.movimiento.idmovimiento;
          public          dmakers    false    234            �            1259    19921    periodocontable    TABLE     �   CREATE TABLE public.periodocontable (
    idperiodo integer NOT NULL,
    inicio character varying(10),
    fin character varying(10)
);
 #   DROP TABLE public.periodocontable;
       public         heap    dmakers    false            �            1259    19920    periodocontable_idperiodo_seq    SEQUENCE     �   CREATE SEQUENCE public.periodocontable_idperiodo_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 4   DROP SEQUENCE public.periodocontable_idperiodo_seq;
       public          dmakers    false    227            �           0    0    periodocontable_idperiodo_seq    SEQUENCE OWNED BY     _   ALTER SEQUENCE public.periodocontable_idperiodo_seq OWNED BY public.periodocontable.idperiodo;
          public          dmakers    false    226            �            1259    19929    producto    TABLE     �   CREATE TABLE public.producto (
    idproducto integer NOT NULL,
    nombreproduto character varying(100),
    preciounitario real
);
    DROP TABLE public.producto;
       public         heap    dmakers    false            �            1259    19928    producto_idproducto_seq    SEQUENCE     �   CREATE SEQUENCE public.producto_idproducto_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 .   DROP SEQUENCE public.producto_idproducto_seq;
       public          dmakers    false    229            �           0    0    producto_idproducto_seq    SEQUENCE OWNED BY     S   ALTER SEQUENCE public.producto_idproducto_seq OWNED BY public.producto.idproducto;
          public          dmakers    false    228            �            1259    19937    reporte    TABLE     �   CREATE TABLE public.reporte (
    idrepoter integer NOT NULL,
    idestadocapital integer,
    idperiodo integer,
    idestadoresultado integer,
    idbalanceg integer,
    firma character varying(100),
    autor character varying(100)
);
    DROP TABLE public.reporte;
       public         heap    dmakers    false            �            1259    19936    reporte_idrepoter_seq    SEQUENCE     �   CREATE SEQUENCE public.reporte_idrepoter_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ,   DROP SEQUENCE public.reporte_idrepoter_seq;
       public          dmakers    false    231            �           0    0    reporte_idrepoter_seq    SEQUENCE OWNED BY     O   ALTER SEQUENCE public.reporte_idrepoter_seq OWNED BY public.reporte.idrepoter;
          public          dmakers    false    230            �            1259    19949    transaccion    TABLE     �   CREATE TABLE public.transaccion (
    idtransaccion integer NOT NULL,
    codigo integer,
    concepto character varying(100),
    debe real,
    haber real
);
    DROP TABLE public.transaccion;
       public         heap    dmakers    false            �            1259    19948    transaccion_idtransaccion_seq    SEQUENCE     �   CREATE SEQUENCE public.transaccion_idtransaccion_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 4   DROP SEQUENCE public.transaccion_idtransaccion_seq;
       public          dmakers    false    233            �           0    0    transaccion_idtransaccion_seq    SEQUENCE OWNED BY     _   ALTER SEQUENCE public.transaccion_idtransaccion_seq OWNED BY public.transaccion.idtransaccion;
          public          dmakers    false    232            �           2604    19835    ajuste idajuste    DEFAULT     r   ALTER TABLE ONLY public.ajuste ALTER COLUMN idajuste SET DEFAULT nextval('public.ajuste_idajuste_seq'::regclass);
 >   ALTER TABLE public.ajuste ALTER COLUMN idajuste DROP DEFAULT;
       public          dmakers    false    209    210    210            �           2604    19844 #   ajustebalancecomprobacion idajustec    DEFAULT     �   ALTER TABLE ONLY public.ajustebalancecomprobacion ALTER COLUMN idajustec SET DEFAULT nextval('public.ajustebalancecomprobacion_idajustec_seq'::regclass);
 R   ALTER TABLE public.ajustebalancecomprobacion ALTER COLUMN idajustec DROP DEFAULT;
       public          dmakers    false    212    211    212            �           2604    19854    balancecomprobacion idbalancec    DEFAULT     �   ALTER TABLE ONLY public.balancecomprobacion ALTER COLUMN idbalancec SET DEFAULT nextval('public.balancecomprobacion_idbalancec_seq'::regclass);
 M   ALTER TABLE public.balancecomprobacion ALTER COLUMN idbalancec DROP DEFAULT;
       public          dmakers    false    214    213    214            �           2604    19863    balancegeneral idbalanceg    DEFAULT     �   ALTER TABLE ONLY public.balancegeneral ALTER COLUMN idbalanceg SET DEFAULT nextval('public.balancegeneral_idbalanceg_seq'::regclass);
 H   ALTER TABLE public.balancegeneral ALTER COLUMN idbalanceg DROP DEFAULT;
       public          dmakers    false    215    216    216            �           2604    19887    cuenta idcuenta    DEFAULT     r   ALTER TABLE ONLY public.cuenta ALTER COLUMN idcuenta SET DEFAULT nextval('public.cuenta_idcuenta_seq'::regclass);
 >   ALTER TABLE public.cuenta ALTER COLUMN idcuenta DROP DEFAULT;
       public          dmakers    false    219    218    219            �           2604    19897    estadocapital idestadocapital    DEFAULT     �   ALTER TABLE ONLY public.estadocapital ALTER COLUMN idestadocapital SET DEFAULT nextval('public.estadocapital_idestadocapital_seq'::regclass);
 L   ALTER TABLE public.estadocapital ALTER COLUMN idestadocapital DROP DEFAULT;
       public          dmakers    false    221    220    221            �           2604    19906 #   estadoderesultado idestadoresultado    DEFAULT     �   ALTER TABLE ONLY public.estadoderesultado ALTER COLUMN idestadoresultado SET DEFAULT nextval('public.estadoderesultado_idestadoresultado_seq'::regclass);
 R   ALTER TABLE public.estadoderesultado ALTER COLUMN idestadoresultado DROP DEFAULT;
       public          dmakers    false    223    222    223            �           2604    19915    inventario idinventario    DEFAULT     �   ALTER TABLE ONLY public.inventario ALTER COLUMN idinventario SET DEFAULT nextval('public.inventario_idinventario_seq'::regclass);
 F   ALTER TABLE public.inventario ALTER COLUMN idinventario DROP DEFAULT;
       public          dmakers    false    224    225    225            �           2604    20061    movimiento idmovimiento    DEFAULT     �   ALTER TABLE ONLY public.movimiento ALTER COLUMN idmovimiento SET DEFAULT nextval('public.movimiento_idmovimiento_seq'::regclass);
 F   ALTER TABLE public.movimiento ALTER COLUMN idmovimiento DROP DEFAULT;
       public          dmakers    false    234    235    235            �           2604    19924    periodocontable idperiodo    DEFAULT     �   ALTER TABLE ONLY public.periodocontable ALTER COLUMN idperiodo SET DEFAULT nextval('public.periodocontable_idperiodo_seq'::regclass);
 H   ALTER TABLE public.periodocontable ALTER COLUMN idperiodo DROP DEFAULT;
       public          dmakers    false    226    227    227            �           2604    19932    producto idproducto    DEFAULT     z   ALTER TABLE ONLY public.producto ALTER COLUMN idproducto SET DEFAULT nextval('public.producto_idproducto_seq'::regclass);
 B   ALTER TABLE public.producto ALTER COLUMN idproducto DROP DEFAULT;
       public          dmakers    false    228    229    229            �           2604    19940    reporte idrepoter    DEFAULT     v   ALTER TABLE ONLY public.reporte ALTER COLUMN idrepoter SET DEFAULT nextval('public.reporte_idrepoter_seq'::regclass);
 @   ALTER TABLE public.reporte ALTER COLUMN idrepoter DROP DEFAULT;
       public          dmakers    false    230    231    231            �           2604    19952    transaccion idtransaccion    DEFAULT     �   ALTER TABLE ONLY public.transaccion ALTER COLUMN idtransaccion SET DEFAULT nextval('public.transaccion_idtransaccion_seq'::regclass);
 H   ALTER TABLE public.transaccion ALTER COLUMN idtransaccion DROP DEFAULT;
       public          dmakers    false    232    233    233            ~          0    19832    ajuste 
   TABLE DATA           W   COPY public.ajuste (idajuste, idcuenta, codigo, saldodeudor, saldoacredor) FROM stdin;
    public          dmakers    false    210   ��       �          0    19841    ajustebalancecomprobacion 
   TABLE DATA           }   COPY public.ajustebalancecomprobacion (idajustec, idbalancec, idajuste, saldodeudor, saldoacredor, codigocuenta) FROM stdin;
    public          dmakers    false    212   ��       �          0    19851    balancecomprobacion 
   TABLE DATA           ^   COPY public.balancecomprobacion (idbalancec, idcuenta, saldoacredor, saldodeudor) FROM stdin;
    public          dmakers    false    214   �       �          0    19860    balancegeneral 
   TABLE DATA           i   COPY public.balancegeneral (idbalanceg, idajustec, totalactivos, totalpasivos, totalcapital) FROM stdin;
    public          dmakers    false    216   n�       �          0    19868    catalogocuenta 
   TABLE DATA           >   COPY public.catalogocuenta (codigo, nombrecuenta) FROM stdin;
    public          dmakers    false    217   ��       �          0    19884    cuenta 
   TABLE DATA           W   COPY public.cuenta (idcuenta, codigo, idtransaccion, totalizacion, deudor) FROM stdin;
    public          dmakers    false    219   e�       �          0    19894    estadocapital 
   TABLE DATA           Q   COPY public.estadocapital (idestadocapital, idajustec, nuevocapital) FROM stdin;
    public          dmakers    false    221   Ʊ       �          0    19903    estadoderesultado 
   TABLE DATA           �   COPY public.estadoderesultado (idestadoresultado, idajustec, utilidadbruta, utilidadoperacion, utilidadantesimpuesto, utilidadneta) FROM stdin;
    public          dmakers    false    223   �       �          0    19912 
   inventario 
   TABLE DATA           �   COPY public.inventario (idinventario, idproducto, cantidadinvetario, preciototal, inventariofinal, totalcompras, costovendido) FROM stdin;
    public          dmakers    false    225   �       �          0    20058 
   movimiento 
   TABLE DATA           �   COPY public.movimiento (idmovimiento, idproducto, cantidadmovimiento, totalmovimiento, clasificacion, fechamovimiento) FROM stdin;
    public          dmakers    false    235   D�       �          0    19921    periodocontable 
   TABLE DATA           A   COPY public.periodocontable (idperiodo, inicio, fin) FROM stdin;
    public          dmakers    false    227   ��       �          0    19929    producto 
   TABLE DATA           M   COPY public.producto (idproducto, nombreproduto, preciounitario) FROM stdin;
    public          dmakers    false    229   Բ       �          0    19937    reporte 
   TABLE DATA           u   COPY public.reporte (idrepoter, idestadocapital, idperiodo, idestadoresultado, idbalanceg, firma, autor) FROM stdin;
    public          dmakers    false    231   �       �          0    19949    transaccion 
   TABLE DATA           S   COPY public.transaccion (idtransaccion, codigo, concepto, debe, haber) FROM stdin;
    public          dmakers    false    233   /�       �           0    0    ajuste_idajuste_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.ajuste_idajuste_seq', 31, true);
          public          dmakers    false    209            �           0    0 '   ajustebalancecomprobacion_idajustec_seq    SEQUENCE SET     V   SELECT pg_catalog.setval('public.ajustebalancecomprobacion_idajustec_seq', 51, true);
          public          dmakers    false    211            �           0    0 "   balancecomprobacion_idbalancec_seq    SEQUENCE SET     Q   SELECT pg_catalog.setval('public.balancecomprobacion_idbalancec_seq', 49, true);
          public          dmakers    false    213            �           0    0    balancegeneral_idbalanceg_seq    SEQUENCE SET     K   SELECT pg_catalog.setval('public.balancegeneral_idbalanceg_seq', 1, true);
          public          dmakers    false    215            �           0    0    cuenta_idcuenta_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.cuenta_idcuenta_seq', 50, true);
          public          dmakers    false    218            �           0    0 !   estadocapital_idestadocapital_seq    SEQUENCE SET     O   SELECT pg_catalog.setval('public.estadocapital_idestadocapital_seq', 1, true);
          public          dmakers    false    220            �           0    0 '   estadoderesultado_idestadoresultado_seq    SEQUENCE SET     U   SELECT pg_catalog.setval('public.estadoderesultado_idestadoresultado_seq', 1, true);
          public          dmakers    false    222            �           0    0    inventario_idinventario_seq    SEQUENCE SET     J   SELECT pg_catalog.setval('public.inventario_idinventario_seq', 1, false);
          public          dmakers    false    224            �           0    0    movimiento_idmovimiento_seq    SEQUENCE SET     I   SELECT pg_catalog.setval('public.movimiento_idmovimiento_seq', 7, true);
          public          dmakers    false    234            �           0    0    periodocontable_idperiodo_seq    SEQUENCE SET     L   SELECT pg_catalog.setval('public.periodocontable_idperiodo_seq', 1, false);
          public          dmakers    false    226            �           0    0    producto_idproducto_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public.producto_idproducto_seq', 2, true);
          public          dmakers    false    228            �           0    0    reporte_idrepoter_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('public.reporte_idrepoter_seq', 1, false);
          public          dmakers    false    230            �           0    0    transaccion_idtransaccion_seq    SEQUENCE SET     M   SELECT pg_catalog.setval('public.transaccion_idtransaccion_seq', 135, true);
          public          dmakers    false    232            �           2606    20063    movimiento movimiento_pkey 
   CONSTRAINT     b   ALTER TABLE ONLY public.movimiento
    ADD CONSTRAINT movimiento_pkey PRIMARY KEY (idmovimiento);
 D   ALTER TABLE ONLY public.movimiento DROP CONSTRAINT movimiento_pkey;
       public            dmakers    false    235            �           2606    19837    ajuste pk_ajuste 
   CONSTRAINT     T   ALTER TABLE ONLY public.ajuste
    ADD CONSTRAINT pk_ajuste PRIMARY KEY (idajuste);
 :   ALTER TABLE ONLY public.ajuste DROP CONSTRAINT pk_ajuste;
       public            dmakers    false    210            �           2606    19846 6   ajustebalancecomprobacion pk_ajustebalancecomprobacion 
   CONSTRAINT     {   ALTER TABLE ONLY public.ajustebalancecomprobacion
    ADD CONSTRAINT pk_ajustebalancecomprobacion PRIMARY KEY (idajustec);
 `   ALTER TABLE ONLY public.ajustebalancecomprobacion DROP CONSTRAINT pk_ajustebalancecomprobacion;
       public            dmakers    false    212            �           2606    19856 *   balancecomprobacion pk_balancecomprobacion 
   CONSTRAINT     p   ALTER TABLE ONLY public.balancecomprobacion
    ADD CONSTRAINT pk_balancecomprobacion PRIMARY KEY (idbalancec);
 T   ALTER TABLE ONLY public.balancecomprobacion DROP CONSTRAINT pk_balancecomprobacion;
       public            dmakers    false    214            �           2606    19865     balancegeneral pk_balancegeneral 
   CONSTRAINT     f   ALTER TABLE ONLY public.balancegeneral
    ADD CONSTRAINT pk_balancegeneral PRIMARY KEY (idbalanceg);
 J   ALTER TABLE ONLY public.balancegeneral DROP CONSTRAINT pk_balancegeneral;
       public            dmakers    false    216            �           2606    19872     catalogocuenta pk_catalogocuenta 
   CONSTRAINT     b   ALTER TABLE ONLY public.catalogocuenta
    ADD CONSTRAINT pk_catalogocuenta PRIMARY KEY (codigo);
 J   ALTER TABLE ONLY public.catalogocuenta DROP CONSTRAINT pk_catalogocuenta;
       public            dmakers    false    217            �           2606    19889    cuenta pk_cuenta 
   CONSTRAINT     T   ALTER TABLE ONLY public.cuenta
    ADD CONSTRAINT pk_cuenta PRIMARY KEY (idcuenta);
 :   ALTER TABLE ONLY public.cuenta DROP CONSTRAINT pk_cuenta;
       public            dmakers    false    219            �           2606    19899    estadocapital pk_estadocapital 
   CONSTRAINT     i   ALTER TABLE ONLY public.estadocapital
    ADD CONSTRAINT pk_estadocapital PRIMARY KEY (idestadocapital);
 H   ALTER TABLE ONLY public.estadocapital DROP CONSTRAINT pk_estadocapital;
       public            dmakers    false    221            �           2606    19908 &   estadoderesultado pk_estadoderesultado 
   CONSTRAINT     s   ALTER TABLE ONLY public.estadoderesultado
    ADD CONSTRAINT pk_estadoderesultado PRIMARY KEY (idestadoresultado);
 P   ALTER TABLE ONLY public.estadoderesultado DROP CONSTRAINT pk_estadoderesultado;
       public            dmakers    false    223            �           2606    19917    inventario pk_inventario 
   CONSTRAINT     `   ALTER TABLE ONLY public.inventario
    ADD CONSTRAINT pk_inventario PRIMARY KEY (idinventario);
 B   ALTER TABLE ONLY public.inventario DROP CONSTRAINT pk_inventario;
       public            dmakers    false    225            �           2606    19926 "   periodocontable pk_periodocontable 
   CONSTRAINT     g   ALTER TABLE ONLY public.periodocontable
    ADD CONSTRAINT pk_periodocontable PRIMARY KEY (idperiodo);
 L   ALTER TABLE ONLY public.periodocontable DROP CONSTRAINT pk_periodocontable;
       public            dmakers    false    227            �           2606    19934    producto pk_producto 
   CONSTRAINT     Z   ALTER TABLE ONLY public.producto
    ADD CONSTRAINT pk_producto PRIMARY KEY (idproducto);
 >   ALTER TABLE ONLY public.producto DROP CONSTRAINT pk_producto;
       public            dmakers    false    229            �           2606    19942    reporte pk_reporte 
   CONSTRAINT     W   ALTER TABLE ONLY public.reporte
    ADD CONSTRAINT pk_reporte PRIMARY KEY (idrepoter);
 <   ALTER TABLE ONLY public.reporte DROP CONSTRAINT pk_reporte;
       public            dmakers    false    231            �           2606    19954    transaccion pk_transaccion 
   CONSTRAINT     c   ALTER TABLE ONLY public.transaccion
    ADD CONSTRAINT pk_transaccion PRIMARY KEY (idtransaccion);
 D   ALTER TABLE ONLY public.transaccion DROP CONSTRAINT pk_transaccion;
       public            dmakers    false    233            �           1259    19838 	   ajuste_pk    INDEX     G   CREATE UNIQUE INDEX ajuste_pk ON public.ajuste USING btree (idajuste);
    DROP INDEX public.ajuste_pk;
       public            dmakers    false    210            �           1259    19847    ajustebalancecomprobacion_pk    INDEX     n   CREATE UNIQUE INDEX ajustebalancecomprobacion_pk ON public.ajustebalancecomprobacion USING btree (idajustec);
 0   DROP INDEX public.ajustebalancecomprobacion_pk;
       public            dmakers    false    212            �           1259    19857    balancecomprobacion_pk    INDEX     c   CREATE UNIQUE INDEX balancecomprobacion_pk ON public.balancecomprobacion USING btree (idbalancec);
 *   DROP INDEX public.balancecomprobacion_pk;
       public            dmakers    false    214            �           1259    19866    balancegeneral_pk    INDEX     Y   CREATE UNIQUE INDEX balancegeneral_pk ON public.balancegeneral USING btree (idbalanceg);
 %   DROP INDEX public.balancegeneral_pk;
       public            dmakers    false    216            �           1259    19873    catalogocuenta_pk    INDEX     U   CREATE UNIQUE INDEX catalogocuenta_pk ON public.catalogocuenta USING btree (codigo);
 %   DROP INDEX public.catalogocuenta_pk;
       public            dmakers    false    217            �           1259    19946    comprende_fk    INDEX     F   CREATE INDEX comprende_fk ON public.reporte USING btree (idbalanceg);
     DROP INDEX public.comprende_fk;
       public            dmakers    false    231            �           1259    19944    contiene_fk    INDEX     J   CREATE INDEX contiene_fk ON public.reporte USING btree (idestadocapital);
    DROP INDEX public.contiene_fk;
       public            dmakers    false    231            �           1259    19890 	   cuenta_pk    INDEX     G   CREATE UNIQUE INDEX cuenta_pk ON public.cuenta USING btree (idcuenta);
    DROP INDEX public.cuenta_pk;
       public            dmakers    false    219            �           1259    19900    estadocapital_pk    INDEX     \   CREATE UNIQUE INDEX estadocapital_pk ON public.estadocapital USING btree (idestadocapital);
 $   DROP INDEX public.estadocapital_pk;
       public            dmakers    false    221            �           1259    19909    estadoderesultado_pk    INDEX     f   CREATE UNIQUE INDEX estadoderesultado_pk ON public.estadoderesultado USING btree (idestadoresultado);
 (   DROP INDEX public.estadoderesultado_pk;
       public            dmakers    false    223            �           1259    19918    inventario_pk    INDEX     S   CREATE UNIQUE INDEX inventario_pk ON public.inventario USING btree (idinventario);
 !   DROP INDEX public.inventario_pk;
       public            dmakers    false    225            �           1259    19867 	   maneja_fk    INDEX     I   CREATE INDEX maneja_fk ON public.balancegeneral USING btree (idajustec);
    DROP INDEX public.maneja_fk;
       public            dmakers    false    216            �           1259    19947    necesita_fk    INDEX     D   CREATE INDEX necesita_fk ON public.reporte USING btree (idperiodo);
    DROP INDEX public.necesita_fk;
       public            dmakers    false    231            �           1259    19848    necestia_fk    INDEX     W   CREATE INDEX necestia_fk ON public.ajustebalancecomprobacion USING btree (idbalancec);
    DROP INDEX public.necestia_fk;
       public            dmakers    false    212            �           1259    19849    ocupa_fk    INDEX     R   CREATE INDEX ocupa_fk ON public.ajustebalancecomprobacion USING btree (idajuste);
    DROP INDEX public.ocupa_fk;
       public            dmakers    false    212            �           1259    19927    periodocontable_pk    INDEX     Z   CREATE UNIQUE INDEX periodocontable_pk ON public.periodocontable USING btree (idperiodo);
 &   DROP INDEX public.periodocontable_pk;
       public            dmakers    false    227            �           1259    19892    petenece_fk    INDEX     @   CREATE INDEX petenece_fk ON public.cuenta USING btree (codigo);
    DROP INDEX public.petenece_fk;
       public            dmakers    false    219            �           1259    19919    posee_fk    INDEX     E   CREATE INDEX posee_fk ON public.inventario USING btree (idproducto);
    DROP INDEX public.posee_fk;
       public            dmakers    false    225            �           1259    19945    presenta_fk    INDEX     L   CREATE INDEX presenta_fk ON public.reporte USING btree (idestadoresultado);
    DROP INDEX public.presenta_fk;
       public            dmakers    false    231            �           1259    19956 
   presisa_fk    INDEX     D   CREATE INDEX presisa_fk ON public.transaccion USING btree (codigo);
    DROP INDEX public.presisa_fk;
       public            dmakers    false    233            �           1259    19935    producto_pk    INDEX     M   CREATE UNIQUE INDEX producto_pk ON public.producto USING btree (idproducto);
    DROP INDEX public.producto_pk;
       public            dmakers    false    229            �           1259    19943 
   reporte_pk    INDEX     J   CREATE UNIQUE INDEX reporte_pk ON public.reporte USING btree (idrepoter);
    DROP INDEX public.reporte_pk;
       public            dmakers    false    231            �           1259    19901    requiere_fk    INDEX     J   CREATE INDEX requiere_fk ON public.estadocapital USING btree (idajustec);
    DROP INDEX public.requiere_fk;
       public            dmakers    false    221            �           1259    19891    se_compone_fk    INDEX     I   CREATE INDEX se_compone_fk ON public.cuenta USING btree (idtransaccion);
 !   DROP INDEX public.se_compone_fk;
       public            dmakers    false    219            �           1259    19858    tiene_fk    INDEX     L   CREATE INDEX tiene_fk ON public.balancecomprobacion USING btree (idcuenta);
    DROP INDEX public.tiene_fk;
       public            dmakers    false    214            �           1259    19955    transaccion_pk    INDEX     V   CREATE UNIQUE INDEX transaccion_pk ON public.transaccion USING btree (idtransaccion);
 "   DROP INDEX public.transaccion_pk;
       public            dmakers    false    233            �           1259    19910    uitiliza_fk    INDEX     N   CREATE INDEX uitiliza_fk ON public.estadoderesultado USING btree (idajustec);
    DROP INDEX public.uitiliza_fk;
       public            dmakers    false    223            �           1259    19839    usa_fk    INDEX     =   CREATE INDEX usa_fk ON public.ajuste USING btree (idcuenta);
    DROP INDEX public.usa_fk;
       public            dmakers    false    210            �           2606    20051    ajuste ajutes_de_cuentas    FK CONSTRAINT     �   ALTER TABLE ONLY public.ajuste
    ADD CONSTRAINT ajutes_de_cuentas FOREIGN KEY (codigo) REFERENCES public.catalogocuenta(codigo);
 B   ALTER TABLE ONLY public.ajuste DROP CONSTRAINT ajutes_de_cuentas;
       public          dmakers    false    3260    210    217            �           2606    19966    ajuste fk_ajuste_usa_cuenta    FK CONSTRAINT     �   ALTER TABLE ONLY public.ajuste
    ADD CONSTRAINT fk_ajuste_usa_cuenta FOREIGN KEY (idcuenta) REFERENCES public.cuenta(idcuenta) ON UPDATE RESTRICT ON DELETE RESTRICT;
 E   ALTER TABLE ONLY public.ajuste DROP CONSTRAINT fk_ajuste_usa_cuenta;
       public          dmakers    false    210    219    3264            �           2606    19971 7   ajustebalancecomprobacion fk_ajusteba_necestia_balancec    FK CONSTRAINT     �   ALTER TABLE ONLY public.ajustebalancecomprobacion
    ADD CONSTRAINT fk_ajusteba_necestia_balancec FOREIGN KEY (idbalancec) REFERENCES public.balancecomprobacion(idbalancec) ON UPDATE RESTRICT ON DELETE RESTRICT;
 a   ALTER TABLE ONLY public.ajustebalancecomprobacion DROP CONSTRAINT fk_ajusteba_necestia_balancec;
       public          dmakers    false    214    212    3252            �           2606    19976 2   ajustebalancecomprobacion fk_ajusteba_ocupa_ajuste    FK CONSTRAINT     �   ALTER TABLE ONLY public.ajustebalancecomprobacion
    ADD CONSTRAINT fk_ajusteba_ocupa_ajuste FOREIGN KEY (idajuste) REFERENCES public.ajuste(idajuste) ON UPDATE RESTRICT ON DELETE RESTRICT;
 \   ALTER TABLE ONLY public.ajustebalancecomprobacion DROP CONSTRAINT fk_ajusteba_ocupa_ajuste;
       public          dmakers    false    212    210    3243            �           2606    19981 ,   balancecomprobacion fk_balancec_tiene_cuenta    FK CONSTRAINT     �   ALTER TABLE ONLY public.balancecomprobacion
    ADD CONSTRAINT fk_balancec_tiene_cuenta FOREIGN KEY (idcuenta) REFERENCES public.cuenta(idcuenta) ON UPDATE RESTRICT ON DELETE RESTRICT;
 V   ALTER TABLE ONLY public.balancecomprobacion DROP CONSTRAINT fk_balancec_tiene_cuenta;
       public          dmakers    false    214    3264    219            �           2606    19986 *   balancegeneral fk_balanceg_maneja_ajusteba    FK CONSTRAINT     �   ALTER TABLE ONLY public.balancegeneral
    ADD CONSTRAINT fk_balanceg_maneja_ajusteba FOREIGN KEY (idajustec) REFERENCES public.ajustebalancecomprobacion(idajustec) ON UPDATE RESTRICT ON DELETE RESTRICT;
 T   ALTER TABLE ONLY public.balancegeneral DROP CONSTRAINT fk_balanceg_maneja_ajusteba;
       public          dmakers    false    216    212    3249            �           2606    19996 "   cuenta fk_cuenta_petenece_catalogo    FK CONSTRAINT     �   ALTER TABLE ONLY public.cuenta
    ADD CONSTRAINT fk_cuenta_petenece_catalogo FOREIGN KEY (codigo) REFERENCES public.catalogocuenta(codigo) ON UPDATE RESTRICT ON DELETE RESTRICT;
 L   ALTER TABLE ONLY public.cuenta DROP CONSTRAINT fk_cuenta_petenece_catalogo;
       public          dmakers    false    217    3260    219            �           2606    20001 #   cuenta fk_cuenta_se_compon_transacc    FK CONSTRAINT     �   ALTER TABLE ONLY public.cuenta
    ADD CONSTRAINT fk_cuenta_se_compon_transacc FOREIGN KEY (idtransaccion) REFERENCES public.transaccion(idtransaccion) ON UPDATE RESTRICT ON DELETE RESTRICT;
 M   ALTER TABLE ONLY public.cuenta DROP CONSTRAINT fk_cuenta_se_compon_transacc;
       public          dmakers    false    233    219    3292            �           2606    20006 +   estadocapital fk_estadoca_requiere_ajusteba    FK CONSTRAINT     �   ALTER TABLE ONLY public.estadocapital
    ADD CONSTRAINT fk_estadoca_requiere_ajusteba FOREIGN KEY (idajustec) REFERENCES public.ajustebalancecomprobacion(idajustec) ON UPDATE RESTRICT ON DELETE RESTRICT;
 U   ALTER TABLE ONLY public.estadocapital DROP CONSTRAINT fk_estadoca_requiere_ajusteba;
       public          dmakers    false    221    212    3249            �           2606    20011 /   estadoderesultado fk_estadode_uitiliza_ajusteba    FK CONSTRAINT     �   ALTER TABLE ONLY public.estadoderesultado
    ADD CONSTRAINT fk_estadode_uitiliza_ajusteba FOREIGN KEY (idajustec) REFERENCES public.ajustebalancecomprobacion(idajustec) ON UPDATE RESTRICT ON DELETE RESTRICT;
 Y   ALTER TABLE ONLY public.estadoderesultado DROP CONSTRAINT fk_estadode_uitiliza_ajusteba;
       public          dmakers    false    223    212    3249            �           2606    20016 %   inventario fk_inventar_posee_producto    FK CONSTRAINT     �   ALTER TABLE ONLY public.inventario
    ADD CONSTRAINT fk_inventar_posee_producto FOREIGN KEY (idproducto) REFERENCES public.producto(idproducto) ON UPDATE RESTRICT ON DELETE RESTRICT;
 O   ALTER TABLE ONLY public.inventario DROP CONSTRAINT fk_inventar_posee_producto;
       public          dmakers    false    229    225    3282            �           2606    20064 !   movimiento fk_movimiento_producto    FK CONSTRAINT     �   ALTER TABLE ONLY public.movimiento
    ADD CONSTRAINT fk_movimiento_producto FOREIGN KEY (idproducto) REFERENCES public.producto(idproducto);
 K   ALTER TABLE ONLY public.movimiento DROP CONSTRAINT fk_movimiento_producto;
       public          dmakers    false    235    229    3282            �           2606    20021 %   reporte fk_reporte_comprende_balanceg    FK CONSTRAINT     �   ALTER TABLE ONLY public.reporte
    ADD CONSTRAINT fk_reporte_comprende_balanceg FOREIGN KEY (idbalanceg) REFERENCES public.balancegeneral(idbalanceg) ON UPDATE RESTRICT ON DELETE RESTRICT;
 O   ALTER TABLE ONLY public.reporte DROP CONSTRAINT fk_reporte_comprende_balanceg;
       public          dmakers    false    216    3257    231            �           2606    20026 $   reporte fk_reporte_contiene_estadoca    FK CONSTRAINT     �   ALTER TABLE ONLY public.reporte
    ADD CONSTRAINT fk_reporte_contiene_estadoca FOREIGN KEY (idestadocapital) REFERENCES public.estadocapital(idestadocapital) ON UPDATE RESTRICT ON DELETE RESTRICT;
 N   ALTER TABLE ONLY public.reporte DROP CONSTRAINT fk_reporte_contiene_estadoca;
       public          dmakers    false    231    221    3268            �           2606    20031 $   reporte fk_reporte_necesita_periodoc    FK CONSTRAINT     �   ALTER TABLE ONLY public.reporte
    ADD CONSTRAINT fk_reporte_necesita_periodoc FOREIGN KEY (idperiodo) REFERENCES public.periodocontable(idperiodo) ON UPDATE RESTRICT ON DELETE RESTRICT;
 N   ALTER TABLE ONLY public.reporte DROP CONSTRAINT fk_reporte_necesita_periodoc;
       public          dmakers    false    3280    227    231            �           2606    20036 $   reporte fk_reporte_presenta_estadode    FK CONSTRAINT     �   ALTER TABLE ONLY public.reporte
    ADD CONSTRAINT fk_reporte_presenta_estadode FOREIGN KEY (idestadoresultado) REFERENCES public.estadoderesultado(idestadoresultado) ON UPDATE RESTRICT ON DELETE RESTRICT;
 N   ALTER TABLE ONLY public.reporte DROP CONSTRAINT fk_reporte_presenta_estadode;
       public          dmakers    false    223    231    3272            �           2606    20041 (   transaccion fk_transacc_presisa_catalogo    FK CONSTRAINT     �   ALTER TABLE ONLY public.transaccion
    ADD CONSTRAINT fk_transacc_presisa_catalogo FOREIGN KEY (codigo) REFERENCES public.catalogocuenta(codigo) ON UPDATE RESTRICT ON DELETE RESTRICT;
 R   ALTER TABLE ONLY public.transaccion DROP CONSTRAINT fk_transacc_presisa_catalogo;
       public          dmakers    false    217    233    3260            ~   '   x�36���414S\� �����i����� �k�      �   Q   x�E���0C�s<�K��L���Am�rz����'��Qh��u�'io�5���c�?��"i1Z��J�U�_֊N���vG      �   @   x�%��� �P��DLL/�_�x��e9w��%A��	��	�U�~SJd��t>&J]H����p�B      �      x�3��"�=... "�6      �   �  x�mR�n�0�ů�&%��k�E��F�L]h��!���Oʐ�[W�Xe+Q�l�{w��ݣ1�8��S����|@T��u�:��5��;]�v��?4!�D�r��Q�-u��>���,N���Ùv�sq�����kNA��]��5�k��D��	�3��{��T�4���������z��P�����q��킬-NF�:E]�H��b�1��EGX5�ͦ���cV��XC�QI��$3�#	�[���G��S<'3�5K���5�ȗ�ʹ>	��Å�ً��Rb�{�$���Bݲ&L�NC��j���0웋	*p\�ٮqL�<O:�vA#L"hk{�������CG��
p�Y��p�oWJ�d:/�j�w�H�B��\��~4~\����W�������ky&R;�m߭��,y���6�.$/ �X��D��j�?>�y��~RJ�3c      �   Q   x�=���0D��R��"$ڄ�
�'z��g�#H������x!Lq�;|A	�E/X�	��ݹ���aJ(�����      �      x�3���4������ ��      �      x�3��C �=... 0(�      �   &   x�3�4�45�4�3�423�4257�4264����� F{      �   c   x�3�4�42�y%E�)��FFƺ���\F������z��҆\�@ic��)���	P��Ԁ3Ə381'U�)�l,��\fp�����^�=... ��,      �      x������ � �      �   .   x�3�t��I�M�+�W�q�44�2�tL�H-�,��42����� ��	�      �      x������ � �      �   �   x�u�A� ���p�9�a(h\w��@CBCi���Ƥ.\ϗ��+Xrp�ϖf_#�R=�^�cL8�|L-{�ZkЊD}	KaACK1w�G��/��I �'��:wA�8Q�(<�5��7 E~z1����}�J�����S�d`�o[X>��#�v���Ro�N�     
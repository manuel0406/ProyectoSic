PGDMP     :        	        	    {            3DMaker    14.8    14.8     �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    17327    3DMaker    DATABASE     e   CREATE DATABASE "3DMaker" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Spanish_Spain.1252';
    DROP DATABASE "3DMaker";
                postgres    false            �            1259    17481    ajustebalancecomprobacion    TABLE     o   CREATE TABLE public.ajustebalancecomprobacion (
    idajustec integer NOT NULL,
    idestadocapital integer
);
 -   DROP TABLE public.ajustebalancecomprobacion;
       public         heap    postgres    false            �            1259    17480 '   ajustebalancecomprobacion_idajustec_seq    SEQUENCE     �   CREATE SEQUENCE public.ajustebalancecomprobacion_idajustec_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 >   DROP SEQUENCE public.ajustebalancecomprobacion_idajustec_seq;
       public          postgres    false    210            �           0    0 '   ajustebalancecomprobacion_idajustec_seq    SEQUENCE OWNED BY     s   ALTER SEQUENCE public.ajustebalancecomprobacion_idajustec_seq OWNED BY public.ajustebalancecomprobacion.idajustec;
          public          postgres    false    209            �            1259    17488    balancecomprobacion    TABLE     �   CREATE TABLE public.balancecomprobacion (
    idbalancec integer NOT NULL,
    idajustec integer,
    idestadoresultado integer,
    idbalanceg integer,
    saldoacredor real,
    saldodeudor real,
    equilibrio boolean
);
 '   DROP TABLE public.balancecomprobacion;
       public         heap    postgres    false            �            1259    17487 "   balancecomprobacion_idbalancec_seq    SEQUENCE     �   CREATE SEQUENCE public.balancecomprobacion_idbalancec_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 9   DROP SEQUENCE public.balancecomprobacion_idbalancec_seq;
       public          postgres    false    212            �           0    0 "   balancecomprobacion_idbalancec_seq    SEQUENCE OWNED BY     i   ALTER SEQUENCE public.balancecomprobacion_idbalancec_seq OWNED BY public.balancecomprobacion.idbalancec;
          public          postgres    false    211            �            1259    17495    balancegeneral    TABLE     �   CREATE TABLE public.balancegeneral (
    idbalanceg integer NOT NULL,
    idrepoter integer,
    totalactivos real,
    totalpasivos real,
    totalcapital real
);
 "   DROP TABLE public.balancegeneral;
       public         heap    postgres    false            �            1259    17494    balancegeneral_idbalanceg_seq    SEQUENCE     �   CREATE SEQUENCE public.balancegeneral_idbalanceg_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 4   DROP SEQUENCE public.balancegeneral_idbalanceg_seq;
       public          postgres    false    214            �           0    0    balancegeneral_idbalanceg_seq    SEQUENCE OWNED BY     _   ALTER SEQUENCE public.balancegeneral_idbalanceg_seq OWNED BY public.balancegeneral.idbalanceg;
          public          postgres    false    213            �            1259    17502    catalogocuenta    TABLE     }   CREATE TABLE public.catalogocuenta (
    idcatalgo integer NOT NULL,
    idcuenta integer,
    nombrecuenta character(10)
);
 "   DROP TABLE public.catalogocuenta;
       public         heap    postgres    false            �            1259    17501    catalogocuenta_idcatalgo_seq    SEQUENCE     �   CREATE SEQUENCE public.catalogocuenta_idcatalgo_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 3   DROP SEQUENCE public.catalogocuenta_idcatalgo_seq;
       public          postgres    false    216            �           0    0    catalogocuenta_idcatalgo_seq    SEQUENCE OWNED BY     ]   ALTER SEQUENCE public.catalogocuenta_idcatalgo_seq OWNED BY public.catalogocuenta.idcatalgo;
          public          postgres    false    215            �            1259    17509    compra    TABLE     �   CREATE TABLE public.compra (
    idcompra integer NOT NULL,
    idinventario integer,
    idproducto integer,
    idtransaccion integer,
    cantidadcompra integer,
    montocompra real
);
    DROP TABLE public.compra;
       public         heap    postgres    false            �            1259    17508    compra_idcompra_seq    SEQUENCE     �   CREATE SEQUENCE public.compra_idcompra_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.compra_idcompra_seq;
       public          postgres    false    218            �           0    0    compra_idcompra_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE public.compra_idcompra_seq OWNED BY public.compra.idcompra;
          public          postgres    false    217            �            1259    17516    cuenta    TABLE     m   CREATE TABLE public.cuenta (
    idcuenta integer NOT NULL,
    idbalancec integer,
    totalizacion real
);
    DROP TABLE public.cuenta;
       public         heap    postgres    false            �            1259    17515    cuenta_idcuenta_seq    SEQUENCE     �   CREATE SEQUENCE public.cuenta_idcuenta_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.cuenta_idcuenta_seq;
       public          postgres    false    220            �           0    0    cuenta_idcuenta_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE public.cuenta_idcuenta_seq OWNED BY public.cuenta.idcuenta;
          public          postgres    false    219            �            1259    17523    estadocapital    TABLE     z   CREATE TABLE public.estadocapital (
    idestadocapital integer NOT NULL,
    idrepoter integer,
    nuevocapital real
);
 !   DROP TABLE public.estadocapital;
       public         heap    postgres    false            �            1259    17522 !   estadocapital_idestadocapital_seq    SEQUENCE     �   CREATE SEQUENCE public.estadocapital_idestadocapital_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 8   DROP SEQUENCE public.estadocapital_idestadocapital_seq;
       public          postgres    false    222            �           0    0 !   estadocapital_idestadocapital_seq    SEQUENCE OWNED BY     g   ALTER SEQUENCE public.estadocapital_idestadocapital_seq OWNED BY public.estadocapital.idestadocapital;
          public          postgres    false    221            �            1259    17530    estadoderesultado    TABLE     �   CREATE TABLE public.estadoderesultado (
    idestadoresultado integer NOT NULL,
    idrepoter integer,
    utilidadbruta real,
    utilidadoperacion real,
    utilidadantesimpuesto real,
    utilidadneta real
);
 %   DROP TABLE public.estadoderesultado;
       public         heap    postgres    false            �            1259    17529 '   estadoderesultado_idestadoresultado_seq    SEQUENCE     �   CREATE SEQUENCE public.estadoderesultado_idestadoresultado_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 >   DROP SEQUENCE public.estadoderesultado_idestadoresultado_seq;
       public          postgres    false    224            �           0    0 '   estadoderesultado_idestadoresultado_seq    SEQUENCE OWNED BY     s   ALTER SEQUENCE public.estadoderesultado_idestadoresultado_seq OWNED BY public.estadoderesultado.idestadoresultado;
          public          postgres    false    223            �            1259    17537    impuesto    TABLE     �   CREATE TABLE public.impuesto (
    idimpuesto integer NOT NULL,
    idtransaccion integer,
    nombreimpuesto character(10),
    porcentaje real
);
    DROP TABLE public.impuesto;
       public         heap    postgres    false            �            1259    17536    impuesto_idimpuesto_seq    SEQUENCE     �   CREATE SEQUENCE public.impuesto_idimpuesto_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 .   DROP SEQUENCE public.impuesto_idimpuesto_seq;
       public          postgres    false    226            �           0    0    impuesto_idimpuesto_seq    SEQUENCE OWNED BY     S   ALTER SEQUENCE public.impuesto_idimpuesto_seq OWNED BY public.impuesto.idimpuesto;
          public          postgres    false    225            �            1259    17544 
   inventario    TABLE     {   CREATE TABLE public.inventario (
    idinventario integer NOT NULL,
    cantidadinvetairo integer,
    preciototal real
);
    DROP TABLE public.inventario;
       public         heap    postgres    false            �            1259    17543    inventario_idinventario_seq    SEQUENCE     �   CREATE SEQUENCE public.inventario_idinventario_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 2   DROP SEQUENCE public.inventario_idinventario_seq;
       public          postgres    false    228            �           0    0    inventario_idinventario_seq    SEQUENCE OWNED BY     [   ALTER SEQUENCE public.inventario_idinventario_seq OWNED BY public.inventario.idinventario;
          public          postgres    false    227            �            1259    17551    periodocontable    TABLE     �   CREATE TABLE public.periodocontable (
    idperiodo integer NOT NULL,
    idrepoter integer,
    inicio character varying(10),
    fin character varying(10)
);
 #   DROP TABLE public.periodocontable;
       public         heap    postgres    false            �            1259    17550    periodocontable_idperiodo_seq    SEQUENCE     �   CREATE SEQUENCE public.periodocontable_idperiodo_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 4   DROP SEQUENCE public.periodocontable_idperiodo_seq;
       public          postgres    false    230            �           0    0    periodocontable_idperiodo_seq    SEQUENCE OWNED BY     _   ALTER SEQUENCE public.periodocontable_idperiodo_seq OWNED BY public.periodocontable.idperiodo;
          public          postgres    false    229            �            1259    17558    producto    TABLE     �   CREATE TABLE public.producto (
    idproducto integer NOT NULL,
    idinventario integer,
    nombreproduto character(10),
    preciounitario real
);
    DROP TABLE public.producto;
       public         heap    postgres    false            �            1259    17557    producto_idproducto_seq    SEQUENCE     �   CREATE SEQUENCE public.producto_idproducto_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 .   DROP SEQUENCE public.producto_idproducto_seq;
       public          postgres    false    232            �           0    0    producto_idproducto_seq    SEQUENCE OWNED BY     S   ALTER SEQUENCE public.producto_idproducto_seq OWNED BY public.producto.idproducto;
          public          postgres    false    231            �            1259    17565    reporte    TABLE     r   CREATE TABLE public.reporte (
    idrepoter integer NOT NULL,
    firma character(10),
    autor character(10)
);
    DROP TABLE public.reporte;
       public         heap    postgres    false            �            1259    17564    reporte_idrepoter_seq    SEQUENCE     �   CREATE SEQUENCE public.reporte_idrepoter_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ,   DROP SEQUENCE public.reporte_idrepoter_seq;
       public          postgres    false    234            �           0    0    reporte_idrepoter_seq    SEQUENCE OWNED BY     O   ALTER SEQUENCE public.reporte_idrepoter_seq OWNED BY public.reporte.idrepoter;
          public          postgres    false    233            �            1259    17572    transaccion    TABLE     y   CREATE TABLE public.transaccion (
    idtransaccion integer NOT NULL,
    idcuenta integer,
    totaltransaccion real
);
    DROP TABLE public.transaccion;
       public         heap    postgres    false            �            1259    17571    transaccion_idtransaccion_seq    SEQUENCE     �   CREATE SEQUENCE public.transaccion_idtransaccion_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 4   DROP SEQUENCE public.transaccion_idtransaccion_seq;
       public          postgres    false    236            �           0    0    transaccion_idtransaccion_seq    SEQUENCE OWNED BY     _   ALTER SEQUENCE public.transaccion_idtransaccion_seq OWNED BY public.transaccion.idtransaccion;
          public          postgres    false    235            �            1259    17579    venta    TABLE     �   CREATE TABLE public.venta (
    idventa integer NOT NULL,
    idinventario integer,
    idtransaccion integer,
    totalventa real
);
    DROP TABLE public.venta;
       public         heap    postgres    false            �            1259    17578    venta_idventa_seq    SEQUENCE     �   CREATE SEQUENCE public.venta_idventa_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE public.venta_idventa_seq;
       public          postgres    false    238            �           0    0    venta_idventa_seq    SEQUENCE OWNED BY     G   ALTER SEQUENCE public.venta_idventa_seq OWNED BY public.venta.idventa;
          public          postgres    false    237            �           2604    17484 #   ajustebalancecomprobacion idajustec    DEFAULT     �   ALTER TABLE ONLY public.ajustebalancecomprobacion ALTER COLUMN idajustec SET DEFAULT nextval('public.ajustebalancecomprobacion_idajustec_seq'::regclass);
 R   ALTER TABLE public.ajustebalancecomprobacion ALTER COLUMN idajustec DROP DEFAULT;
       public          postgres    false    210    209    210            �           2604    17491    balancecomprobacion idbalancec    DEFAULT     �   ALTER TABLE ONLY public.balancecomprobacion ALTER COLUMN idbalancec SET DEFAULT nextval('public.balancecomprobacion_idbalancec_seq'::regclass);
 M   ALTER TABLE public.balancecomprobacion ALTER COLUMN idbalancec DROP DEFAULT;
       public          postgres    false    212    211    212            �           2604    17498    balancegeneral idbalanceg    DEFAULT     �   ALTER TABLE ONLY public.balancegeneral ALTER COLUMN idbalanceg SET DEFAULT nextval('public.balancegeneral_idbalanceg_seq'::regclass);
 H   ALTER TABLE public.balancegeneral ALTER COLUMN idbalanceg DROP DEFAULT;
       public          postgres    false    213    214    214            �           2604    17505    catalogocuenta idcatalgo    DEFAULT     �   ALTER TABLE ONLY public.catalogocuenta ALTER COLUMN idcatalgo SET DEFAULT nextval('public.catalogocuenta_idcatalgo_seq'::regclass);
 G   ALTER TABLE public.catalogocuenta ALTER COLUMN idcatalgo DROP DEFAULT;
       public          postgres    false    216    215    216            �           2604    17512    compra idcompra    DEFAULT     r   ALTER TABLE ONLY public.compra ALTER COLUMN idcompra SET DEFAULT nextval('public.compra_idcompra_seq'::regclass);
 >   ALTER TABLE public.compra ALTER COLUMN idcompra DROP DEFAULT;
       public          postgres    false    218    217    218            �           2604    17519    cuenta idcuenta    DEFAULT     r   ALTER TABLE ONLY public.cuenta ALTER COLUMN idcuenta SET DEFAULT nextval('public.cuenta_idcuenta_seq'::regclass);
 >   ALTER TABLE public.cuenta ALTER COLUMN idcuenta DROP DEFAULT;
       public          postgres    false    220    219    220            �           2604    17526    estadocapital idestadocapital    DEFAULT     �   ALTER TABLE ONLY public.estadocapital ALTER COLUMN idestadocapital SET DEFAULT nextval('public.estadocapital_idestadocapital_seq'::regclass);
 L   ALTER TABLE public.estadocapital ALTER COLUMN idestadocapital DROP DEFAULT;
       public          postgres    false    221    222    222            �           2604    17533 #   estadoderesultado idestadoresultado    DEFAULT     �   ALTER TABLE ONLY public.estadoderesultado ALTER COLUMN idestadoresultado SET DEFAULT nextval('public.estadoderesultado_idestadoresultado_seq'::regclass);
 R   ALTER TABLE public.estadoderesultado ALTER COLUMN idestadoresultado DROP DEFAULT;
       public          postgres    false    224    223    224            �           2604    17540    impuesto idimpuesto    DEFAULT     z   ALTER TABLE ONLY public.impuesto ALTER COLUMN idimpuesto SET DEFAULT nextval('public.impuesto_idimpuesto_seq'::regclass);
 B   ALTER TABLE public.impuesto ALTER COLUMN idimpuesto DROP DEFAULT;
       public          postgres    false    226    225    226            �           2604    17547    inventario idinventario    DEFAULT     �   ALTER TABLE ONLY public.inventario ALTER COLUMN idinventario SET DEFAULT nextval('public.inventario_idinventario_seq'::regclass);
 F   ALTER TABLE public.inventario ALTER COLUMN idinventario DROP DEFAULT;
       public          postgres    false    228    227    228            �           2604    17554    periodocontable idperiodo    DEFAULT     �   ALTER TABLE ONLY public.periodocontable ALTER COLUMN idperiodo SET DEFAULT nextval('public.periodocontable_idperiodo_seq'::regclass);
 H   ALTER TABLE public.periodocontable ALTER COLUMN idperiodo DROP DEFAULT;
       public          postgres    false    230    229    230            �           2604    17561    producto idproducto    DEFAULT     z   ALTER TABLE ONLY public.producto ALTER COLUMN idproducto SET DEFAULT nextval('public.producto_idproducto_seq'::regclass);
 B   ALTER TABLE public.producto ALTER COLUMN idproducto DROP DEFAULT;
       public          postgres    false    231    232    232            �           2604    17568    reporte idrepoter    DEFAULT     v   ALTER TABLE ONLY public.reporte ALTER COLUMN idrepoter SET DEFAULT nextval('public.reporte_idrepoter_seq'::regclass);
 @   ALTER TABLE public.reporte ALTER COLUMN idrepoter DROP DEFAULT;
       public          postgres    false    233    234    234            �           2604    17575    transaccion idtransaccion    DEFAULT     �   ALTER TABLE ONLY public.transaccion ALTER COLUMN idtransaccion SET DEFAULT nextval('public.transaccion_idtransaccion_seq'::regclass);
 H   ALTER TABLE public.transaccion ALTER COLUMN idtransaccion DROP DEFAULT;
       public          postgres    false    236    235    236            �           2604    17582    venta idventa    DEFAULT     n   ALTER TABLE ONLY public.venta ALTER COLUMN idventa SET DEFAULT nextval('public.venta_idventa_seq'::regclass);
 <   ALTER TABLE public.venta ALTER COLUMN idventa DROP DEFAULT;
       public          postgres    false    238    237    238            m          0    17481    ajustebalancecomprobacion 
   TABLE DATA           O   COPY public.ajustebalancecomprobacion (idajustec, idestadocapital) FROM stdin;
    public          postgres    false    210   ��       o          0    17488    balancecomprobacion 
   TABLE DATA           �   COPY public.balancecomprobacion (idbalancec, idajustec, idestadoresultado, idbalanceg, saldoacredor, saldodeudor, equilibrio) FROM stdin;
    public          postgres    false    212   Ԝ       q          0    17495    balancegeneral 
   TABLE DATA           i   COPY public.balancegeneral (idbalanceg, idrepoter, totalactivos, totalpasivos, totalcapital) FROM stdin;
    public          postgres    false    214   �       s          0    17502    catalogocuenta 
   TABLE DATA           K   COPY public.catalogocuenta (idcatalgo, idcuenta, nombrecuenta) FROM stdin;
    public          postgres    false    216   �       u          0    17509    compra 
   TABLE DATA           p   COPY public.compra (idcompra, idinventario, idproducto, idtransaccion, cantidadcompra, montocompra) FROM stdin;
    public          postgres    false    218   +�       w          0    17516    cuenta 
   TABLE DATA           D   COPY public.cuenta (idcuenta, idbalancec, totalizacion) FROM stdin;
    public          postgres    false    220   H�       y          0    17523    estadocapital 
   TABLE DATA           Q   COPY public.estadocapital (idestadocapital, idrepoter, nuevocapital) FROM stdin;
    public          postgres    false    222   e�       {          0    17530    estadoderesultado 
   TABLE DATA           �   COPY public.estadoderesultado (idestadoresultado, idrepoter, utilidadbruta, utilidadoperacion, utilidadantesimpuesto, utilidadneta) FROM stdin;
    public          postgres    false    224   ��       }          0    17537    impuesto 
   TABLE DATA           Y   COPY public.impuesto (idimpuesto, idtransaccion, nombreimpuesto, porcentaje) FROM stdin;
    public          postgres    false    226   ��                 0    17544 
   inventario 
   TABLE DATA           R   COPY public.inventario (idinventario, cantidadinvetairo, preciototal) FROM stdin;
    public          postgres    false    228   ��       �          0    17551    periodocontable 
   TABLE DATA           L   COPY public.periodocontable (idperiodo, idrepoter, inicio, fin) FROM stdin;
    public          postgres    false    230   ٝ       �          0    17558    producto 
   TABLE DATA           [   COPY public.producto (idproducto, idinventario, nombreproduto, preciounitario) FROM stdin;
    public          postgres    false    232   ��       �          0    17565    reporte 
   TABLE DATA           :   COPY public.reporte (idrepoter, firma, autor) FROM stdin;
    public          postgres    false    234   �       �          0    17572    transaccion 
   TABLE DATA           P   COPY public.transaccion (idtransaccion, idcuenta, totaltransaccion) FROM stdin;
    public          postgres    false    236   0�       �          0    17579    venta 
   TABLE DATA           Q   COPY public.venta (idventa, idinventario, idtransaccion, totalventa) FROM stdin;
    public          postgres    false    238   M�       �           0    0 '   ajustebalancecomprobacion_idajustec_seq    SEQUENCE SET     V   SELECT pg_catalog.setval('public.ajustebalancecomprobacion_idajustec_seq', 1, false);
          public          postgres    false    209            �           0    0 "   balancecomprobacion_idbalancec_seq    SEQUENCE SET     Q   SELECT pg_catalog.setval('public.balancecomprobacion_idbalancec_seq', 1, false);
          public          postgres    false    211            �           0    0    balancegeneral_idbalanceg_seq    SEQUENCE SET     L   SELECT pg_catalog.setval('public.balancegeneral_idbalanceg_seq', 1, false);
          public          postgres    false    213            �           0    0    catalogocuenta_idcatalgo_seq    SEQUENCE SET     K   SELECT pg_catalog.setval('public.catalogocuenta_idcatalgo_seq', 1, false);
          public          postgres    false    215            �           0    0    compra_idcompra_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.compra_idcompra_seq', 1, false);
          public          postgres    false    217            �           0    0    cuenta_idcuenta_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.cuenta_idcuenta_seq', 1, false);
          public          postgres    false    219            �           0    0 !   estadocapital_idestadocapital_seq    SEQUENCE SET     P   SELECT pg_catalog.setval('public.estadocapital_idestadocapital_seq', 1, false);
          public          postgres    false    221            �           0    0 '   estadoderesultado_idestadoresultado_seq    SEQUENCE SET     V   SELECT pg_catalog.setval('public.estadoderesultado_idestadoresultado_seq', 1, false);
          public          postgres    false    223            �           0    0    impuesto_idimpuesto_seq    SEQUENCE SET     F   SELECT pg_catalog.setval('public.impuesto_idimpuesto_seq', 1, false);
          public          postgres    false    225            �           0    0    inventario_idinventario_seq    SEQUENCE SET     J   SELECT pg_catalog.setval('public.inventario_idinventario_seq', 1, false);
          public          postgres    false    227            �           0    0    periodocontable_idperiodo_seq    SEQUENCE SET     L   SELECT pg_catalog.setval('public.periodocontable_idperiodo_seq', 1, false);
          public          postgres    false    229            �           0    0    producto_idproducto_seq    SEQUENCE SET     F   SELECT pg_catalog.setval('public.producto_idproducto_seq', 1, false);
          public          postgres    false    231            �           0    0    reporte_idrepoter_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('public.reporte_idrepoter_seq', 1, false);
          public          postgres    false    233            �           0    0    transaccion_idtransaccion_seq    SEQUENCE SET     L   SELECT pg_catalog.setval('public.transaccion_idtransaccion_seq', 1, false);
          public          postgres    false    235            �           0    0    venta_idventa_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public.venta_idventa_seq', 1, false);
          public          postgres    false    237            �           2606    17486 8   ajustebalancecomprobacion ajustebalancecomprobacion_pkey 
   CONSTRAINT     }   ALTER TABLE ONLY public.ajustebalancecomprobacion
    ADD CONSTRAINT ajustebalancecomprobacion_pkey PRIMARY KEY (idajustec);
 b   ALTER TABLE ONLY public.ajustebalancecomprobacion DROP CONSTRAINT ajustebalancecomprobacion_pkey;
       public            postgres    false    210            �           2606    17493 ,   balancecomprobacion balancecomprobacion_pkey 
   CONSTRAINT     r   ALTER TABLE ONLY public.balancecomprobacion
    ADD CONSTRAINT balancecomprobacion_pkey PRIMARY KEY (idbalancec);
 V   ALTER TABLE ONLY public.balancecomprobacion DROP CONSTRAINT balancecomprobacion_pkey;
       public            postgres    false    212            �           2606    17500 "   balancegeneral balancegeneral_pkey 
   CONSTRAINT     h   ALTER TABLE ONLY public.balancegeneral
    ADD CONSTRAINT balancegeneral_pkey PRIMARY KEY (idbalanceg);
 L   ALTER TABLE ONLY public.balancegeneral DROP CONSTRAINT balancegeneral_pkey;
       public            postgres    false    214            �           2606    17507 "   catalogocuenta catalogocuenta_pkey 
   CONSTRAINT     g   ALTER TABLE ONLY public.catalogocuenta
    ADD CONSTRAINT catalogocuenta_pkey PRIMARY KEY (idcatalgo);
 L   ALTER TABLE ONLY public.catalogocuenta DROP CONSTRAINT catalogocuenta_pkey;
       public            postgres    false    216            �           2606    17514    compra compra_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.compra
    ADD CONSTRAINT compra_pkey PRIMARY KEY (idcompra);
 <   ALTER TABLE ONLY public.compra DROP CONSTRAINT compra_pkey;
       public            postgres    false    218            �           2606    17521    cuenta cuenta_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.cuenta
    ADD CONSTRAINT cuenta_pkey PRIMARY KEY (idcuenta);
 <   ALTER TABLE ONLY public.cuenta DROP CONSTRAINT cuenta_pkey;
       public            postgres    false    220            �           2606    17528     estadocapital estadocapital_pkey 
   CONSTRAINT     k   ALTER TABLE ONLY public.estadocapital
    ADD CONSTRAINT estadocapital_pkey PRIMARY KEY (idestadocapital);
 J   ALTER TABLE ONLY public.estadocapital DROP CONSTRAINT estadocapital_pkey;
       public            postgres    false    222            �           2606    17535 (   estadoderesultado estadoderesultado_pkey 
   CONSTRAINT     u   ALTER TABLE ONLY public.estadoderesultado
    ADD CONSTRAINT estadoderesultado_pkey PRIMARY KEY (idestadoresultado);
 R   ALTER TABLE ONLY public.estadoderesultado DROP CONSTRAINT estadoderesultado_pkey;
       public            postgres    false    224            �           2606    17542    impuesto impuesto_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.impuesto
    ADD CONSTRAINT impuesto_pkey PRIMARY KEY (idimpuesto);
 @   ALTER TABLE ONLY public.impuesto DROP CONSTRAINT impuesto_pkey;
       public            postgres    false    226            �           2606    17549    inventario inventario_pkey 
   CONSTRAINT     b   ALTER TABLE ONLY public.inventario
    ADD CONSTRAINT inventario_pkey PRIMARY KEY (idinventario);
 D   ALTER TABLE ONLY public.inventario DROP CONSTRAINT inventario_pkey;
       public            postgres    false    228            �           2606    17556 $   periodocontable periodocontable_pkey 
   CONSTRAINT     i   ALTER TABLE ONLY public.periodocontable
    ADD CONSTRAINT periodocontable_pkey PRIMARY KEY (idperiodo);
 N   ALTER TABLE ONLY public.periodocontable DROP CONSTRAINT periodocontable_pkey;
       public            postgres    false    230            �           2606    17563    producto producto_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.producto
    ADD CONSTRAINT producto_pkey PRIMARY KEY (idproducto);
 @   ALTER TABLE ONLY public.producto DROP CONSTRAINT producto_pkey;
       public            postgres    false    232            �           2606    17570    reporte reporte_pkey 
   CONSTRAINT     Y   ALTER TABLE ONLY public.reporte
    ADD CONSTRAINT reporte_pkey PRIMARY KEY (idrepoter);
 >   ALTER TABLE ONLY public.reporte DROP CONSTRAINT reporte_pkey;
       public            postgres    false    234            �           2606    17577    transaccion transaccion_pkey 
   CONSTRAINT     e   ALTER TABLE ONLY public.transaccion
    ADD CONSTRAINT transaccion_pkey PRIMARY KEY (idtransaccion);
 F   ALTER TABLE ONLY public.transaccion DROP CONSTRAINT transaccion_pkey;
       public            postgres    false    236            �           2606    17584    venta venta_pkey 
   CONSTRAINT     S   ALTER TABLE ONLY public.venta
    ADD CONSTRAINT venta_pkey PRIMARY KEY (idventa);
 :   ALTER TABLE ONLY public.venta DROP CONSTRAINT venta_pkey;
       public            postgres    false    238            �           2606    17585 7   ajustebalancecomprobacion fk_ajusteba_requiere_estadoca    FK CONSTRAINT     �   ALTER TABLE ONLY public.ajustebalancecomprobacion
    ADD CONSTRAINT fk_ajusteba_requiere_estadoca FOREIGN KEY (idestadocapital) REFERENCES public.estadocapital(idestadocapital);
 a   ALTER TABLE ONLY public.ajustebalancecomprobacion DROP CONSTRAINT fk_ajusteba_requiere_estadoca;
       public          postgres    false    3262    210    222            �           2606    17590 /   balancecomprobacion fk_balancec_ajuste_ajusteba    FK CONSTRAINT     �   ALTER TABLE ONLY public.balancecomprobacion
    ADD CONSTRAINT fk_balancec_ajuste_ajusteba FOREIGN KEY (idajustec) REFERENCES public.ajustebalancecomprobacion(idajustec);
 Y   ALTER TABLE ONLY public.balancecomprobacion DROP CONSTRAINT fk_balancec_ajuste_ajusteba;
       public          postgres    false    210    212    3250            �           2606    17595 /   balancecomprobacion fk_balancec_meneja_balanceg    FK CONSTRAINT     �   ALTER TABLE ONLY public.balancecomprobacion
    ADD CONSTRAINT fk_balancec_meneja_balanceg FOREIGN KEY (idbalanceg) REFERENCES public.balancegeneral(idbalanceg);
 Y   ALTER TABLE ONLY public.balancecomprobacion DROP CONSTRAINT fk_balancec_meneja_balanceg;
       public          postgres    false    214    3254    212            �           2606    17600 1   balancecomprobacion fk_balancec_uitiliza_estadode    FK CONSTRAINT     �   ALTER TABLE ONLY public.balancecomprobacion
    ADD CONSTRAINT fk_balancec_uitiliza_estadode FOREIGN KEY (idestadoresultado) REFERENCES public.estadoderesultado(idestadoresultado);
 [   ALTER TABLE ONLY public.balancecomprobacion DROP CONSTRAINT fk_balancec_uitiliza_estadode;
       public          postgres    false    212    3264    224            �           2606    17605 ,   balancegeneral fk_balanceg_comprende_reporte    FK CONSTRAINT     �   ALTER TABLE ONLY public.balancegeneral
    ADD CONSTRAINT fk_balanceg_comprende_reporte FOREIGN KEY (idrepoter) REFERENCES public.reporte(idrepoter);
 V   ALTER TABLE ONLY public.balancegeneral DROP CONSTRAINT fk_balanceg_comprende_reporte;
       public          postgres    false    234    3274    214            �           2606    17610 *   catalogocuenta fk_catalogo_petenece_cuenta    FK CONSTRAINT     �   ALTER TABLE ONLY public.catalogocuenta
    ADD CONSTRAINT fk_catalogo_petenece_cuenta FOREIGN KEY (idcuenta) REFERENCES public.cuenta(idcuenta);
 T   ALTER TABLE ONLY public.catalogocuenta DROP CONSTRAINT fk_catalogo_petenece_cuenta;
       public          postgres    false    3260    216    220            �           2606    17615 !   compra fk_compra_aumenta_inventar    FK CONSTRAINT     �   ALTER TABLE ONLY public.compra
    ADD CONSTRAINT fk_compra_aumenta_inventar FOREIGN KEY (idinventario) REFERENCES public.inventario(idinventario);
 K   ALTER TABLE ONLY public.compra DROP CONSTRAINT fk_compra_aumenta_inventar;
       public          postgres    false    218    228    3268            �           2606    17620     compra fk_compra_es_una_transacc    FK CONSTRAINT     �   ALTER TABLE ONLY public.compra
    ADD CONSTRAINT fk_compra_es_una_transacc FOREIGN KEY (idtransaccion) REFERENCES public.transaccion(idtransaccion);
 J   ALTER TABLE ONLY public.compra DROP CONSTRAINT fk_compra_es_una_transacc;
       public          postgres    false    236    3276    218            �           2606    17625 !   compra fk_compra_obtiene_producto    FK CONSTRAINT     �   ALTER TABLE ONLY public.compra
    ADD CONSTRAINT fk_compra_obtiene_producto FOREIGN KEY (idproducto) REFERENCES public.producto(idproducto);
 K   ALTER TABLE ONLY public.compra DROP CONSTRAINT fk_compra_obtiene_producto;
       public          postgres    false    232    3272    218            �           2606    17630    cuenta fk_cuenta_tiene_balancec    FK CONSTRAINT     �   ALTER TABLE ONLY public.cuenta
    ADD CONSTRAINT fk_cuenta_tiene_balancec FOREIGN KEY (idbalancec) REFERENCES public.balancecomprobacion(idbalancec);
 I   ALTER TABLE ONLY public.cuenta DROP CONSTRAINT fk_cuenta_tiene_balancec;
       public          postgres    false    212    3252    220            �           2606    17635 *   estadocapital fk_estadoca_contiene_reporte    FK CONSTRAINT     �   ALTER TABLE ONLY public.estadocapital
    ADD CONSTRAINT fk_estadoca_contiene_reporte FOREIGN KEY (idrepoter) REFERENCES public.reporte(idrepoter);
 T   ALTER TABLE ONLY public.estadocapital DROP CONSTRAINT fk_estadoca_contiene_reporte;
       public          postgres    false    234    222    3274            �           2606    17640 .   estadoderesultado fk_estadode_presenta_reporte    FK CONSTRAINT     �   ALTER TABLE ONLY public.estadoderesultado
    ADD CONSTRAINT fk_estadode_presenta_reporte FOREIGN KEY (idrepoter) REFERENCES public.reporte(idrepoter);
 X   ALTER TABLE ONLY public.estadoderesultado DROP CONSTRAINT fk_estadode_presenta_reporte;
       public          postgres    false    234    224    3274            �           2606    17645 $   impuesto fk_impuesto_existe_transacc    FK CONSTRAINT     �   ALTER TABLE ONLY public.impuesto
    ADD CONSTRAINT fk_impuesto_existe_transacc FOREIGN KEY (idtransaccion) REFERENCES public.transaccion(idtransaccion);
 N   ALTER TABLE ONLY public.impuesto DROP CONSTRAINT fk_impuesto_existe_transacc;
       public          postgres    false    3276    226    236            �           2606    17650 ,   periodocontable fk_periodoc_necesita_reporte    FK CONSTRAINT     �   ALTER TABLE ONLY public.periodocontable
    ADD CONSTRAINT fk_periodoc_necesita_reporte FOREIGN KEY (idrepoter) REFERENCES public.reporte(idrepoter);
 V   ALTER TABLE ONLY public.periodocontable DROP CONSTRAINT fk_periodoc_necesita_reporte;
       public          postgres    false    234    3274    230            �           2606    17655 #   producto fk_producto_posee_inventar    FK CONSTRAINT     �   ALTER TABLE ONLY public.producto
    ADD CONSTRAINT fk_producto_posee_inventar FOREIGN KEY (idinventario) REFERENCES public.inventario(idinventario);
 M   ALTER TABLE ONLY public.producto DROP CONSTRAINT fk_producto_posee_inventar;
       public          postgres    false    3268    228    232            �           2606    17660 (   transaccion fk_transacc_se_compon_cuenta    FK CONSTRAINT     �   ALTER TABLE ONLY public.transaccion
    ADD CONSTRAINT fk_transacc_se_compon_cuenta FOREIGN KEY (idcuenta) REFERENCES public.cuenta(idcuenta);
 R   ALTER TABLE ONLY public.transaccion DROP CONSTRAINT fk_transacc_se_compon_cuenta;
       public          postgres    false    3260    220    236            �           2606    17665 !   venta fk_venta_descuenta_inventar    FK CONSTRAINT     �   ALTER TABLE ONLY public.venta
    ADD CONSTRAINT fk_venta_descuenta_inventar FOREIGN KEY (idinventario) REFERENCES public.inventario(idinventario);
 K   ALTER TABLE ONLY public.venta DROP CONSTRAINT fk_venta_descuenta_inventar;
       public          postgres    false    238    228    3268            �           2606    17670 !   venta fk_venta_se_convie_transacc    FK CONSTRAINT     �   ALTER TABLE ONLY public.venta
    ADD CONSTRAINT fk_venta_se_convie_transacc FOREIGN KEY (idtransaccion) REFERENCES public.transaccion(idtransaccion);
 K   ALTER TABLE ONLY public.venta DROP CONSTRAINT fk_venta_se_convie_transacc;
       public          postgres    false    3276    238    236            m      x������ � �      o      x������ � �      q      x������ � �      s      x������ � �      u      x������ � �      w      x������ � �      y      x������ � �      {      x������ � �      }      x������ � �            x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �     
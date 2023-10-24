PGDMP     :                	    {            dmakers    14.8    14.8 �    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    19549    dmakers    DATABASE     c   CREATE DATABASE dmakers WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Spanish_Spain.1252';
    DROP DATABASE dmakers;
                dmakers    false            �           0    0    DATABASE dmakers    ACL     i   REVOKE CONNECT,TEMPORARY ON DATABASE dmakers FROM PUBLIC;
GRANT TEMPORARY ON DATABASE dmakers TO PUBLIC;
                   dmakers    false    3520            �            1259    19551    ajuste    TABLE     T   CREATE TABLE public.ajuste (
    idajuste integer NOT NULL,
    idcuenta integer
);
    DROP TABLE public.ajuste;
       public         heap    dmakers    false            �            1259    19550    ajuste_idajuste_seq    SEQUENCE     �   CREATE SEQUENCE public.ajuste_idajuste_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.ajuste_idajuste_seq;
       public          dmakers    false    210            �           0    0    ajuste_idajuste_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE public.ajuste_idajuste_seq OWNED BY public.ajuste.idajuste;
          public          dmakers    false    209            �            1259    19560    ajustebalancecomprobacion    TABLE     �   CREATE TABLE public.ajustebalancecomprobacion (
    idajustec integer NOT NULL,
    idbalancec integer,
    idajuste integer
);
 -   DROP TABLE public.ajustebalancecomprobacion;
       public         heap    dmakers    false            �            1259    19559 '   ajustebalancecomprobacion_idajustec_seq    SEQUENCE     �   CREATE SEQUENCE public.ajustebalancecomprobacion_idajustec_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 >   DROP SEQUENCE public.ajustebalancecomprobacion_idajustec_seq;
       public          dmakers    false    212            �           0    0 '   ajustebalancecomprobacion_idajustec_seq    SEQUENCE OWNED BY     s   ALTER SEQUENCE public.ajustebalancecomprobacion_idajustec_seq OWNED BY public.ajustebalancecomprobacion.idajustec;
          public          dmakers    false    211            �            1259    19570    balancecomprobacion    TABLE     �   CREATE TABLE public.balancecomprobacion (
    idbalancec integer NOT NULL,
    idcuenta integer,
    saldoacredor real,
    saldodeudor real,
    equilibrio boolean
);
 '   DROP TABLE public.balancecomprobacion;
       public         heap    dmakers    false            �            1259    19569 "   balancecomprobacion_idbalancec_seq    SEQUENCE     �   CREATE SEQUENCE public.balancecomprobacion_idbalancec_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 9   DROP SEQUENCE public.balancecomprobacion_idbalancec_seq;
       public          dmakers    false    214            �           0    0 "   balancecomprobacion_idbalancec_seq    SEQUENCE OWNED BY     i   ALTER SEQUENCE public.balancecomprobacion_idbalancec_seq OWNED BY public.balancecomprobacion.idbalancec;
          public          dmakers    false    213            �            1259    19579    balancegeneral    TABLE     �   CREATE TABLE public.balancegeneral (
    idbalanceg integer NOT NULL,
    idajustec integer,
    totalactivos real,
    totalpasivos real,
    totalcapital real
);
 "   DROP TABLE public.balancegeneral;
       public         heap    dmakers    false            �            1259    19578    balancegeneral_idbalanceg_seq    SEQUENCE     �   CREATE SEQUENCE public.balancegeneral_idbalanceg_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 4   DROP SEQUENCE public.balancegeneral_idbalanceg_seq;
       public          dmakers    false    216            �           0    0    balancegeneral_idbalanceg_seq    SEQUENCE OWNED BY     _   ALTER SEQUENCE public.balancegeneral_idbalanceg_seq OWNED BY public.balancegeneral.idbalanceg;
          public          dmakers    false    215            �            1259    19587    catalogocuenta    TABLE     m   CREATE TABLE public.catalogocuenta (
    codigo integer NOT NULL,
    nombrecuenta character varying(100)
);
 "   DROP TABLE public.catalogocuenta;
       public         heap    dmakers    false            �            1259    19594    compra    TABLE     �   CREATE TABLE public.compra (
    idcompra integer NOT NULL,
    idproducto integer,
    cantidadcompra integer,
    montocompra real
);
    DROP TABLE public.compra;
       public         heap    dmakers    false            �            1259    19593    compra_idcompra_seq    SEQUENCE     �   CREATE SEQUENCE public.compra_idcompra_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.compra_idcompra_seq;
       public          dmakers    false    219            �           0    0    compra_idcompra_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE public.compra_idcompra_seq OWNED BY public.compra.idcompra;
          public          dmakers    false    218            �            1259    19603    cuenta    TABLE     �   CREATE TABLE public.cuenta (
    idcuenta integer NOT NULL,
    codigo integer,
    idtransaccion integer,
    totalizacion real
);
    DROP TABLE public.cuenta;
       public         heap    dmakers    false            �            1259    19602    cuenta_idcuenta_seq    SEQUENCE     �   CREATE SEQUENCE public.cuenta_idcuenta_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.cuenta_idcuenta_seq;
       public          dmakers    false    221            �           0    0    cuenta_idcuenta_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE public.cuenta_idcuenta_seq OWNED BY public.cuenta.idcuenta;
          public          dmakers    false    220            �            1259    19613    estadocapital    TABLE     z   CREATE TABLE public.estadocapital (
    idestadocapital integer NOT NULL,
    idajustec integer,
    nuevocapital real
);
 !   DROP TABLE public.estadocapital;
       public         heap    dmakers    false            �            1259    19612 !   estadocapital_idestadocapital_seq    SEQUENCE     �   CREATE SEQUENCE public.estadocapital_idestadocapital_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 8   DROP SEQUENCE public.estadocapital_idestadocapital_seq;
       public          dmakers    false    223            �           0    0 !   estadocapital_idestadocapital_seq    SEQUENCE OWNED BY     g   ALTER SEQUENCE public.estadocapital_idestadocapital_seq OWNED BY public.estadocapital.idestadocapital;
          public          dmakers    false    222            �            1259    19622    estadoderesultado    TABLE     �   CREATE TABLE public.estadoderesultado (
    idestadoresultado integer NOT NULL,
    idajustec integer,
    utilidadbruta real,
    utilidadoperacion real,
    utilidadantesimpuesto real,
    utilidadneta real
);
 %   DROP TABLE public.estadoderesultado;
       public         heap    dmakers    false            �            1259    19621 '   estadoderesultado_idestadoresultado_seq    SEQUENCE     �   CREATE SEQUENCE public.estadoderesultado_idestadoresultado_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 >   DROP SEQUENCE public.estadoderesultado_idestadoresultado_seq;
       public          dmakers    false    225            �           0    0 '   estadoderesultado_idestadoresultado_seq    SEQUENCE OWNED BY     s   ALTER SEQUENCE public.estadoderesultado_idestadoresultado_seq OWNED BY public.estadoderesultado.idestadoresultado;
          public          dmakers    false    224            �            1259    19631    impuesto    TABLE     �   CREATE TABLE public.impuesto (
    idimpuesto integer NOT NULL,
    nombreimpuesto character varying(10),
    porcentaje real
);
    DROP TABLE public.impuesto;
       public         heap    dmakers    false            �            1259    19630    impuesto_idimpuesto_seq    SEQUENCE     �   CREATE SEQUENCE public.impuesto_idimpuesto_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 .   DROP SEQUENCE public.impuesto_idimpuesto_seq;
       public          dmakers    false    227            �           0    0    impuesto_idimpuesto_seq    SEQUENCE OWNED BY     S   ALTER SEQUENCE public.impuesto_idimpuesto_seq OWNED BY public.impuesto.idimpuesto;
          public          dmakers    false    226            �            1259    19639 
   inventario    TABLE     �   CREATE TABLE public.inventario (
    idinventario integer NOT NULL,
    idproducto integer,
    idcompra integer,
    cantidadinvetairo integer,
    preciototal real
);
    DROP TABLE public.inventario;
       public         heap    dmakers    false            �            1259    19638    inventario_idinventario_seq    SEQUENCE     �   CREATE SEQUENCE public.inventario_idinventario_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 2   DROP SEQUENCE public.inventario_idinventario_seq;
       public          dmakers    false    229            �           0    0    inventario_idinventario_seq    SEQUENCE OWNED BY     [   ALTER SEQUENCE public.inventario_idinventario_seq OWNED BY public.inventario.idinventario;
          public          dmakers    false    228            �            1259    19649    periodocontable    TABLE     �   CREATE TABLE public.periodocontable (
    idperiodo integer NOT NULL,
    inicio character varying(10),
    fin character varying(10)
);
 #   DROP TABLE public.periodocontable;
       public         heap    dmakers    false            �            1259    19648    periodocontable_idperiodo_seq    SEQUENCE     �   CREATE SEQUENCE public.periodocontable_idperiodo_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 4   DROP SEQUENCE public.periodocontable_idperiodo_seq;
       public          dmakers    false    231            �           0    0    periodocontable_idperiodo_seq    SEQUENCE OWNED BY     _   ALTER SEQUENCE public.periodocontable_idperiodo_seq OWNED BY public.periodocontable.idperiodo;
          public          dmakers    false    230            �            1259    19657    producto    TABLE     �   CREATE TABLE public.producto (
    idproducto integer NOT NULL,
    nombreproduto character varying(100),
    preciounitario real
);
    DROP TABLE public.producto;
       public         heap    dmakers    false            �            1259    19656    producto_idproducto_seq    SEQUENCE     �   CREATE SEQUENCE public.producto_idproducto_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 .   DROP SEQUENCE public.producto_idproducto_seq;
       public          dmakers    false    233            �           0    0    producto_idproducto_seq    SEQUENCE OWNED BY     S   ALTER SEQUENCE public.producto_idproducto_seq OWNED BY public.producto.idproducto;
          public          dmakers    false    232            �            1259    19665    reporte    TABLE     �   CREATE TABLE public.reporte (
    idrepoter integer NOT NULL,
    idestadocapital integer,
    idperiodo integer,
    idestadoresultado integer,
    idbalanceg integer,
    firma character varying(100),
    autor character varying(100)
);
    DROP TABLE public.reporte;
       public         heap    dmakers    false            �            1259    19664    reporte_idrepoter_seq    SEQUENCE     �   CREATE SEQUENCE public.reporte_idrepoter_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ,   DROP SEQUENCE public.reporte_idrepoter_seq;
       public          dmakers    false    235            �           0    0    reporte_idrepoter_seq    SEQUENCE OWNED BY     O   ALTER SEQUENCE public.reporte_idrepoter_seq OWNED BY public.reporte.idrepoter;
          public          dmakers    false    234            �            1259    19677    transaccion    TABLE     �   CREATE TABLE public.transaccion (
    idtransaccion integer NOT NULL,
    codigo integer,
    idimpuesto integer,
    idcompra integer,
    idventa integer,
    concepto character varying(100),
    debe real,
    haber real
);
    DROP TABLE public.transaccion;
       public         heap    dmakers    false            �            1259    19676    transaccion_idtransaccion_seq    SEQUENCE     �   CREATE SEQUENCE public.transaccion_idtransaccion_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 4   DROP SEQUENCE public.transaccion_idtransaccion_seq;
       public          dmakers    false    237            �           0    0    transaccion_idtransaccion_seq    SEQUENCE OWNED BY     _   ALTER SEQUENCE public.transaccion_idtransaccion_seq OWNED BY public.transaccion.idtransaccion;
          public          dmakers    false    236            �            1259    19689    venta    TABLE     �   CREATE TABLE public.venta (
    idventa integer NOT NULL,
    idproducto integer,
    idinventario integer,
    totalventa real
);
    DROP TABLE public.venta;
       public         heap    dmakers    false            �            1259    19688    venta_idventa_seq    SEQUENCE     �   CREATE SEQUENCE public.venta_idventa_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE public.venta_idventa_seq;
       public          dmakers    false    239            �           0    0    venta_idventa_seq    SEQUENCE OWNED BY     G   ALTER SEQUENCE public.venta_idventa_seq OWNED BY public.venta.idventa;
          public          dmakers    false    238            �           2604    19554    ajuste idajuste    DEFAULT     r   ALTER TABLE ONLY public.ajuste ALTER COLUMN idajuste SET DEFAULT nextval('public.ajuste_idajuste_seq'::regclass);
 >   ALTER TABLE public.ajuste ALTER COLUMN idajuste DROP DEFAULT;
       public          dmakers    false    209    210    210            �           2604    19563 #   ajustebalancecomprobacion idajustec    DEFAULT     �   ALTER TABLE ONLY public.ajustebalancecomprobacion ALTER COLUMN idajustec SET DEFAULT nextval('public.ajustebalancecomprobacion_idajustec_seq'::regclass);
 R   ALTER TABLE public.ajustebalancecomprobacion ALTER COLUMN idajustec DROP DEFAULT;
       public          dmakers    false    212    211    212            �           2604    19573    balancecomprobacion idbalancec    DEFAULT     �   ALTER TABLE ONLY public.balancecomprobacion ALTER COLUMN idbalancec SET DEFAULT nextval('public.balancecomprobacion_idbalancec_seq'::regclass);
 M   ALTER TABLE public.balancecomprobacion ALTER COLUMN idbalancec DROP DEFAULT;
       public          dmakers    false    214    213    214            �           2604    19582    balancegeneral idbalanceg    DEFAULT     �   ALTER TABLE ONLY public.balancegeneral ALTER COLUMN idbalanceg SET DEFAULT nextval('public.balancegeneral_idbalanceg_seq'::regclass);
 H   ALTER TABLE public.balancegeneral ALTER COLUMN idbalanceg DROP DEFAULT;
       public          dmakers    false    216    215    216            �           2604    19597    compra idcompra    DEFAULT     r   ALTER TABLE ONLY public.compra ALTER COLUMN idcompra SET DEFAULT nextval('public.compra_idcompra_seq'::regclass);
 >   ALTER TABLE public.compra ALTER COLUMN idcompra DROP DEFAULT;
       public          dmakers    false    219    218    219            �           2604    19606    cuenta idcuenta    DEFAULT     r   ALTER TABLE ONLY public.cuenta ALTER COLUMN idcuenta SET DEFAULT nextval('public.cuenta_idcuenta_seq'::regclass);
 >   ALTER TABLE public.cuenta ALTER COLUMN idcuenta DROP DEFAULT;
       public          dmakers    false    221    220    221            �           2604    19616    estadocapital idestadocapital    DEFAULT     �   ALTER TABLE ONLY public.estadocapital ALTER COLUMN idestadocapital SET DEFAULT nextval('public.estadocapital_idestadocapital_seq'::regclass);
 L   ALTER TABLE public.estadocapital ALTER COLUMN idestadocapital DROP DEFAULT;
       public          dmakers    false    222    223    223            �           2604    19625 #   estadoderesultado idestadoresultado    DEFAULT     �   ALTER TABLE ONLY public.estadoderesultado ALTER COLUMN idestadoresultado SET DEFAULT nextval('public.estadoderesultado_idestadoresultado_seq'::regclass);
 R   ALTER TABLE public.estadoderesultado ALTER COLUMN idestadoresultado DROP DEFAULT;
       public          dmakers    false    225    224    225            �           2604    19634    impuesto idimpuesto    DEFAULT     z   ALTER TABLE ONLY public.impuesto ALTER COLUMN idimpuesto SET DEFAULT nextval('public.impuesto_idimpuesto_seq'::regclass);
 B   ALTER TABLE public.impuesto ALTER COLUMN idimpuesto DROP DEFAULT;
       public          dmakers    false    227    226    227            �           2604    19642    inventario idinventario    DEFAULT     �   ALTER TABLE ONLY public.inventario ALTER COLUMN idinventario SET DEFAULT nextval('public.inventario_idinventario_seq'::regclass);
 F   ALTER TABLE public.inventario ALTER COLUMN idinventario DROP DEFAULT;
       public          dmakers    false    228    229    229            �           2604    19652    periodocontable idperiodo    DEFAULT     �   ALTER TABLE ONLY public.periodocontable ALTER COLUMN idperiodo SET DEFAULT nextval('public.periodocontable_idperiodo_seq'::regclass);
 H   ALTER TABLE public.periodocontable ALTER COLUMN idperiodo DROP DEFAULT;
       public          dmakers    false    230    231    231            �           2604    19660    producto idproducto    DEFAULT     z   ALTER TABLE ONLY public.producto ALTER COLUMN idproducto SET DEFAULT nextval('public.producto_idproducto_seq'::regclass);
 B   ALTER TABLE public.producto ALTER COLUMN idproducto DROP DEFAULT;
       public          dmakers    false    233    232    233            �           2604    19668    reporte idrepoter    DEFAULT     v   ALTER TABLE ONLY public.reporte ALTER COLUMN idrepoter SET DEFAULT nextval('public.reporte_idrepoter_seq'::regclass);
 @   ALTER TABLE public.reporte ALTER COLUMN idrepoter DROP DEFAULT;
       public          dmakers    false    234    235    235            �           2604    19680    transaccion idtransaccion    DEFAULT     �   ALTER TABLE ONLY public.transaccion ALTER COLUMN idtransaccion SET DEFAULT nextval('public.transaccion_idtransaccion_seq'::regclass);
 H   ALTER TABLE public.transaccion ALTER COLUMN idtransaccion DROP DEFAULT;
       public          dmakers    false    237    236    237            �           2604    19692    venta idventa    DEFAULT     n   ALTER TABLE ONLY public.venta ALTER COLUMN idventa SET DEFAULT nextval('public.venta_idventa_seq'::regclass);
 <   ALTER TABLE public.venta ALTER COLUMN idventa DROP DEFAULT;
       public          dmakers    false    238    239    239            �          0    19551    ajuste 
   TABLE DATA           4   COPY public.ajuste (idajuste, idcuenta) FROM stdin;
    public          dmakers    false    210   R�       �          0    19560    ajustebalancecomprobacion 
   TABLE DATA           T   COPY public.ajustebalancecomprobacion (idajustec, idbalancec, idajuste) FROM stdin;
    public          dmakers    false    212   o�       �          0    19570    balancecomprobacion 
   TABLE DATA           j   COPY public.balancecomprobacion (idbalancec, idcuenta, saldoacredor, saldodeudor, equilibrio) FROM stdin;
    public          dmakers    false    214   ��       �          0    19579    balancegeneral 
   TABLE DATA           i   COPY public.balancegeneral (idbalanceg, idajustec, totalactivos, totalpasivos, totalcapital) FROM stdin;
    public          dmakers    false    216   ��       �          0    19587    catalogocuenta 
   TABLE DATA           >   COPY public.catalogocuenta (codigo, nombrecuenta) FROM stdin;
    public          dmakers    false    217   ��       �          0    19594    compra 
   TABLE DATA           S   COPY public.compra (idcompra, idproducto, cantidadcompra, montocompra) FROM stdin;
    public          dmakers    false    219    �       �          0    19603    cuenta 
   TABLE DATA           O   COPY public.cuenta (idcuenta, codigo, idtransaccion, totalizacion) FROM stdin;
    public          dmakers    false    221   �       �          0    19613    estadocapital 
   TABLE DATA           Q   COPY public.estadocapital (idestadocapital, idajustec, nuevocapital) FROM stdin;
    public          dmakers    false    223   O�       �          0    19622    estadoderesultado 
   TABLE DATA           �   COPY public.estadoderesultado (idestadoresultado, idajustec, utilidadbruta, utilidadoperacion, utilidadantesimpuesto, utilidadneta) FROM stdin;
    public          dmakers    false    225   l�       �          0    19631    impuesto 
   TABLE DATA           J   COPY public.impuesto (idimpuesto, nombreimpuesto, porcentaje) FROM stdin;
    public          dmakers    false    227   ��       �          0    19639 
   inventario 
   TABLE DATA           h   COPY public.inventario (idinventario, idproducto, idcompra, cantidadinvetairo, preciototal) FROM stdin;
    public          dmakers    false    229   ��       �          0    19649    periodocontable 
   TABLE DATA           A   COPY public.periodocontable (idperiodo, inicio, fin) FROM stdin;
    public          dmakers    false    231   ��       �          0    19657    producto 
   TABLE DATA           M   COPY public.producto (idproducto, nombreproduto, preciounitario) FROM stdin;
    public          dmakers    false    233   ��       �          0    19665    reporte 
   TABLE DATA           u   COPY public.reporte (idrepoter, idestadocapital, idperiodo, idestadoresultado, idbalanceg, firma, autor) FROM stdin;
    public          dmakers    false    235   �       �          0    19677    transaccion 
   TABLE DATA           r   COPY public.transaccion (idtransaccion, codigo, idimpuesto, idcompra, idventa, concepto, debe, haber) FROM stdin;
    public          dmakers    false    237   ;�       �          0    19689    venta 
   TABLE DATA           N   COPY public.venta (idventa, idproducto, idinventario, totalventa) FROM stdin;
    public          dmakers    false    239   ��       �           0    0    ajuste_idajuste_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.ajuste_idajuste_seq', 1, false);
          public          dmakers    false    209            �           0    0 '   ajustebalancecomprobacion_idajustec_seq    SEQUENCE SET     V   SELECT pg_catalog.setval('public.ajustebalancecomprobacion_idajustec_seq', 1, false);
          public          dmakers    false    211            �           0    0 "   balancecomprobacion_idbalancec_seq    SEQUENCE SET     Q   SELECT pg_catalog.setval('public.balancecomprobacion_idbalancec_seq', 1, false);
          public          dmakers    false    213            �           0    0    balancegeneral_idbalanceg_seq    SEQUENCE SET     L   SELECT pg_catalog.setval('public.balancegeneral_idbalanceg_seq', 1, false);
          public          dmakers    false    215            �           0    0    compra_idcompra_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.compra_idcompra_seq', 1, false);
          public          dmakers    false    218            �           0    0    cuenta_idcuenta_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.cuenta_idcuenta_seq', 3, true);
          public          dmakers    false    220            �           0    0 !   estadocapital_idestadocapital_seq    SEQUENCE SET     P   SELECT pg_catalog.setval('public.estadocapital_idestadocapital_seq', 1, false);
          public          dmakers    false    222            �           0    0 '   estadoderesultado_idestadoresultado_seq    SEQUENCE SET     V   SELECT pg_catalog.setval('public.estadoderesultado_idestadoresultado_seq', 1, false);
          public          dmakers    false    224            �           0    0    impuesto_idimpuesto_seq    SEQUENCE SET     F   SELECT pg_catalog.setval('public.impuesto_idimpuesto_seq', 1, false);
          public          dmakers    false    226            �           0    0    inventario_idinventario_seq    SEQUENCE SET     J   SELECT pg_catalog.setval('public.inventario_idinventario_seq', 1, false);
          public          dmakers    false    228            �           0    0    periodocontable_idperiodo_seq    SEQUENCE SET     L   SELECT pg_catalog.setval('public.periodocontable_idperiodo_seq', 1, false);
          public          dmakers    false    230            �           0    0    producto_idproducto_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public.producto_idproducto_seq', 2, true);
          public          dmakers    false    232            �           0    0    reporte_idrepoter_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('public.reporte_idrepoter_seq', 1, false);
          public          dmakers    false    234            �           0    0    transaccion_idtransaccion_seq    SEQUENCE SET     L   SELECT pg_catalog.setval('public.transaccion_idtransaccion_seq', 27, true);
          public          dmakers    false    236            �           0    0    venta_idventa_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public.venta_idventa_seq', 1, false);
          public          dmakers    false    238            �           2606    19556    ajuste pk_ajuste 
   CONSTRAINT     T   ALTER TABLE ONLY public.ajuste
    ADD CONSTRAINT pk_ajuste PRIMARY KEY (idajuste);
 :   ALTER TABLE ONLY public.ajuste DROP CONSTRAINT pk_ajuste;
       public            dmakers    false    210            �           2606    19565 6   ajustebalancecomprobacion pk_ajustebalancecomprobacion 
   CONSTRAINT     {   ALTER TABLE ONLY public.ajustebalancecomprobacion
    ADD CONSTRAINT pk_ajustebalancecomprobacion PRIMARY KEY (idajustec);
 `   ALTER TABLE ONLY public.ajustebalancecomprobacion DROP CONSTRAINT pk_ajustebalancecomprobacion;
       public            dmakers    false    212            �           2606    19575 *   balancecomprobacion pk_balancecomprobacion 
   CONSTRAINT     p   ALTER TABLE ONLY public.balancecomprobacion
    ADD CONSTRAINT pk_balancecomprobacion PRIMARY KEY (idbalancec);
 T   ALTER TABLE ONLY public.balancecomprobacion DROP CONSTRAINT pk_balancecomprobacion;
       public            dmakers    false    214            �           2606    19584     balancegeneral pk_balancegeneral 
   CONSTRAINT     f   ALTER TABLE ONLY public.balancegeneral
    ADD CONSTRAINT pk_balancegeneral PRIMARY KEY (idbalanceg);
 J   ALTER TABLE ONLY public.balancegeneral DROP CONSTRAINT pk_balancegeneral;
       public            dmakers    false    216            �           2606    19591     catalogocuenta pk_catalogocuenta 
   CONSTRAINT     b   ALTER TABLE ONLY public.catalogocuenta
    ADD CONSTRAINT pk_catalogocuenta PRIMARY KEY (codigo);
 J   ALTER TABLE ONLY public.catalogocuenta DROP CONSTRAINT pk_catalogocuenta;
       public            dmakers    false    217            �           2606    19599    compra pk_compra 
   CONSTRAINT     T   ALTER TABLE ONLY public.compra
    ADD CONSTRAINT pk_compra PRIMARY KEY (idcompra);
 :   ALTER TABLE ONLY public.compra DROP CONSTRAINT pk_compra;
       public            dmakers    false    219            �           2606    19608    cuenta pk_cuenta 
   CONSTRAINT     T   ALTER TABLE ONLY public.cuenta
    ADD CONSTRAINT pk_cuenta PRIMARY KEY (idcuenta);
 :   ALTER TABLE ONLY public.cuenta DROP CONSTRAINT pk_cuenta;
       public            dmakers    false    221            �           2606    19618    estadocapital pk_estadocapital 
   CONSTRAINT     i   ALTER TABLE ONLY public.estadocapital
    ADD CONSTRAINT pk_estadocapital PRIMARY KEY (idestadocapital);
 H   ALTER TABLE ONLY public.estadocapital DROP CONSTRAINT pk_estadocapital;
       public            dmakers    false    223            �           2606    19627 &   estadoderesultado pk_estadoderesultado 
   CONSTRAINT     s   ALTER TABLE ONLY public.estadoderesultado
    ADD CONSTRAINT pk_estadoderesultado PRIMARY KEY (idestadoresultado);
 P   ALTER TABLE ONLY public.estadoderesultado DROP CONSTRAINT pk_estadoderesultado;
       public            dmakers    false    225            �           2606    19636    impuesto pk_impuesto 
   CONSTRAINT     Z   ALTER TABLE ONLY public.impuesto
    ADD CONSTRAINT pk_impuesto PRIMARY KEY (idimpuesto);
 >   ALTER TABLE ONLY public.impuesto DROP CONSTRAINT pk_impuesto;
       public            dmakers    false    227            �           2606    19644    inventario pk_inventario 
   CONSTRAINT     `   ALTER TABLE ONLY public.inventario
    ADD CONSTRAINT pk_inventario PRIMARY KEY (idinventario);
 B   ALTER TABLE ONLY public.inventario DROP CONSTRAINT pk_inventario;
       public            dmakers    false    229            �           2606    19654 "   periodocontable pk_periodocontable 
   CONSTRAINT     g   ALTER TABLE ONLY public.periodocontable
    ADD CONSTRAINT pk_periodocontable PRIMARY KEY (idperiodo);
 L   ALTER TABLE ONLY public.periodocontable DROP CONSTRAINT pk_periodocontable;
       public            dmakers    false    231            �           2606    19662    producto pk_producto 
   CONSTRAINT     Z   ALTER TABLE ONLY public.producto
    ADD CONSTRAINT pk_producto PRIMARY KEY (idproducto);
 >   ALTER TABLE ONLY public.producto DROP CONSTRAINT pk_producto;
       public            dmakers    false    233            �           2606    19670    reporte pk_reporte 
   CONSTRAINT     W   ALTER TABLE ONLY public.reporte
    ADD CONSTRAINT pk_reporte PRIMARY KEY (idrepoter);
 <   ALTER TABLE ONLY public.reporte DROP CONSTRAINT pk_reporte;
       public            dmakers    false    235            �           2606    19682    transaccion pk_transaccion 
   CONSTRAINT     c   ALTER TABLE ONLY public.transaccion
    ADD CONSTRAINT pk_transaccion PRIMARY KEY (idtransaccion);
 D   ALTER TABLE ONLY public.transaccion DROP CONSTRAINT pk_transaccion;
       public            dmakers    false    237            �           2606    19694    venta pk_venta 
   CONSTRAINT     Q   ALTER TABLE ONLY public.venta
    ADD CONSTRAINT pk_venta PRIMARY KEY (idventa);
 8   ALTER TABLE ONLY public.venta DROP CONSTRAINT pk_venta;
       public            dmakers    false    239            �           1259    19557 	   ajuste_pk    INDEX     G   CREATE UNIQUE INDEX ajuste_pk ON public.ajuste USING btree (idajuste);
    DROP INDEX public.ajuste_pk;
       public            dmakers    false    210            �           1259    19566    ajustebalancecomprobacion_pk    INDEX     n   CREATE UNIQUE INDEX ajustebalancecomprobacion_pk ON public.ajustebalancecomprobacion USING btree (idajustec);
 0   DROP INDEX public.ajustebalancecomprobacion_pk;
       public            dmakers    false    212            �           1259    19646 
   aumenta_fk    INDEX     E   CREATE INDEX aumenta_fk ON public.inventario USING btree (idcompra);
    DROP INDEX public.aumenta_fk;
       public            dmakers    false    229            �           1259    19576    balancecomprobacion_pk    INDEX     c   CREATE UNIQUE INDEX balancecomprobacion_pk ON public.balancecomprobacion USING btree (idbalancec);
 *   DROP INDEX public.balancecomprobacion_pk;
       public            dmakers    false    214            �           1259    19585    balancegeneral_pk    INDEX     Y   CREATE UNIQUE INDEX balancegeneral_pk ON public.balancegeneral USING btree (idbalanceg);
 %   DROP INDEX public.balancegeneral_pk;
       public            dmakers    false    216            �           1259    19592    catalogocuenta_pk    INDEX     U   CREATE UNIQUE INDEX catalogocuenta_pk ON public.catalogocuenta USING btree (codigo);
 %   DROP INDEX public.catalogocuenta_pk;
       public            dmakers    false    217            �           1259    19600 	   compra_pk    INDEX     G   CREATE UNIQUE INDEX compra_pk ON public.compra USING btree (idcompra);
    DROP INDEX public.compra_pk;
       public            dmakers    false    219            �           1259    19674    comprende_fk    INDEX     F   CREATE INDEX comprende_fk ON public.reporte USING btree (idbalanceg);
     DROP INDEX public.comprende_fk;
       public            dmakers    false    235            �           1259    19672    contiene_fk    INDEX     J   CREATE INDEX contiene_fk ON public.reporte USING btree (idestadocapital);
    DROP INDEX public.contiene_fk;
       public            dmakers    false    235            �           1259    19609 	   cuenta_pk    INDEX     G   CREATE UNIQUE INDEX cuenta_pk ON public.cuenta USING btree (idcuenta);
    DROP INDEX public.cuenta_pk;
       public            dmakers    false    221            �           1259    19696    descuenta_fk    INDEX     F   CREATE INDEX descuenta_fk ON public.venta USING btree (idinventario);
     DROP INDEX public.descuenta_fk;
       public            dmakers    false    239            �           1259    19684 	   es_una_fk    INDEX     E   CREATE INDEX es_una_fk ON public.transaccion USING btree (idcompra);
    DROP INDEX public.es_una_fk;
       public            dmakers    false    237            �           1259    19619    estadocapital_pk    INDEX     \   CREATE UNIQUE INDEX estadocapital_pk ON public.estadocapital USING btree (idestadocapital);
 $   DROP INDEX public.estadocapital_pk;
       public            dmakers    false    223            �           1259    19628    estadoderesultado_pk    INDEX     f   CREATE UNIQUE INDEX estadoderesultado_pk ON public.estadoderesultado USING btree (idestadoresultado);
 (   DROP INDEX public.estadoderesultado_pk;
       public            dmakers    false    225            �           1259    19686 	   existe_fk    INDEX     G   CREATE INDEX existe_fk ON public.transaccion USING btree (idimpuesto);
    DROP INDEX public.existe_fk;
       public            dmakers    false    237            �           1259    19637    impuesto_pk    INDEX     M   CREATE UNIQUE INDEX impuesto_pk ON public.impuesto USING btree (idimpuesto);
    DROP INDEX public.impuesto_pk;
       public            dmakers    false    227            �           1259    19645    inventario_pk    INDEX     S   CREATE UNIQUE INDEX inventario_pk ON public.inventario USING btree (idinventario);
 !   DROP INDEX public.inventario_pk;
       public            dmakers    false    229            �           1259    19586 	   maneja_fk    INDEX     I   CREATE INDEX maneja_fk ON public.balancegeneral USING btree (idajustec);
    DROP INDEX public.maneja_fk;
       public            dmakers    false    216            �           1259    19675    necesita_fk    INDEX     D   CREATE INDEX necesita_fk ON public.reporte USING btree (idperiodo);
    DROP INDEX public.necesita_fk;
       public            dmakers    false    235            �           1259    19567    necestia_fk    INDEX     W   CREATE INDEX necestia_fk ON public.ajustebalancecomprobacion USING btree (idbalancec);
    DROP INDEX public.necestia_fk;
       public            dmakers    false    212            �           1259    19697    obstenta_fk    INDEX     C   CREATE INDEX obstenta_fk ON public.venta USING btree (idproducto);
    DROP INDEX public.obstenta_fk;
       public            dmakers    false    239            �           1259    19601 
   obtiene_fk    INDEX     C   CREATE INDEX obtiene_fk ON public.compra USING btree (idproducto);
    DROP INDEX public.obtiene_fk;
       public            dmakers    false    219            �           1259    19568    ocupa_fk    INDEX     R   CREATE INDEX ocupa_fk ON public.ajustebalancecomprobacion USING btree (idajuste);
    DROP INDEX public.ocupa_fk;
       public            dmakers    false    212            �           1259    19655    periodocontable_pk    INDEX     Z   CREATE UNIQUE INDEX periodocontable_pk ON public.periodocontable USING btree (idperiodo);
 &   DROP INDEX public.periodocontable_pk;
       public            dmakers    false    231            �           1259    19611    petenece_fk    INDEX     @   CREATE INDEX petenece_fk ON public.cuenta USING btree (codigo);
    DROP INDEX public.petenece_fk;
       public            dmakers    false    221            �           1259    19647    posee_fk    INDEX     E   CREATE INDEX posee_fk ON public.inventario USING btree (idproducto);
    DROP INDEX public.posee_fk;
       public            dmakers    false    229            �           1259    19673    presenta_fk    INDEX     L   CREATE INDEX presenta_fk ON public.reporte USING btree (idestadoresultado);
    DROP INDEX public.presenta_fk;
       public            dmakers    false    235            �           1259    19687 
   presisa_fk    INDEX     D   CREATE INDEX presisa_fk ON public.transaccion USING btree (codigo);
    DROP INDEX public.presisa_fk;
       public            dmakers    false    237            �           1259    19663    producto_pk    INDEX     M   CREATE UNIQUE INDEX producto_pk ON public.producto USING btree (idproducto);
    DROP INDEX public.producto_pk;
       public            dmakers    false    233            �           1259    19671 
   reporte_pk    INDEX     J   CREATE UNIQUE INDEX reporte_pk ON public.reporte USING btree (idrepoter);
    DROP INDEX public.reporte_pk;
       public            dmakers    false    235            �           1259    19620    requiere_fk    INDEX     J   CREATE INDEX requiere_fk ON public.estadocapital USING btree (idajustec);
    DROP INDEX public.requiere_fk;
       public            dmakers    false    223            �           1259    19610    se_compone_fk    INDEX     I   CREATE INDEX se_compone_fk ON public.cuenta USING btree (idtransaccion);
 !   DROP INDEX public.se_compone_fk;
       public            dmakers    false    221            �           1259    19685    se_convierte_fk    INDEX     J   CREATE INDEX se_convierte_fk ON public.transaccion USING btree (idventa);
 #   DROP INDEX public.se_convierte_fk;
       public            dmakers    false    237            �           1259    19577    tiene_fk    INDEX     L   CREATE INDEX tiene_fk ON public.balancecomprobacion USING btree (idcuenta);
    DROP INDEX public.tiene_fk;
       public            dmakers    false    214            �           1259    19683    transaccion_pk    INDEX     V   CREATE UNIQUE INDEX transaccion_pk ON public.transaccion USING btree (idtransaccion);
 "   DROP INDEX public.transaccion_pk;
       public            dmakers    false    237            �           1259    19629    uitiliza_fk    INDEX     N   CREATE INDEX uitiliza_fk ON public.estadoderesultado USING btree (idajustec);
    DROP INDEX public.uitiliza_fk;
       public            dmakers    false    225            �           1259    19558    usa_fk    INDEX     =   CREATE INDEX usa_fk ON public.ajuste USING btree (idcuenta);
    DROP INDEX public.usa_fk;
       public            dmakers    false    210            �           1259    19695    venta_pk    INDEX     D   CREATE UNIQUE INDEX venta_pk ON public.venta USING btree (idventa);
    DROP INDEX public.venta_pk;
       public            dmakers    false    239            �           2606    19698    ajuste fk_ajuste_usa_cuenta    FK CONSTRAINT     �   ALTER TABLE ONLY public.ajuste
    ADD CONSTRAINT fk_ajuste_usa_cuenta FOREIGN KEY (idcuenta) REFERENCES public.cuenta(idcuenta) ON UPDATE RESTRICT ON DELETE RESTRICT;
 E   ALTER TABLE ONLY public.ajuste DROP CONSTRAINT fk_ajuste_usa_cuenta;
       public          dmakers    false    210    221    3280            �           2606    19703 7   ajustebalancecomprobacion fk_ajusteba_necestia_balancec    FK CONSTRAINT     �   ALTER TABLE ONLY public.ajustebalancecomprobacion
    ADD CONSTRAINT fk_ajusteba_necestia_balancec FOREIGN KEY (idbalancec) REFERENCES public.balancecomprobacion(idbalancec) ON UPDATE RESTRICT ON DELETE RESTRICT;
 a   ALTER TABLE ONLY public.ajustebalancecomprobacion DROP CONSTRAINT fk_ajusteba_necestia_balancec;
       public          dmakers    false    3264    214    212            �           2606    19708 2   ajustebalancecomprobacion fk_ajusteba_ocupa_ajuste    FK CONSTRAINT     �   ALTER TABLE ONLY public.ajustebalancecomprobacion
    ADD CONSTRAINT fk_ajusteba_ocupa_ajuste FOREIGN KEY (idajuste) REFERENCES public.ajuste(idajuste) ON UPDATE RESTRICT ON DELETE RESTRICT;
 \   ALTER TABLE ONLY public.ajustebalancecomprobacion DROP CONSTRAINT fk_ajusteba_ocupa_ajuste;
       public          dmakers    false    3255    212    210            �           2606    19713 ,   balancecomprobacion fk_balancec_tiene_cuenta    FK CONSTRAINT     �   ALTER TABLE ONLY public.balancecomprobacion
    ADD CONSTRAINT fk_balancec_tiene_cuenta FOREIGN KEY (idcuenta) REFERENCES public.cuenta(idcuenta) ON UPDATE RESTRICT ON DELETE RESTRICT;
 V   ALTER TABLE ONLY public.balancecomprobacion DROP CONSTRAINT fk_balancec_tiene_cuenta;
       public          dmakers    false    221    214    3280            �           2606    19718 *   balancegeneral fk_balanceg_maneja_ajusteba    FK CONSTRAINT     �   ALTER TABLE ONLY public.balancegeneral
    ADD CONSTRAINT fk_balanceg_maneja_ajusteba FOREIGN KEY (idajustec) REFERENCES public.ajustebalancecomprobacion(idajustec) ON UPDATE RESTRICT ON DELETE RESTRICT;
 T   ALTER TABLE ONLY public.balancegeneral DROP CONSTRAINT fk_balanceg_maneja_ajusteba;
       public          dmakers    false    212    3261    216                        2606    19723 !   compra fk_compra_obtiene_producto    FK CONSTRAINT     �   ALTER TABLE ONLY public.compra
    ADD CONSTRAINT fk_compra_obtiene_producto FOREIGN KEY (idproducto) REFERENCES public.producto(idproducto) ON UPDATE RESTRICT ON DELETE RESTRICT;
 K   ALTER TABLE ONLY public.compra DROP CONSTRAINT fk_compra_obtiene_producto;
       public          dmakers    false    3302    219    233                       2606    19728 "   cuenta fk_cuenta_petenece_catalogo    FK CONSTRAINT     �   ALTER TABLE ONLY public.cuenta
    ADD CONSTRAINT fk_cuenta_petenece_catalogo FOREIGN KEY (codigo) REFERENCES public.catalogocuenta(codigo) ON UPDATE RESTRICT ON DELETE RESTRICT;
 L   ALTER TABLE ONLY public.cuenta DROP CONSTRAINT fk_cuenta_petenece_catalogo;
       public          dmakers    false    3272    217    221                       2606    19733 #   cuenta fk_cuenta_se_compon_transacc    FK CONSTRAINT     �   ALTER TABLE ONLY public.cuenta
    ADD CONSTRAINT fk_cuenta_se_compon_transacc FOREIGN KEY (idtransaccion) REFERENCES public.transaccion(idtransaccion) ON UPDATE RESTRICT ON DELETE RESTRICT;
 M   ALTER TABLE ONLY public.cuenta DROP CONSTRAINT fk_cuenta_se_compon_transacc;
       public          dmakers    false    3314    221    237                       2606    19738 +   estadocapital fk_estadoca_requiere_ajusteba    FK CONSTRAINT     �   ALTER TABLE ONLY public.estadocapital
    ADD CONSTRAINT fk_estadoca_requiere_ajusteba FOREIGN KEY (idajustec) REFERENCES public.ajustebalancecomprobacion(idajustec) ON UPDATE RESTRICT ON DELETE RESTRICT;
 U   ALTER TABLE ONLY public.estadocapital DROP CONSTRAINT fk_estadoca_requiere_ajusteba;
       public          dmakers    false    3261    212    223                       2606    19743 /   estadoderesultado fk_estadode_uitiliza_ajusteba    FK CONSTRAINT     �   ALTER TABLE ONLY public.estadoderesultado
    ADD CONSTRAINT fk_estadode_uitiliza_ajusteba FOREIGN KEY (idajustec) REFERENCES public.ajustebalancecomprobacion(idajustec) ON UPDATE RESTRICT ON DELETE RESTRICT;
 Y   ALTER TABLE ONLY public.estadoderesultado DROP CONSTRAINT fk_estadode_uitiliza_ajusteba;
       public          dmakers    false    3261    212    225                       2606    19748 %   inventario fk_inventar_aumenta_compra    FK CONSTRAINT     �   ALTER TABLE ONLY public.inventario
    ADD CONSTRAINT fk_inventar_aumenta_compra FOREIGN KEY (idcompra) REFERENCES public.compra(idcompra) ON UPDATE RESTRICT ON DELETE RESTRICT;
 O   ALTER TABLE ONLY public.inventario DROP CONSTRAINT fk_inventar_aumenta_compra;
       public          dmakers    false    3276    219    229                       2606    19753 %   inventario fk_inventar_posee_producto    FK CONSTRAINT     �   ALTER TABLE ONLY public.inventario
    ADD CONSTRAINT fk_inventar_posee_producto FOREIGN KEY (idproducto) REFERENCES public.producto(idproducto) ON UPDATE RESTRICT ON DELETE RESTRICT;
 O   ALTER TABLE ONLY public.inventario DROP CONSTRAINT fk_inventar_posee_producto;
       public          dmakers    false    229    3302    233                       2606    19758 %   reporte fk_reporte_comprende_balanceg    FK CONSTRAINT     �   ALTER TABLE ONLY public.reporte
    ADD CONSTRAINT fk_reporte_comprende_balanceg FOREIGN KEY (idbalanceg) REFERENCES public.balancegeneral(idbalanceg) ON UPDATE RESTRICT ON DELETE RESTRICT;
 O   ALTER TABLE ONLY public.reporte DROP CONSTRAINT fk_reporte_comprende_balanceg;
       public          dmakers    false    235    216    3269                       2606    19763 $   reporte fk_reporte_contiene_estadoca    FK CONSTRAINT     �   ALTER TABLE ONLY public.reporte
    ADD CONSTRAINT fk_reporte_contiene_estadoca FOREIGN KEY (idestadocapital) REFERENCES public.estadocapital(idestadocapital) ON UPDATE RESTRICT ON DELETE RESTRICT;
 N   ALTER TABLE ONLY public.reporte DROP CONSTRAINT fk_reporte_contiene_estadoca;
       public          dmakers    false    235    223    3284            	           2606    19768 $   reporte fk_reporte_necesita_periodoc    FK CONSTRAINT     �   ALTER TABLE ONLY public.reporte
    ADD CONSTRAINT fk_reporte_necesita_periodoc FOREIGN KEY (idperiodo) REFERENCES public.periodocontable(idperiodo) ON UPDATE RESTRICT ON DELETE RESTRICT;
 N   ALTER TABLE ONLY public.reporte DROP CONSTRAINT fk_reporte_necesita_periodoc;
       public          dmakers    false    235    231    3300            
           2606    19773 $   reporte fk_reporte_presenta_estadode    FK CONSTRAINT     �   ALTER TABLE ONLY public.reporte
    ADD CONSTRAINT fk_reporte_presenta_estadode FOREIGN KEY (idestadoresultado) REFERENCES public.estadoderesultado(idestadoresultado) ON UPDATE RESTRICT ON DELETE RESTRICT;
 N   ALTER TABLE ONLY public.reporte DROP CONSTRAINT fk_reporte_presenta_estadode;
       public          dmakers    false    3288    225    235                       2606    19778 %   transaccion fk_transacc_es_una_compra    FK CONSTRAINT     �   ALTER TABLE ONLY public.transaccion
    ADD CONSTRAINT fk_transacc_es_una_compra FOREIGN KEY (idcompra) REFERENCES public.compra(idcompra) ON UPDATE RESTRICT ON DELETE RESTRICT;
 O   ALTER TABLE ONLY public.transaccion DROP CONSTRAINT fk_transacc_es_una_compra;
       public          dmakers    false    219    3276    237                       2606    19783 '   transaccion fk_transacc_existe_impuesto    FK CONSTRAINT     �   ALTER TABLE ONLY public.transaccion
    ADD CONSTRAINT fk_transacc_existe_impuesto FOREIGN KEY (idimpuesto) REFERENCES public.impuesto(idimpuesto) ON UPDATE RESTRICT ON DELETE RESTRICT;
 Q   ALTER TABLE ONLY public.transaccion DROP CONSTRAINT fk_transacc_existe_impuesto;
       public          dmakers    false    227    3292    237                       2606    19788 (   transaccion fk_transacc_presisa_catalogo    FK CONSTRAINT     �   ALTER TABLE ONLY public.transaccion
    ADD CONSTRAINT fk_transacc_presisa_catalogo FOREIGN KEY (codigo) REFERENCES public.catalogocuenta(codigo) ON UPDATE RESTRICT ON DELETE RESTRICT;
 R   ALTER TABLE ONLY public.transaccion DROP CONSTRAINT fk_transacc_presisa_catalogo;
       public          dmakers    false    237    3272    217                       2606    19793 '   transaccion fk_transacc_se_convie_venta    FK CONSTRAINT     �   ALTER TABLE ONLY public.transaccion
    ADD CONSTRAINT fk_transacc_se_convie_venta FOREIGN KEY (idventa) REFERENCES public.venta(idventa) ON UPDATE RESTRICT ON DELETE RESTRICT;
 Q   ALTER TABLE ONLY public.transaccion DROP CONSTRAINT fk_transacc_se_convie_venta;
       public          dmakers    false    239    237    3321                       2606    19798 !   venta fk_venta_descuenta_inventar    FK CONSTRAINT     �   ALTER TABLE ONLY public.venta
    ADD CONSTRAINT fk_venta_descuenta_inventar FOREIGN KEY (idinventario) REFERENCES public.inventario(idinventario) ON UPDATE RESTRICT ON DELETE RESTRICT;
 K   ALTER TABLE ONLY public.venta DROP CONSTRAINT fk_venta_descuenta_inventar;
       public          dmakers    false    3296    239    229                       2606    19803     venta fk_venta_obstenta_producto    FK CONSTRAINT     �   ALTER TABLE ONLY public.venta
    ADD CONSTRAINT fk_venta_obstenta_producto FOREIGN KEY (idproducto) REFERENCES public.producto(idproducto) ON UPDATE RESTRICT ON DELETE RESTRICT;
 J   ALTER TABLE ONLY public.venta DROP CONSTRAINT fk_venta_obstenta_producto;
       public          dmakers    false    239    3302    233            �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �   *   x�344�tN�J�244�tJ�K���9sr�S�b���� ��      �      x������ � �      �   "   x�3�444�42�415�2r�9c���=... MS�      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �   .   x�3�t��I�M�+�W�q�44�2�tL�H-�,��42����� ��	�      �      x������ � �      �   P   x�32�444�����ļ�|��<���Ĝ����<NNS.#3�bc�└D�������)��9�Q���P}1z\\\ re      �      x������ � �     
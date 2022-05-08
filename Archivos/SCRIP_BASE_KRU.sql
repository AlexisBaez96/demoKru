/*==============================================================*/
/* DBMS name:      PostgreSQL 8                                 */
/* Created on:     07/05/2022 16:56:58                          */
/*==============================================================*/


drop index EMPLEADO_PK;

drop table EMPLEADO;

drop index EMP_VAC_FK;

drop index VAC_EMP_FK;

drop index EMP_VAC_PK;

drop table EMP_VAC;

drop index VACUNA_PK;

drop table VACUNA;

/*==============================================================*/
/* Table: EMPLEADO                                              */
/*==============================================================*/
create table EMPLEADO (
   CEDULA               INT4                 not null,
   NOMBRE               VARCHAR(200)         not null,
   APELLIDO             VARCHAR(200)         not null,
   CORREO               VARCHAR(200)         not null,
   FECHANA              DATE                 null,
   DIRECCION            VARCHAR(200)         null,
   TELEFONO             INT4                 null,
   VACUNADO             VARCHAR(200)         null,
   constraint PK_EMPLEADO primary key (CEDULA)
);

/*==============================================================*/
/* Index: EMPLEADO_PK                                           */
/*==============================================================*/
create unique index EMPLEADO_PK on EMPLEADO (
CEDULA
);

/*==============================================================*/
/* Table: EMP_VAC                                               */
/*==============================================================*/
create table EMP_VAC (
   FECHA                DATE                 not null,
   DOSIS                INT4                 not null,
   EMPVAC               SERIAL               not null,
   CEDULA               INT4                 not null,
   VACUNA               INT4                 not null,
   constraint PK_EMP_VAC primary key (EMPVAC)
);

/*==============================================================*/
/* Index: EMP_VAC_PK                                            */
/*==============================================================*/
create unique index EMP_VAC_PK on EMP_VAC (
EMPVAC
);

/*==============================================================*/
/* Index: VAC_EMP_FK                                            */
/*==============================================================*/
create  index VAC_EMP_FK on EMP_VAC (
VACUNA
);

/*==============================================================*/
/* Index: EMP_VAC_FK                                            */
/*==============================================================*/
create  index EMP_VAC_FK on EMP_VAC (
CEDULA
);

/*==============================================================*/
/* Table: VACUNA                                                */
/*==============================================================*/
create table VACUNA (
   VACUNA               INT4                 not null,
   DVACUNA              VARCHAR(200)         not null,
   constraint PK_VACUNA primary key (VACUNA)
);

/*==============================================================*/
/* Index: VACUNA_PK                                             */
/*==============================================================*/
create unique index VACUNA_PK on VACUNA (
VACUNA
);

alter table EMP_VAC
   add constraint FK_EMP_VAC_EMP_VAC_EMPLEADO foreign key (CEDULA)
      references EMPLEADO (CEDULA)
      on delete restrict on update restrict;

alter table EMP_VAC
   add constraint FK_EMP_VAC_VAC_EMP_VACUNA foreign key (VACUNA)
      references VACUNA (VACUNA)
      on delete restrict on update restrict;


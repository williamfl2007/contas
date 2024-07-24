CREATE TABLE public.contas(
  id integer not null primary key identity,
   data_vencimento date,
   valor numeric(10,2),
   descricao varchar(50),
   situacao bit);
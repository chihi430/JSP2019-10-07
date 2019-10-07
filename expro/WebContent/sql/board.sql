create table ex_board(
  id varchar2(20) REFERENCES ex_memberinfo(id),
  board_no number PRIMARY key,
  board_subject varchar2(50) not null,
  board_content long not null,
  ip varchar2(30) not null,
  readcount number default 0,
  file_name varchar2(50),
  section varchar2(30),
  write_date date not null
);

create SEQUENCE seq_ex_board_no
INCREMENT BY 1
START WITH 1;


insert into ex_board values(
  'joker',
  seq_ex_board_no.nextval,
  'test',
  'test',
  '0',
  0,
  'test.jpg',
  'menu1',
  sysdate
);
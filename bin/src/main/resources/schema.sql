
create table board(
	board_seq int primary key AUTO_INCREMENT,
	board_title varchar(200),
	board_content varchar(2000),
	board_createdt timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
);


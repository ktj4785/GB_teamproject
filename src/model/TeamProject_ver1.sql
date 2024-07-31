drop database teamproject;

create database teamproject;

use teamproject;

create table movie(
	movieId 	int primary key auto_increment,
    movieName 	varchar(300) not null,
    director 	varchar(100) not null,
    runningTime varchar(300) not null,
    genre 		varchar(100) not null
);

select * from movie;

create table user(
	userId		varchar(300) primary key,
    userPw		varchar(300) not null,
    userName	varchar(100) not null,
    userAge		int,
    socialNum	varchar(100) unique,
    phone		varchar(100),
    userAddr	varchar(300)
);

create table theater(
	theaterId	int primary key auto_increment,
    theaterName	varchar(300) not null,
    theaterAddr	varchar(300) not null,
    seatCnt		int not null,
    dimension	varchar(300) not null
);

create table actor(
	actorId		int primary key auto_increment,
    actorName	varchar(100) not null,
    movieId 	int,
    constraint actor_movie_fk foreign key (movieId) references movie(movieId)
);

create table schedule(
	scheduleId	int primary key auto_increment,
    startTime	datetime not null,
    endTime		datetime not null,
    leftSeat	int,
    theaterId	int,
    movieId 	int,
    constraint schedule_theater_fk foreign key (theaterId) references theater(theaterId),
    constraint schedule_movie_fk foreign key (movieId) references movie(movieId)
);

create table review(
	reviewId	int primary key auto_increment,
    review		varchar(1000),
    grade		decimal(2,1) default 0,
    createTime	datetime,
    movieId 	int,
    userId		varchar(300),
    constraint review_movie_fk foreign key (movieId) references movie(movieId),
    constraint review_user_fk foreign key (userId) references user(userId)
);

create table reserve(
	reserveId	int primary key auto_increment,
    pNum		int not null,
    price		int not null,
    payment		boolean not null,
    seat		varchar(300),
    userId		varchar(300),
    scheduleId	int,
    constraint reserve_user_fk foreign key (userId) references user(userId),
    constraint reserve_schedule_fk foreign key (scheduleId) references schedule(scheduleId)
);

create table account(
	accountId	int primary key auto_increment,
    bank		varchar(100) not null,
    balance		int default 0,
    userId		varchar(300),
    constraint accnt_user_fk foreign key (userId) references user(userId)
);

insert into theater values 
(1, "강남1관", "서울시 강남구", 100, "2"),
(2, "강남2관", "서울시 강남구", 100, "3"),
(3, "강남3관", "서울시 강남구", 50, "4"),
(4, "서초1관", "서울시 서초구", 50, "2"),
(5, "동탄1관", "경기도 동탄", 100, "2");
/*엄지님 데이터*/

create table seat(
	seatId		int primary key auto_increment,
    seatType	varchar(100),
    seatPrice	int,
    seatLine	varchar(100),
    seatRow		varchar(100),
    theaterId	int,
    constraint seat_theater_fk foreign key (theaterId) references theater(theaterId)
);

INSERT INTO seat (seatId, seatType, seatPrice, seatLine, seatRow, theaterID) VALUES
(1, 'Economy', 80, 10, 'A', 1),
(2, 'Economy', 80, 10, 'B', 1),
(3, 'Standard', 100, 10, 'C', 1),
(4, 'Standard', 100, 10, 'D', 1),
(5, 'VIP', 120, 10, 'E', 1),
(6, 'VIP', 120, 10, 'F', 1);

insert into movie values 
(1, "탈주        ", "이종필", "94분", "느와르"),
(2, "데드풀과 울버린", "숀 레비", "127분", "액션"),
(3, "모가디슈", "류승완", "121분", "전쟁"),
(4, "반도        ", "연상호", "116분", "좀비"),
(5, "베테랑", "류승완", "124분", "형사"),
(6, "범죄도시", "강윤성", "121분", "범죄"),
(7, "범죄도시2", "이상용", "106분", "범죄"),
(8, "범죄도시3", "이상용", "105분", "범죄"),
(9, "범죄도시4", "허명행", "109분", "액션"),
(10, "부산행", "연상호", "118분", "좀비");

insert into actor (actorName, movieId) values 
("이제훈", 1),
("구교환", 1),
("홍사빈", 1),
("라이언 레이놀즈", 2),
("휴 잭맨", 2),
("엠마 코린", 2),
("김윤석", 3),
("조인성", 3),
("구교환", 3),
("구교환", 4),
("강동원", 4),
("권해효", 4),
("황정민", 5),
("유아인", 5),
("마동석", 5),
("마동석", 6),
("윤계상", 6),
("박지환", 6),
("마동석", 7),
("손석구", 7),
("박지환", 7),
("마동석", 8),
("이준혁", 8),
("김민재", 8),
("마동석", 9),
("김무열", 9),
("박지환", 9),
("마동석", 10),
("공유", 10),
("안소희", 10);


insert into review (grade,movieId) values
('3.0','1'),
('3.0','2'),
('3.0','3'),
('3.0','4'),
('3.0','5'),
('3.0','6'),
('3.0','7'),
('3.0','8'),
('3.0','9'),
('3.0','10');

/*insert into schedule values 
(1, '2024-07-20 14:00:00', '2024-07-20 16:00:00', 50, 1, 1),
(2, '2024-07-20 18:00:00', '2024-07-20 20:00:00', 30, 2, 2),
(3, '2024-07-21 12:00:00', '2024-07-21 14:30:00', 25, 3, 3),
(4, '2024-07-21 16:00:00', '2024-07-21 18:30:00', 20, 1, 2),
(5, '2024-07-22 10:00:00', '2024-07-22 12:30:00', 30, 2, 3);*/
/*엄지님 데이터*/
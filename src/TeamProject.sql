create database teamproject;

use teamproject;

create table movie(
	movieId 	int primary key auto_increment,
    movieName 	varchar(300) not null,
    director 	varchar(100) not null,
    runningTime varchar(300) not null,
    genre 		varchar(100) not null,
	avgScore		decimal(2,1) default 0
);

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

create table seat(
	seatId		int primary key auto_increment,
    seatType	varchar(100),
    seatPrice	int,
    seatLine	varchar(100),
    seatRow		varchar(100),
    theaterId	int,
    constraint seat_theater_fk foreign key (theaterId) references theater(theaterId)
);

#회원 관련
select * from user;

#영화 관련
select * from movie;
select * from theater;
select * from schedule;

#데이터 넣기
insert into theater values 
(1, "강남1관", "서울시 강남구", 100, "2"),
(2, "강남2관", "서울시 강남구", 100, "3"),
(3, "강남3관", "서울시 강남구", 50, "4"),
(4, "서초1관", "서울시 서초구", 50, "2"),
(5, "동탄1관", "경기도 동탄", 100, "2");

insert into movie values 
(1, "탈주", "이종필", "94분", "액션", 8.2),
(2, "데드풀과 울버린", "숀 레비", "127분", "액션, 코메디", 9.9),
(3, "슈퍼배드 4", "크리스 리노드", "94분", "액션, 어드밴쳐, 코메디", 8.1),
(4, "인사이드 아웃 2", "캘시 만", "96분", "애니메이션", 8.7),
(5, "명탐정 코난 - 100만 달러의 펜타그램", "나가오카 치카장르", "111분", "애니메이션", 7.2);

insert into schedule values 
(1, '2024-07-20 14:00:00', '2024-07-20 16:00:00', 50, 1, 1),
(2, '2024-07-20 18:00:00', '2024-07-20 20:00:00', 30, 2, 2),
(3, '2024-07-21 12:00:00', '2024-07-21 14:30:00', 25, 3, 3),
(4, '2024-07-21 16:00:00', '2024-07-21 18:30:00', 20, 1, 2),
(5, '2024-07-22 10:00:00', '2024-07-22 12:30:00', 30, 2, 3);

select m.movieName, m.director, m.runningTime, m.genre, m.avgScore 
from movie m 
order by movieName;

select m.movieName, t.theaterName, t.dimension, s.startTime, s.leftSeat 
from schedule s 
join movie m on s.movieId = m.movieId 
join theater t on s.theaterId = t.theaterId 
order by startTime;

select m.movieName, t.dimension, m.movieName, s.startTime, m.runningTime, m.genre, s.leftSeat 
from schedule s 
join movie m on s.movieId = m.movieId 
join theater t on s.theaterId = t.theaterId 
order by theaterName;
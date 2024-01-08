CREATE TABLE `ticket` (
  `ticketNum` int NOT NULL AUTO_INCREMENT,
  `userID` varchar(12) NOT NULL COMMENT '티켓 구매자 ID',
  `musical` char(5) NOT NULL COMMENT '뮤지컬 이름',
  `seatNum` varchar(4) NOT NULL COMMENT '좌석번호',
  `pay` int NOT NULL COMMENT '금액',
  `Date` date NOT NULL COMMENT '뮤지컬 날짜',
  `time` time NOT NULL COMMENT '뮤지컬 시작시간',
  PRIMARY KEY (`ticketNum`),
  KEY `fk_userid_member_idx` (`userID`),
  CONSTRAINT `fk_userid_member` FOREIGN KEY (`userID`) REFERENCES `member` (`userID`)
)

SELECT * FROM projectdb.ticket;

DESC ticket;

INSERT INTO ticket VALUES(null,'hap0p9y','레미제라블','A|5', '100000','2024-01-10','11:00:00');

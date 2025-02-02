CREATE TABLE IF NOT EXISTS `admin` (
  `admin_seq` int(11) NOT NULL AUTO_INCREMENT COMMENT '관리자순번',
  `admin_name` varchar(50) NOT NULL DEFAULT '0',
  `admin_id` varchar(50) NOT NULL COMMENT '관리자아이디',
  `admin_pw` varchar(100) NOT NULL COMMENT '관리자비밀번호',
  `reg_seq` int(11) NOT NULL COMMENT '등록자순번',
  `use_yn` char(1) NOT NULL DEFAULT 'Y' COMMENT '사용여부',
  `reg_date` timestamp NOT NULL DEFAULT current_timestamp() COMMENT '등록일',
  PRIMARY KEY (`admin_seq`),
  UNIQUE KEY `admin_id` (`admin_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci COMMENT='관리자정보';

INSERT INTO `admin` (`admin_name`, `admin_id`, `admin_pw`, `reg_seq`, `use_yn`) VALUES
	('supser', 'admin', '$2a$10$nyCWhDaWmElsbCy08rxebu1WPPanpVr/tk450OjjdOJiWRUYcYXY.', 1, 'Y');



CREATE TABLE IF NOT EXISTS `member` (
  `member_seq` int(11) NOT NULL AUTO_INCREMENT COMMENT '회원순번',
  `member_id` varchar(50) NOT NULL COMMENT '회원아이디',
  `member_pw` varchar(100) NOT NULL COMMENT '비밀번호',
  `member_nick` varchar(50) NOT NULL COMMENT '닉네임',
  `member_profile` varchar(100) DEFAULT NULL COMMENT '프로필사진',
  `member_info` varchar(400) DEFAULT NULL COMMENT '소개',
  `use_yn` char(1) DEFAULT 'Y' COMMENT '사용여부',
  `reg_date` timestamp NULL DEFAULT current_timestamp() COMMENT '등록일',
  PRIMARY KEY (`member_seq`),
  UNIQUE KEY `member_id` (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci COMMENT='회원테이블';


CREATE TABLE IF NOT EXISTS `video` (
  `video_seq` int(11) NOT NULL AUTO_INCREMENT COMMENT '동영상순번',
  `video` varchar(200) NOT NULL COMMENT '동영상',
  `video_thumnail` varchar(100) NOT NULL COMMENT '썸네일',
  `title` varchar(100) NOT NULL,
  `content` varchar(100) NOT NULL,
  `member_seq` int(11) NOT NULL COMMENT '작성자',
  `reg_date` timestamp NOT NULL DEFAULT current_timestamp() COMMENT '작성일',
  `publish_yn` tinyint(1) NOT NULL DEFAULT 0 COMMENT '공개여부',
  `delete_yn` char(1) NOT NULL DEFAULT 'N' COMMENT '삭제여부',
  PRIMARY KEY (`video_seq`),
  KEY `FK_VIDEOS_MEMBERS` (`member_seq`),
  CONSTRAINT `FK_VIDEOS_MEMBERS` FOREIGN KEY (`member_seq`) REFERENCES `member` (`member_seq`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci COMMENT='업로드 동영상';

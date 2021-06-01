-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- ホスト: exerciseJava_mysql_host
-- 生成日時: 2021 年 5 月 15 日 18:17
-- サーバのバージョン： 5.7.34
-- PHP のバージョン: 7.4.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- データベース: `javaframework`
--
CREATE DATABASE IF NOT EXISTS `employee` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE `employee`;

-- --------------------------------------------------------
--
-- テーブルの構造 `post`
--
DROP TABLE IF EXISTS `post`;
CREATE TABLE `post`(
  `post_id` int(11) PRIMARY KEY AUTO_INCREMENT,
  `post_name` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL ,
  `delete_flag` int(2) NOT NULL
);

INSERT INTO post (post_name, delete_flag) VALUES ('会長', 0);  -- id = 1
INSERT INTO post (post_name, delete_flag) VALUES ('代表取締役社長', 0);  -- id = 2
INSERT INTO post (post_name, delete_flag) VALUES ('取締役', 0);  -- id = 3
INSERT INTO post (post_name, delete_flag) VALUES ('本部長', 0);  -- id = 4
INSERT INTO post (post_name, delete_flag) VALUES ('部長', 0);  -- id = 5
INSERT INTO post (post_name, delete_flag) VALUES ('次長', 0);  -- id = 6
INSERT INTO post (post_name, delete_flag) VALUES ('課長', 0);  -- id = 7
INSERT INTO post (post_name, delete_flag) VALUES ('係長', 0);  -- id = 8
INSERT INTO post (post_name, delete_flag) VALUES ('主任', 0);  -- id = 9
INSERT INTO post (post_name, delete_flag) VALUES ('一般社員', 0);  -- id = 10

--
-- テーブルの構造 `department`
--
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department`(
  `dept_id` int(11)  PRIMARY KEY AUTO_INCREMENT,
  `dept_name` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `delete_flag` int(2) NOT NULL
);

INSERT INTO department(dept_name, delete_flag) VALUES ('総務部', 0);  -- id = 1
INSERT INTO department(dept_name, delete_flag) VALUES ('人事部', 0);  -- id = 2
INSERT INTO department(dept_name, delete_flag) VALUES ('経理部', 0);  -- id = 3
INSERT INTO department(dept_name, delete_flag) VALUES ('営業部', 0);  -- id = 4
INSERT INTO department(dept_name, delete_flag) VALUES ('開発部', 0);  -- id = 5
INSERT INTO department(dept_name, delete_flag) VALUES ('事業部', 0);  -- id = 6
INSERT INTO department(dept_name, delete_flag) VALUES ('製造部', 0);  -- id = 7
INSERT INTO department(dept_name, delete_flag) VALUES ('その他', 0);  -- id = 8





--
-- テーブルの構造 `user`
--

DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `emp_id` int(11) PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL ,
  `kana` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL ,
  `hire_date` DATE COLLATE utf8mb4_unicode_ci DEFAULT NULL ,
  `post_id` int(11) DEFAULT NULL ,
  `dept_id` int(11) DEFAULT NULL ,
  `tel` varchar(11) COLLATE utf8mb4_unicode_ci DEFAULT NULL UNIQUE,
  `email` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL UNIQUE,
  `password` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `delete_flag` int(2) NOT NULL,
  `create_at` datetime COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `update_at` datetime COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  FOREIGN KEY fk_post(post_id) REFERENCES post(post_id),
  FOREIGN KEY fk_dept(dept_id) REFERENCES department(dept_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- ダンプしたテーブルのインデックス
--

--
-- テーブルのインデックス `employee`
--


--
-- ダンプしたテーブルの AUTO_INCREMENT
--

--
-- テーブルの AUTO_INCREMENT `user`
--



INSERT INTO employee(name, kana, hire_date, post_id, dept_id, tel, email, password, delete_flag, create_at, update_at) VALUES  /*管理者　パス　admin*/
            ('桐生ココ', 'キリュウココ', '1972-04-01', 1, 8, '08024338464', 'emp111@example.co.jp','$2a$10$LBvlmRQr3NBxi5JMyeDQAO822DlRuzGvfY4V172AOfa54kDLPAkla', 0,'1972-04-01 10:23:45', '1972-04-01 10:23:45');
INSERT INTO employee(name, kana, hire_date, post_id, dept_id, tel, email, password, delete_flag, create_at, update_at) VALUES 
            ('加賀美隼人', 'カガミハヤト', '1972-04-01', 2, 8, '08002745293', 'emp000@example.co.jp','0',0,'1972-04-01 10:23:45', '1972-04-01 10:23:45');
INSERT INTO employee(name, kana, hire_date, post_id, dept_id, tel, email, password, delete_flag, create_at, update_at) VALUES 
            ('海馬瀬人', 'カイバセト', '1972-04-01', 3, 8, '08073548292', 'emp001@example.co.jp','0',0,'1972-04-01 10:23:45', '1972-04-01 10:23:45');
INSERT INTO employee(name, kana, hire_date, post_id, dept_id, tel, email, password, delete_flag, create_at, update_at) VALUES 
            ('麻倉葉', 'アサクラヨウ', '1977-08-01', 3, 8, '08054833414', 'emp002@example.co.jp','0',0,'1977-04-01 10:23:45', '1977-04-01 10:23:45');
INSERT INTO employee(name, kana, hire_date, post_id, dept_id, tel, email, password, delete_flag, create_at, update_at) VALUES 
            ('RandellOrlando', 'ランデル・オーランド', '1976-04-01', 3, 8, '08078330868', 'emp003@example.co.jp','0',0,'1976-04-01 10:23:45', '1976-04-01 10:23:45');
INSERT INTO employee(name, kana, hire_date, post_id, dept_id, tel, email, password, delete_flag, create_at, update_at) VALUES 
            ('虎杖悠二', 'イタドリユウジ', '1979-04-01', 3, 8, '08019258645', 'emp004@example.co.jp','0',0,'1979-04-01 10:23:45', '1979-04-01 10:23:45');
INSERT INTO employee(name, kana, hire_date, post_id, dept_id, tel, email, password, delete_flag, create_at, update_at) VALUES 
            ('比泉秋名', 'ヒイズミアキナ', '1979-04-01', 3, 2, '08019338645', 'emp104@example.co.jp','0',0,'1979-04-01 10:23:45', '1979-04-01 10:23:45');
INSERT INTO employee(name, kana, hire_date, post_id, dept_id, tel, email, password, delete_flag, create_at, update_at) VALUES 
            ('三枝明那', 'サエグサアキナ', '1979-04-01', 3, 5, '08019252222', 'emp839@example.co.jp','0',0,'1979-04-01 10:23:45', '1979-04-01 10:23:45');                        
INSERT INTO employee(name, kana, hire_date, post_id, dept_id, tel, email, password, delete_flag, create_at, update_at) VALUES 
            ('阿良々木暦', 'アララギコヨミ', '1982-04-01', 6, 8, '08081510801', 'emp005@example.co.jp','0',0,'1982-04-01 10:23:45', '1982-04-01 10:23:45');
INSERT INTO employee(name, kana, hire_date, post_id, dept_id, tel, email, password, delete_flag, create_at, update_at) VALUES 
            ('奥村燐', 'オクムラリン', '1982-04-01', 6, 8, '08081572801', 'emp080@example.co.jp','0',0,'1982-04-01 10:23:45', '1982-04-01 10:23:45');            
INSERT INTO employee(name, kana, hire_date, post_id, dept_id, tel, email, password, delete_flag, create_at, update_at) VALUES 
            ('兎田ぺこら', 'ウサダペコラ', '1982-04-01', 4, 4, '08068774992', 'emp022@example.co.jp','0',0,'1982-04-01 10:23:45', '1982-04-01 10:23:45');
INSERT INTO employee(name, kana, hire_date, post_id, dept_id, tel, email, password, delete_flag, create_at, update_at) VALUES 
            ('立花響', 'タチバナヒビキ', '1984-04-01', 4, 8, '08000320846', 'emp006@example.co.jp','0',0,'1984-04-01 10:23:45', '1984-04-01 10:23:45');
INSERT INTO employee(name, kana, hire_date, post_id, dept_id, tel, email, password, delete_flag, create_at, update_at) VALUES 
            ('上条当麻', 'カミジョウトウマ', '1985-04-01', 5, 8, '08012532148', 'emp007@example.co.jp','0',0,'1985-04-01 10:23:45', '1985-04-01 10:23:45');
INSERT INTO employee(name, kana, hire_date, post_id, dept_id, tel, email, password, delete_flag, create_at, update_at) VALUES 
            ('桐谷和人', 'キリガヤカズト', '1986-04-01', 7, 8, '08044651244', 'emp648@example.co.jp','0',0,'1986-04-01 10:23:45', '1986-04-01 10:23:45');
INSERT INTO employee(name, kana, hire_date, post_id, dept_id, tel, email, password, delete_flag, create_at, update_at) VALUES 
            ('禅院真希', 'ゼンインマキ', '1986-04-01', 5, 8, '08087327823', 'emp008@example.co.jp','0',0,'1986-04-01 10:23:45', '1986-04-01 10:23:45');
INSERT INTO employee(name, kana, hire_date, post_id, dept_id, tel, email, password, delete_flag, create_at, update_at) VALUES 
            ('比企谷八幡', 'ヒキガヤハチマン', '1986-04-01', 7, 8, '08097651275', 'emp009@example.co.jp','0',0,'1986-04-01 10:23:45', '1986-04-01 10:23:45');
INSERT INTO employee(name, kana, hire_date, post_id, dept_id, tel, email, password, delete_flag, create_at, update_at) VALUES 
            ('VioletEvergarden', 'ヴァイオレット・エヴァーガーデン', '1986-04-01', 7, 8, '08097651244', 'emp225@example.co.jp','0',0,'1986-04-01 10:23:45', '1986-04-01 10:23:45');
INSERT INTO employee(name, kana, hire_date, post_id, dept_id, tel, email, password, delete_flag, create_at, update_at) VALUES 
            ('菜月昴', 'ナツキスバル', '1986-04-01', 7, 8, '08097651334', 'emp255@example.co.jp','0',0,'1986-04-01 10:23:45', '1986-04-01 10:23:45');
INSERT INTO employee(name, kana, hire_date, post_id, dept_id, tel, email, password, delete_flag, create_at, update_at) VALUES 
            ('BanagherLinks', 'バナージ・リンクス', '1986-04-01', 4, 8, '08011651244', 'emp999@example.co.jp','0',0,'1986-04-01 10:23:45', '1986-04-01 10:23:45'); 
INSERT INTO employee(name, kana, hire_date, post_id, dept_id, tel, email, password, delete_flag, create_at, update_at) VALUES 
            ('衛宮士郎', 'エミヤシロウ', '1986-04-01', 4, 8, '08011000244', 'emp449@example.co.jp','0',0,'1986-04-01 10:23:45', '1986-04-01 10:23:45');                        
INSERT INTO employee(name, kana, hire_date, post_id, dept_id, tel, email, password, delete_flag, create_at, update_at) VALUES 
            ('槙島聖護', 'マキシマショウゴ', '1992-04-01', 4, 8, '08078322964', 'emp010@example.co.jp','0',0,'1992-04-01 10:23:45', '1992-04-01 10:23:45');
INSERT INTO employee(name, kana, hire_date, post_id, dept_id, tel, email, password, delete_flag, create_at, update_at) VALUES 
            ('月ノ美兎', 'ツキノミト', '1993-04-01', 4, 8, '08019728365', 'emp011@example.co.jp','0',0,'1993-04-01 10:23:45', '1993-04-01 10:23:45');
INSERT INTO employee(name, kana, hire_date, post_id, dept_id, tel, email, password, delete_flag, create_at, update_at) VALUES 
            ('黒崎一護', 'クロサキイチゴ', '2002-04-01', 8, 3, '08087631221', 'emp012@example.co.jp','0',0,'2002-04-01 10:23:45', '2002-04-01 10:23:45');
INSERT INTO employee(name, kana, hire_date, post_id, dept_id, tel, email, password, delete_flag, create_at, update_at) VALUES 
            ('朝倉透', 'アサクラトオル', '2002-04-01', 3, 3, '08086631221', 'emp034@example.co.jp','0',0,'2002-04-01 10:23:45', '2002-04-01 10:23:45');            
INSERT INTO employee(name, kana, hire_date, post_id, dept_id, tel, email, password, delete_flag, create_at, update_at) VALUES 
            ('司波達也', 'シバタツヤ', '2002-04-01', 8, 5, '08087881221', 'emp933@example.co.jp','0',0,'2002-04-01 10:23:45', '2002-04-01 10:23:45');
INSERT INTO employee(name, kana, hire_date, post_id, dept_id, tel, email, password, delete_flag, create_at, update_at) VALUES 
            ('爆轟克己', 'バクゴウカツキ', '2003-04-01', 6, 6, '08014513243', 'emp013@example.co.jp','0',0,'2003-04-01 10:23:45', '2003-04-01 10:23:45');
INSERT INTO employee(name, kana, hire_date, post_id, dept_id, tel, email, password, delete_flag, create_at, update_at) VALUES 
            ('工藤新一', 'クドウシンイチ', '2006-04-01', 6, 5, '08048541452', 'emp014@example.co.jp','0',0,'2006-04-01 10:23:45', '2006-04-01 10:23:45');
INSERT INTO employee(name, kana, hire_date, post_id, dept_id, tel, email, password, delete_flag, create_at, update_at) VALUES 
            ('堀京子', 'ホリキョウコ', '2006-04-01', 6, 5, '08033341452', 'emp914@example.co.jp','0',0,'2006-04-01 10:23:45', '2006-04-01 10:23:45');            
INSERT INTO employee(name, kana, hire_date, post_id, dept_id, tel, email, password, delete_flag, create_at, update_at) VALUES 
            ('笠原郁', 'カサハライク', '2008-04-01', 8, 3, '08067337634', 'emp015@example.co.jp','0',0,'2008-04-01 10:23:45', '2008-04-01 10:23:45');
INSERT INTO employee(name, kana, hire_date, post_id, dept_id, tel, email, password, delete_flag, create_at, update_at) VALUES 
            ('岡部倫太郎', 'オカベリンタロウ', '2012-04-01', 9, 8, '08094628466', 'emp016@example.co.jp','0',0,'2012-04-01 10:23:45', '2012-04-01 10:23:45');
INSERT INTO employee(name, kana, hire_date, post_id, dept_id, tel, email, password, delete_flag, create_at, update_at) VALUES 
            ('有馬公生', 'アリマコウセイ', '2014-04-01', 9, 5, '08027452947', 'emp017@example.co.jp','0',0,'2014-04-01 10:23:45', '2014-04-01 10:23:45');
INSERT INTO employee(name, kana, hire_date, post_id, dept_id, tel, email, password, delete_flag, create_at, update_at) VALUES 
            ('金木研', 'カネキケン', '2017-04-01', 10, 1, '08000388462', 'emp018@example.co.jp','0',0,'2017-04-01 10:23:45', '2017-04-01 10:23:45');
INSERT INTO employee(name, kana, hire_date, post_id, dept_id, tel, email, password, delete_flag, create_at, update_at) VALUES 
            ('瀬戸美夜子', 'セトミヤコ', '2017-04-01', 10, 1, '08000385562', 'emp949@example.co.jp','0',0,'2017-04-01 10:23:45', '2017-04-01 10:23:45');
INSERT INTO employee(name, kana, hire_date, post_id, dept_id, tel, email, password, delete_flag, create_at, update_at) VALUES 
            ('結城友奈', 'ユウキユウナ', '2017-04-01', 10, 6, '08000538462', 'emp618@example.co.jp','0',0,'2017-04-01 10:23:45', '2017-04-01 10:23:45');                        
INSERT INTO employee(name, kana, hire_date, post_id, dept_id, tel, email, password, delete_flag, create_at, update_at) VALUES 
            ('小野田坂道', 'オノダサカミチ', '2017-04-01', 10, 7, '08000531462', 'emp420@example.co.jp','0',0,'2017-04-01 10:23:45', '2017-04-01 10:23:45');
INSERT INTO employee(name, kana, hire_date, post_id, dept_id, tel, email, password, delete_flag, create_at, update_at) VALUES 
            ('墨村良守', 'スミムラヨシモリ', '2017-04-01', 10, 3, '08098531362', 'emp739@example.co.jp','0',0,'2017-04-01 10:23:45', '2017-04-01 10:23:45');
INSERT INTO employee(name, kana, hire_date, post_id, dept_id, tel, email, password, delete_flag, create_at, update_at) VALUES 
            ('坂田銀時', 'サカタギントキ', '2017-04-01', 7, 1, '08036638462', 'emp837@example.co.jp','0',0,'2017-04-01 10:23:45', '2017-04-01 10:23:45');               
INSERT INTO employee(name, kana, hire_date, post_id, dept_id, tel, email, password, delete_flag, create_at, update_at) VALUES 
            ('夏目貴志', 'ナツメタカシ', '2020-04-01', 10, 3, '08027551836', 'emp019@example.co.jp','0',0,'2020-04-01 10:23:45', '2020-04-01 10:23:45');
INSERT INTO employee(name, kana, hire_date, post_id, dept_id, tel, email, password, delete_flag, create_at, update_at) VALUES 
            ('海馬モクバ', 'カイバモクバ', '2021-04-01', 10, 6, '08064728912', 'emp020@example.co.jp','0',0,'2021-04-01 10:23:45', '2021-04-01 10:23:45');
INSERT INTO employee(name, kana, hire_date, post_id, dept_id, tel, email, password, delete_flag, create_at, update_at) VALUES 
            ('禅院真衣', 'ゼンインマイ', '2021-04-01', 10, 4, '08053859334', 'emp021@example.co.jp','0',0,'2021-04-01 10:23:45', '2021-04-01 10:23:45');
INSERT INTO employee(name, kana, hire_date, post_id, dept_id, tel, email, password, delete_flag, create_at, update_at) VALUES 
            ('樋口楓', 'ヒグチカエデ', '2021-04-01', 10, 4, '08053899334', 'emp392@example.co.jp','0',0,'2021-04-01 10:23:45', '2021-04-01 10:23:45');
INSERT INTO employee(name, kana, hire_date, post_id, dept_id, tel, email, password, delete_flag, create_at, update_at) VALUES 
            ('桜凛月', 'サクラリツキ', '2021-04-01', 10, 4, '08053369334', 'emp132@example.co.jp','0',0,'2021-04-01 10:23:45', '2021-04-01 10:23:45');            




DROP TABLE IF EXISTS `emp_history`;
CREATE TABLE `emp_history` (
  `emp_history_id` int(11) PRIMARY KEY AUTO_INCREMENT,
  `emp_id` int(11) NOT NULL,
  `name` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL ,
  `kana` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL ,
  `hire_date` DATE COLLATE utf8mb4_unicode_ci DEFAULT NULL ,
  `post_id` int(11) DEFAULT NULL ,
  `dept_id` int(11) DEFAULT NULL ,
  `tel` varchar(11) COLLATE utf8mb4_unicode_ci DEFAULT NULL UNIQUE,
  `email` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL UNIQUE,
  `password` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `delete_flag` int(2) NOT NULL,
  `create_at` datetime COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `update_at` datetime COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `insert_history_at` datetime COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  FOREIGN KEY fk_post(post_id) REFERENCES post(post_id),
  FOREIGN KEY fk_dept(dept_id) REFERENCES department(dept_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

ALTER TABLE emp_history ADD INDEX idx_emp_id (emp_id); 


COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

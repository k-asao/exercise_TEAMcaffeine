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
  `post_name` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL 
);

INSERT INTO post (post_name) VALUES ('会長');  -- id = 1
INSERT INTO post (post_name) VALUES ('代表取締役社長');  -- id = 2
INSERT INTO post (post_name) VALUES ('取締役');  -- id = 3
INSERT INTO post (post_name) VALUES ('本部長');  -- id = 4
INSERT INTO post (post_name) VALUES ('部長');  -- id = 5
INSERT INTO post (post_name) VALUES ('次長');  -- id = 6
INSERT INTO post (post_name) VALUES ('課長');  -- id = 7
INSERT INTO post (post_name) VALUES ('係長');  -- id = 8
INSERT INTO post (post_name) VALUES ('主任');  -- id = 9
INSERT INTO post (post_name) VALUES ('一般社員');  -- id = 10

--
-- テーブルの構造 `department`
--
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department`(
  `dept_id` int(11)  PRIMARY KEY AUTO_INCREMENT,
  `dept_name` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL 
);

INSERT INTO department(dept_name) VALUES ('総務部');  -- id = 1
INSERT INTO department(dept_name) VALUES ('人事部');  -- id = 2
INSERT INTO department(dept_name) VALUES ('経理部');  -- id = 3
INSERT INTO department(dept_name) VALUES ('営業部');  -- id = 4
INSERT INTO department(dept_name) VALUES ('開発部');  -- id = 5
INSERT INTO department(dept_name) VALUES ('事業部');  -- id = 6
INSERT INTO department(dept_name) VALUES ('製造部');  -- id = 7
INSERT INTO department(dept_name) VALUES ('その他');  -- id = 8





--
-- テーブルの構造 `user`
--

DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `emp_id` int(11) NOT NULL,
  `name` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL ,
  `kana` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL ,
  `hire_date` DATE COLLATE utf8mb4_unicode_ci DEFAULT NULL ,
  `post_id` int(11) DEFAULT NULL ,
  `dept_id` int(11) DEFAULT NULL ,
  `tel` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL ,
  `email` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL ,
  FOREIGN KEY fk_post(post_id) REFERENCES post(post_id),
  FOREIGN KEY fk_dept(dept_id) REFERENCES department(dept_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- ダンプしたテーブルのインデックス
--

--
-- テーブルのインデックス `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`emp_id`);

--
-- ダンプしたテーブルの AUTO_INCREMENT
--

--
-- テーブルの AUTO_INCREMENT `user`
--
ALTER TABLE `employee`
  MODIFY `emp_id` int(11) NOT NULL AUTO_INCREMENT;


INSERT INTO employee(name, kana, hire_date, post_id, dept_id, tel, email) VALUES 
            ('海馬瀬人', 'カイバセト', '1972-04-01', 1, 8, '08024338464', 'emp001@example.co.jp');
INSERT INTO employee(name, kana, hire_date, post_id, dept_id, tel, email) VALUES 
            ('麻倉葉', 'アサクラヨウ', '1977-08-01', 2, 8, '08054833414', 'emp002@example.co.jp');
INSERT INTO employee(name, kana, hire_date, post_id, dept_id, tel, email) VALUES 
            ('ランデル・オーランド', 'ランデル・オーランド', '1976-04-01', 3, 8, '08078330868', 'emp003@example.co.jp');
INSERT INTO employee(name, kana, hire_date, post_id, dept_id, tel, email) VALUES 
            ('虎杖悠二', 'イタドリユウジ', '1979-04-01', 3, 8, '08019258645', 'emp004@example.co.jp');
INSERT INTO employee(name, kana, hire_date, post_id, dept_id, tel, email) VALUES 
            ('阿良々木暦', 'アララギコヨミ', '1982-04-01', 6, 8, '08081510801', 'emp005@example.co.jp');
INSERT INTO employee(name, kana, hire_date, post_id, dept_id, tel, email) VALUES 
            ('立花響', 'タチバナヒビキ', '1984-04-01', 4, 8, '08000320846', 'emp006@example.co.jp');
INSERT INTO employee(name, kana, hire_date, post_id, dept_id, tel, email) VALUES 
            ('上条当麻', 'カミジョウトウマ', '1985-04-01', 5, 8, '08012532148', 'emp007@example.co.jp');
INSERT INTO employee(name, kana, hire_date, post_id, dept_id, tel, email) VALUES 
            ('禅院真希', 'ゼンインマキ', '1986-04-01', 5, 8, '08087327823', 'emp008@example.co.jp');
INSERT INTO employee(name, kana, hire_date, post_id, dept_id, tel, email) VALUES 
            ('比企谷八幡', 'ヒキガヤハチマン', '1986-04-01', 7, 8, '08097651275', 'emp009@example.co.jp');
INSERT INTO employee(name, kana, hire_date, post_id, dept_id, tel, email) VALUES 
            ('槙島聖護', 'マキシマショウゴ', '1992-04-01', 4, 8, '08078322964', 'emp010@example.co.jp');
INSERT INTO employee(name, kana, hire_date, post_id, dept_id, tel, email) VALUES 
            ('月ノ美兎', 'ツキノミト', '1993-04-01', 4, 8, '08019728365', 'emp011@example.co.jp');
INSERT INTO employee(name, kana, hire_date, post_id, dept_id, tel, email) VALUES 
            ('黒崎一護', 'クロサキイチゴ', '2002-04-01', 8, 3, '08087631221', 'emp012@example.co.jp');
INSERT INTO employee(name, kana, hire_date, post_id, dept_id, tel, email) VALUES 
            ('爆轟克己', 'バクゴウカツキ', '2003-04-01', 6, 6, '08014513243', 'emp013@example.co.jp');
INSERT INTO employee(name, kana, hire_date, post_id, dept_id, tel, email) VALUES 
            ('工藤新一', 'クドウシンイチ', '2006-04-01', 6, 5, '08048541452', 'emp014@example.co.jp');
INSERT INTO employee(name, kana, hire_date, post_id, dept_id, tel, email) VALUES 
            ('笠原郁', 'カサハライク', '2008-04-01', 8, 3, '08067337634', 'emp015@example.co.jp');
INSERT INTO employee(name, kana, hire_date, post_id, dept_id, tel, email) VALUES 
            ('岡部倫太郎', 'オカベリンタロウ', '2012-04-01', 9, 8, '08094628466', 'emp015@example.co.jp');
INSERT INTO employee(name, kana, hire_date, post_id, dept_id, tel, email) VALUES 
            ('有馬公生', 'アリマコウセイ', '2014-04-01', 9, 5, '08027452947', 'emp015@example.co.jp');
INSERT INTO employee(name, kana, hire_date, post_id, dept_id, tel, email) VALUES 
            ('金木研', 'カネキケン', '2017-04-01', 10, 1, '08000388462', 'emp015@example.co.jp');
INSERT INTO employee(name, kana, hire_date, post_id, dept_id, tel, email) VALUES 
            ('夏目貴志', 'ナツメタカシ', '2020-04-01', 10, 3, '08027551836', 'emp015@example.co.jp');
INSERT INTO employee(name, kana, hire_date, post_id, dept_id, tel, email) VALUES 
            ('関沢菊治', 'セキザワキクジ', '2021-04-01', 10, 6, '08064728912', 'emp015@example.co.jp');

COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

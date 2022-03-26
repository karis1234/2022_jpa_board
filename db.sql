CREATE TABLE article(
    id BIGINT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    reg_date DATETIME NOT NULL,
    update_date DATETIME NOT NULL,
    title VARCHAR(100) NOT NULL,
    `body` TEXT NOT NULL,
    `user_id` BIGINT UNSIGNED NOT NULL
);

# 회원데이터 생성
INSERT INTO article
SET reg_date = NOW(),
update_date = NOW(),
title = '제목1',
`body` = '내용1',
`user_id` = '1';

INSERT INTO article
SET reg_date = NOW(),
update_date = NOW(),
title = '제목2',
`body` = '내용2',
`user_id` = '2';

INSERT INTO article
SET reg_date = NOW(),
update_date = NOW(),
title = '제목3',
`body` = '내용3',
`user_id` = '3';

SELECT *
FROM article;
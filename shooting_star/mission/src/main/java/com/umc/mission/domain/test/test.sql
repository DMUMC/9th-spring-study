-- UMC 9기 미션 챌린지 테스트 데이터
-- 실행 전, 데이터베이스의 모든 테이블 데이터를 삭제(TRUNCATE)하는 것을 권장합니다.

-- 외래 키 제약 조건 비활성화
SET FOREIGN_KEY_CHECKS = 0;

-- 모든 테이블 TRUNCATE (데이터 삭제)
TRUNCATE TABLE region;
TRUNCATE TABLE store_category;
TRUNCATE TABLE member;
TRUNCATE TABLE store;
TRUNCATE TABLE mission;
TRUNCATE TABLE review;
TRUNCATE TABLE member_mission;
TRUNCATE TABLE point_history;
TRUNCATE TABLE region_mission_stats;
TRUNCATE TABLE member_preferred_food;
TRUNCATE TABLE review_image;
TRUNCATE TABLE store_image;
-- 추가적인 테이블이 있다면 여기에 TRUNCATE 문을 추가하세요.

-- 외래 키 제약 조건 다시 활성화
SET FOREIGN_KEY_CHECKS = 1;


-- 1. 독립적인 엔티티 데이터 삽입
INSERT INTO region (name, description, created_at, updated_at)
VALUES ('서울', '수도권 지역입니다.', NOW(), NOW());

INSERT INTO store_category (name, created_at, updated_at)
VALUES ('한식', NOW(), NOW());

INSERT INTO member (email, name, nickname, phone_num, gender, profile_image, social_type, status, inactive_date, created_at, updated_at)
VALUES ('test@umc.com', '김테스트', 'UMC챌린저', '010-1234-5678', 'MALE', null, 'KAKAO', 'ACTIVE', null, NOW(), NOW());


-- 2. 의존성을 가진 엔티티 데이터 삽입
-- 가게 (id=1)
INSERT INTO store (region_id, category_id, name, address, phone_num, description, latitude, longitude, rating, review_count, image_url, created_at, updated_at)
VALUES (1, 1, 'UMC 한식당', '서울시 강남구 테헤란로 123', '02-111-2222', 'UMC 학생들이 자주 찾는 맛집입니다.', 37.5015, 127.0395, 0.0, 0, null, NOW(), NOW());


-- 3-1. 리뷰 테스트 데이터 (가게 id=1, 회원 id=1)
INSERT INTO review (member_id, store_id, member_mission_id, rating, content, status, deleted_at, created_at, updated_at)
VALUES (1, 1, null, 4.5, '음식이 정말 맛있고 분위기도 좋아요! 추천합니다.', 'ACTIVE', null, NOW(), NOW());

INSERT INTO review (member_id, store_id, member_mission_id, rating, content, status, deleted_at, created_at, updated_at)
VALUES (1, 1, null, 5.0, '인생 맛집 등극입니다. 사장님도 친절하세요.', 'ACTIVE', null, NOW() + INTERVAL 1 DAY, NOW() + INTERVAL 1 DAY);

INSERT INTO review (member_id, store_id, member_mission_id, rating, content, status, deleted_at, created_at, updated_at)
VALUES (1, 1, null, 3.5, '맛은 괜찮았는데, 사람이 너무 많아서 정신이 없었어요.', 'ACTIVE', null, NOW() + INTERVAL 2 DAY, NOW() + INTERVAL 2 DAY);

INSERT INTO review (member_id, store_id, member_mission_id, rating, content, status, deleted_at, created_at, updated_at)
VALUES (1, 1, null, 4.0, '가성비가 좋은 것 같아요. 잘 먹고 갑니다!', 'ACTIVE', null, NOW() + INTERVAL 3 DAY, NOW() + INTERVAL 3 DAY);


-- 3-2. 미션 테스트 데이터 (가게 id=1)
-- 페이징 테스트를 위해 6개 삽입 (페이지당 5개)
INSERT INTO mission (store_id, title, description, reward_point, deadline_days, status, created_at, updated_at)
VALUES (1, '리뷰 작성하고 포인트 받기', '음식 사진과 함께 정성스러운 리뷰를 작성해주세요!', 100, 30, 'ACTIVE', NOW(), NOW());

INSERT INTO mission (store_id, title, description, reward_point, deadline_days, status, created_at, updated_at)
VALUES (1, '가게의 숨은 메뉴 찾기', '메뉴판에 없는 비밀 메뉴를 찾아 주문하고 인증샷을 남겨주세요.', 500, 14, 'ACTIVE', NOW(), NOW());

INSERT INTO mission (store_id, title, description, reward_point, deadline_days, status, created_at, updated_at)
VALUES (1, '친구 3명과 함께 방문하기', '3인 이상 방문하여 식사 후, 함께 찍은 사진을 보여주세요.', 300, 60, 'ACTIVE', NOW(), NOW());

INSERT INTO mission (store_id, title, description, reward_point, deadline_days, status, created_at, updated_at)
VALUES (1, '대표 메뉴 2가지 주문하기', '가게의 대표 메뉴인 김치찌개와 제육볶음을 함께 주문해주세요.', 150, 30, 'ACTIVE', NOW(), NOW());

INSERT INTO mission (store_id, title, description, reward_point, deadline_days, status, created_at, updated_at)
VALUES (1, '첫 방문 인증하기', '가게에 처음 방문하셨다면, 사장님께 말씀드리고 10% 할인을 받으세요!', 50, 90, 'ACTIVE', NOW(), NOW());

INSERT INTO mission (store_id, title, description, reward_point, deadline_days, status, created_at, updated_at)
VALUES (1, '비 오는 날 방문해서 막걸리 마시기', '비 오는 날 방문 시, 막걸리 1병을 서비스로 드립니다.', 200, 30, 'ACTIVE', NOW(), NOW());

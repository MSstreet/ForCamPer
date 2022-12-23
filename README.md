# ForCamPer

##  MASON
## 2022-12-19

- 초기 프로젝트 생성
- 캠핑장 공공 데이터 api 선정
- 데이터 JSON 파싱

#### ⚠️ 발생한 이슈 및 해결

1. JSON으로 파싱 시도 할 때 JsonObject not found 에러 발생 

⚒️ 해결 방법  
- 올바른 생성자를 사용하지 않았음 (Stack Overflow 답변 통해 해결)
- 에러가 났던 코드  JSONObject obj = new JSONObject(result);
- 에러 해결 코드  JSONObject obj = new JSONObject(result.toString());

## 2022-12-20

-캠핑 리스트 DB 저장


## 2022-12-21

-캠핑 리스트 DB 저장
-QueryDsl 적용, 페이징 기능 적용
-키워드별 검색 기능 적용
-캠핑장 리스트 출력


## 2022-12-22

## 2022-12-23

 -게시판 기능 추가

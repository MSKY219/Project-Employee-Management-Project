# Project-Employee-Management-Project
1. 개발환경
  - OpenJDK 17.0.2
  - IntelliJ IDEA Community
  - Spring Boot 3.1.6
  - gradle

2. Dependencies / 라이브러리 / 기타 툴
   - Lombok
   - Spring Web : IntelliJ에서 ApachTomcat을 사용할 수 있도록 해주는 디펜던시.
   - MySql Driver
   - MyBatis Framework
   - Thymeleaf : JSP, Freemarker와 같은 템플릿 엔진의 일종으로, 백엔드 서버에서 HTML을 동적으로 렌더링 하는 용도로 사용되며 JSP처럼 확장자를 변경할 필요 없이 HTML 확장자를 유지할 수 있다.
   - 카카오 주소 API
   - ERD Cloud : DB 설계 / https://www.erdcloud.com/d/4FEP4mhkLXo4AuHNi
   - Figma : Front 페이지 와이어프레임 설계 / https://www.figma.com/file/k7VO69k6enrl3GyJw4sPDh/Untitled?type=design&node-id=0-1&mode=design&t=EaVk7eoAqqjVuh1X-0
   - jquery 라이브러리
   - jquery debounce : 유효성 검사 시, 입력 지연 기능. (eg. 아이디를 완료하고 1초동안 아무런 입력이 없을 시 DB에 중복 조회)

3. 기능정의
   1) 직원
      - 등록
         - Validation 기능을 활용해 필수 항목 지정.
         - 이미지 등록 기능.
         - 카카오 주소 API를 사용하여 도로명 주소를 입력 가능.
         - DB에서 이메일 중복 확인 가능.

      - 조회
         - 등록된 직원들을 상태, 기술스택, 등급, 기간 등의 기준을 사용해 검색 가능.
         - Paging 처리.
         - 상세 조회 시, 해당 직원의 정보를 한 눈에 볼 수 있음.
         - 해당 직원이 참여한 프로젝트의 대략적인 내용을 볼 수 있음.

      - 수정
         - Scheduling 사용해 이미지 변경 시, 기존 이미지는 10분마다 Local Storage에서 제거 됨.
         - Validation을 기능을 활용해 필수 항목 지정
         - 수정하는 회원의 이메일은 중복 Validation에 걸리지 않도록 지정.
            -<if test="id != '' || id != null">and e.id != #{id}</if>

      - 삭제
         - DELETE 대신 UPDATE를 사용해 직원의 상태 전환(Y -> N)
        
   2) 프로젝트
      - 등록
         - Validation을 기능을 활용해 필수 항목 지정

      - 조회
         - 등록된 프로젝트를 검색 조건을 사용해 검색 가능.
         - Paging 처리
         - 상세 조회 시, 해당 프로젝트의 정보를 한눈에 볼 수 있음.
         - 참여한 전체 직원을 확인할 수 있음.

      - 수정
         - Validation을 기능을 활용해 필수 항목 지정
         - 날짜 일괄 수정 기능.

      - 삭제
         - DELETE 대신 UPDATE를 사용해 프로젝트의 상태 전환(Y -> N)
         - 프로젝트 안에 직원이 있는 상태에서 삭제 시, 상태 전환 되지 않음.
     
   3) 코드 테이블을 활용한 DB 정규화 및 세분화
      - 

   4) 코드 테이블 등록, 조회, 수정, 삭제 기능 추가 예정.
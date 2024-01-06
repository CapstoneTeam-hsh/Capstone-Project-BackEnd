# Capstone-Project
팀 hsh의 캡스톤 프로젝트 "자비스"

## 📝 규칙

- `커밋 컨벤션`

    - Feat: 새로운 기능 추가
    - Fix: 버그 수정
    - Docs: 문서 수정
    - Style: 코드 포맷팅, 세미콜론 누락, 코드 변경이 없는 경우
    - Refactor: 코드 리팩토링
    - Test: 테스트 코드, 리팩토링 테스트 코드 추가
    - Chore: 빌드 업무 수정, 패키지 매니저 수정
<br><br>
      
- `issue 규칙`
    - 참고: [https://velog.io/@junh0328/협업을-위한-깃허브-이슈-작성하기](https://velog.io/@junh0328/%ED%98%91%EC%97%85%EC%9D%84-%EC%9C%84%ED%95%9C-%EA%B9%83%ED%97%88%EB%B8%8C-%EC%9D%B4%EC%8A%88-%EC%9E%91%EC%84%B1%ED%95%98%EA%B8%B0)
    - 레이블 참고:
      [https://github.com/modolee/github-initial-settings](https://github.com/modolee/github-initial-settings)
    - 제목 참고: [https://doublesprogramming.tistory.com/256](https://doublesprogramming.tistory.com/256)
      <br><br>
    - 템플릿
        - issue 제목
            - 예시: **[Feat] 이슈 정리**
        - issue 템플릿

            ```markdown
            ## 📋 이슈 내용
            
            ## ✅ 체크리스트
            
            ## 📚 레퍼런스
            
            ```
        - 제목 예시
            - [Add] UI button 구현
    <br><br>
- `branch 규칙`
    - 각자의 영어 이름을 딴 branch 명을 사용한다.
    - 예시: 
    ```
  git checkout -b <브랜치명>      
  git checkout -b wonjeong
    ```
    
- `commit message 규칙`
    - 참고: [https://doublesprogramming.tistory.com/256](https://doublesprogramming.tistory.com/256)
    - [종류] 메시지 - #이슈번호
    - 예시
        - [Feat] todo-list 회원 API 엔티티 구현 - #2
        - [Fix] todo-list 회원 단건 조회 서비스 에러 수정 - #2
    <br><br>
- `PR 규칙`
    - PR 템플릿

        ```markdown
        ## 📋 이슈 번호
        
        ## 🛠 구현 사항
        
        ## 📚 기타
        
        ```

- `merge message 규칙`
    - [Merge] 브랜치 이름 - #Issue 번호 혹은 PR 번호
    - 예시
        - [Merge] main - #9
    <br><br>

    # ERD 다이어그램
    <img width="658" alt="image" src="https://github.com/inu-appcenter/server-study-15.5/assets/115551339/ce464dd3-ff56-4bdc-ada0-618832dbdbdc">


    ## User API

| index | Method | URI | Description | Request Parameters | Response Parameters | HTTP Status |
| --- | --- | --- | --- | --- | --- | --- |
| 1 | POST | /users | 유저등록(생성) | name: 유저 이름(String,required) email: 유저 이메일(String,required) userId: 유저 아이디(String,required) password: 유저 비밀번호(String,required)| responseCode:“USER_RESISTED”responseMessage: “유저 등록 완료” data: (유저 정보를 담은Dto(UserDto) | 201: Created(요청이 정상적으로 처리됨)400: Bad Request(요청 파라미터에 문제가 있는 경우) |
| 2 | DELETE | /users/{userId} | 유저 계정 삭제(삭제) | userId:유저의 아이디(Long,required) | responseCode:“User_DELETED” responseMessage: “유저 등록 해제” | 204: No Content(요청이 정상적으로 처리 됨)404: Not Found(해당하는 식별자가 존재하지 않는 경우) |
| 3 | GET | /users/{userId} | 유저 정보 조회 | userId: 유저 아이디(Long,required)  | responseCode:“USER_FOUND” reponseMessage: “유저 프로필 조회 완료”data: (유저  정보를 담은 Dto(UserDto) | 200: OK (요청이 정상적으로 처리됨404: Not Found (해당하는 식별자가 존재하지 않는 경우) |
| 4 | PUT | /users/{userId} | 유저 정보 수정 | userId: 유저 아이디(Long, required)name: 유저 이름(String,required) email: 유저 이메일(String,required) userId: 유저 아이디(String,required) password: 유저 비밀번호(String,required) | responseCode:"USER_UPDATED"responseMessage: "유저 정보 수정 완료"data: (유저 정보를 담은 Dto (UserDto) | 200: OK (요청이 정상적으로 처리됨)400: Bad Request (요청 파라미터에 문제가 있는 경우) |
| 5 | GET | /users/todos/{userId} | 유저 번호로 게시글 조회 | userId:유저의 아이디(Long,required) | responseCode:"USERBOARD_FOUND" responseMessage: "유저의 투두 조회 완료"data: (유저 리스트에 있는 게시물Dto(todoDto) | 200: OK (요청이 정상적으로 처리됨404: Not Found (해당하는 식별자가 존재하지 않는 경우) |
---

## Todo API

| index | Method | URI | Description | Request Parameters | Response Parameters | HTTP Status |
| --- | --- | --- | --- | --- | --- | --- |
| 1 | POST | /todos/{userId} | 게시물 작성 | user: 유저 객체(User,required) title: 투두 제목(String,required) content: 내용(String, required) | reponseCode: “BOARD_REGISTERD” responseMessage: “게시물 등록 완료”data: (게시물 정보를 담은 Dto(BoardDto) | 201: Created (요청이 정상적으로 처리됨)400: Bad Request (요청 파라미터에 문제가 있는 경우) |
| 2 | DELETE | /todos/{todoId} | 투두 삭제 | todoId:투두 번호(Long,equired) | responseCode: “todo_DELETED” responseMessage: “게시물  삭제 완료” | 200: Ok (요청이 정상적으로 처리됨) 404: Not Found(해당하는 식별자가 존재하지 않는 경우) |
| 3 | PUT | /todos/{todoId} | 투두 수정 | userId: 유저아이디(Long,required) title: 투두 제목(String,required) content: 내용(String, required) checked: 할일 성공여부(boolean) | responseCode: "BOARD_UPDATED"responseMessage: "게시물 수정 완료"data: (게시물 정보를 담은 Dto (BoardDto)) | 200: OK (요청이 정상적으로 처리됨)400: Bad Request (요청 파라미터에 문제가 있는 경우)404: Not Found(해당하는 식별자가 존재하지 않는 경우) |
| 4 | GET | /todos/{todoId} | 투두 아이디로 투두 검색 | todoId: 투두 번호(Long,required) | responseCode: “BOARD_FOUND”responseMessage: “ 게시물 번호로 조회 완료”data :(해당 투두 정보를 담은 Dto(todoDto) | 200: OK (요청이 정상적으로 처리됨)404: Not Found (해당하는 식별자가 존재하지 않는 경우 |
| 5 | GET | /todos/{keyword} | 투두 제목 키워드로 조회 | keyword: 키워드(String,required) | responseCode: “BOARD_FOUND”responseMessage: “ 투두 제목 키워드로 조회 완료”data :(해당 투두 정보를 담은 Dto(todoDto) | 200: OK (요청이 정상적으로 처리됨)404: Not Found (해당하는 식별자가 존재하지 않는 경우 |
    
---

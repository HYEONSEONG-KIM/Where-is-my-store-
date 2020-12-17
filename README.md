# Where_is_my_store??  
소상공인을 위한 커뮤니케이션 플랫폼📣
----------------------------------------
----------------------------------------
## 제안 배경
- 코로나로 인해 급격히 침체된 경제상황으로 인해 자영업자의 피해가 극심하며 폐업으로 가게를 넘겨야 하는 상황에서 중개수수료 인한 자영업자의 부담이 증가
- 임차인 & 임대인이 매매하려는 가게를 직접 게시하고 거래하므로 중개수수료와 권리금에 대한 부담을 줄이기 위해 계획
- 또한 지도에 지난 3년간의 폐업 현황을 표시하여 새로 창업하려는 자영업자에게 기본적인 상권 분석에 대한 정보 제공
- 자영업자&창업 꿈나무를 위한 커뮤니케이션 플랫폼(어플리케이션) 제작에 목표
- 이 프로젝트는 한밭대학교 HRD '빅데이터 분석을 이용한 보안 분석모델 개발'과정의 최종 팀프로젝트(팀원 : 3명)
-------------
## 개발 기간
- 2020/11/25~12/18
-------------
## 순서도&구조도
![image](https://user-images.githubusercontent.com/70748105/102423986-974d0a00-404d-11eb-885d-c9e16e016d5b.png)
--------------
## 개발도구
![image](https://user-images.githubusercontent.com/70748105/102424241-15111580-404e-11eb-8143-a02fb03240cd.png)
---------------
## 상세기능
- 회원가입 & 로그인
- 매매
- 현황
- 채팅
- 마이 페이지
- 관리자
----------------
### 회원가입 & 로그인
- FireBase를 이용, RealTime Database에 회원정보를 저장할 수 있는 Users 테이블을 생성
- 입력한 이메일의 중복 여부 확인 후 아이디를 생성과 동시에 Users 테이블에 저장
- 로그인 화면에서 이메일과 비밀번호를 정확히 입력하면, 데이터베이스 안의 정보를 읽어 로그인
![회원가입 및 로그인](https://user-images.githubusercontent.com/70748105/102426382-9a96c480-4052-11eb-8e74-28864041ee30.gif)
![image](https://user-images.githubusercontent.com/70748105/102426561-e5b0d780-4052-11eb-8d37-18600cc19533.png)

### 매매
- 매매 목록 
 - 사용자가 등록한 매매, 게시 글 목록 표시 클릭 시 상세 페이지로 이동
- 글쓰기
 - 이미지, 주소, 이름, 가격, 제목, 내용 등의 내용을 입력 후 Realtime Database에 전송
 - 이미지는 Firebase Storage에 저장
- 게시글 상세 페이지
 - 업로드 된 이미지를 ViewPager로 수평 슬라이드로 확인, 이미지 개수만큼 indicator 표시
 - 게시글 상세 정보 확인
 - 이름 클릭 시, 해당 유저의 정복 확인 액티비티로 전환
- 유저 정보
 - 상단에 표시된 판매자 이름을 누르면 판매자 정보 액티비티로 이동
 - 판매 게시글을 누르면, 해당 유저가 업로드한 게시글을(전체, 거래, 거래 완료) 세 가지 탭으로 확인
 - 신고 버튼 클릭 시 해당 유저를 신고하는 액티비티로 전환
- 신고하기
 - 게시글, 사용자 신고
 - 신고 시, Realtime Database의 Report에 저장
- 찾기
 - 사용자가 찾기 원하는 내용을 입력 시 제목, 내용에 해당 단어를 포함한 게시글 들을 리사이클뷰에 출력
 ![매매 글쓰기](https://user-images.githubusercontent.com/70748105/102426393-9ff40f00-4052-11eb-8bed-5f0106c3ed35.gif)
 ![매매 유저정보 신고 찾기](https://user-images.githubusercontent.com/70748105/102426398-a2eeff80-4052-11eb-879b-1545322eee7a.gif)
 ![image](https://user-images.githubusercontent.com/70748105/102426593-f2cdc680-4052-11eb-8aa4-202bd423b071.png)
 ![image](https://user-images.githubusercontent.com/70748105/102426613-fd885b80-4052-11eb-804c-5b518aa4f66a.png)
 
### 현황
- Local Data에서 데이터를 다운로드 하여 2018부터 폐업 현황과 각 지역의 좌표를 볼 수 있도록 데이터 전처리
- 전 처리한 데이터를 서버pc(cetos7)에 MYSQL로 저장하여 안드로이드로 데이터를 가져옴
- 가져온 데이터를 Google 지도 API에 표시하여 클라이언트가 각 지역별 폐업 현황을 쉽게 볼 수 있도록 구현
![현황](https://user-images.githubusercontent.com/70748105/102426415-abdfd100-4052-11eb-99bf-8a9ae0e60013.gif)

### 채팅
- 파이어베이스에 Chat room을 만들고 채팅 방이 생성되면 user와 message 하위 속성이 생성
- user의 아이디를 검색하여 채팅방이 중복되어 생성되지 않도록 구현
- recyclerview를 사용하여 실시간으로 DB에 저장된 message와 user 이름을 화면에 출력하는 방법으로 채팅 기능 구현
- 매매 기능에서 글을 올린 유저와 채팅을 시작하고, 채팅 목록에서 내가 속한 채팅 방 확인후 원하는 채팅 방에 접근 가능
![채팅1](https://user-images.githubusercontent.com/70748105/102426404-a6828680-4052-11eb-81f8-61dac66172a1.gif)
![채팅2](https://user-images.githubusercontent.com/70748105/102426408-a84c4a00-4052-11eb-8f73-ed6e6d45599b.gif)
![image](https://user-images.githubusercontent.com/70748105/102426638-0842f080-4053-11eb-81f7-6d456cda0870.png)
![image](https://user-images.githubusercontent.com/70748105/102426641-0b3de100-4053-11eb-8544-8c51c6310b3d.png)

### 마이 페이지
- 비밀번호 변경
 * 기존 비밀번호를 입력하여 올바르게 입력하면 변경페이지로 이동
 * 새 비밀번호와 확인을 입력 후 두 값이 일치하면 변경 성공
- 나의 매매 글
 * 나의 Pin값을 액티비티에 전달하여 데이터베이스에 접근 후 나의 매매 글만 표시
- 로그아웃
 * 해당 버튼을 누르면 다이얼로그로 로그아웃 의사를 물어본 뒤 확인을 누르면 기존의 User 정보는 null이 입력되고 로그인 액티비티로 전환
![마이페이지](https://user-images.githubusercontent.com/70748105/102426420-ada99480-4052-11eb-9a26-e752ea3e65d4.gif)
 
### 관리자 
- 신고 게시 글 & 사용자 처리
 * 신고된 게시 글 목록 표시, 처리 중인 게시 글만 표시되며 처리 완료 게시 글은 표시되지 않음
 * 게시 글 클릭 시 하단에 신고 사유 출력, 타당한 사유 시 게시 글 중지 버튼, 정상 게시 글 이라면 정상 게시 글 버튼 클릭
 ![관리자](https://user-images.githubusercontent.com/70748105/102426424-aedac180-4052-11eb-8a94-5f4bf5a9b58b.gif)
 ![image](https://user-images.githubusercontent.com/70748105/102426675-1d1f8400-4053-11eb-90a0-ade66a227335.png)
----------------
## 기대효과
- 특정 업종의 창업을 고려 할 때, 어느 지역이 유리한지 혹은 업종의 발전 추이를 보여줌으로 클라이언트에게 효율적인 선택을 할 수 있도록 도와줄 것으로 기대 
- 보다 쉽게 가계 폐업, 개업 시 매매, 인수를 진행할 수 있는 플랫폼 제공
- 더 나아가 부동산, 공연장 등 더 많은 분야로 확대 하여 통합적인 커뮤니케이션 플랫폼으로 발전할 수 있을 것으로 기대
-----------------
## 전체 시연 영상
- [Youtube링크](https://youtu.be/y6bUoQLKsGo)
- QR코드  
![image](https://user-images.githubusercontent.com/70748105/102426105-0593cb80-4052-11eb-9326-42ae31cab270.png)





 


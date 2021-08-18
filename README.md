# 파일 입출력 기반의 데이터 처리 자바 콘솔 프로젝트
## 주말농장 운영프로그램(java)

**요약 |**
고객과 직원, 관리자가 주말 농장 서비스를 이용할 수 있게 돕는 프로그램
<br><br>
**개발환경 |**
- 기간: 2021. 05. 03(월) ~ 2021. 05. 11(화) (9일간)
- 개발 플랫폼: Windows 10
- 개발 툴: Eclipse (version 4.19.0)
- 사용언어: Java (JDK 1.8)
<br><br>
**기획배경 |**
- 파테크 등 먹거리를 자급자족하는 것에 대한 소비자들의 관심증가
- 주말농장 서비스를 이용하는 고객들의 수요에 맞는 고객 맞춤형 서비스의 부재
- 작물에 대한 난이도별 커리큘럼과 수확한 작물에 대한 거래 시스템에 대한 고객의 니즈
- 관리자의 회원 및 직원관리, 재고수량파악에 대한 어려움
<br><br>
**판매대상 |**
주말 농장 관리자

**이용자 |**
주말 농장 고객, 관리자, 직원
<br><br>
**구현목표 |**
1. 주말농장 이용을 위한 텃밭대여 시스템

2. 주말농장 운영에 위한 회원 및 직원관리 시스템

3. 수확물에 대한 거래소 제공
<br><br>
**담당업무 |**

[로그인]
- 로그인 기능	회원(X), 직원(Y), 관리자(Z)는
각각의 고유받은 번호 4자리를 입력하여 로그인을 한 후,
자신의 상황에 맞는 초기화면을 확인할 수 있다.
*유효성 검사: 총 4글자가 아닌 경우,
고유의 알파벳을 벗어난 경우 에러메시지를 확인할 수 있다.

[회원]
- 내 농장 조회 메뉴 : 재배중인 농작물, 텃밭 현황, 농작물(씨앗) 구매 내역,
농기구 대여 내역, 농장 계약서 조회 기능

[직원, 관리자]
- 회원 농장 조회 메뉴	: 전체 회원들의 회원정보, 텃밭 현황, 농자재 구매 내역,
농기구 대여 내역, 농장계약서 조회 기능

***

**구현화면 |**

### [회원]

![0-0  로그인 화면](https://user-images.githubusercontent.com/76515187/129557191-c5bb08e5-a4c4-4113-9841-da856510893e.PNG)
![1-1-1  내 농장 조회](https://user-images.githubusercontent.com/76515187/129557231-35ab7970-aecd-41f2-afd1-13b677b2e11a.png)
![1-1-2  텃밭 현황](https://user-images.githubusercontent.com/76515187/129557234-eecde9fc-ab5f-4ae8-9d00-189ae1398ec6.png)
![1-1-4  농작물 구매내역](https://user-images.githubusercontent.com/76515187/129557237-8268658c-3bb2-46d6-9d36-e0fb2f168a83.png)
![1-1-5  농기구 대여 현황](https://user-images.githubusercontent.com/76515187/129557238-bf2c0683-dc67-4f92-816e-6469bcae3191.png)
![1-1-6  농장계약서조회](https://user-images.githubusercontent.com/76515187/129557239-f3173942-dc64-418a-a66a-43cbfe8575e7.png)
![1-1-6  농장계약서조회_실행창](https://user-images.githubusercontent.com/76515187/129557240-fe8d5769-721d-4ac9-8364-958a20c9ed79.png)

### [직원, 관리자]

![7-0-1 직원로그인](https://user-images.githubusercontent.com/76515187/129557243-a3b51061-d0c7-4beb-8a30-263baf0c39db.png)
![8-0-2 관리자로그인](https://user-images.githubusercontent.com/76515187/129557266-d0c2df43-1ff2-42eb-a447-9c7527741abb.png)
![7-2-0 회원농장 조회 메뉴화면](https://user-images.githubusercontent.com/76515187/129557244-0e8e7f79-f583-42cf-9d2e-956b9556f629.png)
![7-2-1-1  회원텃밭현황](https://user-images.githubusercontent.com/76515187/129557245-e0ca3f70-e2d6-4c21-a23d-c5251f0da53a.png)
![7-2-1-2  회원텃밭현황](https://user-images.githubusercontent.com/76515187/129557246-ea4e2c13-9ba9-429f-973a-2aadee12c78c.png)
![7-2-2-1 회원정보검색](https://user-images.githubusercontent.com/76515187/129557247-badceda1-eac8-4a02-9692-bb3a55e8bc8d.png)
![7-2-2-2 회원정보검색](https://user-images.githubusercontent.com/76515187/129557252-787b4bc3-6073-4dbe-bb97-12ac105596cd.png)
![7-2-3-1 회원농자재 구매내역](https://user-images.githubusercontent.com/76515187/129557253-673e5ea7-980b-49d3-9bb4-5b522fa132c2.png)
![7-2-3-2 회원농자재 구매내역](https://user-images.githubusercontent.com/76515187/129557254-13526693-2910-4eac-aea1-804900278fd9.png)
![7-2-4-1 회원농기구 구매내역](https://user-images.githubusercontent.com/76515187/129557256-2511cd72-6f21-4fbb-a7e8-59a368247dda.png)
![7-2-4-2 회원농기구 대여내역](https://user-images.githubusercontent.com/76515187/129557257-d31a3f9f-3f1e-4fda-8a24-93f6a72fa206.png)
![7-2-5-1 회원 농장계약서](https://user-images.githubusercontent.com/76515187/129557258-61b173ba-77bb-4314-ba7d-2a87b808e04a.png)
![7-2-5-2 회원 농장계약서](https://user-images.githubusercontent.com/76515187/129557260-01937aa8-ac4d-44b1-b7c7-092e13c9e5c9.png)
![7-2-5-3 회원 농장계약서](https://user-images.githubusercontent.com/76515187/129557263-289429d6-2627-4123-982b-391c9a069c5d.png)

***

**프로젝트 스토리 |**

쌍용교육센터의 첫번째 프로젝트였습니다. 최대한 많은 데이터를 다루면서도 기존에 구현되지 않은 서비스를 구현해보고자 했습니다. 
첫 프로젝트인 만큼, 팀원들과 열심히 주제를 설정하고 각 기능들을 요구분석서에 정리하였습니다. 
처음하는 요구분석이라 쉽지 않았지만, 이 프로그램의 클라이언트와 주 이용자들의 입장에서 생각하며 어떤 기능들이 구현되야할지 브레인스토밍의 시간을 가졌습니다. 
각 이해관계자(고객, 직원, 관리자)들의 니즈를 파악하여 '요구분석서'를 작성하였으며, 요구분석서에 따른 구현기능들을 정리한 '기능명세서'와 실제 화면으로 클라이언트에게 구현과정을 보여줄 수 있는 '화면설계'파일을 작성했습니다.
<br>
실제로 업무에 필요한 기술들은 수업시간때 배운 내용들을 기반으로 구현해보았습니다. 기능에 필요한 Class를 생성하고, getter & setter메소드를 통해 자료를 처리했습니다. 또한, ArrayList<T>, HashMap<T> 등을 사용하며 제네릭을 좀더 심도있게 활용할 수 있는 기회였습니다.	더미데이터를 생성하기 위해 Random, Math클래스를 활용하였고, 파일입출력을 위해 BufferedReader, Writer(new FileReader, Writer( "path" ), Scanner을 사용하여 사용자에게 데이터를 입력받았습니다. 또한, ProcessBuilder클래스를 이용하여 외부 회원 농장 계약서 파일 실행 시켜 프로그램의 현실감을 높일 수 있도록 하였습니다. 수업시간에 배운 내용들을 프로젝트에 하나하나 적용해보며 원하는 기능으로 구현하는 과정에서 큰 기쁨과 보람을 느꼈습니다. 
<br>
특히, 팀원 모두가 처음으로 하는 프로젝트였기 때문에 매 순간이 질문의 과정이었고 어려웠습니다.
하지만, 팀원들이 있었기에 서로 의지하며 어려운 문제들을 풀어나갈 수 있었습니다. 약 1주일이라는 짧은 기간 동안 최대한 일정에 맞춰 프로젝트를 마감하는 것이 목표였고, 저희 조는 마감일을 지키며 프로젝트를 제출할 수 있었습니다.

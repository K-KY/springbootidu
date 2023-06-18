# springbootidu

[githubAction](https://github.com/jojoldu/freelec-springboot2-webservice/issues/806#issue-1299987948) 나중에 이거 해보기

# 범인??
***
nohup java -jar \
-Dspring.config.location=/home/ec2-user/app/step1/springbootidu/src/main/resources/application.properties,\
/home/ec2-user/app/application-oauth.properties,\
/home/ec2-user/app/application-real-db.properties,\
/home/ec2-user/app/step1/springbootidu/src/main/resources/application-real.properties\
-Dspring.profiles.active=real \
$JAR_NAME > $REPOSITORY/nohup.out 2>&1 &
***
책에서는 이렇게 알려줬음

그러나?
* Error: Unable to access jarfile /home/ec2-user/app/application-oauth.properties,
이런 에러가 발생
* 위에서 부터 아래로 진행하기 때문에 맨위에있는 문제점인 application-oauth 밖에 안나온거지 
싹다 문제임
* nohup java -jar $JAR_NAME \
  -Dspring.config.location=/home/ec2-user/app/step1/springbootidu/src/main/resources/application.properties,\
  /home/ec2-user/app/application-oauth.properties,\
  /home/ec2-user/app/application-real-db.properties,\
  /home/ec2-user/app/step1/springbootidu/src/main/resources/application-real.properties\
  -Dspring.profiles.active=real \
  $JAR_NAME > $REPOSITORY/nohup.out 2>&1 &
그래서 $JAR_NAME 을 앞에 넣어서 실행시켰더니 배포 성공
# 나는 이게 정상적으로 되고 있는줄 알았음
+ 그런데? 구글 로그인이 안되는것
+ 알고보니 이게 위에서부터 다 찾은다음 $JAR_NAME 으로 실행하는건데 내가 처음부터 실행하게 해놔서 밑에 에러는 안보고 지나간거임
+ 다 지우고 배포하면 똑같이 됨
+ 그러니까 이렇게 해놓으면 될리가 없음
### 책 깃허브 들어가서 저 부분 복붙했는데 될것같은데?
+ 내가 posts 랑 users 를 삭제해서 지금 안되는중이긴 함
+ 왜 자동으로 생성되지 않는걸까?
#### 그냥 ec2 real-db 들어가서 spring.jpa.hibernate.ddl-auto=none 로 돼있는거 update 로 바꿈
* 일단 이거 때문이니까 테이블이 생성은 됐음
# 유력 용의자  >> 왜 구글 로그인이 안되는가?
- ★ travis-ci 에서 배포 했을때 생성되는 hibernate 문법이 mariadb 문법과 맞지 않아서
users 테이블과 post 테이블을 생성 못하고 있는중 ★
#### 이게 아니었음
## user 가 예약어라서 발생한 문제
자동생성하는 테이블을 users 로 바꿔서 해결

***
+ mysql 문법으로 생성하게 만들었음
+ 
### posts,users 테이블이 없으면 로그인이 안되는가?
직접 삭제해서 증명해 봄
***
* 증명 완료
* users table 을 삭제했을 때 에러페이지가 나옴
* posts 테이블은 존재 하는듯?
* spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
* 이 옵션을 넣었을 때 에러가 나고있음 이거 해결해야함
* 이건 일정 버전 이상 스프링부트에선 
* spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL57Dialect
  spring.jpa.properties.hibernate.dialect.storage_engine=innodb
이렇게 사용해야함
* 
***


로그인만 안되는게 아니었음 테이블을 삭제했더니 페이지 자체가 안뜸

* 왜지? 내가 배포 하기전에 테이블을 먼저 지워서 배포하면서 다시 테이블이 생성 되어야 하는데 테이블이 생기지 않음
* * * 생각해보니 posts 와 users 테이블은 내가 ec2 에 직접 만들었음 hibernate 가 자동으로 만들어준게 아님 
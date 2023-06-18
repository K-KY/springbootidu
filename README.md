# springbootidu

https://github.com/jojoldu/freelec-springboot2-webservice/issues/806#issue-1299987948 나중에 이거 해보기


# 유력 용의자  >> 왜 구글 로그인이 안되는가?
travis-ci 에서 배포 했을때 생성되는 hibernate 문법이 mariadb 문법과 맞지 않아서
users 테이블과 post 테이블을 생성 못하고 있는중
+ mysql 문법으로 생성하게 만들었음
+ 
## post,users 테이블이 없으면 로그인이 안되는가?
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
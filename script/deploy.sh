#!/bin/bash
#cd /opt/codedeploy-agent/deployment-root/deployment-logs/ codedeploy 실행 로그 확인
#nohup.out 도 확인할 것
REPOSITORY=/home/ec2-user/app/step2
PROJECT_NAME=springbootidu
rm -f $REPOSITORY/nohup.out
echo "> 파일 복사"
cp $REPOSITORY/zip/*.jar $REPOSITORY/ # $REPOSITORY/zip 의 확장자가 jar 인 파일을 모두 $REPOSITORY 로 복사

echo "> 현재 구동 중인 애플리케이션 pid 확인"

CURRENT_PID=$(pgrep -fl springbootidu | awk '{print $1}')
# pgrep :: springbootidu  라는 이름을 가진 프로세스를 검색
# grep jar :: 위에서 검색한 프로세스 중에서 jar 문자열을 포함하는 프로세스만 필터링
# grep -v "plain" :: plain 문자열을 포함하지 않는 문자열만 남기고 나머지 제외
# awk '{print $1}' :: 이전 단계에서 남은 프로세스 중 첫 번째 항목(PID) 만 추출한다\

if [ -z "$CURRENT_PID" ]; then
    echo "> 현재 구동 중인 애플리캐이션이 없음"
else
    echo "> kill -15 $CURRENT_PID"
    sudo kill -15 $CURRENT_PID
    sleep 5
fi

echo "> 새 애플리케이션 배포"

JAR_NAME=$(ls -tr $REPOSITORY/*.jar | grep -v "plain" | tail -n 1)
# ls -tr $REPOSITORY/*.jar :: REPOSITORY 디렉토리의 모든 .jar 파일을 수정시간에 따라 나열한다 old -> new
# tail -n 1 :: 위에서 얻은 파일 목록 중 가장 마지막의 파일을 선택한다.
# grep -v "plain" plain 문자열을 포함하지 않는 문자열만 남기고 나머지 제외

echo "> JAR_NAME: $JAR_NAME"
echo "> $JAR_NAME 에 실행권한 추가"
chmod +x $JAR_NAME
echo "> $JAR_NAME  실행"


#jar name 붙여서 해결
nohup java -jar \
    -Dspring.config.location=classpath:/application.properties,classpath:/application-real.properties,/home/ec2-user/app/application-oauth.properties,/home/ec2-user/app/application-real-db.properties\
      -Dspring.profiles.active=real \
      $JAR_NAME > $REPOSITORY/nohup.out 2>&1 &

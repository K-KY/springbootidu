#!/bin/bash
REPOSITORY=/root/app/step1
PROJECT_NAME=springbootidu

echo "> Build 파일 복사"

cp $REPOSITORY/zip/*.jar $REPOSITORY/

echo "> 현재 구동중인 어플리케이션 pid 확인"

CURRENT_PID=$(pgrep -fl springbootidu | grep jar | awk '{print $1}')



if [ -z "$CURRENT_PID" ]; then
    echo "> No running application"
else
    echo "> kill -15 $CURRENT_PID"
    kill -15 $CURRENT_PID
    sleep 5
fi

echo "> Deploy new application"
JAR_NAME=$(ls -tr $REPOSITORY/ | grep jar | grep -v "plain" | tail -n 1)
echo "> JAR Name: $JAR_NAME"

echo "> $JAR_NAME 에 실행권한 추가"
chmod +x $JAR_NAME

echo "> $JAR_NAME 실행"
nohup java -jar -Dspring.config.location=classpath:/application.properties,/root/app/application-oauth.properties,/root/app/application-real-db.properties,/root/app/step1/springbootidu/src/main/resources/application-real.properties -Dspring.profiles.active=real $JAR_NAME > $REPOSITORY/nohup.out 2>&1 &



#nohup java -jar -Dspring.config.location=classpath:/application.properties,/root/app/application-oauth.properties,/root/app/application-real-db.properties,/root/app/step1/springbootidu/src/main/resources/application-real.properties -Dspring.profiles.active=real  $REPOSITORY/$JAR_NAME 2>&1 &



#nohup java -jar $REPOSITORY/$JAR_NAME > $REPOSITORY/nohup.out 2>&1 &

#nohup java -jar \ -Dspring.config.location=classpath:/application.properties,/root/app/application-oauth.properties \$REPOSITORY/$JAR_NAME 2>&1 &
#nohup java -jar \ -Dspring.config.location=classpath:/application-real.properties,/root/app/application-real-db.properties -Dspring.profile.active=real \$REPOSITORY/$JAR_NAME 2>&1 &


#nohup java -jar $REPOSITORY/$JAR_NAME > $REPOSITORY/nohup.out 2>&1 &


#nohup java -jar -  Dspring.config.location=classpath:/application.properties,/root/app/application-oauth.properties $REPOSITORY/$JAR_NAME 2>&1 &

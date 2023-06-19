#!/usr/bin/env bash
ABSPATH=$(readlink -f $0)
ABSDIR=$(dirname $ABSPATH)
source ${ABSDIR}/profile.sh
REPOSITORY=/home/ec2-user/app/step3
PROJECT_NAME=springbootidu
rm -f $REPOSITORY/nohup.out
echo "> 파일 복사"
echo "> cp $REPOSITORY/zip/*.jar $REPOSITORY/"
cp $REPOSITORY/zip/*.jar $REPOSITORY/ # $REPOSITORY/zip 의 확장자가 jar 인 파일을 모두 $REPOSITORY 로 복사


echo "> 새 애플리케이션 배포"

JAR_NAME=$(ls -tr $REPOSITORY/*.jar | grep -v "plain" | tail -n 1)
# ls -tr $REPOSITORY/*.jar :: REPOSITORY 디렉토리의 모든 .jar 파일을 수정시간에 따라 나열한다 old -> new
# tail -n 1 :: 위에서 얻은 파일 목록 중 가장 마지막의 파일을 선택한다.
# grep -v "plain" plain 문자열을 포함하지 않는 문자열만 남기고 나머지 제외

echo "> JAR_NAME: $JAR_NAME"
echo "> $JAR_NAME 에 실행권한 추가"
chmod +x $JAR_NAME
echo "> $JAR_NAME  실행"
IDLE_PROFILE=$(find_idle_profile)
echo "> $JAR_NAME 를 profile=$IDLE_PROFILE 로 실행합니다."

nohup java -jar \
    -Dspring.config.location=classpath:/application.properties,classpath:/application-$IDLE_PROFILE.properties,/home/ec2-user/app/application-oauth.properties,/home/ec2-user/app/application-real-db.properties \
    -Dspring.profiles.active=$IDLE_PROFILE \
    $JAR_NAME > $REPOSITORY/nohup.out 2>&1 &
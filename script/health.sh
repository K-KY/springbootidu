#!/usr/bin/env bash

ABSPATH=$(readlink -f $0)
ABSDIR=$(dirname $ABSPATH)
source ${ABSDIR}/profile.sh
source ${ABSDIR}/switch.sh

IDLE_PORT=$(find_idle_port)

echo "> Health Check Start!"
echo "> IDLE_PORT: $IDLE_PORT"
echo "> curl -s http://localhost:$IDLE_PORT/profile "
sleep 10
for RETRY_COUNT in {1..10}
do
  RESPONSE=$(curl -s http://localhost:${IDLE_PORT}/profile)
  UP_COUNT=$(echo ${RESPONSE} | grep 'real' | wc -l)

  if [ ${UP_COUNT} -ge 1 ]
  # $up_count >= 1 ("real" 문자열이 있는지 검증)
  then
    echo "> Health check success"
    switch_proxy
    break
  else
    echo "> Health check 의 응답을 알수 없거나 실행중이 아님"
    echo "> Health check: ${RESPONSE}"
  fi

  if [ ${RETRY_COUNT} -eq 10 ]
  then
    echo "> Health check failed"
    echo "> 엔진엑스에 연결하지 않고 배포를 종료"
    exit 1
  fi
  echo "> 연결을 재시도 합니다"
  sleep 10
done
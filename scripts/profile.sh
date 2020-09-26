#! /usr/bin/env bash
# 쉬고 있는 profile찾기 : real1이 사용 중이면 real2가 쉬고 있고, 반대면 real1이 쉬고 있음

function find_idle_profile() {
  # 현재 nginx가 바라보는 스프링부트가 정상적으로 수행 중인지 확인한다.
  # 응답값을 HttpStatus로 받는다.
  # 정상이면 200, 오류가 발생하면 , 400~500대 에러가 발생하므로 400이상은 모두 예외로 보고 real2를 현재 profile로 사용한다.
  RESPONSE_CODE=$(curl -s -o /dev/null -w "%{http_code}" http://localhost/profile)

  if [ ${RESPONSE_CODE} -ge 400 ] # 400보다 크면(즉 40,x/50x 에러 모두 포함)

  then
    CURRENT_PROFILE=real2
  else
    CURRENT_PROFILE=$(curl -s http://localhost/profile)

  fi

  if [ ${CURRENT_PROFILE} == real1 ]
  then
    # nginx와 연결되어 있지 않은 profile을 말한다.
    # 스프링부트 프로젝트를 이 profile로 연결하기 위해 반환한다
    IDLE_PROFILE=real2
  else
    IDLE_PROFILE=real1
  fi
  # bash라는 스크립트는 값을 반환하는 기능이 없다
  # 그래서 제일 마지막 줄에 echo로 결과를 출력 후, 클라이언트에서 그 값을 잡아서($(find_idle_profile)) 사용한다
  # 중간에 echo를 사용하면 안 된다.
  echo "${IDLE_PROFILE}"
}

# 쉬고 있는 profile의 port찾기
function find_idle_port() {
  IDLE_PROFILE=$(find_idle_profile)
  
  if [ ${IDLE_PROFILE} == real ]
  then
    echo "8081"
  else
    echo "8082"
  fi
}
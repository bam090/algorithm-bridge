# 별 2개 이하 책 문제 접근 방식 카탈로그

## 목적과 범위

이 문서는 『코딩 테스트 합격자 되기 자바편』의 주제별 몸풀기·모의테스트 중 난이도 `★`와 `★★`인 문제 63개의 접근 방식을 학습 설계용으로 추상화한다.

- 분류·제목·별 개수는 [book-problem-boundaries.md](book-problem-boundaries.md)를 따른다.
- 모의테스트와 공개 문제는 Programmers 공식 페이지를 계약 근거로 삼는다.
- 책 자체 몸풀기는 [저자 공개 저장소](https://github.com/retrogemHK/codingtest_java)의 같은 번호 참고 구현으로 접근 순서만 확인한다.
- 책 문제의 본문·예시·해설·정답 코드는 이 문서에 복사하지 않는다.
- 이 문서는 생성·검증 에이전트용 내부 자료다. 학습자 문제 화면에는 링크나 조합 전체를 먼저 노출하지 않는다.

`접근 원자`는 다른 문제에서도 다시 선택할 수 있는 한 가지 판단이나 동작이다. `접근 조합`은 여러 원자를 실제 풀이 순서로 연결한 것이다.

## 05 배열 — 7문제

| 번호·구분 | 근거 | 접근 원자 | 접근 조합 |
|---|---|---|---|
| 01 몸풀기 ★ 배열 정렬하기 | [저자 참고](https://github.com/retrogemHK/codingtest_java/blob/main/solution/01.java) | 정렬 기준, 표준 정렬, 원본 변경 여부 | 요구 순서 확인 → 필요하면 복사 → 정렬 → 반환 |
| 02 몸풀기 ★★ 배열 제어하기 | [저자 참고](https://github.com/retrogemHK/codingtest_java/blob/main/solution/02.java) | 중복 제거, 내림차순, 기본형 배열 변환 | 고유 값만 남김 → 내림차순 정렬 → 요구 배열로 변환 |
| 03 모의 ★ 두 개 뽑아서 더하기 | [공식](https://school.programmers.co.kr/learn/courses/30/lessons/68644) | 서로 다른 두 인덱스, 모든 쌍, 집합, 정렬 | `i < j`인 쌍 열거 → 합 계산 → 중복 제거 → 오름차순 배열화 |
| 04 모의 ★ 모의고사 | [공식](https://school.programmers.co.kr/learn/courses/30/lessons/42840) | 반복 패턴, 나머지 인덱스, 여러 개수, 최댓값·동점 | 입력 순회 → 패턴을 `%`로 조회 → 대상별 점수 누적 → 최고점·동점자 수집 |
| 05 모의 ★ 행렬의 곱셈 | [공식](https://school.programmers.co.kr/learn/courses/30/lessons/12949) | 결과 크기, 행·열 대응, 내적, 삼중 반복 | 결과 크기 결정 → 결과 칸마다 첫 행렬의 행과 둘째 행렬의 열을 곱해 누적 |
| 06 모의 ★★ 실패율 | [공식](https://school.programmers.co.kr/learn/courses/30/lessons/42889) | 빈도표, 줄어드는 분모, 비율, 복합 정렬 | 단계별 인원 집계 → 도달 인원으로 비율 계산 → 분모 감소 → 비율·번호 기준 정렬 |
| 07 모의 ★★ 방문 길이 | [공식](https://school.programmers.co.kr/learn/courses/30/lessons/49994) | 명령 해석, 좌표 상태, 경계, 방향 없는 간선 집합 | 다음 좌표 계산 → 경계 검사 → 양 끝점으로 길 식별 → 처음 지난 길만 기록 |

핵심 원자군: 복사·정렬, 중복 제거, 인덱스 쌍, 반복 패턴, 집계·동점, 2차원 행·열, 빈도·비율, 좌표·간선.

## 06 스택 — 6문제

| 번호·구분 | 근거 | 접근 원자 | 접근 조합 |
|---|---|---|---|
| 08 몸풀기 ★★ 올바른 괄호 | [공식](https://school.programmers.co.kr/learn/courses/30/lessons/12909) | 최근 열린 값, `push`·`pop`, 조기 실패, 마지막 잔여 | 열면 저장 → 닫으면 최근 값과 짝 확인 → 끝에서 스택이 비었는지 확인 |
| 09 몸풀기 ★ 10진수를 2진수로 변환하기 | [저자 참고](https://github.com/retrogemHK/codingtest_java/blob/main/solution/09.java) | 나눗셈·나머지, 역순 생성, LIFO | 반복 나눗셈으로 나머지 저장 → 스택에서 꺼내 원래 출력 순서 생성 |
| 10 모의 ★ 괄호 회전하기 | [공식](https://school.programmers.co.kr/learn/courses/30/lessons/76502) | 원형 시작점, 여러 짝, 회전별 독립 상태 | 각 시작 위치 선택 → 길이만큼 원형 순회 → 스택 검증 → 성공 집계 |
| 11 모의 ★ 짝지어 제거하기 | [공식](https://school.programmers.co.kr/learn/courses/30/lessons/12973) | 최근 값 비교, 연쇄 상쇄 | 현재 값과 스택 위 비교 → 같으면 제거·다르면 저장 → 남은 값 확인 |
| 12 모의 ★★ 주식 가격 | [공식](https://school.programmers.co.kr/learn/courses/30/lessons/42584) | 미해결 인덱스, 단조 스택, 거리 | 현재 값이 조건을 깨는 동안 과거 인덱스 제거·거리 기록 → 현재 인덱스 저장 → 잔여 마감 |
| 13 모의 ★★ 크레인 인형뽑기 게임 | [공식](https://school.programmers.co.kr/learn/courses/30/lessons/64061) | 열별 스택, 명령 선택, 결과 스택 상쇄 | 2차원 열 전처리 → 지시된 열에서 꺼냄 → 결과 스택 위와 비교해 저장 또는 제거 |

핵심 원자군: LIFO, 조기 실패·잔여, 역순 복원, 원형 구간, 최근 값 상쇄, 미해결 인덱스, 여러 스택 파이프라인.

## 07 큐 — 3문제

| 번호·구분 | 근거 | 접근 원자 | 접근 조합 |
|---|---|---|---|
| 15 몸풀기 ★★ 요세푸스 문제 | [저자 참고](https://github.com/retrogemHK/codingtest_java/blob/main/solution/15.java) | FIFO, 앞→뒤 회전, 반복 제거 | 순서대로 넣음 → `K-1`개를 뒤로 이동 → 다음 항목 제거 → 한 항목까지 반복 |
| 16 모의 ★★ 기능 개발 | [공식](https://school.programmers.co.kr/learn/courses/30/lessons/42586) | 완료일 올림 계산, 앞 작업 장벽, 연속 묶음 | 작업별 완료일 계산 → 앞 작업을 기준일로 유지 → 기준 이하면 묶고 늦으면 묶음 확정 |
| 17 모의 ★★ 카드 뭉치 | [공식](https://school.programmers.co.kr/learn/courses/30/lessons/159994) | 두 큐의 맨 앞, 목표 순서, 선택 소비 | 목표 앞 확인 → 두 큐의 앞과 차례로 비교 → 일치 큐와 목표를 함께 제거 → 불일치 시 실패 |

핵심 원자군: 앞에서 꺼내 뒤에 넣기, 제거 후 다음 시작, 순서 장벽, 두 머리 중 선택.

## 08 해시 — 6문제

| 번호·구분 | 근거 | 접근 원자 | 접근 조합 |
|---|---|---|---|
| 18 몸풀기 ★ 두 수로 특정값 만들기 | [저자 참고](https://github.com/retrogemHK/codingtest_java/blob/main/solution/18.java) | 보수, 이전 값 집합, 포함 확인 | 현재 값의 필요한 짝 계산 → 이전 집합에서 확인 → 없으면 현재 값 저장 |
| 19 모의 ★ 완주하지 못한 선수 | [공식](https://school.programmers.co.kr/learn/courses/30/lessons/42576) | 빈도 맵, 다중집합 차이 | 한 목록의 개수 저장 → 다른 목록으로 차감 → 사용할 개수가 없는 항목 찾기 |
| 20 모의 ★★ 할인 행사 | [공식](https://school.programmers.co.kr/learn/courses/30/lessons/131127) | 목표 수량 맵, 고정 길이 창, 빈도 비교 | 목표 맵 생성 → 각 고정 구간의 빈도 생성·갱신 → 목표와 같은 구간 집계 |
| 21 모의 ★★ 오픈 채팅방 | [공식](https://school.programmers.co.kr/learn/courses/30/lessons/42888) | 안정 ID, 최신 속성, 명령 파싱, 두 번 순회 | 전체 기록으로 최종 속성 확정 → 다시 순회해 출력 대상 사건을 최종 속성으로 변환 |
| 22 모의 ★★ 베스트 앨범 | [공식](https://school.programmers.co.kr/learn/courses/30/lessons/42579) | 그룹화, 그룹 합계, 계층 정렬, 그룹별 제한 | 그룹별 합계·구성원 저장 → 그룹 순위 → 그룹 내부 순위 → 상위 항목 선택 |
| 23 모의 ★★ 신고 결과 받기 | [공식](https://school.programmers.co.kr/learn/courses/30/lessons/92334) | `Map<대상, Set<출처>>`, 관계 중복 제거, 기준 판정, 역전파 | 대상별 고유 출처 수집 → 기준 충족 대상 판정 → 관련 출처의 결과 증가 → 입력 순서로 배열화 |

핵심 원자군: 포함 확인·보수, 빈도·다중집합, 고정 창, 안정 ID·두 번 순회, 그룹 집계·계층 정렬, 관계 집합·역전파.

## 09 트리 — 3문제

| 번호·구분 | 근거 | 접근 원자 | 접근 조합 |
|---|---|---|---|
| 25 몸풀기 ★ 트리 순회 | [저자 참고](https://github.com/retrogemHK/codingtest_java/blob/main/solution/25.java) | 완전 이진 트리 배열, 자식 인덱스, 재귀 종료, 방문 시점 | 인덱스 범위 확인 → 왼쪽·오른쪽 재귀 → 방문 위치를 바꿔 전위·중위·후위 결과 생성 |
| 26 모의 ★ 예상 대진표 | [공식](https://school.programmers.co.kr/learn/courses/30/lessons/12985) | 부모 그룹 계산, 두 상태 수렴, 반복 횟수 | 두 번호를 다음 단계 번호로 변경 → 횟수 증가 → 같은 그룹이 될 때 종료 |
| 27 모의 ★★ 다단계 칫솔 판매 | [공식](https://school.programmers.co.kr/learn/courses/30/lessons/77486) | 이름→부모, 부모 사슬, 비율 분리, 누적, 원래 순서 | 부모 관계 구축 → 사건마다 값을 위로 전달·누적 → 지정된 입력 순서로 결과 생성 |

핵심 원자군: 자식 인덱스·재귀 순회, 부모로 수렴, 이름 기반 부모 사슬, 위쪽 전파·누적.

## 10 집합 — 3문제

| 번호·구분 | 근거 | 접근 원자 | 접근 조합 |
|---|---|---|---|
| 30 몸풀기 ★★ 유니온-파인드 | [저자 참고](https://github.com/retrogemHK/codingtest_java/blob/main/solution/30.java) | 대표 원소, 부모 배열, 루트 찾기, 경로 압축, 합치기 | 자기 집합으로 초기화 → 연산 판별 → 두 루트 연결 또는 두 루트 비교 |
| 31 모의 ★ 폰켓몬 | [공식](https://school.programmers.co.kr/learn/courses/30/lessons/1845) | 중복 제거, 고유 개수, 선택 상한 | 집합 크기 계산 → 선택 가능 개수 계산 → 두 상한 중 작은 값 선택 |
| 32 모의 ★ 영어 끝말잇기 | [공식](https://school.programmers.co.kr/learn/courses/30/lessons/12981) | 사용 이력 집합, 이웃 조건, 첫 실패, 몫·나머지 | 중복·연결 조건 확인 → 첫 실패 인덱스 확정 → 담당 번호와 차례로 변환 |

핵심 원자군은 서로 다른 두 갈래다: `HashSet`의 고유값·사용 이력과 분리 집합의 대표 찾기·합치기.

## 11 그래프 — 6문제

| 번호·구분 | 근거 | 접근 원자 | 접근 조합 |
|---|---|---|---|
| 34 몸풀기 ★ DFS 순회 | [저자 참고](https://github.com/retrogemHK/codingtest_java/blob/main/solution/34.java) | 간선→인접 리스트, 방문 표시, 재귀 | 그래프 표현 변환 → 시작 방문 → 미방문 이웃 재귀 → 결과 축적 |
| 35 몸풀기 ★ BFS 순회 | [저자 참고](https://github.com/retrogemHK/codingtest_java/blob/main/solution/35.java) | 인접 리스트, 큐, 넣을 때 방문 표시 | 시작을 큐에 넣고 방문 → 앞에서 꺼냄 → 미방문 이웃을 뒤에 추가 |
| 37 모의 ★★ 게임 맵 최단거리 | [공식](https://school.programmers.co.kr/learn/courses/30/lessons/1844) | 격자 그래프, 네 방향, 경계·벽, BFS 거리 | 시작 거리 저장 → 이웃 생성 → 유효·미방문 검사 → 거리+1 → 도착 또는 실패 |
| 38 모의 ★★ 네트워크 | [공식](https://school.programmers.co.kr/learn/courses/30/lessons/43162) | 인접 행렬, 연결 요소, 바깥 반복 | 모든 정점 확인 → 미방문 정점에서 탐색 → 한 묶음을 모두 방문 → 묶음 수 증가 |
| 39 모의 ★★ 미로 탈출 | [공식](https://school.programmers.co.kr/learn/courses/30/lessons/159993) | 문자 격자, 필수 경유지, 독립 BFS 두 번 | 표식 위치 탐색 → 시작에서 경유지 거리 → 상태 초기화 → 경유지에서 도착 거리 → 합산 |
| 42 모의 ★★ 전력망을 둘로 나누기 | [공식](https://school.programmers.co.kr/learn/courses/30/lessons/86971) | 무방향 트리, 후위 DFS, 서브트리 크기, 최솟값 | 양방향 그래프 구축 → 자식 크기를 부모로 반환 → 각 간선 양쪽 크기 계산 → 차이 최소화 |

핵심 원자군: 그래프 표현, 방문, DFS, BFS, 거리, 격자 조건, 연결 요소, 필수 경유, 후위 집계.

## 12 백트래킹 — 4문제

| 번호·구분 | 근거 | 접근 원자 | 접근 조합 |
|---|---|---|---|
| 43 몸풀기 ★ 합이 목표인 조합 | [저자 참고](https://github.com/retrogemHK/codingtest_java/blob/main/solution/43.java) | 선택 목록, 다음 시작값, 현재 합, 가지치기 | 다음 후보 선택 → 합 갱신 → 목표면 저장·초과면 중단 → 재귀 후 다음 후보 |
| 45 모의 ★ 피로도 | [공식](https://school.programmers.co.kr/learn/courses/30/lessons/87946) | 순열, 방문 배열, 현재 자원, 최대값 | 가능한 미방문 후보 선택 → 자원 감소 → 재귀 → 최대 갱신 → 선택 취소 |
| 46 모의 ★ N-Queen | [공식](https://school.programmers.co.kr/learn/courses/30/lessons/12952) | 행별 선택, 열·대각선 점유, 고정 깊이 | 현재 행 후보 확인 → 충돌 없으면 표시 → 다음 행 → 완성 수 증가 → 표시 취소 |
| 47 모의 ★★ 양궁 대회 | [공식](https://school.programmers.co.kr/learn/courses/30/lessons/92342) | 제한 자원 분배, 후보 축소, 완성 채점, 동점 규칙 | 칸별 배분 결정 → 남은 자원 재귀 → 완성 상태 채점 → 최고 또는 동점 우선 결과 복사 |

핵심 원자군: 선택→재귀→취소, 시작 위치, 가지치기, 방문 순열, 자원 상태, 제약 배열, 완성 채점·동점.

## 13 정렬 — 7문제

| 번호·구분 | 근거 | 접근 원자 | 접근 조합 |
|---|---|---|---|
| 50 몸풀기 ★ 계수 정렬 | [저자 참고](https://github.com/retrogemHK/codingtest_java/blob/main/solution/50.java) | 제한된 값 범위, 빈도 배열, 결과 복원 | 값을 번호로 변환 → 개수 저장 → 번호순으로 개수만큼 결과 생성 |
| 51 몸풀기 ★ 정렬된 두 배열 합치기 | [저자 참고](https://github.com/retrogemHK/codingtest_java/blob/main/solution/51.java) | 두 포인터, 결과 위치, 잔여 처리 | 두 현재 값 비교 → 작은 값 저장·포인터 이동 → 한쪽 종료 후 나머지 저장 |
| 52 모의 ★ 문자열 내 마음대로 정렬하기 | [공식](https://school.programmers.co.kr/learn/courses/30/lessons/12915) | 주 기준·동점 기준 비교자 | 지정 위치 비교 → 같으면 전체 값 비교 → 정렬 |
| 53 모의 ★ 정수 내림차순 배치 | [공식](https://school.programmers.co.kr/learn/courses/30/lessons/12933) | 표현 분해, 역순 정렬, 재조립, 형 변환 | 숫자를 자릿값으로 분해 → 내림차순 → 이어 붙임 → 숫자로 복원 |
| 54 모의 ★ K번째 수 | [공식](https://school.programmers.co.kr/learn/courses/30/lessons/42748) | 명령 반복, 1·0 기반 변환, 구간 복사, 정렬 후 선택 | 명령 해석 → 범위 변환·복사 → 정렬 → 지정 위치 선택 → 결과 저장 |
| 56 모의 ★★ 튜플 | [공식](https://school.programmers.co.kr/learn/courses/30/lessons/64065) | 구조 문자열 파싱, 묶음 크기 정렬, 본 값 집합 | 묶음 분리 → 작은 묶음부터 처리 → 처음 나타난 값을 차례대로 저장 |
| 58 모의 ★★ 전화번호 목록 | [공식](https://school.programmers.co.kr/learn/courses/30/lessons/42577) | 정렬로 관련 항목 인접화, 이웃 비교 | 사전순 정렬 → 이웃 관계만 검사 → 발견 시 조기 종료 |

핵심 원자군: 빈도 정렬, 두 포인터 병합, 다중 기준 비교자, 표현 변환, 부분 정렬·순위, 정렬 후 이웃, 파싱·집합.

## 14 시뮬레이션 — 8문제

| 번호·구분 | 근거 | 접근 원자 | 접근 조합 |
|---|---|---|---|
| 59 몸풀기 ★★ 배열 회전 | [저자 참고](https://github.com/retrogemHK/codingtest_java/blob/main/solution/59.java) | 좌표 변환, 새 2차원 배열, 변환 반복 | 원본 좌표의 새 위치 계산 → 새 배열에 저장 → 필요한 횟수 반복 |
| 60 몸풀기 ★ 행렬 곱 후 전치 | [저자 참고](https://github.com/retrogemHK/codingtest_java/blob/main/solution/60.java) | 행·열 내적, 중간 결과, 좌표 교환 | 행렬 곱 결과 완성 → 중간 결과의 행·열을 바꿔 새 배열 생성 |
| 61 몸풀기 ★★ 달팽이 수열 | [저자 참고](https://github.com/retrogemHK/codingtest_java/blob/main/solution/61.java) | 네 경계, 방향별 채우기, 경계 축소 | 위·오른쪽·아래·왼쪽 순서로 채움 → 사용한 경계를 안쪽으로 이동 → 반복 |
| 62 모의 ★★ 이진 변환 반복 | [공식](https://school.programmers.co.kr/learn/courses/30/lessons/70129) | 종료 상태, 반복 변환, 회차와 누적량 | 현재 상태 분석 → 제거량 누적 → 다음 상태 생성 → 종료까지 반복 |
| 63 모의 ★★ 롤케이크 자르기 | [공식](https://school.programmers.co.kr/learn/courses/30/lessons/132265) | 오른쪽 빈도, 이동 경계, 왼쪽 집합 | 오른쪽 전체 집계 → 하나를 왼쪽으로 이동하며 양쪽 갱신 → 각 경계 상태 비교 |
| 64 모의 ★★ 카펫 | [공식](https://school.programmers.co.kr/learn/courses/30/lessons/42842) | 약수 후보, 대칭 범위 축소, 추가 조건 | 전체 크기 계산 → 제곱근까지 약수 쌍 열거 → 두 번째 조건으로 정답 판정 |
| 65 모의 ★★ 점프와 순간 이동 | [공식](https://school.programmers.co.kr/learn/courses/30/lessons/12980) | 목표에서 역추적, 무료 연산 우선, 비용 집계 | 목표가 짝수면 나눔 → 홀수면 비용을 내고 감소 → 0까지 반복 |
| 66 모의 ★★ 캐릭터 좌표 | [공식](https://school.programmers.co.kr/learn/courses/30/lessons/120861) | 명령→변화량, 후보 상태, 경계 후 확정 | 명령 해석 → 후보 좌표 계산 → 경계 안이면 상태 반영 |

핵심 원자군: 좌표 변환, 2차원 후처리, 경계 축소, 종료까지 상태 반복, 양쪽 상태, 후보 열거, 역방향 선택, 후보→검사→반영.

## 15 동적 계획법 — 4문제

| 번호·구분 | 근거 | 접근 원자 | 접근 조합 |
|---|---|---|---|
| 70 모의 ★ 피보나치 수 | [공식](https://school.programmers.co.kr/learn/courses/30/lessons/12945) | 상태 정의, 초기값, 이전 두 상태, 단계별 나머지 | `dp[i]` 의미 결정 → 기저값 저장 → 작은 상태부터 점화식 계산 |
| 71 모의 ★ 2×n 타일링 | [공식](https://school.programmers.co.kr/learn/courses/30/lessons/12900) | 마지막 선택으로 경우 분리, 점화식 도출 | 마지막 배치의 경우 분리 → 이전 크기와 연결 → 초기값 → 반복 계산 |
| 72 모의 ★★ 정수 삼각형 | [공식](https://school.programmers.co.kr/learn/courses/30/lessons/43105) | 2차원 상태, 두 선택 중 최선, 계산 방향 | 아래 행 초기화 → 아래에서 위로 두 자식 중 최댓값과 현재 값 결합 |
| 73 모의 ★★ 땅따먹기 | [공식](https://school.programmers.co.kr/learn/courses/30/lessons/12913) | 마지막 상태별 최댓값, 금지된 이전 상태 | 현재 선택마다 허용된 이전 상태의 최댓값 선택 → 현재 점수 추가 → 마지막 최댓값 |

핵심 원자군: 상태·기저·점화식, 마지막 선택, 계산 방향, 다중 상태와 금지 전이.

## 16 그리디 — 6문제

| 번호·구분 | 근거 | 접근 원자 | 접근 조합 |
|---|---|---|---|
| 77 몸풀기 ★★ 거스름돈 | [저자 참고](https://github.com/retrogemHK/codingtest_java/blob/main/solution/77.java) | 큰 단위 우선, 남은 양, 선택 정당성 | 단위를 큰 순서로 확인 → 가능한 만큼 사용 → 남은 양으로 다음 단위 |
| 78 몸풀기 ★★ 부분 배낭 | [저자 참고](https://github.com/retrogemHK/codingtest_java/blob/main/solution/78.java) | 단위 무게당 가치, 내림차순, 마지막 일부 | 효율 계산 → 높은 순서로 통째 선택 → 남은 용량에는 일부 선택 |
| 79 모의 ★ 예산 | [공식](https://school.programmers.co.kr/learn/courses/30/lessons/12982) | 동일 보상, 싼 비용 우선, 누적 | 비용 오름차순 → 감당 가능한 동안 차감·개수 증가 → 중단 |
| 80 모의 ★ 구명보트 | [공식](https://school.programmers.co.kr/learn/courses/30/lessons/42885) | 정렬, 양끝 포인터, 가장 무거운 항목 강제 처리 | 가장 무거운 항목 선택 → 가장 가벼운 항목과 조합 가능 여부 → 사용한 포인터 이동 |
| 81 모의 ★★ 귤 고르기 | [공식](https://school.programmers.co.kr/learn/courses/30/lessons/138476) | 종류별 빈도, 큰 묶음 우선, 목표 누적 | 빈도 집계 → 빈도 내림차순 → 큰 묶음부터 누적 → 목표 도달 시 종료 |
| 82 모의 ★★ 기지국 설치 | [공식](https://school.programmers.co.kr/learn/courses/30/lessons/12979) | 덮인 구간 건너뛰기, 고정 폭 전진, 큰 입력 | 현재 위치가 기존 범위면 끝 다음으로 이동 → 아니면 새 범위를 최대 폭으로 덮고 점프 |

핵심 원자군: 탐욕 기준의 정당성, 정렬 후 선택, 비율, 양끝, 빈도 묶음, 구간 단위 점프.

## 출처와 계약 검증 주의점

- 책 자체 몸풀기의 공개 참고 코드는 접근 순서를 확인하는 자료일 뿐 전체 문제 계약이 아니다. 생성 문제는 독립적인 목표·입출력·제약을 새로 정의한다.
- 문제 06은 실패율 동점일 때 원래 번호가 작은 항목을 먼저 두는 보조 기준을 반드시 검증한다.
- 문제 22는 그룹 내부 동점 시 원래 ID 기준이 필요하다.
- 문제 52·73처럼 참고 구현이 입력을 직접 바꾸는 경우가 있다. 원본 보존 여부는 생성 문제마다 계약에서 먼저 정한다.
- 문제 60의 참고 구현은 크기를 고정한다. 일반 2차원 계약으로 확장할 때 결과 크기와 공통 차원을 별도로 검증한다.
- 문제 65는 결과 공식만 외우지 않고 역방향 판단 과정을 학습 대상으로 삼는다.
- 문제 71은 가장 작은 공식 입력을 포함해 기저값 경계를 검증한다.
- 문제 77은 모든 화폐 단위에서 큰 단위 우선이 최적이지 않다. 적용 조건과 반례를 설명한다.
- 문제 78은 항목을 나눌 수 있을 때만 비율 기준 그리디가 성립한다.

이 카탈로그를 수정할 때는 문제 수 합계가 63인지, 제외 대상 `★★★` 이상이 섞이지 않았는지 함께 확인한다.

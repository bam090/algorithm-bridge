# Algorithm Bridge

## 프로젝트 배경

코딩 테스트 스터디에는 문제 풀이 경험이 많은 스터디원과 아직 코딩 테스트가 익숙하지 않은 스터디원이 함께 참여했다. Algorithm Bridge는 이 문제 풀이 경험의 차이를 줄이기 위해 시작한 프로젝트다.

코딩 테스트 입문자는 알고리즘 개념을 학습해도 문제의 단서를 찾고 적절한 개념을 선택해 실제 풀이에 적용하는 과정이 어려웠다.

그래서 개념 설명과 책 문제 사이에서 작은 문제를 단계적으로 풀며, 배운 개념을 언제·왜 사용해야 하는지 직접 판단하는 연습을 할 수 있는 Algorithm Bridge를 만들었다.

## 프로젝트 소개

Algorithm Bridge는 공부한 알고리즘 개념을 실제 문제에 사용하면서, 스스로 접근 방식을 찾아 풀이를 시작하는 경험을 쌓는 Java 연습 프로젝트다. 개념 학습을 마친 뒤 책의 몸풀기 문제로 넘어가기 전에 사용한다.

정답 코드나 특정 문제의 풀이를 외우는 것이 목표가 아니다. 처음 보는 작은 문제에서 중요한 조건을 찾고, 사용할 자료구조나 알고리즘과 그 이유를 설명한 뒤 데이터 흐름과 풀이 순서를 Java 코드로 옮길 수 있게 되는 것이 목표다.

Algorithm Bridge의 문제는 책의 몸풀기·모의테스트와 별도로 만든다. 책 문제의 복사본, 단순 변형, 해설 대체물 또는 사전 답안 연습장으로 사용하지 않는다.

책 문제로 접근 경험을 옮길 수 있도록 핵심 접근 방식은 의도적으로 공유한다. 같은 접근 방식을 쓴다는 이유만으로 중복으로 보지 않으며, 책 정답 코드를 이름·소재·상수만 바꿔 거의 그대로 재사용할 수 있을 때 나쁜 중복으로 판단한다.

## 쌓아야 하는 경험

문제를 풀 때 다음 판단을 반복해서 경험한다.

1. 문제에서 중요한 조건과 단서를 찾는다.
2. 어떤 자료구조나 알고리즘이 필요한지 떠올린다.
3. 그 선택이 적절한 이유를 말로 설명한다.
4. 입력 데이터가 처리되는 흐름을 손으로 추적한다.
5. 풀이 순서를 의사 코드와 Java 코드로 옮긴다.

## 문제 수와 완료 기준

문제 수는 주제별로 다르다. 새로운 접근 방식을 익히는 데 필요한 만큼만 만든다. 정해진 개수를 채우기 위한 반복 문제는 만들지 않는다.

문제 생성은 다음 순서로 진행한다.

```text
책의 ★·★★ 문제 접근 방식 카탈로그
→ 접근 원자·접근 조합·힌트 없는 전이로 학습 사다리 계산
→ 한 주제의 독립 문제 생성
→ 콘텐츠 검증
→ Java 컴파일·범위 기반 테스트 검증
```

설계는 모든 주제의 관계를 함께 볼 수 있지만, 실제 구현과 검증은 한 번에 한 주제만 끝낸다. 검증에 실패하면 같은 문제 생성 에이전트가 원인을 수정하고 콘텐츠 검증과 테스트를 다시 받는다.

다음 행동을 도움 없이 할 수 있으면 해당 주제의 브릿지를 마친다.

- 처음 보는 작은 문제에서 핵심 조건을 찾는다.
- 사용할 개념과 선택 이유를 설명한다.
- 데이터 흐름이나 풀이 순서를 간단히 적는다.
- 책의 몸풀기 문제 풀이를 스스로 시작한다.

## 주제 순서

```text
배열 → 스택 → 큐 → 해시 → 트리 → 집합 → 그래프
→ 백트래킹 → 정렬 → 투 포인터 → 시뮬레이션 → 동적 계획법 → 그리디
```

각 주제는 IntelliJ의 `src/main/java/bridge` 아래에서 다음 패키지로 찾는다.

```text
src/main/java/bridge/
├── array/               배열
│   ├── onedimensional/  1차원 배열
│   └── twodimensional/  2차원 배열
├── stack/               스택
├── queue/               큐
├── hash/                해시
├── tree/                트리
├── set/                 집합
├── graph/               그래프
├── backtracking/        백트래킹
├── sorting/             정렬
├── twopointer/          투 포인터
├── simulation/          시뮬레이션
├── dynamicprogramming/  동적 계획법
└── greedy/              그리디
```

학습할 주제의 패키지를 열고 `<Topic>Guide.java`부터 읽는다.

## 실행 환경

- Gradle Wrapper 9.6.0
- JUnit Jupiter 6.0.2
- Amazon Corretto 26
- `src/main/java`를 실행 코드 Source Root로 사용
- `src/test/java`를 테스트 코드 Source Root로 사용
- Guide·Problem·Solution은 Java 표준 라이브러리만 사용

## 사용 방법

### 가장 간단한 방법 — AI에게 요청하기

터미널과 로컬 파일을 다룰 수 있는 AI에게 아래 요청을 전달한다.

```text
https://github.com/bam090/algorithm-bridge 프로젝트를 이 컴퓨터에 내려받아
IntelliJ IDEA에서 바로 학습할 수 있도록 준비해 줘.

저장소의 README를 먼저 읽고 안내를 따라 줘.
학습 문제·정답·테스트 파일은 수정하지 말고,
내가 직접 해야 할 설정이 있다면 마지막에 알려 줘.
```

### 직접 시작하기

터미널에서 다음 명령을 실행한다.

```bash
git clone https://github.com/bam090/algorithm-bridge.git
cd algorithm-bridge
```

1. IntelliJ IDEA에서 `Open`을 선택한다.
2. 내려받은 `algorithm-bridge` 폴더를 연다.
3. Gradle 프로젝트로 불러오거나 `build.gradle`의 Gradle 변경 사항을 적용한다.
4. Project SDK와 Gradle JVM이 Amazon Corretto 26인지 확인한다.

Gradle이 `src/main/java`와 `src/test/java`를 각각 실행 코드와 테스트 코드로 인식하고 JUnit 의존성을 준비한다.

터미널에서는 다음 명령으로 전체 테스트를 실행한다.

```bash
./gradlew test
```

특정 테스트 클래스만 실행하려면 완전한 클래스명을 지정한다.

```bash
./gradlew test --tests 'bridge.array.onedimensional.test.ArraySolution01Test'
```

Windows에서는 `gradlew.bat test`를 사용한다.

### 주제별로 학습하기

1. 학습할 주제의 `<Topic>Guide.java`를 읽고 `main()`을 실행한다.
2. `problem/<Topic>ProblemNN.java`의 문제와 생각 질문을 읽는다.
3. 접어 둔 접근 방식은 먼저 펼치지 않고 `solve()`를 직접 구현한다.
4. 구현을 마친 뒤 `solution/<Topic>SolutionNN.java`와 접근 방식·데이터 흐름·코드를 비교한다.
5. `src/test/java`의 `<Topic>SolutionNNTest.java`를 JUnit으로 실행해 정답 코드가 여러 입력과 경계값을 처리하는지 확인한다.

> [!NOTE]
> 현재 Test 파일은 학습자가 작성한 `ProblemNN.solve()`가 아니라 제공된 `SolutionNN.solve()`를 검증한다. 테스트 결과는 정답 코드와 문제 계약의 실행 근거이며, 학습자가 작성한 코드의 자동 채점 결과가 아니다.

## 파일 구조 원칙

아직 학습하지 않는 주제의 빈 패키지를 미리 만들지 않는다. 실제 문제를 생성할 때 해당 주제 구조도 함께 만든다.

예를 들어 배열 주제를 시작할 때 다음 구조를 만든다.

```text
src/
├── main/java/bridge/array/
│   ├── ArrayGuide.java
│   ├── onedimensional/
│   │   ├── problem/
│   │   │   └── ArrayProblem01.java
│   │   └── solution/
│   │       └── ArraySolution01.java
│   └── twodimensional/
│       ├── problem/
│       │   └── ArrayProblem07.java
│       └── solution/
│           └── ArraySolution07.java
└── test/java/bridge/array/
    ├── onedimensional/test/
    │   └── ArraySolution01Test.java
    └── twodimensional/test/
        └── ArraySolution07Test.java
```

- `Guide`: 개념, Java 사용 예시, 문제에서 알아볼 단서, 흔한 실수를 설명한다.
- `problem`: 문제 설명·입출력·생각 질문·접근 방식·제약 조건·힌트를 제목별로 따로 접을 수 있게 두고, 직접 작성할 `solve()` 메서드는 펼쳐 둔다.
- `solution`: 정답 코드뿐 아니라 선택 이유, 데이터 흐름과 시간·공간 복잡도를 설명한다.
- `test`: 제약에서 고른 하한·0·일반 중간값·상한과 길이 경계를 JUnit으로 검증하고, IntelliJ나 Gradle의 테스트 결과에서 통과 여부를 보여준다.
- 배열 문제는 `onedimensional`과 `twodimensional` 하위 패키지로 나눠 차원을 구분한다.

## 설계·작업 문서

- [AGENTS.md](AGENTS.md): 저장소 전체 운영 규칙
- [책 문제 경계 목록](references/book-problem-boundaries.md): 책 목차의 주제·제목·난이도 경계
- [책 문제 접근 방식 카탈로그](references/book-problem-approaches.md): `★`·`★★` 63개의 접근 원자와 접근 조합
- [학습 사다리 설계](references/learning-ladder-design.md): 문제 수 계산 근거와 주제별 학습 슬롯
- [문제 생성·검증 계약](references/problem-generation-contract.md): 문제 생성, 콘텐츠 검증과 테스트 역할의 공통 작업 방식

에이전트는 파일을 만들거나 검증하기 전에 위 문서를 전부 읽는다.

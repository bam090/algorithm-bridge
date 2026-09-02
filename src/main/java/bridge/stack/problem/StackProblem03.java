package bridge.stack.problem;

//region 문제: 원형 필름 작업의 시작점별 받침대 높이
/*
 원형 작업표 operations에는 보호 필름을 올리고 치우는 기록이 들어 있다.
 양수 x는 x번 필름을 맨 위에 올리고, 음수 -x는 맨 위의 x번 필름을 치운다는 뜻이다.
 작업표의 어느 위치에서 시작해도 되며, 시작한 뒤에는 원을 따라 operations.length개 기록을 처리한다.
 맨 위와 다른 필름을 치우거나 빈 곳에서 치우면 그 시작점은 실패한다.
 시작점마다 동시에 쌓인 필름의 최대 개수를 결과 배열의 같은 인덱스에 저장하라.
 실패한 시작점의 결과는 -1로 저장한다.
 */
//endregion

//region 코드를 쓰기 전에 생각할 질문
/*
 - 한 시작점에서 offset번째로 확인할 배열 인덱스는 어떻게 구할 수 있는가?
 - 시작점이 바뀌면 이전 검사에 사용한 스택을 다시 써도 되는가?
 - 필름을 올린 뒤 현재 높이와 그 시작점의 최대 높이는 어떻게 갱신하는가?
 - 모든 기록이 맞아도 스택에 필름이 남으면 성공이라고 할 수 있는가?
 - 실패한 시작점과 성공한 시작점의 값을 결과 배열에서 어떻게 구분할 수 있는가?
 */
//endregion

public final class StackProblem03 {

    private StackProblem03() {
        solve(new int[]{1, -1, 2, -2});
        // 예상 출력: new int[]{1, -1, 1, -1}
    }

    //region 먼저 직접 생각한 뒤 확인할 접근 방식
    /*
     모든 원형 시작점을 고르고 시작점마다 새 스택으로 작업을 검증한 뒤, 성공하면 최대 높이를 실패하면 -1을 같은 위치의 결과에 저장해 반환한다.
     */
    //endregion

    //region 제약 조건
    /*
     - operations는 null이 아니며 길이는 1 이상 200 이하이다.
     - 각 값의 절댓값은 1 이상 1,000 이하이며, 0은 없다.
     - 같은 번호의 필름을 여러 장 겹쳐 올릴 수 있다.
     - operations는 바꾸면 안 된다.
     */
    //endregion

    // solve 메서드 몸체를 직접 구현한다.
    public static int[] solve(int[] operations) {
        int[] answer = {};
        return answer;
    }

    //region 막혔을 때만 확인할 단계별 힌트
    /*
     - 시작점마다 새 스택을 만들고, (start + offset) % operations.length 위치를 확인한다.
     - 결과 배열을 먼저 -1로 채우고, 성공한 시작점의 값만 최대 높이로 바꾼다.
     */
    //endregion
}

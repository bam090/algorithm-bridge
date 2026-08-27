package bridge.stack.problem;

//region 문제: 여러 카드 더미의 합 상쇄
/*
 sourceStacks의 각 행은 카드 더미 하나이며, 카드가 아래에서 위 순서로 들어 있다.
 picks의 각 값은 문제에서 1부터 세는 더미 번호다. 지시된 더미가 비어 있지 않으면 맨 위 카드를 꺼낸다.
 꺼낸 카드와 결과 더미의 맨 위 카드의 합이 cancelSum이면 두 카드를 모두 없애고,
 그렇지 않으면 꺼낸 카드를 결과 더미의 맨 위에 올린다. 빈 원본 더미를 고른 지시는 건너뛴다.
 모든 지시를 처리한 뒤 결과 더미에 남은 카드를 아래에서 위 순서로 반환하라.
 */
//endregion

//region 코드를 쓰기 전에 생각할 질문
/*
 - 한 행의 마지막 값이 맨 위 카드라면 원본 스택에는 어떤 순서로 넣어야 하는가?
 - 선택한 원본 스택이 비어 있을 때 결과 스택도 바뀌어야 하는가?
 - 새 카드와 비교할 값은 결과 스택의 어느 위치에 있는가?
 - pop되는 순서와 문제에서 요구한 아래에서 위 순서는 같은가?
 */
//endregion

public final class StackProblem05 {

    private StackProblem05() {
        solve(
                new int[][]{{4, -3}, {2, 3}, {-4, -2}, {7}},
                new int[]{1, 2, 1, 2, 3, 3, 4},
                0
        );
        // 예상 출력: new int[]{7}
    }

    //region 먼저 직접 생각한 뒤 확인할 접근 방식
    /*
     각 행을 원본 스택으로 만든 뒤, 지시된 스택의 위 카드를 결과 스택 최근 카드와 비교해 저장하거나 둘 다 없애고, 남은 카드를 아래에서 위 순서로 반환한다.
     */
    //endregion

    //region 제약 조건
    /*
     - sourceStacks는 null이 아니며 행 개수는 1 이상 50 이하이다.
     - 각 행은 null이 아니며 길이는 0 이상 2,000 이하이다.
     - 모든 행의 카드 수 합은 0 이상 100,000 이하이다.
     - 각 카드 값은 -1,000 이상 1,000 이하이다.
     - picks는 null이 아니며 길이는 0 이상 100,000 이하이다.
     - 각 더미 번호는 1 이상 sourceStacks.length 이하이다.
     - cancelSum은 -2,000 이상 2,000 이하이다.
     - sourceStacks와 picks는 바꾸면 안 된다.
     */
    //endregion

    // solve 메서드 몸체를 직접 구현한다.
    public static int[] solve(int[][] sourceStacks, int[] picks, int cancelSum) {
        int[] answer = {};
        return answer;
    }

    //region 막혔을 때만 확인할 단계별 힌트
    /*
     - 각 행을 왼쪽부터 push하면 행의 마지막 값이 원본 스택의 맨 위에 놓인다.
     - 결과 스택에서 pop되는 순서가 문제의 반환 순서와 반대인지 확인한다.
     */
    //endregion
}

package bridge.tree.solution;

import java.util.ArrayList;
import java.util.List;

/** 목표 값의 방문 순번 문제 정답과 풀이 설명이다. */
public final class TreeSolution02 {

    private TreeSolution02() {
    }

    public static int solve(int[] values, int target, String visitMoment) {
        // 왼쪽과 오른쪽을 방문하는 흐름은 같고, 현재 값을 기록하는 순간만 달라진다.
        // 재귀 호출 사이의 기록 위치를 바꾸면 세 방문 순서를 같은 구조로 정확히 표현할 수 있다.

        List<Integer> visitOrder = new ArrayList<>(values.length);
        record(values, 0, visitMoment, visitOrder);

        // [6] 완성된 목록에서 target을 찾고 인덱스에 1을 더한다.
        for (int index = 0; index < visitOrder.size(); index++) {
            if (visitOrder.get(index) == target) {
                return index + 1;
            }
        }
        return 0;
    }

    private static void record(
            int[] values,
            int index,
            String visitMoment,
            List<Integer> visitOrder
    ) {
        // [1] 현재 인덱스가 배열 밖이면 재귀를 끝낸다.
        if (index >= values.length) {
            return;
        }

        // [2] BEFORE이면 자식 호출 전에 현재 값을 기록한다.
        if (visitMoment.equals("BEFORE")) {
            visitOrder.add(values[index]);
        }

        // [3] 왼쪽 자식을 방문한다.
        record(values, index * 2 + 1, visitMoment, visitOrder);

        // [4] BETWEEN이면 두 자식 호출 사이에 현재 값을 기록한다.
        if (visitMoment.equals("BETWEEN")) {
            visitOrder.add(values[index]);
        }

        // [5] 오른쪽 자식을 방문하고 AFTER이면 현재 값을 기록한다.
        record(values, index * 2 + 2, visitMoment, visitOrder);
        if (visitMoment.equals("AFTER")) {
            visitOrder.add(values[index]);
        }
    }

    /*
     * 풀이 설명
     *
     * 발견해야 할 단서
     * - 왼쪽과 오른쪽 자식을 방문하는 재귀 구조는 항상 같다.
     * - 바뀌는 것은 현재 값을 두 재귀 호출의 전, 사이, 뒤 중 언제 기록하는지다.
     * - 결과는 전체 방문 목록이 아니라 target의 1부터 세는 순번 하나다.
     *
     * 이 개념을 선택한 이유
     * - 기록 한 줄의 위치를 옮기면 세 방문 시점의 차이를 직접 확인할 수 있다.
     * - values의 값은 모두 다르므로 기록 목록에서 target의 위치는 하나뿐이다.
     *
     * 풀이 순서
     * 1. 현재 인덱스가 배열 밖이면 재귀를 끝낸다.
     * 2. BEFORE이면 자식 호출 전에 현재 값을 기록한다.
     * 3. 왼쪽 자식을 방문한다.
     * 4. BETWEEN이면 두 자식 호출 사이에 현재 값을 기록한다.
     * 5. 오른쪽 자식을 방문하고 AFTER이면 현재 값을 기록한다.
     * 6. 완성된 목록에서 target을 찾고 인덱스에 1을 더한다.
     *
     * 예시 데이터 흐름
     * - values=[10, 20, 30, 40, 50, 60, 70], visitMoment=BETWEEN
     * - 20의 왼쪽 40을 먼저 기록하고, 20을 기록한 뒤 오른쪽 50을 기록한다.
     * - 전체 기록은 [40, 20, 50, 10, 60, 30, 70]이다.
     * - target 50은 인덱스 2이므로 3번째다.
     *
     * 복잡도
     * - 시간 O(n): 노드 n개를 기록하고 target을 한 번 찾는다.
     * - 공간 O(n): 방문 목록에 n개 값을 저장한다.
     * - 재귀 호출 깊이는 완전 이진 트리 높이인 O(log n)이다.
     *
     * 자주 하는 실수
     * - 세 경우 모두 같은 위치에서 현재 값을 기록한다.
     * - 배열 밖 종료 조건을 자식 호출 뒤에 둔다.
     * - 목록 인덱스를 그대로 반환해 답이 0부터 시작한다.
     * - 입력 values를 방문 표시용으로 바꾼다.
     */
}

package bridge.hash.solution;

import java.util.HashMap;
import java.util.Map;

/*
 * 정답 풀이: 두 재고 목록의 남은 차이 수
 *
 * 문제에서 발견해야 했던 단서
 * - 같은 코드가 여러 번 나올 수 있어 존재 여부만으로는 부족하다.
 * - 준비 목록과 실제 목록 양쪽에 남는 차이를 모두 세어야 한다.
 *
 * Map을 선택한 이유
 * 코드마다 남은 개수를 따로 기억해야 한다.
 * 준비 목록은 더하고 실제 목록은 빼면 하나의 Map에서 양쪽 차이를 함께 표현할 수 있다.
 *
 * 풀이 순서
 * 1. 코드별 차이를 저장할 Map을 만든다.
 * 2. 준비 목록의 각 코드는 1씩 더한다.
 * 3. 실제 목록의 각 코드는 1씩 뺀다.
 * 4. 마지막에 남은 모든 값의 절댓값을 더한다.
 *
 * 예시 데이터 흐름
 * expectedCodes = [A, A, B] → A: 2, B: 1
 * actualCodes의 A를 반영 → A: 1, B: 1
 * actualCodes의 C, C를 반영 → A: 1, B: 1, C: -2
 * 절댓값 합 1 + 1 + 2 = 4
 *
 * 시간 복잡도: 평균 O(n + m)
 * 공간 복잡도: O(k), k는 서로 다른 코드 수
 *
 * 초보자가 실수하기 쉬운 부분
 * - Set을 사용하면 같은 코드가 두 번 나온 횟수를 잃는다.
 * - 양수만 더하면 실제 목록에만 있는 코드를 놓친다.
 * - 두 배열의 길이 차이만 계산하면 내용이 다른 같은 길이 배열을 놓친다.
 */
public final class HashSolution02 {

    private HashSolution02() {
    }

    public static int solve(String[] expectedCodes, String[] actualCodes) {
        Map<String, Integer> differences = new HashMap<>();

        for (String code : expectedCodes) {
            differences.merge(code, 1, Integer::sum);
        }
        for (String code : actualCodes) {
            differences.merge(code, -1, Integer::sum);
        }

        int unmatchedCount = 0;
        for (int difference : differences.values()) {
            unmatchedCount += Math.abs(difference);
        }
        return unmatchedCount;
    }
}

package bridge.hash.problem;

/*
 * 문제: 두 재고 목록의 남은 차이 수
 *
 * 이 문제에서 연습할 것
 * 같은 코드가 몇 번 나왔는지 세고, 두 방향에서 생긴 차이를 모두 계산한다.
 *
 * 문제 설명
 * expectedCodes에는 준비했어야 할 물건 코드가, actualCodes에는 실제로 확인한 물건 코드가 있다.
 * 같은 코드는 한 번씩 서로 짝지을 수 있다.
 * 어느 한쪽에만 남은 코드의 총개수를 반환한다.
 * 같은 코드가 여러 번 나오면 나온 횟수만큼 따로 계산한다.
 *
 * 입력과 출력
 * - 입력: 준비 목록 expectedCodes, 실제 목록 actualCodes
 * - 출력: 서로 짝지을 수 없어 한쪽에만 남은 코드의 총개수
 *
 * 입출력 예시
 * expectedCodes = ["A", "A", "B"]
 * actualCodes = ["A", "C", "C"]
 * 결과 = 4
 * A 한 개와 B 한 개가 준비 목록에 남고, C 두 개가 실제 목록에 남는다.
 *
 * 코드를 쓰기 전에 생각할 질문
 *
 * - Set만 사용하면 같은 코드가 두 번 나온 사실을 기억할 수 있는가?
 * - 준비 목록의 코드는 개수를 늘리고 실제 목록의 코드는 줄이면 무엇이 남는가?
 * - 음수로 남은 개수도 차이에 포함해야 하지 않을까?
 * - 두 배열의 길이가 같아도 내용은 다를 수 있지 않은가?
 */
public final class HashProblem02 {

    private HashProblem02() {
        solve(new String[]{"A", "A", "B"}, new String[]{"A", "C", "C"});
        // 예상 출력: 4
    }

    //region 먼저 직접 생각한 뒤 확인할 접근 방식
    /*
     * 준비 목록의 코드별 개수는 늘리고 실제 목록의 개수는 줄인 뒤, 남은 모든 차이의 절댓값을 더해 반환한다.
     */
    //endregion

    /*
     * 제약 조건
     * - 0 <= expectedCodes.length <= 100_000
     * - 0 <= actualCodes.length <= 100_000
     * - 각 코드는 null이 아니며 길이는 1 이상 20 이하이다.
     * - 입력 배열은 바꾸지 않는다.
     */
    public static int solve(String[] expectedCodes, String[] actualCodes) {
        int answer = 0;
        // 여기에 직접 구현한다.
        return answer;
    }

    //region 막혔을 때만 확인할 단계별 힌트
    /*
     * - Map<String, Integer>에 코드별 남은 개수를 저장한다.
     * - expectedCodes를 읽을 때는 1을 더하고 actualCodes를 읽을 때는 1을 뺀다.
     * - 마지막 값이 음수일 수도 있으므로 각 값의 절댓값을 더한다.
     */
    //endregion
}

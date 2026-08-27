package bridge.set.problem;

/*
 * 문제 | 체험 보드의 서로 다른 배지 채우기
 * badgeCodes에는 준비된 배지의 코드가 들어 있다. 같은 코드의 배지는 보드에 하나만 놓을 수 있다.
 * 보드에는 slotLimit개의 칸이 있다. 놓을 수 있는 배지를 최대한 놓았을 때
 * 채운 칸 수와 빈 칸 수를 new int[]{채운 칸 수, 빈 칸 수}로 반환하라.
 * 원본 badgeCodes는 바꾸면 안 된다.
 *
 * 코드를 쓰기 전에 생각할 질문
 *
 * - 같은 코드가 여러 번 나오면 보드에는 몇 개를 놓을 수 있는가?
 * - 서로 다른 코드 수와 slotLimit 중 어느 값이 채운 칸 수의 한계가 되는가?
 * - 빈 칸 수는 slotLimit와 채운 칸 수로 어떻게 구할 수 있는가?
 * - 배지 코드가 음수나 0이어도 HashSet에 넣을 수 있는가?
 */
public final class SetProblem01 {

    private SetProblem01() {
        solve(new int[]{10, 10, 20, 30}, 5);
        // 예상 출력: new int[]{3, 2}
    }

    //region 먼저 직접 생각한 뒤 확인할 접근 방식
    /*
     * 접근 방식
     * 모든 배지 코드를 집합에 넣고, 고유 개수와 보드 칸 수 중 작은 값만큼 채운 뒤 채운 칸과 빈 칸을 반환한다.
     */
    //endregion

    /*
     * 제약 조건
     * - badgeCodes는 null이 아니며 길이는 0 이상 100,000 이하이다.
     * - 각 배지 코드는 -1,000,000 이상 1,000,000 이하이다.
     * - slotLimit는 0 이상 100,000 이하이다.
     * - 원본 badgeCodes는 바꾸지 않는다.
     */

    // solve 메서드 몸체를 직접 구현한다.
    public static int[] solve(int[] badgeCodes, int slotLimit) {
        int[] answer = {};
        return answer;
    }

    //region 막혔을 때만 확인할 단계별 힌트
    /*
     * - HashSet에 같은 값을 여러 번 add()해도 한 번만 남는다.
     * - 채운 칸 수는 set.size()와 slotLimit 중 작은 값이다.
     * - 빈 칸 수는 slotLimit에서 채운 칸 수를 뺀 값이다.
     */
    //endregion
}

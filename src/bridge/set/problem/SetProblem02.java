package bridge.set.problem;

/*
 * 문제 | 순환 점검 기록의 첫 오류
 * inspectors명이 1번부터 차례로 점검 기록을 하나씩 맡고, 마지막 점검자 다음에는 다시 1번이 맡는다.
 * checkCodes를 앞에서부터 확인할 때 아래 규칙을 처음 어긴 기록을 찾는다.
 *
 * - 전에 나온 점검 코드는 다시 사용할 수 없다.
 * - 두 번째 기록부터는 바로 앞 코드와의 차이가 allowedGap 이하여야 한다.
 *
 * 첫 오류를 맡은 점검자 번호와 잘못된 코드를 new int[]{점검자 번호, 코드}로 반환하라.
 * 모든 기록이 올바르면 빈 배열을 반환하고, 원본 checkCodes는 바꾸면 안 된다.
 *
 * 코드를 쓰기 전에 생각할 질문
 *
 * - 이미 나온 코드인지 빠르게 확인하려면 무엇을 기억해야 하는가?
 * - 첫 번째 기록에도 앞 코드와의 차이 규칙을 적용해야 하는가?
 * - 중복과 간격 오류가 여러 번 나오면 어느 기록만 반환해야 하는가?
 * - 배열 인덱스 i를 1부터 세는 점검자 번호로 어떻게 바꿀 수 있는가?
 * - 코드 차이가 allowedGap와 정확히 같으면 올바른가?
 */
public final class SetProblem02 {

    private SetProblem02() {
        solve(new int[]{10, 12, 14, 10}, 3, 5);
        // 예상 출력: new int[]{1, 10}
    }

    //region 먼저 직접 생각한 뒤 확인할 접근 방식
    /*
     * 접근 조합
     * 기록을 앞에서부터 보며 이미 본 코드와 앞 코드와의 간격을 확인하고, 처음 어긴 인덱스를 점검자 번호로 바꿔 코드와 함께 반환한다.
     */
    //endregion

    /*
     * 제약 조건
     * - checkCodes는 null이 아니며 길이는 0 이상 100,000 이하이다.
     * - 각 코드는 -1,000,000 이상 1,000,000 이하이다.
     * - inspectors는 1 이상 1,000 이하이다.
     * - allowedGap는 0 이상 2,000,000 이하이다.
     * - 원본 checkCodes는 바꾸지 않는다.
     */

    // solve 메서드 몸체를 직접 구현한다.
    public static int[] solve(int[] checkCodes, int inspectors, int allowedGap) {
        int[] answer = {};
        return answer;
    }

    //region 막혔을 때만 확인할 단계별 힌트
    /*
     * - 인덱스 i의 기록을 맡은 점검자 번호는 i % inspectors + 1이다.
     */
    //endregion
}

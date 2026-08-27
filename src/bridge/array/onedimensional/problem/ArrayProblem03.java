package bridge.array.onedimensional.problem;

/*
 * 문제 | 연속 기록의 변화량
 * 시간순 기록 values가 주어진다. 두 번째 기록부터 바로 이전 기록과의 차이를 구해
 * 변화량 배열로 반환하라. 변화량은 현재 값 - 이전 값이다.
 *
 * 코드를 쓰기 전에 생각할 질문
 *
 * - 원소가 n개면 이웃한 두 원소의 쌍은 몇 개인가?
 * - 현재 값을 values[i]로 읽을 때 이전 값의 인덱스는 무엇인가?
 * - 입력 인덱스 i에서 만든 변화량은 결과 배열의 어느 인덱스에 들어가는가?
 * - 입력에 원소가 하나뿐이면 결과 길이는 얼마인가?
 */
public final class ArrayProblem03 {

    private ArrayProblem03() {
        solve(new int[]{10, 13, 12, 12});
        // 예상 출력: new int[]{3, -1, 0}
    }

    //region 먼저 직접 생각한 뒤 확인할 접근 방식
    /*
     * 접근 방식
     * 현재 숫자에서 바로 앞 숫자를 뺀 값을 차례대로 저장한 새 배열을 반환한다.
     */
    //endregion

    /*
     * 제약 조건
     * - values는 null이 아니며 길이는 1 이상 1,000 이하이다.
     * - 각 값은 -10,000 이상 10,000 이하이다.
     */

    // solve 메서드 몸체를 직접 구현한다.
    public static int[] solve(int[] values) {
        int[] answer = {};
        return answer;
    }

    //region 막혔을 때만 확인할 단계별 힌트
    /*
     * - 결과 배열의 길이는 입력 길이보다 1 작다.
     * - 이전 값이 있어야 하므로 입력 인덱스 1부터 시작한다.
     * - values[i] - values[i - 1]을 결과 배열의 인덱스 i - 1에 저장한다.
     */
    //endregion
}

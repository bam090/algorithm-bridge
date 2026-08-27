package bridge.array.onedimensional.problem;

/*
 * 문제 | 여러 구간의 기록 합
 * 시간순 정수 기록 values와 여러 구간 ranges가 주어진다.
 * 각 구간에 들어 있는 값의 합을 질문 순서대로 반환하라.
 * 구간의 위치 번호는 1부터 세며, 시작과 끝 위치의 값도 합에 포함한다.
 *
 * 코드를 쓰기 전에 생각할 질문
 *
 * - 각 위치까지의 합을 미리 저장했다면, 구간 합은 어떤 두 값을 빼서 구할 수 있는가?
 * - 첫 번째 값부터 시작하는 구간도 같은 식으로 처리하려면 누적 합 배열의 맨 앞에 어떤 값이 필요한가?
 * - 1부터 세는 위치 번호와 Java 배열의 인덱스는 어떻게 연결되는가?
 * - 왜 누적 합 배열과 결과 배열의 원소 타입이 long이어야 하는가?
 */
public final class ArrayProblem05 {

    private ArrayProblem05() {
        solve(
                new int[]{4, 1, 3, 2, 5},
                new int[][]{{1, 3}, {2, 5}, {4, 4}}
        );
        // 예상 출력: new long[]{8, 11, 2}
    }

    //region 먼저 직접 생각한 뒤 확인할 접근 방식
    /*
     * 접근 방식
     * 처음부터 각 위치까지의 합을 미리 저장한 뒤, 두 합의 차이로 각 구간의 합을 구해 반환한다.
     */
    //endregion

    /*
     * 제약 조건
     * - values는 null이 아니며 길이는 1 이상 100,000 이하이다.
     * - 각 값은 -1,000,000 이상 1,000,000 이하이다.
     * - ranges는 null이 아니며 길이는 1 이상 100,000 이하이다.
     * - 각 구간은 new int[]{start, end}이며, start와 end는 1 이상 values.length 이하이다.
     * - start는 end보다 작거나 같다.
     * - 합은 int 범위를 넘을 수 있으므로 long으로 계산한다.
     * - 모든 값을 구간마다 다시 더하지 않고, 미리 계산한 합을 이용해 각 구간의 합을 구한다.
     */

    // solve 메서드 몸체를 직접 구현한다.
    public static long[] solve(int[] values, int[][] ranges) {
        long[] answer = {};
        return answer;
    }

    //region 막혔을 때만 확인할 단계별 힌트
    /*
     * - values보다 길이가 1 더 크고 첫 값이 0인 누적 합 배열을 만든다.
     * - 누적 합 배열의 다음 칸에는 이전까지의 합과 현재 값을 더해 저장한다.
     * - prefixSums[end]에서 prefixSums[start - 1]을 뺀다.
     */
    //endregion
}

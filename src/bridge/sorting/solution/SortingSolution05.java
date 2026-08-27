package bridge.sorting.solution;

import java.util.Arrays;

/*
 * 정답 풀이: 가장 가까운 점검 시각 간격 찾기
 *
 * 문제에서 발견해야 했던 단서
 * - 모든 두 점검의 차이 중 가장 작은 값이 필요하다.
 * - 입력 배열의 순서를 바꾸면 안 된다.
 * - 같은 시각이 두 번 나오면 답은 바로 0이다.
 *
 * 정렬과 이웃 비교를 선택한 이유
 * 값을 정렬하면 어떤 두 값 사이에 다른 값이 끼어 있는 경우 그 두 값의 차이는 이웃한 차이보다 작을 수 없다.
 * 따라서 모든 쌍을 비교하지 않고 정렬된 이웃만 확인하면 된다.
 * 값 사이의 차이만 사용하므로 같은 값의 입력 순서를 지키는 안정 정렬은 필요하지 않다.
 *
 * 풀이 순서
 * 1. 점검이 두 개보다 적으면 -1을 반환한다.
 * 2. 입력 배열을 복사해 오름차순으로 정렬한다.
 * 3. 두 번째 값부터 확인하며 현재 값과 바로 앞 값의 차이를 계산한다.
 * 4. 지금까지 본 가장 작은 차이를 갱신한다.
 * 5. 차이가 0이면 더 작아질 수 없으므로 바로 반환한다.
 *
 * 예시 데이터 흐름
 * [40, 5, 17, 20]을 복사해 정렬하면 [5, 17, 20, 40]이다.
 * 이웃 차이는 12, 3, 20이고 가장 작은 값은 3이다.
 *
 * 시간 복잡도: O(n log n), 복사한 배열을 정렬한다.
 * 공간 복잡도: O(n), 원본을 보존할 복사본을 만든다.
 *
 * 초보자가 실수하기 쉬운 부분
 * - 입력 배열을 직접 정렬해 원본을 바꾼다.
 * - 모든 쌍을 이중 반복으로 확인해 최대 길이에서 너무 오래 걸린다.
 * - 같은 값 두 개를 하나로 없애 차이 0을 놓친다.
 * - 원소가 하나일 때 큰 초기값을 그대로 반환한다.
 */
public final class SortingSolution05 {

    private SortingSolution05() {
    }

    public static int solve(int[] inspections) {
        if (inspections.length < 2) {
            return -1;
        }

        int[] sorted = Arrays.copyOf(inspections, inspections.length);
        Arrays.sort(sorted);

        int minimumGap = sorted[1] - sorted[0];
        for (int index = 2; index < sorted.length; index++) {
            int gap = sorted[index] - sorted[index - 1];
            minimumGap = Math.min(minimumGap, gap);
            if (minimumGap == 0) {
                return 0;
            }
        }
        return minimumGap;
    }
}

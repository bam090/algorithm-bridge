package bridge.sorting.solution;

import java.util.Arrays;

/*
 * 정답 풀이: 선택 구간의 값 간격표 만들기
 *
 * 문제에서 발견해야 했던 단서
 * - 입력 전체가 아니라 지정된 구간만 사용한다.
 * - 선택한 값은 정렬된 뒤 이웃끼리 비교한다.
 * - 입력 배열의 순서는 보존해야 한다.
 *
 * 구간 복사와 정렬을 선택한 이유
 * 선택 구간을 새 배열로 복사하면 원본을 바꾸지 않고 그 구간만 정렬할 수 있다.
 * 정렬 뒤에는 작은 값부터 이웃하므로 바로 앞 값과 현재 값의 차이를 차례대로 계산할 수 있다.
 *
 * 풀이 순서
 * 1. startIndex부터 endIndex까지를 새 배열로 복사한다.
 * 2. 복사한 배열을 오름차순으로 정렬한다.
 * 3. 선택한 값의 개수보다 하나 작은 결과 배열을 만든다.
 * 4. 두 번째 값부터 확인하며 현재 값에서 바로 앞 값을 뺀다.
 * 5. 간격 배열을 반환한다.
 *
 * 예시 데이터 흐름
 * [8, 3, 12, 3, 7]에서 위치 1부터 4까지 복사하면 [3, 12, 3, 7]이다.
 * 정렬하면 [3, 3, 7, 12]가 된다.
 * 이웃 차이는 3-3=0, 7-3=4, 12-7=5이므로 [0, 4, 5]를 반환한다.
 *
 * 시간 복잡도: O(k log k), k는 선택한 구간의 길이다.
 * 공간 복잡도: O(k), 선택 구간과 간격 결과를 새 배열로 만든다.
 *
 * 초보자가 실수하기 쉬운 부분
 * - copyOfRange의 끝 위치가 포함된다고 생각해 마지막 값을 빠뜨린다.
 * - measurements 전체를 정렬해 선택하지 않은 값까지 사용한다.
 * - 결과 길이를 선택한 값의 개수와 같게 만들어 마지막 칸을 남긴다.
 * - 현재 값에서 앞 값을 빼지 않고 반대로 빼서 음수 간격을 만든다.
 */
public final class SortingSolution04 {

    private SortingSolution04() {
    }

    public static int[] solve(int[] measurements, int startIndex, int endIndex) {
        // 지정 구간만 정렬해야 하지만 입력 배열의 순서는 보존해야 한다.
        // 구간을 복사해 정렬하면 원본을 건드리지 않고 이웃한 값의 차이를 바로 구할 수 있다.
        // [1] startIndex부터 endIndex까지를 새 배열로 복사한다.
        int[] selected = Arrays.copyOfRange(measurements, startIndex, endIndex + 1);
        // [2] 복사한 배열을 오름차순으로 정렬한다.
        Arrays.sort(selected);

        // [3] 선택한 값의 개수보다 하나 작은 결과 배열을 만든다.
        int[] answer = new int[selected.length - 1];
        // [4] 두 번째 값부터 확인하며 현재 값에서 바로 앞 값을 뺀다.
        for (int index = 1; index < selected.length; index++) {
            answer[index - 1] = selected[index] - selected[index - 1];
        }
        // [5] 간격 배열을 반환한다.
        return answer;
    }
}

package bridge.sorting.solution;

/*
 * 정답 풀이: 반복 측정값만 정리하기
 *
 * 문제에서 발견해야 했던 단서
 * - 측정값은 -1_000부터 1_000까지라 가능한 값의 종류가 작고 고정되어 있다.
 * - 같은 값을 몇 번 보았는지 알아야 minimumCount 조건을 확인할 수 있다.
 * - 결과는 작은 값부터 정렬되어야 한다.
 *
 * 빈도 배열을 선택한 이유
 * 가능한 값마다 칸 하나를 준비하면 입력을 한 번 읽으며 등장 횟수를 저장할 수 있다.
 * 그 배열을 작은 값에 해당하는 칸부터 확인하면 비교 정렬 없이 결과 순서를 복원할 수 있다.
 *
 * 풀이 순서
 * 1. 측정값마다 1_000을 더한 위치의 횟수를 늘린다.
 * 2. minimumCount 이상인 값들의 횟수를 더해 결과 길이를 구한다.
 * 3. 결과 길이만큼 새 배열을 만든다.
 * 4. 작은 값부터 확인하며 조건을 만족한 값을 나온 횟수만큼 결과에 담는다.
 * 5. 새 배열을 반환한다.
 *
 * 예시 데이터 흐름
 * -1은 2번, 2는 1번, 3은 3번, 4는 1번 나온다.
 * minimumCount가 2이므로 -1과 3만 남긴다.
 * 작은 값부터 횟수만큼 복원하면 [-1, -1, 3, 3, 3]이다.
 *
 * 시간 복잡도: O(n + R), n은 입력 길이이고 R은 가능한 값 2_001개의 범위다.
 * 공간 복잡도: O(n + R), 횟수 배열과 결과 배열을 사용한다.
 *
 * 초보자가 실수하기 쉬운 부분
 * - 음수를 그대로 배열 위치로 사용하면 오류가 난다.
 * - 조건을 만족한 값의 종류 수만 결과 길이로 세면 중복된 값을 모두 담지 못한다.
 * - 입력 배열을 직접 정렬하면 원본 보존 조건을 어긴다.
 */
public final class SortingSolution01 {

    private static final int MINIMUM_VALUE = -1_000;
    private static final int MAXIMUM_VALUE = 1_000;

    private SortingSolution01() {
    }

    public static int[] solve(int[] readings, int minimumCount) {
        int[] counts = new int[MAXIMUM_VALUE - MINIMUM_VALUE + 1];
        for (int reading : readings) {
            counts[reading - MINIMUM_VALUE]++;
        }

        int resultLength = 0;
        for (int count : counts) {
            if (count >= minimumCount) {
                resultLength += count;
            }
        }

        int[] answer = new int[resultLength];
        int writeIndex = 0;
        for (int index = 0; index < counts.length; index++) {
            if (counts[index] < minimumCount) {
                continue;
            }

            int value = index + MINIMUM_VALUE;
            for (int count = 0; count < counts[index]; count++) {
                answer[writeIndex++] = value;
            }
        }
        return answer;
    }
}

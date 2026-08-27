package bridge.sorting.solution;

import java.util.Arrays;

/*
 * 정답 풀이: 검토 요청 순서 정하기
 *
 * 문제에서 발견해야 했던 단서
 * - 우선 점수는 큰 값부터, 작업량 점수는 작은 값부터 놓는다.
 * - 두 기준이 모두 같으면 입력 순서를 유지한다.
 * - ID와 두 점수는 같은 배열 위치의 한 요청을 나타낸다.
 *
 * 위치 배열과 Comparator를 선택한 이유
 * 배열 위치를 정렬하면 세 입력 배열을 서로 떼어 놓지 않고 필요한 값을 함께 비교할 수 있다.
 * Integer 객체 배열의 정렬은 안정 정렬이므로 비교 결과가 0인 요청은 입력 순서를 유지한다.
 * Integer.compare를 사용하면 두 점수를 직접 뺄 때 생길 수 있는 정수 넘침을 피할 수 있다.
 *
 * 풀이 순서
 * 1. 0부터 요청 수 바로 전까지의 배열 위치를 만든다.
 * 2. 우선 점수를 내림차순으로 비교한다.
 * 3. 우선 점수가 같으면 작업량 점수를 오름차순으로 비교한다.
 * 4. 두 점수가 같으면 0을 반환해 원래 위치 순서를 유지한다.
 * 5. 정렬된 위치에 해당하는 ID를 새 배열에 담는다.
 *
 * 예시 데이터 흐름
 * 우선 점수 3인 B, C, D가 A보다 먼저 온다.
 * 작업량 10인 C와 D가 작업량 20인 B보다 먼저 온다.
 * C와 D의 두 점수는 같으므로 입력에서 앞선 C가 먼저 온다.
 * 결과는 [C, D, B, A]이다.
 *
 * 시간 복잡도: O(n log n), 요청 위치를 비교 정렬한다.
 * 공간 복잡도: O(n), 위치 배열과 결과 배열을 만든다.
 *
 * 초보자가 실수하기 쉬운 부분
 * - 우선 점수를 오름차순으로 정렬한다.
 * - 첫 기준이 같지 않은데 둘째 기준을 비교한다.
 * - 점수를 빼서 비교해 int 범위 끝값에서 넘침을 만든다.
 * - 모든 기준이 같을 때 ID로 다시 정렬해 입력 순서 조건을 깨뜨린다.
 */
public final class SortingSolution03 {

    private SortingSolution03() {
    }

    public static String[] solve(String[] requestIds, int[] priorityScores, int[] effortScores) {
        Integer[] indexes = new Integer[requestIds.length];
        for (int index = 0; index < indexes.length; index++) {
            indexes[index] = index;
        }

        Arrays.sort(indexes, (left, right) -> {
            int byPriority = Integer.compare(priorityScores[right], priorityScores[left]);
            if (byPriority != 0) {
                return byPriority;
            }
            return Integer.compare(effortScores[left], effortScores[right]);
        });

        String[] answer = new String[requestIds.length];
        for (int index = 0; index < indexes.length; index++) {
            answer[index] = requestIds[indexes[index]];
        }
        return answer;
    }
}

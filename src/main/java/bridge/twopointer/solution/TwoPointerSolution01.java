package bridge.twopointer.solution;

import java.util.Arrays;

/*
 * 정답 풀이: 두 정렬 기록의 공통 번호 모으기
 *
 * 문제에서 발견해야 했던 단서
 * - 두 배열은 이미 오름차순으로 정렬되어 있다.
 * - 같은 값이 각 배열에서 여러 번 나올 수 있다.
 * - 두 배열에 함께 남아 있는 횟수만큼만 공통 값으로 사용해야 한다.
 *
 * 두 포인터를 선택한 이유
 * 두 현재 값 가운데 작은 값은 다른 배열의 현재 값과 같아질 수 없다.
 * 작은 쪽만 앞으로 옮기면 지나간 값을 다시 확인하지 않고 공통 값을 찾을 수 있다.
 * 값이 같을 때 양쪽을 함께 옮기면 각 위치를 한 번만 사용해 함께 남은 횟수만 기록할 수 있다.
 *
 * 풀이 순서
 * 1. 두 배열의 현재 위치와 공통 값을 담을 임시 배열을 준비한다.
 * 2. 두 배열에 값이 남아 있는 동안 현재 값을 비교한다.
 * 3. 한쪽 값이 작으면 그 배열의 위치만 한 칸 옮긴다.
 * 4. 두 값이 같으면 공통 값으로 기록하고 두 위치를 모두 옮긴다.
 * 5. 기록한 개수만큼 임시 배열을 잘라 새 배열로 반환한다.
 *
 * 예시 데이터 흐름
 * 1과 2를 비교하면 1이 작으므로 first 위치를 옮긴다.
 * 4와 2를 비교하면 2가 작으므로 second 위치를 옮긴다.
 * 첫 4와 4를 기록하고 양쪽을 옮기면 다음 4와 4도 한 번 더 기록한다.
 * second에 남은 세 번째 4는 first의 다음 값 7보다 작아 지나가고, 7을 기록해 [4, 4, 7]을 반환한다.
 *
 * 시간 복잡도: O(n + m), 두 위치가 각 배열을 한 번만 지나간다.
 * 공간 복잡도: O(min(n, m)), 공통 값이 들어갈 임시 배열과 결과 배열이 필요하다.
 *
 * 초보자가 실수하기 쉬운 부분
 * - 작은 값이 있는 쪽이 아니라 큰 값이 있는 쪽을 옮긴다.
 * - 두 값이 같은데 한쪽 위치만 옮겨 반대쪽의 같은 위치를 여러 번 사용한다.
 * - 한 배열이 끝난 뒤 다른 배열의 남은 값에서도 공통 값을 찾으려 한다.
 * - 입력 배열 중 하나를 결과 배열로 사용해 원본을 바꾼다.
 */
public final class TwoPointerSolution01 {

    private TwoPointerSolution01() {
    }

    public static int[] solve(int[] first, int[] second) {
        // 두 배열이 정렬되어 있어 작은 현재 값은 앞으로도 상대의 현재 값과 같아질 수 없다.
        // 두 위치를 따로 움직이면 이미 확인한 값을 다시 읽지 않고 공통 값만 찾을 수 있다.
        // 값이 같을 때는 양쪽을 함께 옮겨 한쪽의 같은 위치를 여러 번 사용하지 않는다.

        // [1] 두 배열의 현재 위치와 공통 값을 담을 임시 배열을 준비한다.
        int firstIndex = 0;
        int secondIndex = 0;
        int[] commonValues = new int[Math.min(first.length, second.length)];
        int commonCount = 0;

        while (firstIndex < first.length && secondIndex < second.length) {
            // [2] 두 배열에 값이 남아 있는 동안 현재 값을 비교한다.
            int firstValue = first[firstIndex];
            int secondValue = second[secondIndex];

            // [3] 한쪽 값이 작으면 그 배열의 위치만 한 칸 옮긴다.
            if (firstValue < secondValue) {
                firstIndex++;
            } else if (firstValue > secondValue) {
                secondIndex++;
            } else {
                // [4] 두 값이 같으면 공통 값으로 기록하고 두 위치를 모두 옮긴다.
                commonValues[commonCount++] = firstValue;
                firstIndex++;
                secondIndex++;
            }
        }

        // [5] 기록한 개수만큼 임시 배열을 잘라 새 배열로 반환한다.
        return Arrays.copyOf(commonValues, commonCount);
    }
}

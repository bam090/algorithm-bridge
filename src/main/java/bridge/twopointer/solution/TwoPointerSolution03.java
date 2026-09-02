package bridge.twopointer.solution;

/*
 * 정답 풀이: 기준 합을 채우는 가장 짧은 구간 찾기
 *
 * 문제에서 발견해야 했던 단서
 * - 연속한 값만 한 구간으로 선택한다.
 * - 모든 값이 0 이상이다.
 * - 합이 target 이상인 구간 가운데 가장 짧은 위치가 필요하다.
 *
 * 길이가 변하는 두 포인터 구간을 선택한 이유
 * 값이 0 이상이라 오른쪽 값을 넣으면 합이 줄지 않고 왼쪽 값을 빼면 합이 늘지 않는다.
 * 오른쪽으로 합을 채운 뒤 가능한 동안 왼쪽을 줄이면 같은 값을 다시 더하지 않고 가장 짧은 구간을 확인할 수 있다.
 *
 * 풀이 순서
 * 1. 왼쪽 위치, 현재 합과 아직 찾지 못한 최선의 구간을 준비한다.
 * 2. 오른쪽 위치의 값을 현재 합에 더한다.
 * 3. 합이 target 이상인 동안 현재 구간을 확인한다.
 * 4. 더 짧은 구간이면 시작과 끝을 기억한다.
 * 5. 왼쪽 값을 합에서 빼고 왼쪽 위치를 한 칸 옮긴다.
 * 6. 찾은 구간을 1부터 센 위치로 반환하거나 없으면 빈 배열을 반환한다.
 *
 * 예시 데이터 흐름
 * [2, 1, 5, 2]까지 더하면 합이 10이므로 [1, 4]를 먼저 기억한다.
 * 왼쪽의 2와 1을 차례로 빼도 합이 7이라 [3, 4]로 더 짧아진다.
 * 다음 왼쪽 값 5를 빼면 합이 2가 되어 다시 오른쪽을 늘린다.
 *
 * 시간 복잡도: O(n), 각 값은 오른쪽에서 한 번 들어오고 왼쪽에서 최대 한 번 빠진다.
 * 공간 복잡도: O(1), 결과 배열을 제외하면 위치와 합만 기억한다.
 *
 * 초보자가 실수하기 쉬운 부분
 * - 합이 target 이상일 때 왼쪽을 한 번만 옮겨 더 짧은 구간을 놓친다.
 * - 오른쪽 값을 더하기 전에 구간을 검사해 마지막 값을 빼먹는다.
 * - 길이가 같은 뒤쪽 구간으로 앞선 구간을 덮어쓴다.
 * - 위치를 0부터 센 값 그대로 반환한다.
 * - 합을 int로 계산해 큰 입력에서 넘침이 생긴다.
 */
public final class TwoPointerSolution03 {

    private TwoPointerSolution03() {
    }

    public static int[] solve(int[] amounts, long target) {
        // 모든 값이 0 이상이라 오른쪽을 늘리면 합이 줄지 않고 왼쪽을 줄이면 합이 커지지 않는다.
        // 두 위치를 되돌리지 않아도 각 오른쪽 끝에서 만들 수 있는 가장 짧은 구간을 확인할 수 있다.

        // [1] 왼쪽 위치, 현재 합과 아직 찾지 못한 최선의 구간을 준비한다.
        int left = 0;
        long currentSum = 0;
        int bestStart = -1;
        int bestEnd = -1;
        int bestLength = Integer.MAX_VALUE;

        for (int right = 0; right < amounts.length; right++) {
            // [2] 오른쪽 위치의 값을 현재 합에 더한다.
            currentSum += amounts[right];

            // [3] 합이 target 이상인 동안 현재 구간을 확인한다.
            while (currentSum >= target) {
                int currentLength = right - left + 1;

                // [4] 더 짧은 구간이면 시작과 끝을 기억한다.
                if (currentLength < bestLength) {
                    bestStart = left;
                    bestEnd = right;
                    bestLength = currentLength;
                }

                // [5] 왼쪽 값을 합에서 빼고 왼쪽 위치를 한 칸 옮긴다.
                currentSum -= amounts[left];
                left++;
            }
        }

        // [6] 찾은 구간을 1부터 센 위치로 반환하거나 없으면 빈 배열을 반환한다.
        if (bestStart == -1) {
            return new int[0];
        }
        return new int[]{bestStart + 1, bestEnd + 1};
    }
}

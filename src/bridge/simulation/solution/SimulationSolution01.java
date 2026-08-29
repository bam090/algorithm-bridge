package bridge.simulation.solution;

/*
 * 정답 풀이: 수치 조절 기록
 *
 * 문제에서 발견해야 했던 단서
 * - 명령은 현재 값을 1 올리거나 1 내린다.
 * - 범위를 벗어난 명령은 현재 값에 반영하지 않고 따로 세어야 한다.
 *
 * 후보 상태를 선택한 이유
 * 현재 값을 바로 바꾸면 범위를 벗어났을 때 이전 값을 되살려야 한다.
 * 후보 값을 먼저 계산하면 검사 결과에 따라 확정하거나 버리기만 하면 된다.
 *
 * 풀이 순서
 * 1. 현재 값을 initialValue로 시작한다.
 * 2. 명령을 1 또는 -1의 변화량으로 바꾼다.
 * 3. 현재 값에 변화량을 더한 후보 값을 만든다.
 * 4. 후보가 허용 범위 안이면 현재 값으로 확정한다.
 * 5. 범위 밖이면 현재 값은 그대로 두고 무시한 명령 수를 늘린다.
 * 6. 최종 값과 무시한 명령 수를 새 배열로 반환한다.
 *
 * 예시 데이터 흐름
 * 시작 값 0에서 첫 UP 후보 1은 범위 안이므로 확정한다.
 * 다음 UP 후보 2는 최댓값 1을 넘으므로 무시한다.
 * DOWN 후보 0은 범위 안이므로 확정해 [0, 1]을 반환한다.
 *
 * 시간 복잡도: O(n), n은 commands의 길이다.
 * 공간 복잡도: O(1), 반환 배열을 제외하고 명령 수와 관계없이 같은 수의 변수만 사용한다.
 *
 * 초보자가 실수하기 쉬운 부분
 * - 후보를 검사하기 전에 현재 값을 바꾸면 거절된 명령도 반영될 수 있다.
 * - 최솟값과 최댓값 자체는 허용된다는 점을 놓칠 수 있다.
 */
public final class SimulationSolution01 {

    private SimulationSolution01() {
    }

    public static int[] solve(int initialValue, String[] commands, int minimumValue, int maximumValue) {
        // 명령을 적용할지는 바뀐 값이 허용 범위 안인지 보고 결정해야 한다.
        // current를 바로 고치지 않고 candidate를 검사하면 필요한 경우에만 값을 저장할 수 있다.
        // [1] 현재 값을 initialValue로 시작한다.
        int current = initialValue;
        int rejectedCount = 0;

        for (String command : commands) {
            // [2] 명령을 1 또는 -1의 변화량으로 바꾼다.
            int change = switch (command) {
                case "UP" -> 1;
                case "DOWN" -> -1;
                default -> throw new IllegalArgumentException("지원하지 않는 명령: " + command);
            };

            // [3] 현재 값에 변화량을 더한 후보 값을 만든다.
            int candidate = current + change;
            // [4] 후보가 허용 범위 안이면 현재 값으로 확정한다.
            if (minimumValue <= candidate && candidate <= maximumValue) {
                current = candidate;
            } else {
                // [5] 범위 밖이면 현재 값은 그대로 두고 무시한 명령 수를 늘린다.
                rejectedCount++;
            }
        }

        // [6] 최종 값과 무시한 명령 수를 새 배열로 반환한다.
        return new int[]{current, rejectedCount};
    }
}

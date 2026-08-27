package bridge.dynamicprogramming;

import java.util.Arrays;

/**
 * 동적 계획법은 같은 작은 계산을 다시 하지 않도록 답을 표에 적어 두고,
 * 그 답으로 더 큰 문제의 답을 만드는 방법이다.
 * <p>
 * 표의 한 칸이 무엇을 뜻하는지 먼저 정해야 한다. 그다음 직접 알 수 있는 가장 작은 답을
 * 적고, 이미 계산한 어느 칸을 읽어 다음 칸을 만들지 정한다.
 */
public final class DynamicProgrammingGuide {

    private DynamicProgrammingGuide() {
    }

    public static void main(String[] args) {
        // 1. state[day]는 day일까지 모은 점수라고 뜻을 먼저 정한다.
        int[] dailyPoints = {3, 1, 4, 2};
        int[] state = new int[dailyPoints.length];
        state[0] = dailyPoints[0];
        for (int day = 1; day < dailyPoints.length; day++) {
            state[day] = state[day - 1] + dailyPoints[day];
        }
        System.out.println("[1] 날짜별 누적 점수: " + Arrays.toString(state));

        // 2. 현재 칸이 위쪽과 왼쪽 칸을 읽는다면 위에서 아래, 왼쪽에서 오른쪽으로 계산한다.
        int[][] value = {
                {2, 1, 3},
                {4, 2, 1}
        };
        int[][] minimum = minimumCostTable(value);
        System.out.println("\n[2] 각 칸까지의 최소 비용표");
        for (int[] row : minimum) {
            System.out.println(Arrays.toString(row));
        }

        // 3. int 범위를 넘을 수 있는 합은 long으로 계산한다.
        long largeTotal = 1_000_000_000L * 1_000L;
        System.out.println("\n[3] long으로 계산한 큰 합: " + largeTotal);

        // 4. 문제에서 나머지를 요구하면 값이 커진 뒤가 아니라 매 단계에서 나머지를 구한다.
        int modulo = 10_007;
        long previous = 9_500;
        long added = 2_000;
        long next = (previous + added) % modulo;
        System.out.println("[4] 단계별 나머지 계산: " + next);
    }

    private static int[][] minimumCostTable(int[][] value) {
        int rowCount = value.length;
        int columnCount = value[0].length;
        int[][] minimum = new int[rowCount][columnCount];

        for (int row = 0; row < rowCount; row++) {
            for (int column = 0; column < columnCount; column++) {
                if (row == 0 && column == 0) {
                    minimum[row][column] = value[row][column];
                } else if (row == 0) {
                    minimum[row][column] = minimum[row][column - 1] + value[row][column];
                } else if (column == 0) {
                    minimum[row][column] = minimum[row - 1][column] + value[row][column];
                } else {
                    minimum[row][column] = Math.min(
                            minimum[row - 1][column],
                            minimum[row][column - 1]
                    ) + value[row][column];
                }
            }
        }
        return minimum;
    }

    /*
     * 5. 문제에서 동적 계획법을 떠올릴 단서
     *
     * - 큰 문제의 답을 더 작은 크기의 답으로 만들 수 있다.
     * - 같은 작은 계산이 여러 선택에서 반복된다.
     * - 경우의 수, 최솟값, 최댓값처럼 지금까지의 가장 좋은 값을 이어 간다.
     * - 현재 선택이 바로 앞 선택이나 현재 위치의 위·왼쪽 칸에 영향을 받는다.
     */

    /*
     * 6. 표를 만드는 순서
     *
     * 1) 한 칸의 뜻을 짧은 문장으로 적는다.
     * 2) 계산하지 않아도 알 수 있는 가장 작은 입력의 답을 적는다.
     * 3) 마지막 행동을 나누어 현재 칸이 어느 작은 칸을 읽는지 찾는다.
     * 4) 읽을 칸이 먼저 계산되도록 반복문의 방향을 정한다.
     * 5) 문제에서 요구한 마지막 칸이나 여러 마지막 상태 중 결과를 고른다.
     */

    /*
     * 7. 자주 사용하는 Java 도구
     *
     * - int[] 또는 long[]: 크기 하나로 상태를 구분할 때 쓴다.
     * - int[][] 또는 long[][]: 위치나 마지막 선택처럼 상태 기준이 두 개일 때 쓴다.
     * - Arrays.fill(): 아직 도달하지 못한 상태를 -1 같은 값으로 채울 때 쓴다.
     * - Math.min(), Math.max(): 여러 이전 상태 중 더 좋은 값을 고를 때 쓴다.
     * - long: 입력 개수와 한 칸의 값이 곱해져 int 범위를 넘을 수 있을 때 쓴다.
     */

    /*
     * 8. 자주 하는 실수와 확인 방법
     *
     * - 표의 칸 뜻을 정하지 않고 점화식부터 외운다.
     * - 입력이 0이나 1일 때 존재하지 않는 두 번째 칸에 값을 넣는다.
     * - 아직 계산하지 않은 아래쪽이나 오른쪽 칸을 먼저 읽는다.
     * - 도달하지 못한 상태를 점수 0과 같은 값으로 취급한다.
     * - 합이 커질 수 있는데 int로 계산하거나, 나머지를 마지막에 한 번만 구한다.
     * - 입력 배열을 상태표로 고쳐 써서 원본을 바꾼다.
     */

    /*
     * 9. 코딩 테스트 문제를 읽는 순서
     *
     * 1) 한 상태는 어떤 작은 문제의 답인가?
     * 2) 가장 작은 입력의 답은 무엇인가?
     * 3) 마지막 행동은 어떤 경우로 나눌 수 있는가?
     * 4) 각 경우는 어느 이전 상태와 연결되는가?
     * 5) 어느 방향으로 계산해야 필요한 상태가 먼저 준비되는가?
     * 6) 도달할 수 없는 상태는 어떤 값으로 구분할 것인가?
     * 7) 결과는 한 칸인가, 여러 마지막 상태 중 하나인가?
     * 8) int를 넘을 수 있는가, 단계별 나머지가 필요한가?
     */
}

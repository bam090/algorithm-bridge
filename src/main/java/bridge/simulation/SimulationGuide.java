package bridge.simulation;

import java.util.Arrays;

/**
 * 시뮬레이션은 문제에 적힌 규칙을 한 번씩 그대로 실행하며 현재 상태를 바꾸는 방법이다.
 * 복잡한 공식을 먼저 찾기보다, 지금 값에서 다음 값이 어떻게 만들어지는지 순서를 정하는 것이 중요하다.
 *
 * <p>범위가 있는 상태는 먼저 후보를 계산하고, 후보가 허용 범위 안에 있을 때만 현재 상태로 확정한다.
 * 먼저 상태를 바꾼 뒤 되돌리면 거절된 명령의 수나 이전 상태를 놓치기 쉽다.</p>
 *
 * <p>2차원 배열의 위치를 바꿀 때는 원본을 읽으면서 새 배열에 결과를 적는다.
 * 같은 배열에서 읽기와 쓰기를 동시에 하면 아직 읽지 않은 값이 덮일 수 있다.</p>
 */
public final class SimulationGuide {

    private SimulationGuide() {
    }

    public static void main(String[] args) {
        applyOnlyValidChanges();
        moveCellsToANewBoard();
        shrinkBoundaries();
        repeatUntilFinished();
    }

    private static void applyOnlyValidChanges() {
        int current = 2;
        int minimum = 0;
        int maximum = 3;
        int[] changes = {1, 1, -1, -1, -1, -1};

        for (int change : changes) {
            int candidate = current + change;
            if (minimum <= candidate && candidate <= maximum) {
                current = candidate;
            }
        }

        System.out.println("[1] 후보를 검사한 뒤 확정하기");
        System.out.println("마지막 값: " + current);
        System.out.println();
    }

    private static void moveCellsToANewBoard() {
        int[][] original = {
                {1, 2, 3},
                {4, 5, 6}
        };
        int[][] moved = new int[original.length][original[0].length];

        for (int row = 0; row < original.length; row++) {
            for (int column = 0; column < original[row].length; column++) {
                int newColumn = (column + 1) % original[row].length;
                moved[row][newColumn] = original[row][column];
            }
        }

        System.out.println("[2] 원본 위치를 새 배열의 위치로 옮기기");
        System.out.println("원본: " + Arrays.deepToString(original));
        System.out.println("결과: " + Arrays.deepToString(moved));
        System.out.println();
    }

    private static void shrinkBoundaries() {
        int top = 0;
        int bottom = 3;
        int left = 0;
        int right = 4;

        System.out.println("[3] 바깥 범위를 처리한 뒤 경계 줄이기");
        while (top <= bottom && left <= right) {
            System.out.println("행 " + top + "~" + bottom + ", 열 " + left + "~" + right);
            top++;
            bottom--;
            left++;
            right--;
        }
        System.out.println();
    }

    private static void repeatUntilFinished() {
        int current = 23;
        int rounds = 0;

        while (current > 1) {
            current /= 2;
            rounds++;
        }

        System.out.println("[4] 종료 조건까지 상태 바꾸기");
        System.out.println("마지막 값: " + current + ", 반복 횟수: " + rounds);
    }

    /*
     * 시뮬레이션을 떠올릴 문제의 단서
     *
     * - 명령을 순서대로 실행해야 한다.
     * - 현재 위치나 값을 다음 명령에서도 이어서 사용한다.
     * - 범위를 벗어난 명령은 무시하거나 따로 센다.
     * - 2차원 배열의 각 칸이 정해진 규칙으로 다른 위치로 이동한다.
     * - 위, 아래, 왼쪽, 오른쪽처럼 여러 경계를 기억해야 한다.
     * - 어떤 종료 상태가 될 때까지 같은 분석과 변환을 반복한다.
     */

    /*
     * 자주 사용하는 Java 기능
     *
     * - switch: 명령마다 서로 다른 변화량이나 행동을 고른다.
     * - Math.min, Math.max: 경계와 현재 최솟값·최댓값을 비교한다.
     * - Arrays.copyOf: 원본을 남겨야 할 때 새 배열을 만든다.
     * - long: 많은 값을 더하거나 큰 상태를 다룰 때 int 넘침을 피한다.
     * - %: 반복되는 위치를 배열 범위 안으로 되돌린다.
     */

    /*
     * 데이터가 처리되는 흐름
     *
     * 현재 상태를 정한다.
     * → 이번 명령이나 규칙으로 후보 상태를 계산한다.
     * → 범위와 추가 조건을 확인한다.
     * → 유효한 후보만 다음 현재 상태로 확정한다.
     * → 종료 조건을 만족할 때까지 필요한 만큼 반복한다.
     */

    /*
     * 초보자가 자주 하는 실수
     *
     * - 범위를 확인하기 전에 현재 상태를 바꾼다.
     * - 행과 열의 순서를 뒤바꾸거나 직사각형을 정사각형으로 가정한다.
     * - 같은 2차원 배열에 결과를 덮어써서 아직 읽지 않은 값을 잃는다.
     * - 한 줄이나 한 칸만 남은 경우를 두 번 처리한다.
     * - 종료할 때마다 상태가 실제로 작아지거나 달라지는지 확인하지 않는다.
     * - 여러 int 값을 더한 합이 int 범위를 넘는지 확인하지 않는다.
     */

    /*
     * 코딩 테스트 문제를 읽는 순서
     *
     * 1. 현재 상태에 포함되는 값을 적는다.
     * 2. 명령 하나가 상태를 바꾸는 정확한 순서를 적는다.
     * 3. 후보를 언제 거절하고 언제 확정하는지 적는다.
     * 4. 행·열과 경계의 포함 범위를 그림으로 확인한다.
     * 5. 반복이 끝나는 조건과 매번 끝에 가까워지는 이유를 확인한다.
     * 6. 누적값이 크면 long이 필요한지 계산한다.
     * 7. 빈 입력, 1×1, 직사각형, 경계값과 최대 입력을 손으로 확인한다.
     */
}

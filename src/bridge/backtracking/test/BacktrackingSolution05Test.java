package bridge.backtracking.test;

import bridge.backtracking.solution.BacktrackingSolution05;

import java.util.Arrays;

/** 외부 테스트 라이브러리 없이 BacktrackingSolution05를 검증한다. */
public final class BacktrackingSolution05Test {

    private static int passed;
    private static int failed;

    private BacktrackingSolution05Test() {
    }

    /*
     * 매개변수 | 허용 범위 | 실제 확인 값
     * 프로젝트 수 | 1 이상 8 이하 | 1, 2, 8
     * 프로젝트별 시간 후보 수 | 1 이상 6 이하 | 1, 2, 3, 6
     * pointsByHours 값 | 0 이상 1,000 이하 | 0, 940, 1,000
     * hourLimit | 0 이상 12 이하 | 0, 1, 2, 12
     * 추가 계약 | 최고 점수, 적은 사용 시간, 앞에서 작은 배정, 결과 복사, 원본 보존 | 각 동점과 최대 탐색
     */
    public static void main(String[] args) {
        check(
                "두 프로젝트의 최고 점수",
                new int[][]{{0, 4, 7}, {0, 5, 6}},
                2,
                new int[]{1, 1}
        );
        check(
                "시간 한도 0과 점수 중간값·상한",
                new int[][]{{0, 940}, {0, 1_000}},
                0,
                new int[]{0, 0}
        );
        check(
                "점수가 같으면 더 적은 시간 사용",
                new int[][]{{0, 10, 10}},
                2,
                new int[]{1}
        );
        check(
                "점수와 시간도 같으면 앞 프로젝트 시간을 작게",
                new int[][]{{0, 10}, {0, 10}},
                1,
                new int[]{0, 1}
        );
        check(
                "배정할 수 없는 남은 시간은 사용하지 않음",
                new int[][]{{0}, {0}},
                12,
                new int[]{0, 0}
        );
        check(
                "점수 상한 동률에서 배열 순서 적용",
                new int[][]{{0, 940, 1_000}, {0, 0, 1_000}},
                2,
                new int[]{0, 2}
        );
        runCase("최대 프로젝트·후보·시간 한도", BacktrackingSolution05Test::testMaximumDimensions);

        finish();
    }

    private static void check(String name, int[][] pointsByHours, int hourLimit, int[] expected) {
        runCase(name, () -> {
            int[][] original = cloneMatrix(pointsByHours);
            int[] actual = BacktrackingSolution05.solve(pointsByHours, hourLimit);

            assertArrayEquals(expected, actual);
            if (!Arrays.deepEquals(original, pointsByHours)) {
                throw new AssertionError("입력 점수표가 바뀌었다.");
            }
            for (int[] row : pointsByHours) {
                if (actual == row) {
                    throw new AssertionError("결과는 입력 행과 다른 배열이어야 한다.");
                }
            }
        });
    }

    private static void testMaximumDimensions() {
        int[][] points = new int[8][6];
        for (int project = 0; project < points.length; project++) {
            for (int hours = 0; hours < points[project].length; hours++) {
                points[project][hours] = hours;
            }
        }

        int[] actual = BacktrackingSolution05.solve(points, 12);
        assertArrayEquals(new int[]{0, 0, 0, 0, 0, 2, 5, 5}, actual);
    }

    private static int[][] cloneMatrix(int[][] matrix) {
        int[][] clone = new int[matrix.length][];
        for (int row = 0; row < matrix.length; row++) {
            clone[row] = matrix[row].clone();
        }
        return clone;
    }

    private static void runCase(String name, Runnable test) {
        try {
            test.run();
            passed++;
            System.out.println("[PASS] " + name);
        } catch (AssertionError | RuntimeException error) {
            failed++;
            System.out.println("[FAIL] " + name + ": " + error.getMessage());
        }
    }

    private static void finish() {
        System.out.println("[RESULT] BacktrackingSolution05: " + passed + "/" + (passed + failed) + " 통과");
        if (failed > 0) {
            throw new AssertionError("BacktrackingSolution05 실패: " + failed + "건");
        }
    }

    private static void assertArrayEquals(int[] expected, int[] actual) {
        if (!Arrays.equals(expected, actual)) {
            throw new AssertionError(
                    "expected=" + Arrays.toString(expected) + ", actual=" + Arrays.toString(actual)
            );
        }
    }
}

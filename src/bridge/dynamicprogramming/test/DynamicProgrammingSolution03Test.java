package bridge.dynamicprogramming.test;

import bridge.dynamicprogramming.solution.DynamicProgrammingSolution03;

import java.util.Arrays;

/** 외부 테스트 라이브러리 없이 DynamicProgrammingSolution03을 검증한다. */
public final class DynamicProgrammingSolution03Test {

    private static int passed;
    private static int failed;

    private DynamicProgrammingSolution03Test() {
    }

    /*
     * 매개변수 | 허용 범위 | 실제 확인 값
     * 행 수 | 0 이상 1,000 이하 | 0, 1, 3, 20, 100, 1,000
     * 열 수 | 0 이상 1,000 이하 | 0, 1, 3, 4, 20, 100, 940, 1,000
     * 전체 칸 수 | 0 이상 100,000 이하 | 0, 1, 12, 400, 940, 100,000
     * 칸 값 | 0 또는 1 | 열린 칸, 장애물, 막힌 출발·도착
     * 경로 수 | 0, 1, 일반값, 나머지 적용값 | 0, 1, 4, 6, 345,263,555
     * 추가 계약 | 도달 불가, 긴 한 행, 가로·세로 최대, 원본 보존 | 각각 실행
     *
     * 대표 오답
     * - 빈 바깥 배열이나 빈 행에서 첫 칸을 읽는다.
     * - 출발·도착 장애물과 중간 장벽을 무시한다.
     * - 계산 방향을 바꾸거나 위쪽·왼쪽 중 하나만 더한다.
     * - 행과 열 반복 범위를 바꾸어 직사각형에서 실패한다.
     * - 큰 경로 수를 int로 더하거나 나머지를 적용하지 않는다.
     */
    public static void main(String[] args) {
        check("빈 바깥 배열", new int[][]{}, 0);
        check("행은 있지만 열이 0개", new int[][]{{}, {}}, 0);
        check("열린 1×1 창고", new int[][]{{0}}, 1);
        check("막힌 출발이자 도착 칸", new int[][]{{1}}, 0);
        check(
                "장애물을 우회하는 일반 직사각형",
                new int[][]{
                        {0, 0, 0, 0},
                        {0, 1, 0, 0},
                        {0, 0, 0, 0}
                },
                4
        );
        check(
                "도착 칸은 열려 있지만 갈 수 없음",
                new int[][]{
                        {0, 1, 0},
                        {1, 1, 0},
                        {0, 0, 0}
                },
                0
        );
        check("3×3 열린 창고", new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}}, 6);
        check("경로 수 나머지와 long 덧셈", new int[20][20], 345_263_555);
        check("열 길이 일반 중간값 940", new int[1][940], 1);
        check("행 수 상한과 전체 칸 상한", onePathCorridor(1_000, 100), 1);
        check("열 수 상한과 전체 칸 상한", onePathCorridor(100, 1_000), 1);

        finish();
    }

    private static void check(String name, int[][] warehouse, int expected) {
        runCase(name, () -> {
            int[][] before = cloneMatrix(warehouse);
            int actual = DynamicProgrammingSolution03.solve(warehouse);

            assertEquals(expected, actual);
            if (!Arrays.deepEquals(before, warehouse)) {
                throw new AssertionError("입력 창고 배열이 바뀌었다.");
            }
        });
    }

    private static int[][] onePathCorridor(int rowCount, int columnCount) {
        int[][] warehouse = new int[rowCount][columnCount];
        for (int[] row : warehouse) {
            Arrays.fill(row, 1);
        }
        Arrays.fill(warehouse[0], 0);
        for (int row = 0; row < rowCount; row++) {
            warehouse[row][columnCount - 1] = 0;
        }
        return warehouse;
    }

    private static int[][] cloneMatrix(int[][] source) {
        int[][] copy = new int[source.length][];
        for (int row = 0; row < source.length; row++) {
            copy[row] = source[row].clone();
        }
        return copy;
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
        System.out.println("[RESULT] DynamicProgrammingSolution03: "
                + passed + "/" + (passed + failed) + " 통과");
        if (failed > 0) {
            throw new AssertionError("DynamicProgrammingSolution03 실패: " + failed + "건");
        }
    }

    private static void assertEquals(int expected, int actual) {
        if (expected != actual) {
            throw new AssertionError("expected=" + expected + ", actual=" + actual);
        }
    }
}

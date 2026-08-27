package bridge.backtracking.test;

import bridge.backtracking.solution.BacktrackingSolution04;

import java.util.Arrays;

/** 외부 테스트 라이브러리 없이 BacktrackingSolution04를 검증한다. */
public final class BacktrackingSolution04Test {

    private static int passed;
    private static int failed;

    private BacktrackingSolution04Test() {
    }

    /*
     * 매개변수 | 허용 범위 | 실제 확인 값
     * 날짜 수 | 1 이상 7 이하 | 1, 2, 3, 7
     * 날짜별 후보 수 | 1 이상 7 이하 | 1, 2, 7
     * 담당자·도구 ID | 0 이상 1,000 이하 | 0, 940, 1,000
     * 추가 계약 | 날짜마다 하나, 담당자·도구 각각 중복 금지, 원본 보존 | 한쪽 충돌, 선택 취소, 최대 크기
     */
    public static void main(String[] args) {
        check(
                "날짜 1개·후보 1개의 실제 하한",
                new int[][]{{0}},
                new int[][]{{1_000}},
                true
        );
        check(
                "두 조건을 지키는 일정",
                new int[][]{{1, 2}, {1, 3}},
                new int[][]{{10, 10}, {11, 10}},
                true
        );
        check(
                "담당자만 겹치는 일정",
                new int[][]{{940}, {940}},
                new int[][]{{0}, {1_000}},
                false
        );
        check(
                "도구만 겹치는 일정",
                new int[][]{{0}, {1_000}},
                new int[][]{{940}, {940}},
                false
        );
        check(
                "첫 후보 실패 뒤 표시를 지우고 성공",
                new int[][]{{0, 1}, {0}},
                new int[][]{{0, 1}, {2}},
                true
        );
        check(
                "후보보다 날짜가 많아 완성 불가",
                new int[][]{{1, 2}, {1, 2}, {1, 2}},
                new int[][]{{1, 2}, {2, 1}, {1, 2}},
                false
        );
        check(
                "ID 하한·중간값·상한",
                new int[][]{{0}, {940}, {1_000}},
                new int[][]{{1_000}, {940}, {0}},
                true
        );
        runCase("최대 날짜와 후보 수", BacktrackingSolution04Test::testMaximumDimensions);

        finish();
    }

    private static void check(String name, int[][] workerIds, int[][] toolIds, boolean expected) {
        runCase(name, () -> {
            int[][] originalWorkers = cloneMatrix(workerIds);
            int[][] originalTools = cloneMatrix(toolIds);
            boolean actual = BacktrackingSolution04.solve(workerIds, toolIds);

            if (actual != expected) {
                throw new AssertionError("expected=" + expected + ", actual=" + actual);
            }
            if (!Arrays.deepEquals(originalWorkers, workerIds)
                    || !Arrays.deepEquals(originalTools, toolIds)) {
                throw new AssertionError("입력 2차원 배열이 바뀌었다.");
            }
        });
    }

    private static void testMaximumDimensions() {
        int size = 7;
        int[][] workers = new int[size][size];
        int[][] tools = new int[size][size];
        for (int day = 0; day < size; day++) {
            for (int candidate = 0; candidate < size; candidate++) {
                workers[day][candidate] = candidate;
                tools[day][candidate] = (candidate + day) % size;
            }
        }
        if (!BacktrackingSolution04.solve(workers, tools)) {
            throw new AssertionError("7일·각 7후보 일정은 완성 가능해야 한다.");
        }
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
        System.out.println("[RESULT] BacktrackingSolution04: " + passed + "/" + (passed + failed) + " 통과");
        if (failed > 0) {
            throw new AssertionError("BacktrackingSolution04 실패: " + failed + "건");
        }
    }
}

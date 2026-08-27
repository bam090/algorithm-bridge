package bridge.set.test;

import bridge.set.solution.SetSolution03;

import java.util.Arrays;

/** 외부 테스트 라이브러리 없이 SetSolution03을 검증한다. */
public final class SetSolution03Test {

    private SetSolution03Test() {
    }

    /*
     * 검증 범위
     * 항목        | 제약                  | 실제 확인값
     * itemCount   | 0~100,000             | 0·1·4·6·1,000·100,000
     * connections | 길이 0~100,000        | 0·1·3·4·5·100,000개
     * 작업대 번호 | 0~itemCount-1         | 0·940·999·99,999
     * 대표 오답   | 자기·중복 연결에서 중복 감소, 최종 대표 미확인, 고립 구역 누락, 원본 변경
     */
    public static void main(String[] args) {
        int total = 7;
        int passed = 0;

        passed += runCase(1, "세 연결로 세 구역 남기기와 원본 보존", () -> {
            int[][] connections = {{4, 2}, {3, 1}, {2, 1}};
            int[][] before = copy(connections);
            assertEquals(3, SetSolution03.solve(6, connections));
            assertDeepArrayEquals(before, connections);
        });
        passed += runCase(2, "작업대와 연결이 모두 없음", () -> assertEquals(
                0,
                SetSolution03.solve(0, new int[][]{})
        ));
        passed += runCase(3, "작업대 하나의 자기 연결", () -> assertEquals(
                1,
                SetSolution03.solve(1, new int[][]{{0, 0}})
        ));
        passed += runCase(4, "중복·반대 방향·자기 연결은 다시 줄이지 않음", () -> assertEquals(
                2,
                SetSolution03.solve(
                        4,
                        new int[][]{{0, 1}, {1, 0}, {1, 1}, {2, 3}, {0, 1}}
                )
        ));
        passed += runCase(5, "바로 위 부모가 달라도 같은 구역인 중복 연결", () -> assertEquals(
                1,
                SetSolution03.solve(
                        4,
                        new int[][]{{0, 1}, {2, 3}, {1, 3}, {0, 3}}
                )
        ));
        passed += runCase(6, "작업대 0·940·999를 한 구역으로 연결", () -> {
            int[][] connections = {{0, 940}, {940, 999}};
            int[][] before = copy(connections);
            assertEquals(998, SetSolution03.solve(1_000, connections));
            assertDeepArrayEquals(before, connections);
        });
        passed += runCase(7, "최대 작업대와 연결 수의 긴 연결", () -> {
            int itemCount = 100_000;
            int[][] connections = new int[100_000][2];
            for (int i = 0; i < itemCount - 1; i++) {
                connections[i][0] = i;
                connections[i][1] = i + 1;
            }
            connections[connections.length - 1][0] = itemCount - 1;
            connections[connections.length - 1][1] = itemCount - 1;

            assertEquals(1, SetSolution03.solve(itemCount, connections));
        });

        finish("SetSolution03", passed, total);
    }

    private static int[][] copy(int[][] original) {
        int[][] copied = new int[original.length][];
        for (int i = 0; i < original.length; i++) {
            copied[i] = original[i].clone();
        }
        return copied;
    }

    private static int runCase(int number, String name, Runnable test) {
        try {
            test.run();
            System.out.printf("[PASS] 테스트 %d: %s%n", number, name);
            return 1;
        } catch (AssertionError | RuntimeException error) {
            System.out.printf("[FAIL] 테스트 %d: %s | %s%n", number, name, error.getMessage());
            return 0;
        }
    }

    private static void finish(String solutionName, int passed, int total) {
        System.out.printf("[RESULT] %s: %d/%d 통과%n", solutionName, passed, total);
        if (passed != total) {
            throw new AssertionError(solutionName + ": 통과하지 못한 테스트가 있습니다.");
        }
    }

    private static void assertEquals(int expected, int actual) {
        if (expected != actual) {
            throw new AssertionError("expected=" + expected + ", actual=" + actual);
        }
    }

    private static void assertDeepArrayEquals(int[][] expected, int[][] actual) {
        if (!Arrays.deepEquals(expected, actual)) {
            throw new AssertionError("expected=" + Arrays.deepToString(expected)
                    + ", actual=" + Arrays.deepToString(actual));
        }
    }
}

package bridge.tree.test;

import bridge.tree.solution.TreeSolution03;

import java.util.Arrays;

/** 외부 테스트 라이브러리 없이 TreeSolution03을 검증한다. */
public final class TreeSolution03Test {

    private TreeSolution03Test() {
    }

    /*
     * 테스트 범위
     * - 두 노드 번호: 하한 1, 중간 940·941, 상한 1,000,000,000
     * - 관계: 같은 노드, 형제, 조상·자손, 서로 다른 깊이, 뿌리에서 만남
     * - 이동 횟수: 한쪽 0, 양쪽 같은 횟수, 양쪽 다른 횟수, 최대 29
     */
    public static void main(String[] args) {
        int total = 6;
        int passed = 0;

        passed += runCase(1, "형제 노드", () -> assertArrayEquals(
                new int[]{5, 1, 1},
                TreeSolution03.solve(10, 11)
        ));
        passed += runCase(2, "한쪽이 다른 쪽의 조상", () -> assertArrayEquals(
                new int[]{2, 0, 2},
                TreeSolution03.solve(2, 9)
        ));
        passed += runCase(3, "번호 하한에서 같은 노드", () -> assertArrayEquals(
                new int[]{1, 0, 0},
                TreeSolution03.solve(1, 1)
        ));
        passed += runCase(4, "중간값 940과 이웃 형제", () -> assertArrayEquals(
                new int[]{470, 1, 1},
                TreeSolution03.solve(940, 941)
        ));
        passed += runCase(5, "서로 다른 깊이에서 뿌리로 수렴", () -> assertArrayEquals(
                new int[]{1, 4, 3},
                TreeSolution03.solve(31, 8)
        ));
        passed += runCase(6, "번호 상한에서 뿌리까지 29번", () -> assertArrayEquals(
                new int[]{1, 0, 29},
                TreeSolution03.solve(1, 1_000_000_000)
        ));

        finish("TreeSolution03", passed, total);
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

    private static void assertArrayEquals(int[] expected, int[] actual) {
        if (!Arrays.equals(expected, actual)) {
            throw new AssertionError("expected=" + Arrays.toString(expected)
                    + ", actual=" + Arrays.toString(actual));
        }
    }
}

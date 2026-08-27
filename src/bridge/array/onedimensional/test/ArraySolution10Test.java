package bridge.array.onedimensional.test;

import bridge.array.onedimensional.solution.ArraySolution10;

import java.util.Arrays;

/** 외부 테스트 라이브러리 없이 ArraySolution10을 검증한다. */
public final class ArraySolution10Test {

    private ArraySolution10Test() {
    }

    /*
     * 매개변수 | 허용 범위
     * values | null이 아니며 길이는 0 이상 300 이하
     * values의 각 값 | -1,000 이상 1,000 이하
     * maxDifference | 0 이상 2,000 이하
     */
    public static void main(String[] args) {
        int total = 7;
        int passed = 0;

        passed += runCase(1, "비인접 위치 쌍도 확인", () -> verify(
                1, new int[]{0, 100, 1}, 1
        ));
        passed += runCase(2, "빈 배열", () -> verify(
                0, new int[]{}, 0
        ));
        passed += runCase(3, "원소 하나", () -> verify(
                0, new int[]{940}, 2_000
        ));
        passed += runCase(4, "같은 값의 서로 다른 위치 쌍", () -> verify(
                3, new int[]{0, 0, 0}, 0
        ));
        passed += runCase(5, "차이가 기준과 같은 쌍 포함", () -> verify(
                2, new int[]{-1, 1, 3}, 2
        ));
        passed += runCase(6, "값 하한·0·940·상한과 차이 상한", () -> verify(
                6, new int[]{-1_000, 0, 940, 1_000}, 2_000
        ));
        passed += runCase(7, "최대 길이에서 모든 위치 쌍", () -> {
            int[] values = new int[300];
            Arrays.fill(values, 940);
            verify(44_850, values, 0);
        });

        finish("ArraySolution10", passed, total);
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

    private static void verify(int expected, int[] input, int maxDifference) {
        int[] original = input.clone();
        int actual = ArraySolution10.solve(input, maxDifference);

        if (expected != actual) {
            throw new AssertionError("input=" + Arrays.toString(input)
                    + ", maxDifference=" + maxDifference
                    + ", expected=" + expected
                    + ", actual=" + actual);
        }
        if (!Arrays.equals(original, input)) {
            throw new AssertionError("원본이 변경되었습니다. before=" + Arrays.toString(original)
                    + ", after=" + Arrays.toString(input));
        }
    }
}

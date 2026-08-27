package bridge.array.onedimensional.test;

import bridge.array.onedimensional.solution.ArraySolution09;

import java.util.Arrays;

/** 외부 테스트 라이브러리 없이 ArraySolution09를 검증한다. */
public final class ArraySolution09Test {

    private ArraySolution09Test() {
    }

    /*
     * 매개변수 | 허용 범위
     * values | null이 아니며 길이는 0 이상 1,000 이하
     * values의 각 값 | -1,000 이상 1,000 이하
     * 결과·원본 | 처음 등장한 순서를 유지하고 원본 values를 바꾸지 않음
     */
    public static void main(String[] args) {
        int total = 6;
        int passed = 0;

        passed += runCase(1, "떨어져 있는 중복과 원래 순서", () -> verify(
                new int[]{3, 1, 2}, new int[]{3, 1, 3, 2, 1}
        ));
        passed += runCase(2, "빈 배열도 새 배열로 반환", () -> verify(
                new int[]{}, new int[]{}
        ));
        passed += runCase(3, "원소 하나도 새 배열로 반환", () -> verify(
                new int[]{940}, new int[]{940}
        ));
        passed += runCase(4, "하한·0·940·상한과 중복", () -> verify(
                new int[]{1_000, 0, 940, -1_000},
                new int[]{1_000, 0, 940, -1_000, 940, 0}
        ));
        passed += runCase(5, "연속 중복과 떨어진 중복", () -> verify(
                new int[]{4, 2, 7}, new int[]{4, 4, 2, 4, 7, 2}
        ));
        passed += runCase(6, "최대 길이에서 처음 등장한 순서", () -> {
            int[] values = new int[1_000];
            int[] cycle = {940, -1_000, 0, 1_000};
            for (int i = 0; i < values.length; i++) {
                values[i] = cycle[i % cycle.length];
            }
            verify(cycle, values);
        });

        finish("ArraySolution09", passed, total);
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

    private static void verify(int[] expected, int[] input) {
        int[] original = input.clone();
        int[] actual = ArraySolution09.solve(input);

        if (!Arrays.equals(expected, actual)) {
            throw new AssertionError("input=" + Arrays.toString(input)
                    + ", expected=" + Arrays.toString(expected)
                    + ", actual=" + Arrays.toString(actual));
        }
        if (!Arrays.equals(original, input)) {
            throw new AssertionError("원본이 변경되었습니다. before=" + Arrays.toString(original)
                    + ", after=" + Arrays.toString(input));
        }
        if (input == actual) {
            throw new AssertionError("결과가 원본과 같은 배열을 가리킵니다.");
        }
    }
}

package bridge.array.onedimensional.test;

import bridge.array.onedimensional.solution.ArraySolution08;

import java.util.Arrays;

/** 외부 테스트 라이브러리 없이 ArraySolution08을 검증한다. */
public final class ArraySolution08Test {

    private ArraySolution08Test() {
    }

    /*
     * 매개변수 | 허용 범위
     * values | null이 아니며 길이는 1 이상 999 이하인 홀수
     * values의 각 값 | -1,000 이상 1,000 이하
     * 원본 보존 | solve() 실행 뒤 values의 내용이 같아야 함
     */
    public static void main(String[] args) {
        int total = 6;
        int passed = 0;

        passed += runCase(1, "섞인 값의 중앙값과 원본 보존", () -> verify(
                5, new int[]{8, 2, 5, 1, 9}
        ));
        passed += runCase(2, "원소 하나와 값의 하한", () -> verify(
                -1_000, new int[]{-1_000}
        ));
        passed += runCase(3, "0·940·상한을 포함한 중앙값", () -> verify(
                940, new int[]{1_000, 940, 0}
        ));
        passed += runCase(4, "중복 값이 있는 배열", () -> verify(
                3, new int[]{9, 3, 3, 1, 7}
        ));
        passed += runCase(5, "정렬 전 가운데 칸이 정답이 아님", () -> verify(
                0, new int[]{940, -1_000, 1_000, 0, -3}
        ));
        passed += runCase(6, "최대 길이와 일반 중간값 940", () -> {
            int[] values = new int[999];
            Arrays.fill(values, 940);
            values[0] = -1_000;
            values[998] = 1_000;
            verify(940, values);
        });

        finish("ArraySolution08", passed, total);
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

    private static void verify(int expected, int[] input) {
        int[] original = input.clone();
        int actual = ArraySolution08.solve(input);

        if (expected != actual) {
            throw new AssertionError("input=" + Arrays.toString(input)
                    + ", expected=" + expected
                    + ", actual=" + actual);
        }
        if (!Arrays.equals(original, input)) {
            throw new AssertionError("원본이 변경되었습니다. before=" + Arrays.toString(original)
                    + ", after=" + Arrays.toString(input));
        }
    }
}

package bridge.set.test;

import bridge.set.solution.SetSolution01;

import java.util.Arrays;

/** 외부 테스트 라이브러리 없이 SetSolution01을 검증한다. */
public final class SetSolution01Test {

    private SetSolution01Test() {
    }

    /*
     * 검증 범위
     * 항목       | 제약                              | 실제 확인값
     * badgeCodes | 길이 0~100,000, 값 -1,000,000~1,000,000 | 0·1·100,000개, 하한·0·940·상한
     * slotLimit  | 0~100,000                         | 0·2·5·10·940·100,000
     * 대표 오답  | 배열 길이를 고유 개수로 사용, 슬롯 상한 무시, 원본 변경
     */
    public static void main(String[] args) {
        int total = 7;
        int passed = 0;

        passed += runCase(1, "중복 배지와 남는 칸 및 원본 보존", () -> {
            int[] badgeCodes = {10, 10, 20, 30};
            int[] before = badgeCodes.clone();
            int[] actual = SetSolution01.solve(badgeCodes, 5);
            assertArrayEquals(new int[]{3, 2}, actual);
            assertArrayEquals(before, badgeCodes);
            assertDifferentReference(badgeCodes, actual);
        });
        passed += runCase(2, "배지 코드 하한·0·940·상한", () -> assertArrayEquals(
                new int[]{4, 6},
                SetSolution01.solve(
                        new int[]{-1_000_000, 0, 940, 1_000_000, -1_000_000},
                        10
                )
        ));
        passed += runCase(3, "보드 칸 수가 0", () -> assertArrayEquals(
                new int[]{0, 0},
                SetSolution01.solve(new int[]{1, 2, 3}, 0)
        ));
        passed += runCase(4, "배지가 없고 칸 수가 940", () -> assertArrayEquals(
                new int[]{0, 940},
                SetSolution01.solve(new int[]{}, 940)
        ));
        passed += runCase(5, "고유 배지가 칸보다 많음", () -> assertArrayEquals(
                new int[]{2, 0},
                SetSolution01.solve(new int[]{1, 2, 3, 4, 5}, 2)
        ));
        passed += runCase(6, "길이 1과 슬롯 상한", () -> {
            int[] badgeCodes = {940};
            int[] before = badgeCodes.clone();

            int[] actual = SetSolution01.solve(badgeCodes, 100_000);

            assertArrayEquals(new int[]{1, 99_999}, actual);
            assertArrayEquals(before, badgeCodes);
            assertDifferentReference(badgeCodes, actual);
        });
        passed += runCase(7, "최대 길이에서 1,000가지 중 940칸 채우기", () -> {
            int[] badgeCodes = new int[100_000];
            for (int i = 0; i < badgeCodes.length; i++) {
                badgeCodes[i] = i % 1_000;
            }
            int[] before = badgeCodes.clone();

            assertArrayEquals(new int[]{940, 0}, SetSolution01.solve(badgeCodes, 940));
            assertArrayEquals(before, badgeCodes);
        });

        finish("SetSolution01", passed, total);
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

    private static void assertDifferentReference(int[] original, int[] actual) {
        if (original == actual) {
            throw new AssertionError("결과가 입력 배열과 같은 참조입니다.");
        }
    }
}

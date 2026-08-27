package bridge.set.test;

import bridge.set.solution.SetSolution02;

import java.util.Arrays;

/** 외부 테스트 라이브러리 없이 SetSolution02를 검증한다. */
public final class SetSolution02Test {

    private SetSolution02Test() {
    }

    /*
     * 검증 범위
     * 항목       | 제약                                  | 실제 확인값
     * checkCodes | 길이 0~100,000, 값 -1,000,000~1,000,000 | 0·1·942·100,000개, 하한·0·940·상한
     * inspectors | 1~1,000                               | 1·2·3·940·1,000
     * allowedGap | 0~2,000,000                           | 0·1·2·5·10·940·2,000,000
     * 대표 오답  | 중복 누락, 간격 경계 오류, 마지막 오류 반환, 담당 번호 계산 오류, 원본 변경
     */
    public static void main(String[] args) {
        int total = 9;
        int passed = 0;

        passed += runCase(1, "모든 기록이 올바르고 원본 보존", () -> {
            int[] checkCodes = {10, 12, 14, 16};
            int[] before = checkCodes.clone();
            assertArrayEquals(new int[]{}, SetSolution02.solve(checkCodes, 3, 2));
            assertArrayEquals(before, checkCodes);
        });
        passed += runCase(2, "빈 기록", () -> assertArrayEquals(
                new int[]{},
                SetSolution02.solve(new int[]{}, 1, 0)
        ));
        passed += runCase(3, "기록 하나와 점검자 상한", () -> assertArrayEquals(
                new int[]{},
                SetSolution02.solve(new int[]{940}, 1_000, 0)
        ));
        passed += runCase(4, "간격은 맞지만 처음 반복된 코드", () -> assertArrayEquals(
                new int[]{3, 5},
                SetSolution02.solve(new int[]{5, 6, 5, 100}, 3, 10)
        ));
        passed += runCase(5, "코드 940에서 처음 간격 초과", () -> assertArrayEquals(
                new int[]{2, 940},
                SetSolution02.solve(new int[]{0, 1, 2, 3, 940}, 3, 10)
        ));
        passed += runCase(6, "더 늦은 중복보다 앞선 간격 오류 반환", () -> assertArrayEquals(
                new int[]{2, 5},
                SetSolution02.solve(new int[]{1, 5, 1}, 2, 2)
        ));
        passed += runCase(7, "점검자 940명이 한 바퀴 돈 뒤 담당 번호", () -> {
            int[] checkCodes = new int[942];
            for (int i = 0; i <= 940; i++) {
                checkCodes[i] = i;
            }
            checkCodes[941] = 0;
            int[] before = checkCodes.clone();

            assertArrayEquals(new int[]{2, 0}, SetSolution02.solve(checkCodes, 940, 940));
            assertArrayEquals(before, checkCodes);
        });
        passed += runCase(8, "코드 하한과 상한의 허용 간격 경계", () -> assertArrayEquals(
                new int[]{},
                SetSolution02.solve(new int[]{-1_000_000, 1_000_000}, 1_000, 2_000_000)
        ));
        passed += runCase(9, "최대 길이의 고유 연속 기록", () -> {
            int[] checkCodes = new int[100_000];
            for (int i = 0; i < checkCodes.length; i++) {
                checkCodes[i] = i - 50_000;
            }
            int[] before = checkCodes.clone();

            assertArrayEquals(new int[]{}, SetSolution02.solve(checkCodes, 1_000, 1));
            assertArrayEquals(before, checkCodes);
        });

        finish("SetSolution02", passed, total);
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

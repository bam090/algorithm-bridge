package bridge.backtracking.test;

import bridge.backtracking.solution.BacktrackingSolution03;

import java.util.Arrays;

/** 외부 테스트 라이브러리 없이 BacktrackingSolution03을 검증한다. */
public final class BacktrackingSolution03Test {

    private static int passed;
    private static int failed;

    private BacktrackingSolution03Test() {
    }

    /*
     * 매개변수 | 허용 범위 | 실제 확인 값
     * 장치 수 | 1 이상 8 이하 | 1, 2, 3, 4, 8
     * initialEnergy | 0 이상 1,000 이하 | 0, 4, 5, 940, 1,000
     * requiredEnergy[i] | 0 이상 1,000 이하 | 0, 2, 4, 6, 940, 1,000
     * signedEnergyChange[i] | -1,000 이상 1,000 이하 | -1,000, -940, -3, 0, 4, 940, 1,000
     * 추가 계약 | 모든 장치 한 번, 사전식 첫 1기반 순서, 원본 보존 | 충전·소모, 불가능, 선택 취소, 최대 순열
     */
    public static void main(String[] args) {
        check(
                "낮은 번호에서 충전 장치로 이어지는 첫 순서",
                5,
                new int[]{4, 6, 2},
                new int[]{-3, 4, 4},
                new int[]{1, 3, 2}
        );
        check(
                "장치 하나와 에너지 중간값",
                940,
                new int[]{940},
                new int[]{0},
                new int[]{1}
        );
        check(
                "변화량과 필요 에너지의 하한·중간값·상한",
                0,
                new int[]{0, 1_000, 0, 940},
                new int[]{1_000, -1_000, 940, -940},
                new int[]{1, 2, 3, 4}
        );
        check(
                "필요 에너지를 만족할 수 없어 불가능",
                0,
                new int[]{1},
                new int[]{1_000},
                new int[0]
        );
        check(
                "변화 뒤 에너지가 음수라 불가능",
                0,
                new int[]{0},
                new int[]{-1},
                new int[0]
        );
        check(
                "첫 후보 실패 뒤 다음 시작 후보로 성공",
                4,
                new int[]{0, 4},
                new int[]{-4, 4},
                new int[]{2, 1}
        );
        runCase("최대 장치 수의 사전식 첫 순서", BacktrackingSolution03Test::testMaximumDeviceCount);

        finish();
    }

    private static void check(
            String name,
            int initialEnergy,
            int[] requiredEnergy,
            int[] signedEnergyChange,
            int[] expected
    ) {
        runCase(name, () -> {
            int[] originalRequired = requiredEnergy.clone();
            int[] originalChange = signedEnergyChange.clone();
            int[] actual = BacktrackingSolution03.solve(
                    initialEnergy,
                    requiredEnergy,
                    signedEnergyChange
            );

            assertArrayEquals(expected, actual);
            assertArrayEquals(originalRequired, requiredEnergy);
            assertArrayEquals(originalChange, signedEnergyChange);
            assertTrue(
                    actual != requiredEnergy && actual != signedEnergyChange,
                    "결과는 입력과 다른 배열이어야 한다."
            );
        });
    }

    private static void testMaximumDeviceCount() {
        int length = 8;
        int[] requiredEnergy = new int[length];
        int[] signedEnergyChange = new int[length];
        int[] actual = BacktrackingSolution03.solve(1_000, requiredEnergy, signedEnergyChange);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6, 7, 8}, actual);
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
        System.out.println("[RESULT] BacktrackingSolution03: " + passed + "/" + (passed + failed) + " 통과");
        if (failed > 0) {
            throw new AssertionError("BacktrackingSolution03 실패: " + failed + "건");
        }
    }

    private static void assertArrayEquals(int[] expected, int[] actual) {
        if (!Arrays.equals(expected, actual)) {
            throw new AssertionError(
                    "expected=" + Arrays.toString(expected) + ", actual=" + Arrays.toString(actual)
            );
        }
    }

    private static void assertTrue(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError(message);
        }
    }
}

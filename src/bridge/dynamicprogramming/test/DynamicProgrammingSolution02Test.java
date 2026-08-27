package bridge.dynamicprogramming.test;

import bridge.dynamicprogramming.solution.DynamicProgrammingSolution02;

import java.util.Arrays;

/** 외부 테스트 라이브러리 없이 DynamicProgrammingSolution02를 검증한다. */
public final class DynamicProgrammingSolution02Test {

    private static int passed;
    private static int failed;

    private DynamicProgrammingSolution02Test() {
    }

    /*
     * 매개변수 | 허용 범위 | 실제 확인 값
     * 사진 수 | 0 이상 1,000 이하 | 0, 1, 3, 4, 940, 1,000
     * 한 장 비용 | 0 이상 1,000,000,000 이하 | 0, 7, 940, 1,000,000,000
     * 묶음 비용 | 0 이상 1,000,000,000 이하 | 0, 1, 6, 1,000, 1,000,000,000
     * 결과 자료형 | 최대 1,000장 비용의 합 | 500,000,000,000L
     * 추가 계약 | 빈 입력, 마지막 행동 두 경우, 겹치는 묶음, 원본 보존 | 각각 실행
     *
     * 대표 오답
     * - 사진이 0장 또는 1장일 때 존재하지 않는 이전 상태를 읽는다.
     * - 묶음 비용을 두 장의 한 장 비용에 추가한다.
     * - 가장 싼 묶음을 먼저 골라 서로 겹치는 선택을 한다.
     * - 비용 합을 int로 저장하거나 입력 배열을 정렬한다.
     */
    public static void main(String[] args) {
        check("빈 사진 목록", new int[]{}, new int[]{}, 0L);
        check("사진 한 장과 비용 상한", new int[]{1_000_000_000}, new int[]{}, 1_000_000_000L);
        check(
                "마지막 한 장과 마지막 묶음 비교",
                new int[]{6, 5, 7, 4},
                new int[]{8, 10, 6},
                14L
        );
        check(
                "서로 겹치는 싼 묶음을 탐욕으로 고르면 실패",
                new int[]{8, 100, 8, 100},
                new int[]{9, 1, 9},
                18L
        );
        check(
                "비용 0·940·상한",
                new int[]{0, 940, 1_000_000_000},
                new int[]{940, 0},
                0L
        );
        runCase("사진 수 940과 일반 중간 비용", () -> {
            int[] singleCosts = new int[940];
            int[] pairCosts = new int[939];
            Arrays.fill(singleCosts, 940);
            Arrays.fill(pairCosts, 1_000);
            assertEquals(470_000L, DynamicProgrammingSolution02.solve(singleCosts, pairCosts));
        });
        runCase("사진 수 상한과 long 결과", () -> {
            int[] singleCosts = new int[1_000];
            int[] pairCosts = new int[999];
            Arrays.fill(singleCosts, 1_000_000_000);
            Arrays.fill(pairCosts, 1_000_000_000);
            assertEquals(
                    500_000_000_000L,
                    DynamicProgrammingSolution02.solve(singleCosts, pairCosts)
            );
        });

        finish();
    }

    private static void check(String name, int[] singleCosts, int[] pairCosts, long expected) {
        runCase(name, () -> {
            int[] singleBefore = singleCosts.clone();
            int[] pairBefore = pairCosts.clone();
            long actual = DynamicProgrammingSolution02.solve(singleCosts, pairCosts);

            assertEquals(expected, actual);
            assertArrayEquals(singleBefore, singleCosts);
            assertArrayEquals(pairBefore, pairCosts);
        });
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
        System.out.println("[RESULT] DynamicProgrammingSolution02: "
                + passed + "/" + (passed + failed) + " 통과");
        if (failed > 0) {
            throw new AssertionError("DynamicProgrammingSolution02 실패: " + failed + "건");
        }
    }

    private static void assertEquals(long expected, long actual) {
        if (expected != actual) {
            throw new AssertionError("expected=" + expected + ", actual=" + actual);
        }
    }

    private static void assertArrayEquals(int[] expected, int[] actual) {
        if (!Arrays.equals(expected, actual)) {
            throw new AssertionError(
                    "expected=" + Arrays.toString(expected) + ", actual=" + Arrays.toString(actual)
            );
        }
    }
}

package bridge.dynamicprogramming.test;

import bridge.dynamicprogramming.solution.DynamicProgrammingSolution01;

import java.util.Arrays;

/** 외부 테스트 라이브러리 없이 DynamicProgrammingSolution01을 검증한다. */
public final class DynamicProgrammingSolution01Test {

    private static final int MODULO = 10_007;
    private static int passed;
    private static int failed;

    private DynamicProgrammingSolution01Test() {
    }

    /*
     * 매개변수 | 허용 범위 | 실제 확인 값
     * lastDay | 0 이상 100,000 이하 | 0, 1, 3, 940, 100,000
     * 날짜별 값 | 0 이상 10,006 이하 | 나머지 적용 전후의 여러 값
     * 결과 길이 | lastDay + 1 | 1, 2, 4, 941, 100,001
     * 큰 곱셈 | day × day를 long으로 계산 | 100,000²
     *
     * 대표 오답
     * - 0일을 결과에서 빼거나 배열 길이를 lastDay로 만든다.
     * - 가장 작은 상태 1을 저장하지 않는다.
     * - day × day를 int로 계산해 큰 날짜에서 값이 넘친다.
     * - 단계마다 나머지를 구하지 않거나 마지막 값만 반환한다.
     */
    public static void main(String[] args) {
        check("날짜 하한 0", 0);
        check("첫 점화식 계산", 1);
        check("문제의 일반 예시", 3);
        check("일반 중간값 940", 940);
        check("날짜 상한과 long 곱셈", 100_000);

        finish();
    }

    private static void check(String name, int lastDay) {
        runCase(name, () -> assertArrayEquals(
                expectedBySquareSum(lastDay),
                DynamicProgrammingSolution01.solve(lastDay)
        ));
    }

    private static int[] expectedBySquareSum(int lastDay) {
        int[] expected = new int[lastDay + 1];
        for (int day = 0; day <= lastDay; day++) {
            long squareSum = (long) day * (day + 1) * (2L * day + 1) / 6;
            expected[day] = (int) ((1 + squareSum) % MODULO);
        }
        return expected;
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
        System.out.println("[RESULT] DynamicProgrammingSolution01: "
                + passed + "/" + (passed + failed) + " 통과");
        if (failed > 0) {
            throw new AssertionError("DynamicProgrammingSolution01 실패: " + failed + "건");
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

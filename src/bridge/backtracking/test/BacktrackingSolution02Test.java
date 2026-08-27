package bridge.backtracking.test;

import bridge.backtracking.solution.BacktrackingSolution02;

import java.util.Arrays;

/** 외부 테스트 라이브러리 없이 BacktrackingSolution02를 검증한다. */
public final class BacktrackingSolution02Test {

    private static int passed;
    private static int failed;

    private BacktrackingSolution02Test() {
    }

    /*
     * 매개변수 | 허용 범위 | 실제 확인 값
     * factorCards.length | 0 이상 15 이하 | 0, 1, 4, 5, 15
     * factorCards[i] | 2 이상 1,000 이하 | 2, 940, 1,000
     * target | 2 이상 1,000,000 이하 | 2, 12, 64, 940, 1,000,000
     * 추가 계약 | 한 장 한 번, 순서 중복 제외, 원본 보존 | 세 조합, 시작 위치, 초과 가지치기
     */
    public static void main(String[] args) {
        check("서로 다른 세 조합", new int[]{2, 3, 4, 6, 12}, 12, 3);
        check("빈 카드와 target 하한", new int[0], 2, 0);
        check("카드 한 장으로 target 하한", new int[]{2}, 2, 1);
        check("중간값 카드 한 장", new int[]{940, 2, 5}, 940, 1);
        check("순서만 다른 조합을 한 번만 계산", new int[]{2, 4, 8, 16}, 64, 2);
        check("factor와 target 상한에서 만들 수 없음", new int[]{1_000, 2, 4, 8, 16}, 1_000_000, 0);
        check(
                "최대 카드 수와 여러 초과 가지",
                new int[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47},
                30,
                1
        );

        finish();
    }

    private static void check(String name, int[] factorCards, int target, int expected) {
        try {
            int[] original = factorCards.clone();
            int actual = BacktrackingSolution02.solve(factorCards, target);
            if (actual != expected) {
                throw new AssertionError("expected=" + expected + ", actual=" + actual);
            }
            if (!Arrays.equals(original, factorCards)) {
                throw new AssertionError("입력 카드 배열이 바뀌었다.");
            }
            passed++;
            System.out.println("[PASS] " + name);
        } catch (AssertionError | RuntimeException error) {
            failed++;
            System.out.println("[FAIL] " + name + ": " + error.getMessage());
        }
    }

    private static void finish() {
        System.out.println("[RESULT] BacktrackingSolution02: " + passed + "/" + (passed + failed) + " 통과");
        if (failed > 0) {
            throw new AssertionError("BacktrackingSolution02 실패: " + failed + "건");
        }
    }
}

package bridge.queue.test;

import bridge.queue.solution.QueueSolution02;

import java.util.Arrays;

/** 외부 테스트 라이브러리 없이 QueueSolution02를 검증한다. */
public final class QueueSolution02Test {

    private QueueSolution02Test() {
    }

    /*
     * 검증 범위
     * - scores 길이 0·1·1,000, 점수 -1,000·0·940·1,000
     * - rotations 길이 0·1·1,000, 회전 횟수 0·940·1,000
     * - removalLimit -1,000·0·2·4·940·1,000
     * - 제거로 현재 큐 길이가 바뀐 뒤의 회전과 두 입력 배열의 원본 보존
     */

    public static void main(String[] args) {
        int total = 8;
        int passed = 0;

        passed += runCase(1, "회전 뒤 조건부 제거와 두 원본 보존", () -> {
            int[] scores = {4, 9, 2, 7};
            int[] rotations = {2, 1, 0};
            int[] scoresBefore = scores.clone();
            int[] rotationsBefore = rotations.clone();

            assertArrayEquals(new int[]{9, 7}, QueueSolution02.solve(scores, rotations, 4));
            assertArrayEquals(scoresBefore, scores);
            assertArrayEquals(rotationsBefore, rotations);
        });
        passed += runCase(2, "명령이 없으면 같은 순서의 새 배열", () -> {
            int[] scores = {3, 1, 2};
            int[] before = scores.clone();
            int[] actual = QueueSolution02.solve(scores, new int[]{}, 0);
            assertArrayEquals(new int[]{3, 1, 2}, actual);
            assertArrayEquals(before, scores);
            assertDifferentReference(scores, actual);
        });
        passed += runCase(3, "빈 큐는 남은 명령 건너뛰기", () -> assertArrayEquals(
                new int[]{},
                QueueSolution02.solve(new int[]{}, new int[]{0, 940, 1_000}, 0)
        ));
        passed += runCase(4, "기준과 같은 값까지 연속 제거", () -> assertArrayEquals(
                new int[]{1_000},
                QueueSolution02.solve(
                        new int[]{-1_000, 0, 940, 1_000},
                        new int[]{0, 0, 0, 0},
                        940
                )
        ));
        passed += runCase(5, "첫 제거 뒤 줄어든 현재 길이로 940회 회전", () -> {
            int[] scores = {1, 2, 3, 4};
            int[] rotations = {0, 940};
            int[] scoresBefore = scores.clone();
            int[] rotationsBefore = rotations.clone();

            assertArrayEquals(new int[]{3, 4, 2}, QueueSolution02.solve(scores, rotations, 2));
            assertArrayEquals(scoresBefore, scores);
            assertArrayEquals(rotationsBefore, rotations);
        });
        passed += runCase(6, "제거 기준 상한 1,000과 같은 값 제거", () -> assertArrayEquals(
                new int[]{},
                QueueSolution02.solve(new int[]{1_000}, new int[]{0}, 1_000)
        ));
        passed += runCase(7, "기준보다 큰 앞 값은 남기고 다음 명령 처리", () -> assertArrayEquals(
                new int[]{9, 5},
                QueueSolution02.solve(new int[]{5, 1, 9}, new int[]{0, 1}, 4)
        ));
        passed += runCase(8, "최대 길이와 명령 수에서 모두 제거", () -> {
            int[] scores = new int[1_000];
            Arrays.fill(scores, -1_000);
            int[] rotations = new int[1_000];
            int[] before = scores.clone();

            assertArrayEquals(new int[]{}, QueueSolution02.solve(scores, rotations, -1_000));
            assertArrayEquals(before, scores);
        });

        finish("QueueSolution02", passed, total);
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
            throw new AssertionError("결과가 원본과 같은 배열을 가리킵니다.");
        }
    }
}

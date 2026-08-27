package bridge.set.test;

import bridge.set.solution.SetSolution04;

import java.util.Arrays;

/** 외부 테스트 라이브러리 없이 SetSolution04를 검증한다. */
public final class SetSolution04Test {

    private SetSolution04Test() {
    }

    /*
     * 검증 범위
     * 항목        | 제약                    | 실제 확인값
     * deviceCount | 0~100,000               | 0·2·4·6·1,000·100,000
     * 요청 수     | 0~200,000               | 0·1·3·4·5·7·200,000개
     * 장비 번호   | 0~deviceCount-1         | 0·940·999·99,999
     * 요청 종류   | LINK 또는 AUDIT         | 선행·반복 LINK, 성공·실패·자기 AUDIT
     * 대표 오답   | LINK 선처리, 최종 대표 누락, 요청 번호 오프바이원, 반복 연결 손상, 원본 변경
     */
    public static void main(String[] args) {
        int total = 9;
        int passed = 0;

        passed += runCase(1, "요청 시점에 따른 실패 1·5와 원본 보존", () -> {
            String[] actions = {"AUDIT", "LINK", "LINK", "AUDIT", "AUDIT", "LINK", "AUDIT"};
            int[] firstIds = {0, 0, 1, 0, 0, 2, 0};
            int[] secondIds = {1, 1, 2, 2, 5, 5, 5};
            String[] actionsBefore = actions.clone();
            int[] firstBefore = firstIds.clone();
            int[] secondBefore = secondIds.clone();

            assertArrayEquals(new int[]{1, 5}, SetSolution04.solve(
                    6,
                    actions,
                    firstIds,
                    secondIds
            ));
            assertStringArrayEquals(actionsBefore, actions);
            assertArrayEquals(firstBefore, firstIds);
            assertArrayEquals(secondBefore, secondIds);
        });
        passed += runCase(2, "장비와 요청이 모두 없음", () -> assertArrayEquals(
                new int[]{},
                SetSolution04.solve(0, new String[]{}, new int[]{}, new int[]{})
        ));
        passed += runCase(3, "AUDIT 요청 한 건의 실패 번호 1", () -> assertArrayEquals(
                new int[]{1},
                SetSolution04.solve(
                        2,
                        new String[]{"AUDIT"},
                        new int[]{0},
                        new int[]{1}
                )
        ));
        passed += runCase(4, "바로 위 부모가 달라도 최종 대표가 같은 연결", () -> assertArrayEquals(
                new int[]{},
                SetSolution04.solve(
                        4,
                        new String[]{"LINK", "LINK", "LINK", "AUDIT"},
                        new int[]{0, 2, 1, 0},
                        new int[]{1, 3, 3, 3}
                )
        ));
        passed += runCase(5, "전체 요청 번호 4에서 실패", () -> assertArrayEquals(
                new int[]{4},
                SetSolution04.solve(
                        4,
                        new String[]{"LINK", "AUDIT", "LINK", "AUDIT"},
                        new int[]{0, 0, 2, 0},
                        new int[]{1, 1, 3, 3}
                )
        ));
        passed += runCase(6, "자기 연결·반복 연결 뒤 감사 성공", () -> assertArrayEquals(
                new int[]{},
                SetSolution04.solve(
                        2,
                        new String[]{"LINK", "LINK", "LINK", "AUDIT", "AUDIT"},
                        new int[]{0, 0, 1, 1, 0},
                        new int[]{0, 1, 0, 1, 1}
                )
        ));
        passed += runCase(7, "장비 0·940 연결 뒤 999 감사 실패", () -> {
            String[] actions = {"LINK", "AUDIT", "AUDIT"};
            int[] firstIds = {0, 0, 940};
            int[] secondIds = {940, 940, 999};
            String[] actionsBefore = actions.clone();
            int[] firstBefore = firstIds.clone();
            int[] secondBefore = secondIds.clone();

            assertArrayEquals(new int[]{3}, SetSolution04.solve(
                    1_000,
                    actions,
                    firstIds,
                    secondIds
            ));
            assertStringArrayEquals(actionsBefore, actions);
            assertArrayEquals(firstBefore, firstIds);
            assertArrayEquals(secondBefore, secondIds);
        });
        passed += runCase(8, "여러 실패의 전체 요청 번호만 반환", () -> assertArrayEquals(
                new int[]{1, 3},
                SetSolution04.solve(
                        4,
                        new String[]{"AUDIT", "LINK", "AUDIT", "AUDIT"},
                        new int[]{0, 0, 2, 0},
                        new int[]{1, 1, 3, 1}
                )
        ));
        passed += runCase(9, "최대 장비와 요청 수에서 압축된 연결 감사", () -> {
            int deviceCount = 100_000;
            int requestCount = 200_000;
            int linkCount = deviceCount - 1;
            String[] actions = new String[requestCount];
            int[] firstIds = new int[requestCount];
            int[] secondIds = new int[requestCount];

            for (int i = 0; i < linkCount; i++) {
                actions[i] = "LINK";
                firstIds[i] = i;
                secondIds[i] = i + 1;
            }
            for (int i = linkCount; i < requestCount; i++) {
                actions[i] = "AUDIT";
                firstIds[i] = 0;
                secondIds[i] = (i - linkCount) % deviceCount;
            }

            assertArrayEquals(new int[]{}, SetSolution04.solve(
                    deviceCount,
                    actions,
                    firstIds,
                    secondIds
            ));
        });

        finish("SetSolution04", passed, total);
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

    private static void assertStringArrayEquals(String[] expected, String[] actual) {
        if (!Arrays.equals(expected, actual)) {
            throw new AssertionError("expected=" + Arrays.toString(expected)
                    + ", actual=" + Arrays.toString(actual));
        }
    }
}

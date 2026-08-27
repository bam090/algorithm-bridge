package bridge.greedy.test;

import bridge.greedy.solution.GreedySolution05;

import java.util.Arrays;

/** 외부 테스트 라이브러리 없이 GreedySolution05를 검증한다. */
public final class GreedySolution05Test {

    private GreedySolution05Test() {
    }

    /*
     * 범위표
     * - sourceLabels 길이: 0, 1, 940을 포함한 일반 길이, 최대 100,000
     * - targetRecords: 0, 940, sourceLabels.length 상한
     * - sourceLimit: 0, 일반 중간값, sourceLabels.length 상한
     * - 그룹: 한 종류, 모두 동점, 크기가 서로 다른 여러 종류
     * - 대표 오답: Set 크기만 사용, 작은 그룹 우선, 동점 이름 누락, 목표 뒤 계속 선택, 실패 부분 결과, 원본 정렬
     */
    public static void main(String[] args) {
        int total = 10;
        int passed = 0;

        passed += runCase(1, "큰 출처 묶음 우선과 원본 보존", () -> {
            String[] input = {"api", "ui", "api", "db", "api", "ui"};
            String[] before = input.clone();
            assertArrayEquals(new String[]{"api", "ui"}, GreedySolution05.solve(input, 4, 2));
            assertArrayEquals(before, input);
        });
        passed += runCase(2, "목표 0은 출처를 고르지 않음", () -> assertArrayEquals(
                new String[]{},
                GreedySolution05.solve(new String[]{"api"}, 0, 0)
        ));
        passed += runCase(3, "빈 기록과 목표 0", () -> assertArrayEquals(
                new String[]{},
                GreedySolution05.solve(new String[]{}, 0, 0)
        ));
        passed += runCase(4, "출처 한도 0이면 양수 목표 실패", () -> assertArrayEquals(
                new String[]{},
                GreedySolution05.solve(new String[]{"api"}, 1, 0)
        ));
        passed += runCase(5, "출처 수 제한에서 목표 도달 불가", () -> assertArrayEquals(
                new String[]{},
                GreedySolution05.solve(
                        new String[]{"alpha", "alpha", "beta", "beta", "gamma", "gamma"},
                        5,
                        2
                )
        ));
        passed += runCase(6, "기록 수 동점은 이름 사전순", () -> assertArrayEquals(
                new String[]{"alpha", "beta"},
                GreedySolution05.solve(
                        new String[]{"beta", "alpha", "gamma", "beta", "alpha", "gamma"},
                        4,
                        2
                )
        ));
        passed += runCase(7, "한 출처의 940개 기록", () -> {
            String[] input = new String[940];
            Arrays.fill(input, "abcdefghijklmnopqrst");
            assertArrayEquals(
                    new String[]{"abcdefghijklmnopqrst"},
                    GreedySolution05.solve(input, 940, 1)
            );
        });
        passed += runCase(8, "목표 도달 즉시 종료", () -> assertArrayEquals(
                new String[]{"alpha"},
                GreedySolution05.solve(
                        new String[]{"alpha", "alpha", "alpha", "alpha", "beta", "beta", "beta", "gamma"},
                        4,
                        8
                )
        ));
        passed += runCase(9, "전체 목표에는 모든 출처가 필요", () -> assertArrayEquals(
                new String[]{"a", "b", "c", "d"},
                GreedySolution05.solve(
                        new String[]{"a", "a", "a", "a", "b", "b", "b", "c", "c", "d"},
                        10,
                        10
                )
        ));
        passed += runCase(10, "최대 100000개와 동점 출처", () -> {
            String[] input = new String[100_000];
            for (int i = 0; i < 50_000; i++) {
                input[i] = "beta";
                input[i + 50_000] = "alpha";
            }
            String[] before = input.clone();
            assertArrayEquals(
                    new String[]{"alpha", "beta"},
                    GreedySolution05.solve(input, 50_001, 2)
            );
            assertArrayEquals(before, input);
        });

        finish("GreedySolution05", passed, total);
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

    private static void assertArrayEquals(String[] expected, String[] actual) {
        if (!Arrays.equals(expected, actual)) {
            throw new AssertionError("expected=" + Arrays.toString(expected)
                    + ", actual=" + Arrays.toString(actual));
        }
    }
}

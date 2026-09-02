package bridge.greedy.test;

import bridge.greedy.solution.GreedySolution05;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public final class GreedySolution05Test {

    /*
     * 범위표
     * - sourceLabels 길이: 0, 1, 940을 포함한 일반 길이, 최대 100,000
     * - targetRecords: 0, 940, sourceLabels.length 상한
     * - sourceLimit: 0, 일반 중간값, sourceLabels.length 상한
     * - 그룹: 한 종류, 모두 동점, 크기가 서로 다른 여러 종류
     * - 대표 오답: Set 크기만 사용, 작은 그룹 우선, 동점 이름 누락, 목표 뒤 계속 선택, 실패 부분 결과, 원본 정렬
     */
    @DisplayName("큰 출처 묶음부터 목표 기록 수를 채운다")
    @ParameterizedTest(name = "{0}")
    @MethodSource("ordinaryCases")
    void testSelectedSources(
            String name,
            String[] sourceLabels,
            int targetRecords,
            int sourceLimit,
            String[] expected
    ) {
        check(name, sourceLabels, targetRecords, sourceLimit, expected);
    }

    private static Stream<Arguments> ordinaryCases() {
        return Stream.of(
                arguments("큰 출처 묶음 우선과 원본 보존",
                        new String[]{"api", "ui", "api", "db", "api", "ui"}, 4, 2,
                        new String[]{"api", "ui"}),
                arguments("목표 0은 출처를 고르지 않음",
                        new String[]{"api"}, 0, 0, new String[]{}),
                arguments("빈 기록과 목표 0", new String[]{}, 0, 0, new String[]{}),
                arguments("출처 한도 0이면 양수 목표 실패",
                        new String[]{"api"}, 1, 0, new String[]{}),
                arguments("출처 수 제한에서 목표 도달 불가",
                        new String[]{"alpha", "alpha", "beta", "beta", "gamma", "gamma"}, 5, 2,
                        new String[]{}),
                arguments("기록 수 동점은 이름 사전순",
                        new String[]{"beta", "alpha", "gamma", "beta", "alpha", "gamma"}, 4, 2,
                        new String[]{"alpha", "beta"}),
                arguments("목표 도달 즉시 종료",
                        new String[]{"alpha", "alpha", "alpha", "alpha", "beta", "beta", "beta", "gamma"},
                        4, 8, new String[]{"alpha"}),
                arguments("전체 목표에는 모든 출처가 필요",
                        new String[]{"a", "a", "a", "a", "b", "b", "b", "c", "c", "d"},
                        10, 10, new String[]{"a", "b", "c", "d"})
        );
    }

    @Test
    @DisplayName("한 출처의 940개 기록을 처리한다")
    void testOrdinaryMiddleSize() {
        String[] input = new String[940];
        Arrays.fill(input, "abcdefghijklmnopqrst");
        check("한 출처의 940개 기록", input, 940, 1,
                new String[]{"abcdefghijklmnopqrst"});
    }

    @Test
    @DisplayName("최대 100000개의 동점 출처를 이름순으로 선택한다")
    void testMaximumLabels() {
        String[] input = new String[100_000];
        for (int index = 0; index < 50_000; index++) {
            input[index] = "beta";
            input[index + 50_000] = "alpha";
        }
        check("최대 100000개와 동점 출처", input, 50_001, 2,
                new String[]{"alpha", "beta"});
    }

    private static void check(
            String name,
            String[] sourceLabels,
            int targetRecords,
            int sourceLimit,
            String[] expected
    ) {
        String[] before = sourceLabels.clone();
        String[] actual = GreedySolution05.solve(sourceLabels, targetRecords, sourceLimit);
        assertAll(name,
                () -> assertArrayEquals(expected, actual),
                () -> assertArrayEquals(before, sourceLabels, "입력 출처 배열을 보존해야 한다")
        );
    }
}

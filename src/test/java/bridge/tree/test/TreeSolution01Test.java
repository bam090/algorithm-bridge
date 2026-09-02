package bridge.tree.test;

import bridge.tree.solution.TreeSolution01;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("트리 01 - 자식 위치로 서브트리 합 구하기")
final class TreeSolution01Test {

    /*
     * 테스트 범위
     * - treeValues 길이: 하한 0, 원소 하나, 일반 길이, 상한 100,000
     * - 값: 하한 -1,000,000, 0, 중간 940, 상한 1,000,000
     * - 모양: 자식 둘, 마지막 부모의 자식 하나, 빈 트리
     * - 합: int 범위를 넘는 long 결과와 원본 보존
     */
    @Test
    @DisplayName("자식이 둘인 완전한 세 층과 원본 보존")
    void sumsCompleteThreeLevelsWithoutChangingInput() {
        int[] values = {10, 20, 30, 40, 50, 60, 70};
        int[] before = values.clone();

        long actual = TreeSolution01.solve(values);

        assertAll(
                () -> assertEquals(220L, actual),
                () -> assertArrayEquals(before, values)
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("basicCases")
    @DisplayName("길이·값·트리 모양 경계")
    void handlesBasicBoundaries(String name, int[] values, long expected) {
        assertEquals(expected, TreeSolution01.solve(values));
    }

    static Stream<Arguments> basicCases() {
        return Stream.of(
                Arguments.of("길이 하한인 빈 트리", new int[]{}, 0L),
                Arguments.of("원소 하나와 중간값 940", new int[]{940}, 940L),
                Arguments.of("마지막 부모의 자식이 하나", new int[]{10, 20, 30, 40, 50, 60}, 150L),
                Arguments.of(
                        "값 하한·0·중간·상한",
                        new int[]{0, 1, 940, -1_000_000, 1_000_000},
                        940L
                )
        );
    }

    @Test
    @DisplayName("최대 길이와 int 범위를 넘는 합")
    void usesLongForMaximumLengthSum() {
        int[] values = new int[100_000];
        Arrays.fill(values, 1_000_000);

        assertEquals(50_000_000_000L, TreeSolution01.solve(values));
    }
}

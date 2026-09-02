package bridge.dynamicprogramming.test;

import bridge.dynamicprogramming.solution.DynamicProgrammingSolution04;
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
import static org.junit.jupiter.params.provider.Arguments.arguments;

public final class DynamicProgrammingSolution04Test {

    /*
     * 매개변수 | 허용 범위 | 실제 확인 값
     * 단계 수 | 1 이상 1,000 이하 | 1, 2, 3, 1,000
     * 부품 수 | 1 이상 8 이하 | 1, 2, 3, 8
     * 단계별 점수 | 0 이상 1,000,000,000 이하 | 0, 1, 940, 1,000,000,000
     * 호환 관계 | false 또는 true | 방향 불일치, 자기 연결, 완전 단절, 모든 자기 연결
     * 결과 | 마지막 부품 번호 또는 -1 | -1, 0, 1, 2
     * 추가 계약 | 최고점 동점, long 합계, 원본 보존 | 각각 실행
     *
     * 대표 오답
     * - 단계별 전체 최고점 하나만 기억한다.
     * - 방향 있는 canFollow의 행과 열을 반대로 읽는다.
     * - 같은 부품의 연속 선택을 무조건 금지한다.
     * - 도달 불가를 점수 0으로 취급한다.
     * - 점수 합을 int로 저장하거나 동점에서 큰 번호를 반환한다.
     */
    @DisplayName("호환 관계에 맞는 마지막 부품을 선택한다")
    @ParameterizedTest(name = "{0}")
    @MethodSource("ordinaryCases")
    void testLastPart(String name, int[][] pointsByStage, boolean[][] canFollow, int expected) {
        check(name, pointsByStage, canFollow, expected);
    }

    private static Stream<Arguments> ordinaryCases() {
        return Stream.of(
                arguments("임의 호환 관계에서 마지막 부품 선택",
                new int[][]{
                        {5, 2, 4},
                        {3, 8, 1},
                        {6, 2, 20}
                },
                new boolean[][]{
                        {false, true, true},
                        {true, false, false},
                        {true, true, false}
                },
                2),
                arguments("한 단계·점수 0·부품 한 개",
                new int[][]{{0}},
                new boolean[][]{{false}},
                0),
                arguments("최고 총점 동점이면 작은 부품 번호",
                new int[][]{{940, 940, 1}},
                new boolean[][]{
                        {false, false, false},
                        {false, false, false},
                        {false, false, false}
                },
                0),
                arguments("같은 부품 연속 선택을 입력이 허용",
                new int[][]{{5, 1}, {10, 0}},
                new boolean[][]{{true, false}, {false, true}},
                0),
                arguments("호환 관계의 방향을 반대로 읽지 않음",
                new int[][]{{100, 1}, {100, 100}},
                new boolean[][]{{false, true}, {false, false}},
                1),
                arguments("점수 0과 완전한 도달 불가 구분",
                new int[][]{{0, 940}, {1, 1}},
                new boolean[][]{{false, false}, {false, false}},
                -1)
        );
    }

    private static void check(
            String name,
            int[][] pointsByStage,
            boolean[][] canFollow,
            int expected
    ) {
        int[][] pointsBefore = cloneMatrix(pointsByStage);
        boolean[][] followBefore = cloneMatrix(canFollow);
        int actual = DynamicProgrammingSolution04.solve(pointsByStage, canFollow);
        assertAll(name,
                () -> assertEquals(expected, actual),
                () -> assertArrayEquals(pointsBefore, pointsByStage, "입력 점수표를 보존해야 한다"),
                () -> assertArrayEquals(followBefore, canFollow, "입력 호환 관계를 보존해야 한다")
        );
    }

    @Test
    @DisplayName("단계·부품 상한에서 long 범위의 합계를 처리한다")
    void testMaximumRange() {
        int[][] pointsByStage = new int[1_000][8];
        for (int[] stage : pointsByStage) {
            Arrays.fill(stage, 940_000_000);
            stage[0] = 1_000_000_000;
        }

        boolean[][] canFollow = new boolean[8][8];
        for (int part = 0; part < canFollow.length; part++) {
            canFollow[part][part] = true;
        }

        check("단계·부품 상한과 long 합계", pointsByStage, canFollow, 0);
    }

    private static int[][] cloneMatrix(int[][] source) {
        int[][] copy = new int[source.length][];
        for (int row = 0; row < source.length; row++) {
            copy[row] = source[row].clone();
        }
        return copy;
    }

    private static boolean[][] cloneMatrix(boolean[][] source) {
        boolean[][] copy = new boolean[source.length][];
        for (int row = 0; row < source.length; row++) {
            copy[row] = source[row].clone();
        }
        return copy;
    }

}

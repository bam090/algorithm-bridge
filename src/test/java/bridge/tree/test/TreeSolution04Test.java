package bridge.tree.test;

import bridge.tree.solution.TreeSolution04;
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

@DisplayName("트리 04 - 부모 사슬로 점수 누적하기")
final class TreeSolution04Test {

    /*
     * 테스트 범위
     * - outputOrder 길이: 하한 1, 임의 반환 순서, 상한 1,000
     * - 폴더 이름 길이: 하한 1, 상한 20
     * - relations 길이: 하한 0, 섞인 행 순서, 부모가 자식보다 뒤인 순서, 상한 999
     * - 트리 깊이: 일반 분기와 부모 사슬에서 만나는 폴더 수 상한 50
     * - 사건 수: 하한 0, 반복 사건, 상한 100,000
     * - eventPoints: 하한 -1,000,000, 0, 중간 940, 상한 1,000,000
     * - 결과: 부모·뿌리 누적, 지정 반환 순서, 음수 상쇄, long 범위와 원본 보존
     *
     * 대표 오답
     * - outputOrder와 relations의 같은 인덱스가 연결됐다고 가정한다.
     * - 부모가 먼저 나와야 관계를 만들 수 있다고 가정한다.
     * - 뿌리의 부모를 찾다가 null을 잘못 처리하거나 결과를 Map 순서로 반환한다.
     * - 누적값을 int로 계산하거나 입력 배열을 바꾼다.
     */
    @Test
    @DisplayName("분기된 부모 누적과 원본 보존")
    void accumulatesBranchesWithoutChangingInputs() {
        String[] outputOrder = {"summer", "root", "work", "photo"};
        String[][] relations = {{"work", "root"}, {"summer", "photo"}, {"photo", "root"}};
        String[] eventNames = {"summer", "work"};
        int[] eventPoints = {5, 2};
        String[] outputOrderBefore = outputOrder.clone();
        String[][] relationsBefore = cloneRows(relations);
        String[] eventsBefore = eventNames.clone();
        int[] pointsBefore = eventPoints.clone();

        long[] actual = TreeSolution04.solve(outputOrder, relations, eventNames, eventPoints);

        assertAll(
                () -> assertArrayEquals(new long[]{5, 7, 2, 5}, actual),
                () -> assertArrayEquals(outputOrderBefore, outputOrder),
                () -> assertRowsEqual(relationsBefore, relations),
                () -> assertArrayEquals(eventsBefore, eventNames),
                () -> assertArrayEquals(pointsBefore, eventPoints)
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("basicCases")
    @DisplayName("입력과 점수 경계")
    void accumulatesBasicCases(
            String name,
            String[] outputOrder,
            String[][] relations,
            String[] eventNames,
            int[] eventPoints,
            long[] expected
    ) {
        assertArrayEquals(expected, TreeSolution04.solve(outputOrder, relations, eventNames, eventPoints));
    }

    static Stream<Arguments> basicCases() {
        return Stream.of(
                Arguments.of(
                        "폴더·이름·관계·사건 수 하한",
                        new String[]{"r"}, new String[][]{}, new String[]{}, new int[]{},
                        new long[]{0}
                ),
                Arguments.of(
                        "반복 사건과 0·940·음수",
                        new String[]{"beta", "root", "alpha"},
                        new String[][]{{"beta", "alpha"}, {"alpha", "root"}},
                        new String[]{"beta", "beta", "alpha"},
                        new int[]{940, -1_000, 0},
                        new long[]{-60, -60, -60}
                ),
                Arguments.of(
                        "점수 하한과 상한이 뿌리에서 상쇄",
                        new String[]{"left", "right", "root"},
                        new String[][]{{"right", "root"}, {"left", "root"}},
                        new String[]{"left", "right"},
                        new int[]{-1_000_000, 1_000_000},
                        new long[]{-1_000_000, 1_000_000, 0}
                ),
                Arguments.of(
                        "뿌리 사건과 중간값 940",
                        new String[]{"leaf", "root"},
                        new String[][]{{"leaf", "root"}},
                        new String[]{"root"},
                        new int[]{940},
                        new long[]{0, 940}
                )
        );
    }

    @Test
    @DisplayName("이름·관계·깊이·사건 수 상한과 long 누적")
    void handlesMaximumSizesAndLongAccumulation() {
        String[] outputOrder = new String[1_000];
        for (int index = 0; index < outputOrder.length; index++) {
            outputOrder[index] = nodeName(outputOrder.length - 1 - index);
        }

        String[][] relations = new String[999][2];
        int relationIndex = 0;
        for (int child = 999; child >= 1; child--) {
            relations[relationIndex][0] = nodeName(child);
            relations[relationIndex][1] = child < 50 ? nodeName(child - 1) : nodeName(0);
            relationIndex++;
        }

        String[] eventNames = new String[100_000];
        Arrays.fill(eventNames, nodeName(49));
        int[] eventPoints = new int[100_000];
        Arrays.fill(eventPoints, 1_000_000);

        long[] expected = new long[1_000];
        for (int node = 0; node < 50; node++) {
            expected[999 - node] = 100_000_000_000L;
        }
        assertArrayEquals(
                expected,
                TreeSolution04.solve(outputOrder, relations, eventNames, eventPoints)
        );
    }

    private static String[][] cloneRows(String[][] source) {
        String[][] copy = new String[source.length][];
        for (int index = 0; index < source.length; index++) {
            copy[index] = source[index].clone();
        }
        return copy;
    }

    private static String nodeName(int number) {
        return String.format("node%016d", number);
    }

    private static void assertRowsEqual(String[][] expected, String[][] actual) {
        assertEquals(expected.length, actual.length);
        for (int row = 0; row < expected.length; row++) {
            assertArrayEquals(expected[row], actual[row], "row=" + row);
        }
    }
}

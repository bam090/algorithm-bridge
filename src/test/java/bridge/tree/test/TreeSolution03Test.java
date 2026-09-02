package bridge.tree.test;

import bridge.tree.solution.TreeSolution03;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

@DisplayName("트리 03 - 두 노드가 만나는 부모 찾기")
final class TreeSolution03Test {

    /*
     * 테스트 범위
     * - 두 노드 번호: 하한 1, 중간 940·941, 상한 1,000,000,000
     * - 관계: 같은 노드, 형제, 조상·자손, 서로 다른 깊이, 뿌리에서 만남
     * - 이동 횟수: 한쪽 0, 양쪽 같은 횟수, 양쪽 다른 횟수, 최대 29
     */
    @ParameterizedTest(name = "{0}")
    @MethodSource("nodeCases")
    @DisplayName("관계와 노드 번호 경계")
    void findsCommonParentAndMoveCounts(String name, int first, int second, int[] expected) {
        assertArrayEquals(expected, TreeSolution03.solve(first, second));
    }

    static Stream<Arguments> nodeCases() {
        return Stream.of(
                Arguments.of("형제 노드", 10, 11, new int[]{5, 1, 1}),
                Arguments.of("한쪽이 다른 쪽의 조상", 2, 9, new int[]{2, 0, 2}),
                Arguments.of("번호 하한에서 같은 노드", 1, 1, new int[]{1, 0, 0}),
                Arguments.of("중간값 940과 이웃 형제", 940, 941, new int[]{470, 1, 1}),
                Arguments.of("서로 다른 깊이에서 뿌리로 수렴", 31, 8, new int[]{1, 4, 3}),
                Arguments.of(
                        "번호 상한에서 뿌리까지 29번",
                        1,
                        1_000_000_000,
                        new int[]{1, 0, 29}
                )
        );
    }
}

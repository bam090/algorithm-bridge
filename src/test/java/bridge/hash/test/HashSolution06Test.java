package bridge.hash.test;

import bridge.hash.solution.HashSolution06;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import java.util.Arrays;

public final class HashSolution06Test {

    /*
     * 매개변수 | 허용 범위
     * reviewerOrder.length | 1 이상 1_000 이하
     * projectIds.length | 0 이상 10_000 이하
     * priorities.length | projectIds.length와 같음
     * relations.length | 0 이상 100_000 이하
     * relations[i].length | 2
     * 모든 검수자 ID와 프로젝트 ID 길이 | 1 이상 20 이하
     * priorities[i] | 0 이상 1_000 이하
     * minimumReviewers | 1 이상 reviewerOrder.length 이하
     */
    @ParameterizedTest(name = "{0}")
    @MethodSource("reviewCases")
    @DisplayName("검수자별 조건에 맞는 프로젝트를 고른다")
    void testReviews(
            String name,
            String[] reviewerOrder,
            String[] projectIds,
            int[] priorities,
            String[][] relations,
            int minimumReviewers,
            String[] expected
    ) {
        check(name, reviewerOrder, projectIds, priorities, relations, minimumReviewers, expected);
    }

    @Test
    @DisplayName("프로젝트 길이 상한과 전체 우선순위 동점")
    void testProjectCountMaximum() {
        int projectCount = 10_000;
        String[] projects = new String[projectCount];
        int[] priorities = new int[projectCount];
        String[][] relations = new String[projectCount][2];

        for (int index = 0; index < projectCount; index++) {
            projects[index] = String.format("p%05d", projectCount - 1 - index);
            priorities[index] = 940;
            relations[index][0] = "A";
            relations[index][1] = projects[index];
        }
        check("프로젝트 길이 상한과 전체 우선순위 동점", new String[]{"A"}, projects, priorities,
                relations, 1, new String[]{"p00000"});
    }

    @Test
    @DisplayName("검수자·관계 길이와 기준 상한")
    void testReviewerAndRelationMaximums() {
        int reviewerCount = 1_000;
        String[] reviewers = new String[reviewerCount];
        for (int index = 0; index < reviewerCount; index++) {
            reviewers[index] = String.format("r%03d", index);
        }

        int relationCount = 100_000;
        String[][] relations = new String[relationCount][2];
        for (int index = 0; index < relationCount; index++) {
            relations[index][0] = reviewers[index % reviewerCount];
            relations[index][1] = "P";
        }

        String[] expected = new String[reviewerCount];
        Arrays.fill(expected, "P");
        check("검수자·관계 길이와 기준 상한", reviewers, new String[]{"P"}, new int[]{940},
                relations, reviewerCount, expected);
    }

    private static Stream<Arguments> reviewCases() {
        String reviewer = "abcdefghijklmnopqrst";
        String project = "ABCDEFGHIJKLMNOPQRST";
        return Stream.of(
                Arguments.of(
                        "중복 제거·우선순위·ID 동점",
                        new String[]{"A", "B", "C"},
                        new String[]{"P", "Q", "R"},
                        new int[]{3, 5, 5},
                        new String[][]{
                            {"A", "P"}, {"A", "P"}, {"B", "P"},
                            {"A", "Q"}, {"C", "Q"}, {"B", "R"}, {"C", "R"}
                        },
                        2,
                        new String[]{"Q", "R", "Q"}),
                Arguments.of(
                        "프로젝트와 관계 길이 하한",
                        new String[]{"A"}, new String[0], new int[0], new String[0][2], 1,
                        new String[]{""}),
                Arguments.of(
                        "검수자 하나와 ID 길이 상한",
                        new String[]{reviewer},
                        new String[]{project},
                        new int[]{940},
                        new String[][]{{reviewer, project}},
                        1,
                        new String[]{project}),
                Arguments.of(
                        "중복 관계를 여러 사람으로 세는 오답 방지",
                        new String[]{"A", "B"},
                        new String[]{"P"},
                        new int[]{100},
                        new String[][]{{"A", "P"}, {"A", "P"}},
                        2,
                        new String[]{"", ""}),
                Arguments.of(
                        "우선순위 하한·상한과 검수자 원래 순서",
                        new String[]{"C", "A", "B"},
                        new String[]{"low", "high"},
                        new int[]{0, 1_000},
                        new String[][]{
                            {"C", "low"}, {"A", "low"}, {"A", "high"}, {"B", "high"}
                        },
                        2,
                        new String[]{"low", "high", "high"})
        );
    }

    private static void check(
            String name,
            String[] reviewerOrder,
            String[] projectIds,
            int[] priorities,
            String[][] relations,
            int minimumReviewers,
            String[] expected
    ) {
        String[] originalReviewerOrder = reviewerOrder.clone();
        String[] originalProjectIds = projectIds.clone();
        int[] originalPriorities = priorities.clone();
        String[][] originalRelations = copyRelations(relations);
        String[] actual = HashSolution06.solve(
                reviewerOrder,
                projectIds,
                priorities,
                relations,
                minimumReviewers
        );

        assertAll(name,
                () -> assertArrayEquals(expected, actual),
                () -> assertArrayEquals(originalReviewerOrder, reviewerOrder,
                        "원본 reviewerOrder가 변경되었습니다."),
                () -> assertArrayEquals(originalProjectIds, projectIds,
                        "원본 projectIds가 변경되었습니다."),
                () -> assertArrayEquals(originalPriorities, priorities,
                        "원본 priorities가 변경되었습니다."),
                () -> assertRowsEqual(originalRelations, relations,
                        "원본 relations가 변경되었습니다."),
                () -> assertNotSame(reviewerOrder, actual,
                        "결과가 원본 reviewerOrder와 같은 배열입니다."),
                () -> assertNotSame(projectIds, actual,
                        "결과가 원본 projectIds와 같은 배열입니다."));
    }

    private static void assertRowsEqual(String[][] expected, String[][] actual, String message) {
        assertEquals(expected.length, actual.length, message);
        for (int row = 0; row < expected.length; row++) {
            assertArrayEquals(expected[row], actual[row], message + " row=" + row);
        }
    }

    private static String[][] copyRelations(String[][] relations) {
        String[][] copy = new String[relations.length][];
        for (int index = 0; index < relations.length; index++) {
            copy[index] = relations[index].clone();
        }
        return copy;
    }

}

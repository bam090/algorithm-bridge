package bridge.hash.test;

import bridge.hash.solution.HashSolution06;

import java.util.Arrays;

public final class HashSolution06Test {

    private static int passed;
    private static int failed;

    private HashSolution06Test() {
    }

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
    public static void main(String[] args) {
        runCase("중복 제거·우선순위·ID 동점", HashSolution06Test::testPriorityAndIdentifierTieBreaker);
        runCase("프로젝트와 관계 길이 하한", HashSolution06Test::testNoProjectsOrRelations);
        runCase("검수자 하나와 ID 길이 상한", HashSolution06Test::testOneReviewerAndIdentifierLengthUpper);
        runCase("중복 관계를 여러 사람으로 세는 오답 방지", HashSolution06Test::testDuplicateRelationDoesNotReachThreshold);
        runCase("우선순위 하한·상한과 검수자 원래 순서", HashSolution06Test::testPriorityBoundsAndReviewerOrder);
        runCase("프로젝트 길이 상한과 전체 우선순위 동점", HashSolution06Test::testProjectCountMaximum);
        runCase("검수자·관계 길이와 기준 상한", HashSolution06Test::testReviewerAndRelationMaximums);

        finish();
    }

    private static void runCase(String name, Runnable test) {
        try {
            test.run();
        } catch (AssertionError | RuntimeException error) {
            failed++;
            System.out.println("[FAIL] " + name + ": " + error);
        }
    }

    private static void testPriorityAndIdentifierTieBreaker() {
        String[] reviewers = {"A", "B", "C"};
        String[] projects = {"P", "Q", "R"};
        int[] priorities = {3, 5, 5};
        String[][] relations = {
                {"A", "P"}, {"A", "P"}, {"B", "P"},
                {"A", "Q"}, {"C", "Q"}, {"B", "R"}, {"C", "R"}
        };
        check("중복 제거·우선순위·ID 동점", reviewers, projects, priorities, relations, 2,
                new String[]{"Q", "R", "Q"});
    }

    private static void testNoProjectsOrRelations() {
        check("프로젝트와 관계 길이 하한", new String[]{"A"}, new String[0], new int[0],
                new String[0][2], 1, new String[]{""});
    }

    private static void testOneReviewerAndIdentifierLengthUpper() {
        String reviewer = "abcdefghijklmnopqrst";
        String project = "ABCDEFGHIJKLMNOPQRST";
        check("검수자 하나와 ID 길이 상한", new String[]{reviewer}, new String[]{project},
                new int[]{940}, new String[][]{{reviewer, project}}, 1, new String[]{project});
    }

    private static void testDuplicateRelationDoesNotReachThreshold() {
        String[] reviewers = {"A", "B"};
        String[] projects = {"P"};
        int[] priorities = {100};
        String[][] relations = {{"A", "P"}, {"A", "P"}};
        check("중복 관계를 여러 사람으로 세는 오답 방지", reviewers, projects, priorities, relations, 2,
                new String[]{"", ""});
    }

    private static void testPriorityBoundsAndReviewerOrder() {
        String[] reviewers = {"C", "A", "B"};
        String[] projects = {"low", "high"};
        int[] priorities = {0, 1_000};
        String[][] relations = {{"C", "low"}, {"A", "low"}, {"A", "high"}, {"B", "high"}};
        check("우선순위 하한·상한과 검수자 원래 순서", reviewers, projects, priorities, relations, 2,
                new String[]{"low", "high", "high"});
    }

    private static void testProjectCountMaximum() {
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

    private static void testReviewerAndRelationMaximums() {
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
        boolean originalsPreserved = Arrays.equals(reviewerOrder, originalReviewerOrder)
                && Arrays.equals(projectIds, originalProjectIds)
                && Arrays.equals(priorities, originalPriorities)
                && Arrays.deepEquals(relations, originalRelations);
        boolean newArrayReturned = actual != reviewerOrder && actual != projectIds;
        boolean success = Arrays.equals(actual, expected) && originalsPreserved && newArrayReturned;

        if (success) {
            passed++;
            System.out.println("[PASS] " + name);
        } else {
            failed++;
            System.out.println("[FAIL] " + name
                    + ": expected=" + Arrays.toString(expected)
                    + ", actual=" + Arrays.toString(actual)
                    + ", originalsPreserved=" + originalsPreserved
                    + ", newArrayReturned=" + newArrayReturned);
        }
    }

    private static String[][] copyRelations(String[][] relations) {
        String[][] copy = new String[relations.length][];
        for (int index = 0; index < relations.length; index++) {
            copy[index] = relations[index].clone();
        }
        return copy;
    }

    private static void finish() {
        System.out.println("[RESULT] HashSolution06: " + passed + "/" + (passed + failed) + " 통과");
        if (failed > 0) {
            throw new AssertionError("HashSolution06 실패: " + failed + "건");
        }
    }
}

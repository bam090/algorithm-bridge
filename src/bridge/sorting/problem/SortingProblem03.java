package bridge.sorting.problem;

/*
 * 문제: 검토 요청 순서 정하기
 *
 * 문제 설명
 * 각 검토 요청에는 ID, 우선 점수와 작업량 점수가 있다.
 * 우선 점수가 높은 요청을 먼저 놓고, 우선 점수가 같으면 작업량 점수가 낮은 요청을 먼저 놓는다.
 * 두 점수가 모두 같으면 입력에서 먼저 나온 요청의 순서를 그대로 지킨다.
 * 정해진 순서대로 요청 ID를 담은 새 배열을 반환하며 입력 배열은 바꾸지 않는다.
 *
 * 입력과 출력
 * - 입력: 요청 ID requestIds, 우선 점수 priorityScores, 작업량 점수 effortScores
 * - 출력: 모든 정렬 기준을 적용한 요청 ID 배열
 *
 * 입출력 예시
 * requestIds = ["A", "B", "C", "D"]
 * priorityScores = [2, 3, 3, 3]
 * effortScores = [10, 20, 10, 10]
 * 결과 = ["C", "D", "B", "A"]
 *
 * 코드를 쓰기 전에 생각할 질문
 *
 * - 첫 번째 정렬 기준은 어느 방향으로 비교해야 하는가?
 * - 우선 점수가 같을 때 어떤 값을 다음으로 비교해야 하는가?
 * - 두 점수가 모두 같을 때 입력 순서를 지키려면 비교 결과를 어떻게 해야 하는가?
 * - ID만 따로 정렬하지 않고 같은 위치의 두 점수를 함께 확인할 방법은 무엇인가?
 */
public final class SortingProblem03 {

    private SortingProblem03() {
        solve(
                new String[]{"A", "B", "C", "D"},
                new int[]{2, 3, 3, 3},
                new int[]{10, 20, 10, 10}
        );
        // 예상 출력: ["C", "D", "B", "A"]
    }

    //region 먼저 직접 생각한 뒤 확인할 접근 방식
    /*
     * 배열 위치를 우선 점수 내림차순으로 비교하고, 동점이면 작업량 점수 오름차순으로 비교한 뒤, 두 점수가 같으면 입력 순서를 유지해 ID 배열로 반환한다.
     */
    //endregion

    /*
     * 제약 조건
     * - 0 <= requestIds.length <= 10_000
     * - requestIds.length == priorityScores.length == effortScores.length
     * - 요청 ID는 서로 다르며 null이 아니고 길이는 1 이상 20 이하이다.
     * - priorityScores[i]와 effortScores[i]는 int 범위의 정수이다.
     * - 두 점수가 모두 같으면 입력 순서를 유지해야 한다.
     * - 입력 배열은 바꾸지 않고 항상 새 배열을 반환한다.
     */
    public static String[] solve(String[] requestIds, int[] priorityScores, int[] effortScores) {
        String[] answer = {};
        // 여기에 직접 구현한다.
        return answer;
    }

    //region 막혔을 때만 확인할 단계별 힌트
    /*
     * - 요청 ID 대신 0부터 시작하는 배열 위치를 정렬하면 같은 위치의 점수도 함께 확인할 수 있다.
     * - 첫 기준의 비교 결과가 0일 때만 둘째 기준을 비교한다.
     * - 두 점수가 같을 때 비교 결과를 0으로 두면 안정 정렬이 입력 순서를 지킨다.
     */
    //endregion
}

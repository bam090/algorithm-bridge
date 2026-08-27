package bridge.greedy.problem;

/*
 * 문제 | 산책로 빈 구간 덮기
 * 산책로의 표지 위치는 0부터 roadLength - 1까지이다.
 * coveredIntervals의 각 행 {시작, 끝}은 이미 덮인 양끝 포함 구간이다.
 * 새 덮개 하나를 위치 start에 놓으면 start부터 start + patchWidth - 1까지 덮는다.
 *
 * 모든 표지 위치를 가장 적은 새 덮개로 덮고, 새 덮개의 시작 위치를 설치 순서대로 반환하라.
 * 기존 구간은 겹치거나 정렬되지 않을 수 있다. 빈 산책로의 결과는 빈 배열이며 원본은 바꾸면 안 된다.
 *
 * 코드를 쓰기 전에 생각할 질문
 *
 * - 현재 위치가 기존 구간 안에 있다면 어디까지 바로 건너뛸 수 있는가?
 * - 첫 번째 빈 위치를 덮는 새 덮개는 어디서 시작해야 오른쪽을 가장 멀리 덮는가?
 * - 겹친 기존 구간을 만났을 때 현재 위치를 뒤로 옮기면 안 되는 이유는 무엇인가?
 * - 길이가 매우 커도 모든 위치를 배열로 만들 필요가 있는가?
 */
public final class GreedyProblem06 {

    private GreedyProblem06() {
        solve(15, new int[][]{{4, 6}, {11, 12}}, 3);
        // 예상 출력: new int[]{0, 3, 7, 10, 13}
    }

    //region 먼저 직접 생각한 뒤 확인할 접근 방식
    /*
     * 접근 조합
     * 기존 구간의 끝 다음으로 건너뛰고, 빈 위치에서는 그 위치부터 고정 폭을 최대한 덮으며 시작 위치를 반환한다.
     */
    //endregion

    /*
     * 제약 조건
     * - roadLength는 0 이상 1,000,000,000 이하이다.
     * - coveredIntervals는 null이 아니고 길이는 0 이상 100,000 이하이다.
     * - roadLength가 0이면 coveredIntervals는 비어 있다.
     * - 각 행의 길이는 2이며 {시작, 끝}인 양끝 포함 구간이다.
     * - roadLength가 양수일 때 0 <= 시작 <= 끝 < roadLength이다.
     * - 기존 구간은 겹치거나 정렬되지 않을 수 있다.
     * - patchWidth는 1 이상 1,000,000,000 이하이다.
     * - 새 덮개 시작 위치의 개수는 100,000 이하가 되도록 입력이 주어진다.
     * - 새 덮개가 roadLength - 1을 넘어 덮어도 된다.
     * - 원본 배열과 각 행을 바꾸지 않는다.
     */

    // solve 메서드 몸체를 직접 구현한다.
    public static int[] solve(int roadLength, int[][] coveredIntervals, int patchWidth) {
        int[] answer = {};
        return answer;
    }
}

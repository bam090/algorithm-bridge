package bridge.dynamicprogramming.problem;

//region 문제: 마지막 조립 부품 고르기
/*
 pointsByStage[stage][part]는 stage번 조립 단계에서 part번 부품을 골랐을 때의 점수다.
 첫 단계에서는 어떤 부품이든 고를 수 있다. 두 번째 단계부터는 바로 앞 단계의 부품과
 현재 부품이 canFollow[previousPart][currentPart]에서 true일 때만 이어서 고를 수 있다.

 모든 단계를 마치는 총점이 가장 커지는 마지막 부품 번호를 반환하라.
 최고 총점으로 끝나는 부품이 여러 개면 번호가 가장 작은 부품을 반환한다.
 모든 단계를 마칠 수 없으면 -1을 반환한다.
 */
//endregion

//region 입력과 출력
/*
 - 입력: 단계별 부품 점수 pointsByStage와 앞뒤 부품의 방향 있는 호환 관계 canFollow
 - 출력: 최고 총점으로 모든 단계를 마치는 마지막 부품 번호 또는 -1
 */
//endregion

//region 코드를 쓰기 전에 생각할 질문
/*
 - 한 단계가 끝났을 때 전체 최고점 하나만 기억하면 다음 부품의 호환 여부를 판단할 수 있는가?
 - 현재 부품마다 어떤 값을 따로 기억해야 하는가?
 - canFollow의 행과 열은 각각 앞 부품과 현재 부품 중 무엇인가?
 - 점수가 0인 상태와 도달할 수 없는 상태를 어떻게 구분할 것인가?
 - 마지막 부품의 최고 총점이 같을 때 어떤 순서로 답을 고를 것인가?
 */
//endregion

public final class DynamicProgrammingProblem04 {

    private DynamicProgrammingProblem04() {
        solve(
                new int[][]{
                        {5, 2, 4},
                        {3, 8, 1},
                        {6, 2, 20}
                },
                new boolean[][]{
                        {false, true, true},
                        {true, false, false},
                        {true, true, false}
                }
        );
        // 예상 출력: 2
    }

    //region 먼저 직접 생각한 뒤 확인할 접근 방식
    /*
     현재 부품마다 호환되는 앞 부품들의 최고 총점을 찾아 현재 점수를 더하고, 마지막 단계의 상태별 총점을 비교해 가장 큰 총점의 가장 작은 부품 번호를 반환한다.
     */
    //endregion

    //region 제약 조건
    /*
     - pointsByStage와 canFollow는 null이 아니다.
     - 1 <= pointsByStage.length <= 1,000
     - 1 <= pointsByStage[stage].length <= 8이고 모든 행 길이는 같다.
     - 0 <= pointsByStage[stage][part] <= 1,000,000,000
     - canFollow는 부품 수 × 부품 수 크기의 정사각형 배열이다.
     - canFollow[previousPart][currentPart]는 방향이 있으며 반대 방향과 값이 다를 수 있다.
     - 같은 부품을 연속해서 고를 수 있는지도 canFollow[part][part]의 값으로 정한다.
     - 여러 단계의 점수 합은 int 범위를 넘을 수 있으므로 long으로 계산한다.
     - 입력 배열은 바꾸면 안 된다.
     */
    //endregion

    // solve 메서드 몸체를 직접 구현한다.
    public static int solve(int[][] pointsByStage, boolean[][] canFollow) {
        int answer = -1;
        return answer;
    }
}

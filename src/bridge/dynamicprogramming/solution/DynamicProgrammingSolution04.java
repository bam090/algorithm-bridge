package bridge.dynamicprogramming.solution;

import java.util.Arrays;

/** 마지막 조립 부품 고르기 문제 정답과 풀이 설명이다. */
public final class DynamicProgrammingSolution04 {

    private DynamicProgrammingSolution04() {
    }

    public static int solve(int[][] pointsByStage, boolean[][] canFollow) {
        // 동적 계획법을 선택한 이유:
        // 다음 선택은 앞 부품에 따라 달라지므로, 단계와 마지막 부품별 최고 점수를 따로 저장한다.

        int stageCount = pointsByStage.length;
        int partCount = pointsByStage[0].length;

        // [1] 모든 상태를 도달 불가를 뜻하는 -1로 채운다.
        long[][] bestScore = new long[stageCount][partCount];

        for (long[] row : bestScore) {
            Arrays.fill(row, -1L);
        }

        // [2] 첫 단계는 앞 부품이 없으므로 각 부품의 점수를 그대로 저장한다.
        for (int part = 0; part < partCount; part++) {
            bestScore[0][part] = pointsByStage[0][part];
        }

        // [3] 현재 부품마다 canFollow[앞 부품][현재 부품]이 true인 앞 상태만 확인한다.
        for (int stage = 1; stage < stageCount; stage++) {
            for (int currentPart = 0; currentPart < partCount; currentPart++) {
                long bestPrevious = -1L;
                for (int previousPart = 0; previousPart < partCount; previousPart++) {
                    if (canFollow[previousPart][currentPart]
                            && bestScore[stage - 1][previousPart] >= 0L) {
                        bestPrevious = Math.max(
                                bestPrevious,
                                bestScore[stage - 1][previousPart]
                        );
                    }
                }

                // [4] 도달 가능한 앞 상태 중 최고 총점에 현재 점수를 더해 현재 상태를 저장한다.
                if (bestPrevious >= 0L) {
                    bestScore[stage][currentPart]
                            = bestPrevious + pointsByStage[stage][currentPart];
                }
            }
        }

        // [5] 마지막 단계의 부품을 작은 번호부터 확인하며 더 큰 총점의 번호를 저장한다.
        int answerPart = -1;
        long answerScore = -1L;
        for (int part = 0; part < partCount; part++) {
            long score = bestScore[stageCount - 1][part];
            if (score > answerScore) {
                answerScore = score;
                answerPart = part;
            }
        }

        // [6] 도달 가능한 마지막 상태가 없으면 -1, 있으면 저장한 부품 번호를 반환한다.
        return answerPart;
    }

    /*
     * 풀이 설명
     *
     * 발견해야 할 단서
     * - 현재 부품을 고를 수 있는지는 바로 앞 단계의 부품에 따라 달라진다.
     * - 전체 최고점 하나만 기억하면 그 점수가 어떤 부품으로 끝났는지 알 수 없다.
     * - 호환 관계에는 방향이 있고, 같은 부품의 연속 선택도 입력으로 결정된다.
     * - 점수 0은 가능한 상태이므로 도달 불가와 다른 값으로 구분해야 한다.
     *
     * 이 개념을 선택한 이유
     * - bestScore[stage][part]를 stage 단계에서 part 부품으로 끝나는 최고 총점으로 정하면 필요한 이전 정보를 잃지 않는다.
     * - 현재 부품과 호환되는 앞 부품의 상태만 비교하면 불가능한 조립 순서를 제외할 수 있다.
     *
     * 풀이 순서
     * 1. 모든 상태를 도달 불가를 뜻하는 -1로 채운다.
     * 2. 첫 단계는 앞 부품이 없으므로 각 부품의 점수를 그대로 저장한다.
     * 3. 현재 부품마다 canFollow[앞 부품][현재 부품]이 true인 앞 상태만 확인한다.
     * 4. 도달 가능한 앞 상태 중 최고 총점에 현재 점수를 더해 현재 상태를 저장한다.
     * 5. 마지막 단계의 부품을 작은 번호부터 확인하며 더 큰 총점의 번호를 저장한다.
     * 6. 도달 가능한 마지막 상태가 없으면 -1, 있으면 저장한 부품 번호를 반환한다.
     *
     * 예시 데이터 흐름
     * - 첫 단계의 상태별 총점은 [5, 2, 4]다.
     * - 둘째 단계에서 0번 부품은 앞의 1번 또는 2번과 이어져 7점이 된다.
     * - 둘째 단계의 1번 부품은 앞의 0번 또는 2번과 이어져 13점, 2번 부품은 앞의 0번과 이어져 6점이다.
     * - 마지막 단계의 상태별 최고 총점은 [19, 9, 27]이므로 2번 부품을 반환한다.
     *
     * 복잡도
     * - 단계 수를 s, 부품 수를 p라 할 때 각 현재 부품에서 앞 부품을 확인하므로 시간 O(s × p²)다.
     * - 단계와 마지막 부품별 최고 총점을 저장하는 데 공간 O(s × p)가 필요하다.
     *
     * 자주 하는 실수
     * - 단계별 전체 최고점 하나만 남겨 어떤 부품으로 끝났는지 잃는다.
     * - canFollow[currentPart][previousPart]처럼 행과 열을 반대로 읽는다.
     * - 같은 부품을 항상 금지하거나 항상 허용해 입력 관계를 무시한다.
     * - 도달 불가 -1을 점수 0과 같은 상태로 계산한다.
     * - 합계를 int에 저장하거나 최고 총점 동점에서 더 작은 부품 번호를 놓친다.
     */
}

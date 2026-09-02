package bridge.greedy.solution;

import java.util.Arrays;

/** 점검실 예약 고르기 문제의 정답과 풀이 설명이다. */
public final class GreedySolution01 {

    private GreedySolution01() {
    }

    public static int[] solve(int[][] reservations) {
        // 그리디를 선택한 이유:
        // 모든 예약의 가치는 같으므로, 가장 빨리 끝나는 예약을 골라야 뒤에 가장 많은 시간이 남는다.

        // [1] 원본의 각 행을 복사한다.
        int[][] ordered = new int[reservations.length][];
        for (int i = 0; i < reservations.length; i++) {
            ordered[i] = reservations[i].clone();
        }

        // [2] 종료 시각, 시작 시각, 예약 번호 순으로 복사본을 정렬한다.
        Arrays.sort(ordered, (left, right) -> {
            int byEnd = Integer.compare(left[2], right[2]);
            if (byEnd != 0) {
                return byEnd;
            }
            int byStart = Integer.compare(left[1], right[1]);
            if (byStart != 0) {
                return byStart;
            }
            return Integer.compare(left[0], right[0]);
        });

        int[] selectedIds = new int[ordered.length];
        int selectedCount = 0;
        int lastEnd = -1;

        // [3] 마지막 종료 시각 뒤에 시작하는 예약만 고르고 종료 시각을 갱신한다.
        for (int[] reservation : ordered) {
            if (reservation[1] >= lastEnd) {
                selectedIds[selectedCount++] = reservation[0];
                lastEnd = reservation[2];
            }
        }

        // [4] 고른 예약 번호만 진행 순서대로 반환한다.
        return Arrays.copyOf(selectedIds, selectedCount);
    }

    /*
     * 풀이 설명
     *
     * 발견해야 할 단서
     * - 점검실은 하나이고, 서로 겹치지 않는 예약을 가장 많이 골라야 한다.
     * - 모든 예약 한 건의 가치는 같으므로 다음 예약이 들어갈 시간을 많이 남기는 선택이 필요하다.
     * - 종료 시각과 시작 시각이 같으면 이어서 진행할 수 있다.
     *
     * 이 선택이 안전한 이유
     * - 지금 고를 수 있는 예약 중 가장 빨리 끝나는 예약을 고르면 뒤에 남는 시간이 가장 길다.
     * - 어떤 최적 일정의 첫 예약이 더 늦게 끝난다면, 그 예약을 가장 빨리 끝나는 예약으로 바꾸어도
     *   뒤의 예약은 그대로 진행할 수 있다. 따라서 예약 수가 줄지 않는다.
     * - 가장 짧은 예약 기준은 안전하지 않다. [0,4], [4,8], [3,5]에서 [3,5]를 먼저 고르면
     *   앞뒤 두 예약 대신 하나만 고르게 된다.
     *
     * 풀이 순서
     * 1. 원본의 각 행을 복사한다.
     * 2. 종료 시각, 시작 시각, 예약 번호 순으로 복사본을 정렬한다.
     * 3. 마지막 종료 시각 뒤에 시작하는 예약만 고르고 종료 시각을 갱신한다.
     * 4. 고른 예약 번호만 진행 순서대로 반환한다.
     *
     * 예시 데이터 흐름
     * - 종료 시각순 예약 번호: 102, 103, 101, 104
     * - 102는 [1,2]이므로 고르고 마지막 종료 시각은 2가 된다.
     * - 103은 [2,3]이므로 이어서 고른다.
     * - 101은 0에 시작해 이미 고른 예약과 겹치므로 건너뛴다.
     * - 104는 [3,5]이므로 고르고 [102,103,104]를 반환한다.
     *
     * 복잡도
     * - 시간 O(n log n): n개 예약을 정렬한 뒤 한 번 확인한다.
     * - 공간 O(n): 원본을 보존하는 행 복사본과 결과 배열이 필요하다.
     *
     * 자주 하는 실수
     * - 시작 시각이나 예약 길이만 보고 먼저 고른다.
     * - start > lastEnd로 비교해 바로 이어지는 예약을 놓친다.
     * - 비교자에서 값을 빼다가 int 범위를 넘긴다.
     * - 원본 행을 그대로 정렬해 입력 순서를 바꾼다.
     */
}

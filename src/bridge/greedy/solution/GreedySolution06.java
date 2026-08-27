package bridge.greedy.solution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** 산책로 빈 구간 덮기 문제의 정답과 풀이 설명이다. */
public final class GreedySolution06 {

    private GreedySolution06() {
    }

    public static int[] solve(int roadLength, int[][] coveredIntervals, int patchWidth) {
        int[][] ordered = new int[coveredIntervals.length][];
        for (int i = 0; i < coveredIntervals.length; i++) {
            ordered[i] = coveredIntervals[i].clone();
        }
        Arrays.sort(ordered, (left, right) -> {
            int byStart = Integer.compare(left[0], right[0]);
            if (byStart != 0) {
                return byStart;
            }
            return Integer.compare(left[1], right[1]);
        });

        List<Integer> patchStarts = new ArrayList<>();
        long current = 0;

        for (int[] interval : ordered) {
            while (current < interval[0]) {
                patchStarts.add((int) current);
                current += patchWidth;
            }
            if (current <= interval[1]) {
                current = (long) interval[1] + 1;
            }
        }

        while (current < roadLength) {
            patchStarts.add((int) current);
            current += patchWidth;
        }

        int[] result = new int[patchStarts.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = patchStarts.get(i);
        }
        return result;
    }

    /*
     * 풀이 설명
     *
     * 발견해야 할 단서
     * - 산책로 길이는 매우 크지만 기존 구간과 새 덮개 시작 위치만 알면 된다.
     * - 새 덮개는 고정 폭이고, 첫 번째 빈 위치를 반드시 덮어야 한다.
     * - 기존 구간은 정렬되지 않고 겹칠 수 있다.
     *
     * 이 선택이 안전한 이유
     * - 첫 번째 빈 위치보다 왼쪽에서 새 덮개를 시작하면 이미 덮인 곳에 폭을 낭비한다.
     * - 첫 번째 빈 위치에서 시작하면 그 위치를 덮으면서 오른쪽을 가장 멀리 덮는다.
     * - 이 선택을 다른 최적 결과의 첫 덮개와 바꾸어도 이후의 빈 위치가 늘어나지 않는다.
     *
     * 풀이 순서
     * 1. 원본 행을 복사해 기존 구간을 시작과 끝 순서로 정렬한다.
     * 2. current가 구간 시작보다 작으면 빈 위치이므로 그곳에 새 덮개를 놓고 폭만큼 이동한다.
     * 3. current가 기존 구간 안에 있으면 구간 끝 다음으로 건너뛴다.
     * 4. 모든 기존 구간을 지난 뒤 산책로 끝까지 같은 방법으로 새 덮개를 놓는다.
     * 5. 새 덮개의 시작 위치를 설치 순서대로 반환한다.
     *
     * 예시 데이터 흐름
     * - 길이 15, 기존 구간 [4,6], [11,12], 새 덮개 폭 3이다.
     * - 0과 3에서 새 덮개를 시작한 뒤 기존 구간 끝 다음인 7로 간다.
     * - 7과 10에서 시작한 뒤 [11,12]를 지나 현재 위치는 13이 된다.
     * - 13에서 마지막 덮개를 놓고 [0,3,7,10,13]을 반환한다.
     *
     * 복잡도
     * - 시간 O(m log m + p): m개 기존 구간을 정렬하고 p개 새 덮개 시작 위치를 만든다.
     * - 공간 O(m + p): 원본 구간 복사본과 결과 목록이 필요하다.
     * - roadLength 크기의 배열은 만들지 않는다.
     *
     * 자주 하는 실수
     * - 양끝 포함 구간의 끝에서 1을 더하지 않아 같은 위치를 다시 확인한다.
     * - 겹친 구간을 만났을 때 current를 더 작은 값으로 되돌린다.
     * - 새 덮개의 마지막 위치를 current로 착각해 한 칸씩 비운다.
     * - 원본 구간을 직접 정렬한다.
     */
}

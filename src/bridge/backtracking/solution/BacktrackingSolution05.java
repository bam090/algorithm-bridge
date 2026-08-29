package bridge.backtracking.solution;

/** 프로젝트 연습 시간 배분 문제 정답과 풀이 설명이다. */
public final class BacktrackingSolution05 {

    private BacktrackingSolution05() {
    }

    public static int[] solve(int[][] pointsByHours, int hourLimit) {
        // 프로젝트마다 시간 후보가 여러 개이고, 최선은 모든 프로젝트를 배정한 뒤에 알 수 있다.
        // 한도 안의 후보를 재귀로 모두 확인하면 점수와 동점 규칙까지 빠짐없이 비교할 수 있다.
        BestAllocation best = new BestAllocation();
        int[] current = new int[pointsByHours.length];
        search(0, hourLimit, 0, 0, pointsByHours, current, best);
        return best.hours;
    }

    private static void search(
            int project,
            int remainingHours,
            int currentScore,
            int usedHours,
            int[][] pointsByHours,
            int[] current,
            BestAllocation best
    ) {
        if (project == pointsByHours.length) {
            if (isBetter(currentScore, usedHours, current, best)) {
                best.score = currentScore;
                best.usedHours = usedHours;
                // [6] 더 좋은 결과라면 현재 배정 배열을 복사해 저장한다.
                best.hours = current.clone();
            }
            return;
        }

        int maximum = Math.min(remainingHours, pointsByHours[project].length - 1);
        // [1] 현재 프로젝트에 0시간부터 가능한 최대 시간까지 하나씩 배정한다.
        for (int hours = 0; hours <= maximum; hours++) {
            // [2] 배정한 시간을 한도에서 빼고 해당 점수를 더해 다음 프로젝트로 이동한다.
            current[project] = hours;
            search(
                    project + 1,
                    remainingHours - hours,
                    currentScore + pointsByHours[project][hours],
                    usedHours + hours,
                    pointsByHours,
                    current,
                    best
            );
        }
        current[project] = 0;
    }

    private static boolean isBetter(
            int score,
            int usedHours,
            int[] current,
            BestAllocation best
    ) {
        // [3] 모든 프로젝트를 확인하면 점수가 더 큰지 비교한다.
        if (best.hours == null || score != best.score) {
            return best.hours == null || score > best.score;
        }
        // [4] 점수가 같으면 사용 시간이 더 적은지 비교한다.
        if (usedHours != best.usedHours) {
            return usedHours < best.usedHours;
        }

        // [5] 두 값도 같으면 앞 프로젝트부터 시간이 더 적은 배정인지 비교한다.
        for (int project = 0; project < current.length; project++) {
            if (current[project] != best.hours[project]) {
                return current[project] < best.hours[project];
            }
        }
        return false;
    }

    private static final class BestAllocation {
        private int score = -1;
        private int usedHours = Integer.MAX_VALUE;
        private int[] hours;
    }

    /*
     * 풀이 설명
     *
     * 발견해야 할 단서
     * - 프로젝트마다 배정할 수 있는 시간이 여러 개이고 전체 시간에 한도가 있다.
     * - 모든 프로젝트의 배정을 끝내야 전체 점수와 동점 기준을 비교할 수 있다.
     * - 현재 배정 배열은 다음 후보를 확인하면서 계속 바뀐다.
     *
     * 이 개념을 선택한 이유
     * - 한 프로젝트의 시간 후보를 하나 골라 다음 프로젝트로 이동하면 가능한 배정을 모두 확인할 수 있다.
     * - 완성된 배정만 최고 결과와 비교하고 clone()으로 복사하면 이후 탐색의 변경에서 답을 지킬 수 있다.
     *
     * 풀이 순서
     * 1. 현재 프로젝트에 0시간부터 가능한 최대 시간까지 하나씩 배정한다.
     * 2. 배정한 시간을 한도에서 빼고 해당 점수를 더해 다음 프로젝트로 이동한다.
     * 3. 모든 프로젝트를 확인하면 점수가 더 큰지 비교한다.
     * 4. 점수가 같으면 사용 시간이 더 적은지 비교한다.
     * 5. 두 값도 같으면 앞 프로젝트부터 시간이 더 적은 배정인지 비교한다.
     * 6. 더 좋은 결과라면 현재 배정 배열을 복사해 저장한다.
     *
     * 예시 데이터 흐름
     * - 시간 한도는 2다.
     * - 첫 프로젝트 1시간은 4점, 둘째 프로젝트 1시간은 5점이다.
     * - [1, 1]을 배정하면 2시간으로 9점을 얻는다.
     * - [2, 0]은 7점이고 [0, 2]는 6점이므로 [1, 1]을 반환한다.
     *
     * 복잡도
     * - 시간 O(p × 6^p): 프로젝트 p개가 각각 최대 6개 시간 후보를 가지며 동점 비교에 p가 든다.
     * - 공간 O(p): 현재·최선 배열과 최대 깊이 p의 재귀 호출을 사용한다.
     *
     * 자주 하는 실수
     * - 남은 시간보다 큰 후보까지 확인해 전체 한도를 넘긴다.
     * - 점수만 비교하고 사용 시간과 배열 순서의 동점 규칙을 빠뜨린다.
     * - current를 그대로 best에 넣어 이후 탐색이 최선의 답까지 바꾼다.
     */
}

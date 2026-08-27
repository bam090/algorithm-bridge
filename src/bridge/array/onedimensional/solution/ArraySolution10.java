package bridge.array.onedimensional.solution;

/** 가까운 기록 쌍 세기 문제의 정답과 풀이 설명이다. */
public final class ArraySolution10 {

    private ArraySolution10() {
    }

    public static int solve(int[] values, int maxDifference) {
        int count = 0;
        for (int i = 0; i < values.length; i++) {
            for (int j = i + 1; j < values.length; j++) {
                if (Math.abs(values[i] - values[j]) <= maxDifference) {
                    count++;
                }
            }
        }
        return count;
    }

    /*
     * 풀이 설명
     *
     * 발견해야 할 단서
     * - 값이 아니라 서로 다른 두 위치를 한 쌍으로 고른다.
     * - 위치 순서는 중요하지 않으므로 (i, j)와 (j, i)를 두 번 세면 안 된다.
     * - 차이가 maxDifference와 같아도 조건을 통과한다.
     *
     * 이 개념을 선택한 이유
     * - 배열 길이가 최대 300이라 모든 서로 다른 위치 쌍을 직접 확인할 수 있다.
     * - 두 번째 인덱스를 i + 1부터 시작하면 자기 자신과 순서만 바뀐 같은 쌍을 함께 제외할 수 있다.
     *
     * 풀이 순서
     * 1. count를 0으로 시작한다.
     * 2. 첫 번째 위치 i를 앞에서부터 고른다.
     * 3. 두 번째 위치 j를 i + 1부터 배열 끝까지 고른다.
     * 4. 두 값의 차이의 절댓값이 maxDifference 이하면 count를 늘린다.
     * 5. 모든 쌍을 확인한 뒤 count를 반환한다.
     *
     * 예시 데이터 흐름
     * - values=[-2, 0, 1, 4], maxDifference=2
     * - (-2, 0)의 차이는 2이므로 count=1
     * - (-2, 1), (-2, 4)는 차이가 2보다 커서 세지 않는다.
     * - (0, 1)의 차이는 1이므로 count=2
     * - (0, 4), (1, 4)는 차이가 2보다 커서 세지 않는다.
     * - 결과: 2
     *
     * 복잡도
     * - 시간 O(n²): 서로 다른 모든 위치 쌍을 확인한다.
     * - 공간 O(1): 입력 크기와 관계없이 count와 인덱스만 사용한다.
     *
     * 자주 하는 실수
     * - j를 0부터 시작해 같은 쌍을 두 번 세거나 자기 자신까지 센다.
     * - 같은 값은 한 번만 세야 한다고 생각해 서로 다른 위치 쌍을 놓친다.
     * - <= 대신 <를 사용해 경계와 같은 차이를 제외한다.
     * - 이웃한 두 위치만 확인해 멀리 떨어진 위치 쌍을 놓친다.
     */
}

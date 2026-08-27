package bridge.hash.problem;

//region 문제: 순서가 달라도 같은 구성의 연속 구간 찾기
/*
 이 문제에서 연습할 것
 고정된 길이의 창을 한 칸씩 옮기며 빠지는 값과 들어오는 값의 개수를 갱신한다.

 문제 설명
 stream에서 pattern과 길이가 같은 연속 구간을 찾는다.
 구간 안의 값 순서는 달라도 되지만 각 값이 나온 횟수는 pattern과 정확히 같아야 한다.
 조건을 만족하는 모든 구간의 시작 위치를 1부터 세어 배열로 반환한다.
 시작 위치가 없으면 빈 배열을 반환한다.
 */
//endregion

//region 입력과 출력
/*
 - 입력: 전체 값 배열 stream, 찾아야 할 값 구성 pattern
 - 출력: 같은 값 구성을 가진 연속 구간의 1부터 센 시작 위치 배열
 */
//endregion

//region 입출력 예시
/*
 stream = [2, 1, 2, 3, 2, 2, 1]
 pattern = [1, 2, 2]
 결과 = [1, 5]
 */
//endregion

//region 코드를 쓰기 전에 생각할 질문
/*
 - 값의 순서가 아니라 어떤 정보가 같아야 하는가?
 - 첫 번째 창의 길이는 무엇으로 정해야 하는가?
 - 창을 한 칸 옮길 때 빠지는 값과 새로 들어오는 값은 각각 어디에 있는가?
 - 개수가 0이 된 이름표를 Map에 남기면 두 개수표가 같다고 판단할 수 있는가?
 - 마지막으로 확인해야 할 창의 시작 위치는 어디인가?
 */
//endregion

public final class HashProblem03 {

    private HashProblem03() {
        solve(new int[]{2, 1, 2, 3, 2, 2, 1}, new int[]{1, 2, 2});
        // 예상 출력: [1, 5]
    }

    //region 먼저 직접 생각한 뒤 확인할 접근 방식
    /*
     pattern과 첫 창의 값별 개수를 만들고, 창을 옮길 때 빠지는 값은 줄이고 들어오는 값은 늘린 뒤, 두 개수표가 같은 시작 위치를 반환한다.
     */
    //endregion

    //region 제약 조건
    /*
     - 0 <= stream.length <= 100_000
     - 1 <= pattern.length <= 1_000
     - -1_000 <= stream[i], pattern[i] <= 1_000
     - 입력 배열은 바꾸지 않는다.
     */
    //endregion
    public static int[] solve(int[] stream, int[] pattern) {
        int[] answer = {};
        // 여기에 직접 구현한다.
        return answer;
    }

    //region 막혔을 때만 확인할 단계별 힌트
    /*
     - 첫 창을 만든 뒤에는 맨 앞 값 하나를 빼고 맨 뒤의 새 값 하나를 넣는다.
     - 어떤 값의 개수가 0이 되면 그 key를 지워야 Map.equals로 정확히 비교할 수 있다.
     */
    //endregion
}

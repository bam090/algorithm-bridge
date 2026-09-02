package bridge.twopointer.problem;

//region 문제: 두 정렬 기록의 공통 번호 모으기
/*
 first와 second에는 작은 값부터 정렬된 번호가 들어 있으며 같은 번호가 반복될 수 있다.
 두 기록에 모두 들어 있는 번호만 작은 값부터 새 배열에 담아 반환한다.
 같은 번호는 두 배열에서 나온 횟수 중 작은 횟수만큼 결과에 담는다.
 공통 번호가 없으면 빈 배열을 반환하며 두 입력 배열은 바꾸지 않는다.
 */
//endregion

//region 입력과 출력
/*
 - 입력: 오름차순으로 정렬된 두 정수 배열 first와 second
 - 출력: 두 배열에 함께 남아 있는 횟수만큼 공통 번호를 담은 새 int 배열
 */
//endregion

//region 입출력 예시
/*
 first = [1, 4, 4, 7, 10], second = [2, 4, 4, 4, 7, 9]
 결과 = [4, 4, 7]
 */
//endregion

//region 코드를 쓰기 전에 생각할 질문
/*
 - 두 배열의 아직 확인하지 않은 값 가운데 가장 앞의 값은 어디에 있는가?
 - 두 현재 값 중 한쪽이 작으면 어느 위치를 옮겨야 같은 값을 찾을 수 있는가?
 - 두 값이 같으면 어느 위치를 옮겨야 같은 번호를 다시 확인하지 않는가?
 - 한 배열을 모두 확인한 뒤에도 공통 번호를 더 찾을 수 있는가?
 */
//endregion

public final class TwoPointerProblem01 {

    private TwoPointerProblem01() {
        solve(new int[]{1, 4, 4, 7, 10}, new int[]{2, 4, 4, 4, 7, 9});
        // 예상 출력: [4, 4, 7]
    }

    //region 먼저 직접 생각한 뒤 확인할 접근 방식
    /*
     두 배열의 현재 값을 비교해 작은 값이 있는 위치를 옮기고, 같으면 값을 기록한 뒤 두 위치를 모두 옮겨 공통 번호 배열을 반환한다.
     */
    //endregion

    //region 제약 조건
    /*
     - first와 second는 null이 아니다.
     - 0 <= first.length, second.length <= 100_000
     - -1_000_000_000 <= first[i], second[i] <= 1_000_000_000
     - 각 배열의 값은 오름차순으로 정렬되어 있으며 같은 값이 반복될 수 있다.
     - 입력 배열은 바꾸지 않는다.
     - 결과는 항상 새 배열로 반환한다.
     */
    //endregion
    public static int[] solve(int[] first, int[] second) {
        int[] answer = {};
        // 여기에 직접 구현한다.
        return answer;
    }

    //region 막혔을 때만 확인할 단계별 힌트
    /*
     - 두 위치를 모두 0에서 시작한다.
     - 작은 현재 값은 다른 배열의 현재 값과 같아질 수 없으므로 그 값이 있는 위치만 옮긴다.
     - 공통 번호를 찾으면 두 위치를 함께 옮긴다.
     */
    //endregion
}

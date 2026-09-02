package bridge.array.onedimensional.problem;

//region 문제: 기록의 중앙값 찾기
/*
 홀수 개의 정수 기록 values가 주어진다.
 값을 작은 순서로 놓았을 때 한가운데에 오는 값을 반환하라.
 원본 values는 바꾸면 안 된다.
 */
//endregion

//region 코드를 쓰기 전에 생각할 질문
/*
 - 값이 섞여 있는 상태에서도 배열의 가운데 인덱스만 읽으면 중앙값이 되는가?
 - 정렬할 때 원본 배열을 지키려면 먼저 무엇을 해야 하는가?
 - 길이가 홀수인 배열에서 가운데 인덱스는 어떻게 구하는가?
 - 원소가 하나뿐이면 중앙값은 무엇인가?
 */
//endregion

public final class ArrayProblem08 {

    private ArrayProblem08() {
        solve(new int[]{8, 2, 5, 1, 9});
        // 예상 출력: 5
    }

    //region 먼저 직접 생각한 뒤 확인할 접근 방식
    /*
     원본 배열을 복사한 뒤 오름차순으로 정렬하고, 정렬한 배열의 가운데 값을 반환한다.
     */
    //endregion

    //region 제약 조건
    /*
     - values는 null이 아니며 길이는 1 이상 999 이하인 홀수이다.
     - 각 값은 -1,000 이상 1,000 이하이다.
     - 원본 values는 바꾸면 안 된다.
     */
    //endregion

    // solve 메서드 몸체를 직접 구현한다.
    public static int solve(int[] values) {
        int answer = 0;
        return answer;
    }

    //region 막혔을 때만 확인할 단계별 힌트
    /*
     - values.clone()으로 원본과 값은 같지만 서로 다른 배열을 만들 수 있다.
     - Arrays.sort()는 전달한 배열 자체를 오름차순으로 정렬한다.
     - 홀수 길이 배열의 가운데 인덱스는 length / 2이다.
     */
    //endregion
}

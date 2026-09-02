package bridge.stack.problem;

//region 문제: 거꾸로 찾은 체크포인트 경로
/*
 체크포인트 번호는 1부터 previousCheckpoint.length까지이다.
 previousCheckpoint[i]에는 i + 1번 체크포인트 바로 전에 지나온 체크포인트 번호가 들어 있다.
 출발점인 1번 체크포인트의 이전 번호는 0이다.
 destination에서 이전 번호를 따라가며 찾은 경로를 출발점부터 destination까지의 순서로 반환하라.
 */
//endregion

//region 코드를 쓰기 전에 생각할 질문
/*
 - destination에서 이전 번호를 따라가면 체크포인트를 어떤 순서로 만나게 되는가?
 - 거꾸로 만난 번호를 출발점부터 읽으려면 어디에 저장하면 좋은가?
 - 현재 체크포인트 번호로 previousCheckpoint의 인덱스를 찾으려면 무엇을 빼야 하는가?
 - 필요한 결과 배열의 길이는 언제 알 수 있는가?
 */
//endregion

public final class StackProblem02 {

    private StackProblem02() {
        solve(new int[]{0, 1, 2, 2, 4}, 5);
        // 예상 출력: new int[]{1, 2, 4, 5}
    }

    //region 먼저 직접 생각한 뒤 확인할 접근 방식
    /*
     목적지에서 이전 체크포인트를 따라가며 번호를 스택에 저장한 뒤, 스택에서 꺼내 출발점부터 목적지까지의 경로를 반환한다.
     */
    //endregion

    //region 제약 조건
    /*
     - previousCheckpoint는 null이 아니며 길이는 1 이상 100,000 이하이다.
     - previousCheckpoint[0]은 0이다.
     - i가 1 이상일 때 previousCheckpoint[i]는 1 이상 i 이하이다.
     - 따라서 이전 번호를 따라가면 번호가 계속 작아지고 반드시 1번을 거쳐 0에서 끝난다.
     - destination은 1 이상 previousCheckpoint.length 이하이다.
     - previousCheckpoint는 바꾸면 안 된다.
     */
    //endregion

    // solve 메서드 몸체를 직접 구현한다.
    public static int[] solve(int[] previousCheckpoint, int destination) {
        int[] answer = {};
        return answer;
    }

    //region 막혔을 때만 확인할 단계별 힌트
    /*
     - current가 0이 될 때까지 current를 push하고, previousCheckpoint[current - 1]로 이동한다.
     - 필요한 결과 길이는 모든 체크포인트를 넣은 뒤 stack.size()로 알 수 있다.
     - 스택에서 하나씩 꺼내 결과 배열의 앞에서부터 저장한다.
     */
    //endregion
}

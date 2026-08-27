package bridge.queue.problem;

//region 문제: 두 검사대에서 시료 꺼내기
/*
 서로 다른 시료 번호가 firstLine과 secondLine에 검사대별 대기 순서로 들어 있다.
 검사대 안의 시료 순서는 바꿀 수 없고, 각 검사대의 맨 앞 시료만 꺼낼 수 있다.
 inspectionPlan을 앞에서부터 확인하며 필요한 시료를 꺼내라.

 계획을 모두 처리할 수 있으면 각 시료를 첫 번째 검사대에서 꺼냈을 때 1,
 두 번째 검사대에서 꺼냈을 때 2를 기록한 배열을 반환한다.
 필요한 시료가 두 검사대의 맨 앞에 모두 없으면 new int[]{-1}을 반환한다.
 빈 계획의 결과는 빈 배열이며 원본 배열은 바꾸면 안 된다.
 */
//endregion

//region 코드를 쓰기 전에 생각할 질문
/*
 - 현재 필요한 시료 번호는 inspectionPlan의 어느 위치에서 확인하는가?
 - 각 검사대에서 지금 바로 꺼낼 수 있는 시료는 몇 개인가?
 - 첫 번째 검사대의 맨 앞과 일치하면 결과 배열에 어떤 값을 기록해야 하는가?
 - 두 검사대의 맨 앞이 모두 필요한 시료가 아니라면 부분 결과를 반환해도 되는가?
 */
//endregion

public final class QueueProblem04 {

    private QueueProblem04() {
        solve(
                new int[]{11, 13, 17},
                new int[]{20, 22},
                new int[]{11, 20, 22, 13}
        );
        // 예상 출력: new int[]{1, 2, 2, 1}
    }

    //region 먼저 직접 생각한 뒤 확인할 접근 방식
    /*
     계획의 다음 번호와 같은 큐의 맨 앞 시료를 꺼내 출처를 기록하고, 둘 다 다르면 실패 결과를 반환한다.
     */
    //endregion

    //region 제약 조건
    /*
     - firstLine, secondLine과 inspectionPlan은 null이 아니다.
     - 세 배열의 길이는 각각 0 이상 100,000 이하이다.
     - firstLine.length + secondLine.length는 100,000 이하이다.
     - inspectionPlan.length는 firstLine.length + secondLine.length 이하이다.
     - 모든 시료 번호는 -1,000,000 이상 1,000,000 이하이다.
     - firstLine과 secondLine에 들어 있는 시료 번호는 두 배열 전체에서 서로 다르다.
     - inspectionPlan에는 검사대에 없는 번호가 들어 있을 수 있다.
     - 원본 배열은 바꾸지 않는다.
     */
    //endregion

    // solve 메서드 몸체를 직접 구현한다.
    public static int[] solve(int[] firstLine, int[] secondLine, int[] inspectionPlan) {
        int[] answer = {};
        return answer;
    }
}

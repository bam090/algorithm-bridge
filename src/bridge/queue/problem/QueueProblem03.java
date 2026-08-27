package bridge.queue.problem;

//region 문제: 주문의 수령 회차 정하기
/*
 주문은 배열에 적힌 순서를 지켜 손님에게 건네야 한다.
 i번째 주문의 준비 시각은 requestedMinutes[i] + preparationMinutes[i]다.

 아직 건네지 않은 맨 앞 주문이 준비되는 시각에 새 수령 회차를 시작한다.
 그 뒤에 연속해서 놓인 주문 중 준비 시각이 이번 수령 시각보다 빠르거나 같은 주문은
 같은 회차에 함께 건넨다. 더 늦게 준비되는 첫 주문은 다음 회차를 시작한다.
 각 주문이 몇 번째 수령 회차에 속하는지 1부터 세어 배열로 반환하라.
 */
//endregion

//region 코드를 쓰기 전에 생각할 질문
/*
 - 주문 하나의 준비 시각은 어떤 두 값을 더해서 구하는가?
 - 뒤 주문이 먼저 준비되어도 맨 앞 주문보다 먼저 건넬 수 있는가?
 - 한 회차에 함께 들어올 수 있는지 비교할 기준 시각은 어느 주문이 정하는가?
 - 결과 배열의 같은 인덱스에는 무엇을 기록해야 하는가?
 */
//endregion

public final class QueueProblem03 {

    private QueueProblem03() {
        solve(
                new int[]{0, 2, 4, 6, 8},
                new int[]{10, 3, 8, 2, 1}
        );
        // 예상 출력: new int[]{1, 1, 2, 2, 2}
    }

    //region 먼저 직접 생각한 뒤 확인할 접근 방식
    /*
     주문별 준비 시각을 큐에 넣고, 맨 앞 준비 시각을 회차 기준으로 삼아 그 시각 이하인 연속 주문에 같은 회차 번호를 기록한다.
     */
    //endregion

    //region 제약 조건
    /*
     - requestedMinutes와 preparationMinutes는 null이 아니며 길이가 같다.
     - 두 배열의 길이는 0 이상 100,000 이하이다.
     - requestedMinutes의 각 값은 0 이상 1,000,000 이하이며 앞에서부터 작아지지 않는다.
     - preparationMinutes의 각 값은 0 이상 1,000,000 이하이다.
     - 준비 시각의 합은 int 범위 안에 있다.
     - 원본 배열은 바꾸지 않는다.
     */
    //endregion

    // solve 메서드 몸체를 직접 구현한다.
    public static int[] solve(int[] requestedMinutes, int[] preparationMinutes) {
        int[] answer = {};
        return answer;
    }

    //region 막혔을 때만 확인할 단계별 힌트
    /*
     - 아직 건네지 않은 맨 앞 준비 시각을 poll()하면 새 회차의 기준 시각을 얻을 수 있다.
     */
    //endregion
}

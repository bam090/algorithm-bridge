package bridge.greedy.problem;

//region 문제: 오류 출처 묶음 고르기
/*
 sourceLabels에는 오류 기록이 발생한 출처 이름이 하나씩 들어 있다.
 한 출처를 조사하기 시작하면 그 출처의 오류 기록을 모두 함께 조사한다.
 최대 sourceLimit개의 출처만 조사해 적어도 targetRecords개의 기록을 확인하려 한다.
 목표에 도달하면 실제로 고른 출처 이름을 선택 순서대로 반환하고,
 출처 수 제한 안에서 목표에 도달할 수 없으면 빈 배열을 반환하라.

 기록 수가 많은 출처를 먼저 고르고, 기록 수가 같으면 이름이 사전순으로 빠른 출처를 먼저 고른다.
 targetRecords가 0이면 빈 배열을 반환하며 원본 배열은 바꾸면 안 된다.
 */
//endregion

//region 코드를 쓰기 전에 생각할 질문
/*
 - 출처 하나를 고를 때 몇 개의 기록을 한꺼번에 조사할 수 있는가?
 - 적은 출처로 목표 기록 수에 도달하려면 어떤 출처부터 골라야 하는가?
 - 같은 기록 수를 가진 출처는 어떤 순서로 반환해야 하는가?
 - sourceLimit개의 출처를 골라도 targetRecords에 못 미치면 무엇을 반환해야 하는가?
 */
//endregion

public final class GreedyProblem05 {

    private GreedyProblem05() {
        solve(new String[]{"api", "ui", "api", "db", "api", "ui"}, 4, 2);
        // 예상 출력: new String[]{"api", "ui"}
    }

    //region 먼저 직접 생각한 뒤 확인할 접근 방식
    /*
     출처별 기록 수를 센 뒤 큰 묶음부터 sourceLimit개 안에서 누적하고, 목표에 도달하면 고른 이름을 반환한다.
     */
    //endregion

    //region 제약 조건
    /*
     - sourceLabels는 null이 아니다.
     - sourceLabels의 길이는 0 이상 100,000 이하이다.
     - 각 출처 이름은 null이 아닌 길이 1 이상 20 이하의 영문 소문자 문자열이다.
     - targetRecords는 0 이상 sourceLabels.length 이하이다.
     - sourceLimit는 0 이상 sourceLabels.length 이하이다.
     - 한 출처를 고르면 그 출처의 기록을 모두 조사한다.
     - 기록 수가 같으면 출처 이름의 사전순으로 선택한다.
     - 원본 배열은 바꾸지 않는다.
     */
    //endregion

    // solve 메서드 몸체를 직접 구현한다.
    public static String[] solve(String[] sourceLabels, int targetRecords, int sourceLimit) {
        String[] answer = {};
        return answer;
    }
}

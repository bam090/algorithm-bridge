package bridge.simulation.test;

import bridge.simulation.solution.SimulationSolution06;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class SimulationSolution06Test {

    /*
     * 매개변수 | 허용 범위
     * totalActions | 1 이상 1_000_000_000_000 이하
     * reportInterval | 1 이상 1_000 이하
     */
    @DisplayName("약수 쌍의 보고 간격 조건을 검증한다")
    @ParameterizedTest(name = "{0}")
    @CsvSource(value = {
            "여러 약수 쌍이 추가 조건을 만족함 | 36 | 5 | 2",
            "전체 작업 수 하한 | 1 | 2 | 1",
            "일반 중간값 940 | 940 | 4 | 2",
            "추가 조건을 만족하는 쌍이 없음 | 6 | 4 | 0",
            "보고 간격 상한까지 후보 확인 | 999 | 1000 | 1",
            "소수의 유일한 약수 쌍 | 997 | 499 | 1",
            "전체 작업 수 상한·보고 간격 하한·제곱 쌍 | 1000000000000 | 1 | 85"
    }, delimiter = '|')
    void testFactorPairs(String name, long totalActions, int reportInterval, int expected) {
        assertEquals(expected, SimulationSolution06.solve(totalActions, reportInterval), name);
    }
}

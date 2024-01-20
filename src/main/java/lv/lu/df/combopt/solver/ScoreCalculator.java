package lv.lu.df.combopt.solver;

import ai.timefold.solver.core.api.score.Score;
import ai.timefold.solver.core.api.score.buildin.hardsoft.HardSoftScore;
import ai.timefold.solver.core.api.score.calculator.EasyScoreCalculator;
import lv.lu.df.combopt.domain.TestRun;
import lv.lu.df.combopt.domain.TestingSchedule;

public class ScoreCalculator implements EasyScoreCalculator<TestingSchedule, HardSoftScore> {
    @Override
    public HardSoftScore calculateScore(TestingSchedule testingSchedule) {
        int hard = 0, soft = 0;
        for (TestRun testRun : testingSchedule.getTestRuns()) {
            if (testRun.getTest() == null) {
                soft += -100;
            }
            else {
                soft += testRun.getTest().getAvgRunTime();
            }
        }
        return HardSoftScore.of(hard, soft);
    }
}

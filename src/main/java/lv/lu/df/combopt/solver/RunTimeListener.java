package lv.lu.df.combopt.solver;

import ai.timefold.solver.core.api.domain.variable.VariableListener;
import ai.timefold.solver.core.api.score.director.ScoreDirector;
import lv.lu.df.combopt.domain.TestRun;
import lv.lu.df.combopt.domain.TestingSchedule;

import static java.lang.Math.round;

public class RunTimeListener implements VariableListener<TestingSchedule, TestRun> {

    @Override
    public void beforeVariableChanged(ScoreDirector<TestingSchedule> scoreDirector, TestRun testRun) {

    }

    @Override
    public void afterVariableChanged(ScoreDirector<TestingSchedule> scoreDirector, TestRun testRun) {
        int time = 0;

        if (testRun.getTest() != null)
            time = (int) round(testRun.getTest().getAvgRunTime() / testRun.getDevice().getCompSpeed());

        scoreDirector.beforeVariableChanged(testRun, "runTime");
        testRun.setRunTime(time);
        scoreDirector.afterVariableChanged(testRun, "runTime");
    }

    @Override
    public void beforeEntityAdded(ScoreDirector<TestingSchedule> scoreDirector, TestRun testRun) {

    }

    @Override
    public void afterEntityAdded(ScoreDirector<TestingSchedule> scoreDirector, TestRun testRun) {

    }

    @Override
    public void beforeEntityRemoved(ScoreDirector<TestingSchedule> scoreDirector, TestRun testRun) {

    }

    @Override
    public void afterEntityRemoved(ScoreDirector<TestingSchedule> scoreDirector, TestRun testRun) {

    }
}

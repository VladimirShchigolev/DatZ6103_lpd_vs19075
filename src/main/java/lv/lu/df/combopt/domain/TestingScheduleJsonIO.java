package lv.lu.df.combopt.domain;

import ai.timefold.solver.jackson.impl.domain.solution.JacksonSolutionFileIO;

public class TestingScheduleJsonIO extends JacksonSolutionFileIO<TestingSchedule> {
    public TestingScheduleJsonIO () {
        super(TestingSchedule.class);
    }
}

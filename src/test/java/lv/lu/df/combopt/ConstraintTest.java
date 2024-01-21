package lv.lu.df.combopt;

import ai.timefold.solver.test.api.score.stream.ConstraintVerifier;
import lv.lu.df.combopt.domain.*;
import lv.lu.df.combopt.solver.StreamCalculator;
import org.junit.jupiter.api.Test;

import static java.lang.Math.round;

public class ConstraintTest {
    Platform windows = new Platform();

    Architecture a1 = new Architecture();

    lv.lu.df.combopt.domain.Test t1 = new lv.lu.df.combopt.domain.Test();
    lv.lu.df.combopt.domain.Test t2 = new lv.lu.df.combopt.domain.Test();
    lv.lu.df.combopt.domain.Test t3 = new lv.lu.df.combopt.domain.Test();

    Device win1 = new Device();

    TestRun run1 = new TestRun();

    public ConstraintTest() {
        int timeLimit = 500;
        windows.setName("windows");
        a1.setName("x86");

        t1.setName("CodeStyleCheck");
        t1.setAvgRunTime(560);
        t1.getPlatforms().add(windows);

        t2.setName("CheckIfCompiles");
        t2.setAvgRunTime(300);
        t2.getPlatforms().add(windows);

        t3.setName("RunHelloWorldSample");
        t3.setAvgRunTime(60);
        t3.getPlatforms().add(windows);

        win1.setName("win-1-x86_64");
        win1.setCompSpeed(1.0);
        win1.setTimeLimit(timeLimit);
        win1.getPlatforms().add(windows);
        win1.getArchitectures().add(a1);

        run1.setTest(t1);
        run1.setDevice(win1);
        run1.setArchitecture(a1);
        run1.setPlatform(windows);
        run1.setRunTime((int) round(t1.getAvgRunTime() / win1.getCompSpeed()));
    }

    ConstraintVerifier<StreamCalculator, TestingSchedule> constraintVerifier = ConstraintVerifier.build(
            new StreamCalculator(), TestingSchedule.class,
            TestRun.class, lv.lu.df.combopt.domain.Test.class, Device.class);

    @Test
    void timeExceedTest() {
        constraintVerifier.verifyThat(StreamCalculator::timeLimitExceed)
                .given(run1)
                .penalizesBy(60);
    }

}

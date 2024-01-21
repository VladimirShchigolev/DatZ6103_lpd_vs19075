package lv.lu.df.combopt.domain;

import ai.timefold.solver.core.api.domain.solution.PlanningEntityCollectionProperty;
import ai.timefold.solver.core.api.domain.solution.PlanningScore;
import ai.timefold.solver.core.api.domain.solution.PlanningSolution;
import ai.timefold.solver.core.api.domain.solution.ProblemFactCollectionProperty;
import ai.timefold.solver.core.api.domain.valuerange.ValueRangeProvider;
import ai.timefold.solver.core.api.score.buildin.hardmediumsoft.HardMediumSoftScore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.lang.Math.max;

@PlanningSolution
@Getter @Setter @NoArgsConstructor
public class TestingSchedule {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestingSchedule.class);

    private String solutionId;
    private Integer timeLimit;
    @PlanningScore
    private HardMediumSoftScore score;

    @PlanningEntityCollectionProperty
    private List<TestRun> testRuns = new ArrayList<>();

    @ProblemFactCollectionProperty
    @ValueRangeProvider(id = "tests")
    private List<Test> tests = new ArrayList<>();

    @ProblemFactCollectionProperty
    @ValueRangeProvider(id = "devices")
    private List<Device> devices = new ArrayList<>();

    @ProblemFactCollectionProperty
    @ValueRangeProvider(id = "platforms")
    private List<Platform> platforms = new ArrayList<>();

    @ProblemFactCollectionProperty
    @ValueRangeProvider(id = "architectures")
    private List<Architecture> architectures = new ArrayList<>();

    public void print() {
        this.getTestRuns().forEach(testRun -> {
            LOGGER.info(testRun.toString());
        });
    }

    public void preprocess() {
        // Find out number of architectures per test
        for (Test test : this.getTests()) {
            Set<Architecture> architectureSet = new HashSet<Architecture>();
            for (Device device : this.getDevices()) {
                Boolean device_approved = false;
                for (Platform platform : device.getPlatforms()) {
                    if (test.getPlatforms().contains(platform)) {
                        device_approved = true;
                        break;
                    }
                }
                if (device_approved) {
                    architectureSet.addAll(device.getArchitectures());
                }
            }
            test.setArchitectureCount(architectureSet.size());
        }

        // Find out maximum possible test runs
        int n = 0;
        for (Test test : this.getTests()) {
            n += max(test.getArchitectureCount(), test.getPlatforms().size());
        }

        for (Integer i = 1; i <= n; i++) {
            TestRun testRun = new TestRun();
            testRun.setId("run#"+i.toString());
            this.getTestRuns().add(testRun);
        }
    }

    public static TestingSchedule generateData() {
        TestingSchedule problem = new TestingSchedule();
        problem.setSolutionId("P1");

        Integer timeLimit = 560;

        Platform windows = new Platform();
        windows.setName("windows");

        Platform linux = new Platform();
        linux.setName("linux");

        Architecture a1 = new Architecture();
        a1.setName("x86");

        Architecture a2 = new Architecture();
        a2.setName("x86_64");

        Test t1 = new Test();
        t1.setName("CodeStyleCheck");
        t1.setAvgRunTime(200);
        t1.getPlatforms().addAll(List.of(windows, linux));

        Test t2 = new Test();
        t2.setName("CheckIfCompiles");
        t2.setAvgRunTime(300);
        t2.getPlatforms().addAll(List.of(windows, linux));

        Test t3 = new Test();
        t3.setName("RunHelloWorldSample");
        t3.setAvgRunTime(60);
        t3.getPlatforms().addAll(List.of(windows, linux));

        Device win1 = new Device();
        win1.setName("win-1-x86_64");
        win1.setCompSpeed(1.0);
        win1.setTimeLimit(timeLimit);
        win1.getPlatforms().add(windows);
        win1.getArchitectures().addAll(List.of(a1, a2));

        Device linux1 = new Device();
        linux1.setName("linux-1-x86_64");
        linux1.setCompSpeed(1.2);
        linux1.setTimeLimit(timeLimit);
        linux1.getPlatforms().add(linux);
        linux1.getArchitectures().addAll(List.of(a1, a2));

        problem.getArchitectures().addAll(List.of(a1, a2));
        problem.getPlatforms().addAll(List.of(windows, linux));
        problem.getDevices().addAll(List.of(win1, linux1));
        problem.getTests().addAll(List.of(t1, t2, t3));

        problem.preprocess();

        return problem;
    }
}

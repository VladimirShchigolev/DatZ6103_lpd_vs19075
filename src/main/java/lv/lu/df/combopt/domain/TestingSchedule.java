package lv.lu.df.combopt.domain;

import ai.timefold.solver.core.api.domain.solution.PlanningEntityCollectionProperty;
import ai.timefold.solver.core.api.domain.solution.PlanningScore;
import ai.timefold.solver.core.api.domain.solution.PlanningSolution;
import ai.timefold.solver.core.api.domain.solution.ProblemFactCollectionProperty;
import ai.timefold.solver.core.api.domain.valuerange.ValueRangeProvider;
import ai.timefold.solver.core.api.score.buildin.hardsoft.HardSoftScore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lv.lu.df.combopt.Main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.Math.max;

@PlanningSolution
@Getter @Setter @NoArgsConstructor
public class TestingSchedule {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestingSchedule.class);

    private String solutionId;
    private Integer timeLimit;
    @PlanningScore
    private HardSoftScore score;

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
        /*
        this.getTests().forEach(test -> {
            LOGGER.info(test.getName() + "(" + test.getAvgRunTime() + ") - {"
                    + test.getPlatforms().stream().map(Platform::getName)
                    .collect(Collectors.joining(", ")) + "}");
        });*/
        this.getTestRuns().forEach(testRun -> {
            LOGGER.info(testRun.getId());
            if (testRun.getTest() != null) {
                LOGGER.info(testRun.getId() + "{" + testRun.getDevice().getName() + ", " +
                        testRun.getTest().getName() + ", " + testRun.getPlatform().getName() + ", " +
                        testRun.getArchitecture().getName() + "}");
            }
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

        for (Integer i = 0; i < n; i++) {
            TestRun testRun = new TestRun();
            testRun.setId("run#"+i.toString());
            this.getTestRuns().add(testRun);
        }
    }

    public static TestingSchedule generateData() {
        TestingSchedule problem = new TestingSchedule();
        problem.setSolutionId("P1");

        Integer timeLimit = 600;

        Platform p1 = new Platform();
        p1.setName("windows");

        Architecture a1 = new Architecture();
        a1.setName("x86");

        Architecture a2 = new Architecture();
        a2.setName("x86_64");

        Test t1 = new Test();
        t1.setName("CodeStyle");
        t1.setAvgRunTime(60);
        t1.getPlatforms().add(p1);

        Test t2 = new Test();
        t2.setName("CheckIfCompiles");
        t2.setAvgRunTime(300);
        t2.getPlatforms().add(p1);

        Device d1 = new Device();
        d1.setName("win-1-x84_64");
        d1.setCompSpeed(1.0);
        d1.setTimeLimit(timeLimit);
        d1.getPlatforms().add(p1);
        d1.getArchitectures().addAll(List.of(a1, a2));

        problem.getArchitectures().addAll(List.of(a1, a2));
        problem.getPlatforms().add(p1);
        problem.getDevices().add(d1);
        problem.getTests().addAll(List.of(t1, t2));

        problem.preprocess();

        return problem;
    }
}

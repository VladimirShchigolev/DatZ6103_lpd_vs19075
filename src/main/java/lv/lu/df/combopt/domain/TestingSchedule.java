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

import java.util.*;

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
        LOGGER.info("Time Limit: " + timeLimit);
        this.getDevices().forEach(device -> {
            LOGGER.info(device.toStringDetailed());
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

    public static TestingSchedule generateData(int scale) {
        int timeLimit = scale * 300;
        int deviceCount = max(scale / 2, 5);
        int testCount = scale;

        Random random = new Random();

        TestingSchedule problem = new TestingSchedule();

        String[] platformNames = {"windows", "linux", "osx", "ios", "android"};
        for (String platformName : platformNames) {
            Platform p = new Platform();
            p.setName(platformName);
            problem.getPlatforms().add(p);
        }

        String [] archNames = {"x86_64", "x86", "arm", "thumb", "arm64", "arm64ec", "mips"};
        for (String archName : archNames) {
            Architecture a = new Architecture();
            a.setName(archName);
            problem.getArchitectures().add(a);
        }


        for (Integer testNo = 1; testNo <= testCount; testNo++) {
            Test t = new Test();
            t.setName("Test#" + testNo.toString());
            t.setAvgRunTime(200 + random.nextInt(400) - 100);
            for (Platform p : problem.getPlatforms()) {
                if (random.nextInt(3) == 0) {
                    t.getPlatforms().add(p);
                }
            }
            if (t.getPlatforms().size() == 0) {
                int idx = random.nextInt(problem.getPlatforms().size());
                t.getPlatforms().add(problem.getPlatforms().get(idx));
            }

            problem.getTests().add(t);
        }

        for (Integer deviceNo = 1; deviceNo <= deviceCount; deviceNo++) {
            Device d = new Device();
            Platform p;
            if (deviceNo <= 5) {
                p = problem.getPlatforms().get(deviceNo-1);
            }
            else {
                p = problem.getPlatforms().get(random.nextInt(problem.getPlatforms().size()));
            }
            d.getPlatforms().add(p);
            if ((p.getName().equals("windows") || p.getName().equals("linux")) && random.nextInt(2)==1) {
                d.getPlatforms().add(problem.getPlatforms().get(4));
            }
            else if (p.getName().equals("osx") && random.nextInt(2)==1) {
                d.getPlatforms().add(problem.getPlatforms().get(3));
            }

            Architecture a = problem.getArchitectures().get(random.nextInt(problem.getArchitectures().size()));
            d.getArchitectures().add(a);
            if (a.getName().equals("x86_64")) {
                d.getArchitectures().add(problem.getArchitectures().get(1));
            }
            else if (a.getName().equals("arm64")) {
                if (random.nextInt(2)==1) {
                    d.getArchitectures().add(problem.getArchitectures().get(2));
                }
                if (random.nextInt(2)==1) {
                    d.getArchitectures().add(problem.getArchitectures().get(5));
                }
            }
            else if (a.getName().equals("thumb") && random.nextInt(2)==1) {
                d.getArchitectures().add(problem.getArchitectures().get(2));
            }

            d.setName(p.getName() + "-" + deviceNo + "-" + a.getName());
            d.setTimeLimit(timeLimit);
            d.setCompSpeed(0.5 + random.nextDouble());

            problem.getDevices().add(d);
        }
        problem.setTimeLimit(timeLimit);
        problem.preprocess();

        return problem;
    }
}

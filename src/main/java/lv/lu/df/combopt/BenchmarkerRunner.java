package lv.lu.df.combopt;

import ai.timefold.solver.benchmark.api.PlannerBenchmark;
import ai.timefold.solver.benchmark.api.PlannerBenchmarkFactory;
import lv.lu.df.combopt.domain.Test;
import lv.lu.df.combopt.domain.TestRun;
import lv.lu.df.combopt.domain.TestingSchedule;
import lv.lu.df.combopt.domain.TestingScheduleJsonIO;

import java.io.File;

public class BenchmarkerRunner {
    public static void main(String[] args) {
        PlannerBenchmarkFactory benchmarkFactory = PlannerBenchmarkFactory
                .createFromXmlResource("BenchmarkConfig.xml");

        //TestingSchedule problem = TestingSchedule.generateData(20);

        /*
        TestingScheduleJsonIO testingScheduleJsonIO = new TestingScheduleJsonIO();

        testingScheduleJsonIO.write(TestingSchedule.generateData(5),
                new File("data/classExample5.json"));
        testingScheduleJsonIO.write(TestingSchedule.generateData(10),
                new File("data/classExample10.json"));
        testingScheduleJsonIO.write(TestingSchedule.generateData(15),
                new File("data/classExample15.json"));
        testingScheduleJsonIO.write(TestingSchedule.generateData(25),
                new File("data/classExample25.json"));
        testingScheduleJsonIO.write(TestingSchedule.generateData(35),
                new File("data/classExample35.json"));
        */

        PlannerBenchmark benchmark = benchmarkFactory.buildPlannerBenchmark();
        benchmark.benchmarkAndShowReportInBrowser();
    }
}

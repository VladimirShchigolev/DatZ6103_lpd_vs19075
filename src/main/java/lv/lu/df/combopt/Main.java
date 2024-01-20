package lv.lu.df.combopt;


import ai.timefold.solver.core.api.solver.Solver;
import ai.timefold.solver.core.api.solver.SolverFactory;
import ai.timefold.solver.core.config.solver.SolverConfig;
import ai.timefold.solver.core.config.solver.termination.TerminationConfig;
import lv.lu.df.combopt.domain.*;
import lv.lu.df.combopt.solver.ScoreCalculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {

        TestingSchedule problem = TestingSchedule.generateData();
        problem.print();

        SolverFactory<TestingSchedule> solverFactory = SolverFactory.create(
                new SolverConfig()
                        .withSolutionClass(TestingSchedule.class)
                        .withEntityClasses(TestRun.class)
                        .withEasyScoreCalculatorClass(ScoreCalculator.class)
                        .withTerminationConfig(new TerminationConfig()
                                .withSecondsSpentLimit(10L))
        );

        Solver<TestingSchedule> solver = solverFactory.buildSolver();
        TestingSchedule solution = solver.solve(problem);

        solution.print();
    }


}
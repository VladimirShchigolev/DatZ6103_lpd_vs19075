package lv.lu.df.combopt;


import ai.timefold.solver.core.api.score.ScoreExplanation;
import ai.timefold.solver.core.api.score.buildin.hardmediumsoft.HardMediumSoftScore;
import ai.timefold.solver.core.api.score.buildin.hardsoft.HardSoftScore;
import ai.timefold.solver.core.api.solver.SolutionManager;
import ai.timefold.solver.core.api.solver.Solver;
import ai.timefold.solver.core.api.solver.SolverFactory;
import ai.timefold.solver.core.config.solver.EnvironmentMode;
import ai.timefold.solver.core.config.solver.SolverConfig;
import ai.timefold.solver.core.config.solver.termination.TerminationConfig;
import lv.lu.df.combopt.domain.*;
import lv.lu.df.combopt.solver.StreamCalculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {

        TestingSchedule problem = TestingSchedule.generateData();
        problem.print();

        SolverFactory<TestingSchedule> solverFactory = SolverFactory.create(
                new SolverConfig()
                        .withSolutionClass(TestingSchedule.class)
                        .withEntityClasses(TestRun.class, Test.class, Device.class)
                        .withConstraintProviderClass(StreamCalculator.class)
                        .withTerminationConfig(new TerminationConfig()
                                .withSecondsSpentLimit(1L))
                        .withEnvironmentMode(EnvironmentMode.FULL_ASSERT)
        );

        Solver<TestingSchedule> solver = solverFactory.buildSolver();
        TestingSchedule solution = solver.solve(problem);

        SolutionManager<TestingSchedule, HardMediumSoftScore> scoreManager = SolutionManager.create(solverFactory);
        ScoreExplanation<TestingSchedule, HardMediumSoftScore> scoreExplanation = scoreManager.explain(solution);
        LOGGER.info(scoreExplanation.getSummary());

        solution.print();
    }


}
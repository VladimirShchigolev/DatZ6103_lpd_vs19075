package lv.lu.df.combopt;


import ai.timefold.solver.core.api.score.ScoreExplanation;
import ai.timefold.solver.core.api.score.buildin.hardmediumsoft.HardMediumSoftScore;
import ai.timefold.solver.core.api.solver.SolutionManager;
import ai.timefold.solver.core.api.solver.Solver;
import ai.timefold.solver.core.api.solver.SolverFactory;
import lv.lu.df.combopt.domain.TestingSchedule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {

        TestingSchedule problem = TestingSchedule.generateData(10);
        problem.print();

        SolverFactory<TestingSchedule> solverFactory =
                SolverFactory.createFromXmlResource("SolverConfig.xml");

        Solver<TestingSchedule> solver = solverFactory.buildSolver();
        TestingSchedule solution = solver.solve(problem);

        SolutionManager<TestingSchedule, HardMediumSoftScore> scoreManager = SolutionManager.create(solverFactory);
        ScoreExplanation<TestingSchedule, HardMediumSoftScore> scoreExplanation = scoreManager.explain(solution);
        LOGGER.info(scoreExplanation.getSummary());

        solution.print();
    }


}
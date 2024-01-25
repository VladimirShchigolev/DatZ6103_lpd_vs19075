package lv.lu.df.combopt.rest;

import ai.timefold.solver.core.api.score.analysis.ScoreAnalysis;
import ai.timefold.solver.core.api.score.buildin.hardmediumsoft.HardMediumSoftScore;
import ai.timefold.solver.core.api.score.constraint.Indictment;
import ai.timefold.solver.core.api.solver.SolutionManager;
import ai.timefold.solver.core.api.solver.SolverManager;
import jakarta.annotation.PostConstruct;
import lv.lu.df.combopt.domain.Test;
import lv.lu.df.combopt.domain.TestingSchedule;
import lv.lu.df.combopt.domain.TestingScheduleJsonIO;
import lv.lu.df.combopt.solver.SimpleIndictmentObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/schedules")
public class TestingScheduleController {

    @Autowired
    private SolverManager<TestingSchedule, String> solverManager;

    @Autowired
    private SolutionManager<TestingSchedule, HardMediumSoftScore> solutionManager;

    private Map<String, TestingSchedule> solutionMap = new HashMap<>();

    @SuppressWarnings("removal")
    @PostMapping("/solve")
    public void solve(@RequestBody TestingSchedule problem) {
        solverManager.solveAndListen(
                problem.getSolutionId(), id -> problem, solution -> {
                    solutionMap.put(solution.getSolutionId(), solution);
                }
        );
    }

    @GetMapping("/solution")
    public TestingSchedule solution(@RequestParam String id) {
        return solutionMap.get(id);
    }

    @GetMapping("/list")
    public List<TestingSchedule> list() {
        return solutionMap.values().stream().toList();
    }

    @GetMapping("/score")
    public ScoreAnalysis<HardMediumSoftScore> score(@RequestParam String id) {
        return solutionManager.analyze(solutionMap.getOrDefault(id, null));
    }

    @GetMapping("/indictments")
    public List<SimpleIndictmentObject> indictments(@RequestParam String id) {
        return solutionManager.explain(solutionMap.getOrDefault(id, null)).getIndictmentMap().entrySet().stream()
                .map(entry -> {
                    Indictment<HardMediumSoftScore> indictment = entry.getValue();
                    return
                            new SimpleIndictmentObject(entry.getKey(), // indicted Object
                                    indictment.getScore(),
                                    indictment.getConstraintMatchCount(),
                                    indictment.getConstraintMatchSet());
                }).collect(Collectors.toList());
    }

    @SuppressWarnings("removal")
    @PostConstruct
    public void init() {
        try {
            TestingScheduleJsonIO testingScheduleJsonIO = new TestingScheduleJsonIO();
            TestingSchedule problem25 = testingScheduleJsonIO.read(new File("data/classExample25.json"));
            problem25.setSolutionId("1");
            solverManager.solveAndListen(
                    problem25.getSolutionId(), id -> problem25, solution -> {
                        solutionMap.put(solution.getSolutionId(), solution);
                    }
            );
        }
        catch (Exception e) {};
    }
}

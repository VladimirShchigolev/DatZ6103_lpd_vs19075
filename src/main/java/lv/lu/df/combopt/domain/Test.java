package lv.lu.df.combopt.domain;

import ai.timefold.solver.core.api.domain.entity.PlanningEntity;
import ai.timefold.solver.core.api.domain.variable.InverseRelationShadowVariable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@PlanningEntity
@Getter @Setter @NoArgsConstructor
public class Test {
    private String name;
    private Integer avgRunTime;
    private Integer architectureCount;

    private Set<Platform> platforms = new HashSet<Platform>();

    @InverseRelationShadowVariable(sourceVariableName = "test")
    private List<TestRun> testRuns = new ArrayList<>();

    @Override
    public String toString() {
        return name;
    }
}

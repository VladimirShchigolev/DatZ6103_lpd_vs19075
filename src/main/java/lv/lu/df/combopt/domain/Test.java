package lv.lu.df.combopt.domain;

import ai.timefold.solver.core.api.domain.entity.PlanningEntity;
import ai.timefold.solver.core.api.domain.variable.InverseRelationShadowVariable;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@PlanningEntity
@Getter @Setter @NoArgsConstructor
@JsonIdentityInfo(scope = Test.class, property = "name", generator = ObjectIdGenerators.PropertyGenerator.class)
public class Test {
    private String name;
    private Integer avgRunTime;
    private Integer architectureCount;

    @JsonIdentityReference
    private Set<Platform> platforms = new HashSet<Platform>();

    @InverseRelationShadowVariable(sourceVariableName = "test")
    @JsonIdentityReference
    private List<TestRun> testRuns = new ArrayList<>();

    @Override
    public String toString() {
        return name + " " + platforms.toString();
    }
}

package lv.lu.df.combopt.domain;

import ai.timefold.solver.core.api.domain.entity.PlanningEntity;
import ai.timefold.solver.core.api.domain.variable.InverseRelationShadowVariable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.round;

@PlanningEntity
@Getter @Setter @NoArgsConstructor
public class Device {
    private String name;
    private Double compSpeed;
    private Integer timeLimit;

    private List<Platform> platforms = new ArrayList<>();
    private List<Architecture> architectures = new ArrayList<>();

    @InverseRelationShadowVariable(sourceVariableName = "device")
    private List<TestRun> testRuns = new ArrayList<>();

    @Override
    public String toString() {
        return name;
    }
}

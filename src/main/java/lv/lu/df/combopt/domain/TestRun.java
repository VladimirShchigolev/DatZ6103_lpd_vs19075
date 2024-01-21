package lv.lu.df.combopt.domain;

import ai.timefold.solver.core.api.domain.entity.PlanningEntity;
import ai.timefold.solver.core.api.domain.variable.PlanningVariable;
import ai.timefold.solver.core.api.domain.variable.ShadowVariable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lv.lu.df.combopt.solver.RunTimeListener;

@PlanningEntity
@Getter @Setter @NoArgsConstructor
public class TestRun {
    private String id;
    private Integer startsAt;
    private Integer endsAt;


    @ShadowVariable(variableListenerClass = RunTimeListener.class, sourceVariableName = "device")
    @ShadowVariable(variableListenerClass = RunTimeListener.class, sourceVariableName = "test")
    private Integer runTime = 0;

    @PlanningVariable(valueRangeProviderRefs = "tests", nullable = true)
    private Test test;

    @PlanningVariable(valueRangeProviderRefs = "devices")
    private Device device;

    @PlanningVariable(valueRangeProviderRefs = "platforms")
    private Platform platform;

    @PlanningVariable(valueRangeProviderRefs = "architectures")
    private Architecture architecture;

    @Override
    public String toString() {
        if (test == null) {
            return this.getId() + " - empty";
        }
        return this.getId() + "{" + this.getDevice().getName() + ", " +
                this.getTest().getName() + ", " + this.getPlatform().getName() + ", " +
                this.getArchitecture().getName() + "}";
    }
}

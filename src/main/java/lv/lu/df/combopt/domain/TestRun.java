package lv.lu.df.combopt.domain;

import ai.timefold.solver.core.api.domain.entity.PlanningEntity;
import ai.timefold.solver.core.api.domain.variable.PlanningVariable;
import ai.timefold.solver.core.api.domain.variable.ShadowVariable;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lv.lu.df.combopt.solver.RunTimeListener;

@PlanningEntity
@Getter @Setter @NoArgsConstructor
@JsonIdentityInfo(scope = TestRun.class, property = "id", generator = ObjectIdGenerators.PropertyGenerator.class)
public class TestRun {
    private String id;

    @ShadowVariable(variableListenerClass = RunTimeListener.class, sourceVariableName = "device")
    @ShadowVariable(variableListenerClass = RunTimeListener.class, sourceVariableName = "test")
    private Integer runTime = 0;

    @PlanningVariable(valueRangeProviderRefs = "tests", nullable = true)
    @JsonIdentityReference
    private Test test;

    @PlanningVariable(valueRangeProviderRefs = "devices")
    @JsonIdentityReference
    private Device device;

    @PlanningVariable(valueRangeProviderRefs = "platforms")
    @JsonIdentityReference
    private Platform platform;

    @PlanningVariable(valueRangeProviderRefs = "architectures")
    @JsonIdentityReference
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

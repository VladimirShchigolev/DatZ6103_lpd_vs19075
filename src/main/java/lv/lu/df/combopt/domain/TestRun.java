package lv.lu.df.combopt.domain;

import ai.timefold.solver.core.api.domain.entity.PlanningEntity;
import ai.timefold.solver.core.api.domain.variable.PlanningVariable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@PlanningEntity
@Getter @Setter @NoArgsConstructor
public class TestRun {
    private String id;
    private Integer startsAt;
    private Integer endsAt;

    @PlanningVariable(valueRangeProviderRefs = "tests", nullable = true)
    private Test test;

    @PlanningVariable(valueRangeProviderRefs = "devices")
    private Device device;

    @PlanningVariable(valueRangeProviderRefs = "platforms")
    private Platform platform;

    @PlanningVariable(valueRangeProviderRefs = "architectures")
    private Architecture architecture;
}

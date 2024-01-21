package lv.lu.df.combopt.domain;

import ai.timefold.solver.core.api.domain.entity.PlanningEntity;
import ai.timefold.solver.core.api.domain.variable.InverseRelationShadowVariable;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.round;

@PlanningEntity
@Getter @Setter @NoArgsConstructor
@JsonIdentityInfo(scope = Device.class, property = "name", generator = ObjectIdGenerators.PropertyGenerator.class)
public class Device {
    private String name;
    private Double compSpeed;
    private Integer timeLimit;

    @JsonIdentityReference
    private List<Platform> platforms = new ArrayList<>();
    @JsonIdentityReference
    private List<Architecture> architectures = new ArrayList<>();

    @InverseRelationShadowVariable(sourceVariableName = "device")
    @JsonIdentityReference
    private List<TestRun> testRuns = new ArrayList<>();

    @Override
    public String toString() {
       return name;
    }

    public String toStringDetailed() {
        String res = "Device {" + name + ", platforms: " + platforms.toString() +
                ", architectures: " + architectures.toString() + ", speed: " + compSpeed + "}\n";
        int totalTime = 0;
        for (TestRun testRun : this.getTestRuns()) {
            if (testRun.getTest() != null) {
                res += "\t\t\t\t\t\t\trun Test " + testRun.getTest().toString() + " on " + testRun.getPlatform().getName() + ", " +
                        testRun.getArchitecture().getName() + " in " + testRun.getRunTime() + " s\n";
                totalTime += testRun.getRunTime();
            }
        }
        res += "\t\t\t\t\t\t\tTotal runtime: " + totalTime + "\n";
        return res;
    }
}

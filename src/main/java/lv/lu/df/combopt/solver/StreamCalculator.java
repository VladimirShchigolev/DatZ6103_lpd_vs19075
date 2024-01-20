package lv.lu.df.combopt.solver;

import ai.timefold.solver.core.api.score.buildin.hardsoft.HardSoftScore;
import ai.timefold.solver.core.api.score.stream.Constraint;
import ai.timefold.solver.core.api.score.stream.ConstraintFactory;
import ai.timefold.solver.core.api.score.stream.ConstraintProvider;
import lv.lu.df.combopt.domain.*;

import static ai.timefold.solver.core.api.score.stream.ConstraintCollectors.countDistinct;
import static ai.timefold.solver.core.api.score.stream.Joiners.equal;

public class StreamCalculator implements ConstraintProvider {
    @Override
    public Constraint[] defineConstraints(ConstraintFactory constraintFactory) {
        return new Constraint[] {
            timeLimitExceed(constraintFactory),
            totalArchitectureCover(constraintFactory)
        };
    }

    public Constraint timeLimitExceed(ConstraintFactory constraintFactory) {
        return constraintFactory
                .forEach(Device.class)
                .penalize(HardSoftScore.ONE_HARD, device -> {
                    int time = device.getTotalTime();
                    if (time > device.getTimeLimit())
                        return time - device.getTimeLimit();
                    else
                        return 0;
                })
                .asConstraint("Time Limit Exceeded");
    }

    public Constraint totalArchitectureCover(ConstraintFactory constraintFactory) {
        return constraintFactory
                .forEach(Test.class)
                .reward(HardSoftScore.ONE_SOFT, Test::getArchitectureCover)
                .asConstraint("Test Architecture Cover");
    }
}

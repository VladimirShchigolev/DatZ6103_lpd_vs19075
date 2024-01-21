package lv.lu.df.combopt.solver;

import ai.timefold.solver.core.api.score.buildin.hardmediumsoft.HardMediumSoftScore;
import ai.timefold.solver.core.api.score.stream.Constraint;
import ai.timefold.solver.core.api.score.stream.ConstraintFactory;
import ai.timefold.solver.core.api.score.stream.ConstraintProvider;
import lv.lu.df.combopt.domain.Test;
import lv.lu.df.combopt.domain.TestRun;

import static ai.timefold.solver.core.api.score.stream.ConstraintCollectors.*;

public class StreamCalculator implements ConstraintProvider {
    @Override
    public Constraint[] defineConstraints(ConstraintFactory constraintFactory) {
        return new Constraint[] {
                timeLimitExceed(constraintFactory),
                wrongTestPlatform(constraintFactory),
                wrongDevicePlatform(constraintFactory),
                wrongDeviceArchitecture(constraintFactory),
                uncheckedTest(constraintFactory),
                uncheckedPlatform(constraintFactory),
                minArchitectureCover(constraintFactory),
                totalArchitectureCover(constraintFactory)
        };
    }

    public Constraint timeLimitExceed(ConstraintFactory constraintFactory) {
        return constraintFactory
                .forEach(TestRun.class)
                .groupBy(TestRun::getDevice, sum(TestRun::getRunTime))
                .penalize(HardMediumSoftScore.ONE_HARD,
                        (device, runTime) -> (device.getTimeLimit() < runTime) ? runTime - device.getTimeLimit() : 0)
                .asConstraint("Time Limit Exceeded");
    }

    public Constraint wrongTestPlatform(ConstraintFactory constraintFactory) {
        return constraintFactory
                .forEach(TestRun.class)
                .filter(testRun -> testRun.getTest() != null)
                .filter(testRun -> !testRun.getTest().getPlatforms().contains(testRun.getPlatform()))
                .penalize(HardMediumSoftScore.ONE_HARD)
                .asConstraint("Wrong Test Platform (Test does not support it)");
    }

    public Constraint wrongDevicePlatform(ConstraintFactory constraintFactory) {
        return constraintFactory
                .forEach(TestRun.class)
                .filter(testRun -> !testRun.getDevice().getPlatforms().contains(testRun.getPlatform()))
                .penalize(HardMediumSoftScore.ONE_HARD)
                .asConstraint("Wrong Test Platform (Device does not support it)");
    }

    public Constraint wrongDeviceArchitecture(ConstraintFactory constraintFactory) {
        return constraintFactory
                .forEach(TestRun.class)
                .filter(testRun -> !testRun.getDevice().getArchitectures().contains(testRun.getArchitecture()))
                .penalize(HardMediumSoftScore.ONE_HARD)
                .asConstraint("Wrong Test Architecture (Device does not support it)");
    }

    public Constraint uncheckedTest(ConstraintFactory constraintFactory) {
        return constraintFactory
                .forEach(Test.class)
                .filter(test -> test.getTestRuns().size() == 0)
                .penalize(HardMediumSoftScore.ONE_HARD, test -> test.getPlatforms().size())
                .asConstraint("Unchecked Test (Unchecked all Platforms)");
    }

    public Constraint uncheckedPlatform(ConstraintFactory constraintFactory) {
        return constraintFactory
                .forEach(TestRun.class)
                .filter(testRun -> testRun.getTest() != null)
                .filter(testRun -> testRun.getTest().getPlatforms().contains(testRun.getPlatform()))
                .groupBy(TestRun::getTest, countDistinct(TestRun::getPlatform))
                .penalize(HardMediumSoftScore.ONE_HARD,
                        (test, checkedPlatforms) -> test.getPlatforms().size() - checkedPlatforms)
                .asConstraint("Unchecked Platforms");
    }


    public Constraint minArchitectureCover(ConstraintFactory constraintFactory) {
        return constraintFactory
                .forEach(TestRun.class)
                .groupBy(TestRun::getTest, countDistinct(TestRun::getArchitecture))
                .map((test, archCount) -> archCount * 100 / test.getArchitectureCount())
                .groupBy(a -> "Minimal Architecture Cover", min())
                .reward(HardMediumSoftScore.ONE_MEDIUM, (s, a) -> a)
                .asConstraint("Minimal Architecture Cover");
    }

    public Constraint totalArchitectureCover(ConstraintFactory constraintFactory) {
        return constraintFactory
                .forEach(TestRun.class)
                .groupBy(TestRun::getTest, countDistinct(TestRun::getArchitecture))
                .map((test, count) -> test, (test, count) -> count * 100 / test.getArchitectureCount())
                .reward(HardMediumSoftScore.ONE_SOFT, (test, archCover) -> archCover)
                .asConstraint("Test Architecture Cover");
    }


}

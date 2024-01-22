package lv.lu.df.combopt.solver;

import ai.timefold.solver.core.api.score.buildin.hardmediumsoft.HardMediumSoftScore;
import ai.timefold.solver.core.api.score.buildin.hardsoft.HardSoftScore;
import ai.timefold.solver.core.api.score.constraint.ConstraintMatch;
import lombok.Getter;
import lombok.Setter;
import lv.lu.df.combopt.domain.Test;
import lv.lu.df.combopt.domain.TestRun;
import lv.lu.df.combopt.domain.Device;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Setter @Getter
public class SimpleIndictmentObject {
    private String indictedObjectID;
    private HardMediumSoftScore score;
    private int matchCount;
    private List<SimpleConstraintMatch> constraintMatches = new ArrayList<>();

    public SimpleIndictmentObject(Object indictedObject, HardMediumSoftScore score, int matchCount, Set<ConstraintMatch<HardMediumSoftScore>> constraintMatches) {
        if (indictedObject instanceof String)
            this.indictedObjectID = (String) indictedObject;
        else if (indictedObject instanceof Integer)
            this.indictedObjectID = ((Integer) indictedObject).toString();
        else if (indictedObject instanceof TestRun)
            this.indictedObjectID = ((TestRun) indictedObject).getId();
        else if (indictedObject instanceof Test)
            this.indictedObjectID = ((Test) indictedObject).getName();
        else
            this.indictedObjectID = ((Device) indictedObject).getName();

        this.score = score;
        this.matchCount = matchCount;
        this.constraintMatches = constraintMatches.stream().map(constraintMatch -> {
            return new SimpleConstraintMatch(constraintMatch);
        }).collect(Collectors.toList());
    }
}
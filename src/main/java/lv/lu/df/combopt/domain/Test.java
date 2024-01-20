package lv.lu.df.combopt.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter @Setter @NoArgsConstructor
public class Test {
    private String name;
    private Integer avgRunTime;
    private Integer architectureCount;

    private
    Set<Platform> platforms = new HashSet<Platform>();
}

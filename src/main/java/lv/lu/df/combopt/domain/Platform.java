package lv.lu.df.combopt.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class Platform {
    private String name;

    @Override
    public String toString() {
        return name;
    }
}

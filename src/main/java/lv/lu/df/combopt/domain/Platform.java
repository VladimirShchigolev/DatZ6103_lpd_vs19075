package lv.lu.df.combopt.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
@JsonIdentityInfo(scope = Platform.class, property = "name", generator = ObjectIdGenerators.PropertyGenerator.class)
public class Platform {
    private String name;

    @Override
    public String toString() {
        return name;
    }
}

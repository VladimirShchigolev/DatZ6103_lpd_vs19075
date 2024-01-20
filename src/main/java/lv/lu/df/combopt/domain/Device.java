package lv.lu.df.combopt.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter @NoArgsConstructor
public class Device {
    private String name;
    private Double compSpeed;

    private List<Platform> platforms = new ArrayList<>();
    private List<Architecture> architectures = new ArrayList<>();
}

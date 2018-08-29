package com.andreitop.xml.unit;

import com.andreitop.xml.mount.Mount;
import com.andreitop.xml.mount.Wolf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Stream;

@Component("zulJin")
public class Troll implements Unit {
    private List<Mount> listOfMounts;
    private Set<Mount> setOfMounts;
    private Map<String, Mount> mapOfMounts;
    private Date creationDate;
    public static final Mount DEFAULT_MOUNT = new Wolf();
    private int colorCode;

    public int getColorCode() {
        return colorCode;
    }

    @Autowired
    public void setColorCode(@Value("#{T(java.util.concurrent.ThreadLocalRandom).current().nextInt(2,10)}") int colorCode) {
        this.colorCode = colorCode;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    @Autowired
    public void setCreationDate( Date creationDate) {
        this.creationDate = creationDate;
    }

    @Autowired
    public void setListOfMounts(@Qualifier("listOfMounts") List<Mount> listOfMounts) {
        this.listOfMounts = listOfMounts;
    }

    @Autowired
    public void setSetOfMounts(Set<Mount> setOfMounts) {
        this.setOfMounts = setOfMounts;
    }

    @Autowired
    public void setMapOfMounts(@Qualifier("trollMountMap") Map<String, Mount> mapOfMounts) {
        this.mapOfMounts = mapOfMounts;
    }

    public List<Mount> getListOfMounts() {
        return listOfMounts;
    }

    public Set<Mount> getSetOfMounts() {
        return setOfMounts;
    }

    public Map<String, Mount> getMapOfMounts() {
        return mapOfMounts;
    }

    @Override
    public void mountMove() {
        mapOfMounts.forEach((k, v) -> {
            System.out.print("key=" + k + " ");
            v.move();
        });
        Stream.of(listOfMounts,setOfMounts)
                .flatMap(Collection::stream)
                .filter(Objects::nonNull)
                .forEach(Mount::move);

    }
}

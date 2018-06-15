package com.zhuoyue.vo;

import java.io.Serializable;
import java.util.List;

/**
 * @author 14258
 */
public class Family implements Serializable {

    private String family;
    private List<FamilyType> familyTypes;


    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public List<FamilyType> getFamilyTypes() {
        return familyTypes;
    }

    public void setFamilyTypes(List<FamilyType> familyTypes) {
        this.familyTypes = familyTypes;
    }

    @Override
    public String toString() {
        return "Family{" +
                "family='" + family + '\'' +
                ", familyTypes=" + familyTypes +
                '}';
    }
}

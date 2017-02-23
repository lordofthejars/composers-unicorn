package org.music.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Composer {

    private String name;
    private Era era;
    private LocalDate birthdate;
    private LocalDate died;

    private List<Opera> operas = new ArrayList<>();

    public Era getEra() {
        return era;
    }

    public void setEra(Era era) {
        this.era = era;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public LocalDate getDied() {
        return died;
    }

    public void setDied(LocalDate died) {
        this.died = died;
    }

    public void addOpera(Opera opera) {
        this.operas.add(opera);
    }

    public List<Opera> getOperas() {
        return operas;
    }

    public void setOperas(List<Opera> operas) {
        this.operas = operas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Composer composer = (Composer) o;

        if (name != null ? !name.equals(composer.name) : composer.name != null) return false;
        if (era != composer.era) return false;
        if (birthdate != null ? !birthdate.equals(composer.birthdate) : composer.birthdate != null) return false;
        if (died != null ? !died.equals(composer.died) : composer.died != null) return false;
        return operas != null ? operas.equals(composer.operas) : composer.operas == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (era != null ? era.hashCode() : 0);
        result = 31 * result + (birthdate != null ? birthdate.hashCode() : 0);
        result = 31 * result + (died != null ? died.hashCode() : 0);
        result = 31 * result + (operas != null ? operas.hashCode() : 0);
        return result;
    }
}

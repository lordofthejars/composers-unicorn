package org.music.entity;

import java.util.HashSet;
import java.util.Set;

public class Opera {

    private String name;
    private String librettist;
    private Language language;

    private Set<String> roles = new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLibrettist() {
        return librettist;
    }

    public void setLibrettist(String librettist) {
        this.librettist = librettist;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public void addRole(String role) {
        this.roles.add(role);
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Opera opera = (Opera) o;

        if (name != null ? !name.equals(opera.name) : opera.name != null) return false;
        if (librettist != null ? !librettist.equals(opera.librettist) : opera.librettist != null) return false;
        if (language != opera.language) return false;
        return roles != null ? roles.equals(opera.roles) : opera.roles == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (librettist != null ? librettist.hashCode() : 0);
        result = 31 * result + (language != null ? language.hashCode() : 0);
        result = 31 * result + (roles != null ? roles.hashCode() : 0);
        return result;
    }
}

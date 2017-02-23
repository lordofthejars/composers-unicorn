package org.music.entity;

import java.util.List;

public class Composer {

    private String name;

    private List<Opera> operas;

    public String getName() {
        return name;
    }

    public List<Opera> getOperas() {
        return operas;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Composer{");
        sb.append("name='").append(name).append('\'');
        sb.append(", operas=").append(operas);
        sb.append('}');
        return sb.toString();
    }
}

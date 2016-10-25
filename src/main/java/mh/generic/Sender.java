package mh.generic;

import mh.generic.item.Id;

import java.util.Set;

public class Sender {

    public int send(Set<? extends Id> items) {
        int result = 0;
        for(Id item : items) {
            result += item.getId();
        }
        return result;
    }

}

package mh.generic.item;

public class BaseId implements Id {

    private final int id;

    public BaseId(final int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BaseId)) {
            return false;
        }
        BaseId baseId = (BaseId) o;
        return id == baseId.id;

    }

    @Override
    public int hashCode() {
        return id;
    }

}

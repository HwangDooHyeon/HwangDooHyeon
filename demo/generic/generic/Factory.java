package generic;

import generic.animal.Animal;

public class Factory<T extends Animal> {

    private Class<T> type;

    public Factory(Class<T> type) {
        this.type = type;
    }

    public T creat() {
        try {
            return type.newInstance();
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }


}

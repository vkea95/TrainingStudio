package core.collections;

import java.util.Iterator;
import java.util.List;

public class Animalterator<String> implements Iterator<String> {

    private List<String> animal;
    int index = 0;

    public Animalterator(Animal animal) {
        this.animal = animal.getAnimalList();
    }

    @Override
    public boolean hasNext() {

        return index < animal.size();
    }

    @Override
    public String next() {
        return animal.get(index++);

    }
}

package core.collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Animal<String> implements Iterable<String> {
    private List<String> animalList = new ArrayList<String>();


    public Animal(List<String> animalList) {
        this.animalList = animalList;
    }

    @Override
    public Iterator<String> iterator() {
        return new Animalterator(this);
    }

    public List<String> getAnimalList() {
        return animalList;
    }
}

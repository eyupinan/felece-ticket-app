package org.felecechallenge.ticket.facade.converter;

import org.felecechallenge.ticket.facade.populator.Populator;

import java.util.ArrayList;
import java.util.List;

public class Converter<SOURCE, TARGET>{
    private Class<TARGET> targetClass;
    private List<Populator> populators = new ArrayList<>();
    public Converter(Class<TARGET> targetClass) {
        this.targetClass = targetClass;
    }

    public TARGET convert(SOURCE source) {
        TARGET target= createTargetClass();
        for (Populator populator : populators) {
            populator.populate(source, target);
        }
        return target;
    }
    public List<TARGET> convertAll(List<SOURCE> sourceList) {
        List<TARGET> convertedList = new ArrayList<>();
        for (SOURCE source : sourceList) {
            convertedList.add(convert(source));
        }
        return convertedList;
    }
    public TARGET createTargetClass(){
        try{
            TARGET target = targetClass.newInstance();
            return target;
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    public void addPopulator(Populator populator){
        populators.add(populator);
    }
}

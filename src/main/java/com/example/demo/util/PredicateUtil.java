package com.example.demo.util;

import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import jakarta.persistence.criteria.Predicate;


@Component
public class PredicateUtil {

    private final List<Predicate> predicates=new ArrayList<>();

    private PredicateUtil(){}

    public static PredicateUtil builder(){
        return new PredicateUtil();
    }

    public <T> PredicateUtil addNullSafety(T object, Function<T, Predicate> function){
        if(object!=null){
            predicates.add(function.apply(object));
        }
        return this;
    }

    public <T> PredicateUtil add(T object, Function<T, Predicate> function){
        if(object!=null){
            predicates.add(function.apply(object));
        }
        return this;
    }

    public Predicate[] build(){
        return predicates.toArray(new Predicate[predicates.size()]);
    }

    public static String  applyLikePattern(String key){
        return MessageFormat.format("%{0}%", key);
    }



}



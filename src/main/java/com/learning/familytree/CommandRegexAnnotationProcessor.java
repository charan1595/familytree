package com.learning.familytree;

import com.learning.familytree.annotations.CommandRegex;
import javafx.util.Pair;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandRegexAnnotationProcessor {
    Object obj;
    Class aClass;
    List<Pair<Method, Pattern>> methods;
    public CommandRegexAnnotationProcessor(Object obj) {
        this.obj=obj;
        this.aClass=obj.getClass();
        methods=new ArrayList<>();
        for (Method method : aClass.getMethods()) {
            if(method.isAnnotationPresent(CommandRegex.class)) {
                this.methods.add(new Pair<>(method, Pattern.compile(method.getAnnotation(CommandRegex.class).value())));
            }
        }
    }

    public void parseCommand(String command) {
        methods.forEach(x -> {
            Matcher matcher=x.getValue().matcher(command);
            if(matcher.find()) {
                List<String> params = new ArrayList<>();
                for (int i = 1; i <= matcher.groupCount(); i++) {
                    params.add(matcher.group(i));
                }
//                System.out.print(x.getKey().getName());
//                params.forEach( y -> System.out.printf("+%s",y));
//                System.out.println();
                try {
                    x.getKey().invoke(this.obj,params.toArray());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void printMethods() {
        methods.forEach(x -> {
            System.out.println(x.getKey().getName()+ " " +x.getValue().pattern());
        });
    }
}

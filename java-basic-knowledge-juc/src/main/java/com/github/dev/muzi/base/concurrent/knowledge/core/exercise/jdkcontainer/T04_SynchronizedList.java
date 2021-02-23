package com.github.dev.muzi.base.concurrent.knowledge.core.exercise.jdkcontainer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class T04_SynchronizedList {

    public static void main(String[] args) {
        List<String> strs = new ArrayList<>();
        List<String> stesSync = Collections.synchronizedList(strs);

    }
}

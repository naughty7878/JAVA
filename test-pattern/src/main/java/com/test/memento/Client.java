package com.test.memento;

import java.util.ArrayList;
import java.util.List;

public class Client {

    public static void main(String[] args) {
        Originator originator = new Originator();
        Caretaker caretaker = new Caretaker();
        // 状态1
        originator.setState("状态1");
        System.out.println("初始状态:" + originator.getState());
        caretaker.saveMemento(originator.createMemento());
        // 状态2
        originator.setState("状态2");
        System.out.println("新的状态:" + originator.getState());
        // 恢复状态
        originator.restoreMemento(caretaker.getLastMemento());
        System.out.println("恢复状态:" + originator.getState());
    }
}

// 备忘录
class Memento {
    private String state;

    public Memento(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}

// 发起人
class Originator {
    private String state;

    public Memento createMemento() {
        return new Memento(state);
    }

    public void restoreMemento(Memento m) {
        this.setState(m.getState());
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}

// 管理者
class Caretaker {
    private List<Memento> mementos = new ArrayList<>();

    public Memento getLastMemento() {
        return mementos.get(mementos.size() - 1);
    }

    public void saveMemento(Memento memento) {
        mementos.add(memento);
    }
}
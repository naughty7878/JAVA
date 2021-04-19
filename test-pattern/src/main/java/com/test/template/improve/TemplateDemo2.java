package com.test.template.improve;

public class TemplateDemo2 {

    public static void main(String[] args) {
        // 制作红豆豆浆
        System.out.println("----制作红豆豆浆----");
        SoyaMilk redBeanSoyaMilk = new RedBeanSoyaMilk();
        redBeanSoyaMilk.make();
        System.out.println("----制作花生豆浆----");
        SoyaMilk peanutSoyaMilk = new PeanutSoyaMilk();
        peanutSoyaMilk.make();
    }
}

// 抽象类，表示豆浆
abstract class SoyaMilk {

    // 模板方法，make，模板方法可以做成 final，不让子类去覆盖
    final void make() {
        select();
        if (customerWantCondiments()) {
            addCondiments();
        }
        soak();
        beat();
    }

    // 选材料
    void select() {
        System.out.println("第一步：选择好的新鲜黄豆 ");
    }

    // 添加不同的配料，抽象方法，子类具体实现
    abstract void addCondiments();

    // 浸泡
    void soak() {
        System.out.println("第三步，黄豆和配料开始浸泡，需要 3 小时 ");
    }

    // 打碎
    void beat() {
        System.out.println("第四步：黄豆和配料放到豆浆机去打碎 ");
    }

    // 钩子方法，决定是否需要添加配料
    boolean customerWantCondiments() {
        return true;
    }
}


// 花生豆浆
class PeanutSoyaMilk extends SoyaMilk {

    @Override
    void addCondiments() {
        System.out.println(" 加入上好的花生 ");
    }
}

// 红豆豆浆
class RedBeanSoyaMilk extends SoyaMilk {

    @Override
    void addCondiments() {
        System.out.println(" 加入上好的红豆 ");
    }
}

// 纯豆豆浆
class PureSoyaMilk extends SoyaMilk{
    @Override
    void addCondiments() {
        //空实现
    }

    @Override
    boolean customerWantCondiments() {
        return false;
    }
}
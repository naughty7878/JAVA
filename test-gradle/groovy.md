
// groovy控制台

// 介绍groovy编程语言
println("hello world");

// groovy中如何定义变量
// groovy变量是弱类型
def i = 18;
println(i);

def s = "xiaoming"
println s;

// 定义一个集合
def list = ['a', 'b']
list << 'c'
println list.get(2)

// 定义一个map
def map = ['key1':'value1', 'key2':'value2']
map.key3 = 'value33';
println map.get('key3');

// groovy中的闭包
// 闭包就是一个段代码块，在gradle中，主要是把闭包当参数来使用
def b1 = {
println "hello b1"
}

// 定义一个方法，方法里面需要闭包类型的参数
// Closure 闭包类型
def method1(Closure closure) {
// 运行闭包
closure()
}

// 调用方法
method1(b1)

// 定义带参数的闭包
def b2 = {
v ->
println "hello ${v}"
}
def method2(Closure closure) {
closure("小黑")
}
method2(b2)
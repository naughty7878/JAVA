# Markdown 教程  
<https://www.runoob.com/markdown/md-tutorial.html>

# Markdown 标题
***
一级标题
=
二级标题
- 
# 一级标题
## 二级标题
### 三级标题
#### 四级标题
##### 五级标题
###### 六级标题

# Markdown 段落格式
***
换行演示  
换行演示

重新开始一个段落

重新开始一个段落

## 字体
*斜体文本*
_斜体文本_
**粗体文本**
__粗体文本__
**粗斜体文本**
__粗斜体文本__

## 分割线
***
* * *
*****
---
- - - 
------

## 删除线
删除线
~~删除线~~

## 下划线
下划线
<u>下划线</u>

## 脚注
[^要注明的文本]

# Markdown 列表
***
* 第一项
* 第二项
* 第三项

+ 第一项
+ 第二项
+ 第三项

- 第一项
- 第二项
- 第三项

1. 第一项
2. 第二项
3. 第三项

### 列表嵌套
1. 第一项
    - 第一项嵌套第一个元素
    - 第一项嵌套第二个元素
2. 第二项
    - 第二项嵌套第一个元素
    - 第二项嵌套第二个元素
3. 第三项
    - 第三项嵌套第一个元素
    - 第三项嵌套第二个元素
    
# Markdown 区块
***
> 区块引用  
> 菜鸟教程  
> 学的不仅是技术更是梦想
> 
> 区块引用二

## 嵌套区块
> 最外层
> > 第一层嵌套  
> > > 第二层嵌套  

## 区块中使用列表
> 区块中使用列表
> 1. 第一项
>   - xxx
>   - yyy
> 2. 第二项
> - 第一项
> - 第二项
> - 第三项

## 列表中使用区块
- 第一项
  > 菜鸟教程
  > 学的不仅是技术更是梦想
- 第二项

# Markdown 代码
***
`printf()` 函数

## 代码区块
### 方法1：代码区块使用 4 个空格或者一个制表符（Tab 键）
    public void main(String[] args) {
        System.out.println("hello world");
    }

### 方法2：用 ``` 包裹一段代码，并指定一种语言（也可以不指定）
```java

class Test {
    public void main(String[] args) {
        System.out.println("hello world");
    }
}

```

# Markdown 链接
***
[链接名称](http://www.baidu.com)  
或者  
<http://www.baidu.com>

## 高级链接
这个链接用 1 作为网址变量 [Baidu][1]  
这个链接用 runoob 作为网址变量 [Runoob][runoob]  
然后在文档的结尾为变量赋值（网址）

[1]: http://www.baidu.com/
[runoob]: http://www.runoob.com/

# Markdown 图片
***
![alt 属性文本](http://static.runoob.com/images/runoob-logo.png)  
![RUNOOB 图标](http://static.runoob.com/images/runoob-logo.png)

这个链接用 1 作为网址变 [RUNOOB][1].
然后在文档的结尾为变量赋值（网址）

[1]: http://static.runoob.com/images/runoob-logo.png

<img src="http://static.runoob.com/images/runoob-logo.png" width="30%">

# Markdown 表格
| 表头1 | 表头2 |
| ---- | ---- |
| 单元格1 | 单元格2 |
| 单元格11 | 单元格22 |

| 左对齐 | 居中对齐 | 右对齐 |
| :---- | :----: | ----: |
| 单元格1 | 单元格2 | 单元格3 |
| 单元格11 | 单元格22 | 单元格33 |

# Markdown 高级技巧
## 支持的HTML元素
不在 Markdown 涵盖范围之内的标签，都可以直接在文档里面用 HTML 撰写。  
目前支持的 HTML 元素有：
```html
    <kbd> <b> <i> <em> <sup> <sub> <br>等 
```
如：

使用 <kbd>Ctrl</kbd>+<kbd>Alt</kbd>+<kbd>Del</kbd> 重启电脑

## 转义
Markdown 使用了很多特殊符号来表示特定的意义，如果需要显示特定的符号则需要使用转义字符，Markdown 使用反斜杠转义特殊字符：

**文本加粗**  
\*\*正常显示星号\*\*
> \ 反斜线  
> \` 反引号  
> `*` 星号  
>  _  下划线  
> {}  花括号  
> []  方括号  
> ()  小括号  
> `#` 井字号  
> `+` 加号  
> `-` 减号  
> . 英文句点  
> ! 感叹号
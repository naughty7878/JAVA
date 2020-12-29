package com.test.seri;

import java.io.*;

/**
 * ObjectInputStream和OjbectOutputSteam
 * 用于存储和读取基本数据类型数据或对象的处理流。它的强大之处就是可
 * 以把Java中的对象写入到数据源中，也能把对象从数据源中还原回来。
 */
public class ObjectInputOutputStreamTest {
    public static void main(String[] args) {
        ObjectInputOutputStreamTest test = new ObjectInputOutputStreamTest();
        test.testAnimal();
    }

    /**
     * 序列化:用ObjectOutputStream类保存基本类型数据或对象的机制
     */
    public void testObjectOutputStream(){
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream("object.bat"));
            oos.writeObject(new String("hello world"));
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * 反序列化:用ObjectInputStream类读取基本类型数据或对象的机制
     */
    public void testObjectInputStream(){
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream("object.bat"));
            Object o = ois.readObject();
            System.out.println(o);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if(ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public void testObjectInputOutStream(){
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream("object.bat"));
            oos.writeObject(new Person(12, "小白"));
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream("object.bat"));
            Person p = (Person) ois.readObject();
            System.out.println(p);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if(ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public void testAnimal(){
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream("object2.bat"));
            oos.writeObject(new Animal(12, "小白"));
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream("object2.bat"));
            Animal a = (Animal) ois.readObject();
            System.out.println(a);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if(ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}


class Person implements Serializable{

    public static final long serialVersionUID = 123321;

    transient Integer age;
    static String  name;

    public Person(Integer age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}

class Animal implements Externalizable{

    public static final long serialVersionUID = 12345;

    Integer age;
    String  name;

    public Animal() {
    }

    public Animal(Integer age, String name) {
        this.age = age;
        this.name = name;
    }

    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeInt(age);
        out.writeUTF(name);
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        age = in.readInt();
        name = in.readUTF();
    }


    @Override
    public String toString() {
        return "Animal{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
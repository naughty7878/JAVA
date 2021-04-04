package com.test.disruptor; /**
 * @description disruptor代码样例。每10ms向disruptor中插入一个元素，消费者读取数据，并打印到终端
 */

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.Scanner;
import java.util.concurrent.ThreadFactory;


public class DisruptorMainTest
{
    public static void main(String[] args) throws Exception
    {
        // 队列中的元素
        class Element {

            private int value;

            public int get(){
                return value;
            }

            public void set(int value){
                this.value= value;
            }

        }

        // 生产者的线程工厂
        ThreadFactory threadFactory = new ThreadFactory(){
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "simpleThread");
            }
        };

        // RingBuffer生产工厂,初始化RingBuffer的时候使用
        EventFactory<Element> factory = new EventFactory<Element>() {
            @Override
            public Element newInstance() {
                return new Element();
            }
        };

        // 处理Event的handler
        EventHandler<Element> handler = new EventHandler<Element>(){
            @Override
            public void onEvent(Element element, long sequence, boolean endOfBatch)
            {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Element: " + element.get());
            }
        };
        EventHandler<Element> handler2 = new EventHandler<Element>(){
            @Override
            public void onEvent(Element element, long sequence, boolean endOfBatch)
            {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Element2: " + element.get());
            }
        };

        // 阻塞策略
        BlockingWaitStrategy strategy = new BlockingWaitStrategy();

        // 指定RingBuffer的大小
        int bufferSize = 16;

        // 创建disruptor，采用单生产者模式
        Disruptor<Element> disruptor = new Disruptor(factory, bufferSize, threadFactory, ProducerType.MULTI, strategy);

        // 设置EventHandler
        disruptor.handleEventsWith(handler,handler2);
//        disruptor.handleEventsWith(handler2);

        // 启动disruptor的线程
        disruptor.start();

        RingBuffer<Element> ringBuffer = disruptor.getRingBuffer();

        Scanner scanner = new Scanner(System.in);
        new Thread(() -> {
            for (int l = 1; ; l++)
            {
                // 获取下一个可用位置的下标
                long sequence = ringBuffer.next(5);
                long sequence2 = ringBuffer.next(5);
                try
                {
                    // 返回可用位置的元素
                    Element event = ringBuffer.get(sequence2);
                    Element event1 = ringBuffer.get(sequence2-1);
                    Element event2 = ringBuffer.get(sequence2-2);
                    Element event3 = ringBuffer.get(sequence2-3);
                    Element event4 = ringBuffer.get(sequence2-4);
                    // 设置该位置元素的值
                    event.set(l);
                    event1.set(l + 100);
                    event2.set(l + 200);
                    event3.set(l + 300);
                    event4.set(l + 400);
                }
                finally
                {
                    ringBuffer.publish(sequence2-4, sequence2);
//                    ringBuffer.publish(sequence);
                }
                try
                {
                    // 返回可用位置的元素
                    Element event = ringBuffer.get(sequence);
                    Element event1 = ringBuffer.get(sequence-1);
                    Element event2 = ringBuffer.get(sequence-2);
                    Element event3 = ringBuffer.get(sequence-3);
                    Element event4 = ringBuffer.get(sequence-4);
                    // 设置该位置元素的值
                    event.set(l);
                    event1.set(l + 100);
                    event2.set(l + 200);
                    event3.set(l + 300);
                    event4.set(l + 400);
                }
                finally
                {
//                    ringBuffer.publish(sequence-4, sequence);
                }

                String next = scanner.next();
                System.out.println("next = " + next);
            }
        }).start();

    }
}
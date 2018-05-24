package org.rone.study.java.designPatterns.producerAndConsumer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 生产者消费者模式，MQ消息队列就是典型的案例
 */
public class ProducerAndConsumerTest {

	public static void main(String[] args) {
		int length = 10;
		List<Long> queue = new ArrayList<Long>(10);
		Producter p1 = new Producter(queue, length);
		Producter p2 = new Producter(queue, length);
		Producter p3 = new Producter(queue, length);
		Consumer c1 = new Consumer(queue);
		Consumer c2 = new Consumer(queue);
		Consumer c3 = new Consumer(queue);
		ExecutorService service = Executors.newCachedThreadPool();
		service.execute(p1);
		service.execute(p2);
		service.execute(p3);
		service.execute(c1);
		service.execute(c2);
		service.execute(c3);
	}

}

class Consumer implements Runnable {

	private List<Long> queue;

	public Consumer(List<Long> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		try {
			while (true) {
				if (Thread.currentThread().isInterrupted()) {
					break;
				}
				Long data = null;
				synchronized (queue) {
					if (queue.size() == 0) {
						queue.wait();
						queue.notifyAll();
					}
					data = queue.remove(0);
				}
				System.out.println(Thread.currentThread().getId() + "消费了：" + data);
				Thread.sleep(1000);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}

class Producter implements Runnable {
	private List<Long> queue;
	private int length;

	public Producter(List<Long> queue, int length) {
		this.queue = queue;
		this.length = length;
	}

	@Override
	public void run() {
		try {
			while (true) {
				if (Thread.currentThread().isInterrupted()) {
					break;
				}
				Long temp = (new Random()).nextLong();
				System.out.println(Thread.currentThread().getId() + "生产了：" + temp);
				synchronized (queue) {
					if (queue.size() >= length) {
						queue.notifyAll();
						queue.wait();
					} else {
						queue.add(temp);
					}
					Thread.sleep(1000);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
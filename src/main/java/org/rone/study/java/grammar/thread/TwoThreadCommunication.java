package org.rone.study.java.grammar.thread;

/**
 * 两个线程同输出
 * 12A34B56C...4950Y5152Z
 */
public class TwoThreadCommunication {
	//控制两线程之间通信
	public static int k = 1;
	public static void main(String[] args) {
		Object lock = new Object();
		First first = new First(lock);
		Second second = new Second(lock);
		System.out.println(Thread.activeCount());
		first.start();
		second.start();
		System.out.println(Thread.activeCount());
	}
}

class First extends Thread {
	private Object lock;
	public First(Object lock) {
		super();
		this.lock = lock;
	}
	@Override
	public void run() {
		//synchronized获取对象锁lock，一次只有一个线程进入该代码块
		synchronized (lock) {
			for (int i = 1; i <= 52; i = i + 1) {
				if (TwoThreadCommunication.k % 3 == 0){
					i--;
					try {
						//放弃CPU，进入阻塞
						lock.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} else {
					System.out.print(i);
					TwoThreadCommunication.k++;
					//唤醒阻塞中的线程
					lock.notify();
				}
			}
		}
	}
}

class Second extends Thread {
	private Object lock;
	public Second(Object lock) {
		super();
		this.lock = lock;
	}
	@Override
	public void run() {
		synchronized (lock) {
			for (int i = 65; i <= 90; i++) {
				if (TwoThreadCommunication.k % 3 != 0) {
					i--;
					try {
						lock.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} else {
					System.out.print((char)(i));
					TwoThreadCommunication.k++;
					lock.notify();
				}
			}
		}
	}
}
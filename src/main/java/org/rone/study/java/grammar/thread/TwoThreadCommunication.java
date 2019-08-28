package org.rone.study.java.grammar.thread;

import org.rone.study.java.util.DateUtil;

import java.util.Date;

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
		System.out.println("主线程目前活跃的进程数：" + Thread.activeCount() + ", time: " + DateUtil.date2String(new Date(), "yyyy-MM-dd HH:mm:ss.SSS"));
		first.start();
		second.start();
		// 设置线程中断
		first.interrupt();
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
		System.out.println("First线程：目前活跃的进程数：" + Thread.activeCount() + ", time: " + DateUtil.date2String(new Date(), "yyyy-MM-dd HH:mm:ss.SSS"));
		//synchronized获取对象锁lock，一次只有一个线程进入该代码块
		synchronized (lock) {
			for (int i = 1; i <= 52; i = i + 1) {
				// 判断当前线程是否中断了
				if (this.isInterrupted()) {
					System.out.println("First 中断了......");
					// 判断当前线程是否中断了，并清除中断状态
					Thread.interrupted();
				}
				if (TwoThreadCommunication.k % 3 == 0){
					i--;
					try {
						//放弃CPU，进入阻塞
						lock.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} else {
					System.out.print(i + " ");
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
		System.out.println("Second线程：目前活跃的进程数：" + Thread.activeCount() + ", time: " + DateUtil.date2String(new Date(), "yyyy-MM-dd HH:mm:ss.SSS"));
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
					System.out.print((char)(i) + " ");
					TwoThreadCommunication.k++;
					lock.notify();
				}
			}
		}
	}
}
package org.rone.study.java.grammar.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 读取一个文件夹/文件
 */
public class ReadFile {
	private static void first(File file,String fileType) {
		if(file.exists()) {
			System.out.println(file.getName());
			if(file.isDirectory()) {
				//获取子文件
				File[] files = file.listFiles();
				if(files != null) {
					for (int i = 0; i< files.length; i++) {
						first(files[i], fileType);
					}
				}
			} else if(file.isFile()) {
				if(file.getName().endsWith("." + fileType)) {
					System.out.println(file.getName() + "的内容:");
					System.out.println("*******************************");
					byte[] a = new byte[10];
					try {
						InputStream in = new FileInputStream(file);
						while(in.read(a) != -1) {
							String str = new String(a, "UTF-8");
							System.out.print(str);
						}
						System.out.println();
						System.out.println("*******************************");
						in.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		File file = new File("");
		first(file,"txt");
	}
}

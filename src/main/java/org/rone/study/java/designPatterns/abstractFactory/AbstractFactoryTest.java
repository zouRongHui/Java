package org.rone.study.java.designPatterns.abstractFactory;

/**
 * 抽象工场模式
 */
public class AbstractFactoryTest {
	public static void main(String[] args) {
		//夏装，短袖配短裤
		ClothingFactory clothingFactory = new SummerFactory();
		Clothes clothes = clothingFactory.gainClothes();
		Trousers trousers = clothingFactory.gainTrousers();
		clothes.wearClothes();
		trousers.wearTrousers();
		System.out.println();
		//冬装，羽绒服配保暖裤
		clothingFactory = new WinterFactory();
		clothes = clothingFactory.gainClothes();
		trousers = clothingFactory.gainTrousers();
		clothes.wearClothes();
		trousers.wearTrousers();
		/*
		this is short sleeve.
		this is shorts.

		this is down jacket.
		this is warm pants.
		 */
	}
}
/**
 * 上衣
 */
interface Clothes {
	void wearClothes();
}
/**
 * 裤子
 */
interface Trousers {
	void wearTrousers();
}
/**
 * 短袖
 */
class ShortSleeve implements Clothes {
	@Override
	public void wearClothes() {
		System.out.println("this is short sleeve.");
	}
}
/**
 * 羽绒服
 */
class DownJacket implements Clothes {
	@Override
	public void wearClothes() {
		System.out.println("this is down jacket.");
	}
}
/**
 * 短裤
 */
class Shorts implements Trousers {
	@Override
	public void wearTrousers() {
		System.out.println("this is shorts.");
	}
}
/**
 * 保暖裤
 */
class WarmPants implements Trousers {
	@Override
	public void wearTrousers() {
		System.out.println("this is warm pants.");
	}
}
/**
 * 服装工厂，生产上衣和裤子
 */
interface ClothingFactory {
	Clothes gainClothes();
	Trousers gainTrousers();
}
/**
 * 夏装工厂，只生产短袖和短裤
 */
class SummerFactory implements ClothingFactory {
	@Override
	public Clothes gainClothes() {
		return new ShortSleeve();
	}
	@Override
	public Trousers gainTrousers() {
		return new Shorts();
	}
}
/**
 * 冬装工厂，只生产羽绒服和保暖裤
 */
class WinterFactory implements ClothingFactory {
	@Override
	public Clothes gainClothes() {
		return new DownJacket();
	}
	@Override
	public Trousers gainTrousers() {
		return new WarmPants();
	}
}



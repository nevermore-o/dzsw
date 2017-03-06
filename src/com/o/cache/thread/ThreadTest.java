package com.o.cache.thread;

public class ThreadTest {
	
	public static void main(String[] args){
		MyThread mt= new MyThread("abcd");
		mt.start();
		try{
			//mt
		} catch( Exception e){
			e.printStackTrace();
		}
		for(int i=0;i<100;i++){
			System.out.println("this is main function --"+i+"--running!");
		}
		
	}
	

}

class MyThread extends Thread{
	
	MyThread(String str){
		super(str);
	}
	
	public void run() {
		for(int i=0;i<100;i++){
			System.out.println("this is --"+i+"--running!");
		}
		try {
			sleep(1000);
		} catch (Exception e) {
			// TODO: handle exception
			return;
		}
		
		
	}
	
}

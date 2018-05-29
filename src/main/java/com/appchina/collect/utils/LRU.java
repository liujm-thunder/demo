package com.appchina.collect.utils;

import java.util.ArrayList;
import java.util.List;

/**
 *  LRU是Least Recently Used的缩写，即最近最少使用页面置换算法，是为虚拟页式存储管理服务的，是根据页面调入内存后的使用情况进行决策了。由于无法预测各页面将来的使用情况，只能利用“最近的过去”作为“最近的将来”的近似，因此，LRU算法就是将最近最久未使用的页面予以淘汰。
 可以用一个特殊的栈来保存当前正在使用的各个页面的页面号。当一个新的进程访问某页面时，便将该页面号压入栈顶，其他的页面号往栈底移，如果内存不够，则将栈底的页面号移除。这样，栈顶始终是最新被访问的页面的编号，而栈底则是最近最久未访问的页面的页面号。
 如输入以下序列时：4,7,0,7,1,0,1,2,1,2,6
 结果为：
 4
 4        7
 4        7        0
 4        0        7
 4        0        7        1
 4        7        1        0
 4        7        0        1
 4        7        0        1        2
 4        7        0        2        1
 4        7        0        1        2
 7        0        1        2        6
 */
public class LRU {
    /**
	 * 内存块的个数
	 */
	public static final int N = 5;
	/**
	 * 内存块数组
	 */
	Object[] array = new Object[N];
	private int size;
	
	public LRU() {
	}
	/**
	 * 判断内存区是否为空
	 * @return
	 */
	public boolean isEmpty() {
		if(size == 0) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * 判断内存区是否达到最大值
	 * @return
	 */
	public boolean isOutOfBoundary() {
		if(size >=N) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * 查找元素o在数组中的位置
	 * @param o
	 * @return
	 */
	public int indexOfElement(Object o) {
		for(int i=0; i<N; i++) { 
			if(o == array[i]) {
				return i;
			}
		}
		return -1;
	}	
	/**
	 * 有新的数据o需要申请内存
	 * @param o
	 * @return 移出内存区的数据
	 */
	public Object push(Object o) {
		int t = -1;
		if(!isOutOfBoundary() && indexOfElement(o) == -1){
			array[size] = o;
			size ++;
		} else if(isOutOfBoundary() && indexOfElement(o) == -1){
			for(int i=0; i<size-1; i++) {
				array[i] = array[i+1];				
			}
			array[size-1] = o;
		} else {
			t = indexOfElement(o);
			for(int i=t; i<size-1; i++) {
				array[i] = array[i+1];
			}
			array[size-1] = o;
		}
		if( -1 == t) {
			return null;
		} else {
			return array[t];
		}
	}
	/**
	 * 输出内存区中的各数据
	 */
	public void showMemoryBlock() {
		for(int i=0; i<size; i++) {
			System.out.print(array[i] + "\t");
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Integer iter[] = {4,7,0,7,1,0,1,2,1,2,6};
		LRU lru = new LRU();
		for(int i=0; i<iter.length; i++) {
			lru.push(iter[i]);
			lru.showMemoryBlock();
			System.out.println();
		}
	}

}

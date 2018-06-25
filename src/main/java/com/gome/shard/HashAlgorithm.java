package com.gome.shard;

/**
 * 分表hash算法，暂定依据app_no分成 2^6 张表
 * @author yyzhang 2017年8月18日14:42:16
 *
 */
public class HashAlgorithm {
	/**
	 * hash算法补充，防止哈希码低位碰撞
	 * @param h
	 * @return
	 */
	private static int hash(int h) {
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }
	
    /**e
     * 返回实际分表后缀
     */
    private static int indexFor(int h, int length) {
        return h & (length-1);
    }
    
    /**
     * 返回散列结果
     * @param strategy
     * @return
     */
    public static String index(String strategy){
    	return String.valueOf(indexFor(hash(strategy.hashCode()), 1 << 3));
    }
    
    public static void main(String[] args) {
		// 测试整数的hash值
		System.out.println(new Integer(1).hashCode());
		System.out.println(new Integer(500000000).hashCode());
		
		System.out.println(hash("MJ2017081700000000000000001".hashCode()) & (1 << 6 - 1));
		System.out.println(String.valueOf(indexFor(hash("MJ2017081700000000000000001".hashCode()), 1 << 3)));
	}
}

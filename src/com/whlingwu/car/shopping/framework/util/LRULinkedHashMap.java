/** * 文件名：LRULinkedHashMap.java 
 * * 版本信息： 
 * 日期：2015-1-24 
 * Copyright: Corporation 2015 
 * 版权所有 * */
package com.whlingwu.car.shopping.framework.util;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
/** 项目名称：carShopping 
 * 类名称：LRULinkedHashMap 
 * 类描述：b2b,b2c，处理页面提交的请求。 
 * 类描述：Bean,响应Action请求，对数据库进行操作，并把结果返回到Action。 
 * Copyright: Copyright (c) 2015 by 芜湖领悟信息科技有限公司
 * Company: b2c b2b game trading platform System 
 * 创建人：凤军军
 * 创建时间：2015-1-24 上午11:15:31 
 * 修改人：凤军军 
 * 修改时间：2015-1-24 上午11:15:31 
 * 修改备注：
 * @version 1.0* */

public class LRULinkedHashMap<K, V> extends LinkedHashMap<K, V> {

    /** serialVersionUID */
    private static final long serialVersionUID = -5933045562735378538L;

    /** 最大数据存储容量 */
    private static final int  LRU_MAX_CAPACITY     = 10;

    /** 存储数据容量  */
    private int               capacity;

    /**
     * 默认构造方法
     */
    public LRULinkedHashMap() {
        super();
    }

    /**
     * 带参数构造方法
     * @param initialCapacity   容量
     * @param loadFactor        装载因子
     * @param isLRU             是否使用lru算法，true：使用（按方案顺序排序）;false：不使用（按存储顺序排序）
     */
    public LRULinkedHashMap(int initialCapacity, float loadFactor, boolean isLRU) {
        super(initialCapacity, loadFactor, true);
        capacity = LRU_MAX_CAPACITY;
    }

    /**
     * 带参数构造方法
     * @param initialCapacity   容量
     * @param loadFactor        装载因子
     * @param isLRU             是否使用lru算法，true：使用（按方案顺序排序）;false：不使用（按存储顺序排序）
     * @param lruCapacity       lru存储数据容量       
     */
    public LRULinkedHashMap(int initialCapacity, float loadFactor, boolean isLRU, int lruCapacity) {
        super(initialCapacity, loadFactor, true);
        this.capacity = lruCapacity;
    }

    /** 
     * @see java.util.LinkedHashMap#removeEldestEntry(java.util.Map.Entry)
     */
    @Override
    protected boolean removeEldestEntry(Entry<K, V> eldest) {
//        System.out.println(eldest.getKey() + "=" + eldest.getValue());
        
        if(size() > capacity) {
            return true;
        }
        return false;
    }
    
    public static void main(String[] args) {

        LinkedHashMap<String, String> map = new LRULinkedHashMap<String, String>(16, 0.75f, true);
        map.put("a", "a1"); //a  a
        map.put("b", "b1"); //a  a b
        map.put("c", "c1"); //a  a b c
        map.put("d", "d1"); //   b c a     
        System.out.println(map);
        System.out.println(map.values());
//        for(Iterator<Entry<String, String>> it = map.entrySet().iterator();it.hasNext();){
//        	System.out.println(it.next().getKey());
//        }
//        map.put("d", "d"); //b  b c a d
//        map.put("a", "a"); //   b c d a
//        map.put("b", "b"); //   c d a b     
//        map.put("f", "f"); //c  c d a b f
//        map.put("g", "g"); //c  c d a b f g
//
//        map.get("d"); //c a b f g d
//        for (Entry<String, String> entry : map.entrySet()) {
//            System.out.print(entry.getValue() + ", ");
//        }
//        System.out.println();
//
//        map.get("a"); //c b f g d a
//        for (Entry<String, String> entry : map.entrySet()) {
//            System.out.print(entry.getValue() + ", ");
//        }
//        System.out.println();
//
//        map.get("c"); //b f g d a c
//        for (Entry<String, String> entry : map.entrySet()) {
//            System.out.print(entry.getValue() + ", ");
//        }
//        System.out.println();
//
//        map.get("b"); //f g d a c b
//        for (Entry<String, String> entry : map.entrySet()) {
//            System.out.print(entry.getValue() + ", ");
//        }
//        System.out.println();
//
//        map.put("h", "h"); //f  f g d a c b h
//        for (Entry<String, String> entry : map.entrySet()) {
//            System.out.print(entry.getValue() + ", ");
//        }
//        System.out.println();
    }

}


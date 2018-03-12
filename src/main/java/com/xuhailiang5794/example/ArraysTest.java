package com.xuhailiang5794.example;


import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * <pre>
 *
 * </pre>
 *
 * @author hailiang.xu
 * @version 1.0
 * @since 2018/3/12 14:30
 */
public class ArraysTest {
    /**
     * 1、返回一个受指定数组支持的大小固定的列表；
     * 2、传入数组如果是一个基本类型数组（如int[]），那么将返回一个大小为1的列表，且列表元素就是传入的这个数组；
     * 3、返回的列表不可添加或删除元素，可以对列表中数据做替换操作；
     * 4、更改列表中元素内容即更改传入数组的内容（返回的列表持有传入数组参数的引用，不是单独的两个对象）。
     */
    @Test
    public void asList() {
        /**
         * 基本类型数组，返回结果为List<int[]>，列表元素为基本类型数组本身
         */
        System.out.println("基本数据类型");
        int[] arr1 = new int[]{1, 2, 3, 4, 5};
        List<int[]> list1 = Arrays.asList(arr1);
        list1.forEach(item -> {
            System.out.println(item + "," + Arrays.toString(item));// 输出结果：[I@6f75e721,[1, 2, 3, 4, 5]
            System.out.println("修改元素");
            item[0] = 9;
            System.out.println(Arrays.toString(item));// 输出结果：[9, 2, 3, 4, 5]
        });
        System.out.println("添加元素");
        try {
            list1.add(null);// java.lang.UnsupportedOperationException
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("类类型");
        Integer[] arr2 = new Integer[]{1, 2, 3, 4, 5};
        List<Integer> list2 = Arrays.asList(arr2);
        list2.forEach(item -> {
            System.out.println(item);
            // 输出结果：1
            // 输出结果：2
            // 输出结果：3
            // 输出结果：4
            // 输出结果：5
        });
        list2.set(0, 9);
        list2.forEach(item -> {
            System.out.println(item);
            // 输出结果：9
            // 输出结果：2
            // 输出结果：3
            // 输出结果：4
            // 输出结果：5
        });
        System.out.println("输出58行中对应下标的数组的值，看是否与更改后的列表是否一致");
        System.out.println("arr2 " + arr2[0]);// 9，与58的结果一致

        System.out.println(Arrays.asList(true, false));// [true, false]，这种方式可能是将单独传入的基本数据装箱成包装类型
        System.out.println(Arrays.asList(new boolean[]{true, false}));// [[Z@470e2030]
    }

    /**
     * 1、使用二分搜索法来搜索指定的数组，以获得指定的值（指定搜索值对应的下标）；
     * 2、查找元素时，不包括toIndex的值，因此传入的toIndex应比实际下标大1；
     * 3、返回结果小于0，表示搜索不到指定的元素；
     * 4、Arrays还提供了一个含Comparator对象的方法，此方法能够更快速的定位到搜索值。
     */
    @Test
    public void binarySearch() {
        byte[] arr = {1, 2, 3, 4};
        System.out.println(Arrays.binarySearch(arr, (byte) 0));// 输出结果：-1
        System.out.println(Arrays.binarySearch(arr, (byte) 1));// 输出结果： 0
        System.out.println(Arrays.binarySearch(arr, (byte) 3));// 输出结果： 2


        System.out.println("指定范围查找");
        // 查找时不包括toIndex（检验值时toIndex与数组长度校验，取值时int high = toIndex - 1）
        System.out.println(Arrays.binarySearch(arr, 1, 4, (byte) 1));// 在下标为1~3之间找不到元素1，输出结果： -2

    }

    /**
     * 1、复制指定的数组，截取或用默认值填充，以使副本具有指定的长度；
     * 2、Arrays.copyOf调用System.arraycopy方法，copy出来的每个副本都是单独的对象。
     */
    @Test
    public void copyOf() {
        int[] arr1 = {1, 2, 3, 4};
        int[] arr2 = Arrays.copyOf(arr1, 2);
        System.out.println(Arrays.toString(arr2));
    }


}

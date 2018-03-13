package com.xuhailiang5794.example;


import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Spliterator;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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

    /**
     * 1、如果两个指定数组彼此是深层相等 的，则返回 true；
     * 2、不支持基本数据类型数组；
     * 3、先比较数组长度，再用==比较，==结果为false时再用equals比较；
     * 4、Arrays为每种基本类型数组定义了一个equals方法。
     */
    @Test
    public void deepEquals() {
        Byte[] arr1 = {1};
        Byte[] arr2 = {1, 2};
        System.out.println(Arrays.deepEquals(arr1, arr2));
    }

    /**
     * 1、将指定的值分配给指定数组指定范围中的每个元素；
     * 2、指定范围时，替换不包括toIndex下标值，因此需要+1。
     */
    @Test
    public void fill() {
        byte[] arr1 = {1, 2, 3, 4};
        // 全部范围
        Arrays.fill(arr1, (byte) 5);
        System.out.println(Arrays.toString(arr1));// [5, 5, 5, 5]
        // 指定范围
        Arrays.fill(arr1, 1, 3, (byte) 1);
        System.out.println(Arrays.toString(arr1));// [5, 1, 5, 5]
    }

    /**
     * 1、对数组arr进行二元迭代；
     * 2、从头到尾逐个按照left op right的方式更新，更新后的值继续迭代下一个元素；
     * 3、TypeBinaryOperator表示二元运算法，Type目前支持类型为：Int、Double、Long
     */
    @Test
    public void parallelPrefix() {
        int[] arr = new int[]{1, 2, 3};
        Arrays.parallelPrefix(arr, (left, right) -> {
            return left * right;
        });
        // left op right 数组元素
        // 1 * 2        [1, 2, 3]
        // 2 * 3        [1, 2, 6]
        System.out.println(Arrays.toString(arr));// [1, 2, 6]
    }

    /**
     * 1、使用填充算法为数组arr的每一个元素赋值；
     * 2、默认使用元素的索引为该元素赋值；
     * 3、可自定义如何生成赋值。
     */
    @Test
    public void parallelSetAll() {
        int[] arr = new int[]{1, 52, 3};
        Arrays.parallelSetAll(arr, index -> {
            return index;
        });// 使用索引值
        System.out.println(Arrays.toString(arr));// [0, 1, 2]
        Arrays.parallelSetAll(arr, index -> {
            return index * 3;
        });// 索引值 * 3。可自定义，例如随机数等
        System.out.println(Arrays.toString(arr));// [0, 3, 6]
    }

    /**
     * 1、JDK1.8新增排序方法；
     * 2、可指定数组范围排序。
     */
    @Test
    public void parallelSort() {
        int[] arr = new int[]{100, 52, 3};
        Arrays.parallelSort(arr, 0, 2);
        System.out.println(Arrays.toString(arr));// [52, 100, 3]
        Arrays.parallelSort(arr);
        System.out.println(Arrays.toString(arr));// [3, 52, 100]
    }

    /**
     * 1、获取一个分割器，可用于遍历数组；
     * 2、可指定范围获取分割器，获取到的分割器中内容不包括endExclusive下标的元素；
     * 3、除支持复杂类型数组遍历之外，还支持int、double、long三种基本类型数组遍历；
     * 4、分割器持有数组的引用，并非独立的对象（即改变数组元素将影响遍历的结果）；
     * 5、遍历过程中可以修改数组中元素的值。
     */
    @Test
    public void spliterator() {
        /**
         * 全部遍历
         */
        Integer[] arr = new Integer[]{100, 52, 3};
        Spliterator<Integer> spliterator = Arrays.spliterator(arr);
        spliterator.forEachRemaining(integer -> {
            System.out.println(integer);
            // 输出结果
            // 100
            // 52
            // 3
        });

        /**
         * 指定范围遍历
         */
        spliterator = Arrays.spliterator(arr, 0, 1);
        spliterator.forEachRemaining(integer -> {
            System.out.println(integer);
            // 输出结果
            // 100
        });

        /**
         * 改变数组中元素的值
         */
        spliterator = Arrays.spliterator(arr);
        arr[0] = 99;
        spliterator.forEachRemaining(integer -> {
            /**
             * “无效修改”，遍历时已经将arr中0的值取出来了，因此在这里对0的元素进行修改并不影响输出结果
             */
            arr[0] = 199;

            // 遍历中改变数组中元素的值
            arr[1] = 199;
            System.out.println(integer);
            // 输出结果
            // 99
            // 99
            // 3
        });

        /**
         * 基本数据类型遍历
         */
        int[] arr2 = {1, 2, 3, 4};
        Arrays.spliterator(arr2).forEachRemaining(new IntConsumer() {
            @Override
            public void accept(int value) {
                System.out.println(value);
                // 输出结果
                // 1
                // 2
                // 3
                // 4
            }
        });
    }

    /**
     * 将数组作为一个连续的流使用，用于上与spliterator相似
     */
    @Test
    public void stream() {
        int[] arr1 = {1, 2, 3, 4};
        IntStream intStream = Arrays.stream(arr1);
        intStream.forEach(new IntConsumer() {
            @Override
            public void accept(int value) {
                System.out.println(value);
            }
        });

        Integer[] arr2 = {1, 2, 3, 4};
        Stream<Integer> integerStream = Arrays.stream(arr2);
        integerStream.forEach(integer -> {
            arr2[0] = 2;
            System.out.println(integer);
        });

    }

}

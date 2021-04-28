package com.hxszd.background;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @description: https://www.cnblogs.com/onepixel/articles/7674659.html
 * @author: pig1etO
 * @create: 2020-11-05 15:57
 **/
public class Sort {

    public static void main(String[] args) {

        List list = null;
//        list = new ArrayList(Arrays.asList(3,44,38,5,47,15,36,26,27,2,46,4,19,50,48));
//        list = new ArrayList(Arrays.asList(19,50,48));
        /*list = Stream.of(3,44,38,5,47,15,36,26,27,2,46,4,19,50,48).collect(Collectors.toList());
        list = new ArrayList(){{
            add(3);
        }};

        // 复制某个元素n遍
        list = Collections.nCopies(3, "apple");

        list = new ArrayList(){{
            Collections.nCopies(3, "apple");
            add("orange");
        }};*/

        Integer[] array = {3,44,38,5,47,15,36,26,27,2,46,4,19,50,48};
//        Integer[] array = {3,44,38,5,47,15,36,26,27,2};


        for (int k = 0; k < array.length; k++) {
            System.out.print(array[k] + " ");
        }
        System.out.println();
        System.out.println("*************以上是原始数组，分隔符*************");

        // 冒泡
        //bubbleSort(array);
        // 选择
        //selectionSort(array);

        // 插入
//        insertionSort(array);

        quickSort(array);
    }

    /**
     * 冒泡排序
     * 核心思想：从头开始遍历，小的放左边大的放右边，移动一个指针位，遍历全部数组
     * @param array
     */
    public static void bubbleSort(int[] array) {
        int tmp;
        for (int i = array.length; i > 0; i--) {
            for (int j = 1; j < i; j++) {
                // 前面数大于后面数就进行交换，然后移动指针
                if (array[j-1] > array[j]) {
                    tmp = array[j];
                    array[j] = array[j-1];
                    array[j-1] = tmp;
                }
            }
            for (int k = 0; k < array.length; k++) {
                System.out.print(array[k] + " ");
            }
            System.out.println();
        }
    }

    /**
     * 选择排序
     * 核心思想：遍历一遍数组，每次都选出来最小的一个数字，仅仅将选出来的数字进行交换
     * @param array
     */
    public static void selectionSort(int[] array) {
        // 最小值下标
        int point;
        int tmp;


        for (int i = 0; i < array.length; i++) {
            point = i;
            for (int j = i+1; j < array.length; j++) {
                if (array[j] < array[point]) {
                    point = j;
                }
            }
            tmp = array[i];
            array[i] = array[point];
            array[point] = tmp;

            // 输出
            for (int k = 0; k < array.length; k++) {
                System.out.print(array[k] + " ");
            }
            System.out.println();
        }
    }

    /**
     * 插入排序
     * 核心思想：最外边遍历一遍数组，以此下标为界，左边是已经排序好的，右边第一个是待排序的字段，
     * 需要将这个待排序的字段反向遍历一遍已经排序好的部分，选择合适的位置后，挪动此位置数字到待排序字段，均右移一位，之后插入当前排序字段
     * 特别要注意的是边界问题，要考虑待排序字段比array[0]都小的时候
     * @param array
     */
    public static void insertionSort(int[] array) {

        // 暂存待插入的数值
        int tmp;
        int tmp2;
        // 待插入数组下标位置
        int tmpPoint = -1;

        for (int i = 0; i < array.length - 1; i++) {
            // 将带出入的数值取出
            tmp = array[i+1];
            for (int k = i; k > 0; k--) {
                // 已完成排序的数组，最大的数都比当前待插入数据小的话，那就不要进行后续没有意义的比较
                if (array[i] < tmp) {
                    break;
                }
                // 比最小值都小，那肯定是最小值了
                if (array[0] > tmp) {
                    tmpPoint = 0;
                    break;
                }

                if (array[k] > tmp && array[k - 1] < tmp) {
                    tmpPoint = k;
                    break;
                }
            }

            // 找到了合适的位置,准备腾地儿
            if (tmpPoint != -1) {
                // 所有数向后移动一位
                for (int k = i+1; k > tmpPoint; k--) {
                    array[k] = array[k-1];
                }
                // 插入
                array[tmpPoint] = tmp;
                // 重置指标
                tmpPoint = -1;
            }

            // 输出
            for (int k = 0; k < array.length; k++) {
                System.out.print(array[k] + " ");
            }
            System.out.println();
        }

        // 输出
        for (int k = 0; k < array.length; k++) {
            System.out.print(array[k] + " ");
        }
        System.out.println();
    }

    /**
     * 快速排序
     * 核心思想：将数组一分为二，选取一个数值作为基准值（一般情况选中间的就好），遍历一遍数组，将数组中小于基准值的数据放到左侧数组，
     * 将数组中大于基准值的数据放到右侧数组，再分别递归左侧数组和右侧数组，拼接最后的数据。左侧数组 + 基准值 + 右侧数组。
     * 注意：需要注意基准值不放到任何一方数组，如果有数跟基准值相同，则放到左右哪个数组都可以
     * @param array
     */
    public static void quickSort(Integer[] array) {

        array = recursion(array).toArray(new Integer[array.length]);

        for (int k = 0; k < array.length; k++) {
            System.out.print(array[k] + " ");
        }
//        System.out.println(Arrays.toString(rightArray));
    }

    private static List<Integer> recursion(Integer [] array) {
        // 已经分割到不能在分割了
        if (array.length <= 1) {
            return Arrays.asList(array);
        }

        // 获取中点
        int midPoint = array.length / 2;
        // 中值
        int pivot = array[midPoint];

        List<Integer> leftArray = new ArrayList<>();
        List<Integer> rightArray = new ArrayList<>();
        // 方便看 结果list
        List<Integer> resultList = new ArrayList<>();

        for (int i = 0; i < array.length; i++) {
            if (i != midPoint) {
                // 把小于中值的数放到leftArray
                if (array[i] < pivot) {
                    leftArray.add(array[i]);
                } else {
                    rightArray.add(array[i]);
                }
            }
        }

        resultList.addAll(recursion(leftArray.toArray(new Integer[leftArray.size()])));
        resultList.add(pivot);
        resultList.addAll(recursion(rightArray.toArray(new Integer[rightArray.size()])));

        return resultList;
    }

    private void test() {

    }
}

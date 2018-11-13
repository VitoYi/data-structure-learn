package array;

import java.util.Arrays;

/**
 * 基于Java的数组，二次封装数组类
 * Created by yisq on 2018/11/13.
 */
public class Array<E> {

    private E[] data;
    private int size;       //数组中的元素个数

    /**
     * 构造方法
     *
     * @param capacity 初始化容量
     */
    public Array(int capacity) {
        data = (E[]) new Object[capacity];
        size = 0;
    }

    /**
     * 无参构造方法，用户没有传入初始化容量，默认长度为10
     */
    public Array() {
        this(10);
    }

    /**
     * 查询数组中有多少个元素
     */
    public int getSize() {
        return size;
    }

    /**
     * 获取数组的容量
     */
    public int getCapacity() {
        return data.length;
    }

    /**
     * 判断该数组是否为空
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 在数组的末尾添加元素
     *
     * @param e 要添加的元素
     */
    public void addLast(E e) {
        /* 复用add就可以了
        //检测该数组是否还有容量
        if (size == data.length)
            throw new IllegalArgumentException("addLast failed. Array is full.");

        data[size++] = e;*/

        add(size, e);
    }

    /**
     * 在数组第一个位置添加元素
     *
     * @param e 要添加的元素
     */
    public void addFirst(E e) {
        add(0, e);
    }

    /**
     * 在数组指定位置添加元素
     *
     * @param index 指定的位置
     * @param e     要添加的元素
     */
    public void add(int index, E e) {
        //检测该数组是否还有容量
        if (size == data.length)
            throw new IllegalArgumentException("add failed. Array is full.");
        //判断指定位置是否合法
        if (index < 0 || index > size)
            throw new IllegalArgumentException("add failed. Require idnex >= 0 and index <= size.");

        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        data[index] = e;
        size++;
    }

    /**
     * 获得指定位置的元素
     *
     * @param index 指定位置
     */
    public E get(int index) {
        //判断指定位置是否合法
        if (index < 0 || index > size)
            throw new IllegalArgumentException("add failed. Require idnex >= 0 and index <= size.");

        for (int i = 0; i < size; i++) {
            if (i == index)
                return data[i];
        }

        return null;
    }

    /**
     * 根据元素获取其在数组中的位置
     *
     * @param e 元素
     */
    public int getIndex(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e))
                return i;
        }
        return -1;
    }


    @Override
    public String toString() {
        return "Array{" +
                "data=" + Arrays.toString(data) +
                ", size=" + size +
                '}';
    }


    public static void main(String[] args) {
        Array array = new Array();
        array.addFirst("sss");
        System.out.println(array);
    }
}

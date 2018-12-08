package array;

import java.util.Arrays;

/**
 * 基于Java的数组，二次封装数组类
 * <p>
 * 动态数组的时间复杂度分析：
 * 增：O(n)
 * 删：O(n)
 * 改：已知索引 O(1)，未知索引 O(n)
 * 查：已知索引 O(1)，未知索引 O(n)
 * <p>
 * Created by yisq on 2018/11/13.
 */
public class Array<E> {

    private E[] data;
    private int size;       //数组中的元素个数

    /**
     * 构造方法
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
     * @param e 要添加的元素
     */
    public void addFirst(E e) {
        add(0, e);
    }

    /**
     * 在数组指定位置添加元素
     * @param index 指定的位置
     * @param e     要添加的元素
     */
    public void add(int index, E e) {
        //判断指定位置是否合法
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Add failed. Require idnex >= 0 and index <= size.");

        //检测该数组是否还有容量
        if (size == data.length) {
            resize(data.length * 2);
        }

        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        data[index] = e;
        size++;
    }

    /**
     * 扩容
     * 扩容是一个比较耗时的操作，但我们能保证它并不会每次 add() 都触发，那么这个耗时是可以分摊到其他的操作中，因此均摊复杂度为 O(1)
     * @param newCapacity 新容量
     * @author VitoYi
     */
    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    /**
     * 获得指定位置的元素
     * @param index 指定位置
     */
    public E get(int index) {
        //判断指定位置是否合法
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Get failed. Index is illegal.");

        return data[index];
    }

    /**
     * 修改指定索引位置的元素
     * @param index 指定索引
     * @param e     新元素
     * @author VitoYi
     */
    public void set(int index, E e) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Get failed. Index is illegal.");
        data[index] = e;
    }

    /**
     * 根据元素获取其在数组中的位置
     * @param e 元素
     */
    public int getIndex(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e))
                return i;
        }
        return -1;
    }

    /**
     * 查看数组中是否包含某个元素
     * @param e
     */
    public boolean contains(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e))
                return true;
        }
        return false;
    }

    /**
     * 查找数组中元素e所在的位置，如没有则返回-1
     * @param e
     */
    public int index(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e))
                return i;
        }
        return -1;
    }

    /**
     * 删除索引位置的元素
     * @param index
     */
    public E remove(int index) {
        //判断指定位置是否合法
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Delete failed. Require idnex >= 0 and index < size.");

        E e = data[index];

        /*for (int i = index; i < size; i++) {
            data[i] = data[i + 1];      data[i + 1] 这样写有数组越界的风险
        }*/

        for (int i = index + 1; i < size; i++) {
            data[i - 1] = data[i];
        }

        size--;
        data[size] = null;

        /*
         * 防止复杂度震荡
         * 在缩容的过程中，随着 data.length 越来越小，有可能等于1，也就是说，data.length / 2 有可能等于0.
         * 但是 new 一个数组空间，肯定是不能将长度设为0，因此，这里 data.length / 2 != 0
         */
        if (size == data.length / 4 && data.length / 2 != 0)
            resize(data.length / 2);

        return e;
    }

    /**
     * 删除第一个元素
     * @return
     */
    public E removeFirst() {
        return remove(0);
    }

    /**
     * 删除最后一个元素
     * @return
     */
    public E removeLast() {
        return remove(size - 1);
    }

    /**
     * 如数组中有元素e，则删除
     * @param e
     */
    public void removeElement(E e) {
        int index = index(e);
        if (index != -1)
            remove(index);
    }


    @Override
    public String toString() {
        return "Array, {" +
                "data=" + Arrays.toString(data) +
                ", size=" + size +
                "} capacity=" + data.length;
    }


    public static void main(String[] args) {
        Array array = new Array();
        for (int i = 0; i < 10; i++) {
            array.addLast(i);
        }
        System.out.println(array);

        array.add(1, 100);
        System.out.println(array);

        array.addFirst(-1);
        System.out.println(array);

        array.remove(2);
        System.out.println(array);

        array.removeElement(4);
        System.out.println(array);

        array.removeFirst();
        System.out.println(array);

    }
}

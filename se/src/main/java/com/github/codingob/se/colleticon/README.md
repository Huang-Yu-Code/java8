# Collection

## List

1. 继承于Collection
2. 元素可以重复
3. 数组长度固定,List长度不固定
4. 可以有null元素

方法
* int size()
* boolean isEmpty()
* boolean contains(Object o)
* Iterator<E> iterator()
* boolean add(E e)
* boolean remove(Object o)
* boolean containsAll(Collection<?> c)
* boolean addAll(Collection<? extends E> c)
*

### Vector

1. 数组结构
2. 线程安全，线程同步的(synchronized)的

### ArrayList

1. 数组结构
2. 线程不安全

### LinkedList

1. 链表结构
2. 线程不安全

## Set

1. 继承于Collection
2. 元素不可以重复(重复会覆盖)
3. 线程不安全

方法
* 
* 
* 
* 
* 
* 

### HashSet

1. 哈希表
2. 无序
3. 可以有null元素

### LinkedHashSet

1. 哈希表+链表
2. 有序
3. 可以有null元素

### TreeSet

1. 红黑树
2. 有序
3. 不可以有null元素

## Queue

方法
* 
* 
* 
* 
* 
* 

### ArrayBlockingQueue

### LinkedBlockingQueue

### SynchronousQueue

### PriorityBlockingQueue

### DelayQueue

## Map

1. key-value形式

方法
* 
* 
* 
* 
* 

### HashMap

1. 数组+链表(红黑树)
2. key和value可以为null
3. 线程不安全

### HashTable

1. 哈希表
2. key和value不可以为null
3. 线程安全

### TreeMap

1. 红黑树
2. key不能为null
3. 线程不安全

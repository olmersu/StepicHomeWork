package com.Algorithms.HaffmanCode.HaffmanCoding;

/**
 * Created by bogomolov on 09.09.2016.
 * Задача на программирование: кодирование Хаффмана
 * По данной непустой строке s длины не более 10^4, состоящей из строчных букв латинского алфавита, постройте оптимальный беспрефиксный код.
 * В первой строке выведите количество различных букв k, встречающихся в строке, и размер получившейся закодированной строки.
 * В следующих k строках запишите коды букв в формате "letter: code". В последней строке выведите закодированную строку.
 * Sample Input 1:
 * a
 * Sample Output 1:
 * 1 1
 * a: 0
 * 0
 * Sample Input 2:
 * abacabad
 * Sample Output 2:
 * 4 14
 * a: 0
 * b: 10
 * c: 110
 * d: 111
 * 01001100100111
 *
 * Задача решалась с помощью подсчета частоты, затем сотрировки массива по частоте и выдача кодов по порядку, но как оказалось необходимо строить дерево.
 * Не проходил тест Failed test #9. got: 23 expected: 20
 * в этом тесте использовалось слово accepted
 * в коментах нашел ссылку на правильно объяснение кодирования Хаффмана:
 * https://habrahabr.ru/post/144200/
 * ЗАдачу решаю с помощью очереди с приоритетами класс:
 * import java.util.PriorityQueue;
 * и собственного класса построения деревьев, спиздил с инета.
 * Passed test #1. 0
 * Passed test #2. 01001100100111
 * Passed test #3. 00
 * Passed test #4. 000
 * Passed test #5. 0000011111
 * Passed test #6. 00000101111111111
 * Passed test #7. 1011001011
 * Passed test #8. 1011001101100010110011011
 * Passed test #9. 10000001110101011011
 * Passed test #10. 111000101100111001101010111101
 * Passed test #11. 100001101101111101111100001001
 * Passed test #12. 100001101101111101111100001001
 * Passed test #13. 101001101010011110100111101001
 * Passed test #14. 000000000000000000000000000000
 * Passed test #15. 101101101101101101101101101101
 * Passed test #16. 011101111001000011100101011011
 * Passed test #17. 011010110011010110111011001101
 * Passed test #18. 100111011101101100010001011011
 * Passed test #19. 111000011000111010111011001111
 * Passed test #20. 111010101111011110111101101111
 * Passed test #21. 011011111110011101110100001110
 * Passed test #22. 101101101010100100100001100010
 * Passed test #23. 101100101100101100101100101100
 * Passed test #24. 110000111110100011110100111110
 * Passed test #25. 100011100101100000101111001100
 * Passed test #26. 011001111011011010110011001010
 * Passed test #27. 111100111111010101011101111010
 * Passed test #28. 011001001100101111101100100111
 * Passed test #29. 011001011100111111011011011001
 * Passed test #30. 110110001101111010010100100101
 * Passed test #31. 001001100001100101000011001001
 * Passed test #32. 111110000100011011111010011110
 * Passed test #33. 000000000000000000000000000000
 * Passed test #34. 010111110000100111001110111010
 * Passed test #35. 010011001001110010011001001111
 * Passed test #36. 000000000000000000000000000000
 * Passed test #37. 000111100100101110001010110110
 * Passed test #38. 110011001110110001100110101001
 * Passed test #39. 010101011010101111110100111011
 * Passed test #40. 010110101101001011011100010110
 * Passed test #41. 101111111010010111011111110010
 */

import java.util.*;

public class HaffmanCoding {
    /*    private static Comparator<TreeNode<Thing>> idComparator = new Comparator<TreeNode<Thing>>(){
            @Override
            public int compare(TreeNode c1, TreeNode c2) {
                return (int) (((Thing)c1.data).getx2() - ((Thing)c2.data).getx2());
            }
        };*/
    private static Comparator<TreeNode<Thing>> idComparator = (c1, c2) -> c1.data.getx2() - c2.data.getx2();
    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        String s = in.next();
        String s = "accepted";
        char [] ch = s.toCharArray();
        char cur_ch;
        Vector things = new Vector();
        Queue<TreeNode<Thing>> thingPriorityQueue = new PriorityQueue<>(idComparator);
        for (int i=0; i < s.length(); i++){
            int ch_amount=0;
            if(ch[i]!=0) {
                cur_ch = ch[i];
                for (int j = i; j < s.length(); j++) {
                    if (cur_ch == ch[j] && ch[j] != 0) {
                        ch_amount++;
                        ch[j] = 0;
                    }
                }
                thingPriorityQueue.add(new TreeNode<>(new Thing(cur_ch, ch_amount)));
            }
        }
        TreeNode node1;
        TreeNode node2;
        if (thingPriorityQueue.size()==1) {
            node1 = thingPriorityQueue.poll();
            node1.code= "0";
            things.add(node1);
        }
        else {
            while (true) {
                node1 = thingPriorityQueue.poll();
                if (node1 == null) break;
                node1.code = "0";
                node2 = thingPriorityQueue.poll();
                if (node2 == null) break;
                node2.code = "1";
                TreeNode<Thing> node = new TreeNode(new Thing('\0', ((Thing) node1.data).getx2() + ((Thing) node2.data).getx2()));
                node.addChild(node1);
                node.addChild(node2);
                thingPriorityQueue.add(node);
                if (((Thing) node1.data).getx1() != 0) {
                    things.add(node1);
                }
                if (((Thing) node2.data).getx1() != 0) {
                    things.add(node2);
                }
            }
        }
        for (int i=0; i<things.size(); i++){
            node1 = (TreeNode) things.get(i);
            ((Thing)((TreeNode) things.get(i)).data).setx3(node1.code);
            node2 = node1.parent;
            while (node2 != null && node2.parent != null){
                ((Thing)((TreeNode) things.get(i)).data).setx3(node2.code+((Thing)((TreeNode) things.get(i)).data).getx3());
                node2=node2.parent;
            }
        }

        ch = s.toCharArray();
        String finals = "";
//        for (int i=0; i < s.length(); i++){
        for (char c: ch){
            for (Object thing : things) {
                if (c == ((Thing) ((TreeNode) thing).data).getx1()) {
                    finals = finals + ((Thing) ((TreeNode) thing).data).getx3();
                    break;
                }
            }
        }
        System.out.print(things.size()+" "+finals.length()+"\n");
        for (Object thing : things) {
            System.out.println(((Thing) ((TreeNode) thing).data).getx1() + ": " + ((Thing) ((TreeNode) thing).data).getx3());
        }
        System.out.println(finals);
    }
}
class Thing implements Comparable<Thing>{
    private char x1; //letter
    private int x2; //frequency
    private String x3; // code
    Thing(char x1, int x2){
        this.x1=x1;
        this.x2=x2;
        this.x3="";
    }
    char getx1(){
        return this.x1;
    }
    int getx2(){
        return this.x2;
    }
    String getx3(){
        return this.x3;
    }
    public void setx1(char x1){
        this.x1=x1;
    }
    public void setx2(int x2){
        this.x2=x2;
    }
    void setx3(String x3){
        this.x3=x3;
    }
    @Override
    public int compareTo(Thing other) {
        return Integer.compare((-1)*this.getx2(), (-1)*other.getx2());
    }
}

class TreeNode<T> implements Iterable<TreeNode<T>> {
    T data;
    TreeNode<T> parent;
    private List<TreeNode<T>> children;
    String code;

    TreeNode(T data) {
        this.data = data;
        this.children = new LinkedList<TreeNode<T>>();
    }

    public TreeNode(T data, TreeNode child1, TreeNode child2) {
        this.data = data;
        this.children = new LinkedList<TreeNode<T>>();
        this.children.add(child1);
        this.children.add(child2);
    }

    TreeNode addChild(TreeNode child) {
        child.parent = this;
        this.children.add(child);
        return child;
    }

    @Override
    public Iterator<TreeNode<T>> iterator() {
        return null;
    }
}



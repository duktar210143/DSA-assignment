// Question 6
// a)	Implement Huffman encoding and decoding.

import java.util.PriorityQueue;
import java.util.Comparator;

class HuffmanNode {
    int data;
    char charData;
    HuffmanNode left;
    HuffmanNode right;
}

class ImplementComparator implements Comparator < HuffmanNode > {
    public int compare(HuffmanNode node1, HuffmanNode node2) {
        return node1.data - node2.data;
    }
}

public class QN6a {

    public static boolean isLetter(char c) {
        return Character.isLetter(c);
    }

    public static void printCode(HuffmanNode node, String s) {
        if (node.left == null && node.right == null && isLetter(node.charData)) {
            System.out.println(node.charData + "   |  " + s);
            return;
        }

        printCode(node.left, s + "0");
        printCode(node.right, s + "1");
    }

    public static void main(String[] args) {

        int n = 4;

        char[] charArray = {
                'B',
                'C',
                'A',
                'D'
        };
        int[] char_frequency = {
                1,
                6,
                5,
                3
        };

        PriorityQueue < HuffmanNode > queue = new PriorityQueue < HuffmanNode > (n, new ImplementComparator());

        for (int in = 0; in < n; in ++) {
            HuffmanNode node = new HuffmanNode();

            node.charData = charArray[ in ];
            node.data = char_frequency[ in ];

            node.left = null;
            node.right = null;

            queue.add(node);
        }

        HuffmanNode root = null;

        while (queue.size() > 1) {

            HuffmanNode min_freq_node = queue.poll();
            HuffmanNode second_min_freq_node = queue.poll();

            HuffmanNode f_node = new HuffmanNode();

            f_node.data = min_freq_node.data + second_min_freq_node.data;
            f_node.charData = '-';
            f_node.left = min_freq_node;
            f_node.right = second_min_freq_node;

            root = f_node;

            queue.add(f_node);
        }
        printCode(root, "");
    }
}

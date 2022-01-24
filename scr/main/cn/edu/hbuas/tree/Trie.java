package cn.edu.hbuas.tree;

public  class Trie {

    private TrieNode root;

    public Trie() {
        this.root = new TrieNode();
    }


    public void insert(String word) {
        if (word == null) {
            return ;
        }
        char[] chs = word.toCharArray();
        TrieNode node = root;
        int index = 0;
        for (int i = 0; i < chs.length; i++) {
            index = chs[i] - 'a';
            if (node.next[index] == null) {
                node.next[index] = new TrieNode();
            }
            node = node.next[index];
            //沿途的path都++
            node.path++;
        }
        //最后一个节点的end++
        node.end++;

    }

    public void delete(String word) {
        if (search(word) != 0) {
            char[] chs = word.toCharArray();
            TrieNode node = root;
            int index = 0;
            for (int i = 0; i < chs.length; i++) {
                index = chs[i] - 'a';
                //根据path的值，来决定是否释放掉那个节点
                if (--node.next[index].path == 0) {
                    node.next[index] = null;
                    return;
                }
                node = node.next[index];
            }
            node.end--;
        }

    }


    /**
    *  查某个word插入了几次
    */
    public int search(String word) {
        if (word == null) {
            return 0;
        }
        char[] chs = word.toCharArray();
        TrieNode node = root;
        int index = 0;
        for (int i = 0; i < chs.length; i++) {
            index = chs[i] - 'a';
            if (node.next[index] == null) {
                return 0;
            }
            node = node.next[index];
        }
        return node.end;
    }

    /**
     *  查某个字符串的以它作为前缀的有多少
     */
    public int prefixNumber(String pre) {
        if (pre == null) {
            return 0;
        }
        char[] chs = pre.toCharArray();
        TrieNode node = root;
        int index = 0;
        for (int i = 0; i < chs.length; i++) {
            index = chs[i] - 'a';
            if (node.next[index] == null) {
                return 0;
            }
            node = node.next[index];
        }
        return node.path;
    }


    private static class TrieNode {
        //有多少字符串到达过这个节点
        private int path;
        //有多少字符串以这个节点结尾
        private int end;
        //路：如果规定字符为a-z，所以每个节点都有26条路，会有一些路不存在，即下方的节点为null
        private TrieNode[] next;

        public TrieNode() {
            this.path = 0;
            this.end = 0;
            this.next = new TrieNode[26];
        }
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("search");
        trie.insert("get");
        trie.insert("get");
        trie.insert("game");
        System.out.println(trie.search("get"));
        System.out.println(trie.prefixNumber("g"));
        trie.delete("get");
        System.out.println(trie.search("get"));
    }
}

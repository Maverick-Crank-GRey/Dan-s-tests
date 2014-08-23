package dan.code.test.treecutter;

/**
 * Created by maverick on 8/22/14.
 */
public class RecursiveCutter implements Cutter {
    public static final int DEAD_INDEX = -1;
    private final int[] tree;

    /**
     * Instantiate a class with a tree structure encoded as an array.
     * The array entries contains indexes of parent entry and so forth.
     *
     * @param tree The tree encoded as an array.
     */
    public RecursiveCutter(int[] tree) {
        this.tree = tree;
    }

    @Override
    public int[] cut(int[] condemnedIndexes) {
        int numberOfDeadElements = markDeadBranches(tree, condemnedIndexes);
        final int newTreeSize = tree.length - numberOfDeadElements;
        return createNewTree(tree, newTreeSize);
    }

    static int markDeadBranches(int[] tree, int[] condemnedIndexes) {
        int numberOfDeadElements = 0;
        for (int condemned : condemnedIndexes) {
            tree[condemned] = DEAD_INDEX;
            numberOfDeadElements++;
            for (int i = 0; i < tree.length; i++) {
                final int value = tree[i];
                if (value == condemned) {
                    int numberOfDeadSubElements = markDeadBranches(tree, new int[]{i});
                    numberOfDeadElements += numberOfDeadSubElements;
                }
            }
        }
        return numberOfDeadElements;
    }

    static int[] createNewTree(final int[] originalTree, final int newTreeSize) {
        final int[] newTree = new int[newTreeSize];
        int newTreeIndex = 0;
        for (final int value : originalTree) {
            if (value != DEAD_INDEX) {
                newTree[newTreeIndex++] = value;
            }
        }
        return newTree;
    }
}

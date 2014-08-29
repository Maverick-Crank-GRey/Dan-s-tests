package dan.code.test.treecutter;

/**
 * Created by maverick on 8/26/14.
 */
public class DumbCutter {
    public static final int DEAD_INDEX = -1;
    protected final int[] tree;

    /**
     * Instantiate a class with a tree structure encoded as an array.
     * The array entries contains indexes of parent entry and so forth.
     *
     * @param tree The tree encoded as an array.
     */
    public DumbCutter(int[] tree) {
        this.tree = tree;
    }

    int[] createNewTree(final int newTreeSize) {
        final int[] newTree = new int[newTreeSize];
        int newTreeIndex = 0;
        for (final int value : tree) {
            if (value != DEAD_INDEX) {
                newTree[newTreeIndex++] = value;
            }
        }
        return newTree;
    }
}

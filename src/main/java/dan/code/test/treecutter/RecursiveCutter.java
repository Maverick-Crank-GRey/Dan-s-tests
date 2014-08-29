package dan.code.test.treecutter;

/**
 * Created by maverick on 8/22/14.
 */
public class RecursiveCutter extends DumbCutter implements Cutter {

    /**
     * Instantiate a class with a tree structure encoded as an array.
     * The array entries contains indexes of parent entry and so forth.
     *
     * @param tree The tree encoded as an array.
     */
    public RecursiveCutter(int[] tree) {
        super(tree);
    }

    @Override
    public int[] cut(int[] condemnedIndexes) {
        int numberOfDeadElements = markDeadBranches(tree, condemnedIndexes);
        final int newTreeSize = tree.length - numberOfDeadElements;
        return createNewTree(newTreeSize);
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
}

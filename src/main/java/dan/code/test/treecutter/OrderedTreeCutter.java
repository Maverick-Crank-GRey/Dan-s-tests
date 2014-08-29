package dan.code.test.treecutter;

/**
 * Created by maverick on 8/26/14.
 */
public class OrderedTreeCutter extends DumbCutter implements Cutter {

    public OrderedTreeCutter(int[] tree) {
        super(tree);
    }

    @Override
    public int[] cut(int[] condemnedIndexes) {
        int numberOfDeadElements = 0;
        for (final int condemnedIndex : condemnedIndexes) {
            tree[condemnedIndex] = DEAD_INDEX;
            numberOfDeadElements++;

            int nextVictimIndex = condemnedIndex + 1;
            while (nextVictimIndex < tree.length) {
                final int nextVictimRef = tree[nextVictimIndex];

                final boolean aSiblingBranch = (nextVictimRef < condemnedIndex);
                final boolean aNewBranchRoot = (nextVictimRef == nextVictimIndex);  // e.g. 10[10]
                final boolean outOfTheCondemnedBranch = (aSiblingBranch || aNewBranchRoot);

                if (outOfTheCondemnedBranch) break;

                tree[nextVictimIndex++] = DEAD_INDEX;
                numberOfDeadElements++;
            }
        }

        return createNewTree(tree.length - numberOfDeadElements);
    }
}

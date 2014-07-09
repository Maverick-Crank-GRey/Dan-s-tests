package dan.code.test;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsEmptyCollection.empty;

/**
 * Created by maverick on 7/7/14.
 */
public class ParserTestSeveralShotWordsDictionary {

    private Parser parser;

    @Before
    public void setUp() throws Exception {
        parser = new Parser(ImmutableSet.of("a", "b", "c"));
    }

    @Test
    public void testInnerParse_severalWordLine() throws Exception {
        try {
            parser.parse(null, "");
            Assert.fail("The inner parse method cannot be called with a null collection!");
        } catch (NullPointerException expected) {}

        Assert.assertThat(
                parser.parse(ImmutableList.<String>of(), "abc"),
                hasItem(ImmutableList.of("a", "b", "c")));

        Assert.assertThat(
                parser.parse(ImmutableList.of("a"), "bc"),
                hasItem(ImmutableList.of("a", "b", "c")));

        Assert.assertThat(
                parser.parse(ImmutableList.of("a", "b"), "c"),
                hasItem(ImmutableList.of("a", "b", "c")));


        try {
            parser.parse(ImmutableList.of("a", "b", "c"), "");
            Assert.fail("The inner parse method cannot be called with an empty line!");
        } catch (IllegalArgumentException expected) {}
    }

    @Test
    public void testParse_oneComplexWordLine() throws Exception {
        final Set<List<String>> lines = parser.parse("abcd");

        Assert.assertThat("The parser must read whole line.",
                lines, is(empty()));
    }
}

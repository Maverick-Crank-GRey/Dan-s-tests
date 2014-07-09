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
public class ParserTestOneWordDictionary {

    private Parser parser;

    @Before
    public void setUp() throws Exception {
        parser = new Parser(ImmutableSet.of("a"));
    }

    @Test
    public void testParse_emptyLine() throws Exception {
        final Set<List<String>> lines = parser.parse("");

        Assert.assertThat("One does not simply find a word in an empty string!",
                lines, is(empty()));
    }

    @Test
    public void testParse_oneWordLine() throws Exception {
        final Set<List<String>> lines = parser.parse("a");

        final List<String> line = ImmutableList.of("a");
        Assert.assertThat(lines, hasItem(line));
    }

    @Test
    public void testInnerParse_secondWordLine() throws Exception {
        final Set<List<String>> lines = parser.parse(ImmutableList.of("a"), "a");

        final List<String> line = ImmutableList.of("a", "a");
        Assert.assertThat(lines, hasItem(line));
    }

    @Test
    public void testInnerParse_threeWordLine() throws Exception {
        Assert.assertThat(
                parser.parse(ImmutableList.<String>of(), "aaa"),
                hasItem(ImmutableList.of("a", "a", "a")));

        Assert.assertThat(
                parser.parse(ImmutableList.of("a"), "aa"),
                hasItem(ImmutableList.of("a", "a", "a")));

        Assert.assertThat(
                parser.parse(ImmutableList.of("a", "a"), "a"),
                hasItem(ImmutableList.of("a", "a", "a")));
    }

    @Test
    public void testInnerParse_oneWordOutOfDictionaryLine() throws Exception {
        final Set<List<String>> lines = parser.parse(ImmutableList.of("a"), "b");

        Assert.assertThat("The parser must recognize all words in the line.",
                lines, is(empty()));
    }
}

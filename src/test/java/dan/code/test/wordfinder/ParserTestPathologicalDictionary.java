package dan.code.test.wordfinder;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import dan.code.test.wordfinder.Parser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Set;

import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;


/**
 * Created by maverick on 7/7/14.
 */
public class ParserTestPathologicalDictionary {

    private Parser parser;

    @Before
    public void setUp() throws Exception {
        parser = new Parser(ImmutableSet.of("a", "aa", "aaa", "aaaa"));
    }

    @Test
    public void testParse() throws Exception {
        final Set<List<String>> lines = parser.parse("aaaa");

        Assert.assertThat(lines, containsInAnyOrder((List<String>)
                ImmutableList.of("a", "a", "a", "a"),
                ImmutableList.of("a", "aa", "a"),
                ImmutableList.of("a", "a", "aa"),
                ImmutableList.of("a", "aaa"),

                ImmutableList.of("aa", "a", "a"),
                ImmutableList.of("aa", "aa"),
                ImmutableList.of("aaa", "a"),
                ImmutableList.of("aaaa")
        ));
    }
}

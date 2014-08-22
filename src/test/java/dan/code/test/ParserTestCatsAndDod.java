package dan.code.test;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Set;

import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;


/**
 * Created by maverick on 7/7/14.
 */
public class ParserTestCatsAndDod {

    private Parser parser;

    @Before
    public void setUp() throws Exception {
        parser = new Parser(ImmutableSet.of("cat", "cats", "and", "sand", "dog"));
    }

    @Test
    public void testParse() throws Exception {
        final Set<List<String>> lines = parser.parse("catsanddog");

        Assert.assertThat(lines, containsInAnyOrder((List<String>)
                ImmutableList.of("cats", "and", "dog"),
                ImmutableList.of("cat", "sand", "dog")
        ));
    }
}

package ca.wbac;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class HelperTest {
	Helper helper = new Helper();
	
	@Test
	public void shouldReturnTrueWhenValueExists() {
		assertThat(helper.existy(1L), is(true));
	}
	
	@Test
	public void shouldRemoveFirstItem() {
		List<Long> testData = Arrays.asList(new Long[]{1L, 2L, 3L});
		List<Long> expected = Arrays.asList(new Long[]{2L, 3L});
		assertThat(helper.rest(testData), is(equalTo(expected)));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void shouldMergeCollections() {
		List<Long> list1 = Arrays.asList(new Long[]{1L, 2L, 3L});
		List<Long> list2 = Arrays.asList(new Long[]{4L, 5L});
		List<Long> list3 = Arrays.asList(new Long[]{6L, 7L});
		assertThat(helper.cat(list1, list2, list3), is(equalTo(Arrays.asList(new Long[] {1L, 2L, 3L, 4L, 5L, 6L, 7L }))));
	}

}

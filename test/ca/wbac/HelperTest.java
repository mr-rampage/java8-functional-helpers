package ca.wbac;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class HelperTest {
	Helper helper = new Helper();
	
	private class Dummy {
		private Long buffer;

		public Long getBuffer() {
			return buffer;
		}

		public void setBuffer(Long buffer) {
			this.buffer = buffer;
		}
		
	}
	
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

	@Test
	public void shouldProcessItemWhenPredicateIsTrue() {
		Dummy dummy = new Dummy();
		helper.process(5, x -> x > 3, x -> x * 2L, dummy::setBuffer);
		assertThat( dummy.getBuffer(), is(equalTo(10L)));
	}
	
	@Test
	public void shouldNotProcessItemWhenPredicateIsFalse() {
		Dummy dummy = new Dummy();
		helper.process(2L, x -> x < 0, x -> x, dummy::setBuffer);
		assertThat( dummy.getBuffer(), is(equalTo(null)));
	}

}

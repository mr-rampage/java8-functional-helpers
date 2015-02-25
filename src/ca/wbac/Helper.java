package ca.wbac;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class Helper {
	
	public <E> Collection<E> rest(Collection<E> list) {
		return doWhen(list, 
				value -> existy(value) && value.size() > 0, 
				value -> value.stream().skip(1).collect(Collectors.toList()));
	}
	
	public <T> boolean existy(T value) {
		return Optional.ofNullable(value).isPresent();
	}
	
	public <T, K> K doWhen(T value, Predicate<T> condition, Function<T, K> action) {
		if (condition.test(value)) {
			return action.apply(value);
		}
		return null;
	}
	
	public <E> Collection<E> cat(@SuppressWarnings("unchecked") Collection<E>... collections) {
		return Arrays.stream(collections)
					 .filter(this::existy)
					 .flatMap(Collection::stream)
					 .collect(Collectors.toList());
	}

}

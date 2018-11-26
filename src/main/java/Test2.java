import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test2 {

	public static void main(String[] args) {

		List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");

		Collections.sort(names, new Comparator<String>() {
			@Override
			public int compare(String a, String b) {
				return b.compareTo(a);
			}
		});
		
		System.out.println("ddddddd");
		
		Comparator<String> cc = (a, b) -> b.compareTo(a);

		Collections.sort(names, (a, b) -> b.compareTo(a));
		System.out.println(names);

		System.out.println("fffffffddddddfffffff");
		String str = "123";

		Converter<String, Integer> converter = (from) -> Integer.parseInt(from + str);

		Integer converted = converter.convert("123");

		System.out.println(converted);

		List<String> stringCollection = new ArrayList<>();
		stringCollection.add("ddd2");
		stringCollection.add("aaa2");
		stringCollection.add("bbb1");
		stringCollection.add("aaa1");
		stringCollection.add("bbb3");
		stringCollection.add("ccc");
		stringCollection.add("bbb2");
		stringCollection.add("ddd1");

		// stringCollection.stream().filter((s) ->
		// s.startsWith("a")).forEach(System.out::println);

		/*stringCollection.stream().sorted().filter((s) -> s.startsWith("a")).forEach((a) -> {
			String str1 = "lizw";
			System.out.println(str1 + a);
		});

		stringCollection.stream().map(String::toUpperCase).sorted((a, b) -> b.compareTo(a))
				.forEach(System.out::println);*/
		
		
/*		boolean anyStartsWithA =stringCollection.stream().anyMatch((s) -> s.startsWith("a"));

			System.out.println(anyStartsWithA);      // true
*/			
		 
		Stream<String> test = Stream.of("aa","bb","cc");

		List<Person> persons = Arrays.asList(new Person("Max", 18), new Person("Peter", 23), new Person("Pamela", 23),
				new Person("David", 12));
		
		
		List<Person> filtered = persons.stream().filter( (s)->s.name.startsWith("P")).collect(Collectors.toList());
		
		//List<Person> filtered2 = persons.stream().collect(Collectors.groupingBy(  p-> p.age ));
		
		
		Map<Integer, List<Person>> personsByAge =   persons.stream().collect(Collectors.groupingBy(p -> p.age));
		 
		
		personsByAge.forEach((age, p) -> System.out.format("age %s: %s\n", age, p));
		
		Runnable task = () -> {
		    String threadName = Thread.currentThread().getName();
		    System.out.println("Hello " + threadName);
		};

		task.run();
		Thread thread = new Thread(task);
		thread.start();
	System.out.println("asdfasfasf");
		System.out.println("Done!");
		System.out.println("asdfasfasf");
	}

}

@FunctionalInterface
interface Converter<F, T> {
	T convert(F from);

	boolean equals(Object obj);

}
class Person {
    String name;
    int age;

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return name;
    }
}

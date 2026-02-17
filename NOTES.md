# Notes

## Java

### Generic Syntax & PECS = Producer Extends, Consumer Super

List<? extends Number> numbers = List.of(1, 2.0);
Number n = numbers.get(0); // okay
numbers.add(3); // ❌ nicht erlaubt

List<? super Integer> ints = new ArrayList<Number>();
ints.add(42); // okay
Number o = ints.get(0); // nur Object sicher

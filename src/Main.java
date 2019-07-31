public class Main {
    public static void main(String[] args) {
        Array<String> arr = new Array<>();
        //System.out.println(arr);
        arr.add("text1");
        //arr.add(1);
        arr.add("text2");
        for(ArrayIterator<String> stringArrayIterator = arr.iterator(); stringArrayIterator.hasNext();){
            System.out.println(stringArrayIterator.next());
        }
        Array<Integer> arr1 = new Array<>();
        arr1.add(1);
        //arr1.add("text");
        arr1.add(2);
        for(ArrayIterator<Integer> integerArrayIterator = arr1.iterator();integerArrayIterator.hasNext();){
            System.out.println(integerArrayIterator.next());
        }
    }

}

import java.util.Arrays;
import java.util.concurrent.TimeoutException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.println("Hello Welcome to Assignment of Binary tree!");
        Searchable testTree = new WorkingSetTree();

//        System.out.println(testTree.add(""));
        System.out.println(testTree.add("orange"));
        System.out.println(testTree.add("salsa"));
        System.out.println(testTree.add("banana"));
        System.out.println(testTree.add("mango"));
        System.out.println(testTree.add("apple"));
//        System.out.println(testTree.remove("banana"));
        System.out.println(testTree.add("tiger"));
        System.out.println(testTree.add("strawberry"));
//        System.out.println(Arrays.toString(testTree.serialize()));
//        System.out.println(testTree.remove("salsa"));
        System.out.println("XX"+testTree.find("apple"));

        System.out.println(Arrays.toString(testTree.serialize()));

//            System.out.println("Delete element: " +testTree.remove("orange"));
        System.out.println("Delete element: " +testTree.find("tiger"));

//        System.out.println("Find element: " + testTree.find("banana"));
//        System.out.println("Add same element: " + testTree.add("tiger"));
//        System.out.println("Delete Element "+testTree.remove("strawberry"));
        System.out.println("ee" + Arrays.toString(testTree.serialize()));

//        System.out.println(testTree.add("strawberry"));
//        System.out.println("Find element: " + testTree.find("banana"));
//        System.out.println("Find Element new " + testTree.find("banana"));
//        System.out.println("Level of:" + testTree.levelOf(("orange")));
//        System.out.println("Size of tree: " + testTree.size());

        String[] rebuild = {null,"Halifax" ,"APple","Shrey",null,null,"SAS","TERE",null,null,null,null,null,null,null};
        System.out.println("Rebuilded Tree is:"+testTree.rebuild(rebuild));
//        System.out.println("Delete element: " + testTree.add("SINH"));


        System.out.println(Arrays.toString(testTree.serialize()));
        String[] arrayCheck = testTree.serialize();
//        System.out.println("Size of tree: " + testTree.size());


//        System.out.println("Rebuilded Tree is:"+testTree.rebuild(arrayCheck));

//        System.out.println(testTree.add("shrey"));



    }
}
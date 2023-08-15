package examples;

class MyClass implements Cloneable {
    private int value;

    public MyClass(int value) {
        this.value = value;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}

public class DeepCopyExample {
    public static void main(String[] args) {
        MyClass originalObject = new MyClass(42);

        try {
            MyClass copiedObject = (MyClass) originalObject.clone();

            System.out.println("Original value: " + originalObject.getValue());
            System.out.println("Copied value: " + copiedObject.getValue());

            copiedObject.setValue(99);

            System.out.println("Original value after modifying copied object: " + originalObject.getValue());
            System.out.println("Copied value after modification: " + copiedObject.getValue());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}
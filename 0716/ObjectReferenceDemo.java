public class ObjectReferenceDemo {
    public static void main(String[] args) {
        Product1 first = new Product1("Mouse", 490, 20);
        Product1 second = first;

        second.restock(5);

        System.out.println("first 的庫存：" + first.getStock());
        System.out.println("second 的庫存：" + second.getStock());
        System.out.println("是否指向同一物件：" + (first == second));
    }
}
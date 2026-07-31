import java.util.ArrayList;

public class ProductArrayListDemo {
    public static void main(String[] args) {
        ArrayList<Product1> products = new ArrayList<>();
        products.add(new Product1("P01", "Keyboard", 10));
        products.add(new Product1("P02", "Mouse", 6));
        products.add(new Product1("P03", "Monitor", 3));
        
        Product1 found = findByCode(products, "P02");
        if (found != null) {
            found.addStock(4);
        }
        
        for (Product1 product : products) {
            System.out.println(product);
        }
    }
    
    public static Product1 findByCode(ArrayList<Product1> products, String code) {
        for (Product1 product : products) {
            if (product.getCode().equals(code)) {
                return product;
            }
        }
        return null;
    }
}
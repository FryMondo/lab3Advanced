public class SQLGeneratorDemo {
    public static void main(String[] args) {
        User user = new User(1, "Alice", "alice@example.com");
        Cat cat = new Cat(1, "Whiskers", 3);
        Product product = new Product(1, "Laptop", 1200.50);

        SQLGenerator<User> userSQLGenerator = new SQLGenerator<>(User.class);
        SQLGenerator<Cat> catSQLGenerator = new SQLGenerator<>(Cat.class);
        SQLGenerator<Product> productSQLGenerator = new SQLGenerator<>(Product.class);

        // Демонстрація SQL генерації
        System.out.println(userSQLGenerator.generateCreateTable());
        System.out.println(userSQLGenerator.generateInsert(user));
        System.out.println(userSQLGenerator.generateSelectAll());
        System.out.println(userSQLGenerator.generateDeleteById("id", 1));

        System.out.println(catSQLGenerator.generateCreateTable());
        System.out.println(catSQLGenerator.generateInsert(cat));
        System.out.println(catSQLGenerator.generateSelectAll());
        System.out.println(catSQLGenerator.generateDeleteById("id", 1));

        System.out.println(productSQLGenerator.generateCreateTable());
        System.out.println(productSQLGenerator.generateInsert(product));
        System.out.println(productSQLGenerator.generateSelectAll());
        System.out.println(productSQLGenerator.generateDeleteById("id", 1));
    }
}

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// Клас генератора SQL
class SQLGenerator<T> {
    private final Class<T> type;

    public SQLGenerator(Class<T> type) {
        this.type = type;
    }

    public String generateCreateTable() {
        if (!type.isAnnotationPresent(Table.class)) {
            throw new IllegalArgumentException("Class " + type.getName() + " is not annotated with @Table");
        }
        String tableName = type.getAnnotation(Table.class).name();
        List<String> columns = Arrays.stream(type.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Column.class))
                .map(field -> field.getAnnotation(Column.class).name() + " "
                        + mapFieldTypeToSQLType(field.getType()))
                .collect(Collectors.toList());

        return "CREATE TABLE " + tableName + " (" + String.join(", ", columns) + ");";
    }

    public String generateInsert(T object) {
        if (!type.isAnnotationPresent(Table.class)) {
            throw new IllegalArgumentException("Class " + type.getName() + " is not annotated with @Table");
        }
        String tableName = type.getAnnotation(Table.class).name();
        List<String> columnNames = new ArrayList<>();
        List<String> values = new ArrayList<>();

        try {
            for (Field field : type.getDeclaredFields()) {
                if (field.isAnnotationPresent(Column.class)) {
                    field.setAccessible(true);
                    columnNames.add(field.getAnnotation(Column.class).name());
                    values.add("'" + field.get(object).toString() + "'");
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Error accessing field values", e);
        }

        return "INSERT INTO " + tableName + " (" + String.join(", ", columnNames)
                + ") VALUES (" + String.join(", ", values) + ");";
    }

    public String generateSelectAll() {
        if (!type.isAnnotationPresent(Table.class)) {
            throw new IllegalArgumentException("Class " + type.getName() + " is not annotated with @Table");
        }
        String tableName = type.getAnnotation(Table.class).name();
        return "SELECT * FROM " + tableName + ";";
    }

    public String generateDeleteById(String idColumn, Object idValue) {
        if (!type.isAnnotationPresent(Table.class)) {
            throw new IllegalArgumentException("Class " + type.getName() + " is not annotated with @Table");
        }
        String tableName = type.getAnnotation(Table.class).name();
        return "DELETE FROM " + tableName + " WHERE " + idColumn + " = '" + idValue + "';";
    }

    private String mapFieldTypeToSQLType(Class<?> fieldType) {
        if (fieldType == int.class || fieldType == Integer.class) {
            return "INTEGER";
        } else if (fieldType == String.class) {
            return "TEXT";
        } else if (fieldType == double.class || fieldType == Double.class) {
            return "REAL";
        } else {
            throw new IllegalArgumentException("Unsupported field type: " + fieldType.getName());
        }
    }
}
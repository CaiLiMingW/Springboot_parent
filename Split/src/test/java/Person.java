import lombok.Data;
import org.bson.Document;

import java.io.Serializable;

/**
 * @author Ccc
 * @date 2018/11/17 0017 下午 3:24
 */
@Data
public class Person  extends Document implements Serializable {
    private Integer id;
    private String name;
    private Integer age;

    public Person() {
    }

    public Person(Integer id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
}

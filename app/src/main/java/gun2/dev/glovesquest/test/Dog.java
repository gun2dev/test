package gun2.dev.glovesquest.test;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

// RealmObject를 확장하여 모델을 정의합니다
public class Dog extends RealmObject {
    @PrimaryKey
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // ... 생성된 getter와 setter\


    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

}
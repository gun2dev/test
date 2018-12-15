package gun2.dev.glovesquest.db.object;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Character extends RealmObject {

    @PrimaryKey
    private int id;
    private int level;

    private long time = System.currentTimeMillis();

}

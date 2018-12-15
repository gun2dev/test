package gun2.dev.glovesquest.db;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import gun2.dev.glovesquest.db.object.Character;
import io.realm.Realm;

public class RealmInitiate {

    private static final String TAG = "RealmInitiate";

    public RealmInitiate(Context context) {
        Realm.init(context);
        mRealm = Realm.getInstance(RealmController.baseRealmConfig());
        mResource = context.getResources();
    }

    private Realm mRealm;
    private Resources mResource;

    /**
     * 캐릭터 불러오기 및 생성하기
     */
    public Character loadCharacter(int id) {
        Character character = null;
        try {
            character = mRealm.where(Character.class).equalTo("id", id).findFirst();
            //저장된 데이터가 없으면 생성
            if (character == null) {
                mRealm.beginTransaction();
                character = mRealm.createObject(Character.class, id); // 관리 객체를 직접 만듭니다
                mRealm.commitTransaction();
                Log.d(TAG, "Character DB be created");
            }

            Log.d(TAG, "Character DB : " + mRealm.where(Character.class).findAll().toString());
            return character;
        } catch (Exception e) {
            Log.d(TAG, "fail loadCharacter");
        }
        return character;

    }

    /**
     * item Db 불러오기
     */
    /*
    public RealmResults<Item> loadItem(int id, Character ch) {
        RealmResults<Item> itemRealmResults = null;

        //Json파일 읽기
        final AssetManager am = mResource.getAssets();
        InputStream is = null;

        //Item DB값 가져오기
        Realm realm = Realm.getInstance(RealmController.baseRealmConfig());
        itemRealmResults = realm.where(Item.class).equalTo("character.id", id).findAll();
        try {
            Gson gson = new Gson();
//            Reader reader = new InputStreamReader(is);
            //////////////////////////////복호화 시점
            //assets의 item list읽기
            is = am.open("enItemList", AssetManager.ACCESS_BUFFER);
            int fileSize = is.available();
            byte[] tmpData = new byte[fileSize];
            is.read(tmpData);
            is.close();

            Reader tmpReader = SecretFile.decodeFile(tmpData);

            //////////////////////////////


            Log.d(TAG, "reader : "+tmpReader.toString());
            Item[] items = gson.fromJson(tmpReader, Item[].class);
            tmpReader.close();
            for (Item item : items) {
                Log.d(TAG, "item : "+item.toString());
                //테스트 후 삭제
            }

            //스냅샷
            OrderedRealmCollectionSnapshot<Item> itemSnapshot = itemRealmResults.createSnapshot();
            //Item DB가 비어있으면
            if (itemSnapshot.isEmpty()) {
                InputStream input = am.open("itemList");
                realm.beginTransaction();
                realm.createAllFromJson(Item.class, input);
                realm.commitTransaction();
                RealmResults<Item> tmpItems = realm.where(Item.class).isNull("character").findAll();
                if (!tmpItems.isEmpty()){
                    //스냅샷
                    OrderedRealmCollectionSnapshot<Item> tmpItemsSnapshot = tmpItems.createSnapshot();
                    realm.beginTransaction();
                    for (Item item : tmpItemsSnapshot){
                        item.setCharacter(ch);
                    }
                    realm.commitTransaction();

                }
                input.close();
            }
            //최신 Item이 존재하는지 확인 (업데이트 위함)
            else {
                for (Item item : items) {
                    Item col = realm.where(Item.class)
                            .equalTo("id", item.getId())
                            .equalTo("character.id", id)
                            .findFirst();
                    if (col == null) {
                        realm.beginTransaction();
                        realm.createObjectFromJson(Item.class, item.toJson());
                        realm.where(Item.class).isNull("character").findFirst().setCharacter(ch);
                        realm.commitTransaction();
                    }
                }
            }
            is.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        if (is != null) {
            try {
                is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        realm.close();
        return itemRealmResults;
    }
*/

    //close

    public void closeRealm(){
        mRealm.close();
    }

}

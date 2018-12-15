package gun2.dev.glovesquest.db.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;

import java.io.InputStream;

import gun2.dev.glovesquest.db.RealmController;
import gun2.dev.glovesquest.db.data.BaseAbility;
import gun2.dev.glovesquest.db.data.CrtLevelUpExp;
import gun2.dev.glovesquest.db.data.DmgIncreaseInfo;
import gun2.dev.glovesquest.db.data.EffectSetInfo;
import gun2.dev.glovesquest.db.data.Egg;
import gun2.dev.glovesquest.db.data.Gloves;
import gun2.dev.glovesquest.db.data.GlovesEnchant;
import gun2.dev.glovesquest.db.data.GlovesGrade;
import gun2.dev.glovesquest.db.data.GlovesValue;
import gun2.dev.glovesquest.db.data.Gold;
import gun2.dev.glovesquest.db.data.Language;
import gun2.dev.glovesquest.db.data.Monster;
import gun2.dev.glovesquest.db.data.MonsterHuntQuest;
import gun2.dev.glovesquest.db.data.MonsterSkill;
import gun2.dev.glovesquest.db.data.PurchInfo;
import gun2.dev.glovesquest.db.data.SoundSetInfo;
import gun2.dev.glovesquest.db.data.SpecialAttackInfo;
import gun2.dev.glovesquest.db.data.Stage;
import gun2.dev.glovesquest.db.data.Twig;
import gun2.dev.glovesquest.utils.LogManager;
import io.realm.Realm;
import io.realm.RealmResults;

public class BaseDbInitiateManager {

    private LogManager Log = new LogManager(getClass().getName().trim());
    private Realm mRealm;
    private AssetManager am;
    private InputStream is = null;
    private Resources mResource;
    private String[] BaseDbArray = {

            "language.json", "costume.json", "dmgIncreaseInfo.json", "twig.json", "purchInfo.json",
            "soundSetInfo.json", "effectSetInfo.json", "gold.json", "egg.json", "glovesEnchant.json",
            "glovesGrade.json", "globesValue.json", "gloves.json", "monsterSkill.json", "monster.json",
            "monsterHuntQuest.json", "stage.json", "specialAttackInfo.json", "baseAbility.json", "crtLevelUpExp",

    };

    public BaseDbInitiateManager(Context context, Resources resources){

        Realm.init(context);
        mResource = resources;
        mRealm = Realm.getInstance(RealmController.baseRealmConfig());
        am = mResource.getAssets();

    }

    public void InitiateDb(){

        try{
            Log.msg("init language.json");
            InputStream input = am.open("language.json");

            mRealm.beginTransaction();
            mRealm.createOrUpdateAllFromJson(Language.class, input);
            mRealm.commitTransaction();
            input.close();

            RealmResults<Language> RealmList = mRealm.where(Language.class).findAll();

            for (Language language : RealmList){
                Log.msg(language.toString());
            }
        }catch (Exception e){
            Log.msg("language.json err");
        }


        try{
            Log.msg("init dmgIncreaseInfo.json");
            InputStream input = am.open("dmgIncreaseInfo.json");

            mRealm.beginTransaction();
            mRealm.createOrUpdateAllFromJson(DmgIncreaseInfo.class, input);
            mRealm.commitTransaction();
            input.close();

            RealmResults<DmgIncreaseInfo> RealmList = mRealm.where(DmgIncreaseInfo.class).findAll();

            for (DmgIncreaseInfo ob : RealmList){
                Log.msg(ob.toString());
            }
        }catch (Exception e){
            Log.msg("dmgIncreaseInfo.json err");
        }

        try{
            Log.msg("init twig.json");
            InputStream input = am.open("twig.json");

            mRealm.beginTransaction();
            mRealm.createOrUpdateAllFromJson(Twig.class, input);
            mRealm.commitTransaction();
            input.close();

            RealmResults<Twig> RealmList = mRealm.where(Twig.class).findAll();

            for (Twig ob : RealmList){
                Log.msg(ob.toString());
            }
        }catch (Exception e){
            Log.msg("twig.json err");
        }

        try{
            Log.msg("init purchInfo.json");
            InputStream input = am.open("purchInfo.json");

            mRealm.beginTransaction();
            mRealm.createOrUpdateAllFromJson(PurchInfo.class, input);
            mRealm.commitTransaction();
            input.close();

            RealmResults<PurchInfo> RealmList = mRealm.where(PurchInfo.class).findAll();

            for (PurchInfo ob : RealmList){
                Log.msg(ob.toString());
            }
        }catch (Exception e){
            Log.msg("purchInfo.json err");
        }

        /************/

        try{
            Log.msg("init soundSetInfo.json");
            InputStream input = am.open("soundSetInfo.json");

            mRealm.beginTransaction();
            mRealm.createOrUpdateAllFromJson(SoundSetInfo.class, input);
            mRealm.commitTransaction();
            input.close();

            RealmResults<SoundSetInfo> RealmList = mRealm.where(SoundSetInfo.class).findAll();

            for (SoundSetInfo ob : RealmList){
                Log.msg(ob.toString());
            }
        }catch (Exception e){
            Log.msg("soundSetInfo.json err");
        }

        try{
            Log.msg("init effectSetInfo.json");
            InputStream input = am.open("effectSetInfo.json");

            mRealm.beginTransaction();
            mRealm.createOrUpdateAllFromJson(EffectSetInfo.class, input);
            mRealm.commitTransaction();
            input.close();

            RealmResults<EffectSetInfo> RealmList = mRealm.where(EffectSetInfo.class).findAll();

            for (EffectSetInfo ob : RealmList){
                Log.msg(ob.toString());
            }
        }catch (Exception e){
            Log.msg("effectSetInfo.json err");
        }

        try{
            Log.msg("init gold.json");
            InputStream input = am.open("gold.json");

            mRealm.beginTransaction();
            mRealm.createOrUpdateAllFromJson(Gold.class, input);
            mRealm.commitTransaction();
            input.close();

            RealmResults<Gold> RealmList = mRealm.where(Gold.class).findAll();

            for (Gold ob : RealmList){
                Log.msg(ob.toString());
            }
        }catch (Exception e){
            Log.msg("gold.json err");
        }

        try{
            Log.msg("init egg.json");
            InputStream input = am.open("egg.json");

            mRealm.beginTransaction();
            mRealm.createOrUpdateAllFromJson(Egg.class, input);
            mRealm.commitTransaction();
            input.close();

            RealmResults<Egg> RealmList = mRealm.where(Egg.class).findAll();

            for (Egg ob : RealmList){
                Log.msg(ob.toString());
            }
        }catch (Exception e){
            Log.msg("egg.json err");
        }

        try{
            Log.msg("init glovesEnchant.json");
            InputStream input = am.open("glovesEnchant.json");

            mRealm.beginTransaction();
            mRealm.createOrUpdateAllFromJson(GlovesEnchant.class, input);
            mRealm.commitTransaction();
            input.close();

            RealmResults<GlovesEnchant> RealmList = mRealm.where(GlovesEnchant.class).findAll();

            for (GlovesEnchant ob : RealmList){
                Log.msg(ob.toString());
            }
        }catch (Exception e){
            Log.msg("glovesEnchant.json err");
        }

        /*************/

        try{
            Log.msg("init glovesGrade.json");
            InputStream input = am.open("glovesGrade.json");

            mRealm.beginTransaction();
            mRealm.createOrUpdateAllFromJson(GlovesGrade.class, input);
            mRealm.commitTransaction();
            input.close();

            RealmResults<GlovesGrade> RealmList = mRealm.where(GlovesGrade.class).findAll();

            for (GlovesGrade ob : RealmList){
                Log.msg(ob.toString());
            }
        }catch (Exception e){
            Log.msg("glovesGrade.json err");
        }

        try{
            Log.msg("init glovesValue.json");
            InputStream input = am.open("glovesValue.json");

            mRealm.beginTransaction();
            mRealm.createOrUpdateAllFromJson(GlovesValue.class, input);
            mRealm.commitTransaction();
            input.close();

            RealmResults<GlovesValue> RealmList = mRealm.where(GlovesValue.class).findAll();

            for (GlovesValue ob : RealmList){
                Log.msg(ob.toString());
            }
        }catch (Exception e){
            Log.msg("glovesValue.json err");
        }

        try{
            Log.msg("init gloves.json");
            InputStream input = am.open("gloves.json");

            mRealm.beginTransaction();
            mRealm.createOrUpdateAllFromJson(Gloves.class, input);
            mRealm.commitTransaction();
            input.close();

            RealmResults<Gloves> RealmList = mRealm.where(Gloves.class).findAll();

            for (Gloves ob : RealmList){
                Log.msg(ob.toString());
            }
        }catch (Exception e){
            Log.msg("gloves.json err");
        }

        try{
            Log.msg("init monsterSkill.json");
            InputStream input = am.open("monsterSkill.json");

            mRealm.beginTransaction();
            mRealm.createOrUpdateAllFromJson(MonsterSkill.class, input);
            mRealm.commitTransaction();
            input.close();

            RealmResults<MonsterSkill> RealmList = mRealm.where(MonsterSkill.class).findAll();

            for (MonsterSkill ob : RealmList){
                Log.msg(ob.toString());
            }
        }catch (Exception e){
            Log.msg("monsterSkill.json err");
        }

        try{
            Log.msg("init monster.json");
            InputStream input = am.open("monster.json");

            mRealm.beginTransaction();
            mRealm.createOrUpdateAllFromJson(Monster.class, input);
            mRealm.commitTransaction();
            input.close();

            RealmResults<Monster> RealmList = mRealm.where(Monster.class).findAll();

            for (Monster ob : RealmList){
                Log.msg(ob.toString());
            }
        }catch (Exception e){
            Log.msg("monster.json err");
        }

        /****************/

        try{
            Log.msg("init monsterHuntQuest.json");
            InputStream input = am.open("monsterHuntQuest.json");

            mRealm.beginTransaction();
            mRealm.createOrUpdateAllFromJson(MonsterHuntQuest.class, input);
            mRealm.commitTransaction();
            input.close();

            RealmResults<MonsterHuntQuest> RealmList = mRealm.where(MonsterHuntQuest.class).findAll();

            for (MonsterHuntQuest ob : RealmList){
                Log.msg(ob.toString());
            }
        }catch (Exception e){
            Log.msg("monsterHuntQuest.json err");
        }

        try{
            Log.msg("init stage.json");
            InputStream input = am.open("stage.json");

            mRealm.beginTransaction();
            mRealm.createOrUpdateAllFromJson(Stage.class, input);
            mRealm.commitTransaction();
            input.close();

            RealmResults<Stage> RealmList = mRealm.where(Stage.class).findAll();

            for (Stage ob : RealmList){
                Log.msg(ob.toString());
            }
        }catch (Exception e){
            Log.msg("stage.json err");
        }

        try{
            Log.msg("init specialAttackInfo.json");
            InputStream input = am.open("specialAttackInfo.json");

            mRealm.beginTransaction();
            mRealm.createOrUpdateAllFromJson(SpecialAttackInfo.class, input);
            mRealm.commitTransaction();
            input.close();

            RealmResults<SpecialAttackInfo> RealmList = mRealm.where(SpecialAttackInfo.class).findAll();

            for (SpecialAttackInfo ob : RealmList){
                Log.msg(ob.toString());
            }
        }catch (Exception e){
            Log.msg("specialAttackInfo.json err");
        }

        try{
            Log.msg("init baseAbility.json");
            InputStream input = am.open("baseAbility.json");

            mRealm.beginTransaction();
            mRealm.createOrUpdateAllFromJson(BaseAbility.class, input);
            mRealm.commitTransaction();
            input.close();

            RealmResults<BaseAbility> RealmList = mRealm.where(BaseAbility.class).findAll();

            for (BaseAbility ob : RealmList){
                Log.msg(ob.toString());
            }
        }catch (Exception e){
            Log.msg("baseAbility.json err");
        }

        try{
            Log.msg("init crtLevelUpExp.json");
            InputStream input = am.open("crtLevelUpExp.json");

            mRealm.beginTransaction();
            mRealm.createOrUpdateAllFromJson(CrtLevelUpExp.class, input);
            mRealm.commitTransaction();
            input.close();

            RealmResults<CrtLevelUpExp> RealmList = mRealm.where(CrtLevelUpExp.class).findAll();

            for (CrtLevelUpExp ob : RealmList){
                Log.msg(ob.toString());
            }
        }catch (Exception e){
            Log.msg("crtLevelUpExp.json err");
        }

        if (!mRealm.isClosed()) mRealm.close();
    }

}

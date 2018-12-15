package gun2.dev.glovesquest.main.object.monster.exec;

import android.util.Log;

import java.util.ArrayList;

import gun2.dev.glovesquest.main.object.monster.skill.MonsterSkillAction;
import gun2.dev.glovesquest.main.object.monster.skill.MonsterSkillAlgorithmInfo;
import gun2.dev.glovesquest.main.object.monster.skill.type.MonsterSkillAnimInfo;
import gun2.dev.glovesquest.main.object.monster.skill.type.MonsterSkillAnimInfoData;
import gun2.dev.glovesquest.main.object.monster.skill.type.MonsterSkillDrawInfo;
import gun2.dev.glovesquest.main.object.monster.skill.type.MonsterSkillDrawInfoData;
import gun2.dev.glovesquest.main.object.monster.skill.type.MonsterSkillInfo;
import gun2.dev.glovesquest.main.object.monster.skill.type.PerMonsterSkillInfo;
import gun2.dev.glovesquest.main.object.monster.type.CreatedMonster;
import gun2.dev.glovesquest.main.object.monster.type.MonsterGenManager;
import gun2.dev.glovesquest.utils.ChanceManager;

public class MonsterActionManager {

    private Thread mMonsterGenThread;
    private Thread mMonsterSkillActionThread;
    private int mGenTimeFlow = 0;
    private int mActionTimeFlow = 0;
    private ArrayList<MonsterGenManager> mMonsterGenManagerList;
    private int mMonsterActivity = 1;

    public void startManage(ArrayList<MonsterGenManager> monsterGenManagerList) {
        mMonsterGenManagerList = monsterGenManagerList;

        monsterSkillActionThread();
        monsterGenStart();
    }

    public void stopManage() {
        mMonsterGenThread.interrupt();
        mMonsterSkillActionThread.interrupt();
    }

    public void monsterSkillActionThread() {

        mMonsterSkillActionThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (!Thread.currentThread().isInterrupted()) {
                        for (MonsterGenManager monsterGenManager : mMonsterGenManagerList) {
                            if (mActionTimeFlow % mMonsterActivity == 0) {
                                for (CreatedMonster createdMonster : monsterGenManager.getCreatedMonsterList()) {
                                    //몬스터가 일반상태이면
                                    if (createdMonster.getCurrentState() == 0) {
                                        Log.d("TAG", "" + createdMonster.getMonsterName().getKo() + "가 노는중");
                                        //스킬발동 확률 측정
                                        MonsterSkillAlgorithmInfo monsterSkillAlgorithmInfo = monsterGenManager.getMonsterShareData().getMonsterSkillAlgorithmInfo();
                                        if (ChanceManager.percent(monsterSkillAlgorithmInfo.getSkillProbability())) {
                                            //스킬발동 확률 적중 monsterSkillList사용
                                            //monsterSkillList중 하나의 객체 선택을 위한 뽑기
                                            int num = ChanceManager.getNum(monsterSkillAlgorithmInfo.getMonsterSkillList().size());
                                            PerMonsterSkillInfo perMonsterSkillInfo = monsterSkillAlgorithmInfo.getMonsterSkillList().get(num);

                                            //현재페이지 지정을위한 createdMonster의 MonsterSkillAction내부 MonsterSkillDrawInfoData로 변경 및 참조

                                            //MonsterSkillAction 설정
                                            MonsterSkillAction monsterSkillAction = new MonsterSkillAction();
                                            monsterSkillAction.setAlertTime(perMonsterSkillInfo.getAlertTime());
                                            monsterSkillAction.setSkillRange(perMonsterSkillInfo.getSkillRange());
                                            monsterSkillAction.setCreatedMonster(createdMonster);

                                            //몬스터 모션&이미지
                                            monsterSkillAction.setMonsterFireImgList(
                                                    getAutoMonsterSkillDrawInfoDataList(perMonsterSkillInfo.getSkillFireImgList(),
                                                            createdMonster)
                                            );
                                            monsterSkillAction.setMonsterCastImgList(
                                                    getAutoMonsterSkillDrawInfoDataList(perMonsterSkillInfo.getSkillCastImgList(),
                                                            createdMonster)
                                            );

                                            //스킬 정보
                                            MonsterSkillInfo monsterSkillInfo = perMonsterSkillInfo.getMonsterSkillInfo();
                                            monsterSkillAction.setMonsterSkillInfo(monsterSkillInfo);

                                            monsterSkillAction.setSkillFireImg(
                                                    getAutoMonsterSkillAnimInfoData(monsterSkillInfo.getSkillFireImg())
                                            );
                                            monsterSkillAction.setSkillCastImg(
                                                    getAutoMonsterSkillAnimInfoData(monsterSkillInfo.getSkillCastImg())
                                            );

                                            createdMonster.setMonsterSkillAction(monsterSkillAction);
                                            //스킬 시전상태로 변경
                                            createdMonster.setCurrentState(-40);


                                        } else {
                                            //스킬발동 실패 defaultSkillList실행
                                        }

                                        Thread.sleep(300);
                                    }
                                }
                            }
                        }
                        try {
                            Thread.sleep(1000);
                            mActionTimeFlow++;
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Log.d("MonsterActionManager", "Thread.currentThread().sleep(300); err");
                } finally {
                    Log.d("TAG", "monsterGenStart thread dead");
                    //test

                }

            }
        });
        mMonsterSkillActionThread.start();
    }


    public MonsterSkillAnimInfoData getAutoMonsterSkillAnimInfoData(MonsterSkillAnimInfo monsterSkillAnimInfo){

        MonsterSkillAnimInfoData monsterSkillAnimInfoData = new MonsterSkillAnimInfoData();

        monsterSkillAnimInfoData.setBackSkillAnim(
                getAutoMonsterSkillDrawInfoData(monsterSkillAnimInfo.getBackSkillAnim())
        );
        monsterSkillAnimInfoData.setFrontSkillAnim(
                getAutoMonsterSkillDrawInfoData(monsterSkillAnimInfo.getFrontSkillAnim())
        );
        monsterSkillAnimInfoData.setBackPoint(monsterSkillAnimInfo.getBackPoint());
        monsterSkillAnimInfoData.setFrontPoint(monsterSkillAnimInfo.getFrontPoint());

        return monsterSkillAnimInfoData;
    }

    public MonsterSkillDrawInfoData getAutoMonsterSkillDrawInfoData(MonsterSkillDrawInfo monsterSkillDrawInfo){
        MonsterSkillDrawInfoData monsterSkillDrawInfoData = new MonsterSkillDrawInfoData();
        monsterSkillDrawInfoData.setMonsterSkillDrawInfo(monsterSkillDrawInfo);

        return monsterSkillDrawInfoData;
    }


    public ArrayList<MonsterSkillDrawInfoData> getAutoMonsterSkillDrawInfoDataList(
            ArrayList<MonsterSkillDrawInfo> monsterSkillDrawInfoList,
            CreatedMonster createdMonster) {
        ArrayList<MonsterSkillDrawInfoData> monsterSkillDrawInfoDataList = new ArrayList<MonsterSkillDrawInfoData>();

        for (int i = 0; i < monsterSkillDrawInfoList.size(); i ++){
            MonsterSkillDrawInfoData monsterSkillDrawInfoData = new MonsterSkillDrawInfoData();
            monsterSkillDrawInfoData.setMonsterSkillDrawInfo(monsterSkillDrawInfoList.get(i));
            monsterSkillDrawInfoData.setMotionGroup(createdMonster.getDefaultImgList().get(i).getMotionGroup());
            monsterSkillDrawInfoDataList.add(monsterSkillDrawInfoData);
        }

        return monsterSkillDrawInfoDataList;
    }


    public void monsterGenStart() {
        mMonsterGenThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (!Thread.currentThread().isInterrupted()) {
                        for (MonsterGenManager monsterGenManager : mMonsterGenManagerList) {
                            if (mGenTimeFlow % monsterGenManager.getMonsterGenTime() == 0) {
                                if (ChanceManager.percent(monsterGenManager.getGenProbability())) {
                                    //젠
                                    for (CreatedMonster createdMonster : monsterGenManager.getCreatedMonsterList()) {
                                        if (createdMonster.getCurrentState() == -100) {
                                            createdMonster.setCurrentState(20);
                                        }
                                    }
                                }
                            }
                        }
                        try {
                            Thread.sleep(1000);
                            mGenTimeFlow++;
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        /*
                        for (MonsterCurrentLocation monsterCurrentLocation : mMonsterCurrentLocationList) {
                            Log.d("monsterCurrentLocation:", "created : " + monsterCurrentLocation.getCreatedMonster() + " y : " + monsterCurrentLocation.getY() +
                                    " \n CurrentState " + monsterCurrentLocation.getCreatedMonster().getCurrentState());
                        }*/

                    }
                } finally {
                    Log.d("TAG", "monsterGenStart thread dead");
                    //test

                }


            }
        });
        mMonsterGenThread.start();
    }
}

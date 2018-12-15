package gun2.dev.glovesquest.main.object.monster.exec;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import gun2.dev.glovesquest.db.data.Monster;
import gun2.dev.glovesquest.db.data.MonsterGen;
import gun2.dev.glovesquest.db.data.MonsterSkill;
import gun2.dev.glovesquest.db.data.MonsterSkillAlgorithm;
import gun2.dev.glovesquest.db.data.PerMonsterSkill;
import gun2.dev.glovesquest.db.type.ImgAnim;
import gun2.dev.glovesquest.db.type.LogicalPoint;
import gun2.dev.glovesquest.db.type.MonsterSkillAnim;
import gun2.dev.glovesquest.db.type.MotionAnim;
import gun2.dev.glovesquest.main.object.monster.MonsterInfo;
import gun2.dev.glovesquest.main.object.monster.skill.MonsterSkillAlgorithmInfo;
import gun2.dev.glovesquest.main.object.monster.skill.type.MonsterReSizeDataType;
import gun2.dev.glovesquest.main.object.monster.skill.type.MonsterSkillAnimInfo;
import gun2.dev.glovesquest.main.object.monster.skill.type.MonsterSkillDrawInfo;
import gun2.dev.glovesquest.main.object.monster.skill.type.MonsterSkillInfo;
import gun2.dev.glovesquest.main.object.monster.skill.type.PerMonsterSkillInfo;
import gun2.dev.glovesquest.main.object.monster.type.CreatedMonster;
import gun2.dev.glovesquest.main.object.monster.type.MonsterCurrentLocation;
import gun2.dev.glovesquest.main.object.monster.type.MonsterDeadAction;
import gun2.dev.glovesquest.main.object.monster.type.MonsterDrawBitmapInfo;
import gun2.dev.glovesquest.main.object.monster.type.MonsterDrawInfo;
import gun2.dev.glovesquest.main.object.monster.type.MonsterDrawInfoData;
import gun2.dev.glovesquest.main.object.monster.type.MonsterGenManager;
import gun2.dev.glovesquest.main.object.monster.type.MonsterMotionGroup;
import gun2.dev.glovesquest.main.object.monster.type.MonsterMotionObject;
import gun2.dev.glovesquest.main.object.monster.type.MonsterPart;
import gun2.dev.glovesquest.main.object.monster.type.MonsterPartDrawInfo;
import gun2.dev.glovesquest.main.object.monster.type.MonsterShareData;
import gun2.dev.glovesquest.utils.BitmapUtil;
import gun2.dev.glovesquest.utils.DeviceEnvironment;
import gun2.dev.glovesquest.utils.LogManager;
import gun2.dev.glovesquest.utils.MotionCalc;
import gun2.dev.glovesquest.utils.type.PointXY;
import io.realm.RealmList;

public class MonsterCreator {
    private LogManager mLog = new LogManager(getClass().getName().trim());

    public MonsterCreator(Resources resources) {
        mResources = resources;
        mBackground.setColor(Color.BLACK);

    }

    private Resources mResources;
    private int mMediaWidth = DeviceEnvironment.sDeviceWidth;
    private int mMediaHeight = DeviceEnvironment.sDeviceHeight;
    private Paint mBackground = new Paint();
    private ArrayList<MonsterInfo> mMonsterInfoList = new ArrayList<MonsterInfo>();
    private ArrayList<MonsterGenManager> mMonsterGenManagerList = new ArrayList<MonsterGenManager>();
    private ArrayList<MonsterCurrentLocation> mMonsterCurrentLocationList = new ArrayList<MonsterCurrentLocation>();
    private Thread mMonsterGenThread;
    private ArrayList<MonsterDeadAction> mMonsterDeadActionList = new ArrayList<MonsterDeadAction>();
    private MonsterActionManager mMonsterActionManager;

    //-----------------
    //testZone CreatedMonster
    //-----------------
    private ArrayList<CreatedMonster> mCreatedMonsterList = new ArrayList<CreatedMonster>();
    private int mTimeFlow = 0;

    public void createMonster(RealmList<Monster> monsterList) {
        int monsterCount = monsterList.size() - 1;

        int crtX = 0;
        int crtY = 0;

        int count = -1; //for 카운트
        int locationX, locationY;
        for (Monster monster : monsterList) {
            count++;
            //현재 몬스터 베이스 위치
            locationX = DeviceEnvironment.sMonsterLocationList.get(monsterCount).get(count).getX();
            locationY = DeviceEnvironment.sMonsterLocationList.get(monsterCount).get(count).getY();

            //MonsterInfo set 몬스터의 기본 좌표 구하기
            MonsterInfo monsterInfo = new MonsterInfo();
            monsterInfo.setBasePointX(locationX);
            monsterInfo.setBasePointY(locationY);

            //MonsterPart set
            MonsterPart monsterPart = new MonsterPart();

            ArrayList<MonsterPartDrawInfo> monsterPartDrawInfoList = new ArrayList<MonsterPartDrawInfo>();

            //MonsterPartDrawInfo set
            for (ImgAnim imgAnim : monster.getImgList()) {
                MonsterPartDrawInfo monsterPartDrawInfo = new MonsterPartDrawInfo(monsterInfo);
                //프레임 딜레이 설정
                monsterPartDrawInfo.setFrameDelay(imgAnim.getFrameDelay());

                //MonsterPArtDrawInfo Img set
                monsterPartDrawInfo.setCanvasLastPage(imgAnim.getImgList().size());
                for (String img : imgAnim.getImgList()) {

                    Bitmap bitmap = BitmapFactory.decodeResource(mResources,
                            mResources.getIdentifier(img, "drawable", "gun2.dev.glovesquest"));

                    //Crt좌표가 비어있으면 채우기
                    if (monsterPartDrawInfo.getCrtX() == 0) {
                        monsterPartDrawInfo.setCrtX(locationX - bitmap.getWidth() / 2);
                        monsterPartDrawInfo.setCrtY(locationY - bitmap.getHeight() / 2);
                    }
                    //파트 이미지 채우기
                    monsterPartDrawInfo.addMonsterImgBitmap(bitmap);
                    //모션 정보 채우기
                    //MotionSet
                    MonsterMotionGroup monsterMotionGroup = new MonsterMotionGroup();
                    for (MotionAnim motionAnim : imgAnim.getMotionSet().getMotionAnimList()) {
                        MonsterMotionObject monsterMotionObject = new MonsterMotionObject();
                        for (String motion : motionAnim.getMotionList()) {
                            monsterMotionObject.addMotion(motion);
                        }
                        ArrayList<PointXY> motionNavigation;
                        motionNavigation = (MotionCalc.sGetMonsterMotionList(
                                monsterPartDrawInfo.getCrtX(),
                                monsterPartDrawInfo.getCrtY(),
                                monsterMotionObject.getMotionList()
                        ));
                        if (motionNavigation.size() == 0) {
                            //모션이 없을경우 본래 위치로 고정
                            ArrayList<PointXY> arrayList = new ArrayList<PointXY>();
                            arrayList.add(new PointXY().setAndGetPoint(monsterPartDrawInfo.getCrtX(),
                                    monsterPartDrawInfo.getCrtY()));
                            monsterMotionObject.setMotionNavigation(arrayList);
                        } else {
                            //모션이 있을경우 추가
                            monsterMotionObject.setMotionNavigation(motionNavigation);
                        }
                        monsterMotionObject.setLastMotionNum(monsterMotionObject.getMotionNavigation().size());
                        monsterMotionObject.setBackDelay(motionAnim.getBackDelay());
                        monsterMotionObject.setFrameDelay(motionAnim.getFrameDelay());
                        monsterMotionObject.setFrontDelay(motionAnim.getFrontDelay());
                        monsterMotionObject.setRepeat(motionAnim.getRepeat());

                        monsterMotionGroup.addMonsterMotionObject(monsterMotionObject);
                    }
                    monsterMotionGroup.setLastMotionGroupNum(monsterMotionGroup.getMonsterMotionObjectList().size());

                    monsterPartDrawInfo.setMotionGroup(monsterMotionGroup);
                }
                //이미지 정보 채우기
                monsterPartDrawInfoList.add(monsterPartDrawInfo);


            }
            //몬스터 기본 이미지파트에 기본이미지,모션 세팅
            monsterPart.setDefaultImgList(monsterPartDrawInfoList);


            monster.getDeadImgList();

//            monster.getPerMonsterSkillList();

            monster.getStruckImgList();

            monster.getGenImgList();

            monsterInfo.setMonsterPart(monsterPart);
            //몬스터 등록
            mMonsterInfoList.add(monsterInfo);
        }

    }


    public void createMonster2(RealmList<Monster> monsterList) {
        int monsterCount = monsterList.size() - 1;

        int count = -1; //for 카운트
        int locationX, locationY;
        for (Monster monster : monsterList) {
            count++;
            //현재 몬스터 베이스 위치
            locationX = DeviceEnvironment.sMonsterLocationList.get(monsterCount).get(count).getX();
            locationY = DeviceEnvironment.sMonsterLocationList.get(monsterCount).get(count).getY();

            //MonsterInfo set 몬스터의 기본 좌표 구하기
            MonsterInfo monsterInfo = new MonsterInfo();
            monsterInfo.setBasePointX(locationX);
            monsterInfo.setBasePointY(locationY);

            //MonsterPart set
            MonsterPart monsterPart = new MonsterPart();

            ArrayList<MonsterPartDrawInfo> monsterPartDrawInfoList = new ArrayList<MonsterPartDrawInfo>();

            //MonsterPartDrawInfo set
            for (ImgAnim imgAnim : monster.getImgList()) {
                MonsterPartDrawInfo monsterPartDrawInfo = new MonsterPartDrawInfo(monsterInfo);
                //프레임 딜레이 설정
                monsterPartDrawInfo.setFrameDelay(imgAnim.getFrameDelay());

                //MonsterPArtDrawInfo Img set
                monsterPartDrawInfo.setCanvasLastPage(imgAnim.getImgList().size());
                for (String img : imgAnim.getImgList()) {

                    Bitmap bitmap = BitmapFactory.decodeResource(mResources,
                            mResources.getIdentifier(img, "drawable", "gun2.dev.glovesquest"));
                    //크기 조정
                    bitmap = BitmapUtil.resizeBitmapBaseWidth(bitmap,
                            DeviceEnvironment.sMonsterWidth +
                                    monster.getMonsterSize() * DeviceEnvironment.sLogicalWidth);

                    //Crt좌표가 비어있으면 채우기
                    if (monsterPartDrawInfo.getCrtX() == 0) {
                        monsterPartDrawInfo.setCrtX(locationX - bitmap.getWidth() / 2);
                        monsterPartDrawInfo.setCrtY(locationY - bitmap.getHeight());

                    }
                    //파트 이미지 채우기
                    monsterPartDrawInfo.addMonsterImgBitmap(bitmap);
                }
                //모션 정보 채우기
                //MotionSet
                MonsterMotionGroup monsterMotionGroup = new MonsterMotionGroup();
                for (MotionAnim motionAnim : imgAnim.getMotionSet().getMotionAnimList()) {
                    MonsterMotionObject monsterMotionObject = new MonsterMotionObject();
                    for (String motion : motionAnim.getMotionList()) {
                        monsterMotionObject.addMotion(motion);
                    }
                    ArrayList<PointXY> motionNavigation;
                    motionNavigation = (MotionCalc.sGetMonsterMotionList(
                            monsterPartDrawInfo.getCrtX(),
                            monsterPartDrawInfo.getCrtY(),
                            monsterMotionObject.getMotionList()
                    ));
                    if (motionNavigation.size() == 0) {
                        //모션이 없을경우 본래 위치로 고정
                        ArrayList<PointXY> arrayList = new ArrayList<PointXY>();
                        arrayList.add(new PointXY().setAndGetPoint(monsterPartDrawInfo.getCrtX(),
                                monsterPartDrawInfo.getCrtY()));
                        monsterMotionObject.setMotionNavigation(arrayList);
                    } else {
                        //모션이 있을경우 추가
                        monsterMotionObject.setMotionNavigation(motionNavigation);
                    }
                    monsterMotionObject.setLastMotionNum(monsterMotionObject.getMotionNavigation().size());
                    monsterMotionObject.setBackDelay(motionAnim.getBackDelay());
                    monsterMotionObject.setFrameDelay(motionAnim.getFrameDelay());
                    monsterMotionObject.setFrontDelay(motionAnim.getFrontDelay());
                    monsterMotionObject.setRepeat(motionAnim.getRepeat());

                    monsterMotionGroup.addMonsterMotionObject(monsterMotionObject);
                    monsterMotionGroup.setLastDelay(imgAnim.getMotionSet().getLastDelay());
                    monsterMotionGroup.setStartDelay(imgAnim.getMotionSet().getStartDelay());
                    monsterMotionGroup.setRepeatDelay(imgAnim.getMotionSet().getRepeatDelay());
                    monsterMotionGroup.setRepeat(imgAnim.getMotionSet().getRepeat());
                    monsterMotionGroup.setFrameDelay(imgAnim.getMotionSet().getFrameDelay());
                }
                monsterMotionGroup.setLastMotionGroupNum(monsterMotionGroup.getMonsterMotionObjectList().size());
                //monsterPartDrawInfo set
                monsterPartDrawInfo.setRepeat(imgAnim.getRepeat());
                monsterPartDrawInfo.setFrameDelay(imgAnim.getFrameDelay());
                monsterPartDrawInfo.setMotionGroup(monsterMotionGroup);
                //이미지 정보 채우기
                monsterPartDrawInfoList.add(monsterPartDrawInfo);


            }
            //몬스터 기본 이미지파트에 기본이미지,모션 세팅
            monsterPart.setDefaultImgList(monsterPartDrawInfoList);


            monster.getDeadImgList();
//            ArrayList<MonsterPartDrawInfo> monsterPartDrawInfoList = new ArrayList<MonsterPartDrawInfo>();

            //MonsterPartDrawInfo dead set
            for (ImgAnim imgAnim : monster.getDeadImgList()) {
                MonsterPartDrawInfo monsterPartDrawInfo = new MonsterPartDrawInfo(monsterInfo);
                //프레임 딜레이 설정
                monsterPartDrawInfo.setFrameDelay(imgAnim.getFrameDelay());

                //MonsterPArtDrawInfo Img set
                monsterPartDrawInfo.setCanvasLastPage(imgAnim.getImgList().size());
                for (String img : imgAnim.getImgList()) {

                    Bitmap bitmap = BitmapFactory.decodeResource(mResources,
                            mResources.getIdentifier(img, "drawable", "gun2.dev.glovesquest"));
                    //크기 조정
                    bitmap = BitmapUtil.resizeBitmapBaseWidth(bitmap,
                            DeviceEnvironment.sMonsterWidth +
                                    monster.getMonsterSize() * DeviceEnvironment.sLogicalWidth);

                    //Crt좌표가 비어있으면 채우기
                    if (monsterPartDrawInfo.getCrtX() == 0) {
                        monsterPartDrawInfo.setCrtX(locationX - bitmap.getWidth() / 2);
                        monsterPartDrawInfo.setCrtY(locationY - bitmap.getHeight());

                    }
                    //파트 이미지 채우기
                    monsterPartDrawInfo.addMonsterImgBitmap(bitmap);
                }
                //모션 정보 채우기
                //MotionSet
                MonsterMotionGroup monsterMotionGroup = new MonsterMotionGroup();
                for (MotionAnim motionAnim : imgAnim.getMotionSet().getMotionAnimList()) {
                    MonsterMotionObject monsterMotionObject = new MonsterMotionObject();
                    for (String motion : motionAnim.getMotionList()) {
                        monsterMotionObject.addMotion(motion);
                    }
                    ArrayList<PointXY> motionNavigation;
                    motionNavigation = (MotionCalc.sGetMonsterMotionList(
                            monsterPartDrawInfo.getCrtX(),
                            monsterPartDrawInfo.getCrtY(),
                            monsterMotionObject.getMotionList()
                    ));
                    if (motionNavigation.size() == 0) {
                        //모션이 없을경우 본래 위치로 고정
                        ArrayList<PointXY> arrayList = new ArrayList<PointXY>();
                        arrayList.add(new PointXY().setAndGetPoint(monsterPartDrawInfo.getCrtX(),
                                monsterPartDrawInfo.getCrtY()));
                        monsterMotionObject.setMotionNavigation(arrayList);
                    } else {
                        //모션이 있을경우 추가
                        monsterMotionObject.setMotionNavigation(motionNavigation);
                    }
                    monsterMotionObject.setLastMotionNum(monsterMotionObject.getMotionNavigation().size());
                    monsterMotionObject.setBackDelay(motionAnim.getBackDelay());
                    monsterMotionObject.setFrameDelay(motionAnim.getFrameDelay());
                    monsterMotionObject.setFrontDelay(motionAnim.getFrontDelay());
                    monsterMotionObject.setRepeat(motionAnim.getRepeat());

                    monsterMotionGroup.addMonsterMotionObject(monsterMotionObject);
                    monsterMotionGroup.setLastDelay(imgAnim.getMotionSet().getLastDelay());
                    monsterMotionGroup.setStartDelay(imgAnim.getMotionSet().getStartDelay());
                    monsterMotionGroup.setRepeatDelay(imgAnim.getMotionSet().getRepeatDelay());
                    monsterMotionGroup.setRepeat(imgAnim.getMotionSet().getRepeat());
                    monsterMotionGroup.setFrameDelay(imgAnim.getMotionSet().getFrameDelay());
                }
                monsterMotionGroup.setLastMotionGroupNum(monsterMotionGroup.getMonsterMotionObjectList().size());
                //monsterPartDrawInfo set
                monsterPartDrawInfo.setRepeat(imgAnim.getRepeat());
                monsterPartDrawInfo.setFrameDelay(imgAnim.getFrameDelay());
                monsterPartDrawInfo.setMotionGroup(monsterMotionGroup);
                //이미지 정보 채우기
                monsterPartDrawInfoList.add(monsterPartDrawInfo);
            }
            //몬스터 죽음 이미지파트에 죽음이미지,모션 세팅
            monsterPart.setDeadImgList(monsterPartDrawInfoList);

//            monster.getPerMonsterSkillList();

            monster.getStruckImgList();

            monster.getGenImgList();

            //파트 정보 등록
            monsterInfo.setMonsterPart(monsterPart);
            //몬스터 등록
            mMonsterInfoList.add(monsterInfo);
        }

    }


    public void monsterGenStop() {
        mMonsterActionManager.stopManage();
    }

    ////////////////////
    public void setMonsterInfo(RealmList<MonsterGen> monsterGenList) {
        for (MonsterGen monsterGen : monsterGenList) {
            MonsterGenManager monsterGenManager = new MonsterGenManager();

            MonsterShareData monsterShareData = new MonsterShareData();

            int monsterSize = monsterGen.getMonster().getMonsterSize();

            monsterShareData.setMonsterDefaultDrawInfoList(getAutoSetMonsterDrawInfo(
                    monsterGen.getMonster().getImgList(),
                    monsterSize
            ));

            monsterShareData.setMonsterDeadDrawInfoList(getAutoSetMonsterDrawInfo(
                    monsterGen.getMonster().getDeadImgList(),
                    monsterSize
            ));

            monsterShareData.setMonsterStruckDrawInfoList(getAutoSetMonsterDrawInfoStruck(
                    monsterGen.getMonster().getStruckImgList(),
                    monsterSize
            ));

            //사진크기 변경에따른 비율 작업
            Bitmap originBitmap = BitmapFactory.decodeResource(mResources,
                    mResources.getIdentifier(monsterGen.getMonster().getImgList().get(0).getImgList().get(0)
                            , "drawable", "gun2.dev.glovesquest"));
            //변경된 사이즈와 비교할 본래 사이즈 생성
            MonsterReSizeDataType monsterReSizeDataType =
                    new MonsterReSizeDataType(monsterSize, originBitmap.getWidth(), originBitmap.getHeight(),
                            monsterShareData.getMonsterDefaultDrawInfoList().get(0).getCrtX(),
                            monsterShareData.getMonsterDefaultDrawInfoList().get(0).getCrtY());

            monsterShareData.setMonsterSkillAlgorithmInfo(getAutoSetMonsterSkillAlgorithm(
                    monsterGen.getMonster().getMonsterSkillAlgorithm(),
                    monsterReSizeDataType
                    )

            );

            monsterGenManager.setMonsterShareData(monsterShareData);
            //젠타임 설정
            monsterGenManager.setMonsterGenTime(monsterGen.getMonsterGenTime());
            //젠 확률 설정
            monsterGenManager.setGenProbability(monsterGen.getGenProbability());
            //created monster set
            ArrayList<CreatedMonster> createdMonsterList = new ArrayList<CreatedMonster>();
            for (LogicalPoint logicalPoint : monsterGen.getGenPointList()) {
                CreatedMonster createdMonster = new CreatedMonster();
                createdMonster.setGenPointX(logicalPoint.getX() * DeviceEnvironment.sLogicalWidth);
                createdMonster.setGenPointY(logicalPoint.getY() * DeviceEnvironment.sLogicalHeight);
                createdMonster.setCurrentX(logicalPoint.getX() * DeviceEnvironment.sLogicalWidth);
                createdMonster.setCurrentY(logicalPoint.getY() * DeviceEnvironment.sLogicalHeight);
                createdMonster.setCurrentState(20);

                createdMonster.setMonsterName(monsterGen.getMonster().getName());
                createdMonster.setDefaultImgList(
                        getMonsterDrawInfoListClone(monsterShareData.getMonsterDefaultDrawInfoList(),
                                createdMonster));
                createdMonster.setDeadImgList(
                        getMonsterDrawInfoListClone(monsterShareData.getMonsterDeadDrawInfoList(),
                                createdMonster));
                createdMonster.setStruckImgList(
                        getMonsterDrawInfoListClone(monsterShareData.getMonsterStruckDrawInfoList(),
                                createdMonster));
                createdMonsterList.add(createdMonster);

                mMonsterCurrentLocationList.add(
                        new MonsterCurrentLocation(createdMonster, logicalPoint.getY()));
            }

            monsterGenManager.setCreatedMonsterList(createdMonsterList);
            mMonsterGenManagerList.add(monsterGenManager);

        }

        Collections.sort(mMonsterCurrentLocationList);

        //test
        for (MonsterCurrentLocation monsterCurrentLocation : mMonsterCurrentLocationList) {
            Log.d("monsterCurrentLocation:", "created : " + monsterCurrentLocation.getCreatedMonster() + " y : " + monsterCurrentLocation.getY());
        }

        mMonsterActionManager = new MonsterActionManager();
        mMonsterActionManager.startManage(mMonsterGenManagerList);
    }

    ////////////////////

    public void createMonster3(RealmList<Monster> monsterList) {
        int monsterCount = monsterList.size() - 1;

        int count = -1; //for 카운트
        int locationX, locationY;
        for (Monster monster : monsterList) {
            count++;
            //현재 몬스터 베이스 위치
            locationX = DeviceEnvironment.sMonsterLocationList.get(monsterCount).get(count).getX();
            locationY = DeviceEnvironment.sMonsterLocationList.get(monsterCount).get(count).getY();

            //MonsterInfo set 몬스터의 기본 좌표 구하기
            MonsterInfo monsterInfo = new MonsterInfo();
            monsterInfo.setBasePointX(locationX);
            monsterInfo.setBasePointY(locationY);

            //MonsterPart set
            MonsterPart monsterPart = new MonsterPart();
            //몬스터 기본 이미지파트에 기본이미지,모션 세팅
            monsterPart.setDefaultImgList(getAutoSetMonsterPartDrawInfo(
                    monster.getImgList(),
                    monsterInfo,
                    locationX,
                    locationY,
                    monster.getMonsterSize()
            ));


//            monster.getDeadImgList();
            //몬스터 죽음 이미지파트에 죽음이미지,모션 세팅
            monsterPart.setDeadImgList(getAutoSetMonsterPartDrawInfo(
                    monster.getDeadImgList(),
                    monsterInfo,
                    locationX,
                    locationY,
                    monster.getMonsterSize()
            ));

            //몬스터 맞음 이미지 파트에 맞을때 이미지, 모션 세팅
//            monster.getPerMonsterSkillList();
            monsterPart.setStruckImgList(getAutoSetMonsterPartDrawInfoStruck(
                    monster.getStruckImgList(),
                    monsterInfo,
                    locationX,
                    locationY,
                    monster.getMonsterSize()
            ));

//            monster.getStruckImgList();

            monster.getGenImgList();

            //파트 정보 등록
            monsterInfo.setMonsterPart(monsterPart);
            //몬스터 등록
            mMonsterInfoList.add(monsterInfo);
        }

    }


    //몬스터 파트 정보 삽입 메소드
    public ArrayList<MonsterPartDrawInfo> getAutoSetMonsterPartDrawInfo(RealmList<ImgAnim> imgAnimList,
                                                                        MonsterInfo monsterInfo,
                                                                        int locationX,
                                                                        int locationY,
                                                                        int monsterSize) {
        ArrayList<MonsterPartDrawInfo> monsterPartDrawInfoList = new ArrayList<MonsterPartDrawInfo>();

        //MonsterPart set
        for (ImgAnim imgAnim : imgAnimList) {
            MonsterPartDrawInfo monsterPartDrawInfo = new MonsterPartDrawInfo(monsterInfo);
            //프레임 딜레이 설정
            monsterPartDrawInfo.setFrameDelay(imgAnim.getFrameDelay());

            //MonsterPArtDrawInfo Img set
            monsterPartDrawInfo.setCanvasLastPage(imgAnim.getImgList().size());
            for (String img : imgAnim.getImgList()) {

                Bitmap bitmap = BitmapFactory.decodeResource(mResources,
                        mResources.getIdentifier(img, "drawable", "gun2.dev.glovesquest"));
                //크기 조정
                bitmap = BitmapUtil.resizeBitmapBaseWidth(bitmap,
                        DeviceEnvironment.sMonsterWidth +
                                monsterSize * DeviceEnvironment.sLogicalWidth);

                //Crt좌표가 비어있으면 채우기
                if (monsterPartDrawInfo.getCrtX() == 0) {
                    monsterPartDrawInfo.setCrtX(locationX - bitmap.getWidth() / 2);
                    monsterPartDrawInfo.setCrtY(locationY - bitmap.getHeight());

                }
                //파트 이미지 채우기
                monsterPartDrawInfo.addMonsterImgBitmap(bitmap);
            }
            //모션 정보 채우기
            //MotionSet
            MonsterMotionGroup monsterMotionGroup = new MonsterMotionGroup();
            //모션정보 있는지 확인
            if (imgAnim.getMotionSet() != null) {
                for (MotionAnim motionAnim : imgAnim.getMotionSet().getMotionAnimList()) {
                    MonsterMotionObject monsterMotionObject = new MonsterMotionObject();
                    for (String motion : motionAnim.getMotionList()) {
                        monsterMotionObject.addMotion(motion);
                    }
                    ArrayList<PointXY> motionNavigation;
                    motionNavigation = (MotionCalc.sGetMonsterMotionList(
                            monsterPartDrawInfo.getCrtX(),
                            monsterPartDrawInfo.getCrtY(),
                            monsterMotionObject.getMotionList()
                    ));
                    if (motionNavigation.size() == 0) {
                        //모션이 없을경우 본래 위치로 고정
                        ArrayList<PointXY> arrayList = new ArrayList<PointXY>();
                        arrayList.add(new PointXY().setAndGetPoint(monsterPartDrawInfo.getCrtX(),
                                monsterPartDrawInfo.getCrtY()));
                        monsterMotionObject.setMotionNavigation(arrayList);
                    } else {
                        //모션이 있을경우 추가
                        monsterMotionObject.setMotionNavigation(motionNavigation);
                    }
                    monsterMotionObject.setLastMotionNum(monsterMotionObject.getMotionNavigation().size());
                    monsterMotionObject.setBackDelay(motionAnim.getBackDelay());
                    monsterMotionObject.setFrameDelay(motionAnim.getFrameDelay());
                    monsterMotionObject.setFrontDelay(motionAnim.getFrontDelay());
                    monsterMotionObject.setRepeat(motionAnim.getRepeat());

                    monsterMotionGroup.addMonsterMotionObject(monsterMotionObject);
                    monsterMotionGroup.setLastDelay(imgAnim.getMotionSet().getLastDelay());
                    monsterMotionGroup.setStartDelay(imgAnim.getMotionSet().getStartDelay());
                    monsterMotionGroup.setRepeatDelay(imgAnim.getMotionSet().getRepeatDelay());
                    monsterMotionGroup.setRepeat(imgAnim.getMotionSet().getRepeat());
                    monsterMotionGroup.setFrameDelay(imgAnim.getMotionSet().getFrameDelay());
                }
                monsterMotionGroup.setLastMotionGroupNum(monsterMotionGroup.getMonsterMotionObjectList().size());
            }
            //모션셋 자체가 없는 경우
            else {
                MonsterMotionObject monsterMotionObject = new MonsterMotionObject();

                //모션이 없을경우 본래 위치로 고정
                ArrayList<PointXY> arrayList = new ArrayList<PointXY>();
                arrayList.add(new PointXY().setAndGetPoint(monsterPartDrawInfo.getCrtX(),
                        monsterPartDrawInfo.getCrtY()));
                monsterMotionObject.setMotionNavigation(arrayList);
                monsterMotionGroup.addMonsterMotionObject(monsterMotionObject);
            }

            //monsterPartDrawInfo set
            monsterPartDrawInfo.setRepeat(imgAnim.getRepeat());
            monsterPartDrawInfo.setFrameDelay(imgAnim.getFrameDelay());
            monsterPartDrawInfo.setMotionGroup(monsterMotionGroup);
            //이미지 정보 채우기
            monsterPartDrawInfoList.add(monsterPartDrawInfo);
        }
        return monsterPartDrawInfoList;

    }


    //피격시 이미지 설정 (기본 모션을 추기하기 위해 따로 설정)
    public ArrayList<MonsterPartDrawInfo> getAutoSetMonsterPartDrawInfoStruck(RealmList<ImgAnim> imgAnimList,
                                                                              MonsterInfo monsterInfo,
                                                                              int locationX,
                                                                              int locationY,
                                                                              int monsterSize) {
        ArrayList<MonsterPartDrawInfo> monsterPartDrawInfoList = new ArrayList<MonsterPartDrawInfo>();

        //MonsterPart set
        for (ImgAnim imgAnim : imgAnimList) {
            MonsterPartDrawInfo monsterPartDrawInfo = new MonsterPartDrawInfo(monsterInfo);
            //프레임 딜레이 설정
            monsterPartDrawInfo.setFrameDelay(imgAnim.getFrameDelay());

            //MonsterPArtDrawInfo Img set
            monsterPartDrawInfo.setCanvasLastPage(imgAnim.getImgList().size());
            for (String img : imgAnim.getImgList()) {

                Bitmap bitmap = BitmapFactory.decodeResource(mResources,
                        mResources.getIdentifier(img, "drawable", "gun2.dev.glovesquest"));
                //크기 조정
                bitmap = BitmapUtil.resizeBitmapBaseWidth(bitmap,
                        DeviceEnvironment.sMonsterWidth +
                                monsterSize * DeviceEnvironment.sLogicalWidth);

                //Crt좌표가 비어있으면 채우기
                if (monsterPartDrawInfo.getCrtX() == 0) {
                    monsterPartDrawInfo.setCrtX(locationX - bitmap.getWidth() / 2);
                    monsterPartDrawInfo.setCrtY(locationY - bitmap.getHeight());

                }
                //파트 이미지 채우기
                monsterPartDrawInfo.addMonsterImgBitmap(bitmap);
            }
            //모션 정보 채우기
            //MotionSet
            MonsterMotionGroup monsterMotionGroup = new MonsterMotionGroup();
            //모션정보 있는지 확인
            if (imgAnim.getMotionSet() != null) {
                for (MotionAnim motionAnim : imgAnim.getMotionSet().getMotionAnimList()) {
                    MonsterMotionObject monsterMotionObject = new MonsterMotionObject();
                    for (String motion : motionAnim.getMotionList()) {
                        monsterMotionObject.addMotion(motion);
                    }
                    ArrayList<PointXY> motionNavigation;
                    motionNavigation = (MotionCalc.sGetMonsterMotionList(
                            monsterPartDrawInfo.getCrtX(),
                            monsterPartDrawInfo.getCrtY(),
                            monsterMotionObject.getMotionList()
                    ));
                    if (motionNavigation.size() == 0) {
                        //모션이 없을경우 기본 피격 모션 사용
                        motionNavigation = (MotionCalc.sGetMonsterMotion(
                                monsterPartDrawInfo.getCrtX(),
                                monsterPartDrawInfo.getCrtY(),
                                "baseStruckMotion01"
                        ));
                        monsterMotionObject.setMotionNavigation(motionNavigation);
                        monsterMotionObject.setLastMotionNum(monsterMotionObject.getMotionNavigation().size());
                        monsterMotionObject.setFrameDelay(1);

                    } else {
                        //모션이 있을경우 추가
                        monsterMotionObject.setMotionNavigation(motionNavigation);
                    }
                    monsterMotionObject.setLastMotionNum(monsterMotionObject.getMotionNavigation().size());
                    monsterMotionObject.setBackDelay(motionAnim.getBackDelay());
                    monsterMotionObject.setFrameDelay(motionAnim.getFrameDelay());
                    monsterMotionObject.setFrontDelay(motionAnim.getFrontDelay());
                    monsterMotionObject.setRepeat(motionAnim.getRepeat());

                    monsterMotionGroup.addMonsterMotionObject(monsterMotionObject);
                    monsterMotionGroup.setLastDelay(imgAnim.getMotionSet().getLastDelay());
                    monsterMotionGroup.setStartDelay(imgAnim.getMotionSet().getStartDelay());
                    monsterMotionGroup.setRepeatDelay(imgAnim.getMotionSet().getRepeatDelay());
                    monsterMotionGroup.setRepeat(imgAnim.getMotionSet().getRepeat());
                    monsterMotionGroup.setFrameDelay(imgAnim.getMotionSet().getFrameDelay());
                }
                monsterMotionGroup.setLastMotionGroupNum(monsterMotionGroup.getMonsterMotionObjectList().size());
            }
            //모션셋 자체가 없는 경우
            else {
                MonsterMotionObject monsterMotionObject = new MonsterMotionObject();

                //모션이 없을경우 기본 피격 모션 사용
                ArrayList<PointXY> motionNavigation = (MotionCalc.sGetMonsterMotion(
                        monsterPartDrawInfo.getCrtX(),
                        monsterPartDrawInfo.getCrtY(),
                        "baseStruckMotion01"
                ));
                monsterMotionObject.setMotionNavigation(motionNavigation);
                monsterMotionObject.setLastMotionNum(monsterMotionObject.getMotionNavigation().size());
                monsterMotionObject.setFrameDelay(1);
                monsterMotionGroup.addMonsterMotionObject(monsterMotionObject);
            }

            //monsterPartDrawInfo set
            monsterPartDrawInfo.setRepeat(imgAnim.getRepeat());
            monsterPartDrawInfo.setFrameDelay(imgAnim.getFrameDelay());
            monsterPartDrawInfo.setMotionGroup(monsterMotionGroup);
            //이미지 정보 채우기
            monsterPartDrawInfoList.add(monsterPartDrawInfo);
        }
        return monsterPartDrawInfoList;

    }

    public MonsterSkillAlgorithmInfo getAutoSetMonsterSkillAlgorithm(MonsterSkillAlgorithm monsterSkillAlgorithm,
                                                                     MonsterReSizeDataType monsterReSizeDataType) {
        MonsterSkillAlgorithmInfo monsterSkillAlgorithmInfo = new MonsterSkillAlgorithmInfo();

        monsterSkillAlgorithmInfo.setDefaultSkillList(
                getAutoSetPerMonsterSkillInfoList(monsterSkillAlgorithm.getDefaultSkillList(),
                        monsterReSizeDataType)
        );

        monsterSkillAlgorithmInfo.setMonsterSkillList(
                getAutoSetPerMonsterSkillInfoList(monsterSkillAlgorithm.getMonsterSkillList(),
                        monsterReSizeDataType)
        );

        monsterSkillAlgorithmInfo.setStartSkillList(
                getAutoSetPerMonsterSkillInfoList(monsterSkillAlgorithm.getStartSkillList(),
                        monsterReSizeDataType)
        );

        monsterSkillAlgorithmInfo.setSkillProbability(monsterSkillAlgorithm.getSkillProbability());

        return monsterSkillAlgorithmInfo;
    }


    public ArrayList<PerMonsterSkillInfo> getAutoSetPerMonsterSkillInfoList(
            RealmList<PerMonsterSkill> perMonsterSkillList,
            MonsterReSizeDataType monsterReSizeDataType) {
        ArrayList<PerMonsterSkillInfo> perMonsterSkillInfoList = new ArrayList<PerMonsterSkillInfo>();
        for (PerMonsterSkill perMonsterSkill : perMonsterSkillList) {
            PerMonsterSkillInfo perMonsterSkillInfo = new PerMonsterSkillInfo();

            MonsterSkillInfo monsterSkillInfo = new MonsterSkillInfo();

            MonsterSkill monsterSkill = perMonsterSkill.getMonsterSkill();
            monsterSkillInfo.setSkillCastImg(
                    getAutoSetMonsterSkillAnimInfo(monsterSkill.getSkillCastImg(), monsterReSizeDataType.getMonsterSize())
            );
            monsterSkillInfo.setSkillFireImg(
                    getAutoSetMonsterSkillAnimInfo(monsterSkill.getSkillFireImg(), monsterReSizeDataType.getMonsterSize())
            );

            monsterSkillInfo.setDuration(monsterSkill.getDuration());
            monsterSkillInfo.setSkillCastDel(monsterSkill.getSkillCastDel());
            monsterSkillInfo.setSkillStateCode(monsterSkill.getSkillStateCode());
            monsterSkillInfo.setSkillCoolTime(monsterSkill.getSkillCoolTime());
            perMonsterSkillInfo.setMonsterSkillInfo(monsterSkillInfo);

            perMonsterSkillInfo.setSkillCastImgList(getAutoSetMonsterSkillDrawInfoList(
                    perMonsterSkill.getSkillCastImgList(),
                    monsterReSizeDataType
            ));

            perMonsterSkillInfo.setSkillFireImgList(getAutoSetMonsterSkillDrawInfoList(
                    perMonsterSkill.getSkillFireImgList(),
                    monsterReSizeDataType
                    )
            );

            ArrayList<Integer> integerList = new ArrayList<Integer>();
            for (Long l : perMonsterSkill.getSkillRange()) {
                integerList.add((int) (long) l);
            }
            perMonsterSkillInfo.setSkillRange(integerList);

            perMonsterSkillInfo.setAlertTime(perMonsterSkill.getAlertTime());

            perMonsterSkillInfoList.add(perMonsterSkillInfo);
        }
        return perMonsterSkillInfoList;
    }


    public MonsterSkillAnimInfo getAutoSetMonsterSkillAnimInfo(MonsterSkillAnim monsterSkillAnim,
                                                               int monsterSize) {
        MonsterSkillAnimInfo monsterSkillAnimInfo = new MonsterSkillAnimInfo();

        monsterSkillAnimInfo.setBackSkillAnim(
                getAutoSetMonsterSkillEffectDrawInfo(monsterSkillAnim.getBackSkillAnim(), monsterSize,
                        monsterSkillAnim.getBackSkillSize())
        );

        //뒤 킬 위치 조정
        if (monsterSkillAnim.getBackPoint() != null) {
            monsterSkillAnimInfo.setBackPoint(new PointXY(
                    (DeviceEnvironment.sLogicalWidth * monsterSkillAnim.getBackPoint().getX()) +
                            monsterSkillAnimInfo.getBackSkillAnim().getCrtX(),
                    (DeviceEnvironment.sLogicalHeight * monsterSkillAnim.getBackPoint().getY()) +
                            monsterSkillAnimInfo.getBackSkillAnim().getCrtY())
            );

        } else {
            monsterSkillAnimInfo.setBackPoint(new PointXY(
                    0, 0)
            );
        }

        monsterSkillAnimInfo.setFrontSkillAnim(
                getAutoSetMonsterSkillEffectDrawInfo(monsterSkillAnim.getFrontSkillAnim(), monsterSize,
                        monsterSkillAnim.getFrontSkillSize())
        );

        //앞 스킬 위치 조정
        if (monsterSkillAnim.getFrontPoint() != null) {
            monsterSkillAnimInfo.setFrontPoint(new PointXY(
                    (DeviceEnvironment.sLogicalWidth * monsterSkillAnim.getFrontPoint().getX()) +
                            monsterSkillAnimInfo.getFrontSkillAnim().getCrtX(),
                    (DeviceEnvironment.sLogicalHeight * monsterSkillAnim.getFrontPoint().getY()) +
                            monsterSkillAnimInfo.getFrontSkillAnim().getCrtY())
            );
        } else {
            monsterSkillAnimInfo.setFrontPoint(new PointXY(
                    0, 0)
            );
        }
        return monsterSkillAnimInfo;
    }


    public ArrayList<MonsterSkillDrawInfo> getAutoSetMonsterSkillDrawInfoList(RealmList<ImgAnim> imgAnimList,
                                                                              MonsterReSizeDataType monsterReSizeDataType) {
        ArrayList<MonsterSkillDrawInfo> monsterSkillDrawInfoList = new ArrayList<MonsterSkillDrawInfo>();
        for (ImgAnim imgAnim : imgAnimList) {
            monsterSkillDrawInfoList.add(getAutoSetMonsterSkillDrawInfo(imgAnim, monsterReSizeDataType));
        }
        return monsterSkillDrawInfoList;
    }


    public MonsterSkillDrawInfo getAutoSetMonsterSkillDrawInfo(ImgAnim imgAnim, MonsterReSizeDataType monsterReSizeDataType) {
        MonsterSkillDrawInfo monsterSkillDrawInfo = new MonsterSkillDrawInfo();

        ArrayList<Bitmap> bitmapList = new ArrayList<Bitmap>();
        for (String img : imgAnim.getImgList()) {
            Bitmap bitmap = BitmapFactory.decodeResource(mResources,
                    mResources.getIdentifier(img, "drawable", "gun2.dev.glovesquest"));
            //크기 조정

            bitmap = BitmapUtil.calcBitmapSizeBaseWidth(bitmap,
                    DeviceEnvironment.sMonsterWidth +
                            (monsterReSizeDataType.getMonsterSize() * DeviceEnvironment.sLogicalWidth),
                    monsterReSizeDataType.getMonsterWidthSize());

            //Crt좌표가 비어있으면 채우기
            if (monsterSkillDrawInfo.getCrtX() == 0) {
                monsterSkillDrawInfo.setCrtX(-bitmap.getWidth() / 2);
                monsterSkillDrawInfo.setCrtY(-bitmap.getHeight());

                monsterSkillDrawInfo.setSizeDifferWidth(
                        monsterSkillDrawInfo.getCrtX() - monsterReSizeDataType.getBaseCrtX()
                );
                monsterSkillDrawInfo.setSizeDifferHeight(
                        monsterSkillDrawInfo.getCrtY() - monsterReSizeDataType.getBaseCrtY()
                );
            }
            //파트 이미지 채우기
            bitmapList.add(bitmap);
        }
        monsterSkillDrawInfo.setSkillImgList(bitmapList);
        monsterSkillDrawInfo.setCanvasLastPage(bitmapList.size());
        monsterSkillDrawInfo.setRepeat(imgAnim.getRepeat());
        monsterSkillDrawInfo.setFrameDelay(imgAnim.getFrameDelay());

        return monsterSkillDrawInfo;
    }


    public MonsterSkillDrawInfo getAutoSetMonsterSkillEffectDrawInfo(ImgAnim imgAnim, int monsterSize, int skillSize) {
        MonsterSkillDrawInfo monsterSkillDrawInfo = new MonsterSkillDrawInfo();

        ArrayList<Bitmap> bitmapList = new ArrayList<Bitmap>();
        for (String img : imgAnim.getImgList()) {
            Bitmap bitmap = BitmapFactory.decodeResource(mResources,
                    mResources.getIdentifier(img, "drawable", "gun2.dev.glovesquest"));
            //크기 조정

            bitmap = BitmapUtil.resizeBitmapBaseWidth(bitmap,
                    DeviceEnvironment.sMonsterWidth +
                            (DeviceEnvironment.sLogicalWidth * (monsterSize+skillSize)));

            //Crt좌표가 비어있으면 채우기
            if (monsterSkillDrawInfo.getCrtX() == 0) {
                monsterSkillDrawInfo.setCrtX(-bitmap.getWidth() / 2);
                monsterSkillDrawInfo.setCrtY(-bitmap.getHeight());
            }
            //파트 이미지 채우기
            bitmapList.add(bitmap);
        }
        monsterSkillDrawInfo.setSkillImgList(bitmapList);
        monsterSkillDrawInfo.setCanvasLastPage(bitmapList.size());
        monsterSkillDrawInfo.setRepeat(imgAnim.getRepeat());
        monsterSkillDrawInfo.setFrameDelay(imgAnim.getFrameDelay());

        return monsterSkillDrawInfo;
    }

    ///////////////
    //test
    //////////////

    //몬스터 파트 정보 삽입 메소드
    public ArrayList<MonsterDrawInfo> getAutoSetMonsterDrawInfo(RealmList<ImgAnim> imgAnimList,
                                                                int monsterSize) {
        ArrayList<MonsterDrawInfo> monsterDrawInfoList = new ArrayList<MonsterDrawInfo>();

        //MonsterPart set
        for (ImgAnim imgAnim : imgAnimList) {
            MonsterDrawInfo monsterDrawInfo = new MonsterDrawInfo();
            //프레임 딜레이 설정
            monsterDrawInfo.setFrameDelay(imgAnim.getFrameDelay());

            //MonsterPArtDrawInfo Img set
            monsterDrawInfo.setCanvasLastPage(imgAnim.getImgList().size());
            ArrayList<Bitmap> bitmapList = new ArrayList<Bitmap>();
            for (String img : imgAnim.getImgList()) {

                Bitmap bitmap = BitmapFactory.decodeResource(mResources,
                        mResources.getIdentifier(img, "drawable", "gun2.dev.glovesquest"));
                //크기 조정
                bitmap = BitmapUtil.resizeBitmapBaseWidth(bitmap,
                        DeviceEnvironment.sMonsterWidth +
                                monsterSize * DeviceEnvironment.sLogicalWidth);

                //Crt좌표가 비어있으면 채우기
                if (monsterDrawInfo.getCrtX() == 0) {
                    monsterDrawInfo.setCrtX(-bitmap.getWidth() / 2);
                    monsterDrawInfo.setCrtY(-bitmap.getHeight());

                }
                //파트 이미지 채우기
                bitmapList.add(bitmap);
            }
            monsterDrawInfo.setMonsterImgList(bitmapList);

            //모션 정보 채우기
            //MotionSet
            MonsterMotionGroup monsterMotionGroup = new MonsterMotionGroup();
            //모션정보 있는지 확인
            if (imgAnim.getMotionSet() != null) {
                for (MotionAnim motionAnim : imgAnim.getMotionSet().getMotionAnimList()) {
                    MonsterMotionObject monsterMotionObject = new MonsterMotionObject();
                    for (String motion : motionAnim.getMotionList()) {
                        monsterMotionObject.addMotion(motion);
                    }
                    ArrayList<PointXY> motionNavigation;
                    motionNavigation = (MotionCalc.sGetMonsterMotionList(
                            monsterDrawInfo.getCrtX(),
                            monsterDrawInfo.getCrtY(),
                            monsterMotionObject.getMotionList()
                    ));
                    if (motionNavigation.size() == 0) {
                        //모션이 없을경우 본래 위치로 고정
                        ArrayList<PointXY> arrayList = new ArrayList<PointXY>();
                        arrayList.add(new PointXY().setAndGetPoint(monsterDrawInfo.getCrtX(),
                                monsterDrawInfo.getCrtY()));
                        monsterMotionObject.setMotionNavigation(arrayList);
                    } else {
                        //모션이 있을경우 추가
                        monsterMotionObject.setMotionNavigation(motionNavigation);
                    }
                    monsterMotionObject.setLastMotionNum(monsterMotionObject.getMotionNavigation().size());
                    monsterMotionObject.setBackDelay(motionAnim.getBackDelay());
                    monsterMotionObject.setFrameDelay(motionAnim.getFrameDelay());
                    monsterMotionObject.setFrontDelay(motionAnim.getFrontDelay());
                    monsterMotionObject.setRepeat(motionAnim.getRepeat());

                    monsterMotionGroup.addMonsterMotionObject(monsterMotionObject);
                    monsterMotionGroup.setLastDelay(imgAnim.getMotionSet().getLastDelay());
                    monsterMotionGroup.setStartDelay(imgAnim.getMotionSet().getStartDelay());
                    monsterMotionGroup.setRepeatDelay(imgAnim.getMotionSet().getRepeatDelay());
                    monsterMotionGroup.setRepeat(imgAnim.getMotionSet().getRepeat());
                    monsterMotionGroup.setFrameDelay(imgAnim.getMotionSet().getFrameDelay());
                }
                monsterMotionGroup.setLastMotionGroupNum(monsterMotionGroup.getMonsterMotionObjectList().size());
            }
            //모션셋 자체가 없는 경우
            else {
                MonsterMotionObject monsterMotionObject = new MonsterMotionObject();

                //모션이 없을경우 본래 위치로 고정
                ArrayList<PointXY> arrayList = new ArrayList<PointXY>();
                arrayList.add(new PointXY().setAndGetPoint(monsterDrawInfo.getCrtX(),
                        monsterDrawInfo.getCrtY()));
                monsterMotionObject.setMotionNavigation(arrayList);
                monsterMotionGroup.addMonsterMotionObject(monsterMotionObject);
            }

            //monsterPartDrawInfo set
            monsterDrawInfo.setRepeat(imgAnim.getRepeat());
            monsterDrawInfo.setFrameDelay(imgAnim.getFrameDelay());
            monsterDrawInfo.setMotionGroup(monsterMotionGroup);
            //이미지 정보 채우기
            monsterDrawInfoList.add(monsterDrawInfo);
        }
        return monsterDrawInfoList;

    }


    //몬스터 파트 정보 삽입 메소드
    public ArrayList<MonsterDrawInfo> getAutoSetMonsterDrawInfoStruck(RealmList<ImgAnim> imgAnimList,
                                                                      int monsterSize) {
        ArrayList<MonsterDrawInfo> monsterDrawInfoList = new ArrayList<MonsterDrawInfo>();

        //MonsterPart set
        for (ImgAnim imgAnim : imgAnimList) {
            MonsterDrawInfo monsterDrawInfo = new MonsterDrawInfo();
            //프레임 딜레이 설정
            monsterDrawInfo.setFrameDelay(imgAnim.getFrameDelay());

            //MonsterPArtDrawInfo Img set
            monsterDrawInfo.setCanvasLastPage(imgAnim.getImgList().size());
            ArrayList<Bitmap> bitmapList = new ArrayList<Bitmap>();
            for (String img : imgAnim.getImgList()) {

                Bitmap bitmap = BitmapFactory.decodeResource(mResources,
                        mResources.getIdentifier(img, "drawable", "gun2.dev.glovesquest"));
                //크기 조정
                bitmap = BitmapUtil.resizeBitmapBaseWidth(bitmap,
                        DeviceEnvironment.sMonsterWidth +
                                monsterSize * DeviceEnvironment.sLogicalWidth);

                //Crt좌표가 비어있으면 채우기
                if (monsterDrawInfo.getCrtX() == 0) {
                    monsterDrawInfo.setCrtX(-bitmap.getWidth() / 2);
                    monsterDrawInfo.setCrtY(-bitmap.getHeight());

                }
                //파트 이미지 채우기
                bitmapList.add(bitmap);
            }
            monsterDrawInfo.setMonsterImgList(bitmapList);
            //모션 정보 채우기
            //MotionSet
            MonsterMotionGroup monsterMotionGroup = new MonsterMotionGroup();
            //모션정보 있는지 확인
            if (imgAnim.getMotionSet() != null) {
                for (MotionAnim motionAnim : imgAnim.getMotionSet().getMotionAnimList()) {
                    MonsterMotionObject monsterMotionObject = new MonsterMotionObject();
                    for (String motion : motionAnim.getMotionList()) {
                        monsterMotionObject.addMotion(motion);
                    }
                    ArrayList<PointXY> motionNavigation;
                    motionNavigation = (MotionCalc.sGetMonsterMotionList(
                            monsterDrawInfo.getCrtX(),
                            monsterDrawInfo.getCrtY(),
                            monsterMotionObject.getMotionList()
                    ));
                    if (motionNavigation.size() == 0) {
                        //모션이 없을경우 기본 피격 모션 사용
                        motionNavigation = (MotionCalc.sGetMonsterMotion(
                                monsterDrawInfo.getCrtX(),
                                monsterDrawInfo.getCrtY(),
                                "baseStruckMotion01"
                        ));
                        monsterMotionObject.setMotionNavigation(motionNavigation);
                        monsterMotionObject.setLastMotionNum(monsterMotionObject.getMotionNavigation().size());
                        monsterMotionObject.setFrameDelay(1);

                    } else {
                        //모션이 있을경우 추가
                        monsterMotionObject.setMotionNavigation(motionNavigation);
                    }
                    monsterMotionObject.setLastMotionNum(monsterMotionObject.getMotionNavigation().size());
                    monsterMotionObject.setBackDelay(motionAnim.getBackDelay());
                    monsterMotionObject.setFrameDelay(motionAnim.getFrameDelay());
                    monsterMotionObject.setFrontDelay(motionAnim.getFrontDelay());
                    monsterMotionObject.setRepeat(motionAnim.getRepeat());

                    monsterMotionGroup.addMonsterMotionObject(monsterMotionObject);
                    monsterMotionGroup.setLastDelay(imgAnim.getMotionSet().getLastDelay());
                    monsterMotionGroup.setStartDelay(imgAnim.getMotionSet().getStartDelay());
                    monsterMotionGroup.setRepeatDelay(imgAnim.getMotionSet().getRepeatDelay());
                    monsterMotionGroup.setRepeat(imgAnim.getMotionSet().getRepeat());
                    monsterMotionGroup.setFrameDelay(imgAnim.getMotionSet().getFrameDelay());
                }
                monsterMotionGroup.setLastMotionGroupNum(monsterMotionGroup.getMonsterMotionObjectList().size());
            }
            //모션셋 자체가 없는 경우
            else {
                MonsterMotionObject monsterMotionObject = new MonsterMotionObject();

                //모션이 없을경우 기본 피격 모션 사용
                ArrayList<PointXY> motionNavigation = (MotionCalc.sGetMonsterMotion(
                        monsterDrawInfo.getCrtX(),
                        monsterDrawInfo.getCrtY(),
                        "baseStruckMotion01"
                ));
                monsterMotionObject.setMotionNavigation(motionNavigation);
                monsterMotionObject.setLastMotionNum(monsterMotionObject.getMotionNavigation().size());
                monsterMotionObject.setFrameDelay(1);
                monsterMotionGroup.addMonsterMotionObject(monsterMotionObject);
            }

            //monsterPartDrawInfo set
            monsterDrawInfo.setRepeat(imgAnim.getRepeat());
            monsterDrawInfo.setFrameDelay(imgAnim.getFrameDelay());
            monsterDrawInfo.setMotionGroup(monsterMotionGroup);
            //이미지 정보 채우기
            monsterDrawInfoList.add(monsterDrawInfo);
        }
        return monsterDrawInfoList;

    }

    //피격시 이미지 설정 (기본 모션을 추기하기 위해 따로 설정)
    public ArrayList<MonsterDrawBitmapInfo> getAutoSetMonsterPartDrawInfoStruck01(RealmList<ImgAnim> imgAnimList,
                                                                                  MonsterInfo monsterInfo,
                                                                                  int monsterSize) {
        ArrayList<MonsterDrawBitmapInfo> monsterPartDrawInfoList = new ArrayList<MonsterDrawBitmapInfo>();

        //MonsterPart set
        for (ImgAnim imgAnim : imgAnimList) {
            MonsterDrawBitmapInfo monsterPartDrawInfo = new MonsterDrawBitmapInfo();
            //프레임 딜레이 설정
            monsterPartDrawInfo.setFrameDelay(imgAnim.getFrameDelay());

            //MonsterPArtDrawInfo Img set
            monsterPartDrawInfo.setCanvasLastPage(imgAnim.getImgList().size());
            for (String img : imgAnim.getImgList()) {

                Bitmap bitmap = BitmapFactory.decodeResource(mResources,
                        mResources.getIdentifier(img, "drawable", "gun2.dev.glovesquest"));
                //크기 조정
                bitmap = BitmapUtil.resizeBitmapBaseWidth(bitmap,
                        DeviceEnvironment.sMonsterWidth +
                                monsterSize * DeviceEnvironment.sLogicalWidth);

                //Crt좌표가 비어있으면 채우기
                if (monsterPartDrawInfo.getCrtX() == 0) {
                    monsterPartDrawInfo.setCrtX(bitmap.getWidth() / 2);
                    monsterPartDrawInfo.setCrtY(bitmap.getHeight());

                }
                //파트 이미지 채우기
                monsterPartDrawInfo.addMonsterImgBitmap(bitmap);
            }
            //모션 정보 채우기
            //MotionSet
            MonsterMotionGroup monsterMotionGroup = new MonsterMotionGroup();
            //모션정보 있는지 확인
            if (imgAnim.getMotionSet() != null) {
                for (MotionAnim motionAnim : imgAnim.getMotionSet().getMotionAnimList()) {
                    MonsterMotionObject monsterMotionObject = new MonsterMotionObject();
                    for (String motion : motionAnim.getMotionList()) {
                        monsterMotionObject.addMotion(motion);
                    }
                    ArrayList<PointXY> motionNavigation;
                    motionNavigation = (MotionCalc.sGetMonsterMotionList(
                            monsterPartDrawInfo.getCrtX(),
                            monsterPartDrawInfo.getCrtY(),
                            monsterMotionObject.getMotionList()
                    ));
                    if (motionNavigation.size() == 0) {
                        //모션이 없을경우 기본 피격 모션 사용
                        motionNavigation = (MotionCalc.sGetMonsterMotion(
                                monsterPartDrawInfo.getCrtX(),
                                monsterPartDrawInfo.getCrtY(),
                                "baseStruckMotion01"
                        ));
                        monsterMotionObject.setMotionNavigation(motionNavigation);
                        monsterMotionObject.setLastMotionNum(monsterMotionObject.getMotionNavigation().size());
                        monsterMotionObject.setFrameDelay(1);

                    } else {
                        //모션이 있을경우 추가
                        monsterMotionObject.setMotionNavigation(motionNavigation);
                    }
                    monsterMotionObject.setLastMotionNum(monsterMotionObject.getMotionNavigation().size());
                    monsterMotionObject.setBackDelay(motionAnim.getBackDelay());
                    monsterMotionObject.setFrameDelay(motionAnim.getFrameDelay());
                    monsterMotionObject.setFrontDelay(motionAnim.getFrontDelay());
                    monsterMotionObject.setRepeat(motionAnim.getRepeat());

                    monsterMotionGroup.addMonsterMotionObject(monsterMotionObject);
                    monsterMotionGroup.setLastDelay(imgAnim.getMotionSet().getLastDelay());
                    monsterMotionGroup.setStartDelay(imgAnim.getMotionSet().getStartDelay());
                    monsterMotionGroup.setRepeatDelay(imgAnim.getMotionSet().getRepeatDelay());
                    monsterMotionGroup.setRepeat(imgAnim.getMotionSet().getRepeat());
                    monsterMotionGroup.setFrameDelay(imgAnim.getMotionSet().getFrameDelay());
                }
                monsterMotionGroup.setLastMotionGroupNum(monsterMotionGroup.getMonsterMotionObjectList().size());
            }
            //모션셋 자체가 없는 경우
            else {
                MonsterMotionObject monsterMotionObject = new MonsterMotionObject();

                //모션이 없을경우 기본 피격 모션 사용
                ArrayList<PointXY> motionNavigation = (MotionCalc.sGetMonsterMotion(
                        monsterPartDrawInfo.getCrtX(),
                        monsterPartDrawInfo.getCrtY(),
                        "baseStruckMotion01"
                ));
                monsterMotionObject.setMotionNavigation(motionNavigation);
                monsterMotionObject.setLastMotionNum(monsterMotionObject.getMotionNavigation().size());
                monsterMotionObject.setFrameDelay(1);
                monsterMotionGroup.addMonsterMotionObject(monsterMotionObject);
            }

            //monsterPartDrawInfo set
            monsterPartDrawInfo.setRepeat(imgAnim.getRepeat());
            monsterPartDrawInfo.setFrameDelay(imgAnim.getFrameDelay());
            monsterPartDrawInfo.setMonsterMotionGroup(monsterMotionGroup);
            //이미지 정보 채우기
            monsterPartDrawInfoList.add(monsterPartDrawInfo);
        }
        return monsterPartDrawInfoList;

    }


    public ArrayList<MonsterDrawInfoData> getMonsterDrawInfoListClone(ArrayList<MonsterDrawInfo> monsterDrawInfoList,
                                                                      CreatedMonster createdMonster) {
        ArrayList<MonsterDrawInfoData> defaultImgList = new ArrayList<>();
        for (MonsterDrawInfo monsterDrawInfo : monsterDrawInfoList) {
            MonsterDrawInfoData monsterDrawInfoData = new MonsterDrawInfoData();

            monsterDrawInfoData.setMonsterImgList(monsterDrawInfo.getMonsterImgList());
            monsterDrawInfoData.setCanvasLastPage(monsterDrawInfo.getCanvasLastPage());
            monsterDrawInfoData.setRepeat(monsterDrawInfo.getRepeat());
            monsterDrawInfoData.setCrtX(monsterDrawInfo.getCrtX());
            monsterDrawInfoData.setCrtY(monsterDrawInfo.getCrtY());
            monsterDrawInfoData.setFrameDelay(monsterDrawInfo.getFrameDelay());
            monsterDrawInfoData.setCreatedMonster(createdMonster);
//            monsterDrawInfoData.setFadeSpeed(monsterDrawInfo.getFadeSpeed());
            monsterDrawInfoData.setStiffenTime(monsterDrawInfo.getStiffenTime());

            //MotionGroup 세팅
            MonsterMotionGroup monsterMotionGroup = new MonsterMotionGroup();
            for (MonsterMotionObject _monsterMotionObject : monsterDrawInfo.getMotionGroup().getMonsterMotionObjectList()) {
                MonsterMotionObject monsterMotionObject = new MonsterMotionObject();
                monsterMotionObject.setFrameDelay(_monsterMotionObject.getFrameDelay());
                monsterMotionObject.setRepeat(_monsterMotionObject.getRepeat());
                monsterMotionObject.setBackDelay(_monsterMotionObject.getBackDelay());
                monsterMotionObject.setFrontDelay(_monsterMotionObject.getFrontDelay());
                monsterMotionObject.setLastMotionNum(_monsterMotionObject.getLastMotionNum());
                monsterMotionObject.setMotionNavigation(_monsterMotionObject.getMotionNavigation());
                monsterMotionObject.setCreatedMonster(createdMonster);
                monsterMotionObject.setMonsterMotionGroup(monsterMotionGroup);

                monsterMotionGroup.addMonsterMotionObject(monsterMotionObject);
            }
            monsterDrawInfoData.setMotionGroup(monsterMotionGroup);

            defaultImgList.add(monsterDrawInfoData);
        }
        return defaultImgList;
    }


    //--------------
    //test gen
    //---------------
    public void monsterGen() {
        for (MonsterInfo monsterInfo : mMonsterInfoList) {
            if (mTimeFlow / monsterInfo.getMonsterGenTime() == 0) {
                for (CreatedMonster createdMonster : mCreatedMonsterList) {
                    if (createdMonster.getCurrentState() == -7) {
                        //죽은 몬스터 체력, 상태, 좌표 초기화 후 살림

                        createdMonster.setCurrentState(2);
                    }
                }

                // 죽은 몬스터 젠 + 아이템 설정 + 몬스터 젠 리미트 (아직 사용 안함) 확인하고 젠
            }
        }

        mTimeFlow++;
    }


    public void changeMonsterCurrentState(int i) {
        for (MonsterCurrentLocation monsterCurrentLocation : mMonsterCurrentLocationList) {
            monsterCurrentLocation.getCreatedMonster().setCurrentState(i);
        }
    }

    //이미지 설정 초기화 (모션 설정 초기화도 추후 개발해야함)
    public void recycleMonster_TestVer() {
        for (MonsterInfo monsterInfo : mMonsterInfoList) {
            //기본 이미지 세팅 초기화
            for (MonsterPartDrawInfo monsterPartDrawInfo : monsterInfo.getMonsterPart().getDefaultImgList()) {
                monsterPartDrawInfo.recycleData();
            }
            //죽음 이미지 세팅 초기화
            for (MonsterPartDrawInfo monsterPartDrawInfo : monsterInfo.getMonsterPart().getDeadImgList()) {
                monsterPartDrawInfo.recycleData();
            }
        }
    }


    public void onDrawMonster(Canvas canvas) {
//        canvas.drawRect(0, 0, mMediaWidth, mMediaHeight, mBackground);
        //몬스터 이름
//        canvas.drawText(mMonsterName, mMediaCenterX, 100, mMonsterNamePaint);
        for (MonsterInfo monsterInfo : mMonsterInfoList) {
            switch (monsterInfo.getCurrentState()) {
                case -6:
                    //으앙주금
                    for (MonsterPartDrawInfo monsterPartDrawInfo : monsterInfo.getMonsterPart().getDeadImgList()) {
                        PointXY pointXY = monsterPartDrawInfo.getCurrentPointXY();
                        canvas.drawBitmap(monsterPartDrawInfo.getCurrentMonsterImgBitmap(0000),
                                pointXY.getX(),
                                pointXY.getY()
                                , monsterPartDrawInfo.getFadeOutPaintDead());
                    }
                    break;
                case -4:
                    //취소불가 스킬시전
                    break;
                case -2:
                    //맞음
                    for (MonsterPartDrawInfo monsterPartDrawInfo : monsterInfo.getMonsterPart().getStruckImgList()) {
                        PointXY pointXY = monsterPartDrawInfo.getCurrentPointXY();
                        canvas.drawBitmap(monsterPartDrawInfo.getCurrentMonsterImgBitmap(00000),
                                pointXY.getX(),
                                pointXY.getY()
                                , monsterPartDrawInfo.getStruckPaintFilter());
                    }
                    break;
                case 0:
                    //기본동작
                    for (MonsterPartDrawInfo monsterPartDrawInfo : monsterInfo.getMonsterPart().getDefaultImgList()) {
                        PointXY pointXY = monsterPartDrawInfo.getCurrentPointXY();
                        canvas.drawBitmap(monsterPartDrawInfo.getCurrentMonsterImgBitmap(000000),
                                pointXY.getX(),
                                pointXY.getY()
                                , null);
                    }
                    break;
                case 2:
                    //젠
                    // 젠 이미지가 설정되않을경우 디폴트 설정으로 첫번째 기본 이미지를 페이드인함
                    if (monsterInfo.getMonsterPart().getGetGenImgList().size() == 0) {
                        for (MonsterPartDrawInfo monsterPartDrawInfo : monsterInfo.getMonsterPart().getDefaultImgList()) {
                            canvas.drawBitmap(monsterPartDrawInfo.getMonsterImg().get(0),
                                    monsterInfo.getMonsterPart().getDefaultImgList().get(0).getCrtX(),
                                    monsterInfo.getMonsterPart().getDefaultImgList().get(0).getCrtY()
                                    , monsterPartDrawInfo.getFadeInPaintGen());
                        }
                    } else {
                        //기타 젠 이미지 설정
                        //추후 설정
                    }

                    break;
            }
        }
    }


    public void onDrawMonster02(Canvas canvas) {
//        canvas.drawRect(0, 0, mMediaWidth, mMediaHeight, mBackground);
        //몬스터 이름
//        canvas.drawText(mMonsterName, mMediaCenterX, 100, mMonsterNamePaint);
        for (MonsterCurrentLocation monsterCurrentLocation : mMonsterCurrentLocationList) {
            CreatedMonster createdMonster = monsterCurrentLocation.getCreatedMonster();
            switch (createdMonster.getCurrentState()) {
                case -60:
                    //으앙주금
                    mMonsterDeadActionList.add(
                            new MonsterDeadAction(createdMonster.getDeadImgList(),
                                    createdMonster.getCurrentX(),
                                    createdMonster.getCurrentY())
                    );
                    createdMonster.setCurrentState(-100);

                    break;
                case -40:
                    //취소불가 스킬시전
                    createdMonster.skillDrawAction(canvas);
                    break;
                case -20:
                    //맞음
                    createdMonster.struckDrawAction(canvas);
                    break;
                case 0:
                    //기본동작
                    createdMonster.defaultDrawAction(canvas);
                    break;
                case 20:
                    //젠
                    // 젠 이미지가 설정되않을경우 디폴트 설정으로 첫번째 기본 이미지를 페이드인함
                    if (createdMonster.getGenImgList() == null) {
                        createdMonster.defaultGenDrawAction(canvas);

                    } else {
                        //기타 젠 이미지 설정
                        //추후 설정
                    }

                    break;
            }
        }//몬스터 행동에 따라 그리기 for

        //죽은 몬스터 잔상 그리기
        for (Iterator<MonsterDeadAction> it = mMonsterDeadActionList.iterator(); it.hasNext(); ) {
            //죽는 동작 중이면 그리기
            MonsterDeadAction monsterDeadAction = it.next();
            if (!monsterDeadAction.isEnd()) {
                monsterDeadAction.defaultDeadDrawAction(canvas);
            }
            //동작이 끝나면 삭제
            else {
                it.remove();
            }
        }
        //10프레임 마다 몬스터 위치 갱신
        if (DeviceEnvironment.sFrame % 10 == 0 ) Collections.sort(mMonsterCurrentLocationList);
    }


    /**
     * recycle zone
     */
    public void recycle() {
        int i = 0;
        for (MonsterGenManager monsterGenManager : mMonsterGenManagerList) {
            i++;
            mLog.msg(":::::"+i+"번젠 리스트:::::");
            try {
                mLog.msg("몬스터 기본 이미지 제거 시작");
                monsterDrawInfoListRecycle(monsterGenManager.getMonsterShareData().getMonsterDefaultDrawInfoList());
            } catch (Exception e) {
                mLog.msg("몬스터 기본 이미지 err");
            }
            try {
                mLog.msg("몬스터 사망 이미지 제거 시작");
                monsterDrawInfoListRecycle(monsterGenManager.getMonsterShareData().getMonsterDeadDrawInfoList());
            } catch (Exception e) {
                mLog.msg("몬스터 사망 이미지 err");
            }
            try {
                mLog.msg("몬스터 피격 이미지 제거 시작");
                monsterDrawInfoListRecycle(monsterGenManager.getMonsterShareData().getMonsterStruckDrawInfoList());
            } catch (Exception e) {
                mLog.msg("몬스터 피격 이미지 err");
            }
            try {
                mLog.msg("몬스터 젠 이미지 제거 시작");
                monsterDrawInfoListRecycle(monsterGenManager.getMonsterShareData().getMonsterGenDrawInfoList());
            } catch (Exception e) {
                mLog.msg("몬스터 젠 이미지 err");
            }
            try {
                mLog.msg("몬스터 스킬 이미지 제거 시작");
                monsterSkillAlgorithmInfoRecycle(monsterGenManager.getMonsterShareData().getMonsterSkillAlgorithmInfo());
            } catch (Exception e) {
                mLog.msg("몬스터 피격 이미지 err");
            }
        }

    }

    public void monsterDrawInfoListRecycle(ArrayList<MonsterDrawInfo> monsterDrawInfoList) {
        for (MonsterDrawInfo monsterDrawInfo : monsterDrawInfoList) {
            for (Bitmap bitmap : monsterDrawInfo.getMonsterImgList()) {
                bitmap.recycle();
            }
        }
    }

    public void monsterSkillAlgorithmInfoRecycle(MonsterSkillAlgorithmInfo monsterSkillAlgorithmInfo) {
        try {
            mLog.msg("기본스킬 제거 시작");
            perMonsterSkillInfoListRecycle(monsterSkillAlgorithmInfo.getDefaultSkillList());
        } catch (Exception e) {
            mLog.msg("기본스킬 제거 err");
        }
        try {
            mLog.msg("지정스킬 제거 시작");
            perMonsterSkillInfoListRecycle(monsterSkillAlgorithmInfo.getMonsterSkillList());
        } catch (Exception e) {
            mLog.msg("지정스킬 제거 err");
        }
        try {
            mLog.msg("시작스킬 제거 시작");
            perMonsterSkillInfoListRecycle(monsterSkillAlgorithmInfo.getStartSkillList());
        } catch (Exception e) {
            mLog.msg("시작스킬 제거 err");
        }
    }

    public void perMonsterSkillInfoListRecycle(ArrayList<PerMonsterSkillInfo> perMonsterSkillInfoList) {
        for (PerMonsterSkillInfo perMonsterSkillInfo : perMonsterSkillInfoList) {
            try {
                mLog.msg("몬스터 스킬 캐스트 이미지 리스트 제거 시작");
                monsterSkillDrawInfoListRecycle(perMonsterSkillInfo.getSkillCastImgList());
            } catch (Exception e) {
                mLog.msg("몬스터 스킬 캐스트 이미지 리스트 제거 err");
            }

            try {
                mLog.msg("몬스터 스킬 발동 이미지 리스트 제거 시작");
                monsterSkillDrawInfoListRecycle(perMonsterSkillInfo.getSkillFireImgList());
            } catch (Exception e) {
                mLog.msg("몬스터 스킬 발동 이미지 리스트 제거 err");
            }

            try {
                mLog.msg("몬스터 스킬 캐스트 이펙트 발동 제거 시작");
                monsterSkillAnimInfoRecycle(perMonsterSkillInfo.getMonsterSkillInfo().getSkillCastImg());
            } catch (Exception e) {
                mLog.msg("몬스터 스킬 캐스트 이펙트 발동 제거 err");
            }

            try {
                mLog.msg("몬스터 스킬 발동 이펙트 발동 제거 시작");
                monsterSkillAnimInfoRecycle(perMonsterSkillInfo.getMonsterSkillInfo().getSkillFireImg());
            } catch (Exception e) {
                mLog.msg("몬스터 스킬 발동 이펙트 발동 제거 err");
            }

            try {
                mLog.msg("몬스터 스킬 효과 이펙트 제거 시작");
                //추후 작성

            } catch (Exception e) {
                mLog.msg("몬스터 스킬 효과 이펙트 제거 err");
            }

        }
    }

    public void monsterSkillDrawInfoListRecycle(ArrayList<MonsterSkillDrawInfo> monsterSkillDrawInfoList) {
        for (MonsterSkillDrawInfo monsterSkillDrawInfo : monsterSkillDrawInfoList) {
            monsterSkillDrawInfoRecycle(monsterSkillDrawInfo);
        }
    }

    public void monsterSkillDrawInfoRecycle(MonsterSkillDrawInfo monsterSkillDrawInfo) {
        for (Bitmap bitmap : monsterSkillDrawInfo.getSkillImgList()) {
            bitmap.recycle();
        }
    }

    public void monsterSkillAnimInfoRecycle(MonsterSkillAnimInfo monsterSkillAnimInfo) {
        try {
            mLog.msg("back 이펙트 제거 시작");
            monsterSkillDrawInfoRecycle(monsterSkillAnimInfo.getBackSkillAnim());
        } catch (Exception e) {
            mLog.msg("back 이펙트 제거 err");
        }

        try {
            mLog.msg("front 이펙트 제거 시작");
            monsterSkillDrawInfoRecycle(monsterSkillAnimInfo.getFrontSkillAnim());
        } catch (Exception e) {
            mLog.msg("front 이펙트 제거 err");
        }
    }
}

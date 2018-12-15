package gun2.dev.glovesquest.utils;

import android.os.Environment;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

/**
 * need Gson lib
 */
public class JsonEditor {
    //----------------
    //interface zone
    //----------------

    /**
     * getJson()용 함수형 인터페이스
     */
    @FunctionalInterface
    public interface ClassTypeSet{
        Object[] set(Gson gson, BufferedReader bufferedReader);
    }
    private ClassTypeSet classTypeSet = new ClassTypeSet() {
        @Override
        public Object[] set(Gson gson, BufferedReader bufferedReader) {
            return new Object[0];
        }
    };

    /**
     * 참 거짓 판별용
     */
    @FunctionalInterface
    public interface Solomon{
        boolean rul(int i);
    }
    Solomon solomon = new Solomon() {
        @Override
        public boolean rul(int i) {
            return false;
        }
    };

    private String mPath;   //경로 저장

    public JsonEditor(String path){
        mPath = Environment.getExternalStorageDirectory().getAbsolutePath()+path;
    }


    /**
     * 디렉터리 경로 생성
     * @param path 디렉터리 경로만 삽입
     */
    public void checkDir(String path){
        try{
            File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+path);
            if (!file.exists()) file.mkdirs();
        }catch (Exception e){
            Log.d("JsonEditor", "DirCheckErr");
        }
    }

    /**
     * Json파일을 읽고
     * 객체 포맷을 확인 한다
     * 포맷이 맞으면 Json을 출력하고
     * 올바르지 않으면 에러표시가 난다.
     *
     */
    public boolean checkObject(Object[] object, ClassTypeSet method){
        try {

            BufferedReader bufferedReader = new BufferedReader(new FileReader(mPath));

            Gson gson = new Gson();

            object = method.set(gson, bufferedReader);
            for (Object obj : object)    {
                System.out.println(obj.toString());
            }
            return true;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.d(object.getClass().getName().trim(), "is checkObject err");
            return false;
        }
    }


    /**
     * 해당 Json파일에서 Json을 얻어옴
     * @param object
     */
    public Object[] getJson(Object[] object, ClassTypeSet method){
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(mPath));
            Gson gson = new Gson();
            if(new FileReader(mPath).read() != -1) {
                object = method.set(gson, bufferedReader);
                for (Object obj : object) {
                    System.out.println(obj.toString());
                }
            }
            return object;

        } catch (java.io.IOException e) {
            e.printStackTrace();
            Log.d(object.getClass().getName().trim(), "is checkObject err");

            return object;
        }
    }

    /**
     * 해당 Json에서 object항목의 번호를 얻어옴
     * 존재하지 않으면 -1 리턴
     * @param Json
     */
    public int searchObjInJson(Object[] Json, Solomon method){
        if (Json != null) {
            for (int i = 0; i < Json.length; i++) {
                if (method.rul(i)) return i;
            }
        }
        return -1;
    }

    /**
     *
     * @param jsons 배열의
     * @param seq 순서에

     */
    public void editJson(Object[] jsons, Object object, int seq) {

        //Object[] result = new Object[jsons.length+1];
        ArrayList<Object> result = new ArrayList<Object>();
        if(jsons == null){
            //새로 생성
        }
        else if (jsons.length == 0){
            //Json 새로 생성
//            Object[] result = new Object[1];
            result.add(object);
        }
        else {
            if (seq >= 0){
                //교체
                jsons[seq] = object;
//                Object[] result = new Object[jsons.length];
                for (Object obj : jsons){
                    result.add(obj);
                }
            }
            else {
//                Object[] result = new Object[jsons.length+1];
//                result = jsons;
//                result[jsons.length] = object;
                for (Object obj : jsons){
                    result.add(obj);
                }
                result.add(object);
                //추가

            }

        }



        // 경우의수
        /********변경점**********/

        //Log.d("TAG", ""+jsons.length);

        /**********************/

        Gson gson = new Gson();

        File file = new File(mPath);

        if (!file.exists()) {
            try {
                file.createNewFile();
            }catch (Exception e){
                Log.w("JsonEditor" ,"editJson() createNewFile() is failed");
            }
        }

        FileWriter fw = null;
        try {
            // open file.
            fw = new FileWriter(file);
            BufferedWriter bufwr = new BufferedWriter(fw);

            // write file.
            bufwr.write(gson.toJson(result));
            bufwr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // close file.
        if (fw != null) {
            // catch Exception here or throw.
            try {
                fw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }


}

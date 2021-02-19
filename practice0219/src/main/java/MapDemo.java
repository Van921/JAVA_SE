import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapDemo {
    public static void main(String[] args) {
        Map<String,Integer> map = new HashMap<>();
//        Integer value = map.put("语文",90);
//        System.out.println("value:"+value);
        map.put("数学",51);
        map.put("英语",97);
        map.put("历史",96);
        map.put("物理",87);
        map.put("化学",68);
        System.out.println(map);

//        value = map.put("语文",85);
//        System.out.println(map);
//        System.out.println(value);
//
//        value = map.get("数学");
//        System.out.println("数学："+value);
//        value = map.get("体育");
//        System.out.println("体育："+value);
//
//        value = map.remove("数学");
//        System.out.println(map);
//        System.out.println(value);
//
//        boolean ck = map.containsKey("英语");
//
//        boolean cv = map.containsValue(97);
//        System.out.println("包含Key："+ck);
//        System.out.println("包含value："+cv);

        Set<String> keySet = map.keySet();
        for (String key: keySet){
            System.out.println("key："+key);
        }

        Set<Map.Entry<String,Integer>>entrySet = map.entrySet();
        for (Map.Entry<String , Integer> e :entrySet){
            String key = e.getKey();
            Integer value = e.getValue();
            System.out.println(key+":"+value);
        }

        Collection<Integer> values = map.values();
        for (Integer value:values){
            System.out.println("value:"+value);
        }

    }
}

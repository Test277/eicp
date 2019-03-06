package test.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class BeanUtil {
    public Object copyBean(Object source) throws IllegalAccessException, InstantiationException {
        Class c = source.getClass();
        Object target = c.newInstance();
        Field[] fields = c.getDeclaredFields();
        for(Field f : fields) {
            f.setAccessible(true);
            if(f.getType().equals(List.class) && f.get(source) != null){
                List targetList = new ArrayList();
                List sourceList = ((List)f.get(source));
                sourceList.forEach(s -> {
                    try {
                        if(s instanceof String){
                            targetList.add(s);
                        }else {
                            targetList.add(copyBean(s));
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    }
                });
                f.set(target, targetList);
            }else {
                f.set(target, f.get(source));
            }
        }
        return target;
    }
}

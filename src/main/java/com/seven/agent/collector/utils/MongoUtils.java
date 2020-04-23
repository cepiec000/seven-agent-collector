package com.seven.agent.collector.utils;

import com.seven.agent.collector.bean.JvmInfoDO;
import org.springframework.data.mongodb.core.query.Update;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MongoUtils {
    public static Update buildUpdate(Object bean) {
        if (null == bean) {
            throw new IllegalArgumentException("update bean is null");
        }
        List<Field> list = new ArrayList<>();
        Class clazz = bean.getClass();
        Class supClazz = clazz.getSuperclass();
        Field[] fields = clazz.getDeclaredFields();
        list.addAll(Arrays.asList(fields));
        if (supClazz != null) {
            list.addAll(Arrays.asList(supClazz.getDeclaredFields()));
        }
        Update update = new Update();
        for (Field field : list) {
            String name = field.getName();
            try {
                if (!"id".equals(name)) {
                    PropertyDescriptor pd = new PropertyDescriptor(name, clazz);
                    update.set(name, pd.getReadMethod().invoke(bean));
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return update;
    }

    public static void main(String[] args) {
        buildUpdate(new JvmInfoDO());
    }
}

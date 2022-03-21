package cn.hutool.db.util;

import cn.hutool.core.convert.Convert;
import cn.hutool.db.Entity;
import cn.hutool.db.anno.Column;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EntityConvertUtil {
    private static Logger logger = LoggerFactory.getLogger(EntityConvertUtil.class);

    private static final Map<Class, Map<Field, String>> classFieldColumnMap = new HashMap();

    private static final Map<Class, Map<String, Field>> classColumnFieldMap = new HashMap();

    /**
     * 只有驼峰转下划线规则，保存抛错，请给对应字段配置 @Column 注解定义字段名
     * @param obj
     * @return
     */
    public static Entity objToEntity(Object obj) {
        if (obj==null) {
            return null;
        }
        Map<Field, String> fieldColumnMap = getFieldColumnMap(obj.getClass());
        try {
            Entity entity = Entity.create();
            for (Field field:fieldColumnMap.keySet()) {
                entity.put(fieldColumnMap.get(field), field.get(obj));
            }
            return entity;
        } catch (IllegalAccessException e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static <T> T entityToobj(Entity entity, Class<T> clazz) {
        if (entity==null) {
            return null;
        }
        try {
            T t = clazz.newInstance();
            Map<String, Field> columnFieldMap = getColumnFieldMap(clazz);
            for (String columnName:columnFieldMap.keySet()) {
                if (entity.containsKey(columnName)==false) {
                    continue;
                }
                Field field = columnFieldMap.get(columnName);
                Class fieldType = field.getType();
                Object value = entity.get(columnName);
                if (fieldType == Integer.class) {
                    field.set(t, Convert.toInt(value));
                } else if (fieldType == Integer[].class) {
                    field.set(t, Convert.toIntArray(value));
                } else if (fieldType == String.class) {
                    field.set(t, Convert.toStr(value));
                } else if (fieldType == String[].class) {
                    field.set(t, Convert.toStrArray(value));
                } else if (fieldType == Character.class) {
                    field.set(t, Convert.toChar(value));
                } else if (fieldType == Character[].class) {
                    field.set(t, Convert.toCharArray(value));
                } else if (fieldType == Byte.class) {
                    field.set(t, Convert.toByte(value));
                } else if (fieldType == Byte[].class) {
                    field.set(t, Convert.toByteArray(value));
                } else if (fieldType == byte[].class) {
                    field.set(t, Convert.toPrimitiveByteArray(value));
                } else if (fieldType == Short.class) {
                    field.set(t, Convert.toShort(value));
                } else if (fieldType == Short[].class) {
                    field.set(t, Convert.toShortArray(value));
                } else if (fieldType == Number.class) {
                    field.set(t, Convert.toNumber(value));
                } else if (fieldType == Number[].class) {
                    field.set(t, Convert.toNumberArray(value));
                } else if (fieldType == Long.class) {
                    field.set(t, Convert.toLong(value));
                } else if (fieldType == Long[].class) {
                    field.set(t, Convert.toLongArray(value));
                } else if (fieldType == Double.class) {
                    field.set(t, Convert.toDouble(value));
                } else if (fieldType == Double[].class) {
                    field.set(t, Convert.toDoubleArray(value));
                } else if (fieldType == Float.class) {
                    field.set(t, Convert.toFloat(value));
                } else if (fieldType == Float[].class) {
                    field.set(t, Convert.toFloatArray(value));
                } else if (fieldType == Boolean.class) {
                    field.set(t, Convert.toBool(value));
                } else if (fieldType == Boolean[].class) {
                    field.set(t, Convert.toBooleanArray(value));
                } else if (fieldType == BigInteger.class) {
                    field.set(t, Convert.toBigInteger(value));
                } else if (fieldType == BigDecimal.class) {
                    field.set(t, Convert.toBigDecimal(value));
                } else if (fieldType == Date.class) {
                    field.set(t, Convert.toDate(value));
                }  else if (fieldType == java.sql.Date.class) {
                    field.set(t, Convert.convert(java.sql.Date.class, value));
                } else if (fieldType == Timestamp.class) {
                    field.set(t, Convert.convert(Timestamp.class, value));
                } else {
                    throw new RuntimeException(clazz+" 的 "+field.getName()+" 属性不属于常规类型，无法转换");
                }
            }
            return t;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    private static Map<String, Field> getColumnFieldMap(Class clazz) {
        Map<String, Field> columnFieldMap = classColumnFieldMap.get(clazz);
        if (columnFieldMap!=null) {
            return columnFieldMap;
        }
        synchronized (clazz+"getColumnFieldMap") {
            columnFieldMap = classColumnFieldMap.get(clazz);
            if (columnFieldMap!=null) {
                return columnFieldMap;
            }
            columnFieldMap = new HashMap();
            Map<Field, String> fieldColumnMap = getFieldColumnMap(clazz);
            for (Field field:fieldColumnMap.keySet()) {
                columnFieldMap.put(fieldColumnMap.get(field), field);
            }
        }
        return columnFieldMap;
    }

    private static Pattern humpPattern = Pattern.compile("[A-Z]");

    /** 驼峰转下划线,效率比上面高 */
    public static String humpToLine(String str) {
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    private static Map<Field, String> getFieldColumnMap(Class clazz) {
        Map<Field, String> fieldColumnMap = classFieldColumnMap.get(clazz);
        if (fieldColumnMap!=null) {
            return fieldColumnMap;
        }
        synchronized(clazz+"getFieldColumnMap") {
            fieldColumnMap = classFieldColumnMap.get(clazz);
            if (fieldColumnMap!=null) {
                return fieldColumnMap;
            }
            fieldColumnMap = new HashMap();
            classFieldColumnMap.put(clazz, fieldColumnMap);
            Field[] fields = clazz.getDeclaredFields();
            if (fields == null || fields.length==0) {
                return fieldColumnMap;
            }
            for (Field field:fields) {
                if (Modifier.isStatic(field.getModifiers())) {
                    continue;
                }
                field.setAccessible(true);
                Column annon = field.getAnnotation(Column.class);
                if (annon != null) {
                    String columnName = annon.value();
                    if (columnName!=null && columnName.trim().equals("")==false) {
                        fieldColumnMap.put(field, columnName.trim());
                        continue;
                    }
                }
                String fieldName = field.getName();
                fieldColumnMap.put(field, humpToLine(fieldName));
            }
        }
        return fieldColumnMap;
    }

}

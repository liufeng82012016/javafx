package com.my.liufeng.chat.Utils;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.my.liufeng.chat.entity.Message;

import java.lang.reflect.Field;
import java.util.Date;

public class CreateTableUtils {
    /**
     * 根据实体类生成create_table 语句
     */
    public static String createTable(Class<?> clazz) {
        TableName tableName = clazz.getAnnotation(TableName.class);
        if (tableName == null || tableName.value() == null) {
            throw new RuntimeException("unknown table: " + clazz.getSimpleName());
        }
        StringBuilder stringBuilder = new StringBuilder("create table ").append(tableName.value());
        stringBuilder.append("(\n");
        Field[] fields = clazz.getDeclaredFields();
        // 遍历元素
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            TableField tableField = field.getAnnotation(TableField.class);
            String value;
            if (tableField != null) {
                value = tableField.value();
            } else {
                // 小驼峰转下划线
                value = toUnderLine(field.getName());
            }
            stringBuilder.append(value).append(" ");

            Class<?> fieldType = field.getType();
            String dataSourceType = getDataSourceType(fieldType,field.getName());

            stringBuilder.append(dataSourceType);

            TableId tableId = field.getAnnotation(TableId.class);

            if (tableId != null) {
                stringBuilder.append(" primary key  auto_increment ");
//                IdType type = tableId.type();
//                if (type == null) {
//                    // 默认自增
//                    type = IdType.AUTO;
//                }
//                if (IdType.AUTO == type) {
//                    stringBuilder.append(" auto_increment ");
//                }
            }
            if (i != fields.length - 1) {
                stringBuilder.append(",");
            }
            stringBuilder.append("\n");
        }
        stringBuilder.append(") engine=InnoDB;");
        return stringBuilder.toString();
    }

    private static String getDataSourceType(Class<?> fieldType, String fieldName) {
        if (String.class == fieldType) {
            return " varchar(64) ";
        } else if (Byte.class == fieldType) {
            return "tinyint";
        } else if (Integer.class == fieldType) {
            return " int ";
        } else if (Long.class == fieldType) {
            return " bigint ";
        } else if (Date.class == fieldType) {
            if ("createTime".equals(fieldName)) {
                return " timestamp default current_timestamp ";
            } else if ("updateTime".equals(fieldName)) {
                return " timestamp default current_timestamp on update current_timestamp ";
            }
            return " timestamp ";
        }
        return "";
    }

    private static String toUnderLine(String str) {
        char[] chars = str.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        for (char c : chars) {
            if (c < 97) {
                c += 32;
                stringBuilder.append("_");
            }
            stringBuilder.append(c);
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        System.out.println(createTable(Message.class));
    }


}

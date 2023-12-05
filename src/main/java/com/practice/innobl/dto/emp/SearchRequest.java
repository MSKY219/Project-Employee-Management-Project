package com.practice.innobl.dto.emp;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Field;

@Data
@ToString
@NoArgsConstructor
public class SearchRequest {

    private String status = "";
    private String skill = "";
    private String grade = "";
    private String dateStart = "";
    private String dateEnd = "";
    private String others = "";
    private String othersDetail = "";

    public String toQueryString() throws IllegalAccessException {
        StringBuffer parameter = new StringBuffer();
        for (Field f : getClass().getDeclaredFields()) {
            f.setAccessible(true);
            String name = f.getName();
            Object value = f.get((Object)this);

            if(!ObjectUtils.isEmpty(value)) {
                parameter.append(name).append("=").append(value).append("&");
            }
        }
        if(!parameter.isEmpty()) {
        parameter.delete(parameter.length()-1, parameter.length());
        }
        return parameter.toString();
    }

    public boolean isEmpty() {
        return status.isEmpty()
                && skill.isEmpty()
                && grade.isEmpty()
                && dateStart.isEmpty()
                && dateEnd.isEmpty()
                && othersDetail.isEmpty();
    }
}
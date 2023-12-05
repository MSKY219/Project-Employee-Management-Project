package com.practice.innobl.mapper;

import com.practice.innobl.dto.TestDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TestMapper {
    // 전체조회
    List<TestDto> getUserList();

    // 등록확인
    void testSaveUser(TestDto inputUser);
}

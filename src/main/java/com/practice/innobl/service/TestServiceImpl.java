package com.practice.innobl.service;

import com.practice.innobl.dto.TestDto;
import com.practice.innobl.mapper.TestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService{
    private final TestMapper testMapper;

    // 전체조회
    @Override
    public List<TestDto> getUserList() {
        return testMapper.getUserList();
    }

    // 등록확인
    @Override
    public void testSaveUser(TestDto inputUser) {
        testMapper.testSaveUser(inputUser);
    }
}

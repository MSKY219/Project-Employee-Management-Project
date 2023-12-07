package com.practice.innobl.controller;

import com.practice.innobl.dto.commoncode.CommondefinitionDto;
import com.practice.innobl.dto.emp.SearchRequest;
import com.practice.innobl.dto.project.AddEmpRequestDto;
import com.practice.innobl.dto.project.ProjectRequestDto;
import com.practice.innobl.dto.project.ProjectResponseDto;
import com.practice.innobl.service.emp.EmpService;
import com.practice.innobl.service.project.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


@Controller
@RequiredArgsConstructor
public class ProjectController {

    private final EmpService empService; // 등록 창 이동 시 필요한 정보를 가지고 오기 위한 용도
    private final ProjectService projectService;

    // 프로젝트 조회 창 이동
    @GetMapping("/project")
    public String projectList(Model model,
                              @RequestParam(value = "cp", required = false, defaultValue = "1") int cp,
                              @RequestParam(value = "status", required = false, defaultValue = "") String status,
                              @RequestParam(value = "skill", required = false, defaultValue = "") String skill,
                              @RequestParam(value = "grade", required = false, defaultValue = "") String grade,
                              @RequestParam(value = "dateStart", required = false, defaultValue = "") String dateStart,
                              @RequestParam(value = "dateEnd", required = false, defaultValue = "") String dateEnd,
                              @RequestParam(value = "others", required = false, defaultValue = "") String others,
                              @RequestParam(value = "othersDetail", required = false, defaultValue = "") String othersDetail
    ) throws IllegalAccessException {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.setStatus(status);
        searchRequest.setSkill(skill);
        searchRequest.setGrade(grade);
        searchRequest.setDateStart(dateStart);
        searchRequest.setDateEnd(dateEnd);
        searchRequest.setOthers(others);
        searchRequest.setOthersDetail(othersDetail);

        String query = searchRequest.toQueryString();

        System.out.println(searchRequest);

        boolean isSearch = !searchRequest.isEmpty();

        List<CommondefinitionDto> getAddOption = empService.getAddOptions();

        if (isSearch) {
            Map<String, Object> getProjectList = projectService.getProjectList(cp, searchRequest);
            model.addAttribute("projectList", getProjectList.get("getProjectList"));
            model.addAttribute("pagination", getProjectList.get("pagination"));
        }
        model.addAttribute("optionList", getAddOption);
        model.addAttribute("projectMenu", "projectList");
        model.addAttribute("searchRequest", searchRequest);
        model.addAttribute("query", query);
        return "project-list";
    }

    // 프로젝트 목록 -> 직원 상태 변경
    @PostMapping("/project/update/st")
    public @ResponseBody String updateSt(ProjectRequestDto projectRequestDto) {

        int result = projectService.updateSt(projectRequestDto);

        return null;
    }

    // 프로젝트 등록 창 이동
    @GetMapping("/project/add")
    public String addProject(Model model) {
        // 등록 창 이동시 필요 정보 가지고 오기
        List<CommondefinitionDto> getAddOption = empService.getAddOptions();
        model.addAttribute("optionList", getAddOption);
        model.addAttribute("projectMenu", "addProject");
        return "project-add";
    }

    // 프로젝트 등록
    @PostMapping("/project/save")
    public @ResponseBody String saveProject(@ModelAttribute ProjectRequestDto projectRequestDto) {
        System.out.println("projectRequestDto = " + projectRequestDto);
        int result = projectService.saveProject(projectRequestDto);

        return "project-list";
    }

    // 프로젝트 상세내역 직원 조회
    @GetMapping("/project/detail/{id}")
    public String projectDetail(Model model, @PathVariable String id,
                                @RequestParam(value = "cp", required = false, defaultValue = "1") int cp,
                                @RequestParam(value = "status", required = false, defaultValue = "") String status,
                                @RequestParam(value = "skill", required = false, defaultValue = "") String skill,
                                @RequestParam(value = "grade", required = false, defaultValue = "") String grade,
                                @RequestParam(value = "dateStart", required = false, defaultValue = "") String dateStart,
                                @RequestParam(value = "dateEnd", required = false, defaultValue = "") String dateEnd,
                                @RequestParam(value = "others", required = false, defaultValue = "") String others,
                                @RequestParam(value = "othersDetail", required = false, defaultValue = "") String othersDetail) throws IllegalAccessException {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.setStatus(status);
        searchRequest.setSkill(skill);
        searchRequest.setGrade(grade);
        searchRequest.setDateStart(dateStart);
        searchRequest.setDateEnd(dateEnd);
        searchRequest.setOthers(others);
        searchRequest.setOthersDetail(othersDetail);
        searchRequest.setProjectId(id);

        String query = searchRequest.toQueryString();

        List<ProjectResponseDto> getProjectDetail = projectService.getProjectDetail(id);
        boolean isSearch = !searchRequest.isEmpty();

        List<CommondefinitionDto> getAddOption = empService.getAddOptions();
        if (isSearch) {
            Map<String, Object> getEmpList = projectService.getEmpList(cp, searchRequest);
            model.addAttribute("empList", getEmpList.get("getEmpList"));
            model.addAttribute("pagination", getEmpList.get("pagination"));
        }

        model.addAttribute("optionList", getAddOption);
        model.addAttribute("searchRequest", searchRequest);
        model.addAttribute("query", query);
        model.addAttribute("projectDetail", getProjectDetail);
        model.addAttribute("projectMenu", "projectList");
        model.addAttribute("pId", id);

        return "project-detail";
    }

    // 프로젝트 정보 조회 (수정 하기 전 조회)
    @GetMapping("/project/edit/{id}")
    public String getProjectEditableInfo(Model model, @PathVariable String id) {
        List<ProjectResponseDto> getProjectDetail = projectService.getProjectDetail(id);
        List<CommondefinitionDto> getAddOption = empService.getAddOptions();
        model.addAttribute("projectDetail", getProjectDetail);
        model.addAttribute("optionList", getAddOption);
        model.addAttribute("projectMenu", "projectList");
        return "project-edit";
    }

    // 프로젝트 정보 수정
    @PostMapping("project/edit")
    public @ResponseBody String updateProject(@ModelAttribute ProjectRequestDto projectRequestDto) {

        System.out.println("projectRequestDto = " + projectRequestDto);
        int result = projectService.updateProject(projectRequestDto);

        return "project-list";
    }

    // 프로젝트 직원 추가창 이동
    @GetMapping("/project/getList/{pId}")
    public String empInfoList(Model model,
                              @PathVariable("pId") Long pId,
                              @RequestParam(value = "cp", required = false, defaultValue = "1") int cp,
                              @RequestParam(value = "status", required = false, defaultValue = "") String status,
                              @RequestParam(value = "skill", required = false, defaultValue = "") String skill,
                              @RequestParam(value = "grade", required = false, defaultValue = "") String grade,
                              @RequestParam(value = "dateStart", required = false, defaultValue = "") String dateStart,
                              @RequestParam(value = "dateEnd", required = false, defaultValue = "") String dateEnd,
                              @RequestParam(value = "others", required = false, defaultValue = "") String others,
                              @RequestParam(value = "othersDetail", required = false, defaultValue = "") String othersDetail,
                              @RequestParam(value = "projectId", required = false, defaultValue = "") String projectId
    ) throws IllegalAccessException {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.setStatus(status);
        searchRequest.setSkill(skill);
        searchRequest.setGrade(grade);
        searchRequest.setDateStart(dateStart);
        searchRequest.setDateEnd(dateEnd);
        searchRequest.setOthers(others);
        searchRequest.setOthersDetail(othersDetail);
        searchRequest.setProjectId(projectId);

        String query = searchRequest.toQueryString();

        System.out.println(searchRequest);

        System.out.println("========================================================= pId : " + pId);

        boolean isSearch = !searchRequest.isEmpty();

        List<CommondefinitionDto> getAddOption = empService.getAddOptions();
        if (isSearch) {
            Map<String, Object> getEmpList = empService.getEmpList(cp, searchRequest);
            model.addAttribute("empList", getEmpList.get("getEmpList"));
            model.addAttribute("pagination", getEmpList.get("pagination"));
        }
        model.addAttribute("optionList", getAddOption);
        model.addAttribute("activeMenu", "memberList");
        model.addAttribute("searchRequest", searchRequest);
        model.addAttribute("query", query);
        model.addAttribute("pId", pId);

        return "project-popup";
    }

    // 프로젝트 직원 추가
    @PostMapping("/project/add/member")
    public @ResponseBody String addEmp(
            @RequestParam(value = "empIdArr") String[] eArr,
            @RequestParam(value = "projectId") Long pId
    ) {
        AddEmpRequestDto addEmpRequestDto = new AddEmpRequestDto();
        System.out.println("eArr : " + Arrays.toString(eArr));
        System.out.println("pId : " + pId);

        if (eArr != null && eArr.length > 0) {
            for (int i = 0; i < eArr.length; i++) {
                System.out.println("eArr의 " + i + "번째 값은 : " + eArr[i] + "함꼐 넘어갈 pId : " + pId);
                addEmpRequestDto.setEmployeeId(eArr[i]);
                addEmpRequestDto.setProjectId(pId);
                System.out.println(i + 1 + "번쨰");

                int result = projectService.addEmp(addEmpRequestDto);
            }
        }
        return null;
    }
}

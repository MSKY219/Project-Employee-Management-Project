$(document).ready(function () {
    const addEmp = $('.addEmp');
    const pId = $('#pId');
    const pIdPop = $('#pId-pop');
    const closeBtn = $('.closeBtn');
    const editBtn = $('.editBtn');

    addEmp.on('click', () => {
        let openedWindow = window.open('/project/getList/' + pId.val(), 'test', 'width=1400px,height=700px,scrollbars=yes');
    });

    closeBtn.on('click', () => {
        window.history.back();
    });

    editBtn.on('click', () => {
        console.log("수정버튼 누름");
        let openedWindow = window.open('/project/editList/' + pId.val(), 'test', 'width=1400px,height=700px,scrollbars=yes');
    });

    // 체크 박스 선택
    const selectAll = $('#selectAll');
    const boxes = $('.boxes');
    const idArr = [];

    selectAll.on('change', (e) => {
        let checked = selectAll.prop('checked');
        console.log(checked);

        boxes.prop('checked', checked); // 모든 체크박스에 대해 상태 변경

        if (checked) {
            // 전체 선택 시 모든 pId를 배열에 추가
            idArr.length = 0; // 배열 비우기
            boxes.each(function () {
                let pId = $(this).val();
                idArr.push(pId);
            });
        } else {
            // 전체 해제 시 배열 비우기
            idArr.length = 0;
        }

        console.log("idArr : " + idArr);
    });

    boxes.on('change', (e) => {
        let clickTarget = $(e.currentTarget);
        let pId = clickTarget.val();

        if (clickTarget.prop('checked')) {
            idArr.push(pId);
        } else {
            let index = idArr.indexOf(pId);
            if (index !== -1) {
                idArr.splice(index, 1);
            }
        }

        console.log("idArr : " + idArr);
    });

    const deleteAll = $('.deleteAll');
    deleteAll.on('click', () => {
        console.log("삭제 버튼 누름");
        console.log("idArr : " + JSON.stringify(idArr));
        console.log("pIdPop : " + pIdPop.val());

        if (idArr.length == 0) {
            alert("한 명 이상의 직원을 선택해 주세요.")
            e.preventDefault();
            return false;
        }

        $.ajax({
            url: "/project/delete/member",
            data: {
                "empIdArr": idArr,
                "projectId": pIdPop.val()
            },
            type: "post",
            traditional: true,
            success: function (res) {
                console.log("성공");
                window.location.reload();
                alert("삭제 성공");
            },
            error: function () {
                console.log("실패");
            }
        });

    });
});
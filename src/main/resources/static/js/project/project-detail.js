$(document).ready(function () {


    const addEmp = $('.addEmp');
    const pId = $('#pId');
    const pIdPop = $('#pId-pop');
    const closeBtn = $('.closeBtn');

    addEmp.on('click', () => {
        console.log('추가 버튼 누름');
        console.log("pId : " + pId.val());
        let openedWindow = window.open('/project/getList/' + pId.val(), 'test', 'width=1400px,height=700px,scrollbars=yes');
    });

    closeBtn.on('click', () => {
        refreshing();
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
            // 체크된 경우 배열에 추가
            idArr.push(pId);
        } else {
            // 체크 해제된 경우 배열에서 제거
            let index = idArr.indexOf(pId);
            if (index !== -1) {
                idArr.splice(index, 1);
            }
        }

        console.log("idArr : " + idArr);
    });

    const addAll = $('.addAll');

    addAll.on('click', () => {
        console.log("추가 버튼 누름");
        console.log("idArr : " + JSON.stringify(idArr));
        console.log("pIdPop : " + pIdPop.val());

        $.ajax({
            url: "/project/add/member",
            data: {
                "empIdArr": idArr,
                "projectId": pIdPop.val()
            },
            type: "post",
            traditional: true,  // Set traditional to true for handling arrays
            success: function (res) {
                console.log("성공");
                refreshing();
            },
            error: function () {
                console.log("실패");
            }
        });

    });


});

function refreshing() {
    window.opener.location.reload();
    window.close();
}
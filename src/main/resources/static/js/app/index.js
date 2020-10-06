const main = {
    init : function () {
        const _this = this;
        /*
        * '등록' 버튼 클릭 시 등록 이벤트 처리
        * */
        $('#btn-save').on('click', () => {
            _this.save();
        });

        /*
        *   '수정 완료'버튼 클릭 시 수정 이벤트 처리
        * */
        $('#btn-update').on('click', () => {
            _this.update();
        })
    },
    save : function () {    //게시물 등록
        const data = {
            title : $('#title').val(),
            author:$('#author').val(),
            content:$('#content').val()
        };  //data

        $.ajax({
            type        : 'POST',
            url         : '/api/v1/posts',
            dataType    : 'json',
            contentType : 'application/json; charset=utf-8',
            data        : JSON.stringify(data)
        }).done(() => {
            alert("등록되었습니다.");
            window.location.href = "/";
        }).fail((error) => {
            alert(JSON.stringify(error));
        }); //ajax
    }, //save()
    update : function () {
        const data = {
                title : $('#title').val(),
                content : $('#content').val()
            }; //data
        const id = $('#id').val();

        $.ajax({
            type        : 'PUT',
            url         : '/api/v1/posts/'+id,
            dataType    : 'json',
            contentType : 'application/json; charset=utf-8',
            data        : JSON.stringify(data)
        }).done(() => {
            alert("수정되었습니다.");
            window.location.href = '/';
        }).fail((error) => {
            alert(JSON.stringify(error));
        }); //ajax
    } //update

} //main

main.init();
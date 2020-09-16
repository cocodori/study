const main = {
    init : function () {
        const _this = this;

        //등록
        $('#btn-save').on('click', function () {
            _this.save();
        });

        //수정
        $('#btn-update').on('click', function() {
            _this.update();
        })
    }, //init
    save : function () {
        const data = {
            title   : $('#title').val(),
            author  : $('#author').val(),
            content : $('#content').val()
        };

        $.ajax({
            type        : 'POST',
            url         : '/api/v1/posts',
            dataType    : 'json',
            contentType : 'application/json; charset=UTF-8',
            data        : JSON.stringify(data)
        }).done(function () {
            alert('등록되었습니다.');
            //글이 성공적으로 등록되었다면 index페이지로 이동한다.
            window.location.href = '/';
        }).fail(function(error){
            alert(JSON.stringify(error));
        }); //ajax
    }, //save
    update : function () {
        const data = {
            title   : $('#title').val(),
            content : $('#content').val()
        };

        const id = $('#id').val();

        $.ajax({
            type        : 'PUT',
            url         : '/api/v1/posts/'+id,
            dataType    : 'json',
            contentType : 'application/json; charset=UTF-8',
            data        : JSON.stringify(data)
        }).done(function(){
            alert('수정되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        })
    }
} //main

main.init();
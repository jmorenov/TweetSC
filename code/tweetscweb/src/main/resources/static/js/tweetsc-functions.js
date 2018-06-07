function ajaxPost(urlPath, formData, successFunction, errorFunction){
    $.ajax({
        type : "POST",
        contentType : "application/json",
        url : window.location.origin + urlPath,
        data : JSON.stringify(formData),
        dataType : 'json',
        success : function(result) {
            successFunction(result);
        },
        error : function(e) {
            errorFunction(e);
        }
    });
}


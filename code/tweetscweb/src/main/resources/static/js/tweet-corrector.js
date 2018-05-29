$( document ).ready(function() {

    // SUBMIT FORM
    $("#tweet-form").submit(function(event) {
        // Prevent the form from submitting via the browser.
        event.preventDefault();
        ajaxPost();
    });


    function ajaxPost(){

        // PREPARE FORM DATA
        var formData = {
            id: "",
            content : $("#tweet").val(),
            correctedContent: ""
        };

        // DO POST
        $.ajax({
            type : "POST",
            contentType : "application/json",
            url : window.location + "/api/tweet/corrector",
            data : JSON.stringify(formData),
            dataType : 'json',
            success : function(result) {
                if(result.status === "Done"){
                    $("#tweet-corrected").html(result.data.correctedContent);
                }else{
                    $("#tweet-corrected").html("<strong>Error</strong>");
                }
            },
            error : function(e) {
                console.log("ERROR: ", e);
            }
        });

        // Reset FormData after Posting
        resetData();

    }

    function resetData(){
        $("#tweet").val("");
    }
});
function successCorrection(result) {
    if(result.status === "Done"){
        $("#tweet-corrected").html(result.data.correctedContent);
    }else{
        errorCorrection();
    }
}

function errorCorrection(error) {
    $("#tweet-corrected").html("<strong>Error</strong>");
}

$( document ).ready(function() {
    $("#tweet-form").submit(function(event) {
        event.preventDefault();

        if ($("#tweet").val() !== "") {
            var formData = {
                id: "",
                content : $("#tweet").val(),
                correctedContent: ""
            };

            ajaxPost("/api/tweet/corrector", formData, successCorrection, errorCorrection);
        }
    });
});
function successCorrection(result) {
    if(result.status === "Done"){
        $("#simple-tweet-corrected").html(result.data.correctedText);
    }else{
        errorCorrection();
    }
}

function errorCorrection(error) {
    console.log(error);
}

$( document ).ready(function() {
    $("#tweet-form").submit(function(event) {
        event.preventDefault();

        if ($("#tweet").val() !== "") {
            var formData = {
                id: "",
                username: "",
                hash: "",
                text : $("#tweet").val()
            };

            $("#simple-tweet-corrected").html("Loading...");
            ajaxPost("/api/tweet/corrector", formData, successCorrection, errorCorrection);
        }
    });
});
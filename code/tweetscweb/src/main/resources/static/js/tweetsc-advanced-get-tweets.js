function successGet(result) {
    if(result.status === "Done"){
        //console.log(result.data.tweets);
        var me = this;
        result.data.tweets.forEach(function (tweet) {
            me.$("#tweets-list").append('<a href="#" class="list-group-item list-group-item-action flex-column align-items-start tweet-container">\n' +
                '            <div class="d-flex w-100 justify-content-between">\n' +
                '                <h6 class="mb-1">' + tweet.username + '</h6>\n' +
                '                <small>Date</small>\n' +
                '            </div>\n' +
                '            <span class="tweet">' + tweet.text + '</span>\n' +
                '        </a>')
        });
    }else {
        errorGet();
    }
}

function errorGet(error) {
    alert("ERROR");
}

$( document ).ready(function() {
    $("#search-tweets-form").submit(function(event) {
        event.preventDefault();

        if ($("#queryTweets").val() !== "") {
            var formData = {
                query : $("#queryTweets").val(),
                tweets: []
            };

            ajaxPost("/api/tweet/get", formData, successGet, errorGet);
        }
    });
});
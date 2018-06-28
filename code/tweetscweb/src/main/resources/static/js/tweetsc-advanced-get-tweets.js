var tweetsSearches = [];

function successGet(result) {
    $("#loading").hide();

    if(result.status === "Done"){
        if (result.data.tweets.length > 0) {
            var me = this;

            result.data.tweets.forEach(function (tweet) {
                me.$("#tweets-list").append('<a id="' + tweet.id + '" class="list-group-item list-group-item-action ' +
                    '           flex-column align-items-start tweet-container">\n' +
                    '            <div class="d-flex w-100 justify-content-between">\n' +
                    '                <h6 class="mb-1">' + tweet.username + '</h6>\n' +
                    '                <small>'+ tweet.date +'</small>\n' +
                    '            </div>\n' +
                    '            <pre class="tweet html-text">' + tweet.text + '</pre>\n' +
                    '        </a>');
                me.$("#" + tweet.id).click(tweet, toggleSelectTweet);
            });
            tweetsSearches = result.data.tweets;
        } else {
            $("#tweets-list").append('<div class="alert alert-info" role="alert">\n' +
                'Not tweets found.\n' +
                '</div>');
        }
    } else {
        errorGet(result);
    }
}

function errorGet(error) {
    $("#loading").hide();
    $("#tweets-list").append('<div class="alert alert-danger" role="alert"><strong>Error: </strong>' + error.status || error.message + '</div>');
}

function clearTweets() {
    tweetsSearches = [];
    tweetsSelected = [];
    $("#tweets-list").empty();
    $("#advanced-correct-tweets-button").prop('disabled', true);
}

function clearQuerySearch() {
    $("#queryTweets").val("");
}

$( document ).ready(function() {
    $("#advanced-search-tweets").submit(function(event) {
        event.preventDefault();

        if ($("#queryTweets").val() !== "") {
            var formData = {
                query : $("#queryTweets").val(),
                tweets: []
            };

            clearTweets();
            $("#loading").show();
            ajaxPost("/api/tweet/get", formData, successGet, errorGet);
        }
    });
});
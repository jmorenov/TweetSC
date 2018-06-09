var tweetsSearches = [];

function successGet(result) {
    $("#loading").hide();

    if(result.status === "Done"){
        if (result.data.tweets.length > 0) {
            var me = this;

            result.data.tweets.forEach(function (tweet) {
                me.$("#tweets-list-search").append('<a id="' + tweet.id + '" class="list-group-item list-group-item-action ' +
                    '           flex-column align-items-start tweet-container">\n' +
                    '            <div class="d-flex w-100 justify-content-between">\n' +
                    '                <h6 class="mb-1">' + tweet.username + '</h6>\n' +
                    '                <small>Date</small>\n' +
                    '            </div>\n' +
                    '            <span class="tweet">' + tweet.text + '</span>\n' +
                    '        </a>');
                me.$("#" + tweet.id).click(tweet, toggleSelectTweet);
            });
            tweetsSearches = result.data.tweets;
        } else {
            $("#tweets-list-search").append('<div class="alert alert-info" role="alert">\n' +
                'Not tweets found.\n' +
                '</div>');
        }
    } else {
        errorGet(result);
    }
}

function errorGet(error) {
    $("#loading").hide();
    $("#tweets-list-search").append('<div class="alert alert-danger" role="alert"><strong>Error: </strong>' + error.status || error.message + '</div>');
}

function clearTweets() {
    tweetsSearches = [];
    tweetsSelected = [];
    $("#tweets-list-result").empty();
    $("#tweets-list-search").empty();
    $("#advanced-correct-tweets").prop('disabled', true);
}

function clearQuerySearch() {
    $("#queryTweets").val("");
}

$( document ).ready(function() {
    $("#search-tweets-form").submit(function(event) {
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
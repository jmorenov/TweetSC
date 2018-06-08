function successAdvancedCorrection(result) {
    if(result.status === "Done"){
        var me = this;

        result.data.tweets.forEach(function (tweet) {
            me.$("#tweets-list-result").append('<div id="' + tweet.id + '" class="list-group-item ' +
                '           flex-column align-items-start tweet-container">\n' +
                '            <div class="d-flex w-100 justify-content-between">\n' +
                '                <h6 class="mb-1">' + tweet.username + '</h6>\n' +
                '                <small>Date</small>\n' +
                '            </div>\n' +
                '            <span class="tweet">' + tweet.text + '</span>\n' +
                '            <div class="alert alert-success" role="alert">' + tweet.correctedContent + '</div>' +
                '        </div>');
        });
    }else{
        errorAdvancedCorrection(result);
    }
}

function errorAdvancedCorrection(error) {
    $("#tweets-list-result").append('<div class="alert alert-danger" role="alert"><strong>Error: </strong>' + error.status || error.message + '</div>');
}

function openTweetCorrectionResultModal() {
    $("#advancedCorrectTweetModal").modal("toggle");
    $("#advancedCorrectTweetModalResult").modal("toggle");
}

$( document ).ready(function() {
    $("#advanced-correct-tweets-search-again").click(function(event) {
        clearTweets();
    });
});
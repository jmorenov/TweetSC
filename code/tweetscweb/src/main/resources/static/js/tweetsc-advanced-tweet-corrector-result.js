function successAdvancedCorrection(result) {
    $("#loading").hide();

    if(result.status === "Done"){
        var me = this;

        result.data.tweets.forEach(function (tweet) {
            me.$("#tweets-list").append('<div id="' + tweet.id + '" class="list-group-item ' +
                '           flex-column align-items-start tweet-container">\n' +
                '            <div class="d-flex w-100 justify-content-between">\n' +
                '                <h6 class="mb-1">' + tweet.username + '</h6>\n' +
                '                <small>'+ tweet.date +'</small>\n' +
                '            </div>\n' +
                '            <pre class="tweet html-text">' + tweet.text + '</pre>\n' +
                '            <pre class="alert alert-success html-text" role="alert">' + tweet.correctedText + '</pre>' +
                '        </div>');
        });
    }else{
        errorAdvancedCorrection(result);
    }
}

function errorAdvancedCorrection(error) {
    $("#loading").hide();
    $("#tweets-list").append('<div class="alert alert-danger" role="alert"><strong>Error: </strong>' + error.status || error.message + '</div>');
}

$( document ).ready(function() {
    $("#advanced-search-again-button").click(function(event) {
        openTweetSearchModal();
    });
});
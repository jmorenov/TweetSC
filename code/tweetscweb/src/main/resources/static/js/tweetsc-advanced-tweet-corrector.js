var tweetsSelected = [];

function toggleSelectTweet(event) {
    event.preventDefault();

    var element = $("#" + event.data.id);
    var activeClass = element.attr('class').search('active');

    if (activeClass !== -1) { //Selected
        element.removeClass('active');

        var tweetIndex = -1;
        tweetsSelected.forEach(function (tweet, index) {
            if (tweet.id === event.data.id) {
                tweetIndex = index;
            }
        });
        tweetsSelected.splice(tweetIndex, 1);
    } else {
        element.addClass('active');
        tweetsSelected.push(event.data);
    }

    if (tweetsSelected.length > 0) {
        $("#advanced-correct-tweets-button").prop('disabled', false);
    } else {
        $("#advanced-correct-tweets-button").prop('disabled', true);
    }
}

function openTweetSearchModal() {
    clearTweets();
    clearQuerySearch();
    $("#loading").hide();
    $("#advanced-correct-tweets-button").show();
    $("#advanced-search-again-button").hide();
    $("#advanced-search-tweets").show();
    $("#advanced-example-search").show();
}

function openTweetCorrectionResultModal() {
    clearTweets();
    clearQuerySearch();
    $("#loading").hide();
    $("#advanced-correct-tweets-button").hide();
    $("#advanced-search-again-button").show();
    $("#advanced-search-tweets").hide();
    $("#advanced-example-search").hide();
}

$( document ).ready(function() {
    $("#button-advanced-correct-tweet").click(function (event) {
        event.preventDefault();
        openTweetSearchModal();
    });

    $("#advanced-correct-tweets-button").click(function (event) {
        event.preventDefault();

        if (tweetsSelected.length > 0) {
            var formData = {
                query: "",
                tweets: tweetsSelected
            };

            ajaxPost("/api/tweet/advancedcorrector", formData, successAdvancedCorrection, errorAdvancedCorrection);
            openTweetCorrectionResultModal();
            $("#loading").show();
        }
    });
});
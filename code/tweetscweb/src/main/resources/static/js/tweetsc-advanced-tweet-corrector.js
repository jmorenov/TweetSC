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
        $("#advanced-correct-tweets").prop('disabled', false);
    } else {
        $("#advanced-correct-tweets").prop('disabled', true);
    }
}

$( document ).ready(function() {
    $("#advanced-correct-tweets").click(function(event) {
        event.preventDefault();

        if (tweetsSelected.length > 0) {
            var formData = {
                query: "",
                tweets: tweetsSelected
            };

            ajaxPost("/api/tweet/advancedcorrector", formData, successAdvancedCorrection, errorAdvancedCorrection);
            clearQuerySearch();
            clearTweets();
            openTweetCorrectionResultModal();
        }
    });
});
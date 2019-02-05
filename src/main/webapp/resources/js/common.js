$(document).keypress(function(e){
    if (e.which == 13){
        $("#save_post").click();
    }
});


$("#save_post").click(function() {
	
	var data = $("#chat-input").val();
	
	$('.chat-screen').append("<p>03:02:45 kokoko:" + data + "</p>");
	$("#chat-input").val('');
	
	$('.chat-screen').scrollTop($('.chat-screen')[0].scrollHeight);
})

$(document).ready(function() {
    $('.chat-screen').animate({
        scrollTop: $('.chat-screen').get(0).scrollHeight
    }, 2000);
});

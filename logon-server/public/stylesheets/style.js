$(window).resize(function () {
	$('.fill-height').each(function () {
		var $this = $(this);
		var marginTop = parseInt($this.css('margin-top'));
		var marginBot = parseInt($this.css('margin-bottom'));
		$this.outerHeight($this.parent().height() - marginTop - marginBot);
	});
});
$(document).ready(function() {
	$(window).resize();
});

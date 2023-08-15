$(function() {

	var itemsToShow = 20;
	var $accommodationItems = $(".accommodation_item");

	$accommodationItems.slice(itemsToShow).addClass("hidden");

	$("#loadMoreButton").click(function() {
		var $hiddenItems = $(".accommodation_item.hidden");

		$hiddenItems.slice(0, itemsToShow).removeClass("hidden");

		if ($hiddenItems.length <= itemsToShow) {
			$("#loadMoreButton").hide();
		}
	});

});
function showFilterModal() {
	var modal = document.getElementById('filterModal');
	modal.style.display = 'block';
}
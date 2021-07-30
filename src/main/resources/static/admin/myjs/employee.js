$('document').ready(function(){
//	$('table #editButton').on('click', function(event){
//
//
//		event.preventDefault();
//
//		// /countries/findById/?id=1
//		var href = $(this).attr('href');
//
//		$.get(href, function(employee, status){
//			$('#idEdit').val(country.id);
//			$('#descriptionEdit').val(country.description);
//			$('#capitalEdit').val(country.capital);
//			$('#codeEdit').val(country.code);
//			$('#continentEdit').val(country.continent);
//			$('#nationalityEdit').val(country.nationality);
//		});
//
//		$('#editModal').modal();
//	});

	$("table #deactiveButton").on('click',function(event){
		event.preventDefault();
		var href = $(this).attr('href');

		$('#confirmDeleteButton').attr('href', href);

		$('#deleteModal').modal();
	})
});
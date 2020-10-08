<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<fmt:setBundle basename="messages"/>

<script src='${initParam.staticfiles}/static/js/tinymce/tinymce.min.js'></script>
<script>



function gotoDetail(id)
{
	window.location.href = "${pageContext.request.contextPath}/controller/pages/alias/analysys/detail/"+id;
}


	$(document).ready(function() {
		tinymce.init({
			  selector: 'textarea',
			  menubar: false,
			  statusbar : false,
			
			  toolbar: 'undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | outdent indent ',
			  height : 285
			});
		
		$RIGHT_COL.css("min-height",$(window).height());
		
		
		
		
		
		 
	});
</script>
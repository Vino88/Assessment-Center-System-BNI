<script src='${initParam.staticfiles}/static/js/tinymce/tinymce.min.js'></script>
<script>
tinymce.init({
	  selector: 'textarea',
	  height : 500,
	  plugins: [
		    'image paste',
		  ],
		  file_browser_callback: function(field_name, url, type, win) {
			  
	            if(type=='image')
	            	{
	            		$('#fileTemp').click();
	            		$('#fileTemp').on('change', function() {
	            			var form = $("#imageForm");
	                      
	                        form.submit(function (event) {

	                            //disable the default form submission
	                            event.preventDefault();
	                            //grab all form data  
	                            var formData = new FormData(this);
	                            

	                            $.ajax({
	                                url: form.attr("action"),
	                                type: 'POST',
	                                data: formData,
	                                async: false,
	                                cache: false,
	                                contentType: false,
	                                processData: false,
	                                success: function (result) {
	                                	 win.document.getElementById(field_name).value = result.location;
	                                },
	                                error: function(){
	                                    alert("error in ajax form submission");
	                                }
	                            });

	                            return false;
	                        });
	                        
	                        form.submit();
	            		});
	            	}
	        }
	
	});
</script>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<fmt:setBundle basename="messages"/>
<style>
.alertify-logs {
    top: 75px;
    right: 42%;
}
</style>
<div id="dialog-example" title="Contoh" style="display:none">
<div autofocus></div>
<p>
Pertanyaan:<br/>
<b>Ceritakan salah satu pengalaman Anda ketika mengatasi penolakan salah satu bawahan Anda saat ia diberikan target yang lebih tinggi dari target sebelumnya.</b><br/><br/>
Dalam menjawab pertanyaan diatas Anda diharapkan menggunakan format <b>STAR</b> (atau <b>Situation/Task-Action-Result</b>). Untuk itu berikan jawaban Anda dengan panduan pertanyaan-pertanyaan berikut yaitu:<br/>

<ul>
	<li>
		<b>Situation/Task atau S/T</b>: Jelaskan dengan singkat latar belakang Situasi dari pengalaman Anda tersebut (misalnya: kapan, dimana, siapa saja yang terlibat, tantangan yang dihadapi, dan seterusnya.)<br/>
		Contoh Jawaban:<br/>
		<b>Pada posisi saat ini, saya membawahi 2 (dua) orang Manager dan 3 (tiga) orang Asisten Manager dengan tugas rutin masing-masing yang sudah sangat menyita waktu. Sekitar pertengahan bulan Juli 2016 yang lalu, Divisi SDM meminta kami untuk mengirimkan review atas employee cap atau jumlah maksimum pegawai yang dapat dipenuhi di setiap unit, dengan deadline di akhir Juli. Setiap tahunnya, data Manpower Planning (MPP) selalu kami submit pada awal Oktober sesuai dengan jadwal. Namun dengan adanya review ini, kami harus meminta sejumlah data kepada remote unit lebih awal.</b><br/><br/>
	</li>
	
	<li>
		<b>Action atau A</b>: Terkait Situasi diatas, aksi atau tindakan apa saja yang Anda lakukan saat itu. Jelaskan secara rinci, namun tidak berlebihan dalam penggunaan kata-kata.<br/>
		Contoh Jawaban:<br/>
		<b>Ketika saya meminta salah satu Manager yang mengelola data tersebut untuk mengerjakan permintaan dari Divisi SDM tersebut, ybs. menolak karena merasa tidak ada cukup waktu dengan beban kerja rutin yang telah ada, dan permintaan tersebut agak sulit untuk diselipkan dalam timeline kerja yang telah dibuat sebelumnya. Setelah mendengarkan dan memahami keluhan dan alasan keberatannya, saya mengumpulkan semua anak buah yang ada, dan menanyakan beban kerja dan deadline tugas setiap anak buah dalam waktu 2 minggu ke depan. Saya memimpin diskusi tentang tugas-tugas spesifik setiap orang yang bisa ditunda untuk dua minggu ke depan. Setelah mendapatkan informasi tersebut, saya mengalihkan pimpinan diskusi kepada Manager yang bertugas mengelola data MPP dan memintanya untuk dapat membuat pembagian tugas dan peran dari setiap orang dalam kelompok untuk dua minggu ke depan, sesuai dengan pengalihan prioritas tugas.</b><br/><br/>
	</li>
	
	<li>
		<b>Result atau R</b>: Terkait Aksi diatas, jelaskan  dampak yang terjadi atas aksi atau tindakan yang Anda jelaskan sebelumnya  (mis.  biaya, pendapatan, kepuasan pelanggan, perkembangan pasar, situasi kompetitif, kulitas dan integritas). <br/>
		Contoh Jawaban:<br/>
		<b>Manager tersebut senang dengan proses tersebut, dan bersedia memimpin proses pembuatan laporan serta menjanjikan akan menyelesaikan review employee cap tersebut sesuai dengan jadwal yang telah ditetapkan Divisi SDM.</b>
	</li>
</ul>
</div>

<script src='${initParam.staticfiles}/static/js/tinymce/tinymce.min.js'></script>

<input type="hidden" id="GLOBAL_FLAG_DISABLED_SAVE" value="false" />

<script>

var GLOBAL_LAST_SAVE = moment();

function showExample()
{
	$("#dialog-example").dialog({
	    resizable: false,
	    height: "auto",
	    width: 800,
	      
	    modal: true,
	  	buttons: {
	        "Close": function() {
	        	$( this ).dialog( "close" );
	    	}
	    }
	});
}


	
	$(document).ready(function() {
		
		
		tinymce.init({
			  selector: 'textarea',
			  menubar: false,
			  statusbar : false,
			
			  toolbar: 'undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | outdent indent | saveasdraft',
			  
			  height : 200,
			  
			  setup: function(editor) {
				  
				    
				    function saveAsDraft() {
				    	var GLOBAL_FLAG_DISABLED_SAVE = $("#GLOBAL_FLAG_DISABLED_SAVE").val();
				    	if(GLOBAL_FLAG_DISABLED_SAVE == "true" || GLOBAL_FLAG_DISABLED_SAVE == true)
			    		{
			    			alertify.alert("Mohon menunggu..")
			    		}
				    	else
			    		{
				    		tinyMCE.triggerSave();
				    		$("#GLOBAL_FLAG_DISABLED_SAVE").val("true");
				    		$.ajax({
								  type: "POST",
								  cache: false,
								  async : false,
								  url: "${pageContext.request.contextPath}/controller/rest/view/cap/save",
								  data: $("#capForm").serializeArray(),
								  dataType: "json",
								  success: function(data, textStatus)
								  {
									  GLOBAL_LAST_SAVE = moment();
									  $("#GLOBAL_FLAG_DISABLED_SAVE").val("false");
									  alertify.success("Saved successfully");
								  },
								
								});
			    		}
				    	
				    	
				    }

				    editor.addButton('saveasdraft', {
				      icon: 'save',
				      //image: 'http://p.yusukekamiyamane.com/icons/search/fugue/icons/calendar-blue.png',
				      tooltip: "Save As Draft",
				      onclick: saveAsDraft
				    });
				  }
			});
		$('#wizard_cap').smartWizard({
			
			labelFinish : '<fmt:message key="btn.submit"></fmt:message>',
			labelNext: '<fmt:message key="btn.next"></fmt:message>',
			labelPrevious: '<fmt:message key="btn.prev"></fmt:message>',
			
			enableAllSteps: true,
			onLeaveStep: function(curStep, context)
			{
				
				$("#fromStep").val(context.fromStep-1);
				$("#toStep").val(context.toStep-1);
				$("#lastTimer").val($('#timer').text());
				
				tinyMCE.triggerSave();
	    		$("#GLOBAL_FLAG_DISABLED_SAVE").val("true");
	    		$.ajax({
					  type: "POST",
					  cache: false,
					  async : true,
					  url: "${pageContext.request.contextPath}/controller/rest/view/cap/save",
					  data: $("#capForm").serializeArray(),
					  dataType: "json",
					  success: function(data, textStatus)
					  {
						  GLOBAL_LAST_SAVE = moment();
						  $("#GLOBAL_FLAG_DISABLED_SAVE").val("false");
						  
					  },
					
					});
				return true;
			},
			
			onFinish : function()
			{
				alertify.set({ labels: {
				    ok     : '<fmt:message key="btn.confirm.ok"></fmt:message>',
				    cancel : '<fmt:message key="btn.confirm.cancel"></fmt:message>'
				} });
				
				alertify.confirm('<p>Sistem akan mengunci jawaban Anda</p> <p>Anda tidak bisa mengubah kembali jawaban Anda</p> <p>Anda akan diarahkan ke halaman <i>Leadership Inventory</i></p> <p> Apakah Anda yakin akan mengirimkan jawaban Anda sekarang ?</p>', function (e) {
				    if (e) {
				    	tinyMCE.triggerSave();
				    	$("body").loading();
				    	$("#capForm").attr("action", "${pageContext.request.contextPath}/controller/pages/alias/cap/finish");
				    	$("#capForm").submit();
				    } else {
				        // user clicked "cancel"
				    }
				});
                
			}
			
			
		});

		$('.buttonNext').addClass('btn btn-success');
		$('.buttonPrevious').addClass('btn btn-primary');
		$('.buttonFinish').addClass('btn btn-default');
		
		
		
		$RIGHT_COL.css("min-height",$(window).height());
		
	});
	
	
</script>
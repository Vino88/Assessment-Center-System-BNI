<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<fmt:setBundle basename="messages"/>

<div class="main_container" role="main" >
	<div class="right_col">
	
		<div class="row">
			<div class="col-md-12 col-sm-12 col-xs-12">
           		<div class="x_panel">
	                <div class="x_title">
	                	<h2>Selamat Datang, ${sessionScope.session_auth_participant.fullName}</h2>
	                   	<div class="clearfix"></div>
	            	</div>
	               	<div class="x_content">
	                	<p>
	                     	Selamat, Anda terpilih untuk mengikuti program asesmen <i>online</i> yang diselenggarakan oleh tim BNI Corporate University (BNV). <br/>
	                   	</p>
	                    <p>
	                     	Selama asesmen berlangsung, Anda tidak diperkenankan untuk keluar dari laman asesmen ini sampai seluruh tugas Anda selesaikan. Tugas-tugas tersebut adalah sebagai berikut:
	                  	</p>
                  		<p>
	                  		<b><u>Program asesmen online ini terdiri dari dua sesi.
	                  		Saat ini adalah <c:if test='${sessionScope.session_auth_participant.participantStatus == "SESSION_I" }'>sesi pertama </c:if><c:if test='${sessionScope.session_auth_participant.participantStatus == "SESSION_II" }'>sesi kedua.</c:if> </u></b><br/>
	                  		<br>Berikut adalah detail sesi saat ini :
	                  	</p>
	                  	<br/>
	                     <div id="wizard_home" class="form_wizard wizard_horizontal">
		                      <ul class="wizard_steps">
		                      <c:if test='${sessionScope.session_auth_participant.participantStatus == "SESSION_I" }'>
		                        <li>
		                          <a href="#step-11">
		                            <span class="step_no">1</span>
		                          </a>
		                        </li>
		                        <li>
		                          <a href="#step-22">
		                            <span class="step_no">2</span>
		                          </a>
		                        </li>
		                         <li>
		                          <a href="#step-33">
		                            <span class="step_no">3</span>
		                          </a>
		                        </li>
		                        <li>
		                          <a href="#step-44">
		                            <span class="step_no">4</span>
		                          </a>
		                        </li>
		                        </c:if>
		                        
		                        <c:if test='${sessionScope.session_auth_participant.participantStatus == "SESSION_II" }'>
		                       
		                        
		                         <li>
		                          <a href="#step-55">
		                            <span class="step_no">5</span>
		                          </a>
		                        </li>
		                         <li>
		                          <a href="#step-66">
		                            <span class="step_no">6</span>
		                          </a>
		                        </li>
		                         <li>
		                          <a href="#step-77">
		                            <span class="step_no">7</span>
		                          </a>
		                        </li>
		                        <li>
		                          <a href="#step-88">
		                            <span class="step_no">8</span>
		                          </a>
		                        </li>
		                         <li>
		                          <a href="#step-99">
		                            <span class="step_no">9</span>
		                          </a>
		                        </li>
		                        </c:if>
		                      </ul>
		                      
		                      <c:if test='${sessionScope.session_auth_participant.participantStatus == "SESSION_I" }'>
		                      <div id="step-11">
		                        <h2 class="StepTitle">Data Diri</h2>
		                        <p>Isilah data pribadi Anda selengkap mungkin.</p>
		                      </div>
		                      <div id="step-22">
		                        <h2 class="StepTitle">Prestasi Karir</h2>
		                        <p>Untuk menjawab bagian ini, Anda diminta untuk memberikan jawaban dalam format STAR:</p>
		                     
		                        <ul>
		                        	<li><i>Situation or Task</i> atau S/T: Jelaskan dengan singkat latar belakang Situasi dari pengalaman Anda tsb. (misalnya : kapan, dimana, siapa saja yang terlibat, tantangan yang dihadapi, dst.)</li>
		                        	<li><i>Action</i> atau A: Terkait Situasi diatas, aksi atau tindakan apa saja yang Anda lakukan saat itu. Jelaskan secara rinci, namun tidak berlebihan dalam penggunaan kata-kata.</li>
		                        	<li><i>Result</i> atau R: adalah hasil dari aksi yang dilakukan (R): Terkait Aksi diatas, jelaskan  dampak yang terjadi atas aksi atau tindakan yang Anda jelaskan sebelumnya  (mis.  biaya, pendapatan, kepuasan pelanggan, perkembangan pasar, situasi kompetitif, kulitas dan integritas).</li>
		                        </ul>
								<p>Anda memiliki waktu <b>90 menit</b> untuk menyelesaikan tugas ini.</p>
								<br/>
								
		                      </div>
		                      
		                      <div id="step-33">
		                        <h2 class="StepTitle"><i>Leadership Inventory</i></h2>
		                        <p>Anda diminta untuk menjawab serangkaian pertanyaan sesuai instruksi yang disertakan pada setiap bagian. Terdapat 3 (tiga) bagian pertanyaan, dengan keseluruhan jumlah pertanyaan 70 (tujuh  puluh). Anda dibatasi waktu untuk menyelesaikannya, untuk itu periksalah jawaban Anda agar tidak ada yang tertinggal, sebelum pindah ke bagian selanjutnya. <br/>
		                        <br>Anda memiliki waktu <b>60 menit</b> untuk menyelesaikan tugas ini.
		                        </p>
		                      </div>
		                      
		                      <div id="step-44">
		                        <h2 class="StepTitle">Jabatan dan Aspirasi Karir</h2>
		                        <p>Isilah data jabatan saat ini dan aspirasi karir Anda.</p>
		                      </div>
		                      
		                      </c:if>
		                      
		                      
		                      <c:if test='${sessionScope.session_auth_participant.participantStatus == "SESSION_II" }'>
		                      
		                      
		                      <div id="step-55">
		                        <h2 class="StepTitle">Simulasi Pemecahan Masalah</h2>
		                        <p>Dalam simulasi ini Anda diminta menindak lanjuti 8 (delapan) surat atau <i>email</i>, sesuai pertanyaan-pertanyaan yang mengikuti setiap surat atau <i>email</i>. Anda diperbolehkan membaca keseluruhan surat atau <i>email</i>, sebelum menjawabnya satu per satu. Ingatlah bahwa Anda dibatasi waktu, namun demikian tetaplah menjawabnya dengan selengkap mungkin.<br/><br/>
		                        Anda memiliki waktu <b>90 menit</b> untuk menyelesaikan seluruh tugas ini.
		                        </p>
		                      </div>
		                      <div id="step-66">
		                        <h2 class="StepTitle">Simulasi Interaksi dengan Bawahan</h2>
		                        <p>Dalam simulasi ini Anda diminta untuk menindaklanjuti masalah yang disampaikan oleh bawahan Anda dengan membaca surat atau <i>email</i> terlampir dan menyimak video yang disertakan. <br/><br/>
		                        Anda memiliki waktu <b>15 menit</b> untuk menyelesaikan tugas ini.
		                        </p>
		                      </div>
		                       <div id="step-77">
		                        <h2 class="StepTitle">Simulasi Interaksi dengan Mitra Kerja</h2>
		                        <p>Dalam simulasi ini Anda diminta untuk menindaklanjuti masalah yang disampaikan oleh Mitra Kerja Anda dengan membaca surat atau <i>email</i> terlampir dan menyimak video yang disertakan. <br/><br/>
		                        Anda memiliki waktu <b>15 menit</b> untuk menyelesaikan tugas ini.
		                        </p>
		                      </div>
		                      <div id="step-88">
		                        <h2 class="StepTitle">Simulasi Rencana Bisnis</h2>
		                        <p>Dalam simulasi ini Anda diminta untuk melakukan analisa dan membuat rencana peningkatan bisnis sesuai pertanyaan-pertanyaan yang telah disiapkan. Ingatlah bahwa Anda dibatasi oleh waktu, jangan lupa menyimpan hasil kerja Anda setiap kali Anda selesai membuat jawaban. Jawablah dengan lengkap dan rinci. Setelah itu Anda akan diminta untuk merekam video berisikan penjelasan mengenai rencana bisnis Anda, selama maksimal 3 menit. Buatlah video sesuai dengan instruksi yang disediakan.<br/><br/>
		                        Anda memiliki waktu <b>90 menit</b> untuk menyelesaikan seluruh tugas ini.
		                        </p>
		                      </div>
		                      <div id="step-99">
		                        <h2 class="StepTitle">Simulasi Presentasi Visi</h2>
		                        <p>Setelah melakukan analisa dan membuat rencana peningkatan bisnis, Anda diminta untuk membuat presentasi dalam bentuk video selama maksimal <b>3 menit</b>. Pastikan Anda mempersiapkan diri dengan menjawab pertanyaan-pertanyaan yang telah disiapkan dan Anda diberikan kolom untuk membuat draft sebelum merekam presentasi Anda.
		                        </p>
		                      </div>
		                      </c:if>
                    		</div>
	                 </div>
	          	</div>
            </div>
        </div>
		<div class="clearfix"></div>
	</div>
</div>

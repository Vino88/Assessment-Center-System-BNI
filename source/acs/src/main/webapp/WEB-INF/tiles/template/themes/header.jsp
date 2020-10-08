		<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
		
		<div class="col-md-3 left_col menu_fixed">
          <div class="left_col scroll-view">
            <div class="navbar nav_title" style="border: 0; min-height: 95px;">
              <img src="${initParam.staticfiles}/static/images/logo.png" width="100%" style="margin-bottom: 5px; border-bottom: 5px solid #1abb9c;">
            </div>

            <div class="clearfix"></div>

            <!-- sidebar menu -->
            <div id="sidebar-menu" class="main_menu_side hidden-print main_menu" >
              <div class="menu_section">
                <ul class="nav side-menu">
                    <li><a href="${pageContext.request.contextPath}/controller/pages/view/home"><i class="fa fa-home"></i> Beranda</a></li>
                    
                    <c:if test='${sessionScope.session_auth_participant.participantStatus == "SESSION_I" }'>
	                   
	                    
		                 <c:choose>
		                	<c:when test='${empty sessionScope.session_participant_log_during.factsheetStartTime }'>
			                    
		                	</c:when>
		                	<c:when test='${empty sessionScope.session_participant_log_during.factsheetFinishTime }'>
			                    <li><a href="${pageContext.request.contextPath}/controller/pages/view/factsheet"><i class="fa fa-user"></i> Data Diri </a></li>
	                    
		                	</c:when>
		                	<c:otherwise>
		                		<li style="padding:13px 15px 12px;position:relative;display:block;"><i class="fa fa-user"></i> Data Diri <i class="fa fa-check"></i></li>
		                	</c:otherwise>
		                </c:choose>
		                
		                <c:if test='${not empty sessionScope.session_participant_log_during.factsheetFinishTime }' >
                    <c:choose>
		                	<c:when test='${empty sessionScope.session_participant_log_during.capFinishTime }'>
			                    <li><a href="${pageContext.request.contextPath}/controller/pages/view/cap/home"><i class="fa fa-edit"></i> Prestasi Karir </a></li>
		                	</c:when>
		                	<c:otherwise>
		                		<li style="padding:13px 15px 12px;position:relative;display:block;"><i class="fa fa-edit"></i> Prestasi Karir <i class="fa fa-check"></i></li>
		                	</c:otherwise>
		                </c:choose>
	                </c:if>
		                
		                 <c:if test='${not empty sessionScope.session_participant_log_during.capFinishTime }' >
                    <c:choose>
	                	<c:when test="${empty session_participant_log_during.lcbFinishTime }" >
		                    <li><a href="${pageContext.request.contextPath}/controller/pages/view/lcb/home"><i class="fa fa-edit"></i> Leadership Inventory</a></li>
	                	</c:when>
	                	<c:otherwise>
	                		<li style="padding:13px 15px 12px;position:relative;display:block;"><i class="fa fa-edit"></i> Leadership Inventory <i class="fa fa-check"></i></li>
	                	</c:otherwise>
	                </c:choose>
	                </c:if>
	                
	                <c:if test='${not empty sessionScope.session_participant_log_during.lcbFinishTime }' >
                    	<li><a href="${pageContext.request.contextPath}/controller/pages/view/aspiration"><i class="fa fa-edit"></i> Tanggung Jawab & Aspirasi</a></li>
	                </c:if>
	                
	                
                    </c:if>
                    
                    <c:if test='${sessionScope.session_auth_participant.participantStatus == "SESSION_II" }'>
                    
	                <c:if test='${sessionScope.session_participant_log_during.logDuringStatus == "INBASKET" || sessionScope.session_participant_log_during.logDuringStatus == "ANALYSYS" || sessionScope.session_participant_log_during.logDuringStatus == "VISION_SPEECH"  }' >
	                
	                
	               
	               
                    <li id="headerMenuSimulasi"><a><i class="fa fa-edit"></i> Simulasi <span class="fa fa-chevron-down"></span></a>
                        <ul class="nav child_menu">			                
                        	
                        	<c:if test='${sessionScope.session_participant_log_during.logDuringStatus == "INBASKET" }' >
                        	<c:choose>
                        	<c:when test="${empty session_participant_log_during.inbasketReadMemoStartTime }" >
                        	
                        	</c:when>
                        	<c:when test="${empty session_participant_log_during.inbasketStartTime }" >
                        		
                        	</c:when>
		                	<c:when test="${empty session_participant_log_during.inbasketFinishTime }" >
		                		
		                		<li><a href="${pageContext.request.contextPath}/controller/pages/view/inbasket/backgroundinfo"> Latar Belakang Organisasi</a></li>
			                    <li><a href="${pageContext.request.contextPath}/controller/pages/view/inbasket"> Simulasi Pemecahan Masalah</a></li>
			                    
			                    <c:choose>
			                    <c:when test="${not empty session_participant_log_during.inbasketVideoLinkOpened || countAnswer > 7 }">
			                    	<c:forEach items="${sessionScope.session_inbasket_video_list}" var="inboxVideo">
			                            <li id="headerMenuInbasketDetail"><a href="${pageContext.request.contextPath}/controller/pages/view/inbasket/detail/${inboxVideo.id}">
					                      ${inboxVideo.title }
					                    </a></li>
		                            </c:forEach>
		                            <li><a href="${pageContext.request.contextPath}/controller/pages/view/inbasket/beforefinish">Selesai</a></li>
			                    </c:when>
			                    
			                    <c:otherwise>
			                    		<c:forEach items="${sessionScope.session_inbasket_video_list}" var="inboxVideo">
			                            <li>
					                      ${inboxVideo.title }
					                    </li>
		                            </c:forEach>
					                    
					                    <li>Selesai</li>
			                    </c:otherwise>
			                    		
			                    </c:choose>
			                   
	                            
		                	</c:when>
		                	<c:otherwise>
		                		<li>Simulasi Pemecahan Masalah (Done)</li>
	                            <c:forEach items="${sessionScope.session_inbasket_video_list}" var="inboxVideo">
		                            <li>
				                      ${inboxVideo.title } (Done)
				                    </li>
	                            </c:forEach>
		                	</c:otherwise>
		                </c:choose>
		                </c:if>
		                
		                
		                <c:if test='${sessionScope.session_participant_log_during.logDuringStatus == "ANALYSYS" }' >
		                
		                <c:choose>
		                <c:when test="${empty session_participant_log_during.analysysStartTime }" >
                        		<li><a href="${pageContext.request.contextPath}/controller/pages/view/analysys/home"> Mulai</a></li>
                        	</c:when>
		                	<c:when test="${empty session_participant_log_during.analysysFinishTime }" >
	                            <li id="headerMenuAnalysisDetail"><a href="${pageContext.request.contextPath}/controller/pages/view/analysys"> Simulasi Rencana Bisnis</a></li>
	                            
	                            <li><a href="${pageContext.request.contextPath}/controller/pages/view/analysys/beforefinish">Selesai</a></li>
		                	</c:when>
		                	<c:otherwise>
		                		 <li>Simulasi Rencana Bisnis (Done)</li>
	                            <c:if test="${not empty sessionScope.session_vision_speech_first}" >
	                            <li>Simulasi Presentasi Visi (Done)</li>
	                            </c:if>
		                	</c:otherwise>
		                </c:choose>
		                </c:if>
                            
                            <c:if test='${sessionScope.session_participant_log_during.logDuringStatus == "VISION_SPEECH" }' >
		                
		                <c:choose>
		                <c:when test="${empty session_participant_log_during.visionStartTime }" >
                        		<li><a href="${pageContext.request.contextPath}/controller/pages/view/visionspeech/home"> Mulai</a></li>
                        	</c:when>
		                	<c:when test="${empty session_participant_log_during.visionFinishTime }" >
	                            <li><a href="${pageContext.request.contextPath}/controller/pages/view/visionspeech/detail/visionspeechvs/${sessionScope.session_vision_speech_first.id}"> Simulasi Presentasi Visi</a></li>
	                            <li><a href="${pageContext.request.contextPath}/controller/pages/view/visionspeech/beforefinish">Selesai</a></li>
		                	</c:when>
		                </c:choose>
		                </c:if>
                        </ul>
                    </li>  
                    </c:if>
                    </c:if>
                          
                  <li><a href="${pageContext.request.contextPath}/controller/pages/view/logout"><i class="fa fa-sign-out"></i> Logout </a></li>
                </ul>
              </div>

            </div>
            <!-- /sidebar menu -->

          </div>
        </div>
	
        <!-- top navigation -->
        <div class="top_nav">
          <div class="nav_menu">
            <nav>
			
              <ul class="nav navbar-nav navbar-right">
                <li class="">
                  <a href="javascript:;" class="user-profile dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                   
                    Menu <i class="fa fa-bars"></i>
                  </a>
                  <ul class="dropdown-menu dropdown-usermenu pull-right">
                  	<li><a href="${pageContext.request.contextPath}/controller/pages/view/home"><i class="fa fa-home pull-right"></i> Beranda</a></li>
                    <li><a href="${pageContext.request.contextPath}/controller/pages/view/logout"><i class="fa fa-sign-out pull-right"></i> Log Out</a></li>
                  </ul>
                </li>

                
              </ul>
            </nav>
          </div>
        </div>
        <!-- /top navigation -->

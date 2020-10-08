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
                    <li><a href="${pageContext.request.contextPath}/controller/pages/alias/home"><i class="fa fa-home"></i> Beranda </a></li>
                    <li><a href="${pageContext.request.contextPath}/controller/pages/alias/factsheet"><i class="fa fa-user"></i> Data Diri </a></li>
                    <li><a href="${pageContext.request.contextPath}/controller/pages/alias/cap"><i class="fa fa-edit"></i> Prestasi Karir </a></li>
                    <li><a href="${pageContext.request.contextPath}/controller/pages/alias/lcb"><i class="fa fa-edit"></i> Leadership Inventory</a></li>
                    
                    <li id="headerMenuSimulasi"><a><i class="fa fa-edit"></i> Simulasi <span class="fa fa-chevron-down"></span></a>
                        <ul class="nav child_menu">
                        <li><a href="${pageContext.request.contextPath}/controller/pages/alias/inbasket"> Simulasi Pemecahan Masalah</a></li>
			              <c:forEach items="${sessionScope.session_inbasket_video_list}" var="inboxVideo">
			                           <li id="headerMenuInbasketDetail"><a href="${pageContext.request.contextPath}/controller/pages/alias/inbasket/detail/${inboxVideo.id}">
				                      ${inboxVideo.title }
				                    </a></li>
			                          </c:forEach>     
		                    
		                    
		                    <li id="headerMenuAnalysisDetail"><a href="${pageContext.request.contextPath}/controller/pages/alias/analysys"> Simulasi Rencana Bisnis</a></li>
		                      <c:if test="${not empty sessionScope.session_vision_speech_first}" >
		                      	<li><a href="${pageContext.request.contextPath}/controller/pages/alias/analysys/detail/visionspeech/${sessionScope.session_vision_speech_first.id}"> Simulasi Presentasi Visi</a></li>
		                      </c:if>
                        </ul>
                        
                    </li>
                    
	                            
                  <li><a href="${pageContext.request.contextPath}/controller/pages/alias/logout"><i class="fa fa-sign-out"></i> Logout </a></li>
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
                  	<li><a href="${pageContext.request.contextPath}/controller/pages/alias/home">Halaman Utama</a></li>
                    <li><a href="${pageContext.request.contextPath}/controller/pages/alias/logout"><i class="fa fa-sign-out pull-right"></i> Log Out</a></li>
                  </ul>
                </li>

                
              </ul>
            </nav>
          </div>
        </div>
        <!-- /top navigation -->

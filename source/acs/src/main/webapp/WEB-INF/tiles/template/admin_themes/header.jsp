<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="col-md-3 left_col">
	<div class="left_col scroll-view">
		<div class="navbar nav_title" style="border: 0;min-height: 95px;">
			<img src="${initParam.staticfiles}/static/images/logo.png" width="100%" style="margin-bottom: 5px; border-bottom: 5px solid #1abb9c;">
		</div>
		
		<div class="clearfix"></div>
		
		<!-- sidebar menu -->
        <div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
			<div class="menu_section">
				<ul class="nav side-menu">
               		<li><a href="${pageContext.request.contextPath}/controller/pages/admin/home"><i class="fa fa-home"></i> Home </a></li>
                 	<c:if test='${session_auth_user_admin.role == "ADMINISTRATOR"}'>
		            	<li><a href="${pageContext.request.contextPath}/controller/pages/admin/administrator/participant/batch/inprogress/view"><i class="fa fa-calendar"></i> Register/In Progress Batch </a></li>
		               	<li></li>
		                
		                <li>
		                	<a><i class="fa fa-save"></i>Assessment Record<span class="fa fa-chevron-down"></span></a>
		                    <ul class="nav child_menu">
		                    	<li><a href="${pageContext.request.contextPath}/controller/pages/admin/administrator/participant/batch/view"><i class="fa fa-calendar"></i> Batch </a></li>
		                 		<li><a href="${pageContext.request.contextPath}/controller/pages/admin/administrator/assessement/participant/view"><i class="fa fa-user"></i> Participant </a></li>
		                 		
		                 		<li><a href="${pageContext.request.contextPath}/controller/pages/admin/administrator/assessement/assessor/view"><i class="fa fa-user"></i> Assessor </a></li>
		                    </ul>
		               	</li>
		                <li><a href="${pageContext.request.contextPath}/controller/pages/admin/administrator/competency/competencylibrarywritingassistance/view"><i class="fa fa-edit"></i> Writing Assistance </a></li>
		                <li></li>
						<li><a href="${pageContext.request.contextPath}/controller/pages/admin/administrator/masterdata/employee/view"><i class="fa fa-users"></i> Master Data Employee</a></li>
		                <li><a href="${pageContext.request.contextPath}/controller/pages/admin/administrator/credential/useradmin/view"><i class="fa fa-users"></i> User Admin</a></li>
                 	</c:if>
	               	<c:if test='${session_auth_user_admin.role == "OPERATOR"}'>
	                 
	                </c:if>
	                
	                
	                <c:if test='${session_auth_user_admin.role == "ASSESSOR"}'>
	                 	<li><a href="${pageContext.request.contextPath}/controller/pages/admin/assessor/participant/history/view"><i class="fa fa-user"></i> Participant History</a></li>
	                </c:if>
                 
                 	<li></li>
                  	<li><a href="${pageContext.request.contextPath}/controller/pages/admin/changepassword"><i class="fa fa-key"></i> Change Password</a></li> 
                  	<li><a href="${pageContext.request.contextPath}/controller/pages/admin/logout"><i class="fa fa-sign-out"></i> Log Out</a></li>
             	</ul>
          	</div>
          	
          	<c:if test='${session_auth_user_admin.role == "ADMINISTRATOR"}'>
          		<div class="menu_section">
             		<ul class="nav side-menu">
		               	<li>
							<a><i class="fa fa-table"></i>Master Data<span class="fa fa-chevron-down"></span></a>
		                    <ul class="nav child_menu">
		                    	<li>
				                 	<a>Competency Library<span class="fa fa-chevron-down"></span></a>
				                    <ul class="nav child_menu">
				                    	<li><a href="${pageContext.request.contextPath}/controller/pages/admin/administrator/competency/competencylibrary/view">Competency Library </a></li>
		                    			<li><a href="${pageContext.request.contextPath}/controller/pages/admin/administrator/competency/competencylibrarybehaviour/view">Behaviour </a></li>
				                    </ul>
			                    </li>
		                    	<li><a href="${pageContext.request.contextPath}/controller/pages/admin/administrator/cap/cap/view">Prestasi Karir </a></li>
		                      	<li><a href="${pageContext.request.contextPath}/controller/pages/admin/administrator/lcb/view">Inventory </a></li>
		                      	<li>
				                 	<a>Inbasket<span class="fa fa-chevron-down"></span></a>
				                    <ul class="nav child_menu">
				                    	<li><a href="${pageContext.request.contextPath}/controller/pages/admin/administrator/inbasket/inbasketinbox/view">Inbasket Inbox </a></li>
				                    	<li><a href="${pageContext.request.contextPath}/controller/pages/admin/administrator/inbasket/inbasketquestion/view">Inbasket Question </a></li>
				                    </ul>
			                    </li>
			                    <li><a href="${pageContext.request.contextPath}/controller/pages/admin/administrator/analysys/competencylibraryanalysys/view">Analysis </a></li>
		                    </ul>
						</li>
	                   	<li><a href="${pageContext.request.contextPath}/controller/pages/admin/administrator/config/systemconfig/view"><i class="fa fa-table"></i>System Config</a></li>
             		</ul>
          		</div>
          	</c:if>
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
	            		<li><a href="${pageContext.request.contextPath}/controller/pages/admin/home"><i class="fa fa-home pull-right"></i> Home</a></li>
	              		<li><a href="${pageContext.request.contextPath}/controller/pages/admin/logout"><i class="fa fa-sign-out pull-right"></i> Log Out</a></li>
	            	</ul>
	         	</li>
      		</ul>
    	</nav>
  	</div>
</div>
<!-- /top navigation -->
